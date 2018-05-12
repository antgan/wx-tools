package com.soecode.wxtools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {
	public static String getCurrentWeek(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String week = "";
		try {
			System.out.println("date-- : " + date);
			if(date!=null){
				Date startDate = sdf.parse(date);
				int startDay = (int) (startDate.getTime() / 1000 / 60 / 60 / 24);
				int currentDay = (int) (new Date().getTime() / 1000 / 60 / 60 / 24);
				week = ""+((currentDay - startDay - 1) / 7 + 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return week;
	}

	public static String getWeekOfDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);  
		return week;
	}

	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getNowTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String getTimestamp(){
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	public static long getDelay(String targetTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date targetDate = null;
		try {
			targetDate = sdf.parse(targetTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return targetDate.getTime()-new Date().getTime();
	}

}
