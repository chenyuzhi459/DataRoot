package io.sugo.gizwits;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by root on 16-12-5.
 */
public class Consumer implements Runnable {
  private Properties props;
  private BlockingQueue<List<String>> msgQueue;
  private CountDownLatch latch;
  private final String myTopic;
  private KafkaProducer<Integer, String> producer;
  private AtomicInteger sendCnt;
  private CountDownLatch syncLatch;
  private AtomicInteger consumeNum;

  public Consumer(Properties props, BlockingQueue<List<String>> msgQueue, CountDownLatch latch, AtomicInteger sendCnt, CountDownLatch syncLatch, AtomicInteger consumeNum) {
    this.props = props;
    this.msgQueue = msgQueue;
    this.latch = latch;
    this.sendCnt = sendCnt;
    myTopic = props.get("myTopic").toString();
    producer = new KafkaProducer<>(props);
    this.syncLatch = syncLatch;
    this.consumeNum = consumeNum;
  }

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    try {
      List<String> actions;
      while (!msgQueue.isEmpty() || syncLatch.getCount() > 0) {
        try {
          actions = msgQueue.poll(3, TimeUnit.SECONDS);
          if (actions != null) {
            for (String action : actions) {
              Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(myTopic, action), new ProducerCallback());
//              ret.get();
//              producer.send(new ProducerRecord<>(myTopic, action), new ProducerCallback());
//              System.out.println(action);
              consumeNum.incrementAndGet();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } finally {
      producer.close();
      long end = System.currentTimeMillis();
      long spend = end - start;
      sendCnt.addAndGet(consumeNum.get());
      System.out.println(Thread.currentThread().getName() + " consumed:" + consumeNum.get() + " spend time:" + spend);
      latch.countDown();
    }
  }

}
