package io.sugo.myRobot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//参考数据
public class DataConst {

  public static String[] ADDR_PROVINCE = ("北京市,天津市,上海市,重庆市,河北省,山西省,辽宁省,吉林省,黑龙江省,江苏省," +
          "浙江省,安徽省,福建省,江西省,山东省,河南省,湖北省,湖南省,广东省,海南省," +
          "四川省,贵州省,云南省,陕西省,甘肃省,青海省,台湾省,内蒙古自治区,广西壮族自治区,西藏自治区," +
          "宁夏回族自治区,新疆维吾尔自治区,香港特别行政区,澳门特别行政区").split(",");
  public static int[] ADDR_PROVINCE_RATE = {5,10,15,20,25,27,29,31,33,35,
          40,42,45,47,49,52,55,58,68,70,
          72,73,78,81,84,86,88,90,91,92,
          93,94,95,100};
  public static String[] ADDR_COUNTRY = {"中国"};

  //来源
  public static String[][] SOURCE=new String[][]{
          {"直接访问","40"},
          {"谷歌搜索","50"},
          {"百度搜索","75"},
          {"360搜索","77"},
          {"腾讯广点通","85"},
          {"百度网盟","90"},
          {"搜狗网盟","92"},
          {"百度推广","97"},
          {"谷歌推广","100"}
  };

  //设备
  public static String[] EQUIPMENT_TYPE = {"Windows","Linux","Mac OS","android","IOS"};
  //分辨率
  public static String[][] EQUIPMENT_RESOLVING=new String[][]{
          "3440*1440,2560*1440,2560*1080,1920*1080,1600*900,1440*900,1280*1024,1366*768,1280*800,1024*768,3840*2160,800*600,1920*1200,3840*2400,1840*958,1536*864".split(","),
          "3440*1440,2560*1440,2560*1080,1920*1080,1600*900,1440*900,1280*1024,1366*768,1280*800,1024*768,3840*2160,800*600,1920*1200,3840*2400,1840*958,1536*864".split(","),
          "1440*900,1280*800,2880*1800".split(","),
          "2960*1440,2560*1440,2560*1440,1920*1080,1920*1080,1280*720,1920*1080,2560*1440,1920*1080".split(","),
          "1136*640,1334*750,1920*1080".split(",")
  };

  //电脑浏览器
  public static String[] COMPUTER_BROWSER_TYPE="Internet Explorer,Fire Fox,Chrome,Safari".split(",");
  public static int[] COMPUTER_BROWSER_RATE1={5,20,100};
  public static int[] COMPUTER_BROWSER_RATE2={65,100};

  //浏览器版本
  public static String[][] COMPUTER_BROWSER_VERSION=new String[][]{
          "11,10,9".split(","),
          "53,47".split(","),
          "58,57,56,55,53,59,60,54".split(","),
          "10,10.1,9".split(",")
  } ;

  //运营商
  public static String[] CELLPHONE_OPERATOR="中国移动,中国联通,中国电信".split(",");
  public static int[] CELLPHONE_OPERATOR_RATE={40,80,100};
  //品牌
  public static String[] CELLPHONE_BRAND="三星,vivo,OPPO,华为,中兴,iPhone".split(",");
  //品牌型号
  public static String[][] CELLPHONE_VERSION=new String[][]{
          "GALAXY S8,GALAXY S8+,GALAXY S7 Edge,GALAXY Note 8,GALAXY S7 Edge".split(","),
          "Xplay6,Xplay5,Xplay3S,X9,X9Plus,X7,X7 Plus,X6S,X6SPlus".split(","),
          "R9s,R11,R9,R9s Plus,R9 Plus,A57,A59s".split(","),
          "nova 2,Mate 9,Mate 8,P10 Plus".split(","),
          "AXON天机,Blade V8".split(","),
          "iPhone SE,iPhone 5C,iPhone 5S,iPhone 7,iPhone 6S,iPhone 6,iPhone 7 Plus,iPhone 6S Plus,iPhone 6 Plus".split(",")
  };

