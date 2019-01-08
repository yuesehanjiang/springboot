package com.alibaba.common.listener;

import java.io.FileInputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;

import com.alibaba.util.RedisUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

public class PutObjectProgressListener implements ProgressListener {
    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;
    private   HttpSession  session;
    private  Integer percent=0;
    private   String userId;
   
    
    public PutObjectProgressListener(Integer totalBytes,String userId) {
    	this.userId=userId;
    	this.totalBytes=totalBytes;
    }
    
  
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
        case TRANSFER_STARTED_EVENT:
            System.out.println("Start to upload......");
            break;
        case REQUEST_CONTENT_LENGTH_EVENT:
            this.totalBytes = bytes;
            System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
            break;
        case REQUEST_BYTE_TRANSFER_EVENT:
            this.bytesWritten += bytes;
            if (this.totalBytes != -1) {//819200    12567013 
               double percent = (double)((this.bytesWritten *100 / this.totalBytes));
                  
                RedisUtils.setHashKV("upload", userId, percent+"");
               
                 
                System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
            } else {
                System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
            }
            break;
        case TRANSFER_COMPLETED_EVENT:
            this.succeed = true;   //Succeed to upload, 677394 bytes have been transferred in total
            System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
               
            int percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
             RedisUtils.setHashKV("upload", "upload_percent", percent+"");
             String hashKV = RedisUtils.getHashKV("upload", "upload_percent");
             
             System.err.println("haskkey::"+hashKV);
             
             
             String hashKVs = RedisUtils.getHashKV("upload", userId);
		     Cookie cookie=new Cookie("aa", hashKVs);	
			  cookie.setPath("/");
			  
			Cookie sessionId = new Cookie("JSESESSIONID", session.getId());
             
             
            break;
        case TRANSFER_FAILED_EVENT:
            System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
            break;
        default:
            break;
        }
    }
    public boolean isSucceed() {
        return succeed;
    }
    
  //(String endpoint, String accessKeyId, String secretAccessKey)  
    private static OSSClient ossClient = new OSSClient(
			"http://oss-cn-shenzhen.aliyuncs.com", "LTAIncw5TlYKR4SL",
			"YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui");
    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint ="http://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIncw5TlYKR4SL";
        String accessKeySecret = "YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui";
        String bucketName = "tianjiulongoa";
        String objectName = "G:\\Users\\qzy017\\Desktop\\1 001.jpg";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 带进度条的上传。
         /*   PutObjectResult putObject = ossClient.putObject(new PutObjectRequest(bucketName, objectName, new FileInputStream("G:\\Users\\qzy017\\Desktop\\1 001.jpg")).
                    <PutObjectRequest>withProgressListener(new PutObjectProgressListener(s)));*/
            
            
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}