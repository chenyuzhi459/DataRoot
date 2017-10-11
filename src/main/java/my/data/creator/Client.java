package my.data.creator;

import my.data.creator.JsonDataCreator.JsonDataCreator;
import my.data.creator.dataUtil.MyDataUtil;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by chenyuzhi on 17-7-21.
 */
public class Client {

	static File outputFile;
	static DateTime startTime;
	static DateTime endTime;
	static int dataSize;
	static int rowSize;
	static int strSize;
	static int intSize;
	static CsvDataCreator creator;


	public static void main(String[] args) throws IOException {
		init();
		FileWriter writer = new FileWriter(outputFile);
		println("rowSize:" + rowSize);
		println("strSize:" + strSize);
		println("intSize:" + intSize);

		List<String> dataList = creator.getDataList(dataSize,rowSize,strSize,intSize);

		for (Iterator<String> it = dataList.iterator(); it.hasNext();) {
			writer.write(it.next());
			if(it.hasNext()){
				writer.write("\n");
			}

		}
		writer.flush();
		writer.close();

	}

	public static void init() throws IOException {
		MyDataUtil.init();
		outputFile = MyDataUtil.outputFile;
		startTime = MyDataUtil.startTime;
		endTime = MyDataUtil.endTime;
		dataSize = MyDataUtil.dataSize;
		rowSize = MyDataUtil.rowSize;
		strSize = MyDataUtil.strSize;
		intSize = MyDataUtil.intSize;
		println("starTime:" + startTime);
		println("endTime:" + endTime);
		creator = new CsvDataCreator(startTime,endTime);

	}



	public static void println(Object str){
		System.out.println(str);
	}
}

