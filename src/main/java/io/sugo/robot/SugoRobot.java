package io.sugo.robot;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gary on 16-9-30.
 */
public class SugoRobot {

  public static AtomicInteger totalCount;

  public static void main(String[] args) throws Exception {
    Properties props = new Properties();
    props.put("myTopic", "sugoApp");
    props.put("consumerNum", "8");
    props.put("producerNum", "4");
    props.put("totalCount", "10000");
    props.put("userCount", "3000000");

    props.load(new FileInputStream("kafka.properties"));
    System.out.println(props.getProperty("bootstrap.servers"));

    int consumerNum = Integer.valueOf(props.getProperty("consumerNum", "1"));
    int producerNum = Integer.valueOf(props.getProperty("producerNum", "1"));
    totalCount = new AtomicInteger(Integer.valueOf(props.getProperty("totalCount", "1")));
    int totalRows = totalCount.get();

    AtomicInteger produceCnt = new AtomicInteger();
    AtomicInteger sendCnt = new AtomicInteger();

    BlockingQueue<List<String>> msgQueue = new ArrayBlockingQueue<>(100);
    CountDownLatch latch = new CountDownLatch(consumerNum + producerNum);

    long start = System.currentTimeMillis();
    List<Thread> consumers = new ArrayList<>(consumerNum);
    for (int i = 0; i < consumerNum; i++) {
      Thread consumer = new Thread(new Consumer(props, msgQueue, latch, sendCnt), "Consumer-" + i);
      consumers.add(consumer);
      consumer.start();
    }

    Monitor monitor = new Monitor(msgQueue, totalCount, totalRows);
    new Thread(monitor).start();

    List<Thread> producers = new ArrayList<>(producerNum);
    for (int i = 0; i < producerNum; i++) {
      Thread producer = new Thread(new Producer(props, msgQueue, latch, produceCnt, totalRows), "Producer-" + i);
      producers.add(producer);
      producer.start();
    }

    latch.await();
    monitor.stop();

    long end = System.currentTimeMillis();
    long spend = end - start;
    System.out.println(String.format("totalCount:[%s] spendTime:[%d] produced:[%d] send:[%d]",
        props.getProperty("totalCount", "1"), spend, produceCnt.get(), sendCnt.get()));
  }
}
