package io.sugo.myRobotNew;


import java.util.*;

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


  //首页具体的手机属性
  public static String[][] homePageConcrete = {
          "热门手机 Apple-iPhone 7 128G 银色,Apple-iPhone 7详情页,20".split(","),
          "热门手机 Apple-iPhone 7 Plus 128G 玫瑰金,Apple-iPhone 7 Plus详情页,35".split(","),
          "热门手机 Apple-iPhone 6S Plus 128G 金色,Apple-iPhone 6S详情页,40".split(","),
          "热门手机 三星-GALAXY S8 64G 绮梦金,三星-GALAXY S8详情页,52".split(","),
          "热门手机 三星-GALAXY S8+ 128G 谜夜黑,三星-GALAXY S8+详情页,60".split(","),
          "热门手机 三星-GALAXY S7 Edge 64G 莹钻粉,三星-GALAXY S7 Edge详情页,64".split(","),
          "热门手机 VIVO-X9 64G 活力蓝,VIVO-X9详情页,76".split(","),
          "热门手机 VIVO-X9Plus 64G 星空灰,VIVO-X9Plus详情页,80".split(","),
          "热门手机 VIVO-Xplay6 128G 玫瑰金,VIVO-Xplay6详情页,82".split(","),
          "热门手机 华为-华为P10 128G 钻雕金,华为-华为P10详情页,90".split(","),
          "热门手机 华为-华为Mate 9 64G 香槟金,华为-华为Mate 9详情页,96".split(","),
          "热门手机 华为-华为P10 Plus 256G 玫瑰金,华为-华为P10 Plus详情页,100".split(",")
  };

  //详情页的可选项
  public static String[][] lastPageConcrete = {
    "手机概述,20".split(","),
    "手机参数,30".split(","),
    "手机图集,20".split(","),
    "观看视频介绍,10".split(","),
    "微信分享,10".split(","),
    "QQ分享,7".split(","),
    "微博分享,3".split(",")
  };

  //页面名称的英文和中文对照
  public static Map<String,String> nameMap = new HashMap<>();
  static {
    nameMap.put("homePage","手机平台首页");
    nameMap.put("appleList","Apple品牌列表页");
    nameMap.put("xiaomiList","小米品牌列表页");
    nameMap.put("sumsungList","三星品牌列表页");
    nameMap.put("vivoList","VIVO品牌列表页");
    nameMap.put("huaweiList","华为品牌列表页");
    nameMap.put("oppoList","OPPO品牌列表页");
    nameMap.put("meizuList","魅族品牌列表页");
    nameMap.put("motoList","Moto品牌列表页");
    nameMap.put("sonyList","索尼品牌列表页");
    nameMap.put("zhongxingList","中兴品牌列表页");
  }

  public static Map<String,String> eventNameMap = new HashMap<>();
  static {
    eventNameMap.put("homePage","回到首页");
    eventNameMap.put("appleList","Apple品牌");
    eventNameMap.put("xiaomiList","小米品牌");
    eventNameMap.put("sumsungList","三星品牌");
    eventNameMap.put("vivoList","VIVO品牌");
    eventNameMap.put("huaweiList","华为品牌");
    eventNameMap.put("oppoList","OPPO品牌");
    eventNameMap.put("meizuList","魅族品牌");
    eventNameMap.put("motoList","Moto品牌");
    eventNameMap.put("sonyList","索尼品牌");
    eventNameMap.put("zhongxingList","中兴品牌");
  }



  //列表页和首页，不变部分 unchange
  public static Map<String,Map<String,String>> unchange = new HashMap<>();
  static {
    Map<String,String> homePageUc = new HashMap<>();
    homePageUc.put("next_page","homePage");
    homePageUc.put("rate","20");
    unchange.put("homePage",homePageUc);

    Map<String,String> appleUc = new HashMap<>();
    appleUc.put("next_page","appleList");
    appleUc.put("rate","30");
    unchange.put("appleList",appleUc);

    Map<String,String> sumsungUc = new HashMap<>();
    sumsungUc.put("next_page","sumsungList");
    sumsungUc.put("rate","10");
    unchange.put("sumsungList",sumsungUc);

    Map<String,String> vivoUc = new HashMap<>();
    vivoUc.put("next_page","vivoList");
    vivoUc.put("rate","10");
    unchange.put("vivoList",vivoUc);

    Map<String,String> huaweiUc = new HashMap<>();
    huaweiUc.put("next_page","huaweiList");
    huaweiUc.put("rate","10");
    unchange.put("huaweiList",huaweiUc);

    Map<String,String> oppoUc = new HashMap<>();
    oppoUc.put("next_page","oppoList");
    oppoUc.put("rate","5");
    unchange.put("oppoList",oppoUc);

    Map<String,String> meizuUc = new HashMap<>();
    meizuUc.put("next_page","meizuList");
    meizuUc.put("rate","5");
    unchange.put("meizuList",meizuUc);

    Map<String,String> motoUc = new HashMap<>();
    motoUc.put("next_page","motoList");
    motoUc.put("rate","5");
    unchange.put("motoList",motoUc);

    Map<String,String> sonyUc = new HashMap<>();
    sonyUc.put("next_page","sonyList");
    sonyUc.put("rate","3");
    unchange.put("sonyList",sonyUc);

    Map<String,String> zhongxingUc = new HashMap<>();
    zhongxingUc.put("next_page","zhongxingList");
    zhongxingUc.put("rate","2");
    unchange.put("zhongxingList",zhongxingUc);
  }

  //页面的筛选
  public static Map<String,String[]> concretePage = new HashMap<>();

  static {
    concretePage.put("apple","iPhone_7,iPhone_7_Plus,iPhone_6S,iPhone_6S_Plus,iPhone_6,iPhone_6_Plus".split(","));
    concretePage.put("xiaomi","小米6,小米Max_2,小米5c,红米4X,小米Note_2,小米5".split(","));
    concretePage.put("sumsung","GALAXY_S8,GALAXY_S8+,GALAXY_S7_Edge,GALAXY_Note_7,GALAXY_S7".split(","));
    concretePage.put("vivo","X9,X9Plus,Xplay6,Y67,Xplay5".split(","));
    concretePage.put("huawei","华为P10,华为Mate_9,华为P10_Plus,荣耀V9,荣耀8".split(","));
    concretePage.put("oppo","R9s,R11,R9,R9s_Plus".split(","));
    concretePage.put("meizu","魅蓝_E2,魅族PRO_6_Plus,魅族PRO_6s".split(","));
    concretePage.put("moto","Moto_Z,Moto_X_Pro".split(","));
    concretePage.put("sony","Xperia_XZ,Xperia_XZs".split(","));
    concretePage.put("zhongxing","天机7,天机7_Max".split(","));
  }


