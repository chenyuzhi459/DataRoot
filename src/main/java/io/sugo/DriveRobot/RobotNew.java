package io.sugo.DriveRobot;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by qwe on 17-6-16.
 */
public class RobotNew {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Logger logger = Logger.getLogger(RobotNew.class);
    public static int num = 0;
    public static Map<Integer,Map<String,String>> oldUsers = new HashMap<>();
    private static Properties props = new Properties();


    public static void main(String[] args) throws Exception {
        props.load(new FileInputStream("kafka.properties"));
        Random random = new Random();
        DataCreator creator = new DataCreator();
        //过去的数据还是实时的数据
        boolean history = true;


        //结束时间
        DateTime endTime = DateTime.parse("2100-01-01");
        //当前时间
        DateTime nowTime = new DateTime();
        System.out.println(props.getProperty("numOfDayBefore"));
        DateTime startTime = nowTime.minusDays(Integer.valueOf(props.getProperty("numOfDayBefore","10")));
        //执行时间
        DateTime present = startTime;

        int sended = 0;
        while (present.isBefore(endTime)) {
            if(present.isBefore(nowTime)) {
                present = present.plusMillis(random.nextInt(getRandomMillis(present)));
            }else {
                String info = "user number : "+ num + "  , " + "order count : "+sended;
                logger.info(info);
//                System.out.println(info);
                present = new DateTime();
                Thread.sleep(random.nextInt(getRandomMillis(present)));
            }

            //判断是生成新用户，还是从老用户中取
            //老用户
            boolean isOldUser = random.nextInt(100) > 45 && num >300 ;

            List<String> msgs = creator.generateData(num, present, endTime, oldUsers,isOldUser);
            for (String msg : msgs) {
                //creator.send(msg);
                System.out.println(msg);
                sended++;
            }

            if(!isOldUser) {
                num++;
            }



        }
        creator.close();
    }

    public static int getRandomMillis(DateTime time) {
        int hourOfDay = time.getHourOfDay();
        int milliNum = 0;
        if(hourOfDay < 2) {
            milliNum = 5 * 60 * 1000;
        }else if(hourOfDay < 7) {
            milliNum = 20 * 60 * 1000;
        }else if(hourOfDay < 9) {
            milliNum = 4 * 60 * 1000;
        } else if(hourOfDay < 13) {
            milliNum = 3 * 60 * 1000;
        } else if(hourOfDay < 17) {
            milliNum = 1 * 60 * 1000;
        } else if(hourOfDay < 20) {
            milliNum = 30 * 1000;
        } else if(hourOfDay < 22) {
            milliNum = 15 * 1000;
        } else {
            milliNum = 30 * 1000;
        }
        return milliNum;
    }
}
