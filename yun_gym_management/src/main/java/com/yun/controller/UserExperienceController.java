package com.yun.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.UserExperience;
import com.yun.service.UserExperienceService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.RedisUtils;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/userexperience/")
public class UserExperienceController {

	@Autowired
	private  UserExperienceService userExperienceService;
	
	private static final Logger logger=Logger.getLogger(UserExperienceController.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 发送短信验证码 
	 * url: http://localhost:8091/gym/userexperience/sendSms
	 * @param {"phone":"18529527874", "gymAddr":"123234"}
	 * @return
	 */
	@PostMapping(value = "sendSms")
	public HashMap<String, Object> sendSms(String phone, String gymAddr) {
		try {
			return userExperienceService.sendMessage(phone, gymAddr);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询客服体验数据
	 * http://localhost:8091/gym/userexperience/listUserExperience
	 * {"pageNum":"0", "pageSize":"10", "keyword":"", "dateTime":"", "startTime":"", "endTime":""}
	 * @return
	 */
	@PostMapping("listUserExperience")
	public HashMap<String, Object> listUserExperience(@RequestBody ParameterUtil parameterUtil){
		try {
			return userExperienceService.listUserExperience(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 添加客服体验数据
	 * http://localhost:8091/gym/userexperience/saveUserExperience
	 * {"name":"","phone":"","introducePerson":"","gymAddr":"", "createTime":"", "code":""}
	 * @return
	 */
	@PostMapping("saveUserExperience")
    public HashMap<String, Object> saveUserExperience(@RequestBody UserExperience userExperience){
        try {
            String object = (String) redisUtils.get("code_"+userExperience.getPhone());
            if(!userExperience.getCode().equals(object)){
                return ServerResponse.build("203", "验证码错误!");
            }
            return userExperienceService.saveUserExperience(userExperience);
        } catch (Exception e) {
            e.printStackTrace();logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
	
	/**
	 * 删除客服体验数据
	 * http://localhost:8091/gym/userexperience/delUserExperience
	 * {"id":""}
	 * @return
	 */
	@PostMapping("delUserExperience")
	public HashMap<String, Object> delUserExperience(@RequestBody UserExperience userExperience){
		try {
			return userExperienceService.delUserExperience(userExperience.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
}