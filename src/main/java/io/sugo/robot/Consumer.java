package io.sugo.robot;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
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
  private int consumed = 0;
  private AtomicInteger sendCnt;

  public Consumer(Properties props, BlockingQueue<List<String>> msgQueue, CountDownLatch latch, AtomicInteger sendCnt) {
    this.props = props;
    this.msgQueue = msgQueue;
    this.latch = latch;
    this.sendCnt = sendCnt;
    myTopic = props.get("myTopic").toString();
    producer = new KafkaProducer<>(props);
  }

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    try {
      List<String> actions;
      while (SugoRobot.totalCount.get() > -1 || !msgQueue.isEmpty()) {
        try {
          actions = msgQueue.poll(3, TimeUnit.SECONDS);
          if (actions != null) {
            for (String action : actions) {
              producer.send(new ProducerRecord<>(myTopic, action), new ProducerCallback());
              consumed++;
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } finally {
      long end = System.currentTimeMillis();
      long spend = end - start;
      sendCnt.addAndGet(consumed);
      System.out.println(Thread.currentThread().getName() + " consumed:" + consumed + " spend time:" + spend);
      latch.countDown();
    }
  }

  class ProducerCallback implements Callback{
    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
      if (null != metadata) {
        // println("Sent message:" + count + "  sent to partition:" + metadata.partition() + ", offset:" + metadata.offset())
      } else {
        e.printStackTrace();
      }
    }
  }
}
