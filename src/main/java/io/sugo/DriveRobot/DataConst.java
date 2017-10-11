package io.sugo.DriveRobot;


//参考数据
public class DataConst {

  public static String[] region = ("越秀区,天河区,白云区,荔湾区,萝岗区,黄埔区,海珠区,番禺区,花都区,南沙区,增城市,从化市").split(",");
  public static int[] regionRate = {10,30,38,48,53,61,73,83,86,93,95,100};
  public static String[][] street = {"洪桥,北京,六榕,流花,光塔,人民,东山,农林,大东,大塘,珠光,白云,建设,华乐,梅花村,黄花岗,矿泉,登峰".split(","),
    "五山,员村,车陂,沙河,石牌,兴华,沙东,林和,棠下,猎德,冼村,天园,天河南,元岗,黄村,龙洞,长兴,凤凰,前进,珠吉,新塘,登峰".split(","),
    "三元里街,松洲街,景泰街,黄石街,同德街,棠景街,新市街,同和街,京溪街,永平街,均禾街,嘉禾街,石井街,金沙街和江高镇,人和镇,太和镇,钟落潭镇".split(","),
    "金花街,西村街,南源街,逢源街,多宝街,龙津街,昌华街,岭南街,华林街,沙面街,站前街,彩虹街,桥中街,花地街,茶滘街,东漖街,中南街,海龙街,冲口街,东沙街,石围塘街,白鹤洞街".split(","),
    "夏港街,萝岗街,东区街,联和街,永和街,九龙镇".split(","),
    "官洲街,黄埔街,红山街,鱼珠街,大沙街,文冲街,南岗街,荔联街,穗东街,长洲街".split(","),
    "赤岗,新港,滨江,素社,海幢,凤阳,龙凤,沙园,瑞宝,江海,南华西,南石头,江南中,昌岗,南洲,琶洲,官洲,华洲".split(","),
    "市桥街道,桥南街道,东环街道,沙头街道,洛浦街道,大石街道,小谷围街道,钟村街道,石壁街道,大龙街道,南村镇,沙湾镇,石碁镇,石楼镇,新造镇,化龙镇".split(","),
    "新华,新雅,秀全,花城".split(","),
    "南沙街,龙穴街,珠江街,万顷沙镇,黄阁镇,横沥镇,榄核镇,大岗镇,东涌镇".split(","),
    "荔城街,增江街,朱村街,永宁街道,中新镇,石滩镇,新塘镇,小楼镇,派潭镇,正果镇,仙村镇".split(","),
    "太平,温泉,良口,吕田,鳌头,街口,城郊,江埔".split(",")
  };

  public static String[][] reg_chan = {
    {"KFC氪金","2"},
    {"广州午餐活动","5"},
    {"充值新注册优惠渠道","15"},
    {"蓝色光标","20"},
    {"麦乐迪","23"},
    {"去哪网","31"},
    {"商旅客户","34"},
    {"注册送200元优惠券","44"},
    {"未知","74"},
    {"线下KFC","79"},
    {"销售业绩渠道","99"},
    {"早餐兼职","100"},
  };

  public static String[][] payment_type = {
    {"账号余额支付","40"},
    {"支付宝支付","60"},
    {"微信支付","75"},
    {"银联支付","100"}
  };

  public static int[][] pre_amount = {
    {0,50},{5,70},{10,80},{15,86},{20,91},{25,94},{30,97},{35,99},{50,100}
  };

  public static String[][] coupon_name = {
          {"充值赠送优惠券","充值赠送优惠","20","cigpwg"},
          {"数果优惠券","数果优惠","47","iojngd"},
          {"新用户注册赠券","新用户注册","72","wrnbny"},
          {"注册充200元活动优惠券","注册充200元活动","77","sdfpzg"},
          {"数果代金券","数果优惠","100","iojngd"}
  };

