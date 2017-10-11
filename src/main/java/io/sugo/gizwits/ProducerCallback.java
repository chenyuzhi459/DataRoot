package io.sugo.gizwits;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Created by root on 16-12-7.
 */
public class ProducerCallback implements Callback {
  @Override
  public void onCompletion(RecordMetadata metadata, Exception e) {
    if (null != metadata) {
      // println("Sent message:" + count + "  sent to partition:" + metadata.partition() + ", offset:" + metadata.offset())
    } else {
      e.printStackTrace();
    }
  }
}
