package my.data.creator;

import com.sun.org.apache.bcel.internal.generic.NEW;
import my.data.creator.dataUtil.MyDataUtil;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by chenyuzhi on 17-7-21.
 */
public class CsvDataCreator {

	static Random random = new Random(new DateTime().getMillis());
	static DateTime startTime;
	static DateTime endTime;
	static int randomStrSeed = 10;
	static int randomIntSeed = 10;



	public CsvDataCreator(DateTime _startTime, DateTime _endTime) throws IOException {
		startTime = _startTime;
		endTime = _endTime;

	}


	public static List<String> getDataListBydefault(int size, int rowSize){
		List<String> dataList = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			dataList.add(createRandomRowDataByDefault(rowSize));
		}
		return dataList;
	}


	public static String createRandomRowDataByDefault(int rowsize){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < rowsize - 1; i++) {
			if(i == 0){
				buffer.append(getRandomDate(startTime.getMillis(),endTime.getMillis()));
			}else{
				buffer.append(getRandomString());
			}
			buffer.append(",");
		}
		buffer.append(getRandomString());
		return buffer.toString();
	}

	public static List<String> getDataList(int size, int rowSize, int strSize, int intSize){
		List<String> dataList = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			dataList.add(createRandomRowData(rowSize,strSize,intSize));
		}
		return dataList;
	}

	public static String createRandomRowData(int rowSize, int strSize, int intSize){
		if((strSize + intSize + 1) > rowSize ){
			throw new RuntimeException("string的列数与int的列数必须小于总列数");
		}
		//boolean isAllString = false;
		StringBuffer buffer = new StringBuffer();

		Long time = getRandomDate(startTime.getMillis(),endTime.getMillis());
		println(new DateTime(time));

		buffer.append(time);
		buffer.append(",");
		rowSize--;
		if(strSize >0){
			for (int i = 0; i < strSize; i++) {
				buffer.append(getRandomString());
				buffer.append(",");
			}
			rowSize = rowSize - strSize;
		}

		if(intSize > 0){
			for (int i = 0; i < intSize; i++) {
				buffer.append(getRandomInt());
				buffer.append(",");
			}
			rowSize = rowSize - intSize;
		}

		if(rowSize > 0){
			for (int i = 0; i < rowSize; i++) {
				buffer.append(getRandomInt());
				buffer.append(",");
			}
		}
		int index =buffer.lastIndexOf(",");
		if(index == (buffer.length()-1)){
			buffer.deleteCharAt(index);
		}


		return buffer.toString();
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

	public static long getRandomInt(){
		int size = random.nextInt(randomIntSeed) + 2;
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
}
