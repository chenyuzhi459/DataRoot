package io.sugo.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by root on 16-12-5.
 */
public class Producer implements Runnable {

  private static Random rdint = new Random();

  private Properties props;
  private BlockingQueue<List<String>> msgQueue;
  private CountDownLatch latch;
  private int produced = 0;
  private AtomicInteger produceCnt;
  private int totalRows;

  public Producer(Properties props, BlockingQueue<List<String>> msgQueue, CountDownLatch latch, AtomicInteger produceCnt, int totalRows) {
    this.props = props;
    this.msgQueue = msgQueue;
    this.latch = latch;
    this.produceCnt = produceCnt;
    this.totalRows = totalRows;
  }

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    try {
      String action;
      int userCount = Integer.valueOf(props.getProperty("userCount", "1"));
      int pos = SugoRobot.totalCount.decrementAndGet();
      List<String> actions = new ArrayList<>(100);
      while (pos > -1) {
        action = ActionUtil.getAction(userCount, pos, totalRows);
        actions.add(action);
        if (actions.size() == 100) {
          msgQueue.put(actions);
          actions = new ArrayList<>(100);
        }
        produced++;
        pos = SugoRobot.totalCount.decrementAndGet();
      }
      if (!actions.isEmpty()) {
        msgQueue.put(actions);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      long end = System.currentTimeMillis();
      long spend = end - start;
      produceCnt.addAndGet(produced);
      System.out.println(Thread.currentThread().getName() + " produced:" + produced + " spend time:" + spend);
      latch.countDown();
    }
  }
}
