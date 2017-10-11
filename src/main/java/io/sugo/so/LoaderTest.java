package io.sugo.so;

import java.net.MalformedURLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoaderTest {
  public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
    Lock lock = new ReentrantLock();
    lock.lock();
    System.out.println("1");
    lock.lock();
    System.out.println("11");
    lock.unlock();
    System.out.println("2");
    lock.unlock();
    System.out.println("3");
//    URL[] urls = new URL[1];
//    File jar = new File("/work/druid/test/druid_sugo/extensions/druid-lucene-extensions/druid-lucene-extensions-0.9.1-SNAPSHOT-ENCRIPT.jar");
//    urls[0] = jar.toURI().toURL();
//    CustomClassLoader loader = new CustomClassLoader(urls, LoaderTest.class.getClassLoader());
//    Class clazz = Class.forName("io.druid.customanalysis.CustomAnalysisDruidModule");
//    Object config = ServiceLoader.load(clazz, loader);
//    System.out.println(config);
  }
}
