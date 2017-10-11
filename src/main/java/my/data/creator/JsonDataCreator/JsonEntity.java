package my.data.creator.JsonDataCreator;

import org.joda.time.DateTime;

/**
 * Created by chenyuzhi on 17-7-26.
 */
public class JsonEntity {
	private long time;
	private String strValue;
	private long longValue;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		System.out.println("time_stamp: " + new DateTime(time));
		this.time = time;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public String getStrValueColumnName(){
		return "s|strValue";
	}

	public String getLongValuelColumnName(){
		return "l|longValue";
	}

	public String getTimeColumnName(){
		return "d|time";
	}
}
