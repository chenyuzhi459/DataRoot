package io.sugo.map;

import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by root on 16-12-5.
 */
public class Monitor implements Runnable {
  private BlockingQueue<List<String>> msgQueue;
  private volatile boolean stop = false;
  private long cnt = 0;
  private AtomicLong[] produceTimes;
  private AtomicInteger[] produceNums;
  private AtomicInteger[] consumeNums;

  public Monitor(BlockingQueue<List<String>> msgQueue,
      AtomicLong[] produceTimes, AtomicInteger[] produceNums, AtomicInteger[] consumeNums) {
    this.msgQueue = msgQueue;
    this.produceTimes = produceTimes;
    this.produceNums = produceNums;
    this.consumeNums = consumeNums;
  }

  @Override
  public void run() {
    int idx = 1;
    long count = 0;
    long lastCnt = 0;
    int produced = 0;
    int cCount = 0;
    int lastCCnt = 0;
    while (!stop) {
      try {
        Thread.sleep(1000);
        if (cnt++ % 60 == 0) {
          System.out.println(new DateTime());
          for (int i = 0; i < produceTimes.length; i++) {
            produced = produceNums[i].get();
            count += produced;
            System.out.print(new DateTime(produceTimes[i].get()) + " : " + produced + "\t");
          }
          System.out.println();
          for (int i = 0; i < consumeNums.length; i++) {
            cCount += consumeNums[i].get();
          }
          System.out.println();
          System.out.println(
              String.format("%d msgQueue size:%d, produced:%d - %d consumed:%d - %d",
              idx++, msgQueue.size(), count, count - lastCnt, cCount, cCount - lastCCnt));
          lastCnt = count;
          count = 0;
          lastCCnt = cCount;
          cCount = 0;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stop() {
    stop = true;
  }
}
