package io.sugo.myRobotNew;

import org.apache.hadoop.util.hash.Hash;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import static io.sugo.myRobotNew.DataConst.*;

/**
 * Created by qwe on 17-6-26.
 */
public class Util {

    public static Random random = new Random();

    //计算去除本页之后的概率
    public static float getRateAfterRemove(String page_name,float rate) {
        float rateRemove = Float.parseFloat(unchange.get(page_name).get("rate"));
        float multi = 100 / (100-rateRemove);
        return  rate * multi;
    }

    //两个日期之间的间隔 精确的小数点后两位
    public static float timeBetween (DateTime start , DateTime end) {
        float millis =  (end.getMillis() - start.getMillis());
        float between = millis / 1000;
        return  new BigDecimal(between).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    //随机生成
    public static String getNameFromMap(Map map) {
        int mapFlag = random.nextInt(map.size());
        int index = 0;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(index == mapFlag) {
                String returnStr = (String) entry.getKey();
                if(returnStr.equals("rate")) {
                    return getNameFromMap(map);
                }
                return returnStr;
            }
            index++;
        }
        return "";
    }

    //随机生成点击的可选项                 //apple             //appleList
    public static String getChoiceName(String web_brand, String event_name) {
        Map map = brand.get(web_brand);
        int sum = map.size();

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(((String) entry.getKey()).equals(event_name)) {
                sum --;
                break;
            }
        }

