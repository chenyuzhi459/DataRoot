package io.sugo.so;

import sun.misc.Resource;
import sun.misc.URLClassPath;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.*;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

public class CustomClassLoader extends URLClassLoader
{
  static {
    System.load("/work/druid/test/druid_sugo/so/libcipher.so");
  }

  public enum ENCRPT_TYPE {ENCRIPT, DECRIPT}

  private URLClassPath ucp;
  private AccessControlContext acc;
  private Method getProtectionDomainMethod;

  public CustomClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
  }

  //系统方法
  protected Class<?> findClass(final String name)
      throws ClassNotFoundException
  {
    final Class<?> result;
    try {
      check();
      result = AccessController.doPrivileged(
          new PrivilegedExceptionAction<Class<?>>() {
            public Class<?> run() throws ClassNotFoundException {
              String path = name.replace('.', '/').concat(".class");
              Resource res = ucp.getResource(path, false);
              if (res != null) {
                try {
                  return defineClass(name, res);
                } catch (IOException e) {
                  throw new ClassNotFoundException(name, e);
                }
              } else {
                return null;
              }
            }
          }, acc);
    } catch (PrivilegedActionException pae) {
      throw (ClassNotFoundException) pae.getException();
    }
    if (result == null) {
      throw new ClassNotFoundException(name);
    }
    return result;
  }

  private Class<?> defineClass(String name, Resource res) throws IOException {
    long t0 = System.nanoTime();
    int i = name.lastIndexOf('.');
    URL url = res.getCodeSourceURL();
    if (i != -1) {
      String pkgname = name.substring(0, i);
      // Check if package already loaded.
      Manifest man = res.getManifest();
      definePackageInternal(pkgname, man, url);
    }
    // Now read the class bytes and define the class
    byte[] b = res.getBytes();
    // must read certificates AFTER reading bytes.
    CodeSigner[] signers = res.getCodeSigners();
    CodeSource cs = new CodeSource(url, signers);
    sun.misc.PerfCounter.getReadClassBytesTime().addElapsedTimeFrom(t0);
    return defineClassInternal(name, b, 0, b.length, cs);
  }

  //自定义方法 调用dll文件中的解密算法来解密class文件并装载到jvm中
  private native Class<?> defineClass0(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  private native Class<?> defineClass1(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2, ProtectionDomain paramProtectionDomain);


  protected final Class<?> defineClassInternal(String name, byte[] b, int off, int len, CodeSource cs)
  {
    Class clz;
    try
    {
      clz = defineClass1(name, b, off, len, getProtectionDomainInternal(cs));
    }
    catch (ClassFormatError ex)
    {
      return super.defineClass(name, b, off, len, getProtectionDomainInternal(cs));
    }
    if (clz == null)
    {
      return super.defineClass(name, b, off, len, getProtectionDomainInternal(cs));
    }
    return clz;
  }


  //系统方法 反射父类
  protected ProtectionDomain getProtectionDomainInternal(CodeSource cs)
  {
    try {
      return ((ProtectionDomain)this.getProtectionDomainMethod.invoke(this, new Object[] { cs }));
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  //自定义方法 检测父类的属性初始化给子类调用，使用java反射技术实现。
  private void check()
  {
    try {
      if (this.ucp == null)
      {
        Field fieldOfucp = getClass().getSuperclass().getDeclaredField("ucp");//获取private类型的字段
        fieldOfucp.setAccessible(true);//设置private类型修饰的字段可以访问
        this.ucp = ((URLClassPath)fieldOfucp.get(this));//访问private类型修饰的字段
      }
      if (this.acc == null)
      {
        Field fieldOfacc = getClass().getSuperclass().getDeclaredField("acc");
        fieldOfacc.setAccessible(true);
        this.acc = ((AccessControlContext)fieldOfacc.get(this));
      }
      if (this.getProtectionDomainMethod != null) return;
      this.getProtectionDomainMethod = getClass().getSuperclass().getSuperclass().getDeclaredMethod("getProtectionDomain", new Class[] { CodeSource.class });
      this.getProtectionDomainMethod.setAccessible(true);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }


  private void definePackageInternal(String pkgname, Manifest man, URL url)
  {
    if (getAndVerifyPackage(pkgname, man, url) == null) {
      try {
        if (man != null) {
          definePackage(pkgname, man, url);
        } else {
          definePackage(pkgname, null, null, null, null, null, null, null);
        }
      } catch (IllegalArgumentException iae) {
        // parallel-capable class loaders: re-verify in case of a
        // race condition
        if (getAndVerifyPackage(pkgname, man, url) == null) {
          // Should never happen
          throw new AssertionError("Cannot find package " +
              pkgname);
        }
      }
    }
  }

  private Package getAndVerifyPackage(String pkgname,
                                      Manifest man, URL url) {
    Package pkg = getPackage(pkgname);
    if (pkg != null) {
      // Package found, so check package sealing.
      if (pkg.isSealed()) {
        // Verify that code source URL is the same.
        if (!pkg.isSealed(url)) {
          throw new SecurityException(
              "sealing violation: package " + pkgname + " is sealed");
        }
      } else {
        // Make sure we are not attempting to seal the package
        // at this code source URL.
        if ((man != null) && isSealed(pkgname, man)) {
          throw new SecurityException(
              "sealing violation: can't seal package " + pkgname +
                  ": already loaded");
        }
      }
    }
    return pkg;
  }

  private boolean isSealed(String name, Manifest man) {
    String path = name.replace('.', '/').concat("/");
    Attributes attr = man.getAttributes(path);
    String sealed = null;
    if (attr != null) {
      sealed = attr.getValue(Name.SEALED);
    }
    if (sealed == null) {
      if ((attr = man.getMainAttributes()) != null) {
        sealed = attr.getValue(Name.SEALED);
      }
    }
    return "true".equalsIgnoreCase(sealed);
  }


}
