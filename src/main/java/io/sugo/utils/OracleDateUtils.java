package io.sugo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sugo on 16-7-21.
 */
public class OracleDateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    public static Date oracleDate2Date(String oracleDate) throws ParseException {
        String[] q = oracleDate.split(" ", -1);
        String[] dm = q[0].split("-");
        String y = "", M, d, h, m, s;
        String[] hms = new String[3];
        d = dm[0];
        M = dm[1].substring(0, dm[1].length()-1);
        if (dm.length == 2) {
            y = q[1].replace("-", "20");
            hms = q[2].split("\\.");
        } else if (dm.length == 3) {
            y = "20" + dm[2];
            hms = q[1].split("\\.");
        }

        h = hms[0];
        m = hms[1];
        s = hms[2];
        if ("下午".equals(q[q.length-1])) {
            h = String.valueOf(Integer.parseInt(h) +12);
        }
        String yMdhms = y
                + (M.length() == 1 ? "0"+M : M)
                + (d.length() == 1 ? "0"+d : d)
                + h+m+s;

        return sdf.parse(yMdhms);
    }

    public static void main(String[] args) throws ParseException {
        String date = "22-12月 -15 10.31.00.071157 上午";
        System.out.println(oracleDate2Date(date));
    }
}