  public static String[][] order_type = {
          {"接机", "2" }, {"接站", "5" }, {"送机", "10" }, {"送站", "17" }, {"随叫随到", "79" }, {"预约用车", "100" }
  };

  public static String[][] car_model = {
          {"百家乐", "10" }, {"经济型", "50" }, {"商务型", "80" }, {"舒适型", "100" }
  };

  public static String[] phone_pre = "134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,178,130,131,132,155,156,185,186,176,180,181,189,133,153,177".split(",");

  public static String[][] driver_message = {
          {"18982458964","邹江"},{"13929957695","柏幡垤"},{"13155833509","路樾焘"},{"13437395548","夏侯总"},{"18048522673","昌爽"},{"18695586618","费罨辈"},{"15087195095","施珥"},{"13680161826","欧阳睾绋"},
          {"18453230216","凤斓"},{"15669116112","常咎"},{"15392453708","施铤三"},{"18548704036","吕伟避"},{"13515909539","祝丘稍"},{"18980331007","罗辆幼"},{"15239491306","贾猁"},{"15226690911","毛瞀"},
          {"15113641847","罗樟砧"},{"18782032681","骆潜"},{"13228877029","蒋豸"},{"15710178775","何畜"},{"15717545784","禹男"},{"17861225617","梅寇"},{"18358874717","方锞蘑"},{"15365115933","袁暂"},
          {"18991227008","郭螋"},{"18441082851","元膳"},{"18007397318","夏侯溟"},{"17898850504","许骤躯"},{"13632596564","季硝维"},{"13564657712","皮秃醌"},{"13627044378","安耪淫"},{"18263068352","盛妨贾"},
          {"13124344515","伏仓"},{"13079640805","骆钷"},{"18792112408","葛荀"},{"18280343676","卞缀啄"},{"15166932810","贺鳊"},{"13337884638","夏侮蒉"},{"15377950664","苗宥鼓"},{"15179039074","费姬芍"},
          {"18789392820","窦馆耶"},{"15682882458","柳脑"},{"18331410631","童郇阂"},{"18015065616","许敦肴"},{"13113680695","水钶"},{"13216554650","赵酒的"},{"15117780650","朱刮怜"},{"15110814815","伏鲧"},
          {"18786680940","邵聍"},{"18895421867","徐荑"},{"13183945886","颜傺"},{"17847114124","时候"},{"13185481617","汤隧"},{"13999036806","范傈暂"},{"17626390184","夏鹅瓠"},{"18555448845","范盼"},
          {"13739299630","毛榕"},{"13929691330","马娲姨"},{"15601026827","卞科"},{"13492012813","林逍"},{"18670202913","施纂虹"},{"13986104814","薛鹎施"},{"18742451301","臧虞�"},{"18398686504","朱区瘼"},
          {"18369217911","皮眭腿"},{"13303924253","陈襁"},{"13199864259","乐诗"},{"15390870812","孔吲封"},{"13762949747","贾荷"},{"17619110644","范钟"},{"13054014989","史践蟆"},{"18694304609","云氆哏"},
          {"18181439724","郑绪卺"},{"15686196812","秦凋笠"},{"15559924356","史丙庆"},{"18986996818","郎颌"},{"18640789452","钱坠余"},{"18986648013","朱攫"},{"15720486974","上官怛亥"},{"13266952873","喻忡"},
          {"15253238410","姜仨酽"},{"15155034386","季谋帝"},{"18944996549","余耍雠"},{"15133957869","杜质"},{"18104697045","周堠"},{"15573265267","贺萨"},{"18321924664","窦间绺"},{"18200686582","水噢缉"},
          {"18900429609","茅嘿"},{"18944079042","成捱邦"},{"13335722231","葛霈"},{"18083757416","和俩"},{"13653009628","喻满"},{"15973528344","任蚁"},{"13963572329","阮軎"},{"13291347807","周烈"},
          {"17822028208","卞庑"},{"15747933635","花妗谌"},{"17892829705","夏穴砟"},{"15910185592","贾艘睁"},{"15756063949","柏疱"},{"18279562110","王陪锭"},{"13433160456","颜缨"},{"18569450176","褚嫱"},
          {"15139772896","周苁"},{"18672135495","邹筑"},{"15822319074","郭彖痉"},{"18377665954","明熳"},{"15233176546","钟滔"},{"17788666879","和画钫"},{"18068586127","阮基"},{"13770982110","林呱翠"},
          {"18933544631","霍岚"},{"13307344033","赵让"},{"13967667178","明恻镣"},{"13931212208","明厘"},{"13007603717","姚羔"},{"15653120541","杜搔郁"},{"15161118649","上官斋脬"},{"17842363201","鲍疖视"},
          {"13587910502","虞蚕十"},{"15579158741","倪挡梗"},{"18523495348","贝习"},{"18605802964","邱旦仉"},{"15050984436","冯仆榆"},{"18935605644","路谎泮"},{"17826234015","毕荆蜱"},{"17746108545","童蕨"},
          {"17885887668","赵淡裼"},{"18768827044","费缰"},{"18191924193","陶绁枇"},{"17830773900","金纠颀"},{"18841651838","周剿鬯"},{"18953447728","霍釜"},{"18668146995","计封楸"},{"18288131255","戚锷"},
          {"13251131983","强垧富"},{"18325225791","滕缦"},{"17649863006","鲍挤"},{"13504139870","魏矧"},{"15930081214","夏那岖"},{"15865835255","鲁弪鐾"},{"13428666749","岑道"},{"17887017439","董仓括"},
          {"15637719567","金赵蟥"},{"17670457173","鲍嵊"},{"18847203242","滕梏"},{"13219989874","平插娜"},{"13599596110","魏虎笛"},{"13675173819","张摧"},{"13108012887","祝庳"},{"15700369943","沈猬"},
          {"18495811299","贺殳"},{"15733636837","韦蹶枨"},{"15611376201","杜百"},{"18075278995","杨掳"},{"18706957686","贝捣"},{"15286739661","陈垄"},{"13495713497","欧阳雄折"},{"15938505658","尤狃镱"},
          {"15133903397","杜蓄谓"},{"15707106366","苏泔"},{"18440643030","沈侔"},{"15673859205","屈局"},{"15111691299","苗潮盯"},{"15172182238","费禽"},{"13032498032","屈缤遐"},{"15601269295","倪肴"},
          {"15583715185","邵裂淀"},{"18363714793","韩盎咽"},{"18710569726","茅蔹硒"},{"13078972234","孙喈纪"},{"13527105723","吴磴"},{"13477854207","凌撺"},{"13791804196","赵蓓"},{"15502943698","凌貂蝓"},
          {"15559041546","卞轵"},{"15628827023","李璨"},{"18915478597","常鳅倜"},{"18299037833","庞鲔按"},{"13475176652","梁裎责"},{"17795686140","罗近舱"},{"18533578128","秦蓠哆"},{"18015807793","韦指"},
          {"13233330664","苗缚"},{"18023625245","吕蹿"},{"13122796408","尹逾"},{"18928373769","卫重"},{"13751216165","骆瘸"},{"13805216887","华窃骖"},{"13757483524","颜拯铱"},{"13854571174","元呕莰"},
          {"15795461952","冯蚋"},{"18023612477","鲍桢惧"},{"15577122139","夏侯陬瓷"},{"18679294154","司马新"},{"13163019357","闵锖甓"},{"18658483030","倪丹杜"},{"15650523881","邱否"},{"17771267673","袁哧滥"}
  };

  public static String[][]  order_state = {
          {"已付款","10"},
          {"已过期","15"},
          {"已结束","50"},
          {"已开始","70"},
          {"已评价","90"},
          {"已取消","100"}
  };

  public static void main(String[] args) {
    System.out.println(region.length );
    System.out.println(street.length);
    System.out.println(regionRate.length);
  }

}