package com.flash.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final int ONEDAYMILS = 1000*60*60*24;//一天的毫秒数
	
	public static final int ONEWEEKMILS = 1000*60*60*24*7;
	
	public static final int ONEMONTHMILS = 1000*60*60*24*30;
	
	public static Timestamp getTodayStartTimestamp(){
		Timestamp startToday = null;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String startStr = format.format(today);
			Date startDate = format.parse(startStr);
			long startMil = startDate.getTime();
			startToday = new Timestamp(startMil);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期格式不正确");
		}
		
		return startToday;
	}
	public static Timestamp getTodayEndTimestamp() {
		long startMil = getTodayStartTimestamp().getTime();
		long endMil = startMil + ONEDAYMILS - 1;
		Timestamp endToday = new Timestamp(endMil);
		return endToday;
	}
	
	/**
	 * 获取自今天起 一个月内的开始日期
	 * @return
	 */
	public static Timestamp getOneMonthAgo(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		long startMils = c.getTimeInMillis();
		Timestamp start = new Timestamp(startMils);
		return start;
	}
	/**
	 * 获取1970年第一天
	 * @return
	 */
	public static Timestamp getGreenwichDay() {
		return new Timestamp(0L);
	}
}
