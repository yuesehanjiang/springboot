package com.alibaba.service.serviceiml;

import java.io.InputStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.common.listener.PutObjectProgressListener;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
@Component
public class AsycServiceIml {
    
	
	@Async
	public void fileUpload(Long totalSize, String userToken, String objectName,
			InputStream inputStream,
			PutObjectProgressListener putObjectProgressListener) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
				String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
				// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
				// https://ram.console.aliyun.com 创建RAM账号。
				String accessKeyId = "LTAIj6VwurVuWjsD";
				String accessKeySecret = "MHIXLKKUXWFMCzuewlMkBgioaAjhGN";
				String bucketName = "tianjiulongoa1";
				// 创建OSSClient实例。
				//String objectName = "G:\\Users\\qzy017\\Desktop\\1 001.jpg";	
				OSSClient ossClient = new OSSClient(endpoint,
						accessKeyId, accessKeySecret);
			
				
				PutObjectResult putObject = ossClient
						.putObject(new PutObjectRequest(
								bucketName, objectName,
								inputStream)
								.<PutObjectRequest> 
						withProgressListener(new PutObjectProgressListener(Integer.parseInt(totalSize+""),userToken)));
		
	}

}
