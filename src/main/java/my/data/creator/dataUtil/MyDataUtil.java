package my.data.creator.dataUtil;

import my.data.creator.Client;
import my.data.creator.CsvDataCreator;
import my.data.creator.JsonDataCreator.JsonDataCreator;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * Created by chenyuzhi on 17-7-26.
 */
public class MyDataUtil {
	public static char[] baseChars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	public static char[] baseIntChars = "0123456789".toCharArray();
	public static File outputFile;
	public static DateTime startTime;
	public static DateTime endTime;
	public static int dataSize;
	public static int rowSize;
	public static int strSize;
	public static int intSize;

	public static void init() throws IOException {
		Properties config = getDataConfig();
		initFile(config);
		startTime = DateTime.parse(config.getProperty("starttime"));
		endTime = DateTime.parse(config.getProperty("endtime"));
		dataSize = Integer.parseInt(config.getProperty("datasize","20"));
		rowSize = Integer.parseInt(config.getProperty("rowsize","10"));
		strSize =  Integer.parseInt(config.getProperty("strsize","4"));
		intSize =  Integer.parseInt(config.getProperty("intsize","5"));

	}

	public static void initFile (Properties config) throws IOException {
		outputFile = new File(config.getProperty("outputPath") + config.getProperty("filename"));
		if(!outputFile.exists()){
			outputFile.createNewFile();
		}
	}

	public static Properties getDataConfig() throws IOException {
		Properties props = new Properties();
		InputStream in = new FileInputStream("./dataConfig.properties");
		props.load(in);
		println(props.getProperty("outputPath"));
		if(null != in){
			in.close();
		}
		return props;
	}

	public static void println(Object str){
		System.out.println(str);
	}
}
