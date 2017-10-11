package io.sugo.ec;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by root on 17-2-27.
 */
public class IntTest {
  //  private static final int COUNT = 100000000;
  private static final int COUNT = 4000;
  private static final int NUM_COUNT = 1000000;

  public static void main(String[] args) {
    doWithIntBuffer();
    System.out.println("doWithIntBuffer end");
    doWithArray();
    System.out.println("doWithArray end");
    doWithByteBuffer();
    System.out.println("doWithByteBuffer end");
  }

  private static void doWithByteBuffer() {
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(COUNT * 4);
    long s1 = System.currentTimeMillis();
    for (int i = 0; i < COUNT; i++) {
      byteBuffer.putInt(i);
    }
    long s2 = System.currentTimeMillis();
    System.out.println(s2 - s1);

    byteBuffer.flip();
    long sum = 0;
    s2 = System.currentTimeMillis();
    for (int num = 0; num < NUM_COUNT; num++) {
      sum = 0;
      for (int i = 0; i < COUNT * 4; i += 4) {
        sum += byteBuffer.getInt(i);
      }
    }
    long s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum);

    long sum1 = 0;
    long sum2 = 0;
    long sum3 = 0;
    long sum4 = 0;
    s2 = System.currentTimeMillis();
    for (int num = 0; num < NUM_COUNT; num++) {
      sum1 = 0;
      sum2 = 0;
      sum3 = 0;
      sum4 = 0;
      for (int i = 0; i < COUNT * 4; i += 16) {
        sum1 += byteBuffer.getInt(i);
        sum2 += byteBuffer.getInt(i + 4);
        sum3 += byteBuffer.getInt(i + 8);
        sum4 += byteBuffer.getInt(i + 12);
      }
    }
    s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum1 + sum2 + sum3 + sum4);
  }

  private static void doWithArray() {
    int[] buffer = new int[COUNT];
    long s1 = System.currentTimeMillis();
    for (int i = 0; i < COUNT; i++) {
      buffer[i] = i;
    }
    long s2 = System.currentTimeMillis();
    System.out.println(s2 - s1);

    long sum = 0;
    s2 = System.currentTimeMillis();
    for (int num = 0; num < NUM_COUNT; num++) {
      sum = 0;
      for (int i = 0; i < COUNT; i++) {
        sum += buffer[i];
      }
    }
    long s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum);

    long sum1 = 0;
    long sum2 = 0;
    long sum3 = 0;
    long sum4 = 0;
    s2 = System.currentTimeMillis();
    for (int num = 0; num < NUM_COUNT; num++) {
      sum1 = 0;
      sum2 = 0;
      sum3 = 0;
      sum4 = 0;
      for (int i = 0; i < COUNT; i += 4) {
        sum1 += buffer[i];
        sum2 += buffer[i + 1];
        sum3 += buffer[i + 2];
        sum4 += buffer[i + 3];
      }
    }
    s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum1 + sum2 + sum3 + sum4);
  }

  private static void doWithIntBuffer() {
    IntBuffer buffer = IntBuffer.allocate(COUNT);
    //    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10000 * 1000 * 4);
    long s1 = System.currentTimeMillis();
    for (int i = 0; i < COUNT; i++) {
      buffer.put(i);
    }
    long s2 = System.currentTimeMillis();
    System.out.println(s2 - s1);

    //    byteBuffer.flip();
    buffer.flip();
    s2 = System.currentTimeMillis();
    long sum = 0;
    for (int num = 0; num < NUM_COUNT; num++) {
      sum = 0;
      for (int i = 0; i < COUNT; i++) {
        sum += buffer.get(i);
      }
    }
    long s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum);

    long sum1 = 0;
    long sum2 = 0;
    long sum3 = 0;
    long sum4 = 0;
    s2 = System.currentTimeMillis();
    for (int num = 0; num < NUM_COUNT; num++) {
      sum1 = 0;
      sum2 = 0;
      sum3 = 0;
      sum4 = 0;
      for (int i = 0; i < COUNT; i += 4) {
        sum1 += buffer.get(i);
        sum2 += buffer.get(i + 1);
        sum3 += buffer.get(i + 2);
        sum4 += buffer.get(i + 3);
      }
    }
    s3 = System.currentTimeMillis();
    System.out.println(s3 - s2);
    System.out.println(sum1 + sum2 + sum3 + sum4);
  }
}
