package com.sa.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author wangzhichao
 * @date 2017年4月14日 下午3:49:03
 *
 */
public class DateUtil {
	
	/** 锁对象 */
    private static final Object lockObj = new Object();

    /**短格式**/
    public static final String DATE_PARTERN = "yyyy-MM-dd";
    
    /**长格式**/
    public static final String DATETIME_PARTEN = "yyyy-MM-dd HH:mm:ss";
    
    /**长格式**/
    public static final String SHORT_PARTEN = "yyyy-MM-dd HH:mm";
    
    /**时间**/
    public static final String TIME_PARTEN = "HH:mm";
    
    
    
    
    /**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static final String DATE_FORMAT_01 = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_02 = "yyyy-MM-dd";

	/**
	 * yyyyMMdd
	 */
	public static final String DATE_FORMAT_03 = "yyyyMMdd";
	
	/**
	 * yyyy/MM/dd
	 */
	public static final String DATE_FORMAT_04 = "yyyy/MM/dd";
	
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_05 = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_06 = "yyyy-MM-dd HH:mm:ss";
	
	
	
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String DATE_FORMAT_07 = "yyyyMMddHHmmssSSS";
	
	/**
	 * HH:mm:ss
	 */
	public static final String DATE_FORMAT_08 = "HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DATE_FORMAT_09 = "yyyy-MM-dd HH:mm";
    
    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
	
    /**
     * 计算两个日期相隔天天数
     * 
     * @param smdate 开始日期
     * @param bdate 结束日期
     * @return
     * @throws ParseException
     * 
     *  @author jerry.Yue
     * 	@date 2017年11月23日 下午3:49:03
     */
    public static int daysBetweenByDate(String smdate,String bdate) { 
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");  
        Calendar cal = Calendar.getInstance();    
        long time1=0;
        long time2=0;
        try {
			cal.setTime(sdf.parse(smdate));
			time1 = cal.getTimeInMillis();                 
			cal.setTime(sdf.parse(bdate));    
			time2 = cal.getTimeInMillis();         
		} catch (ParseException e) {
			e.printStackTrace();
		}    
       long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    /**
     * 计算两个日期相隔天天数
     * 
     * @param smdate 开始日期
     * @param bdate 结束日期
     * @return
     * 
     *  @author jerry.Yue
     * 	@date 2017年11月23日 下午3:49:03
     */
    public static int daysBetweenByNumber(Long smdate,Long bdate) {  
	       Long between_days=(bdate-smdate)/(3600*24);  
	       return Integer.parseInt(String.valueOf(between_days));     
	}  
	  
    // 时间戳转化为Sting 
    public static String formatTimestamp2Date(String Timestamp,String dtFormat) throws Exception{  
        SimpleDateFormat format = new SimpleDateFormat(dtFormat);  
        Long time = new Long(Timestamp);  
        return format.format(time*1000);   
    }
      
    // String转化为时间戳  
    public static String formatDate2Timestamp(String date,String dtFormat) throws Exception{  
        SimpleDateFormat format = new SimpleDateFormat(dtFormat);  
        Date dt= format.parse(date);
        Long ts =dt.getTime()/1000;
        return String.valueOf(ts);
    }
    
    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * 
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }
    
    
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }
    
    public static String parse(Long timestamp, String pattern) throws ParseException {
        return getSdf(pattern).format(timestamp);
    }
    
    public static Date parse(Long timestamp) throws ParseException {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }
    
    public static Date parse(String value,String pattern) throws ParseException {
    	return getSdf(pattern).parse(value);
    }
    
    
    public static Date parseUnixTimestamp(Long timestamp) throws ParseException {
        Timestamp stamp = new Timestamp(timestamp * 1000);
        return new Date(stamp.getTime());
    }
    
    public static DateFormat getDateFormat(String pattern) {
    	return getSdf(pattern);
    }
    
    public static LocalDate toLocalDate(String date) {
    	return LocalDate.parse(date);
    }
    
    public static Date toDate(LocalDate ld) {
    	return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static LocalDate plusDays(LocalDate ld,Long days) {
    	return ld.plusDays(days);
    } 
    
    public static Date toDateTime(Date date,Date time) throws Exception {
    	if(Objects.isNull(date) || Objects.isNull(time)) {
    		throw new Exception("参数为空");
    	}
    	String datePatten = format(date, DATE_PARTERN);
    	String timePatten = format(time, TIME_PARTEN);   	
    	String dateTimePatten = datePatten + " " + timePatten;
		return parse(dateTimePatten, SHORT_PARTEN);
    }
    
    /**
	     * 判断时间是否在时间段内 
     *    
     * @param: @return      
     * @return: boolean      
     * @throws:   
     * @author:弓步高
     * @date:2018年12月20日 下午5:57:09
     */
    public static boolean isInDate(Date compareSrc, Date compareTarStart, Date compareTarEnd) {
    	if (compareSrc.getTime() >= compareTarStart.getTime()
				&& compareSrc.getTime() <= compareTarEnd.getTime()) {
    		return true;
		}
    	return false;
    }
	 /**
	     * 判断时间是否在时间段内 
	 *    
	 * @param: @return      
	 * @return: boolean      
	 * @throws:   
	 * @author:弓步高
	 * @date:2018年12月20日 下午5:57:09
	 */
	public static boolean isInDate(Date compareSrcStart,Date compareSrcEnd, Date compareTarStart, Date compareTarEnd) {
		if (compareSrcStart.getTime() <= compareTarStart.getTime()
				&& compareSrcEnd.getTime() >= compareTarEnd.getTime()) {
			return true;
		}
		return false;
	}
	/**
	 * 日期格式字符串转换成UNIX时间戳
	 *
	 * @param dateStr 字符串日期
	 * @param format  如：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String date2TimeStamp(String dateStr, String format) {
	  try {
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return date2TimeStamp(sdf.parse(dateStr));
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	  return "";
	}
	
	/**
	 * 日期格式字符串转换成UNIX时间戳
	 *
	 * @param dateStr 字符串日期
	 * @param format  如：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String date2TimeStamp(Date date) {
	  try {
	    return String.valueOf(date.getTime() / 1000);
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	  return "";
	}
}
