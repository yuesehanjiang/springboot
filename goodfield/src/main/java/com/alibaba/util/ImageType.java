package com.alibaba.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import cn.jiguang.common.utils.StringUtils;
/**
 * Created by Administrator on 2018/5/19.
 */
public class ImageType {

	/**
     * 判断网络图片是否存在
     * posturl 图片地址链接
     */
    public static String isImagesTrue(String posturl) throws IOException {
    	try {
    		URL urlStr = new URL(posturl);         
    		HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();     
    		int state = connection.getResponseCode();       
    		if (state == 200) {
    			return "success";
    		} else {
    			return "error";
    		}
        } catch (Exception e) {
        	return "error";
        }
    }
	
	/**
	 * 判断视频文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String VideoType(String filePath) throws IOException{
		Path path = Paths.get(filePath);
		String type = Files.probeContentType(path);
		return type;
	}
	
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
            if (type.contains("FFD8FF") || type.contains("89504E47") || type.contains("47494638") || type.contains("424D") || type.contains("49492A00")) {
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
    /**
    * 根据文件流判断图片类型
    *
    * @param fis
    * @return jpg/png/gif/bmp
    */
   public static boolean getPicType2(InputStream fis) {
       //读取文件的前几个字节来判断图片格式
       byte[] b = new byte[4];
       try {
           fis.read(b, 0, b.length);
           String type = bytesToHexString(b).toUpperCase();
           if (type.contains("FFD8FF") || type.contains("89504E47") || type.contains("424D") || type.contains("49492A00")) {
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
    
    public static String fun(String str, String s){
		String[] split = s.split(",");
		for (String string : split) {
			str = str.replace(string, "").replaceAll("(,)+", ",");
		}
		if(str.endsWith(",")){
			str = str.substring(0, str.length() - 1);
		}
		if(str.startsWith(",")){
			str = str.substring(1);
		}
		return str;
	}
    
    /**
     * 获取ip
     * @param str
     * @param s
     * @return
     */
    public static String ipInfo(){
    	try {
			InetAddress addr = InetAddress.getLocalHost();  
			String ip = addr.getHostAddress().toString(); //获取本机ip  
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	public static String getIpAdrress(HttpServletRequest request) {
		String Xip = request.getHeader("X-Real-IP");
		String XFor = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = XFor.indexOf(",");
			if (index != -1) {
				return XFor.substring(0, index);
			} else {
				return XFor;
			}
		}
		XFor = Xip;
		if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			return XFor;
		}
		if (StringUtils.isNotEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isNotEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isNotEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isNotEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isNotEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getRemoteAddr();
		}
		return XFor;
	}
    
}