        int mapFlag = random.nextInt(sum);
        int index = 0;
        iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(((String) entry.getKey()).equals(event_name)) {
                continue;
            }
            if(index == mapFlag) {
                String returnStr = (String) entry.getKey();
                return returnStr;
            }
            index++;
        }

        return "iPhone_7";
    }

    //随机生成点击的具体项（全部）
    public static String getConcreteName(String web_brand) {

        Map map = brand.get(web_brand);
        //如 iPhone_7
        String concreteName = "";

        int flag = random.nextInt(100);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int rate = Integer.parseInt((String)(((Map)(((Map) entry.getValue()).get("rate"))).get("rate")));
            if( flag < rate){
                concreteName = (String) entry.getKey();
            }
        }

        map = (Map) map.get(concreteName);

        int mapFlag = random.nextInt(map.size()-1);
        int index = 0;
        iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(((String)entry.getKey()).equals("rate")) continue;
            if(index == mapFlag) {
                String returnStr = (String) entry.getKey();
                return returnStr;
            }
            index++;
        }
        return "";

    }

    //同等概率随机
    public static String getSameRateRandom(String[][] choice,int index) {
        return choice[random.nextInt(choice.length)][index];
    }

    //随机生成下个页面（unchange部分）
    public static String getUcEventName(String pageName) {

        float unchangeFlag=random.nextFloat();
        float unchangeSum = 0;
        //按概率随机生成点击了哪个按钮
        Iterator<Map.Entry<String, Map<String, String>>> iter = unchange.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = iter.next();
            String key = entry.getKey();
            if(key!=null && key.equals(pageName)) continue;
            if(pageName == null || !(pageName.contains("List") || pageName.equals("homePage"))) {
                unchangeSum += Float.parseFloat(unchange.get(key).get("rate")) / 100;
            }else{
                unchangeSum += Util.getRateAfterRemove(pageName,Float.parseFloat(unchange.get(key).get("rate")) / 100);
            }
            if(unchangeFlag < unchangeSum) {
                String returnStr = unchange.get(key).get("next_page");
                return returnStr;
            }
        }
        return "";
    }


    //获得可选项的中文名
    public static String getChoiceCN(String event_name) {
        return "筛选"+event_name.replaceAll("_"," ").replace("xiaomi","小米")
                .replace("hongmi","红米").replace("huawei","华为")
                .replace("meilan","魅蓝").replace("tianji","天机")
                .replace("rongyao","荣耀").replace("meizu","魅族");
    }

    //通过具体的名字获得其相应的map  如 iPhone_7_64G
    public static Map getMapByConcreteName(String web_brand,String name) {
        name = changeCN2En(name);
        String strs[] = name.split("_");
        String nameStr = "";
        for(int i=0;i<strs.length-1;i++) {
            nameStr += strs[i]+"_";
        }
        nameStr = nameStr.substring(0,nameStr.length()-1);
        return brand.get(web_brand).get(nameStr).get(name);
    }

    //通过Map获取具体的名字
    public static String getConcreteNameCNByMap(Map map) {
        String brand = (String) map.get("brand");
        String model = (String) map.get("model");
        String rom = (String) map.get("web_rom");
        String colors[] = ((String) map.get("web_color")).split(",");
        String color = colors[random.nextInt(colors.length)];

        return brand + "-" + model + " " + rom + " " + color;
    }

    public static void putAttributeToMap(Map map,Map eventNameMap,String color) {

        if(color == null || color.trim().equals("") ) {
            String colors[] = ((String)eventNameMap.get("web_color")).split(",");
            color = colors[random.nextInt(colors.length)];
        }

        map.put("s|web_brand",eventNameMap.get("brand"));
        map.put("s|web_model",eventNameMap.get("model"));
        map.put("s|web_price",eventNameMap.get("web_price"));
        map.put("s|web_rom",eventNameMap.get("web_rom"));
        map.put("s|web_color",color);
        map.put("s|web_size",eventNameMap.get("web_size"));
        map.put("s|web_dpi",eventNameMap.get("web_dpi"));
        map.put("s|web_battery",eventNameMap.get("web_battery"));
        map.put("s|web_num_cameras",eventNameMap.get("web_num_cameras"));
        map.put("s|web_recamera_par",eventNameMap.get("web_recamera_par"));
        map.put("s|web_frcamera_par",eventNameMap.get("web_frcamera_par"));
        map.put("s|web_weight",eventNameMap.get("web_weight"));
        map.put("s|web_num_core",eventNameMap.get("web_num_core"));
    }

    public static void removeAttributeFromMap(Map map) {
        map.remove("s|web_price");
        map.remove("s|web_rom");
        map.remove("s|web_color");
        map.remove("s|web_size");
        map.remove("s|web_dpi");
        map.remove("s|web_battery");
        map.remove("s|web_num_cameras");
        map.remove("s|web_recamera_par");
        map.remove("s|web_frcamera_par");
        map.remove("s|web_weight");
        map.remove("s|web_num_core");
    }

    public static String getEventNameAtHomePage(String event_name_pre) {
        String str1 = event_name_pre.split("-")[1];
        String strs[] = str1.split(" ");

        String event_name = "";
        for(int i=0;i<strs.length-1;i++) {
            strs[i] = changeCN2En(strs[i]);
            event_name += strs[i]+"_";
        }
        event_name = event_name.substring(0,event_name.length()-1);
        return event_name;
    }

    public static String changeCN2En(String chineseStr){
        if(chineseStr == null)  return "";
        return chineseStr.replace("小米","xiaomi")
                .replace("红米","hongmi").replace("华为","huawei")
                .replace("魅蓝","meilan").replace("天机","tianji")
                .replace("荣耀","rongyao").replace("魅族","meizu")
                .replaceAll("\\+","_Plus");
    }

    public static String getBrandAtHomePage(String event_name_pre) {
        String brandCnName = event_name_pre.split("-")[0];
        return getBrandEnByCN(brandCnName);
    }

    public static String getBrandEnByCN(String chineseStr) {
        if(chineseStr == null)  return "";
        return chineseStr.replace("Apple","apple").replace("小米","xiaomi").replace("三星","sumsung")
                .replace("VIVO","vivo").replace("华为","huawei").replace("OPPO","oppo")
                .replace("魅族","meizu").replace("Moto","moto").replace("索尼","sony")
                .replace("中兴","zhongxing");
    }

    //获取不重复的详情页的可选操作
    public static String getLastPageSelectRandom(String event_name_CN) {

        int sum = 0;
        for(int i=0;i<lastPageConcrete.length;i++) {
            if(lastPageConcrete[i][0].equals(event_name_CN))    continue;
            sum += Integer.parseInt(lastPageConcrete[i][1]);
        }

        int flag = random.nextInt(sum);
        int newSum = 0;
        for(int i=0;i<lastPageConcrete.length;i++) {
            if(lastPageConcrete[i][0].equals(event_name_CN))  continue;
            newSum += Integer.parseInt(lastPageConcrete[i][1]);
            if(flag < newSum ) {
                return lastPageConcrete[i][0];
            }
        }
        return "";
    }

    public static String getHomePageConcrete() {
        int flag = random.nextInt(100);
        for(int i=0;i<homePageConcrete.length;i++ ) {
            if(flag < Integer.parseInt(homePageConcrete[i][2])) {
                return homePageConcrete[i][0];
            }
        }

        return "热门手机 Apple-iPhone 7 128G 银色";
    }
}
