package io.sugo.smartbi;

import com.csvreader.CsvReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataAnalysis implements Closeable {
  //product_id,time_id,customer_id,promotion_id,store_id,store_sales,store_cost,unit_sales,warehouse_id,
  // stores_id,warehouse_country,warehouse_state_province,warehouse_city,warehouse_owner_name,product_name,
  // product_family,product_department,product_category,product_subcategory,the_year,quarter,the_month,the_day,
  // the_date,country,state_province,city,fullname

  private KafkaProducer<Integer, String> producer;
  private CsvReader data;
  private CsvReader bigData;
  private int rowCount = 0;
  private final String[] headers;
  private String topic = "smart_sugo";
  private int sended = 0;
  public static BlockingQueue<Future<RecordMetadata>> futureQueue = new ArrayBlockingQueue<>(100000);
  private FutureRunner runner;
  private Thread consumer;
  private AtomicInteger failed = new AtomicInteger();

  public DataAnalysis() throws Exception {
    Properties props = new Properties();
    props.put("consumerNum", "8");
    props.load(new FileInputStream("kafka.properties"));
    //1118900
    data = new CsvReader("/work/win7/smartBI/100w/data_gbk.csv", ',', Charset.forName("gbk"));
    //11382000
    bigData = new CsvReader("/work/win7/smartBI/data1000w/data.csv", ',', Charset.forName("utf-8"));
    //  12500900 12448843   12448115
    producer = new KafkaProducer<>(props);
    data.readHeaders();
    headers = data.getHeaders();
    for (int i = 0; i < headers.length; i++) {
      //      System.out.println("//" + headers[i]);
    }
    runner = new FutureRunner(futureQueue);
    consumer = new Thread(runner, "FutureRunner");
  }

  @Override public void close() throws IOException {
    data.close();
    bigData.close();
    producer.close();
    runner.shutdown();
    System.out.println("sended:" + sended + " time:" + new DateTime());
  }

  public void work() throws Exception {
    consumer.start();
    rowCount = 0;
    int randTime = 6 * 30 * 24 * 60;//259200
    Random rand = new Random();
    int recordCount = 1000000000;
    DateTime now = new DateTime();
    for (int i = 0; i < recordCount; i++) {
      StringBuilder builder = new StringBuilder();
      DateTime dt = now.minusMinutes(rand.nextInt(randTime)).minusSeconds(rand.nextInt(60));

      builder.append("the_date=").append(dt.getMillis()).append("&");
      builder.append("product_id=").append(rand.nextInt(1551)).append("&");
      builder.append("time_id=").append(rand.nextInt(1065) + 367).append("&");
      builder.append("customer_id=").append(rand.nextInt(100000) + 1).append("&");
      builder.append("promotion_id=").append(rand.nextInt(2000) + 1).append("&");
      builder.append("store_id=").append(rand.nextInt(24) + 1).append("&");
      Float sales = rand.nextFloat() * 100 + rand.nextInt(1000);
      builder.append("store_sales=").append(String.format("%.2f", sales + rand.nextInt(1000))).append("&");
      builder.append("store_cost=").append(String.format("%.2f", sales)).append("&");
      builder.append("unit_sales=").append(rand.nextInt(100)).append("&");
      builder.append("warehouse_id=").append(rand.nextInt(50) + 1).append("&");
      builder.append("stores_id=").append(rand.nextInt(24) + 1).append("&");
      builder.append("warehouse_country=").append(DataConst.warehouse_country[rand.nextInt(DataConst.warehouse_country.length)]).append("&");
      builder.append("warehouse_state_province=").append(DataConst.warehouse_state_province[rand.nextInt(DataConst.warehouse_state_province.length)]).append("&");
      builder.append("warehouse_city=").append(DataConst.warehouse_city[rand.nextInt(DataConst.warehouse_city.length)]).append("&");
      builder.append("warehouse_owner_name=").append("&");
      builder.append("product_name=").append(DataConst.product_name[rand.nextInt(DataConst.product_name.length)]).append("&");
      builder.append("product_family=").append(DataConst.product_family[rand.nextInt(DataConst.product_family.length)]).append("&");
      builder.append("product_department=").append(DataConst.product_department[rand.nextInt(DataConst.product_department.length)]).append("&");
      builder.append("product_category=").append(DataConst.product_category[rand.nextInt(DataConst.product_category.length)]).append("&");
      builder.append("product_subcategory=").append(DataConst.product_subcategory[rand.nextInt(DataConst.product_subcategory.length)]).append("&");
      builder.append("the_year=").append(dt.getYear() + "年").append("&");
      builder.append("quarter=").append(dt.getYear() + "年" + (dt.getMonthOfYear() % 4 + 1) + "季度").append("&");
      builder.append("the_month=").append(dt.getYear() + "年" + dt.getMonthOfYear() + "月").append("&");
      builder.append("the_day=").append(DataConst.the_day[new DateTime().getDayOfWeek() - 1]).append("&");
      builder.append("country=").append("USA").append("&");
      builder.append("state_province=").append(DataConst.state_province[rand.nextInt(DataConst.state_province.length)]).append("&");
      builder.append("city=").append(DataConst.cities[rand.nextInt(DataConst.cities.length)]).append("&");
      builder.append("fullname=").append(DataConst.fullname[rand.nextInt(DataConst.fullname.length)])
          .append(DataConst.sexs[rand.nextInt(DataConst.sexs.length)]);
      //      System.out.println(builder.toString());

      Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(topic, builder.toString()), new ProducerCallback(failed));
      //    ret.get();
      futureQueue.put(ret);
      //    ret.get();
      if (sended % 500000 == 0) {
        System.out.println("send:" + sended + " time:" + new DateTime());
      }
      sended++;
    }

    System.out.println("rowCount:" + rowCount);
    System.out.println("failed:" + failed.get());
  }


  private void dealRecord(String[] headers, CsvReader data) throws IOException, ExecutionException, InterruptedException {
    String value = "";
    String header = "";
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < headers.length; i++) {
      try {
        header = headers[i];
        value = data.get(headers[i]);
        if ("the_date".equals(header)) {
          DateTime dt = new DateTime(value);
          builder.append(header).append("=").append(dt.getMillis()).append("&");
        } else {
          builder.append(header).append("=").append(value).append("&");
        }
      } catch (Exception e) {
        System.out.println(headers[i] + "--" + value);
        System.out.println(builder.toString());
        System.out.println(e.getMessage());
      }
    }
    String action = builder.substring(0, builder.length() - 1);
    //    Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(topic, action), new ProducerCallback());
    Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(topic, action), new ProducerCallback(failed));
    //    ret.get();
    futureQueue.put(ret);
    //    ret.get();
    if (sended % 500000 == 0) {
      System.out.println("send:" + sended + " time:" + new DateTime());
    }
    sended++;

    //12447115
    //12445413
    //12500900
  }


  public static CountDownLatch latch = new CountDownLatch(1);

  public static void main(String[] args) throws Exception {
    System.out.println(DataConst.fullname.length);
    DataAnalysis bi = new DataAnalysis();
//    bi.work();
    bi.close();
    int randTime = 6 * 30 * 24 * 60;
    System.out.println(randTime);
    System.out.println(new DateTime().getMonthOfYear());
    System.out.println(DataConst.the_day[new DateTime().getDayOfWeek() - 1]);
//    latch.await();
    System.out.println("exit, time:" + new DateTime());
  }

}
