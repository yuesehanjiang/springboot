package com.alibaba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 判断2个时间段的时间是否有重叠现象
 * @author xiangchao
 *
 */
public class TimeSlotUtil {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //true 有重叠  false 没有重叠
	public static Boolean isOverlap(String startdate1, String enddate1,
			String startdate2, String enddate2) {
		Date leftStartDate = null;
		Date leftEndDate = null;
		Date rightStartDate = null;
		Date rightEndDate = null;
		try {
			leftStartDate = format.parse(startdate1);
			leftEndDate = format.parse(enddate1);
			rightStartDate = format.parse(startdate2);
			rightEndDate = format.parse(enddate2);
		} catch (ParseException e) {
			return false;
		}
		return ((leftStartDate.getTime() >= rightStartDate.getTime()) && leftStartDate
				.getTime() < rightEndDate.getTime())
				|| ((leftStartDate.getTime() > rightStartDate.getTime()) && leftStartDate
						.getTime() <= rightEndDate.getTime())
				|| ((rightStartDate.getTime() >= leftStartDate.getTime()) && rightStartDate
						.getTime() < leftEndDate.getTime())
				|| ((rightStartDate.getTime() > leftStartDate.getTime()) && rightStartDate
						.getTime() <= leftEndDate.getTime());
	}

	
	  

	 /**
	     * 获取过去或者未来 任意天内的日期数组
	     * @param intervals      intervals天内
	     * @return              日期数组
	     */
	    public static ArrayList<String> fetureDaysList(int intervals ) {
	        ArrayList<String> pastDaysList = new ArrayList<String>();
	        ArrayList<String> fetureDaysList = new ArrayList<String>();
	        for (int i = 0; i <intervals; i++) {
	            //pastDaysList.add(getPastDate(i));
	            fetureDaysList.add(getFetureDate(i));
	        }
	        return fetureDaysList;
	    }
	
	    
	    /**
	     * 获取未来 第 past 天的日期
	     * @param past
	     * @return
	     */
	    public static String getFetureDate(int past) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
	        Date today = calendar.getTime();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        String result = format.format(today);
	       
	        return result;
	    }

	

	public static void main(String[] args) {
		Boolean overlap = isOverlap("2018-12-12 12:12:12","2018-12-13 12:12:12",
				"2018-12-14 12:12:12", "2018-12-15 12:12:12");
		
		System.out.println(overlap);
	}

}
