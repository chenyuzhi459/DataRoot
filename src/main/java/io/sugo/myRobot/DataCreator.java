package io.sugo.myRobot;

import io.sugo.myRobot.DataConst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.htrace.impl.MilliSpan;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import static io.sugo.myRobot.DataConst.*;

public class DataCreator implements Closeable {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static Properties props = new Properties();
    private static String mytopic = "testRealTime000";

    private final KafkaProducer<Integer, String> producer;
    private final Random random = new Random();
    private static DateTime now;
    private MessageDigest md;

    public DataCreator() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("kafka.properties"));

        mytopic = props.getProperty("topic");
        producer = new KafkaProducer<>(props);
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static final String base = "abcdefghijklmnopqrstuvwxyz0123456789";

    public String randomString(int length) { //length表示生成字符串的长度
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public String randomString(String str, int length) { //length表示生成字符串的长度
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public void send(String msg) {
        producer.send(new ProducerRecord<>(mytopic, msg));
    }

    @Override public void close() throws IOException {
        producer.close();
    }

    private String getMd5Sum(String pos) {
        StringBuffer sb = new StringBuffer();
        md.reset();
        md.update(pos.getBytes());
        byte[] digest = md.digest();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    //SessionID              SESSION_ID
    //用户ID                  USER_ID                                   STRING
    //国家                    ADDR_COUNTRY                              STRING
    //省份                    ADDR_PROVINCE                             STRING
    //设备                    EQUIPMENT_TYPE                            STRING
    //分辨率                  EQUIPMENT_RESOLVING               STRING
    //浏览器名称               COMPUTER_BROWSER_TYPE                     STRING
    //浏览器版本               COMPUTER_BROWSER_VERSION                  STRING
    //手机运营商               CELLPHONE_OPERATOR                        STRING
    //手机品牌                CELLPHONE_BRAND                            STRING
    //手机品牌型号             CELLPHONE_VERSION                          STRING

    //页面名称                PAGE_NAME                                 STRING
    //页面操作名称            PAGE_ACTION                               STRING
    //服务器端时间            ACTION_TIME                               DATE
    //停留时间                STAY_TIME                                DATE


    public List<String> generateData(int num, DateTime now, DateTime endTime, Map<Integer,Map<String,Object>> oldUsers,Boolean isOldUser) throws JsonProcessingException {
        List<String> msgs = new ArrayList<>(10);

        //属性声明初始化
        String user_id = "";
        String source = "";
        String addr_country = "";
        String addr_province = "";
        String equipment_type="";
        String equipment_resolving="";
        int equipment_resolving_length=0;
        int equipment_resolving_width=0;
        String session_id="";

        String computer_browser_type="";
        String computer_browser_version="";
        String cellphone_operator="";
        String cellphone_brand="";
        String cellphone_version="";
        String cellphone_id="";

        String web_brand="";
        String web_model="";

        //页面起始时间
        DateTime start = now;
//        DateTime end = start.plusSeconds(random.nextInt(300));
        DateTime userTime = now;


        //判断是生成新用户，还是从老用户中取
            //老用户
        if(isOldUser) {
            //标示新用户的序号

            int oldUserIndex = random.nextInt(num);
            Map<String,Object> userRecord = oldUsers.get(oldUserIndex);
            user_id = (String) userRecord.get("user_id");
            source = (String) userRecord.get("source");
            addr_country = (String) userRecord.get("addr_country");
            addr_province = (String) userRecord.get("addr_province");
            equipment_type = (String) userRecord.get("equipment_type");
            equipment_resolving = (String) userRecord.get("equipment_resolving");
            equipment_resolving_length = (int) userRecord.get("equipment_resolving_length");
            equipment_resolving_width = (int) userRecord.get("equipment_resolving_width");
            computer_browser_type = (String) userRecord.get("computer_browser_type");
            computer_browser_version = (String) userRecord.get("computer_browser_version");
            cellphone_operator = (String) userRecord.get("cellphone_operator");
            cellphone_brand = (String) userRecord.get("cellphone_brand");
            cellphone_version = (String) userRecord.get("cellphone_version");
            cellphone_id = (String) userRecord.get("cellphone_id");
        }else {
            //相同属性
            //用户id
            user_id = getMd5Sum(num + "");

            addr_country = ADDR_COUNTRY[0];

            int province_flag = random.nextInt(100);
            for(int i=0;i<ADDR_PROVINCE_RATE.length;i++) {
                if(province_flag < ADDR_PROVINCE_RATE[i]) {
                    addr_province = ADDR_PROVINCE[i];
                    break;
                }
            }


            //来源
            int source_index_flag = random.nextInt(100);

            for(int i=0;i<SOURCE.length;i++) {
                if(source_index_flag<Integer.parseInt(SOURCE[i][1])) {
                    source = SOURCE[i][0];
                    break;
                }
            }

            //设备类型
            int equipment_type_index = random.nextInt(EQUIPMENT_TYPE.length);
            equipment_type = EQUIPMENT_TYPE[equipment_type_index];
            //会话Id
            session_id = randomString(8)+"-"+randomString(4)+"-"+randomString(4)+"-"+randomString(4)+"-"+randomString(12);

            //分辨率
            equipment_resolving = EQUIPMENT_RESOLVING[equipment_type_index][random.nextInt(EQUIPMENT_RESOLVING[equipment_type_index].length)];
            String equipment_resolving_arr[] = equipment_resolving.split("\\*");

            //分辨率的长
            equipment_resolving_length = Integer.parseInt(equipment_resolving_arr[0]);
            //分辨率的宽
            equipment_resolving_width = Integer.parseInt(equipment_resolving_arr[1]);


            int computer_browser_flag = random.nextInt(100);

            int computer_browser_type_index=0;
            if(equipment_type_index<2) {
                for(int i=0; i<COMPUTER_BROWSER_RATE1.length;i++) {
                    if(computer_browser_flag<COMPUTER_BROWSER_RATE1[i]) {
                        computer_browser_type_index = i;
                        break;
                    }
                }
                computer_browser_type = COMPUTER_BROWSER_TYPE[computer_browser_type_index];
                //浏览器版本
                computer_browser_version = COMPUTER_BROWSER_VERSION[computer_browser_type_index][random.nextInt(COMPUTER_BROWSER_VERSION[computer_browser_type_index].length)];
            }else if(equipment_type_index<3) {
                for(int i=0; i<COMPUTER_BROWSER_RATE2.length;i++) {
                    if(computer_browser_flag<COMPUTER_BROWSER_RATE2[i]) {
                        computer_browser_type_index = i+2;
                        break;
                    }
                }
                computer_browser_type = COMPUTER_BROWSER_TYPE[computer_browser_type_index];
                //浏览器版本
                computer_browser_version = COMPUTER_BROWSER_VERSION[computer_browser_type_index][random.nextInt(COMPUTER_BROWSER_VERSION[computer_browser_type_index].length)];
            }

            if(equipment_type_index>2) {
                cellphone_id = randomString(15);
                int cellphone_operator_flag = random.nextInt(100);
                for(int i=0; i<CELLPHONE_OPERATOR_RATE.length;i++) {
                    if(cellphone_operator_flag<CELLPHONE_OPERATOR_RATE[i]) {
                        cellphone_operator_flag = i;
                        break;
                    }
                }

                cellphone_operator = CELLPHONE_OPERATOR[cellphone_operator_flag];

                int cellphone_brand_index;
                if(equipment_type.equals("IOS")) {
                    cellphone_brand_index = 5;
                }else {
                    cellphone_brand_index = random.nextInt(CELLPHONE_BRAND.length-1);
                }
                cellphone_brand = CELLPHONE_BRAND[cellphone_brand_index];
                cellphone_version = CELLPHONE_VERSION[cellphone_brand_index][random.nextInt(CELLPHONE_VERSION[cellphone_brand_index].length)];
            }

            Map<String,Object> newUserRecord = new HashMap<>();


            newUserRecord.put("user_id",user_id);
            newUserRecord.put("source",source);
            newUserRecord.put("addr_country",addr_country);
            newUserRecord.put("addr_province",addr_province);
            newUserRecord.put("equipment_type",equipment_type);
            newUserRecord.put("equipment_resolving",equipment_resolving);
            newUserRecord.put("equipment_resolving_length",equipment_resolving_length);
            newUserRecord.put("equipment_resolving_width",equipment_resolving_width);
            newUserRecord.put("computer_browser_type",computer_browser_type);
            newUserRecord.put("computer_browser_version",computer_browser_version);
            newUserRecord.put("cellphone_operator",cellphone_operator);
            newUserRecord.put("cellphone_brand",cellphone_brand);
            newUserRecord.put("cellphone_version",cellphone_version);
            newUserRecord.put("cellphone_id",cellphone_id);
            oldUsers.put(num,newUserRecord);
        }


        //不同属性
        //页面名称               PAGE_NAME
        int page_name_index = 0;
        String page_name = PAGE_NAME[0];
        //页面操作名称            PAGE_ACTION
        //页面操作
        int page_action_index = random.nextInt(PAGE_0_ACTION.length);
        //操作的名称
        String page_action;
        //第一次为浏览

        page_action = "浏览";




        //服务器端时间            ACTION_TIME
        //停留时间               STAY_TIME

        Map<String, Object> map = new HashMap<>();

        //结束时间之前
        if(userTime.isBefore(endTime)) {
            map.put("s|distinct_id", user_id); //用户ID
            map.put("s|source", source);   //来源
            map.put("s|sugo_nation", addr_country); //国家
            map.put("s|sugo_province", addr_province);
            map.put("s|system_name", equipment_type);
            map.put("s|screen_dpi", equipment_resolving);
            map.put("i|screen_width",equipment_resolving_length);
            map.put("i|screen_height",equipment_resolving_width);

            if(!computer_browser_type.equals("")) {
                map.put("s|browser", computer_browser_type);
                map.put("s|browser_version", computer_browser_version);
            }

            if(!cellphone_id.equals("")) {
                map.put("s|carrier", cellphone_operator);
                map.put("s|device_brand", cellphone_brand);
                map.put("s|device_model", cellphone_version);
                map.put("s|device_id", cellphone_id);
            }

            map.put("s|session_id", session_id);

            map.put("s|page_name", page_name);
            map.put("s|event_type", page_action);
            map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
            map.put("d|event_time", userTime.getMillis());
            //添加一条记录，类别为浏览
            msgs.add(jsonMapper.writeValueAsString(map));
        }









        while(true) {

            //页面操作名称            PAGE_ACTION
            String page_action_arr[][];
            switch (page_name_index) {
                case 0 : page_action_arr = PAGE_0_ACTION; break;
                case 1 : page_action_arr = PAGE_1_ACTION; break;
                case 2 : page_action_arr = PAGE_2_ACTION; break;
                case 3 : page_action_arr = PAGE_3_ACTION; break;
                case 4 : page_action_arr = PAGE_4_ACTION; break;
                case 5 : page_action_arr = PAGE_5_ACTION; break;
                case 6 : page_action_arr = PAGE_6_ACTION; break;
                case 7 : page_action_arr = PAGE_7_ACTION; break;
                case 8 : page_action_arr = PAGE_8_ACTION; break;
                case 9 : page_action_arr = PAGE_9_ACTION; break;
                case 10 : page_action_arr = PAGE_10_ACTION; break;
                case 11 : page_action_arr = PAGE_11_ACTION; break;
                default: page_action_arr = PAGE_0_ACTION;
            }
            //获得action,需要知道当前页面 page_name_index
            int action_flag = random.nextInt(100);
            int sum=0;
            int old_page_action_index = page_action_index;
            //判断是否为第11页上的前四项操作
            boolean pre_action_flag = false;
            if(page_name_index == 11 && old_page_action_index < 4) {
                pre_action_flag = true;
            }

            //用来区分用户是否离开页面
            page_action_index = -1;
            //获取每一个权重，累加进行比较
            for(int i=0; i<page_action_arr.length; i++) {
                if(pre_action_flag && old_page_action_index == i) continue;

                sum += Integer.parseInt(page_action_arr[i][1]);
                if(action_flag < sum) {
                    //页面动作
                    page_action_index = i;
                    break;
                }
            }

            //模拟停留时间
            int stay_time_second = random.nextInt(55)+5;
            int stay_time_milli = random.nextInt(1000);

            userTime = userTime.plusSeconds(stay_time_second).plusMillis(stay_time_milli);
            if(userTime.isAfter(endTime))  break;

            if(page_action_index != -1){   //用户点击页面    输出记录为三条   当前页面的点击和停留 下个页面的浏览
                //当前页面的点击
                    //改变的字段   页面操作名称   下一步页面   服务器端时间  停留时间

                map.put("s|event_type", page_action_arr[page_action_index][0]);
                map.put("s|event_name",page_action_arr[page_action_index][3]);
//                map.put("s|PAGE_NEXT", page_action_arr[page_action_index][2]);
                map.put("d|event_time", userTime.getMillis());
                map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                map.remove("f|duration");
                msgs.add(jsonMapper.writeValueAsString(map));

                userTime = userTime.plusMillis(10);
                if(userTime.isAfter(endTime))  break;

                //如果是详情页上的不跳转页面的操作,不记录停留和下个页面的浏览
                if(page_action_arr[page_action_index][2]!=null && page_action_arr[page_action_index][2].trim().length()>0) {
                    //当前页面的停留
                    //改变的字段   页面操作名称   下一步页面   停留时间
                    map.put("s|event_type", "停留");
                    map.put("f|duration", timeBetween(start,userTime));
                    map.remove("s|event_name");
                    map.put("d|event_time", userTime.getMillis());
                    map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                    start = userTime;
                    msgs.add(jsonMapper.writeValueAsString(map));


                    //下个页面的浏览
                        //改变的字段    当前页面     页面操作名称   下一步页面   服务器端时间  停留时间
                            //下个页面的名称
                    String web_name =  page_action_arr[page_action_index][2];
                    if(web_name.contains("详情页")) {
                        page_name_index = 11;
                        page_name = page_action_arr[page_action_index][2];
                        //网页 手机品牌 型号
                        web_brand = web_name.split("-")[0];
                        web_model = web_name.substring(0,web_name.length()-3);

                        map.put("s|web_brand",web_brand);
                        map.put("s|web_model",web_model);

                    }else{
                        for(int j=0; j<PAGE_NAME.length-1; j++) {

                            if(page_action_arr[page_action_index][2].equals(PAGE_NAME[j])) {
                                page_name_index = j;
                                page_name = PAGE_NAME[j];
                            }
                        }

                        if(page_name_index == 0) {
                            map.remove("s|web_brand");
                        }else{
                            web_brand = web_name.substring(0,web_name.length()-5);
                            map.put("s|web_brand",web_brand);
                        }
                        map.remove("s|web_model");

                    }

                    //web_brand web_model

                    userTime = userTime.plusMillis(30);
                    if(userTime.isAfter(endTime))  break;

                    map.put("s|page_name", page_name);
                    map.put("s|event_type", "浏览");
    //                map.put("s|PAGE_NEXT", "");
                    map.remove("s|event_name");
                    map.put("d|event_time", userTime.getMillis());
                    map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                    map.remove("f|duration");



                    msgs.add(jsonMapper.writeValueAsString(map));

                }

            }else {   //用户离开页面  输出记录一条，为停留
                //改变的字段
                    //页面操作名称   下一步页面   服务器端时间  停留时间
                userTime = userTime.plusMillis(10);
                if(userTime.isAfter(endTime))  break;

                map.put("s|event_type", "停留");
                map.remove("s|event_name");
                map.put("d|event_time", userTime.getMillis());
                map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                map.put("f|duration", timeBetween(start,userTime));
                msgs.add(jsonMapper.writeValueAsString(map));
                break;
            }
        }

        return msgs;
    }

    public float timeBetween (DateTime start , DateTime end) {
        float millis =  (end.getMillis() - start.getMillis());
//        String.format();
        float between = millis / 1000;
        return  new BigDecimal(between).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }



}
