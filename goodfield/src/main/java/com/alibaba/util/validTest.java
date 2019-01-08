package com.alibaba.util;

public class validTest {

	public static boolean isValid(String contentType, String... allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.indexOf(type) > -1) {
				return true;
			}
		}
		return false;
	}

	
}
