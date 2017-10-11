package my.data.creator.JsonDataCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.data.creator.dataUtil.MyDataUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Created by chenyuzhi on 17-7-21.
 */
public class JsonDataCreator implements Closeable{

	static Random random = new Random(new DateTime().getMillis());
	static long startTimeMillis;
	static long endTimeMillis;
	static int randomStrSeed = 10;
	static int randomLongSeed = 5;
	static JsonEntity entity = new JsonEntity();
	static Map rowMap = new HashMap<String,Object>();
	static ObjectMapper jsonMapper = new ObjectMapper();
	String topic;
	KafkaProducer producer;

	public JsonDataCreator(DateTime _startTime, DateTime _endTime) throws IOException {
		startTimeMillis = _startTime.getMillis();
		endTimeMillis = _endTime.getMillis();

		//初始化kafka配置
		Properties props = new Properties();
		props.load(new FileInputStream("kafka.properties"));
		topic = props.getProperty("topic");
		producer = new KafkaProducer<>(props);

	}

	public void send(String msg){
		println("msg: " +msg );
		producer.send(new ProducerRecord(topic,msg));
	}


	public static String createRandomRowData(){
		try {
			entity.setTime(getRandomDate(startTimeMillis,endTimeMillis));
			entity.setStrValue(getRandomString());
			entity.setLongValue(getRandomLong());
			rowMap.put(entity.getTimeColumnName(),entity.getTime());
			rowMap.put(entity.getStrValueColumnName(),entity.getStrValue());
			rowMap.put(entity.getLongValuelColumnName(),entity.getLongValue());
			return jsonMapper.writeValueAsString(rowMap);
		} catch (JsonProcessingException e) {
			println("jsonMapper parser Exception: 对象转换为json时出错");
			e.printStackTrace();
		}
		return null;
	}



	public static String getRandomString(){
		int size = random.nextInt(randomStrSeed) + 3;
		StringBuffer buffer = new StringBuffer();
		int offset = -1;
		for (int i = 0; i < size; i++) {
			offset = random.nextInt(MyDataUtil.baseChars.length);
			buffer.append(MyDataUtil.baseChars[offset]);
		}
		return buffer.toString();
	}

	public static long getRandomLong(){
		int size = random.nextInt(randomLongSeed) + 2;
		StringBuffer buffer = new StringBuffer();
		int offset = -1;
		for (int i = 0; i < size; i++) {
			offset = random.nextInt(MyDataUtil.baseIntChars.length);
			buffer.append(MyDataUtil.baseIntChars[offset]);
		}
		return Long.parseLong(buffer.toString());
	}

	public static Long getRandomDate(Long start, Long end){

		return start +  (long)(Math.random() * (end - start));
	}


	public static void println(Object str){
		System.out.println(str);
	}

	@Override
	public void close() throws IOException {
		producer.close();
	}
}
