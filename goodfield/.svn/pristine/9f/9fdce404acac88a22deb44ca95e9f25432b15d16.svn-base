package com.alibaba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;




/**
 * 
 * @ClassName: TimeInterval
 * @Description: TODO(计算时间区间)
 * @author Administrator
 * @date 2018年5月31日
 *
 */
public class TimeInterval {
	
	/**
	 * 判断两个时间区间是否有交集的方法
	 * 
	 * @param date1_1
	 *            区间1的时间始
	 * @param date1_2
	 *            区间1的时间止
	 * @param date2_1
	 *            区间2的时间始
	 * @param date2_2
	 *            区间2的时间止
	 * @return 区间1和区间2如果存在交集,则返回true,否则返回falses
	 * @throws Exception
	 */
	public static boolean isDateCross(Date date1_1, Date date1_2, Date date2_1,
			Date date2_2) throws Exception {
		boolean flag = false;// 默认无交集
		long l1_1 = date1_1.getTime();
		long l1_2 = date1_2.getTime();
		long l2_1 = date2_1.getTime();
		long l2_2 = date2_2.getTime();

		if ((l1_1 > l1_2) || (l2_1 > l2_2))

			throw new Exception(
					"Parameter error:date1_1 should not be great than date1_2 and date2_1 should not be great than date2_2");
		if (((l1_1 <= l2_1) && (l2_1 <= l1_2))
				|| ((l1_1 <= l2_2) && (l2_2 <= l1_2))
				|| ((l2_1 <= l1_1) && (l1_1 <= l2_2))
				|| ((l2_1 <= l1_2) && (l1_2 <= l2_2))) {
			flag = true;
		}
		return flag;
	}
	
	
	
	public static boolean isEffectiveDate(Date nowTime, Date startTime,
			Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	

	/**
	 * String转data String 类型是 HH:ss
	 */
	public static Date StringToDate(String data) {

		String format = "yyyy-MM-dd HH:mm";
		Date nowTime = null;
		try {
			nowTime = new SimpleDateFormat(format).parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowTime;
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}

	/**
	 * 得到当前时间加上一天
	 */
	public static Date getLastDate(Date date) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, Integer.MAX_VALUE);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		return date;

	}
	
	
	/**
	 * 获取一个史前的 时间
	 */
	public static Date getFirstDate(Date date) {
		if(null==date){
			return null;
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -10);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		return date;

	}
	
	/**
	 * 计算两个时间之间 的天数
	 */
	public static Integer getDateDay(String dbtime2,String dbtime1) {
		
		
		//算两个日期间隔多少天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			Date date1= format.parse(dbtime1);
			Date date2 = format.parse(dbtime2);
			int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
			return a;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return null;
	}
	
	
	
	/**
	 *  计算两个时间之间的小时数
	 */
  public static Integer getDateDate(String dbtime2,String dbtime1) {
			//算两个日期间隔多少天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		try {
			Date date1= format.parse(dbtime1);
			Date date2 = format.parse(dbtime2);
			int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600));
			return a;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return null;
	}
  
  
  public static void main(String[] args) {
		//Date stringtoDate = DateToString.StringtoDate("2018-12-12 13:12:12");
		
		
	//	Date stringtoDate1 = DateToString.StringtoDate("2019-12-12 13:12:12");
	           Integer dateDate = 
	        		   getDateDate("2019-12-12 13:12:12", "2018-12-12 14:12:12");
		
	          System.out.println(dateDate);
		
	}

	
	

}
