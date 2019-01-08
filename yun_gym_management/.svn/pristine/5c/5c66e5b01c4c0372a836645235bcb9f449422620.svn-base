package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.AppUrl;
import com.yun.service.AppUrlService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/appUrl/")
public class AppUrlController {

    private final static Logger logger = LoggerFactory.getLogger(AppUrlController.class);
    
    
    @Autowired
    private AppUrlService appUrlService;
    
    /**
     * 保存appurl地址
     * (1)体检新的url
     * (2)覆盖旧的url并推送最新url到指定门店终端机
     * @param appUrl
     * @return
     */
    @PostMapping("saveAppUrl")
    public HashMap<String, Object> saveAppUrl(@RequestBody AppUrl appUrl){
        try {
            return appUrlService.saveAppUrl(appUrl);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 根据地址码获取小程序url
     * @param gymaddr
     * @return
     */
    @PostMapping("getAppUrl")
    public HashMap<String, Object> getAppUrl(@RequestBody ParameterUtil parameterUtil){
        try {
            return appUrlService.getAppUrl(parameterUtil.getGymAddr());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
}



