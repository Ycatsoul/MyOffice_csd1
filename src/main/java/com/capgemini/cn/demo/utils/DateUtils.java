package com.capgemini.cn.demo.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*   
 * @ClassName: DateUtils  
 * @Description: 时间处理工具类
 * @Author: xia.xinggui
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String CHART_FORMAT = "yyyy-MM-dd HH:mm";
	
    public static final String DEFAULT_ZONE = "GMT+8";
	
	public static final String YYYY = "yyyy";

	public static final String YYYY_MM = "yyyy-MM";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYY_MM_DD_HH_MM_SS_fff = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String HH_MM = "HH:mm";

	private static String[] parsePatterns = { 
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", 
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 
	 * @Title: getNowDate   
	 * @Description: 获取当前时间  
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 获取当前日期, 默认格式为yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getDate() {
		return dateTimeNow(YYYY_MM_DD);
	}

	public static final String getTime() {
		return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
	}

	public static final String dateTimeNow() {
		return dateTimeNow(YYYYMMDDHHMMSS);
	}

	public static final String dateTimeNow(final String format) {
		return parseDateToStr(format, new Date());
	}

	public static final String dateTime(final Date date) {
		return parseDateToStr(YYYY_MM_DD, date);
	}

	public static final String parseDateToStr(final String format, final Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static final Date dateTime(final String format, final String ts) {
		try {
			return new SimpleDateFormat(format).parse(ts);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日期路径 即年/月/日 如2018/08/08
	 */
	public static final String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 日期路径 即年/月/日 如20180808
	 */
	public static final String dateTime() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyyMMdd");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取服务器启动时间
	 */
	public static Date getServerStartDate() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}

	
	/** 
	* @Title：currentMonthFirstDay 
	* @Description：获取当前月第一天
	* @param ：@return 
	* @return ：String 
	* @throws 
	*/
	public static String  currentMonthFirstDay() {
		Calendar   cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = parseDateToStr(YYYY_MM_DD,cal_1.getTime());
        return firstDay;

	}
	
	/** 
	* @Title：currentMonthFirstDay 
	* @Description：获取当前月最后一天
	* @param ：@return 
	* @return ：String 
	* @throws 
	*/
	public static String  currentMonthLastDay() {
		 Calendar cale = Calendar.getInstance();   
	     cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
	     String lastDay = parseDateToStr(YYYY_MM_DD,cale.getTime());
         return lastDay;
	}
	
	/** 
	* @Title：getRemainSecondsOneDay 
	* @Description：获取一天最后剩余的时间（秒）
	* @param ：@param currentDate
	* @param ：@return 
	* @return ：Integer 
	* @throws 
	*/
	public static Integer getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight=Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(midnight.DAY_OF_MONTH,1);
        midnight.set(midnight.HOUR_OF_DAY,0);
        midnight.set(midnight.MINUTE,0);
        midnight.set(midnight.SECOND,0);
        midnight.set(midnight.MILLISECOND,0);
        Integer seconds=(int)((midnight.getTime().getTime()-currentDate.getTime())/1000);
        return seconds;
    }

	public static String addDay(Date beforeDate, int count){
		Calendar cal = Calendar.getInstance();
        cal.setTime(beforeDate);
        cal.add(Calendar.DAY_OF_MONTH, count);
		return parseDateToStr(YYYY_MM_DD, cal.getTime());
	}

}
