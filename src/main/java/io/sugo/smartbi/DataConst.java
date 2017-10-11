package io.sugo.smartbi;

public class DataConst {
  public static String[] cities = new String[]{"Albany", "Altadena", "Anacortes", "Arcadia", "Ballard", "Beaverton",
      "Bellflower", "Bellingham", "Berkeley", "Beverly Hills", "Bremerton", "Burbank", "Burien", "Burlingame", "Chula Vista",
      "Colma", "Concord", "Coronado", "Corvallis", "Daly City", "Downey", "Edmonds", "El Cajon", "Everett", "Fremont", "Glendale",
      "Grossmont", "Imperial Beach", "Issaquah", "Kirkland", "La Jolla", "La Mesa", "Lake Oswego", "Lakewood", "Lebanon",
      "Lemon Grove", "Lincoln Acres", "Long Beach", "Los Angeles", "Lynnwood", "Marysville", "Mill Valley", "Milwaukie",
      "National City", "Newport Beach", "Novato", "Oakland", "Olympia", "Oregon City", "Palo Alto", "Pomona", "Port Orchard",
      "Portland", "Puyallup", "Redmond", "Redwood City", "Renton", "Richmond", "Salem", "San Carlos", "San Diego", "San Francisco",
      "San Gabriel", "San Jose", "Santa Cruz", "Santa Monica", "Seattle", "Sedro Woolley", "Spokane", "Spring Valley", "Tacoma",
      "Torrance", "W. Linn", "Walla Walla", "West Covina", "Woodburn", "Woodland Hills", "Yakima"
  };

  public static String country = "USA";

  public static String[] sexs = new String[]{"先生", "女生"};
  public static String[] fullname = new String[]{"艾", "安", "敖", "巴", "白", "柏", "班", "包", "鲍", "暴", "贝", "贲", "毕", "边", "卞", "别", "丙", "邴", "卜", "步", "蔡", "苍", "曹", "岑", "查", "柴", "昌", "长", "常", "晁", "巢", "车", "辰", "陈", "成", "程", "池", "充", "仇", "丑", "储", "褚", "淳", "从", "崔", "逮", "戴", "单", "澹", "党", "邓", "狄", "刁", "丁", "东", "董", "都", "钭", "窦", "堵", "杜", "段", "鄂", "樊", "范", "方", "房", "费", "丰", "封", "酆", "冯", "凤", "伏", "扶", "符", "傅", "富", "干", "甘", "高", "郜", "戈", "葛", "庚", "耿", "弓", "公", "宫", "龚", "巩", "贡", "勾", "古", "谷", "顾", "关", "管", "广", "桂", "郭", "国", "亥", "韩", "杭", "郝", "何", "和", "盍", "贺", "赫", "衡", "弘", "红", "洪", "侯", "后", "胡", "扈", "花", "华", "滑", "怀", "桓", "宦", "皇", "黄", "惠", "霍", "姬", "嵇", "吉", "汲", "籍", "己", "计", "纪", "季", "蓟", "暨", "冀", "家", "甲", "贾", "简", "江", "姜", "蒋", "焦", "解", "金", "靳", "荆", "井", "景", "居", "鞠", "瞿", "阚", "康", "柯", "空", "孔", "寇", "蒯", "匡", "夔", "赖", "蓝", "郎", "劳", "乐", "雷", "冷", "黎", "李", "历", "利", "郦", "连", "廉", "梁", "廖", "林", "蔺", "凌", "令", "刘", "柳", "龙", "隆", "娄", "卢", "鲁", "陆", "禄", "路", "吕", "栾", "罗", "骆", "麻", "马", "满", "毛", "茅", "卯", "梅", "蒙", "孟", "糜", "米", "宓", "苗", "乜", "闵", "明", "缪", "莫", "牧", "慕", "穆", "那", "能", "倪", "聂", "宁", "牛", "钮", "农", "女", "欧", "潘", "庞", "逄", "裴", "彭", "蓬", "皮", "平", "蒲", "濮", "浦", "溥", "戚", "祁", "齐", "钱", "强", "乔", "秦", "邱", "秋", "裘", "曲", "屈", "璩", "全", "权", "却", "阙", "冉", "饶", "壬", "任", "戎", "荣", "容", "融", "茹", "阮", "芮", "桑", "沙", "山", "上", "尚", "韶", "邵", "厍", "申", "沈", "慎", "盛", "师", "施", "石", "时", "史", "寿", "殳", "舒", "束", "双", "水", "司", "巳", "松", "宋", "苏", "宿", "孙", "索", "邰", "太", "谈", "谭", "汤", "唐", "陶", "滕", "田", "通", "童", "屠", "万", "汪", "王", "危", "韦", "隗", "卫", "未", "尉", "蔚", "魏", "温", "文", "闻", "翁", "沃", "乌", "邬", "巫", "毋", "吴", "午", "伍", "武", "戊", "郗", "奚", "习", "席", "夏", "先", "咸", "相", "向", "项", "萧", "谢", "辛", "莘", "邢", "幸", "熊", "戌", "胥", "须", "徐", "许", "轩", "宣", "薛", "荀", "严", "阎", "颜", "晏", "燕", "羊", "阳", "杨", "仰", "养", "姚", "叶", "伊", "乙", "易", "羿", "益", "殷", "尹", "印", "应", "雍", "尤", "游", "酉", "于", "余", "鱼", "俞", "虞", "宇", "禹", "庾", "郁", "喻", "元", "袁", "越", "云", "宰", "昝", "臧", "曾", "翟", "詹", "湛", "张", "章", "赵", "甄", "郑", "支", "终", "钟", "仲", "周", "朱", "诸", "竺", "祝", "庄", "卓", "子", "訾", "宗", "邹", "祖", "左"};

