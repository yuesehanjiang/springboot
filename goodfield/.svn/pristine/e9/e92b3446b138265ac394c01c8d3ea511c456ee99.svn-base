package com.alibaba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
	
	
static	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//date转成String
	public static String dateToString(Date date) {
		
		String format = sdf.format(date);
		System.out.println(format);
		return format;
	}
    
	
	//2 date转成String
		public static String dateToStringNoTime(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			String format = sdf.format(date);
			System.out.println(format);
			return format;
		}
	
	//String转成date
	public static Date StringtoDate(String date) {
		 Date parse=null;
		 
		try {
			parse = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println(parse);
		return parse;
		
	}
    
	
	//2String转成date
		public static Date StringtoDateNoTime(String date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Date parse=null;
			try {
				parse = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.err.println(parse);
			return parse;
			
		}
		
		//2String转成date  带有时间和分钟
				public static Date StringtoDateNoTimeHavaHourMin(String date) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					 Date parse=null;
					try {
						parse = sdf.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.err.println(parse);
					return parse;
					
				}

		public static void main(String[] args) {
			 Date stringtoDate = StringtoDateNoTimeHavaHourMin("2018-07-04 08:30");
			 System.out.println(stringtoDate);
		}
    
   

}
