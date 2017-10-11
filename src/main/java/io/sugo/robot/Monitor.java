package io.sugo.robot;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by root on 16-12-5.
 */
public class Monitor implements Runnable {
  private BlockingQueue<List<String>> msgQueue;
  private volatile boolean stop = false;
  private long cnt = 0;
  private AtomicInteger totalCount;
  private int totalRows;

  public Monitor(BlockingQueue<List<String>> msgQueue, AtomicInteger totalCount, int totalRows) {
    this.msgQueue = msgQueue;
    this.totalCount = totalCount;
    this.totalRows = totalRows;
  }

  @Override
  public void run() {
    int last = totalRows;
    int remainded = 0;
    int idx = 1;
    while (!stop) {
      try {
        Thread.sleep(1000);
        cnt++;
        if (cnt % 10 == 0) {
          remainded = totalCount.get();
          System.out.println(String.format("%d remainder:%d, send:%d, msgQueue size:%d", idx++, remainded, last - remainded, msgQueue.size()));
          last = remainded;
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
