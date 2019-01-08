package com.alibaba.util;

import java.io.IOException;
import java.io.InputStream;

public class VideoType {

	    /**
	     * byte数组转换成16进制字符串
	     *
	     * @param src
	     * @return
	     */
	    public static String bytesToHexString(byte[] src) {
	        StringBuilder stringBuilder = new StringBuilder();
	        if (src == null || src.length <= 0) {
	            return null;
	        }
	        for (int i = 0; i < src.length; i++) {
	            int v = src[i] & 0xFF;
	            String hv = Integer.toHexString(v);
	            if (hv.length() < 2) {
	                stringBuilder.append(0);
	            }
	            stringBuilder.append(hv);
	        }
	        return stringBuilder.toString();
	    }

	    /**
	     * 根据文件流判断图片类型
	     *
	     * @param fis
	     * @return jpg/png/gif/bmp
	     */
	    public static boolean getPicType(InputStream fis) {
	        //读取文件的前几个字节来判断图片格式
	        byte[] b = new byte[4];
	        try {
	            fis.read(b, 0, b.length);
	            String type = bytesToHexString(b).toUpperCase();
	            if (type.contains("41564920") || type.contains("6D6F6F76") ) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fis != null) {
	                try {
	                    fis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return false;
	    }

}
