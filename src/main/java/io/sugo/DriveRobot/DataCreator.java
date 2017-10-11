package io.sugo.DriveRobot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class DataCreator implements Closeable {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static Properties props = new Properties();
    private static String mytopic = "driverOrder";

    private final KafkaProducer<Integer, String> producer;
    private final Random random = new Random();
    private static DateTime now;

    public DataCreator() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("kafka.properties"));

        mytopic = props.getProperty("topic");
        producer = new KafkaProducer<>(props);
    }

    @Override public void close() throws IOException {
        producer.close();
    }

    public List<String> generateData(int num, DateTime now, DateTime endTime, Map<Integer,Map<String,String>> oldUsers,Boolean isOldUser) throws JsonProcessingException {
        List<String> msgs = new ArrayList<>(10);

        //属性声明初始化
        //用户ID
        String distinct_id = "";
        //日期
        DateTime _time = new DateTime();
        //订单号
        String order_ID = "";
        //订车人手机号
        String book_tel = "";
        //乘车人手机号
        String rider_tel = "";
        //注册邀请码
        String reg_code = "";
        //注册渠道
        String reg_chan = "";
        //支付类型
        String payment_type = "";
        //订单金额
        int order_amount;
        //实付金额
        int paid_amount;
        //优惠金额
        int pre_amount;
        //优惠券编码
        String coupon_id = "";
        //优惠券名称
        String coupon_name = "";
        //优惠券所属活动ID
        String coupon_act_id = "";
        //优惠券所属活动名称
        String coupon_act_name = "";
        //该活动所属渠道
        String coupon_act_sour = "";
        //订单产品类型
        String order_type = "";
        //车型
        String car_model = "";
        //起点省份
        String start_province = "";
        //起点城市
        String start_city = "";
        //起点地区
        String start_region = "";
        //起点街道
        String start_street = "";
        //终点省份
        String end_province = "";
        //终点城市
        String end_city = "";
        //终点地区
        String end_region = "";
        //终点街道
        String end_street = "";
        //下单时间	time_place
        //预约时间	time_order
        //开始时间	time_start
        //结束时间	time_end
        //订单状态
        String order_state = "";
        //是否首单
        String order_first = "";
        //司机手机号
        String driver_tel = "";

        //页面起始时间
        DateTime start = now;
//        DateTime end = start.plusSeconds(random.nextInt(300));
        DateTime userTime = now;


        //判断是生成新用户，还是从老用户中取
        //老用户
        if (isOldUser) {
            //标示新用户的序号
            int oldUserIndex = random.nextInt(num);
            Map<String, String> userRecord = oldUsers.get(oldUserIndex);
            book_tel = userRecord.get("book_tel");
            reg_code = userRecord.get("reg_code");
            reg_chan = userRecord.get("reg_chan");
            distinct_id = userRecord.get("distinct_id");
            order_first = "不是首单";
        } else {
            //首单 用户id 预定电话 注册渠道 注册邀请码
            book_tel = Util.randomPhoneString();
            if (random.nextInt(100) < 65) {
                reg_code = Util.randomString(6);
            }
            reg_chan = Util.getNameByRate(DataConst.reg_chan);
            distinct_id = Util.getMd5Sum(num+"");
            order_first = "首单";

            Map<String,String> oldUser = new HashMap<String,String>();
            oldUser.put("book_tel",book_tel);
            oldUser.put("reg_code",reg_code);
            oldUser.put("reg_chan",reg_chan);
            oldUser.put("distinct_id",distinct_id);
            oldUsers.put(num,oldUser);
        }

        _time = new DateTime(now.getYear(),now.getMonthOfYear(),now.getDayOfMonth(),0,0,0);






        Map map = new HashMap<String,String>();
        map.put("s|order_first",order_first);
        map.put("s|book_tel",book_tel);
        if(reg_code!=null && reg_code.trim().length()!=0) {
            map.put("s|reg_code",reg_code);
        }
        map.put("s|reg_chan",reg_chan);
        map.put("s|distinct_id",distinct_id);
        msgs.add(jsonMapper.writeValueAsString(map));

        return msgs;
    }
}
