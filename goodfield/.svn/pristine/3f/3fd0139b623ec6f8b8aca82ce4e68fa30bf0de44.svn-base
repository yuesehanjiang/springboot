package com.alibaba.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringToIntegerArray {
    //String转 int  数组 
	public static List<String> stringToIntegerarry(String str){
		  
		   List<String> list=new ArrayList<String>();  
		   if(null !=str ||  !str.trim().equalsIgnoreCase("")) {
			      String[] split = str.split(",");   
			      List<String> asList = Arrays.asList(split);
			      for(int i=0;i<asList.size();i++) {
			    	  String string = asList.get(i);  
			    	
			    	  list.add(string);
			      }
			      return list;
		   }
		   
           return null;
	}
	
	
	
	
	/**
	 * list集合 转成字符串
	 * @param integers
	 * @return
	 */
	
	public static  String  listToString(List<String> integers) {
		String  string="";
		
		
		
		if(null!=integers  && integers.size()>0) {
			for (int i = 0; i < integers.size(); i++) {
				String integer = integers.get(i);
				string+=integer+",";
			}
			
		}
		
		return string;
	}
	
	
	
	
	
}