  //页面名称
  public static String[] PAGE_NAME="手机平台首页,Apple品牌列表页,小米品牌列表页,三星品牌列表页,VIVO品牌列表页,华为品牌列表页,OPPO品牌列表页,魅族品牌列表页,Moto品牌列表页,索尼品牌列表页,华硕品牌列表页,详情页".split(",");
  public static String[] WEB_BRAND="Apple,小米,三星,VIVO,华为,OPPO,魅族,Moto,索尼,华硕".split(",");
  //页面0操作
  public static String[][] PAGE_0_ACTION=new String[][]{
          "点击,8,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,7,小米品牌列表页,小米品牌列表".split(","),
          "点击,6,三星品牌列表页,三星品牌列表".split(","),
          "点击,4,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,4,华为品牌列表页,华为品牌列表".split(","),
          "点击,8,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,5,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,2,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,2,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,1,Apple-iPhone 7详情页,热门型号iPhone 7".split(","),
          "点击,4,Apple-iPhone 7 Plus详情页,热门型号iPhone 7 Plus".split(","),
          "点击,2,Apple-iPhone 6S Plus详情页,热门型号iPhone 6S Plus".split(","),
          "点击,3,小米-小米6详情页,热门型号小米6".split(","),
          "点击,2,小米-小米Max 2详情页,热门型号小米Max 2".split(","),
          "点击,1,小米-小米5c详情页,热门型号小米5c".split(","),
          "点击,3,三星-GALAXY S8详情页,热门型号GALAXY S8".split(","),
          "点击,1,三星-GALAXY S8+详情页,热门型号GALAXY S8+".split(","),
          "点击,1,三星-GALAXY S7 Edge详情页,热门型号GALAXY S7 Edge".split(","),
          "点击,1,VIVO-X9详情页,热门型号X9".split(","),
          "点击,3,VIVO-X9Plus详情页,热门型号X9Plus".split(","),
          "点击,3,VIVO-Xplay6详情页,热门型号Xplay6".split(","),
          "点击,1,华为-华为P10详情页,热门型号华为P10".split(","),
          "点击,2,华为-华为Mate 9详情页,热门型号华为Mate 9".split(","),
          "点击,3,华为-华为P10 Plus详情页,热门型号华为P10 Plus".split(",")
  };
  //页面1操作
  public static String[][] PAGE_1_ACTION=new String[][]{
          "点击,8,Apple-iPhone 7详情页,iPhone 7".split(","),
          "点击,8,Apple-iPhone 7 Plus详情页,iPhone 7 Plus".split(","),
          "点击,6,Apple-iPhone 6S详情页,iPhone 6S".split(","),
          "点击,7,Apple-iPhone 6S Plus详情页,iPhone 6S Plus".split(","),
          "点击,6,Apple-iPhone 6详情页,iPhone 6".split(","),
          "点击,6,Apple-iPhone 6 Plus详情页,iPhone 6 Plus".split(","),

          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")
  };
  //页面2操作
  public static String[][] PAGE_2_ACTION=new String[][]{
          "点击,7,小米-小米6详情页,小米6".split(","),
          "点击,6,小米-小米Max 2详情页,小米Max 2".split(","),
          "点击,5,小米-小米5c详情页,小米5c".split(","),
          "点击,5,小米-红米4X详情页,红米4X".split(","),
          "点击,6,小米-小米Note 2详情页,小米Note 2".split(","),
          "点击,5,小米-小米5详情页,小米5".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(","),

  };
  //页面3操作
  public static String[][] PAGE_3_ACTION=new String[][]{
          "点击,8,三星-GALAXY S8详情页,GALAXY S8".split(","),
          "点击,8,三星-GALAXY S8+详情页,GALAXY S8+".split(","),
          "点击,6,三星-GALAXY S7 Edge详情页,GALAXY S7 Edge".split(","),
          "点击,7,三星-GALAXY Note 8详情页,GALAXY Note 8".split(","),
          "点击,6,三星-GALAXY S7详情页,GALAXY S7".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")

  };
  //页面4操作
  public static String[][] PAGE_4_ACTION=new String[][]{
          "点击,8,VIVO-X9详情页,X9".split(","),
          "点击,6,VIVO-X9Plus详情页,X9Plus".split(","),
          "点击,5,VIVO-Xplay6详情页,Xplay6".split(","),
          "点击,8,VIVO-Y67详情页,Y67".split(","),
          "点击,5,VIVO-Y67A详情页,Y67A".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")

  };
  //页面5操作
  public static String[][] PAGE_5_ACTION=new String[][]{
          "点击,8,华为-华为P10详情页,华为P10".split(","),
          "点击,8,华为-华为Mate 9详情页,华为Mate 9".split(","),
          "点击,6,华为-华为P10 Plus详情页,华为P10 Plus".split(","),
          "点击,7,华为-荣耀V9详情页,荣耀V9".split(","),
          "点击,6,华为-荣耀8详情页,荣耀8".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")

  };
  //页面6操作
  public static String[][] PAGE_6_ACTION=new String[][]{
          "点击,7,OPPO-R9s详情页,R9s".split(","),
          "点击,7,OPPO-R11详情页,R11".split(","),
          "点击,7,OPPO-R9详情页,R9".split(","),
          "点击,6,OPPO-R9s Plus详情页,R9s Plus".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")

  };
  //页面7操作
  public static String[][] PAGE_7_ACTION=new String[][]{
          "点击,7,魅族-魅蓝E2详情页,魅蓝E2".split(","),
          "点击,7,魅族-魅族PRO 6 Plus详情页,魅族PRO 6 Plus".split(","),
          "点击,7,魅族-魅族PRO 7详情页,魅族PRO 7".split(","),
          "点击,3,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,3,小米品牌列表页,小米品牌列表".split(","),
          "点击,3,三星品牌列表页,三星品牌列表".split(","),
          "点击,3,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,3,华为品牌列表页,华为品牌列表".split(","),
          "点击,3,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,3,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,3,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,3,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,3,手机平台首页,首页".split(",")

  };
  //页面8操作
  public static String[][] PAGE_8_ACTION=new String[][]{
          "点击,10,Moto-Moto Z详情页,Moto Z".split(","),
          "点击,10,Moto-Moto Z2 Play详情页,Moto Z2 Play".split(","),
          "点击,5,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,5,小米品牌列表页,小米品牌列表".split(","),
          "点击,5,三星品牌列表页,三星品牌列表".split(","),
          "点击,5,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,5,华为品牌列表页,华为品牌列表".split(","),
          "点击,5,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,5,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,5,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,5,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,5,手机平台首页,首页".split(",")

  };
  //页面9操作
  public static String[][] PAGE_9_ACTION=new String[][]{
          "点击,10,索尼-Xperia XZ详情页,Xperia XZ".split(","),
          "点击,10,索尼-Xperia XZs详情页,Xperia XZs".split(","),
          "点击,5,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,5,小米品牌列表页,小米品牌列表".split(","),
          "点击,5,三星品牌列表页,三星品牌列表".split(","),
          "点击,5,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,5,华为品牌列表页,华为品牌列表".split(","),
          "点击,5,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,5,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,5,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,5,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,5,手机平台首页,首页".split(",")

  };
  //页面10操作
  public static String[][] PAGE_10_ACTION=new String[][]{
          "点击,10,华硕-ZenFone AR详情页,ZenFone AR".split(","),
          "点击,10,华硕-ZenFone 2详情页,ZenFone 2".split(","),
          "点击,5,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,5,小米品牌列表页,小米品牌列表".split(","),
          "点击,5,三星品牌列表页,三星品牌列表".split(","),
          "点击,5,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,5,华为品牌列表页,华为品牌列表".split(","),
          "点击,5,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,5,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,5,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,5,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,5,手机平台首页,首页".split(",")

  };
  //页面11操作
  public static String[][] PAGE_11_ACTION=new String[][]{
          "点击,6,,手机概述".split(","),
          "点击,10,,手机参数".split(","),
          "点击,10,,手机图集".split(","),
          "点击,25,,观看视频介绍".split(","),
          "点击,5,,微信分享".split(","),
          "点击,2,,QQ分享".split(","),
          "点击,1,,微博分享".split(","),
          "点击,2,Apple品牌列表页,Apple品牌列表".split(","),
          "点击,2,小米品牌列表页,小米品牌列表".split(","),
          "点击,2,三星品牌列表页,三星品牌列表".split(","),
          "点击,2,VIVO品牌列表页,VIVO品牌列表".split(","),
          "点击,2,华为品牌列表页,华为品牌列表".split(","),
          "点击,2,OPPO品牌列表页,OPPO品牌列表".split(","),
          "点击,2,魅族品牌列表页,魅族品牌列表".split(","),
          "点击,2,Moto品牌列表页,Moto品牌列表".split(","),
          "点击,2,索尼品牌列表页,索尼品牌列表".split(","),
          "点击,2,华硕品牌列表页,华硕品牌列表".split(","),
          "点击,2,手机平台首页,首页".split(",")
  };

  public static void main(String[] args) {
    System.out.println(new Random().nextInt(2));

  }

}