//具体的
  //属性
  public static Map<String,String> iPhone_7_32G = new HashMap<>();
  public static Map<String,String> iPhone_7_128G = new HashMap<>();
  public static Map<String,String> iPhone_7_256G = new HashMap<>();
  public static Map<String,String> iPhone_7_Plus_32G = new HashMap<>();
  public static Map<String,String> iPhone_7_Plus_128G = new HashMap<>();
  public static Map<String,String> iPhone_7_Plus_256G = new HashMap<>();
  public static Map<String,String> iPhone_6S_16G = new HashMap<>();
  public static Map<String,String> iPhone_6S_64G = new HashMap<>();
  public static Map<String,String> iPhone_6S_128G = new HashMap<>();
  public static Map<String,String> iPhone_6S_Plus_16G = new HashMap<>();
  public static Map<String,String> iPhone_6S_Plus_64G = new HashMap<>();
  public static Map<String,String> iPhone_6S_Plus_128G = new HashMap<>();
  public static Map<String,String> iPhone_6_16G = new HashMap<>();
  public static Map<String,String> iPhone_6_64G = new HashMap<>();
  public static Map<String,String> iPhone_6_128G = new HashMap<>();
  public static Map<String,String> iPhone_6_Plus_16G = new HashMap<>();
  public static Map<String,String> iPhone_6_Plus_64G = new HashMap<>();
  public static Map<String,String> iPhone_6_Plus_128G = new HashMap<>();
  public static Map<String,String> xiaomi6_64G = new HashMap<>();
  public static Map<String,String> xiaomi6_128G = new HashMap<>();
  public static Map<String,String> xiaomiMax_2_64G = new HashMap<>();
  public static Map<String,String> xiaomi5c_64G = new HashMap<>();
  public static Map<String,String> hongmi4X_16G = new HashMap<>();
  public static Map<String,String> xiaomiNote_2_64G = new HashMap<>();
  public static Map<String,String> xiaomi5_32G = new HashMap<>();
  public static Map<String,String> GALAXY_S8_64G = new HashMap<>();
  public static Map<String,String> GALAXY_S8_Plus_64G = new HashMap<>();
  public static Map<String,String> GALAXY_S8_Plus_128G = new HashMap<>();
  public static Map<String,String> GALAXY_S7_Edge_32G = new HashMap<>();
  public static Map<String,String> GALAXY_S7_Edge_64G = new HashMap<>();
  public static Map<String,String> GALAXY_S7_Edge_128G = new HashMap<>();
  public static Map<String,String> GALAXY_Note_7_64G = new HashMap<>();
  public static Map<String,String> GALAXY_S7_32G = new HashMap<>();
  public static Map<String,String> X9_64G = new HashMap<>();
  public static Map<String,String> X9_128G = new HashMap<>();
  public static Map<String,String> X9Plus_64G = new HashMap<>();
  public static Map<String,String> Xplay6_64G = new HashMap<>();
  public static Map<String,String> Xplay6_128G = new HashMap<>();
  public static Map<String,String> Y67_64G = new HashMap<>();
  public static Map<String,String> Xplay5_128G = new HashMap<>();
  public static Map<String,String> huaweiP10_64G = new HashMap<>();
  public static Map<String,String> huaweiP10_128G = new HashMap<>();
  public static Map<String,String> huaweiMate_9_64G = new HashMap<>();
  public static Map<String,String> huaweiP10_Plus_64G = new HashMap<>();
  public static Map<String,String> huaweiP10_Plus_128G = new HashMap<>();
  public static Map<String,String> huaweiP10_Plus_256G = new HashMap<>();
  public static Map<String,String> rongyaoV9_128G = new HashMap<>();
  public static Map<String,String> rongyao8_32G = new HashMap<>();
  public static Map<String,String> R9s_64G = new HashMap<>();
  public static Map<String,String> R11_64G = new HashMap<>();
  public static Map<String,String> R9_64G = new HashMap<>();
  public static Map<String,String> R9s_Plus_64G = new HashMap<>();
  public static Map<String,String> meilan_E2_64G = new HashMap<>();
  public static Map<String,String> meizuPRO_6_Plus_64G = new HashMap<>();
  public static Map<String,String> meizuPRO_6s_64G = new HashMap<>();
  public static Map<String,String> Moto_Z_64G = new HashMap<>();
  public static Map<String,String> Moto_X_Pro_64G = new HashMap<>();
  public static Map<String,String> Xperia_XZ_64G = new HashMap<>();
  public static Map<String,String> Xperia_XZs_64G = new HashMap<>();
  public static Map<String,String> tianji7_64G = new HashMap<>();
  public static Map<String,String> tianji7_Max_64G = new HashMap<>();


  public static void main(String[] args) {
    System.out.println(new Random().nextInt(2));

  }




  static {
    iPhone_7_32G.put("name","");
    iPhone_7_32G.put("brand","Apple");
    iPhone_7_32G.put("model","iPhone 7");
    iPhone_7_32G.put("web_price","4878");
    iPhone_7_32G.put("web_rom","32G");
    iPhone_7_32G.put("web_color","银色");
    iPhone_7_32G.put("web_size","4.7");
    iPhone_7_32G.put("web_dpi","1334×750");
    iPhone_7_32G.put("web_battery","1960mAH");
    iPhone_7_32G.put("web_num_cameras","2");
    iPhone_7_32G.put("web_recamera_par","1200万");
    iPhone_7_32G.put("web_frcamera_par","700万");
    iPhone_7_32G.put("web_weight","138g");
    iPhone_7_32G.put("web_num_core","四核");
    iPhone_7_32G.put("rate","0.05");

    iPhone_7_128G.put("name","");
    iPhone_7_128G.put("brand","Apple");
    iPhone_7_128G.put("model","iPhone 7");
    iPhone_7_128G.put("web_price","6188");
    iPhone_7_128G.put("web_rom","128G");
    iPhone_7_128G.put("web_color","银色,金色,玫瑰色,黑色,亮黑色,红色");
    iPhone_7_128G.put("web_size","4.7");
    iPhone_7_128G.put("web_dpi","1334×750");
    iPhone_7_128G.put("web_battery","1960mAH");
    iPhone_7_128G.put("web_num_cameras","2");
    iPhone_7_128G.put("web_recamera_par","1200万");
    iPhone_7_128G.put("web_frcamera_par","700万");
    iPhone_7_128G.put("web_weight","138g");
    iPhone_7_128G.put("web_num_core","四核");
    iPhone_7_128G.put("rate","0.45");

    iPhone_7_256G.put("name","");
    iPhone_7_256G.put("brand","Apple");
    iPhone_7_256G.put("model","iPhone 7");
    iPhone_7_256G.put("web_price","6988");
    iPhone_7_256G.put("web_rom","256G");
    iPhone_7_256G.put("web_color","银色,金色,玫瑰色,黑色,亮黑色,红色");
    iPhone_7_256G.put("web_size","4.7");
    iPhone_7_256G.put("web_dpi","1334×750");
    iPhone_7_256G.put("web_battery","1960mAH");
    iPhone_7_256G.put("web_num_cameras","2");
    iPhone_7_256G.put("web_recamera_par","1200万");
    iPhone_7_256G.put("web_frcamera_par","700万");
    iPhone_7_256G.put("web_weight","138g");
    iPhone_7_256G.put("web_num_core","四核");
    iPhone_7_256G.put("rate","0.5");

    iPhone_7_Plus_32G.put("name","");
    iPhone_7_Plus_32G.put("brand","Apple");
    iPhone_7_Plus_32G.put("model","iPhone 7 Plus");
    iPhone_7_Plus_32G.put("web_price","6388");
    iPhone_7_Plus_32G.put("web_rom","32G");
    iPhone_7_Plus_32G.put("web_color","银色");
    iPhone_7_Plus_32G.put("web_size","5.5");
    iPhone_7_Plus_32G.put("web_dpi","1920×1080");
    iPhone_7_Plus_32G.put("web_battery","2910mAH");
    iPhone_7_Plus_32G.put("web_num_cameras","3");
    iPhone_7_Plus_32G.put("web_recamera_par","1200万");
    iPhone_7_Plus_32G.put("web_frcamera_par","700万");
    iPhone_7_Plus_32G.put("web_weight","188g");
    iPhone_7_Plus_32G.put("web_num_core","四核");
    iPhone_7_Plus_32G.put("rate","0.5");

    iPhone_7_Plus_128G.put("name","");
    iPhone_7_Plus_128G.put("brand","Apple");
    iPhone_7_Plus_128G.put("model","iPhone 7 Plus");
    iPhone_7_Plus_128G.put("web_price","7188");
    iPhone_7_Plus_128G.put("web_rom","128G");
    iPhone_7_Plus_128G.put("web_color","银色,金色,玫瑰色,黑色,亮黑色,红色");
    iPhone_7_Plus_128G.put("web_size","5.5");
    iPhone_7_Plus_128G.put("web_dpi","1920×1080");
    iPhone_7_Plus_128G.put("web_battery","2910mAH");
    iPhone_7_Plus_128G.put("web_num_cameras","3");
    iPhone_7_Plus_128G.put("web_recamera_par","1200万");
    iPhone_7_Plus_128G.put("web_frcamera_par","700万");
    iPhone_7_Plus_128G.put("web_weight","188g");
    iPhone_7_Plus_128G.put("web_num_core","四核");
    iPhone_7_Plus_128G.put("rate","0.5");

    iPhone_7_Plus_256G.put("name","");
    iPhone_7_Plus_256G.put("brand","Apple");
    iPhone_7_Plus_256G.put("model","iPhone 7 Plus");
    iPhone_7_Plus_256G.put("web_price","7988");
    iPhone_7_Plus_256G.put("web_rom","256G");
    iPhone_7_Plus_256G.put("web_color","银色,金色,玫瑰色,黑色,亮黑色,红色");
    iPhone_7_Plus_256G.put("web_size","5.5");
    iPhone_7_Plus_256G.put("web_dpi","1920×1080");
    iPhone_7_Plus_256G.put("web_battery","2910mAH");
    iPhone_7_Plus_256G.put("web_num_cameras","3");
    iPhone_7_Plus_256G.put("web_recamera_par","1200万");
    iPhone_7_Plus_256G.put("web_frcamera_par","700万");
    iPhone_7_Plus_256G.put("web_weight","188g");
    iPhone_7_Plus_256G.put("web_num_core","四核");
    iPhone_7_Plus_256G.put("rate","0.5");

    iPhone_6S_16G.put("name","");
    iPhone_6S_16G.put("brand","Apple");
    iPhone_6S_16G.put("model","iPhone 6S");
    iPhone_6S_16G.put("web_price","4588");
    iPhone_6S_16G.put("web_rom","16G");
    iPhone_6S_16G.put("web_color","银色");
    iPhone_6S_16G.put("web_size","4.7");
    iPhone_6S_16G.put("web_dpi","1334×750");
    iPhone_6S_16G.put("web_battery","1810mAH");
    iPhone_6S_16G.put("web_num_cameras","2");
    iPhone_6S_16G.put("web_recamera_par","800万");
    iPhone_6S_16G.put("web_frcamera_par","120万");
    iPhone_6S_16G.put("web_weight","129g");
    iPhone_6S_16G.put("web_num_core","双核");
    iPhone_6S_16G.put("rate","0.5");

    iPhone_6S_64G.put("name","");
    iPhone_6S_64G.put("brand","Apple");
    iPhone_6S_64G.put("model","iPhone 6S");
    iPhone_6S_64G.put("web_price","5388");
    iPhone_6S_64G.put("web_rom","64G");
    iPhone_6S_64G.put("web_color","深空灰色,金色");
    iPhone_6S_64G.put("web_size","4.7");
    iPhone_6S_64G.put("web_dpi","1334×750");
    iPhone_6S_64G.put("web_battery","1810mAH");
    iPhone_6S_64G.put("web_num_cameras","2");
    iPhone_6S_64G.put("web_recamera_par","800万");
    iPhone_6S_64G.put("web_frcamera_par","120万");
    iPhone_6S_64G.put("web_weight","129g");
    iPhone_6S_64G.put("web_num_core","双核");
    iPhone_6S_64G.put("rate","0.5");

    iPhone_6S_128G.put("name","");
    iPhone_6S_128G.put("brand","Apple");
    iPhone_6S_128G.put("model","iPhone 6S");
    iPhone_6S_128G.put("web_price","6088");
    iPhone_6S_128G.put("web_rom","128G");
    iPhone_6S_128G.put("web_color","深空灰色,金色");
    iPhone_6S_128G.put("web_size","4.7");
    iPhone_6S_128G.put("web_dpi","1334×750");
    iPhone_6S_128G.put("web_battery","1810mAH");
    iPhone_6S_128G.put("web_num_cameras","2");
    iPhone_6S_128G.put("web_recamera_par","800万");
    iPhone_6S_128G.put("web_frcamera_par","120万");
    iPhone_6S_128G.put("web_weight","129g");
    iPhone_6S_128G.put("web_num_core","双核");
    iPhone_6S_128G.put("rate","0.5");

    iPhone_6S_Plus_16G.put("name","");
    iPhone_6S_Plus_16G.put("brand","Apple");
    iPhone_6S_Plus_16G.put("model","iPhone 6S Plus");
    iPhone_6S_Plus_16G.put("web_price","3820");
    iPhone_6S_Plus_16G.put("web_rom","16G");
    iPhone_6S_Plus_16G.put("web_color","银色");
    iPhone_6S_Plus_16G.put("web_size","5.5");
    iPhone_6S_Plus_16G.put("web_dpi","1920×1080");
    iPhone_6S_Plus_16G.put("web_battery","2750mAH");
    iPhone_6S_Plus_16G.put("web_num_cameras","2");
    iPhone_6S_Plus_16G.put("web_recamera_par","1200万");
    iPhone_6S_Plus_16G.put("web_frcamera_par","500万");
    iPhone_6S_Plus_16G.put("web_weight","192g");
    iPhone_6S_Plus_16G.put("web_num_core","双核");
    iPhone_6S_Plus_16G.put("rate","0.5");

    iPhone_6S_Plus_64G.put("name","");
    iPhone_6S_Plus_64G.put("brand","Apple");
    iPhone_6S_Plus_64G.put("model","iPhone 6S Plus");
    iPhone_6S_Plus_64G.put("web_price","4720");
    iPhone_6S_Plus_64G.put("web_rom","64G");
    iPhone_6S_Plus_64G.put("web_color","金色,玫瑰金色,银色,深空灰色");
    iPhone_6S_Plus_64G.put("web_size","5.5");
    iPhone_6S_Plus_64G.put("web_dpi","1920×1080");
    iPhone_6S_Plus_64G.put("web_battery","2750mAH");
    iPhone_6S_Plus_64G.put("web_num_cameras","2");
    iPhone_6S_Plus_64G.put("web_recamera_par","1200万");
    iPhone_6S_Plus_64G.put("web_frcamera_par","500万");
    iPhone_6S_Plus_64G.put("web_weight","192g");
    iPhone_6S_Plus_64G.put("web_num_core","双核");
    iPhone_6S_Plus_64G.put("rate","0.5");

    iPhone_6S_Plus_128G.put("name","");
    iPhone_6S_Plus_128G.put("brand","Apple");
    iPhone_6S_Plus_128G.put("model","iPhone 6S Plus");
    iPhone_6S_Plus_128G.put("web_price","6188");
    iPhone_6S_Plus_128G.put("web_rom","128G");
    iPhone_6S_Plus_128G.put("web_color","金色,玫瑰金色,银色,深空灰色");
    iPhone_6S_Plus_128G.put("web_size","5.5");
    iPhone_6S_Plus_128G.put("web_dpi","1920×1080");
    iPhone_6S_Plus_128G.put("web_battery","2750mAH");
    iPhone_6S_Plus_128G.put("web_num_cameras","2");
    iPhone_6S_Plus_128G.put("web_recamera_par","1200万");
    iPhone_6S_Plus_128G.put("web_frcamera_par","500万");
    iPhone_6S_Plus_128G.put("web_weight","192g");
    iPhone_6S_Plus_128G.put("web_num_core","双核");
    iPhone_6S_Plus_128G.put("rate","0.5");

    iPhone_6_Plus_16G.put("name","");
    iPhone_6_Plus_16G.put("brand","Apple");
    iPhone_6_Plus_16G.put("model","iPhone 6 Plus");
    iPhone_6_Plus_16G.put("web_price","3650");
    iPhone_6_Plus_16G.put("web_rom","16G");
    iPhone_6_Plus_16G.put("web_color","银色");
    iPhone_6_Plus_16G.put("web_size","5.5");
    iPhone_6_Plus_16G.put("web_dpi","1920×1080");
    iPhone_6_Plus_16G.put("web_battery","2750mAH");
    iPhone_6_Plus_16G.put("web_num_cameras","2");
    iPhone_6_Plus_16G.put("web_recamera_par","1200万");
    iPhone_6_Plus_16G.put("web_frcamera_par","500万");
    iPhone_6_Plus_16G.put("web_weight","192g");
    iPhone_6_Plus_16G.put("web_num_core","双核");
    iPhone_6_Plus_16G.put("rate","0.5");

    iPhone_6_Plus_64G.put("name","");
    iPhone_6_Plus_64G.put("brand","Apple");
    iPhone_6_Plus_64G.put("model","iPhone 6 Plus");
    iPhone_6_Plus_64G.put("web_price","4720");
    iPhone_6_Plus_64G.put("web_rom","64G");
    iPhone_6_Plus_64G.put("web_color","金色,玫瑰金色,银色,深空灰色");
    iPhone_6_Plus_64G.put("web_size","5.5");
    iPhone_6_Plus_64G.put("web_dpi","1920×1080");
    iPhone_6_Plus_64G.put("web_battery","2750mAH");
    iPhone_6_Plus_64G.put("web_num_cameras","2");
    iPhone_6_Plus_64G.put("web_recamera_par","1200万");
    iPhone_6_Plus_64G.put("web_frcamera_par","500万");
    iPhone_6_Plus_64G.put("web_weight","192g");
    iPhone_6_Plus_64G.put("web_num_core","双核");
    iPhone_6_Plus_64G.put("rate","0.5");

    iPhone_6_Plus_128G.put("name","");
    iPhone_6_Plus_128G.put("brand","Apple");
    iPhone_6_Plus_128G.put("model","iPhone 6 Plus");
    iPhone_6_Plus_128G.put("web_price","6188");
    iPhone_6_Plus_128G.put("web_rom","128G");
    iPhone_6_Plus_128G.put("web_color","金色,玫瑰金色,银色,深空灰色");
    iPhone_6_Plus_128G.put("web_size","5.5");
    iPhone_6_Plus_128G.put("web_dpi","1920×1080");
    iPhone_6_Plus_128G.put("web_battery","2750mAH");
    iPhone_6_Plus_128G.put("web_num_cameras","2");
    iPhone_6_Plus_128G.put("web_recamera_par","1200万");
    iPhone_6_Plus_128G.put("web_frcamera_par","500万");
    iPhone_6_Plus_128G.put("web_weight","192g");
    iPhone_6_Plus_128G.put("web_num_core","双核");
    iPhone_6_Plus_128G.put("rate","0.5");

    iPhone_6_16G.put("name","");
    iPhone_6_16G.put("brand","Apple");
    iPhone_6_16G.put("model","iPhone 6");
    iPhone_6_16G.put("web_price","2490");
    iPhone_6_16G.put("web_rom","16G");
    iPhone_6_16G.put("web_color","银色");
    iPhone_6_16G.put("web_size","4.7");
    iPhone_6_16G.put("web_dpi","1334×750");
    iPhone_6_16G.put("web_battery","1810mAH");
    iPhone_6_16G.put("web_num_cameras","2");
    iPhone_6_16G.put("web_recamera_par","800万");
    iPhone_6_16G.put("web_frcamera_par","120万");
    iPhone_6_16G.put("web_weight","129g");
    iPhone_6_16G.put("web_num_core","双核");
    iPhone_6_16G.put("rate","0.5");

    iPhone_6_64G.put("name","");
    iPhone_6_64G.put("brand","Apple");
    iPhone_6_64G.put("model","iPhone 6");
    iPhone_6_64G.put("web_price","3555");
    iPhone_6_64G.put("web_rom","64G");
    iPhone_6_64G.put("web_color","深空灰色,银色,金色");
    iPhone_6_64G.put("web_size","4.7");
    iPhone_6_64G.put("web_dpi","1334×750");
    iPhone_6_64G.put("web_battery","1810mAH");
    iPhone_6_64G.put("web_num_cameras","2");
    iPhone_6_64G.put("web_recamera_par","800万");
    iPhone_6_64G.put("web_frcamera_par","120万");
    iPhone_6_64G.put("web_weight","129g");
    iPhone_6_64G.put("web_num_core","双核");
    iPhone_6_64G.put("rate","0.5");

    iPhone_6_128G.put("name","");
    iPhone_6_128G.put("brand","Apple");
    iPhone_6_128G.put("model","iPhone 6");
    iPhone_6_128G.put("web_price","3888");
    iPhone_6_128G.put("web_rom","128G");
    iPhone_6_128G.put("web_color","深空灰色,银色,金色");
    iPhone_6_128G.put("web_size","4.7");
    iPhone_6_128G.put("web_dpi","1334×750");
    iPhone_6_128G.put("web_battery","1810mAH");
    iPhone_6_128G.put("web_num_cameras","2");
    iPhone_6_128G.put("web_recamera_par","800万");
    iPhone_6_128G.put("web_frcamera_par","120万");
    iPhone_6_128G.put("web_weight","129g");
    iPhone_6_128G.put("web_num_core","双核");
    iPhone_6_128G.put("rate","0.5");

    xiaomi6_64G.put("name","");
    xiaomi6_64G.put("brand","小米");
    xiaomi6_64G.put("model","小米6");
    xiaomi6_64G.put("web_price","2499");
    xiaomi6_64G.put("web_rom","64G");
    xiaomi6_64G.put("web_color","亮黑");
    xiaomi6_64G.put("web_size","5.15");
    xiaomi6_64G.put("web_dpi","1920×1080");
    xiaomi6_64G.put("web_battery","3350mAH");
    xiaomi6_64G.put("web_num_cameras","2");
    xiaomi6_64G.put("web_recamera_par","1200万");
    xiaomi6_64G.put("web_frcamera_par","800万");
    xiaomi6_64G.put("web_weight","168g");
    xiaomi6_64G.put("web_num_core","八核");
    xiaomi6_64G.put("rate","0.5");

    xiaomi6_128G.put("name","");
    xiaomi6_128G.put("brand","小米");
    xiaomi6_128G.put("model","小米6");
    xiaomi6_128G.put("web_price","2899");
    xiaomi6_128G.put("web_rom","128G");
    xiaomi6_128G.put("web_color","亮黑,亮蓝");
    xiaomi6_128G.put("web_size","5.15");
    xiaomi6_128G.put("web_dpi","1920×1080");
    xiaomi6_128G.put("web_battery","3350mAH");
    xiaomi6_128G.put("web_num_cameras","2");
    xiaomi6_128G.put("web_recamera_par","1200万");
    xiaomi6_128G.put("web_frcamera_par","800万");
    xiaomi6_128G.put("web_weight","168g");
    xiaomi6_128G.put("web_num_core","八核");
    xiaomi6_128G.put("rate","0.5");

    xiaomiMax_2_64G.put("name","");
    xiaomiMax_2_64G.put("brand","小米");
    xiaomiMax_2_64G.put("model","小米Max 2");
    xiaomiMax_2_64G.put("web_price","1699");
    xiaomiMax_2_64G.put("web_rom","64G");
    xiaomiMax_2_64G.put("web_color","金色");
    xiaomiMax_2_64G.put("web_size","6.44");
    xiaomiMax_2_64G.put("web_dpi","1920×1080");
    xiaomiMax_2_64G.put("web_battery","5300mAH");
    xiaomiMax_2_64G.put("web_num_cameras","2");
    xiaomiMax_2_64G.put("web_recamera_par","1200万");
    xiaomiMax_2_64G.put("web_frcamera_par","500万");
    xiaomiMax_2_64G.put("web_weight","211g");
    xiaomiMax_2_64G.put("web_num_core","八核");
    xiaomiMax_2_64G.put("rate","0.5");

    xiaomi5c_64G.put("name","");
    xiaomi5c_64G.put("brand","小米");
    xiaomi5c_64G.put("model","小米5c");
    xiaomi5c_64G.put("web_price","1499");
    xiaomi5c_64G.put("web_rom","64G");
    xiaomi5c_64G.put("web_color","黑色,金色,玫瑰金");
    xiaomi5c_64G.put("web_size","5.15");
    xiaomi5c_64G.put("web_dpi","1920×1080");
    xiaomi5c_64G.put("web_battery","2860mAH");
    xiaomi5c_64G.put("web_num_cameras","2");
    xiaomi5c_64G.put("web_recamera_par","1200万");
    xiaomi5c_64G.put("web_frcamera_par","800万");
    xiaomi5c_64G.put("web_weight","135g");
    xiaomi5c_64G.put("web_num_core","八核");
    xiaomi5c_64G.put("rate","0.5");

    hongmi4X_16G.put("name","");
    hongmi4X_16G.put("brand","小米");
    hongmi4X_16G.put("model","红米4X");
    hongmi4X_16G.put("web_price","799");
    hongmi4X_16G.put("web_rom","16G");
    hongmi4X_16G.put("web_color","银色,金色,灰色");
    hongmi4X_16G.put("web_size","5.5");
    hongmi4X_16G.put("web_dpi","1920×1080");
    hongmi4X_16G.put("web_battery","4100mAH");
    hongmi4X_16G.put("web_num_cameras","2");
    hongmi4X_16G.put("web_recamera_par","1300万");
    hongmi4X_16G.put("web_frcamera_par","500万");
    hongmi4X_16G.put("web_weight","175g");
    hongmi4X_16G.put("web_num_core","八核");
    hongmi4X_16G.put("rate","0.5");

    xiaomiNote_2_64G.put("name","");
    xiaomiNote_2_64G.put("brand","小米");
    xiaomiNote_2_64G.put("model","小米Note 2");
    xiaomiNote_2_64G.put("web_price","2799");
    xiaomiNote_2_64G.put("web_rom","64G");
    xiaomiNote_2_64G.put("web_color","黑色,银色");
    xiaomiNote_2_64G.put("web_size","5.7");
    xiaomiNote_2_64G.put("web_dpi","1920×1080");
    xiaomiNote_2_64G.put("web_battery","4070mAH");
    xiaomiNote_2_64G.put("web_num_cameras","2");
    xiaomiNote_2_64G.put("web_recamera_par","2256万");
    xiaomiNote_2_64G.put("web_frcamera_par","800万");
    xiaomiNote_2_64G.put("web_weight","166g");
    xiaomiNote_2_64G.put("web_num_core","四核");
    xiaomiNote_2_64G.put("rate","0.5");

    xiaomi5_32G.put("name","");
    xiaomi5_32G.put("brand","小米");
    xiaomi5_32G.put("model","小米5");
    xiaomi5_32G.put("web_price","1599");
    xiaomi5_32G.put("web_rom","32G");
    xiaomi5_32G.put("web_color","黑色,白色,金色");
    xiaomi5_32G.put("web_size","5.15");
    xiaomi5_32G.put("web_dpi","1920×1080");
    xiaomi5_32G.put("web_battery","3000mAH");
    xiaomi5_32G.put("web_num_cameras","2");
    xiaomi5_32G.put("web_recamera_par","1600万");
    xiaomi5_32G.put("web_frcamera_par","400万");
    xiaomi5_32G.put("web_weight","129g");
    xiaomi5_32G.put("web_num_core","四核");
    xiaomi5_32G.put("rate","0.5");

    GALAXY_S8_64G.put("name","");
    GALAXY_S8_64G.put("brand","三星");
    GALAXY_S8_64G.put("model","GALAXY S8");
    GALAXY_S8_64G.put("web_price","5688");
    GALAXY_S8_64G.put("web_rom","64G");
    GALAXY_S8_64G.put("web_color","烟晶灰,绮梦金,谜夜黑");
    GALAXY_S8_64G.put("web_size","5.8");
    GALAXY_S8_64G.put("web_dpi","2960×1440");
    GALAXY_S8_64G.put("web_battery","3000mAH");
    GALAXY_S8_64G.put("web_num_cameras","2");
    GALAXY_S8_64G.put("web_recamera_par","1200万");
    GALAXY_S8_64G.put("web_frcamera_par","800万");
    GALAXY_S8_64G.put("web_weight","155g");
    GALAXY_S8_64G.put("web_num_core","八核");
    GALAXY_S8_64G.put("rate","0.5");

    GALAXY_S8_Plus_64G.put("name","");
    GALAXY_S8_Plus_64G.put("brand","三星");
    GALAXY_S8_Plus_64G.put("model","GALAXY S8 Plus");
    GALAXY_S8_Plus_64G.put("web_price","6188");
    GALAXY_S8_Plus_64G.put("web_rom","64G");
    GALAXY_S8_Plus_64G.put("web_color","烟晶灰,谜夜黑,雾屿蓝");
    GALAXY_S8_Plus_64G.put("web_size","6.2");
    GALAXY_S8_Plus_64G.put("web_dpi","2960×1440");
    GALAXY_S8_Plus_64G.put("web_battery","3500mAH");
    GALAXY_S8_Plus_64G.put("web_num_cameras","2");
    GALAXY_S8_Plus_64G.put("web_recamera_par","1200万");
    GALAXY_S8_Plus_64G.put("web_frcamera_par","800万");
    GALAXY_S8_Plus_64G.put("web_weight","173g");
    GALAXY_S8_Plus_64G.put("web_num_core","八核");
    GALAXY_S8_Plus_64G.put("rate","0.5");

    GALAXY_S8_Plus_128G.put("name","");
    GALAXY_S8_Plus_128G.put("brand","三星");
    GALAXY_S8_Plus_128G.put("model","GALAXY S8 Plus");
    GALAXY_S8_Plus_128G.put("web_price","6988");
    GALAXY_S8_Plus_128G.put("web_rom","128G");
    GALAXY_S8_Plus_128G.put("web_color","绮梦金,烟晶灰,谜夜黑");
    GALAXY_S8_Plus_128G.put("web_size","6.2");
    GALAXY_S8_Plus_128G.put("web_dpi","2960×1440");
    GALAXY_S8_Plus_128G.put("web_battery","3500mAH");
    GALAXY_S8_Plus_128G.put("web_num_cameras","2");
    GALAXY_S8_Plus_128G.put("web_recamera_par","1200万");
    GALAXY_S8_Plus_128G.put("web_frcamera_par","800万");
    GALAXY_S8_Plus_128G.put("web_weight","173g");
    GALAXY_S8_Plus_128G.put("web_num_core","八核");
    GALAXY_S8_Plus_128G.put("rate","0.5");

    GALAXY_S7_Edge_32G.put("name","");
    GALAXY_S7_Edge_32G.put("brand","三星");
    GALAXY_S7_Edge_32G.put("model","GALAXY S7 Edge");
    GALAXY_S7_Edge_32G.put("web_price","4888");
    GALAXY_S7_Edge_32G.put("web_rom","32G");
    GALAXY_S7_Edge_32G.put("web_color","莹钻粉,珊瑚蓝,铂光金,星钻黑");
    GALAXY_S7_Edge_32G.put("web_size","5.5");
    GALAXY_S7_Edge_32G.put("web_dpi","2560×1440");
    GALAXY_S7_Edge_32G.put("web_battery","3600mAH");
    GALAXY_S7_Edge_32G.put("web_num_cameras","2");
    GALAXY_S7_Edge_32G.put("web_recamera_par","1200万");
    GALAXY_S7_Edge_32G.put("web_frcamera_par","800万");
    GALAXY_S7_Edge_32G.put("web_weight","157g");
    GALAXY_S7_Edge_32G.put("web_num_core","四核");
    GALAXY_S7_Edge_32G.put("rate","0.5");

    GALAXY_S7_Edge_64G.put("name","");
    GALAXY_S7_Edge_64G.put("brand","三星");
    GALAXY_S7_Edge_64G.put("model","GALAXY S7 Edge");
    GALAXY_S7_Edge_64G.put("web_price","5188");
    GALAXY_S7_Edge_64G.put("web_rom","64G");
    GALAXY_S7_Edge_64G.put("web_color","莹钻粉,珊瑚蓝,铂光金,星钻黑");
    GALAXY_S7_Edge_64G.put("web_size","5.5");
    GALAXY_S7_Edge_64G.put("web_dpi","2560×1440");
    GALAXY_S7_Edge_64G.put("web_battery","3600mAH");
    GALAXY_S7_Edge_64G.put("web_num_cameras","2");
    GALAXY_S7_Edge_64G.put("web_recamera_par","1200万");
    GALAXY_S7_Edge_64G.put("web_frcamera_par","800万");
    GALAXY_S7_Edge_64G.put("web_weight","157g");
    GALAXY_S7_Edge_64G.put("web_num_core","四核");
    GALAXY_S7_Edge_64G.put("rate","0.5");

    GALAXY_S7_Edge_128G.put("name","");
    GALAXY_S7_Edge_128G.put("brand","三星");
    GALAXY_S7_Edge_128G.put("model","GALAXY S7 Edge");
    GALAXY_S7_Edge_128G.put("web_price","5988");
    GALAXY_S7_Edge_128G.put("web_rom","128G");
    GALAXY_S7_Edge_128G.put("web_color","曜岩黑");
    GALAXY_S7_Edge_128G.put("web_size","5.5");
    GALAXY_S7_Edge_128G.put("web_dpi","2560×1440");
    GALAXY_S7_Edge_128G.put("web_battery","3600mAH");
    GALAXY_S7_Edge_128G.put("web_num_cameras","2");
    GALAXY_S7_Edge_128G.put("web_recamera_par","1200万");
    GALAXY_S7_Edge_128G.put("web_frcamera_par","800万");
    GALAXY_S7_Edge_128G.put("web_weight","157g");
    GALAXY_S7_Edge_128G.put("web_num_core","四核");
    GALAXY_S7_Edge_128G.put("rate","0.5");

    GALAXY_Note_7_64G.put("name","");
    GALAXY_Note_7_64G.put("brand","三星");
    GALAXY_Note_7_64G.put("model","GALAXY Note 7");
    GALAXY_Note_7_64G.put("web_price","4380");
    GALAXY_Note_7_64G.put("web_rom","64G");
    GALAXY_Note_7_64G.put("web_color","黑色,银色,蓝色,金色");
    GALAXY_Note_7_64G.put("web_size","5.7");
    GALAXY_Note_7_64G.put("web_dpi","2560×1440");
    GALAXY_Note_7_64G.put("web_battery","3500mAH");
    GALAXY_Note_7_64G.put("web_num_cameras","2");
    GALAXY_Note_7_64G.put("web_recamera_par","1200万");
    GALAXY_Note_7_64G.put("web_frcamera_par","800万");
    GALAXY_Note_7_64G.put("web_weight","152g");
    GALAXY_Note_7_64G.put("web_num_core","四核");
    GALAXY_Note_7_64G.put("rate","0.5");

    GALAXY_S7_32G.put("name","");
    GALAXY_S7_32G.put("brand","三星");
    GALAXY_S7_32G.put("model","GALAXY S7");
    GALAXY_S7_32G.put("web_price","3099");
    GALAXY_S7_32G.put("web_rom","32G");
    GALAXY_S7_32G.put("web_color","黑色,粉金色");
    GALAXY_S7_32G.put("web_size","5.1");
    GALAXY_S7_32G.put("web_dpi","2560×1440");
    GALAXY_S7_32G.put("web_battery","3000mAH");
    GALAXY_S7_32G.put("web_num_cameras","2");
    GALAXY_S7_32G.put("web_recamera_par","1200万");
    GALAXY_S7_32G.put("web_frcamera_par","800万");
    GALAXY_S7_32G.put("web_weight","152g");
    GALAXY_S7_32G.put("web_num_core","四核");
    GALAXY_S7_32G.put("rate","0.5");

    X9_64G.put("name","");
    X9_64G.put("brand","VIVO");
    X9_64G.put("model","X9");
    X9_64G.put("web_price","2598");
    X9_64G.put("web_rom","64G");
    X9_64G.put("web_color","金色,玫瑰金,磨砂黑,活力蓝");
    X9_64G.put("web_size","5.5");
    X9_64G.put("web_dpi","1920×1080");
    X9_64G.put("web_battery","3050mAH");
    X9_64G.put("web_num_cameras","3");
    X9_64G.put("web_recamera_par","1600万");
    X9_64G.put("web_frcamera_par","2000万");
    X9_64G.put("web_weight","154g");
    X9_64G.put("web_num_core","八核");
    X9_64G.put("rate","0.5");

    X9_128G.put("name","");
    X9_128G.put("brand","VIVO");
    X9_128G.put("model","X9");
    X9_128G.put("web_price","2799");
    X9_128G.put("web_rom","128G");
    X9_128G.put("web_color","金色,玫瑰金");
    X9_128G.put("web_size","5.5");
    X9_128G.put("web_dpi","1920×1080");
    X9_128G.put("web_battery","3050mAH");
    X9_128G.put("web_num_cameras","3");
    X9_128G.put("web_recamera_par","1600万");
    X9_128G.put("web_frcamera_par","2000万");
    X9_128G.put("web_weight","154g");
    X9_128G.put("web_num_core","八核");
    X9_128G.put("rate","0.5");

    X9Plus_64G.put("name","");
    X9Plus_64G.put("brand","VIVO");
    X9Plus_64G.put("model","X9Plus");
    X9Plus_64G.put("web_price","2998");
    X9Plus_64G.put("web_rom","64G");
    X9Plus_64G.put("web_color","金色,玫瑰金,星空灰");
    X9Plus_64G.put("web_size","5.88");
    X9Plus_64G.put("web_dpi","1920×1080");
    X9Plus_64G.put("web_battery","4000mAH");
    X9Plus_64G.put("web_num_cameras","3");
    X9Plus_64G.put("web_recamera_par","1600万");
    X9Plus_64G.put("web_frcamera_par","2000万");
    X9Plus_64G.put("web_weight","199g");
    X9Plus_64G.put("web_num_core","八核");
    X9Plus_64G.put("rate","0.5");

    Xplay6_64G.put("name","");
    Xplay6_64G.put("brand","VIVO");
    Xplay6_64G.put("model","Xplay6");
    Xplay6_64G.put("web_price","3998");
    Xplay6_64G.put("web_rom","64G");
    Xplay6_64G.put("web_color","金色,玫瑰金,磨砂黑");
    Xplay6_64G.put("web_size","5.46");
    Xplay6_64G.put("web_dpi","2560×1440");
    Xplay6_64G.put("web_battery","4080mAH");
    Xplay6_64G.put("web_num_cameras","3");
    Xplay6_64G.put("web_recamera_par","1200万");
    Xplay6_64G.put("web_frcamera_par","1600万");
    Xplay6_64G.put("web_weight","178g");
    Xplay6_64G.put("web_num_core","四核");
    Xplay6_64G.put("rate","0.5");

    Xplay6_128G.put("name","");
    Xplay6_128G.put("brand","VIVO");
    Xplay6_128G.put("model","Xplay6");
    Xplay6_128G.put("web_price","4498");
    Xplay6_128G.put("web_rom","128G");
    Xplay6_128G.put("web_color","玫瑰金,磨砂黑");
    Xplay6_128G.put("web_size","5.46");
    Xplay6_128G.put("web_dpi","2560×1440");
    Xplay6_128G.put("web_battery","4080mAH");
    Xplay6_128G.put("web_num_cameras","3");
    Xplay6_128G.put("web_recamera_par","1200万");
    Xplay6_128G.put("web_frcamera_par","1600万");
    Xplay6_128G.put("web_weight","178g");
    Xplay6_128G.put("web_num_core","四核");
    Xplay6_128G.put("rate","0.5");

    Y67_64G.put("name","");
    Y67_64G.put("brand","VIVO");
    Y67_64G.put("model","Y67");
    Y67_64G.put("web_price","1798");
    Y67_64G.put("web_rom","64G");
    Y67_64G.put("web_color","香槟金,玫瑰金,星空灰,磨砂黑,炫动橙");
    Y67_64G.put("web_size","5.5");
    Y67_64G.put("web_dpi","1280×720");
    Y67_64G.put("web_battery","3000mAH");
    Y67_64G.put("web_num_cameras","2");
    Y67_64G.put("web_recamera_par","1300万");
    Y67_64G.put("web_frcamera_par","1600万");
    Y67_64G.put("web_weight","154g");
    Y67_64G.put("web_num_core","八核");
    Y67_64G.put("rate","0.5");

    Xplay5_128G.put("name","");
    Xplay5_128G.put("brand","VIVO");
    Xplay5_128G.put("model","Xplay5");
    Xplay5_128G.put("web_price","2699");
    Xplay5_128G.put("web_rom","128G");
    Xplay5_128G.put("web_color","香槟金,玫瑰金");
    Xplay5_128G.put("web_size","5.43");
    Xplay5_128G.put("web_dpi","2560×1440");
    Xplay5_128G.put("web_battery","3600mAH");
    Xplay5_128G.put("web_num_cameras","2");
    Xplay5_128G.put("web_recamera_par","1600万");
    Xplay5_128G.put("web_frcamera_par","800万");
    Xplay5_128G.put("web_weight","167.8g");
    Xplay5_128G.put("web_num_core","八核");
    Xplay5_128G.put("rate","0.5");

    huaweiP10_64G.put("name","");
    huaweiP10_64G.put("brand","华为");
    huaweiP10_64G.put("model","华为P10");
    huaweiP10_64G.put("web_price","3358");
    huaweiP10_64G.put("web_rom","64G");
    huaweiP10_64G.put("web_color","玫瑰金,草木绿,钻雕蓝,陶瓷白,曜石黑,钻雕金");
    huaweiP10_64G.put("web_size","5.1");
    huaweiP10_64G.put("web_dpi","1920×1080");
    huaweiP10_64G.put("web_battery","3200mAH");
    huaweiP10_64G.put("web_num_cameras","3");
    huaweiP10_64G.put("web_recamera_par","2000万");
    huaweiP10_64G.put("web_frcamera_par","800万");
    huaweiP10_64G.put("web_weight","145g");
    huaweiP10_64G.put("web_num_core","八核");
    huaweiP10_64G.put("rate","0.5");

    huaweiP10_128G.put("name","");
    huaweiP10_128G.put("brand","华为");
    huaweiP10_128G.put("model","华为P10");
    huaweiP10_128G.put("web_price","3788");
    huaweiP10_128G.put("web_rom","128G");
    huaweiP10_128G.put("web_color","玫瑰金,草木绿,钻雕蓝,陶瓷白,曜石黑,钻雕金");
    huaweiP10_128G.put("web_size","5.1");
    huaweiP10_128G.put("web_dpi","1920×1080");
    huaweiP10_128G.put("web_battery","3200mAH");
    huaweiP10_128G.put("web_num_cameras","3");
    huaweiP10_128G.put("web_recamera_par","2000万");
    huaweiP10_128G.put("web_frcamera_par","800万");
    huaweiP10_128G.put("web_weight","145g");
    huaweiP10_128G.put("web_num_core","八核");
    huaweiP10_128G.put("rate","0.5");

    huaweiMate_9_64G.put("name","");
    huaweiMate_9_64G.put("brand","华为");
    huaweiMate_9_64G.put("model","华为Mate 9");
    huaweiMate_9_64G.put("web_price","3099");
    huaweiMate_9_64G.put("web_rom","64G");
    huaweiMate_9_64G.put("web_color","陶瓷白,香槟金,摩卡金,月光银,苍穹灰,黑色");
    huaweiMate_9_64G.put("web_size","5.9");
    huaweiMate_9_64G.put("web_dpi","1920×1080");
    huaweiMate_9_64G.put("web_battery","4000mAH");
    huaweiMate_9_64G.put("web_num_cameras","3");
    huaweiMate_9_64G.put("web_recamera_par","2000万");
    huaweiMate_9_64G.put("web_frcamera_par","800万");
    huaweiMate_9_64G.put("web_weight","190g");
    huaweiMate_9_64G.put("web_num_core","八核");
    huaweiMate_9_64G.put("rate","0.5");

    huaweiP10_Plus_64G.put("name","");
    huaweiP10_Plus_64G.put("brand","华为");
    huaweiP10_Plus_64G.put("model","华为P10 Plus");
    huaweiP10_Plus_64G.put("web_price","3888");
    huaweiP10_Plus_64G.put("web_rom","64G");
    huaweiP10_Plus_64G.put("web_color","钻雕金,曜石黑,玫瑰金,钻雕蓝,陶瓷白,草木绿");
    huaweiP10_Plus_64G.put("web_size","5.5");
    huaweiP10_Plus_64G.put("web_dpi","2560×1440");
    huaweiP10_Plus_64G.put("web_battery","3750mAH");
    huaweiP10_Plus_64G.put("web_num_cameras","3");
    huaweiP10_Plus_64G.put("web_recamera_par","2000万");
    huaweiP10_Plus_64G.put("web_frcamera_par","800万");
    huaweiP10_Plus_64G.put("web_weight","165g");
    huaweiP10_Plus_64G.put("web_num_core","八核");
    huaweiP10_Plus_64G.put("rate","0.5");

    huaweiP10_Plus_128G.put("name","");
    huaweiP10_Plus_128G.put("brand","华为");
    huaweiP10_Plus_128G.put("model","华为P10 Plus");
    huaweiP10_Plus_128G.put("web_price","4268");
    huaweiP10_Plus_128G.put("web_rom","128G");
    huaweiP10_Plus_128G.put("web_color","钻雕金,曜石黑,玫瑰金,钻雕蓝,陶瓷白,草木绿");
    huaweiP10_Plus_128G.put("web_size","5.5");
    huaweiP10_Plus_128G.put("web_dpi","2560×1440");
    huaweiP10_Plus_128G.put("web_battery","3750mAH");
    huaweiP10_Plus_128G.put("web_num_cameras","3");
    huaweiP10_Plus_128G.put("web_recamera_par","2000万");
    huaweiP10_Plus_128G.put("web_frcamera_par","800万");
    huaweiP10_Plus_128G.put("web_weight","165g");
    huaweiP10_Plus_128G.put("web_num_core","八核");
    huaweiP10_Plus_128G.put("rate","0.5");

    huaweiP10_Plus_256G.put("name","");
    huaweiP10_Plus_256G.put("brand","华为");
    huaweiP10_Plus_256G.put("model","华为P10 Plus");
    huaweiP10_Plus_256G.put("web_price","5333");
    huaweiP10_Plus_256G.put("web_rom","256G");
    huaweiP10_Plus_256G.put("web_color","钻雕金,曜石黑,玫瑰金,钻雕蓝,陶瓷白,草木绿");
    huaweiP10_Plus_256G.put("web_size","5.5");
    huaweiP10_Plus_256G.put("web_dpi","2560×1440");
    huaweiP10_Plus_256G.put("web_battery","3750mAH");
    huaweiP10_Plus_256G.put("web_num_cameras","3");
    huaweiP10_Plus_256G.put("web_recamera_par","2000万");
    huaweiP10_Plus_256G.put("web_frcamera_par","800万");
    huaweiP10_Plus_256G.put("web_weight","165g");
    huaweiP10_Plus_256G.put("web_num_core","八核");
    huaweiP10_Plus_256G.put("rate","0.5");

    rongyaoV9_128G.put("name","");
    rongyaoV9_128G.put("brand","华为");
    rongyaoV9_128G.put("model","荣耀V9");
    rongyaoV9_128G.put("web_price","3249");
    rongyaoV9_128G.put("web_rom","128G");
    rongyaoV9_128G.put("web_color","铂光金,幻夜黑,极光蓝,魅焰红");
    rongyaoV9_128G.put("web_size","5.7");
    rongyaoV9_128G.put("web_dpi","2560×1440");
    rongyaoV9_128G.put("web_battery","4000mAH");
    rongyaoV9_128G.put("web_num_cameras","3");
    rongyaoV9_128G.put("web_recamera_par","2000万");
    rongyaoV9_128G.put("web_frcamera_par","800万");
    rongyaoV9_128G.put("web_weight","184g");
    rongyaoV9_128G.put("web_num_core","八核");
    rongyaoV9_128G.put("rate","0.5");

    rongyao8_32G.put("name","");
    rongyao8_32G.put("brand","华为");
    rongyao8_32G.put("model","荣耀8");
    rongyao8_32G.put("web_price","1716");
    rongyao8_32G.put("web_rom","32G");
    rongyao8_32G.put("web_color","幻夜黑,珠光白,流光金,樱语粉,魅海蓝");
    rongyao8_32G.put("web_size","5.2");
    rongyao8_32G.put("web_dpi","2560×1440");
    rongyao8_32G.put("web_battery","3000mAH");
    rongyao8_32G.put("web_num_cameras","3");
    rongyao8_32G.put("web_recamera_par","1200万");
    rongyao8_32G.put("web_frcamera_par","800万");
    rongyao8_32G.put("web_weight","153g");
    rongyao8_32G.put("web_num_core","八核");
    rongyao8_32G.put("rate","0.5");

    R9s_64G.put("name","");
    R9s_64G.put("brand","OPPO");
    R9s_64G.put("model","R9s");
    R9s_64G.put("web_price","2599");
    R9s_64G.put("web_rom","64G");
    R9s_64G.put("web_color","金色,玫瑰金,黑色,绿色,红色");
    R9s_64G.put("web_size","5.5");
    R9s_64G.put("web_dpi","1920×1440");
    R9s_64G.put("web_battery","3010mAH");
    R9s_64G.put("web_num_cameras","2");
    R9s_64G.put("web_recamera_par","1600万");
    R9s_64G.put("web_frcamera_par","1600万");
    R9s_64G.put("web_weight","145g");
    R9s_64G.put("web_num_core","八核");
    R9s_64G.put("rate","0.5");

    R11_64G.put("name","");
    R11_64G.put("brand","OPPO");
    R11_64G.put("model","R11");
    R11_64G.put("web_price","2999");
    R11_64G.put("web_rom","64G");
    R11_64G.put("web_color","金色,玫瑰金,黑色,热力红");
    R11_64G.put("web_size","5.5");
    R11_64G.put("web_dpi","1920×1080");
    R11_64G.put("web_battery","3000mAH");
    R11_64G.put("web_num_cameras","3");
    R11_64G.put("web_recamera_par","2000万");
    R11_64G.put("web_frcamera_par","2000万");
    R11_64G.put("web_weight","150g");
    R11_64G.put("web_num_core","八核");
    R11_64G.put("rate","0.5");

    R9_64G.put("name","");
    R9_64G.put("brand","OPPO");
    R9_64G.put("model","R9");
    R9_64G.put("web_price","2499");
    R9_64G.put("web_rom","64G");
    R9_64G.put("web_color","金色,玫瑰金");
    R9_64G.put("web_size","5.5");
    R9_64G.put("web_dpi","1920×1080");
    R9_64G.put("web_battery","2850mAH");
    R9_64G.put("web_num_cameras","2");
    R9_64G.put("web_recamera_par","1300万");
    R9_64G.put("web_frcamera_par","1600万");
    R9_64G.put("web_weight","145g");
    R9_64G.put("web_num_core","八核");
    R9_64G.put("rate","0.5");

    R9s_Plus_64G.put("name","");
    R9s_Plus_64G.put("brand","OPPO");
    R9s_Plus_64G.put("model","R9s Plus");
    R9s_Plus_64G.put("web_price","2779");
    R9s_Plus_64G.put("web_rom","64G");
    R9s_Plus_64G.put("web_color","金色,玫瑰金");
    R9s_Plus_64G.put("web_size","6");
    R9s_Plus_64G.put("web_dpi","1920×1080");
    R9s_Plus_64G.put("web_battery","4120mAH");
    R9s_Plus_64G.put("web_num_cameras","2");
    R9s_Plus_64G.put("web_recamera_par","1600万");
    R9s_Plus_64G.put("web_frcamera_par","1600万");
    R9s_Plus_64G.put("web_weight","185g");
    R9s_Plus_64G.put("web_num_core","八核");
    R9s_Plus_64G.put("rate","0.5");

    meilan_E2_64G.put("name","");
    meilan_E2_64G.put("brand","魅族");
    meilan_E2_64G.put("model","魅蓝_E2");
    meilan_E2_64G.put("web_price","1599");
    meilan_E2_64G.put("web_rom","64G");
    meilan_E2_64G.put("web_color","曜石黑,香槟金,月光银");
    meilan_E2_64G.put("web_size","5.5");
    meilan_E2_64G.put("web_dpi","1920×1080");
    meilan_E2_64G.put("web_battery","3400mAH");
    meilan_E2_64G.put("web_num_cameras","2");
    meilan_E2_64G.put("web_recamera_par","1300万");
    meilan_E2_64G.put("web_frcamera_par","800万");
    meilan_E2_64G.put("web_weight","155g");
    meilan_E2_64G.put("web_num_core","八核");
    meilan_E2_64G.put("rate","0.5");

    meizuPRO_6_Plus_64G.put("name","");
    meizuPRO_6_Plus_64G.put("brand","魅族");
    meizuPRO_6_Plus_64G.put("model","魅族PRO 6 Plus");
    meizuPRO_6_Plus_64G.put("web_price","2999");
    meizuPRO_6_Plus_64G.put("web_rom","64G");
    meizuPRO_6_Plus_64G.put("web_color","香槟金,深空灰,月光银");
    meizuPRO_6_Plus_64G.put("web_size","5.7");
    meizuPRO_6_Plus_64G.put("web_dpi","2560×1440");
    meizuPRO_6_Plus_64G.put("web_battery","3400mAH");
    meizuPRO_6_Plus_64G.put("web_num_cameras","2");
    meizuPRO_6_Plus_64G.put("web_recamera_par","1200万");
    meizuPRO_6_Plus_64G.put("web_frcamera_par","500万");
    meizuPRO_6_Plus_64G.put("web_weight","158g");
    meizuPRO_6_Plus_64G.put("web_num_core","八核");
    meizuPRO_6_Plus_64G.put("rate","0.5");

    meizuPRO_6s_64G.put("name","");
    meizuPRO_6s_64G.put("brand","魅族");
    meizuPRO_6s_64G.put("model","魅族PRO 6s");
    meizuPRO_6s_64G.put("web_price","2299");
    meizuPRO_6s_64G.put("web_rom","64G");
    meizuPRO_6s_64G.put("web_color","月光银,玫瑰金,星空黑,香槟金");
    meizuPRO_6s_64G.put("web_size","5.2");
    meizuPRO_6s_64G.put("web_dpi","1920×1080");
    meizuPRO_6s_64G.put("web_battery","3060mAH");
    meizuPRO_6s_64G.put("web_num_cameras","2");
    meizuPRO_6s_64G.put("web_recamera_par","1200万");
    meizuPRO_6s_64G.put("web_frcamera_par","500万");
    meizuPRO_6s_64G.put("web_weight","163g");
    meizuPRO_6s_64G.put("web_num_core","十核");
    meizuPRO_6s_64G.put("rate","0.5");

    Moto_Z_64G.put("name","");
    Moto_Z_64G.put("brand","Moto");
    Moto_Z_64G.put("model","Moto Z");
    Moto_Z_64G.put("web_price","3999");
    Moto_Z_64G.put("web_rom","64G");
    Moto_Z_64G.put("web_color","流金黑");
    Moto_Z_64G.put("web_size","5.5");
    Moto_Z_64G.put("web_dpi","2560×1440");
    Moto_Z_64G.put("web_battery","2600mAH");
    Moto_Z_64G.put("web_num_cameras","2");
    Moto_Z_64G.put("web_recamera_par","1300万");
    Moto_Z_64G.put("web_frcamera_par","500万");
    Moto_Z_64G.put("web_weight","137g");
    Moto_Z_64G.put("web_num_core","四核");
    Moto_Z_64G.put("rate","0.5");

    Moto_X_Pro_64G.put("name","");
    Moto_X_Pro_64G.put("brand","Moto");
    Moto_X_Pro_64G.put("model","Moto X Pro");
    Moto_X_Pro_64G.put("web_price","1769");
    Moto_X_Pro_64G.put("web_rom","64G");
    Moto_X_Pro_64G.put("web_color","黑色");
    Moto_X_Pro_64G.put("web_size","5.96");
    Moto_X_Pro_64G.put("web_dpi","2560×1440");
    Moto_X_Pro_64G.put("web_battery","3220mAH");
    Moto_X_Pro_64G.put("web_num_cameras","2");
    Moto_X_Pro_64G.put("web_recamera_par","1300万");
    Moto_X_Pro_64G.put("web_frcamera_par","200万");
    Moto_X_Pro_64G.put("web_weight","184g");
    Moto_X_Pro_64G.put("web_num_core","四核");
    Moto_X_Pro_64G.put("rate","0.5");

    Xperia_XZ_64G.put("name","");
    Xperia_XZ_64G.put("brand","索尼");
    Xperia_XZ_64G.put("model","Xperia XZ");
    Xperia_XZ_64G.put("web_price","3999");
    Xperia_XZ_64G.put("web_rom","64G");
    Xperia_XZ_64G.put("web_color","静谧蓝,流光银,幻影黑,深蜜粉");
    Xperia_XZ_64G.put("web_size","5.2");
    Xperia_XZ_64G.put("web_dpi","1920×1080");
    Xperia_XZ_64G.put("web_battery","2900mAH");
    Xperia_XZ_64G.put("web_num_cameras","2");
    Xperia_XZ_64G.put("web_recamera_par","2300万");
    Xperia_XZ_64G.put("web_frcamera_par","1300万");
    Xperia_XZ_64G.put("web_weight","161g");
    Xperia_XZ_64G.put("web_num_core","四核");
    Xperia_XZ_64G.put("rate","0.5");

    Xperia_XZs_64G.put("name","");
    Xperia_XZs_64G.put("brand","索尼");
    Xperia_XZs_64G.put("model","Xperia XZs");
    Xperia_XZs_64G.put("web_price","4699");
    Xperia_XZs_64G.put("web_rom","64G");
    Xperia_XZs_64G.put("web_color","暗黑,暖银,冰蓝");
    Xperia_XZs_64G.put("web_size","5.2");
    Xperia_XZs_64G.put("web_dpi","1920×1080");
    Xperia_XZs_64G.put("web_battery","2900mAH");
    Xperia_XZs_64G.put("web_num_cameras","2");
    Xperia_XZs_64G.put("web_recamera_par","1900万");
    Xperia_XZs_64G.put("web_frcamera_par","1300万");
    Xperia_XZs_64G.put("web_weight","161g");
    Xperia_XZs_64G.put("web_num_core","四核");
    Xperia_XZs_64G.put("rate","0.5");

    tianji7_64G.put("name","");
    tianji7_64G.put("brand","中兴");
    tianji7_64G.put("model","天机7");
    tianji7_64G.put("web_price","3099");
    tianji7_64G.put("web_rom","64G");
    tianji7_64G.put("web_color","金色");
    tianji7_64G.put("web_size","5.5");
    tianji7_64G.put("web_dpi","2560×1440");
    tianji7_64G.put("web_battery","3250mAH");
    tianji7_64G.put("web_num_cameras","2");
    tianji7_64G.put("web_recamera_par","2000万");
    tianji7_64G.put("web_frcamera_par","800万");
    tianji7_64G.put("web_weight","175g");
    tianji7_64G.put("web_num_core","四核");
    tianji7_64G.put("rate","0.5");

    tianji7_Max_64G.put("name","");
    tianji7_Max_64G.put("brand","中兴");
    tianji7_Max_64G.put("model","天机7 Max");
    tianji7_Max_64G.put("web_price","2999");
    tianji7_Max_64G.put("web_rom","64G");
    tianji7_Max_64G.put("web_color","华尔金");
    tianji7_Max_64G.put("web_size","6");
    tianji7_Max_64G.put("web_dpi","1920×1080");
    tianji7_Max_64G.put("web_battery","4100mAH");
    tianji7_Max_64G.put("web_num_cameras","3");
    tianji7_Max_64G.put("web_recamera_par","1300万");
    tianji7_Max_64G.put("web_frcamera_par","1300万");
    tianji7_Max_64G.put("web_weight","196g");
    tianji7_Max_64G.put("web_num_core","八核");
    tianji7_Max_64G.put("rate","0.5");
  }

  //型号
  public static Map<String,Map<String,String>> iPhone_7 = new HashMap<>();
  public static Map<String,Map<String,String>> iPhone_7_Plus = new HashMap<>();
  public static Map<String,Map<String,String>> iPhone_6S = new HashMap<>();
  public static Map<String,Map<String,String>> iPhone_6S_Plus = new HashMap<>();
  public static Map<String,Map<String,String>> iPhone_6 = new HashMap<>();
  public static Map<String,Map<String,String>> iPhone_6_Plus = new HashMap<>();

  public static Map<String,Map<String,String>> xiaomi6 = new HashMap<>();
  public static Map<String,Map<String,String>> xiaomiMax_2 = new HashMap<>();
  public static Map<String,Map<String,String>> xiaomi5c = new HashMap<>();
  public static Map<String,Map<String,String>> hongmi4X = new HashMap<>();
  public static Map<String,Map<String,String>> xiaomiNote_2 = new HashMap<>();
  public static Map<String,Map<String,String>> xiaomi5 = new HashMap<>();

  public static Map<String,Map<String,String>> GALAXY_S8 = new HashMap<>();
  public static Map<String,Map<String,String>> GALAXY_S8_Plus = new HashMap<>();
  public static Map<String,Map<String,String>> GALAXY_S7_Edge = new HashMap<>();
  public static Map<String,Map<String,String>> GALAXY_Note_7 = new HashMap<>();
  public static Map<String,Map<String,String>> GALAXY_S7 = new HashMap<>();

  public static Map<String,Map<String,String>> X9 = new HashMap<>();
  public static Map<String,Map<String,String>> X9Plus = new HashMap<>();
  public static Map<String,Map<String,String>> Xplay6 = new HashMap<>();
  public static Map<String,Map<String,String>> Y67 = new HashMap<>();
  public static Map<String,Map<String,String>> Xplay5 = new HashMap<>();

  public static Map<String,Map<String,String>> huaweiP10 = new HashMap<>();
  public static Map<String,Map<String,String>> huaweiMate_9 = new HashMap<>();
  public static Map<String,Map<String,String>> huaweiP10_Plus = new HashMap<>();
  public static Map<String,Map<String,String>> rongyaoV9 = new HashMap<>();
  public static Map<String,Map<String,String>> rongyao8 = new HashMap<>();

  public static Map<String,Map<String,String>> R9s = new HashMap<>();
  public static Map<String,Map<String,String>> R11 = new HashMap<>();
  public static Map<String,Map<String,String>> R9 = new HashMap<>();
  public static Map<String,Map<String,String>> R9s_Plus = new HashMap<>();

  public static Map<String,Map<String,String>> meilan_E2 = new HashMap<>();
  public static Map<String,Map<String,String>> meizuPRO_6_Plus = new HashMap<>();
  public static Map<String,Map<String,String>> meizuPRO_6s = new HashMap<>();

  public static Map<String,Map<String,String>> Moto_Z = new HashMap<>();
  public static Map<String,Map<String,String>> Moto_X_Pro = new HashMap<>();

  public static Map<String,Map<String,String>> Xperia_XZ = new HashMap<>();
  public static Map<String,Map<String,String>> Xperia_XZs = new HashMap<>();

  public static Map<String,Map<String,String>> tianji7 = new HashMap<>();
  public static Map<String,Map<String,String>> tianji7_Max = new HashMap<>();



  static {
    Map<String,String> rateMap1 = new HashMap<>();
    rateMap1.put("rate","40");
    iPhone_7.put("rate",rateMap1);
    iPhone_7.put("iPhone_7_32G",iPhone_7_32G);
    iPhone_7.put("iPhone_7_128G",iPhone_7_128G);
    iPhone_7.put("iPhone_7_256G",iPhone_7_256G);

    Map<String,String> rateMap2 = new HashMap<>();
    rateMap2.put("rate","70");
    iPhone_7_Plus.put("rate",rateMap2);
    iPhone_7_Plus.put("iPhone_7_Plus_32G",iPhone_7_Plus_32G);
    iPhone_7_Plus.put("iPhone_7_Plus_128G",iPhone_7_Plus_128G);
    iPhone_7_Plus.put("iPhone_7_Plus_256G",iPhone_7_Plus_256G);

    Map<String,String> rateMap3 = new HashMap<>();
    rateMap3.put("rate","85");
    iPhone_6S.put("rate",rateMap3);
    iPhone_6S.put("iPhone_6S_16G",iPhone_6S_16G);
    iPhone_6S.put("iPhone_6S_64G",iPhone_6S_64G);
    iPhone_6S.put("iPhone_6S_128G",iPhone_6S_128G);

    Map<String,String> rateMap4 = new HashMap<>();
    rateMap4.put("rate","95");
    iPhone_6S_Plus.put("rate",rateMap4);
    iPhone_6S_Plus.put("iPhone_6S_Plus_16G",iPhone_6S_Plus_16G);
    iPhone_6S_Plus.put("iPhone_6S_Plus_64G",iPhone_6S_Plus_64G);
    iPhone_6S_Plus.put("iPhone_6S_Plus_128G",iPhone_6S_Plus_128G);

    Map<String,String> rateMap5 = new HashMap<>();
    rateMap5.put("rate","97");
    iPhone_6.put("rate",rateMap5);
    iPhone_6.put("iPhone_6_16G",iPhone_6_16G);
    iPhone_6.put("iPhone_6_64G",iPhone_6_64G);
    iPhone_6.put("iPhone_6_128G",iPhone_6_128G);

    Map<String,String> rateMap6 = new HashMap<>();
    rateMap6.put("rate","100");
    iPhone_6_Plus.put("rate",rateMap6);
    iPhone_6_Plus.put("iPhone_6_Plus_16G",iPhone_6_Plus_16G);
    iPhone_6_Plus.put("iPhone_6_Plus_64G",iPhone_6_Plus_64G);
    iPhone_6_Plus.put("iPhone_6_Plus_128G",iPhone_6_Plus_128G);

    Map<String,String> rateMap7 = new HashMap<>();
    rateMap7.put("rate","40");
    xiaomi6.put("rate",rateMap7);
    xiaomi6.put("xiaomi6_64G",xiaomi6_64G);
    xiaomi6.put("xiaomi6_128G",xiaomi6_128G);

    Map<String,String> rateMap8 = new HashMap<>();
    rateMap8.put("rate","60");
    xiaomiMax_2.put("rate",rateMap8);
    xiaomiMax_2.put("xiaomiMax_2_64G",xiaomiMax_2_64G);

    Map<String,String> rateMap9 = new HashMap<>();
    rateMap9.put("rate","69");
    xiaomi5c.put("rate",rateMap9);
    xiaomi5c.put("xiaomi5c_64G",xiaomi5c_64G);

    Map<String,String> rateMap10 = new HashMap<>();
    rateMap10.put("rate","78");
    hongmi4X.put("rate",rateMap10);
    hongmi4X.put("hongmi4X_16G",hongmi4X_16G);

    Map<String,String> rateMap11 = new HashMap<>();
    rateMap11.put("rate","85");
    xiaomiNote_2.put("rate",rateMap11);
    xiaomiNote_2.put("xiaomiNote_2_64G",xiaomiNote_2_64G);

    Map<String,String> rateMap12 = new HashMap<>();
    rateMap12.put("rate","100");
    xiaomi5.put("rate",rateMap12);
    xiaomi5.put("xiaomi5_32G",xiaomi5_32G);

    Map<String,String> rateMap13 = new HashMap<>();
    rateMap13.put("rate","45");
    GALAXY_S8.put("rate",rateMap13);
    GALAXY_S8.put("GALAXY_S8_64G",GALAXY_S8_64G);

    Map<String,String> rateMap14 = new HashMap<>();
    rateMap14.put("rate","66");
    GALAXY_S8_Plus.put("rate",rateMap14);
    GALAXY_S8_Plus.put("GALAXY_S8_Plus_64G",GALAXY_S8_Plus_64G);
    GALAXY_S8_Plus.put("GALAXY_S8_Plus_128G",GALAXY_S8_Plus_128G);

    Map<String,String> rateMap15 = new HashMap<>();
    rateMap15.put("rate","88");
    GALAXY_S7_Edge.put("rate",rateMap15);
    GALAXY_S7_Edge.put("GALAXY_S7_Edge_32G",GALAXY_S7_Edge_32G);
    GALAXY_S7_Edge.put("GALAXY_S7_Edge_64G",GALAXY_S7_Edge_64G);
    GALAXY_S7_Edge.put("GALAXY_S7_Edge_128G",GALAXY_S7_Edge_128G);

    Map<String,String> rateMap16 = new HashMap<>();
    rateMap16.put("rate","95");
    GALAXY_Note_7.put("rate",rateMap16);
    GALAXY_Note_7.put("GALAXY_Note_7_64G",GALAXY_Note_7_64G);

    Map<String,String> rateMap17 = new HashMap<>();
    rateMap17.put("rate","100");
    GALAXY_S7.put("rate",rateMap17);
    GALAXY_S7.put("GALAXY_S7_32G",GALAXY_S7_32G);

    Map<String,String> rateMap18 = new HashMap<>();
    rateMap18.put("rate","42");
    X9.put("rate",rateMap18);
    X9.put("X9_64G",X9_64G);
    X9.put("X9_128G",X9_128G);

    Map<String,String> rateMap19 = new HashMap<>();
    rateMap19.put("rate","63");
    X9Plus.put("rate",rateMap19);
    X9Plus.put("X9Plus_64G",X9Plus_64G);

    Map<String,String> rateMap20 = new HashMap<>();
    rateMap20.put("rate","79");
    Xplay6.put("rate",rateMap20);
    Xplay6.put("Xplay6_64G",Xplay6_64G);
    Xplay6.put("Xplay6_128G",Xplay6_128G);

    Map<String,String> rateMap21 = new HashMap<>();
    rateMap21.put("rate","93");
    Y67.put("rate",rateMap21);
    Y67.put("Y67_64G",Y67_64G);

    Map<String,String> rateMap22 = new HashMap<>();
    rateMap22.put("rate","100");
    Xplay5.put("rate",rateMap22);
    Xplay5.put("Xplay5_128G",Xplay5_128G);

    Map<String,String> rateMap23 = new HashMap<>();
    rateMap23.put("rate","42");
    huaweiP10.put("rate",rateMap23);
    huaweiP10.put("huaweiP10_64G",huaweiP10_64G);
    huaweiP10.put("huaweiP10_128G",huaweiP10_128G);

    Map<String,String> rateMap24 = new HashMap<>();
    rateMap24.put("rate","63");
    huaweiMate_9.put("rate",rateMap24);
    huaweiMate_9.put("huaweiMate_9_64G",huaweiMate_9_64G);

    Map<String,String> rateMap25 = new HashMap<>();
    rateMap25.put("rate","75");
    huaweiP10_Plus.put("rate",rateMap25);
    huaweiP10_Plus.put("huaweiP10_Plus_64G",huaweiP10_Plus_64G);
    huaweiP10_Plus.put("huaweiP10_Plus_128G",huaweiP10_Plus_128G);
    huaweiP10_Plus.put("huaweiP10_Plus_256G",huaweiP10_Plus_256G);

    Map<String,String> rateMap26 = new HashMap<>();
    rateMap26.put("rate","86");
    rongyaoV9.put("rate",rateMap26);
    rongyaoV9.put("rongyaoV9_128G",rongyaoV9_128G);

    Map<String,String> rateMap27 = new HashMap<>();
    rateMap27.put("rate","100");
    rongyao8.put("rate",rateMap27);
    rongyao8.put("rongyao8_32G",rongyao8_32G);

    Map<String,String> rateMap28 = new HashMap<>();
    rateMap28.put("rate","47");
    R9s.put("rate",rateMap28);
    R9s.put("R9s_64G",R9s_64G);

    Map<String,String> rateMap29 = new HashMap<>();
    rateMap29.put("rate","69");
    R11.put("rate",rateMap29);
    R11.put("R11_64G",R11_64G);

    Map<String,String> rateMap30 = new HashMap<>();
    rateMap30.put("rate","86");
    R9.put("rate",rateMap30);
    R9.put("R9_64G",R9_64G);

    Map<String,String> rateMap31 = new HashMap<>();
    rateMap31.put("rate","100");
    R9s_Plus.put("rate",rateMap31);
    R9s_Plus.put("R9s_Plus_64G",R9s_Plus_64G);

    Map<String,String> rateMap32 = new HashMap<>();
    rateMap32.put("rate","46");
    meilan_E2.put("rate",rateMap32);
    meilan_E2.put("meilan_E2_64G",meilan_E2_64G);

    Map<String,String> rateMap33 = new HashMap<>();
    rateMap33.put("rate","60");
    meizuPRO_6s.put("rate",rateMap33);
    meizuPRO_6s.put("meizuPRO_6s_64G",meizuPRO_6s_64G);

    Map<String,String> rateMap331 = new HashMap<>();
    rateMap331.put("rate","100");
    meizuPRO_6_Plus.put("rate",rateMap331);
    meizuPRO_6_Plus.put("meizuPRO_6_Plus_64G",meizuPRO_6_Plus_64G);

    Map<String,String> rateMap34 = new HashMap<>();
    rateMap34.put("rate","64");
    Moto_Z.put("rate",rateMap34);
    Moto_Z.put("Moto_Z_64G",Moto_Z_64G);

    Map<String,String> rateMap35 = new HashMap<>();
    rateMap35.put("rate","100");
    Moto_X_Pro.put("rate",rateMap35);
    Moto_X_Pro.put("Moto_X_Pro_64G",Moto_X_Pro_64G);

    Map<String,String> rateMap36 = new HashMap<>();
    rateMap36.put("rate","69");
    Xperia_XZ.put("rate",rateMap36);
    Xperia_XZ.put("Xperia_XZ_64G",Xperia_XZ_64G);

    Map<String,String> rateMap37 = new HashMap<>();
    rateMap37.put("rate","100");
    Xperia_XZs.put("rate",rateMap37);
    Xperia_XZs.put("Xperia_XZs_64G",Xperia_XZs_64G);

    Map<String,String> rateMap38 = new HashMap<>();
    rateMap38.put("rate","58");
    tianji7.put("rate",rateMap38);
    tianji7.put("tianji7_64G",tianji7_64G);

    Map<String,String> rateMap39 = new HashMap<>();
    rateMap39.put("rate","100");
    tianji7_Max.put("rate",rateMap39);
    tianji7_Max.put("tianji7_Max_64G",tianji7_Max_64G);

  }

  //品牌
  public static Map<String,Map<String,Map<String,String>>> apple = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> xiaomi = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> sumsung = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> vivo = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> huawei = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> oppo = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> meizu = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> moto = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> sony = new LinkedHashMap<>();
  public static Map<String,Map<String,Map<String,String>>> zhongxing = new LinkedHashMap<>();

  public static Map<String,Map<String,Map<String,Map<String,String>>>> brand = new HashMap<>();

  static {
    apple.put("iPhone_7",iPhone_7);
    apple.put("iPhone_7_Plus",iPhone_7_Plus);
    apple.put("iPhone_6S",iPhone_6S);
    apple.put("iPhone_6S_Plus",iPhone_6S_Plus);
    apple.put("iPhone_6",iPhone_6);
    apple.put("iPhone_6_Plus",iPhone_6_Plus);

    xiaomi.put("xiaomi6",xiaomi6);
    xiaomi.put("xiaomiMax_2",xiaomiMax_2);
    xiaomi.put("xiaomi5c",xiaomi5c);
    xiaomi.put("hongmi4X",hongmi4X);
    xiaomi.put("xiaomiNote_2",xiaomiNote_2);
    xiaomi.put("xiaomi5",xiaomi5);

    sumsung.put("GALAXY_S8",GALAXY_S8);
    sumsung.put("GALAXY_S8_Plus",GALAXY_S8_Plus);
    sumsung.put("GALAXY_S7_Edge",GALAXY_S7_Edge);
    sumsung.put("GALAXY_Note_7",GALAXY_Note_7);
    sumsung.put("GALAXY_S7",GALAXY_S7);

    vivo.put("X9",X9);
    vivo.put("X9Plus",X9Plus);
    vivo.put("Xplay6",Xplay6);
    vivo.put("Y67",Y67);
    vivo.put("Xplay5",Xplay5);

    huawei.put("huaweiP10",huaweiP10);
    huawei.put("huaweiMate_9",huaweiMate_9);
    huawei.put("huaweiP10_Plus",huaweiP10_Plus);
    huawei.put("rongyaoV9",rongyaoV9);
    huawei.put("rongyao8",rongyao8);

    oppo.put("R9s",R9s);
    oppo.put("R11",R11);
    oppo.put("R9",R9);
    oppo.put("R9s_Plus",R9s_Plus);

    meizu.put("meilan_E2",meilan_E2);
    meizu.put("meizuPRO_6_Plus",meizuPRO_6_Plus);
    meizu.put("meizuPRO_6s",meizuPRO_6s);

    moto.put("Moto_Z",Moto_Z);
    moto.put("Moto_X_Pro",Moto_X_Pro);

    sony.put("Xperia_XZ",Xperia_XZ);
    sony.put("Xperia_XZs",Xperia_XZs);

    zhongxing.put("tianji7",tianji7);
    zhongxing.put("tianji7_Max",tianji7_Max);

    brand.put("apple",apple);
    brand.put("xiaomi",xiaomi);
    brand.put("sumsung",sumsung);
    brand.put("vivo",vivo);
    brand.put("huawei",huawei);
    brand.put("oppo",oppo);
    brand.put("meizu",meizu);
    brand.put("moto",moto);
    brand.put("sony",sony);
    brand.put("zhongxing",zhongxing);
  }


}