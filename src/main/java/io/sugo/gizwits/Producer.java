package io.sugo.gizwits;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by root on 16-12-5.
 */
public class Producer implements Runnable {

  private static String[] areas = new String[]{
      "延庆县", "顺义县", "通县", "和平区", "河东区", "河西区", "南开区", "宜宾市", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区", "北辰区", "宁河县", "武清县", "静海县", "宝坻县", "蓟县", "邯郸市大名县", "邯郸市涉县", "邯郸市磁县", "邯郸市肥乡县", "邯郸市永年县", "邯郸市邱县", "邯郸市鸡泽县", "邯郸市广平县", "邯郸市馆陶县", "邯郸市魏县", "邢台市隆尧县", "邢台市任县", "邢台市南和县", "邢台市宁晋县", "沧洲市郊区", "廊坊市市辖区", "廊坊市安次区", "沧州市泊头市", "沧州市任丘市", "沧洲市郊区", "定州市", "涿州市", "安国市", "高碑店市", "易县", "徐水县", "涞源县", "定兴县", "顺平县", "唐县", "望都县", "涞水县", "高阳县", "安新县", "雄县", "容城县", "曲阳县", "阜平县", "博野县", "蠡县", "太原市市辖区", "双城市", "尚志市", "五常市", "巴彦县", "木兰县", "通河县", "延寿县", "兰州市市辖区", "兰州市城关区", "吴忠市青铜峡市", "吴忠市灵武市", "固原地区固原县", "喀什地区泽普县", "喀什地区伽师县", "阿勒泰地区富蕴县", "贵阳市云岩区", "绥阳县", "正安县", "道真仡佬苗族自治县", "务川仡佬苗族自治县", "凤冈县", "湄潭县", "余庆县", "仁怀县", "习水县", "昆明市市辖区", "昆明市五华区", "铜川市郊区", "兴文县", "屏山县", "石柱土家族自治县", "秀山土家族苗族自治县", "黔江土家族苗族自治县", "彭水苗族土家族自治县", "华蓥市", "岳池县", "广安县", "武胜县"
  };

  private static Random rand = new Random();

  private Properties props;
  private BlockingQueue<List<String>> msgQueue;
  private CountDownLatch latch;
  private AtomicInteger produceCnt;
  private CountDownLatch syncLatch;
  private int sPos;
  private int ePos;
  private DateTime startTime;
  private DateTime endTime;
  private MessageDigest md;
  private Map<Integer, String> idMap;
  private AtomicLong produceTime;
  private AtomicInteger produceNum;

  public Producer(Properties props, BlockingQueue<List<String>> msgQueue,
      CountDownLatch latch, AtomicInteger produceCnt,
      CountDownLatch syncLatch, int sPos, int ePos,
      AtomicLong produceTime, AtomicInteger produceNum) {
    this.props = props;
    this.msgQueue = msgQueue;
    this.latch = latch;
    this.produceCnt = produceCnt;
    this.syncLatch = syncLatch;
    this.sPos = sPos;
    this.ePos = ePos + 1;
    String startStr = props.getProperty("startTime", "2015-01-01");
    startTime = new DateTime(startStr, DateTimeZone.UTC).withTimeAtStartOfDay();
    String endStr = props.getProperty("endTime", "2015-03-31T00:02:00.000+08:00");
    endTime = new DateTime(endStr, DateTimeZone.UTC).plusDays(1).withTimeAtStartOfDay().minusMillis(1);
//    endTime = new DateTime("2015-01-01T00:02:00.000+08:00");
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    idMap = new HashMap<>(ePos - sPos);
    this.produceTime = produceTime;
    this.produceNum = produceNum;
  }

  private Producer() {
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    idMap = new HashMap<>(ePos - sPos);
  }

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    int arraySize = 10000;
    try {
      String action;
      List<String> actions = new ArrayList<>(arraySize);
      DateTime dt = startTime;
      while (dt.isBefore(endTime)) {
        for (int pos = sPos; pos < ePos; pos++) {
          action = getAction(pos, dt);
          actions.add(action);
          if (actions.size() == arraySize) {
            msgQueue.put(actions);
            actions = new ArrayList<>(arraySize);
          }
          produceNum.incrementAndGet();
        }
        dt = dt.plusSeconds(20);
        produceTime.set(dt.getMillis());
      }
      if (!actions.isEmpty()) {
        msgQueue.put(actions);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      long end = System.currentTimeMillis();
      long spend = end - start;
      produceCnt.addAndGet(produceNum.get());
      System.out.println(Thread.currentThread().getName() + " produced:" + produceNum.get() + " spend time:" + spend);
      latch.countDown();
      syncLatch.countDown();
    }
  }

  /**
   * 100w deviceId, 3 per minute, one year
   * id, ts, electricity(ele, 1~100), electricCurrent(eleCur, float), voltage(vol,float), area(100)
   *
   * @param pos
   * @param dt
   */
  private String getAction(int pos, DateTime dt) {
    StringBuffer sb = new StringBuffer();
    //    String id = getDeviceId(pos);
    sb.append("id=" + String.format("ID-%07d", pos)).append("&");
    sb.append("ts=" + dt.getMillis()).append("&");
    sb.append("ele=" + rand.nextInt(100)).append("&");
    float tmp = rand.nextFloat() * 10 + rand.nextInt(10);
    sb.append(String.format("eleCur=%.2f", tmp)).append("&");
    tmp = rand.nextFloat() * 10 + 220 + rand.nextInt(10) * (rand.nextBoolean() ? 1 : -1);
    sb.append(String.format("vol=%.2f", tmp)).append("&");
    sb.append("area=" + areas[rand.nextInt(areas.length)]);
    return sb.toString();
  }

  private String getDeviceId(int pos) {
    String id = idMap.get(pos);
    if (id != null) {
      return id;
    }
    StringBuffer sb = new StringBuffer();
    md.reset();
    md.update(String.valueOf(pos).getBytes());
    byte[] digest = md.digest();
    for (byte b : digest) {
      sb.append(String.format("%02x", b & 0xff));
    }
    id = sb.toString();
    idMap.put(pos, id);
    return id;
  }

  public static void main(String[] args) {
    Producer p = new Producer();
    System.out.println(p.getDeviceId(100));
    DateTime dt = new DateTime();
    long s1 = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
      p.getAction(i, dt);
    }
    long s2 = System.currentTimeMillis();
    System.out.println(s2 - s1);
    s1 = System.currentTimeMillis();
    for (int num = 0; num < 1; num++) {
      for (int i = 0; i < 1000000; i++) {
        p.getAction(i, dt);
      }
    }
    s2 = System.currentTimeMillis();
    System.out.println(s2 - s1);
    System.out.println(p.getAction(100, new DateTime()));
    System.out.println(areas.length);
    System.out.println(String.format("%s-%07d", "ID", 100));
  }
}
