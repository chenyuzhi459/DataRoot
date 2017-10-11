package io.sugo.dynamic.column;

import io.sugo.smartbi.DataConst;
import io.sugo.smartbi.ProducerCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicDataCreater implements Closeable {
  private static final int ROW_COUNT = 10;
  private final static String COL_SEPARATOR = "\001";
  private final static String ROW_SEPARATOR = "\002";
  private static final String topic = "dynamic_test4";

  private KafkaProducer<Integer, String> producer;
  private int sended = 0;
  private AtomicInteger failed = new AtomicInteger();

  DynamicDataCreater() throws IOException {
    Properties props = new Properties();
    props.put("consumerNum", "8");
    props.load(new FileInputStream("kafka.properties"));
    producer = new KafkaProducer<>(props);
  }

  @Override public void close() throws IOException {
    producer.close();
    System.out.println("sended:" + sended + " time:" + new DateTime());
  }

  private static final String head = "\011";
  private static final Base64.Encoder encoder = Base64.getEncoder();

  public void work() throws Exception {
    int randTime = 10 * 24 * 60;//259200
    Random rand = new Random();
    DateTime now = new DateTime();
    StringBuilder builder;
    StringBuilder body;
    int seed = 3;
    for (int i = 0; i < ROW_COUNT; i++) {
      DateTime dt = now.minusMinutes(rand.nextInt(randTime)).minusSeconds(rand.nextInt(60));

      builder = new StringBuilder();
      builder.append(dt.getMillis()).append(head)
          .append("localhost").append(head)
          .append("locate=test&token=sugo").append(head)
          .append("cookie-" + i).append(head)
          .append(head)
          .append(head)
          .append("Apache-HttpClient/4.5.2 (Java/1.8.0_121)").append(head);

      //      public static final String FIELD_HEAD_TIMESTAMP = "d|sugo_timestamp";
      //      public static final String FIELD_HEAD_IP = "s|sugo_ip";
      //      public static final String FIELD_HEAD_ARGS = "s|sugo_args";
      //      public static final String FIELD_HEAD_HTTP_COOKIE = "s|sugo_http_cookie";
      //      public static final String FIELD_HEAD_HTTP_FORWARD = "s|sugo_http_forward";
      //      public static final String FIELD_HEAD_HTTP_REFER = "s|sugo_http_refer";
      //      public static final String FIELD_HEAD_USER_AGENT = "s|sugo_user_agent";
      //      (byte) 0x09
      //      Apache-HttpClient/4.5.2 (Java/1.8.0_121)

      body = new StringBuilder();
//      seed = rand.nextInt(100) % 3;
      switch (seed) {
      case 0:
        body.append("d|the_date,s|product,i|age,s|customer");
        for (int num = 0; num < 10; num++) {
          body.append(ROW_SEPARATOR)
              .append(dt.getMillis()).append(COL_SEPARATOR)
              .append(rand.nextInt(1551)).append(COL_SEPARATOR)
              .append(rand.nextInt(100)).append(COL_SEPARATOR)
              .append(rand.nextInt(100000) + 1);
        }
        break;
      case 1:
        body.append("d|the_date,s|promotion,i|store_id,f|store_sales");
        for (int num = 0; num < 10; num++) {
          body.append(ROW_SEPARATOR)
              .append(dt.getMillis()).append(COL_SEPARATOR)
              .append(rand.nextInt(2000) + 1).append(COL_SEPARATOR)
              .append(rand.nextInt(24) + 1).append(COL_SEPARATOR)
              .append(rand.nextFloat() * 100 + rand.nextInt(1000));
        }
        break;
      case 2:
        body.append("d|the_date,s|promotion1,i|store_id1,f|store_sales1");
        for (int num = 0; num < 10; num++) {
          body.append(ROW_SEPARATOR)
              .append(dt.getMillis()).append(COL_SEPARATOR)
              .append(rand.nextInt(2000) + 1).append(COL_SEPARATOR)
              .append(rand.nextInt(24) + 1).append(COL_SEPARATOR)
              .append(rand.nextFloat() * 100 + rand.nextInt(1000));
        }
        break;
      default:
        body.append("d|the_date,s|product_name,s|product_family,s|product_subcategory,s|city");
        for (int num = 0; num < 10; num++) {
          body.append(ROW_SEPARATOR)
              .append(dt.getMillis()).append(COL_SEPARATOR)
              .append(DataConst.product_family[rand.nextInt(DataConst.product_family.length)]).append(COL_SEPARATOR)
              .append(DataConst.product_category[rand.nextInt(DataConst.product_category.length)]).append(COL_SEPARATOR)
              .append(DataConst.product_subcategory[rand.nextInt(DataConst.product_subcategory.length)]).append(COL_SEPARATOR)
              .append(DataConst.cities[rand.nextInt(DataConst.cities.length)]);
        }
      }

      builder.append(encoder.encodeToString(body.toString().getBytes()));
      System.out.println(builder.toString());
//      Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(topic, builder.toString()), new ProducerCallback(failed));
      if (sended % 5000 == 0) {
        System.out.println("send:" + sended + " time:" + new DateTime());
        System.out.println(body.toString());
        System.out.println(builder.toString());
      }
      sended++;
    }

    System.out.println("failed:" + failed.get());
  }

  public static void main(String[] args) throws Exception {
    System.out.println(DataConst.fullname.length);
    DynamicDataCreater creater = new DynamicDataCreater();
    creater.work();
    creater.close();
    int randTime = 6 * 30 * 24 * 60;
    System.out.println(randTime);
    System.out.println(new DateTime().getMonthOfYear());
    System.out.println(DataConst.the_day[new DateTime().getDayOfWeek() - 1]);
    System.out.println("exit, time:" + new DateTime());
  }
}