  public static String[] warehouse_country = new String[]{"Canada", "Mexico", "USA"};

  public static String[] warehouse_state_province = new String[]{"BC", "CA", "DF", "Guerrero", "Jalisco", "OR", "Veracruz", "WA", "Yucatan", "Zacatecas"};
  public static String[] warehouse_city = new String[]{"Acapulco", "Bellingham", "Beverly Hills", "Bremerton", "Camacho", "Guadalajara", "Hidalgo", "Los Angeles", "Marida", "Mexico City", "Orizaba", "Portland", "Salem", "San Andres", "San Diego", "San Francisco", "Seattle", "Spokane", "Tacoma", "Vancouver", "Victoria", "Walla Walla", "Yakima"};
  public static String[] product_name = new String[]{"型号1", "型号2", "型号3", "型号4", "型号5", "型号6"};
  public static String[] product_family = new String[]{"白色家电", "黑色家电", "小家电"};
  public static String[] product_department = new String[]{"Alcoholic Beverages", "Baking Goods", "Beverages", "Canned Products", "Dairy", "Deli", "Household", "Produce", "Seafood", "Starchy Foods"};
  public static String[] product_category = new String[]{"Baking Goods", "Beer and Wine", "Carbonated Beverages", "Cleaning Supplies", "Dairy", "Fruit", "Meat", "Paper Products", "Pure Juice Beverages", "Seafood", "Specialty", "Starchy Foods"};
  public static String[] product_subcategory = new String[]{"冰柜", "冰箱", "电吹风", "电饭煲", "电热水器", "电熨斗", "空调", "摄像机", "微波炉", "洗衣机", "消毒柜", "音响", "影碟机"};
  public static String[] the_day = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
  public static String[] state_province = new String[]{"CA", "OR", "WA"};


