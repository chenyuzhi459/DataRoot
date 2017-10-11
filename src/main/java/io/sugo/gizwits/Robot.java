package io.sugo.gizwits;


import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gary on 16-9-30.
 */
public class Robot {

  public static AtomicInteger totalCount;
  public static volatile boolean produceFinished = false;

  public static void main(String[] args) throws Exception {

    System.out.println(new DateTime().getMillis());
    if(new DateTime().getMillis() > 0){
      return;
    }

    Properties props = new Properties();
    props.put("myTopic", "gizwits");
    props.put("consumerNum", "8");

    int totalDevice = 1000000;

    props.load(new FileInputStream("kafka.properties"));
    System.out.println(props);

    int consumerNum = Integer.valueOf(props.getProperty("consumerNum", "6"));
    int producerNum = Integer.valueOf(props.getProperty("producerNum", "6"));

    AtomicInteger produceCnt = new AtomicInteger();
    AtomicInteger sendCnt = new AtomicInteger();

    BlockingQueue<List<String>> msgQueue = new ArrayBlockingQueue<>(1000);
    CountDownLatch latch = new CountDownLatch(consumerNum + producerNum);
    CountDownLatch syncLatch = new CountDownLatch(producerNum);

    AtomicLong[] produceTimes = new AtomicLong[producerNum];
    AtomicInteger[] produceNums = new AtomicInteger[producerNum];
    for(int i = 0; i<producerNum; i++){
      produceTimes[i] = new AtomicLong();
      produceNums[i] = new AtomicInteger();
    }

    AtomicInteger[] consumeNums = new AtomicInteger[consumerNum];
    for(int i = 0; i<consumerNum; i++){
      consumeNums[i] = new AtomicInteger();
    }

    long s1 = System.currentTimeMillis();

    List<Thread> producers = new ArrayList<>(producerNum);
    int sPos = 1;
    int ePos = totalDevice / producerNum;
    for (int i = 0; i < producerNum; i++) {
      Thread producer = new Thread(new Producer(props, msgQueue, latch, produceCnt,  syncLatch, sPos, ePos, produceTimes[i], produceNums[i]), "Producer-" + i);
      sPos = ePos;
      ePos += totalDevice / producerNum;
      producers.add(producer);
      producer.start();
    }

    List<Thread> consumers = new ArrayList<>(consumerNum);
    for (int i = 0; i < consumerNum; i++) {
      Thread consumer = new Thread(new Consumer(props, msgQueue, latch, sendCnt, syncLatch, consumeNums[i]), "Consumer-" + i);
      consumers.add(consumer);
      consumer.start();
    }

    Monitor monitor = new Monitor(msgQueue, produceTimes, produceNums, consumeNums);
    new Thread(monitor).start();

    latch.await();
    monitor.stop();

    long s2 = System.currentTimeMillis();
    long spend = s2 - s1;
    System.out.println(String.format("totalCount:[%s] spendTime:[%d] produced:[%d] send:[%d]",
        props.getProperty("totalCount", "1"), spend, produceCnt.get(), sendCnt.get()));
  }
}
