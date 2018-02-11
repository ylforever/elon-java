package com.elon.date.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * 日期处理公共类定义。
 * 
 * @author elon
 * @version 2018-02-11
 */
public class DateUtil {

	public DateUtil() {
	}
	
	/**
	 * 定义UTC日期转换器。SimpleDateFormat不是线程安全的。
	 */
	private static ThreadLocal<SimpleDateFormat> utcDateFormatter = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			return format;
		}
	};
	
	/**
	 * 定义当前系统时间转换器。使用的时区为当前系统所在时区。
	 */
	private static ThreadLocal<SimpleDateFormat> localDateFormatter = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format;
		}
	};
	
	/**
	 * 将UTC日期转换为字符串形式。方便日志文件记录。
	 * @param date 日期
	 * @return yyyy-MM-dd hh:mm:ss格式的字符串
	 */
	public static String toUTCDateString(Date date) {
		return utcDateFormatter.get().format(date);
	}
	
	/**
	 * 将UTC时间戳转换为字符串格式。
	 * 
	 * @param timestamp 时间戳
	 * @return yyyy-MM-dd hh:mm:ss格式的字符串
	 */
	public static String timeStamp2UTCDateStr(long timestamp) {
		return toUTCDateString(new Date(timestamp));
	}
	
	/**
	 * 获取当前系统时间的utc格式
	 * 
	 * @return yyyy-MM-dd hh:mm:ss格式的utc时间字符串
	 */
	public static String getCurrentUTC() {
		
		// Date本身是没有时区概念的.转换为字符串的过程中涉及时区信息。
		return toUTCDateString(new Date());
	}
	
	/**
	 * 将日期转换为本地时间字符串。
	 * @param date 日期对象
	 * @return 字符串
	 */
	public static String toLocalDateStr(Date date) {
		return localDateFormatter.get().format(date);
	}
}




