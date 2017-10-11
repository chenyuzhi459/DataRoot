package io.sugo.ec;

/**
 * Created by root on 17-2-27.
 */
public class ArrayTest {
  private static final int COUNT = 4000;
  private static final int NUM_COUNT = 10000000;

  public static void main(String[] args) {
    doWithArray();
    System.out.println("doWithArray end");
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

}
