package com.alibaba.controller.jiguang;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.yun.utils.STSUtils;

import net.sf.json.JSONObject;


@RestController
public class STSController {
    
    @Value("classpath:static/config.json")
    private Resource config;
    
    
   org.apache.log4j.Logger longger=org.apache.log4j.Logger.getLogger(STSController.class);

    @RequestMapping("/sts/getToken")
    public String getToken() throws IOException{
        String result = STSUtils.readJsonFromStatic(config);
        JSONObject jsonObj  = JSONObject.fromObject(result);
        // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
        String accessKeyId = jsonObj.getString("AccessKeyID");
        String accessKeySecret = jsonObj.getString("AccessKeySecret");
        
        // RoleArn 需要在 RAM 控制台上获取
        String roleArn = jsonObj.getString("RoleArn");
        long durationSeconds = jsonObj.getLong("TokenExpireTime");
        String policy = STSUtils.ReadJson(jsonObj.getString("PolicyFile"));
        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        // 具体规则请参考API文档中的格式要求
        String roleSessionName = "alice-001";
        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;    
        try {
            final AssumeRoleResponse stsResponse = STSUtils.assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, durationSeconds);
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("status", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
               
            JSONObject ja1 = JSONObject.fromObject(respMap);
            return ja1.toString();  
        } catch (ClientException e) {
        	longger.error(e.getMessage());
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("status", e.getErrCode());
            respMap.put("AccessKeyId", "");
            respMap.put("AccessKeySecret", "");
            respMap.put("SecurityToken", "");
            respMap.put("Expiration", "");         
            JSONObject ja1 = JSONObject.fromObject(respMap);
            return ja1.toString();  
        }
    } 
    
    
    @RequestMapping(value="/hello")
    public String hello() {
    	
    	try {
			int ooo=10/0;
		} catch (Exception e) {
		 longger.error(e.getMessage());
			e.printStackTrace();
		}
    	return null;
    }
    
    
    
}
