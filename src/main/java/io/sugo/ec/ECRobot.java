package io.sugo.ec;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static io.sugo.ec.ECConst.*;

public class ECRobot {
  private static int randTime = 80 * 24 * 60;
  private static Random rand = new Random();
  private static int recordCount;

  public static void main(String[] args) throws Exception {
    work();
//    for (int i = 0; i < 20; i++) {
//      System.out.println(buildMessage());
//    }
  }

  public static void work() throws Exception {
    Properties props = new Properties();
    props.load(new FileInputStream("kafka.properties"));
    props.put("bootstrap.servers", "192.168.0.215:9092,192.168.0.217:9092");
    String topic = props.getProperty("topic", "ec_sugo");
    recordCount = Integer.valueOf(props.getProperty("recordCount", "10000000"));
    KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

    AtomicInteger failed = new AtomicInteger();
    int send = 0;

    for (int i = 0; i < recordCount; i++) {
      String msg = buildMessage();
      Future<RecordMetadata> ret = producer.send(new ProducerRecord<>(topic, msg), new ECCallback(failed));
      if (send % 500000 == 0) {
        System.out.println("send:" + send + " time:" + new DateTime() + " - " + startTime);
      }
      send++;
    }

    producer.close();
    System.out.println("failed:" + failed.get());
  }

  private static DateTime startTime;

  private static String buildMessage() {
    StringBuilder builder = new StringBuilder();
    DateTime now = new DateTime();
    startTime = now.minusMinutes(rand.nextInt(randTime)).minusSeconds(rand.nextInt(60));

    builder.append("f_corp_id=").append(rand.nextInt(10000) + 1000000).append("&");
    builder.append("f_waste_id=").append(String.format("100%10d%04d", startTime.getMillis() / 1000, rand.nextInt(10000))).append("&");
    String[] tmp = f_types[rand.nextInt(f_types.length)].split("_");
    String f_type = tmp[0];
    String f_call_type = tmp[1];
    String f_in_out_type = tmp[2];
    String f_type_name = tmp[3];
    int f_mp3_len = rand.nextInt(300000) + 10000;
    int f_Calltime = f_mp3_len / 1000;
    DateTime endTime = startTime.plusMillis(f_mp3_len);
    DateTime f_time = endTime.plusMillis(rand.nextInt(1000));
    float f_money = 0;
    String callNo = String.format("%s%04d%04d",
        f_calltono[rand.nextInt(f_calltono.length)],
        rand.nextInt(10000),
        rand.nextInt(10000)
    );
    //呼出
    int f_result = 0;
    if ("2".equals(f_in_out_type)) {
      f_money = f_Calltime * 0.03f;
      builder.append("f_money=").append(String.format("%.2f", f_money)).append("&");
      builder.append("f_callno=").append(phones[rand.nextInt(phones.length)]).append("&");
      builder.append("f_calltono=").append(callNo).append("&");
      f_result = 2;
    } else {
      builder.append("f_money=").append(0).append("&");
      builder.append("f_callno=").append(callNo).append("&");
      builder.append("f_calltono=").append(phones[rand.nextInt(phones.length)]).append("&");
    }
    builder.append("f_type=").append(f_type).append("&");
    builder.append("f_type_name=").append(f_type_name).append("&");
    builder.append("f_Starttime=").append(startTime.getMillis()).append("&");
    builder.append("f_Endtime=").append(endTime.getMillis()).append("&");
    builder.append("f_Calltime=").append(f_Calltime).append("&");
    builder.append("f_status=").append(0).append("&");
    builder.append("f_num=").append(f_nums[rand.nextInt(f_nums.length)]).append("&");
    builder.append("f_result=").append(f_result).append("&");
    builder.append("f_time=").append(f_time.getMillis()).append("&");
    int userId = rand.nextInt(10000) + 1000000;
    builder.append("f_user_id=").append(userId).append("&");
    builder.append("f_is400=").append(0).append("&");
    builder.append("f_crm_log=").append("&");
    builder.append("f_code=").append("&");
    String path = String.format("/os/%d/%02d/%02d/%d_%d_%04d.mp3",
        f_time.getYear(),
        f_time.getMonthOfYear(),
        f_time.getDayOfMonth(),
        f_time.getMillis() / 1000,
        userId,
        rand.nextInt(10000)
    );
    builder.append("f_path=").append(path).append("&");
    builder.append("f_crm_id=").append(String.format("%d", rand.nextInt(10000) + 100000)).append("&");
    builder.append("f_telbox_id=").append(0).append("&");
    builder.append("f_mp3_len=").append(f_mp3_len).append("&");
    builder.append("f_in_out_type=").append(f_in_out_type).append("&");
    builder.append("f_call_type=").append(f_call_type);


    //    字段	类型	中文名称	样例	取值范围
    //    `f_money`	decimal(11,2)	'通话金额',	0	0~20.00
    //    `f_type`	int(11)	类型'	25	1~28
    //    `f_callno`	varchar(20)	'主叫号码',		是否也是手机号码？
    //    `f_calltono`	varchar(20)	'被叫号码',	15067782858	手机号码（11位，以180、159、130、189、139、150等开头）
    //    `f_Starttime`	datetime	'开始时间',	2017/2/24 9:14	精确到毫秒
    //    `f_Endtime`	datetime	'结束时间',	2017/2/24 9:15	精确到毫秒
    //    `f_Calltime`	varchar(20)	'通话时长',	47
    //    `f_status`	tinyint(4)	'电话状态',	0	取值范围？
    //    `f_num`	varchar(20)	'区号',	755	取值范围？
    //    `f_result`	tinyint(4)	处理结果	0	0,2
    //    `f_time`	datetime	'处理时间',	2017/2/24 9:15	是否可以作为时间序列？
    //    `f_user_id`	bigint(20)	'用户',	1001877
    //    `f_is400`	tinyint(4)	'1为400电话',	0	是否全为0？
    //    `f_crm_log`	varchar(20)	'通话记录所需值',		取值范围？
    //    `f_code`	varchar(20)	'计费代码',
    //    `f_path`	varchar(200)	'语音文件路径',	/os/2017/02/24/1487898882_1001877_0224.mp3
    //    `f_crm_id`	bigint(20)	'客户库id',	0	取值范围？
    //    `f_telbox_id`	int(10)	'总机id',	0	取值范围？
    //    `f_mp3_len`	int(11)	'录音时长',	47520	毫秒
    //    `f_in_out_type`	tinyint(4)	0:未知，1:为呼入类型，2:呼出类型',	2	0,1,2
    //    `f_call_type`	tinyint(4)		1	0~5

    return builder.toString();

  }


}
