package my.data.creator.JsonDataCreator;

import my.data.creator.CsvDataCreator;
import my.data.creator.dataUtil.MyDataUtil;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenyuzhi on 17-7-26.
 */
public class JsonDataClient {
	static File outputFile;
	static DateTime startTime;
	static DateTime endTime;
	static int dataSize;



	static JsonDataCreator creator;


	public static void main(String[] args) throws IOException, InterruptedException {
		//TODO 原来的
		/*init();
		long count = 0;
		try {
			for (int i = 0; i < dataSize; i++) {
				creator.send(creator.createRandomRowData());
				++count;
				println("已发送：" + count);
			}

		}finally {
			creator.close();
		}*/

		init();
		long count = 0;
		try {
			for (int i = 0; ; ) {
				creator.send(creator.createRandomRowData());
				++count;
				println("已发送：" + count);
				if(count % 10 == 0 ){
					count = 0;
					Thread.sleep(10000);
				}

			}

		}finally {
			creator.close();
		}

	}

	public static void init() throws IOException {
		MyDataUtil.init();
		outputFile = MyDataUtil.outputFile;
		startTime = MyDataUtil.startTime;
		endTime = MyDataUtil.endTime;
		dataSize = MyDataUtil.dataSize;
		creator = new JsonDataCreator(startTime,endTime);

	}

	public static void println(Object str){
		System.out.println(str);
	}

}
