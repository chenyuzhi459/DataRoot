package io.sugo.myRobot;


import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qwe on 17-6-13.
 */
public class Test {

    public static void main(String[] args) {
        String web_name="华为-华为P10 Plus详情页";
        System.out.println(web_name.split("-")[0]);
        System.out.println(web_name.substring(0,web_name.length()-3) );
    }

}




