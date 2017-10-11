package my.data.creator.multiSplitterCsvData;

import my.data.creator.CsvDataCreator;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenyuzhi on 17-7-21.
 */
public class MultiSplitterClient {

	static File outputFile;
	static DateTime startTime;
	static DateTime endTime;
	static int dataSize;
	static int rowSize;
	static int strSize;
	static int intSize;
	static MultiSplitterCsvDataCreator creator;


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
		Properties config = getDataConfig();
		initFile(config);
		creator = new MultiSplitterCsvDataCreator(config);
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
		InputStream in = MultiSplitterClient.class.getClassLoader().getResourceAsStream("dataConfig.properties");
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

