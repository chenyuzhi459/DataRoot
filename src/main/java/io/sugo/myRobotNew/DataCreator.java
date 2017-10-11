package io.sugo.myRobotNew;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static io.sugo.myRobotNew.DataConst.*;

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
        String web_brand_CN ="";
        String web_model="";

        String event_name = "";
        String event_name_CN = "手机平台首页";
        String color = "";
        String page_name_CN = "";

        int jumpPageFlag = 1;
        Map eventNameMap = new HashMap();

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

        //会话Id
        session_id = randomString(8)+"-"+randomString(4)+"-"+randomString(4)+"-"+randomString(4)+"-"+randomString(12);

        //不同属性
        String page_name = "homePage";
        //页面操作名称    PAGE_ACTION  第一次为浏览
        String page_action = "浏览";

        //存放记录
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
            map.put("s|page_name", nameMap.get(page_name));
            map.put("s|event_type", page_action);
            map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
            map.put("d|event_time", userTime.getMillis());
            //添加一条记录，类别为浏览
            msgs.add(jsonMapper.writeValueAsString(map));
        }


        while(true) {
            //判断是否离开
            if(random.nextInt(5)>1) {
                //模拟停留时间
                int stay_time_second = random.nextInt(55)+5;
                int stay_time_milli = random.nextInt(1000);

                userTime = userTime.plusSeconds(stay_time_second).plusMillis(stay_time_milli);
                if(userTime.isAfter(endTime))  break;

                //页面点击
                    // 首页  unchange+具体的
                if(page_name!=null && page_name.equals("homePage")){

                    //是否点击unchange部分
                    if(random.nextInt(100)<60) {
                        event_name = Util.getUcEventName(page_name);
                        event_name_CN = DataConst.eventNameMap.get(event_name);
                    }else{ //点击具体手机部分
                        event_name_CN = Util.getHomePageConcrete();

                        String strs[] = event_name_CN.split(" ");
                        color = strs[strs.length-1];
                        String event_name_pre = event_name_CN.replace("热门手机 ","");

                        String brand = Util.getBrandAtHomePage(event_name_pre);
                        event_name = Util.getEventNameAtHomePage(event_name_pre);
                        eventNameMap = Util.getMapByConcreteName(brand,event_name);
                        //next_page
                    }
                //列表页  unchange+具体的+可选项
                }else if(page_name!=null && page_name.contains("List") || event_name_CN.contains("筛选")) {
                    web_brand = page_name.substring(0,page_name.length()-4);

                    int unchangeRate = 30;
                    int selectRate = 50;
                    //如果上次点击了筛选，调整概率
                    if(event_name_CN.contains("筛选")) {
                        unchangeRate = 10;
                        selectRate = 10;
                    }

                    //是否点击unchange部分
                    if(random.nextInt(100)<unchangeRate) {
                        event_name = Util.getUcEventName(page_name);
                        event_name_CN = DataConst.eventNameMap.get(event_name);
                    }else if(random.nextInt(100)<selectRate){   //可选项
                        event_name = Util.getChoiceName(web_brand,event_name);
                        event_name_CN = Util.getChoiceCN(event_name);
                        jumpPageFlag --;
                    }else { //具体的参数
                        if(!event_name_CN.contains("筛选")) {   //跳转页面
                            event_name = Util.getConcreteName(web_brand);
                        }else {
                            event_name = Util.getNameFromMap(brand.get(web_brand).get(event_name));
                        }

                        eventNameMap = Util.getMapByConcreteName(web_brand,event_name);
                        event_name_CN = Util.getConcreteNameCNByMap(eventNameMap);
                        String strs[] = event_name_CN.split(" ");

                        color = strs[strs.length-1];
                    }

                }else {  // 详情页  unchange+可选项(手机参数 分享渠道等)
                    if(random.nextInt(100)<30) {
                        event_name = Util.getUcEventName(page_name);
                        event_name_CN = DataConst.eventNameMap.get(event_name);
                    }else {   //可选项
                        event_name_CN = Util.getLastPageSelectRandom(event_name_CN);
                        jumpPageFlag --;
                    }
                }

                map.put("s|event_type", "点击");
                map.put("s|event_name",event_name_CN);
                map.put("d|event_time", userTime.getMillis());
                map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                map.remove("f|duration");
                msgs.add(jsonMapper.writeValueAsString(map));

                //模拟点击之后的时间花费
                userTime = userTime.plusMillis(10);
                if(userTime.isAfter(endTime))  break;

                //如果不是详情页上的不跳转页面的操作,不记录停留和下个页面的浏览
                if(jumpPageFlag == 1) {
                    //停留
                    map.put("s|event_type", "停留");
                    map.put("f|duration", Util.timeBetween(start,userTime));
                    map.remove("s|event_name");
                    map.put("d|event_time", userTime.getMillis());
                    map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                    start = userTime;
                    msgs.add(jsonMapper.writeValueAsString(map));

                    //浏览
                      //模拟停留和浏览之间花费的时间
                    userTime = userTime.plusMillis(30);
                    if(userTime.isAfter(endTime))  break;


                    if(event_name_CN.contains("品牌")) {
                        Util.removeAttributeFromMap(map);
                        map.remove("s|web_model");
                        web_brand_CN = event_name_CN.substring(0,event_name_CN.length()-2);
                        web_brand = Util.getBrandEnByCN(web_brand_CN);
                        map.put("s|web_brand",web_brand_CN);
                        page_name = event_name;
                        page_name_CN = event_name_CN+"列表页";
                    }else if(event_name.equals("homePage")) {
                        //移除属性
                        Util.removeAttributeFromMap(map);
                        map.remove("s|web_brand");
                        map.remove("s|web_model");
                        page_name = "homePage";
                        page_name_CN = nameMap.get(page_name);
                        web_brand = "";
                        web_brand_CN = "";
                    }else {
                        //添加属性
                        Util.putAttributeToMap(map,eventNameMap,color);
                        page_name = event_name_CN.replaceAll("热门手机 ","");
                        page_name_CN = page_name + " 详情页";
                        web_brand_CN = (String) eventNameMap.get("brand");
                        web_brand = Util.getBrandEnByCN(web_brand_CN);
                    }


                    map.put("s|page_name", page_name_CN);
                    map.put("s|event_type", "浏览");
                    map.put("d|event_time", userTime.getMillis());
                    map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                    map.remove("f|duration");

                    msgs.add(jsonMapper.writeValueAsString(map));
                }else {
                    jumpPageFlag++ ;
                }

            }else{  //用户离开
                userTime = userTime.plusMillis(10);
                if(userTime.isAfter(endTime))  break;

                map.put("s|event_type", "停留");
                map.remove("s|event_name");

                map.put("d|event_time", userTime.getMillis());
                map.put("s|sugo_timezone", userTime.getHourOfDay()+"");
                map.put("f|duration", Util.timeBetween(start,userTime));
                msgs.add(jsonMapper.writeValueAsString(map));
                break;
            }
        }

        return msgs;
    }







}
