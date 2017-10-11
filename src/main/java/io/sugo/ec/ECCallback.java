package io.sugo.ec;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.atomic.AtomicInteger;

public class ECCallback implements Callback {
  private AtomicInteger failed;
  public ECCallback(AtomicInteger failed){
    this.failed = failed;
  }
  @Override
  public void onCompletion(RecordMetadata metadata, Exception e) {
    if (null != metadata) {
      // println("Sent message:" + count + "  sent to partition:" + metadata.partition() + ", offset:" + metadata.offset())
    } else {
      failed.incrementAndGet();
      e.printStackTrace();
    }
  }
}
