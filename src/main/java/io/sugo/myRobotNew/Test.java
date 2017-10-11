package io.sugo.myRobotNew;


import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static io.sugo.myRobotNew.DataConst.*;

/**
 * Created by qwe on 17-6-13.
 */
public class Test {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        Integer i=new Integer(3);
        Integer j=new Integer(2);

        System.out.println(
                i.compareTo(j)
        );
    }

}