  //  the_year	"1997年","1998年"
  //  quarter	"1997年1季度","1997年2季度","1997年3季度","1997年4季度","1998年1季度","1998年2季度","1998年3季度","1998年4季度"
  //  the_month	"1997年10月","1997年11月","1997年12月","1997年1月","1997年2月","1997年3月","1997年4月","1997年5月","1997年6月","1997年7月","1997年8月","1997年9月","1998年10月","1998年11月","1998年1月","1998年2月","1998年3月","1998年4月","1998年5月","1998年6月","1998年7月","1998年8月","1998年9月"
  //  the_day
  //  the_date
  //  country	"USA"
  //  state_province
  //  city	"Albany","Altadena","Anacortes","Arcadia","Ballard","Beaverton","Bellflower","Bellingham","Berkeley","Beverly Hills","Bremerton","Burbank","Burien","Burlingame","Chula Vista","Colma","Concord","Coronado","Corvallis","Daly City","Downey","Edmonds","El Cajon","Everett","Fremont","Glendale","Grossmont","Imperial Beach","Issaquah","Kirkland","La Jolla","La Mesa","Lake Oswego","Lakewood","Lebanon","Lemon Grove","Lincoln Acres","Long Beach","Los Angeles","Lynnwood","Marysville","Mill Valley","Milwaukie","National City","Newport Beach","Novato","Oakland","Olympia","Oregon City","Palo Alto","Pomona","Port Orchard","Portland","Puyallup","Redmond","Redwood City","Renton","Richmond","Salem","San Carlos","San Diego","San Francisco","San Gabriel","San Jose","Santa Cruz","Santa Monica","Seattle","Sedro Woolley","Spokane","Spring Valley","Tacoma","Torrance","W. Linn","Walla Walla","West Covina","Woodburn","Woodland Hills","Yakima"
  //  fullname	"艾","安","敖","巴","白","柏","班","包","鲍","暴","贝","贲","毕","边","卞","别","丙","邴","卜","步","蔡","苍","曹","岑","查","柴","昌","长","常","晁","巢","车","辰","陈","成","程","池","充","仇","丑","储","褚","淳","从","崔","逮","戴","单","澹","党","邓","狄","刁","丁","东","董","都","钭","窦","堵","杜","段","鄂","樊","范","方","房","费","丰","封","酆","冯","凤","伏","扶","符","傅","富","干","甘","高","郜","戈","葛","庚","耿","弓","公","宫","龚","巩","贡","勾","古","谷","顾","关","管","广","桂","郭","国","亥","韩","杭","郝","何","和","盍","贺","赫","衡","弘","红","洪","侯","后","胡","扈","花","华","滑","怀","桓","宦","皇","黄","惠","霍","姬","嵇","吉","汲","籍","己","计","纪","季","蓟","暨","冀","家","甲","贾","简","江","姜","蒋","焦","解","金","靳","荆","井","景","居","鞠","瞿","阚","康","柯","空","孔","寇","蒯","匡","夔","赖","蓝","郎","劳","乐","雷","冷","黎","李","历","利","郦","连","廉","梁","廖","林","蔺","凌","令","刘","柳","龙","隆","娄","卢","鲁","陆","禄","路","吕","栾","罗","骆","麻","马","满","毛","茅","卯","梅","蒙","孟","糜","米","宓","苗","乜","闵","明","缪","莫","牧","慕","穆","那","能","倪","聂","宁","牛","钮","农","女","欧","潘","庞","逄","裴","彭","蓬","皮","平","蒲","濮","浦","溥","戚","祁","齐","钱","强","乔","秦","邱","秋","裘","曲","屈","璩","全","权","却","阙","冉","饶","壬","任","戎","荣","容","融","茹","阮","芮","桑","沙","山","上","尚","韶","邵","厍","申","沈","慎","盛","师","施","石","时","史","寿","殳","舒","束","双","水","司","巳","松","宋","苏","宿","孙","索","邰","太","谈","谭","汤","唐","陶","滕","田","通","童","屠","万","汪","王","危","韦","隗","卫","未","尉","蔚","魏","温","文","闻","翁","沃","乌","邬","巫","毋","吴","午","伍","武","戊","郗","奚","习","席","夏","先","咸","相","向","项","萧","谢","辛","莘","邢","幸","熊","戌","胥","须","徐","许","轩","宣","薛","荀","严","阎","颜","晏","燕","羊","阳","杨","仰","养","姚","叶","伊","乙","易","羿","益","殷","尹","印","应","雍","尤","游","酉","于","余","鱼","俞","虞","宇","禹","庾","郁","喻","元","袁","越","云","宰","昝","臧","曾","翟","詹","湛","张","章","赵","甄","郑","支","终","钟","仲","周","朱","诸","竺","祝","庄","卓","子","訾","宗","邹","祖","左"


}
