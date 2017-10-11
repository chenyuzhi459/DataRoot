package my.data.creator.dataUtil;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by chenyuzhi on 17-7-27.
 */
public class MyKafkaConsumer {
	public static KafkaConsumer consumer;
	public static String topic;

	public static void main(String[] args) throws IOException {
		//println("++" + StringDeserializer.class.getCanonicalName());
		init();

		consumer.subscribe(Arrays.asList(topic));
		while (true){
			ConsumerRecords<String, String> records = consumer.poll(1000);
			for (ConsumerRecord<String, String> record: records) {
				println("topic: "+record.topic() +
						" key: " + record.key() +
						" value: " + record.value() +
						" partition1: "+ record.partition());
			}
		}
	}

	public static void init() throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("kafka.properties"));
		consumer = new KafkaConsumer(props);
		topic = props.getProperty("topic");
	}

	public static void println(String str){
		System.out.println(str);
	}
}
