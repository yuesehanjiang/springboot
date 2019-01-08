package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yun.constant.JiGuanPushConstant;
import com.yun.constant.ResponseMessage;
import com.yun.mapper.AppUrlMapper;
import com.yun.pojo.AppUrl;
import com.yun.service.AppUrlService;
import com.yun.utils.JiGunag;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AppUrlServiceImpl implements AppUrlService{


    @Autowired
    private AppUrlMapper appUrlMapper;

    @Override
    public HashMap<String, Object> saveAppUrl(AppUrl appUrl) {
        //最新添加
        try {
            if (appUrl.getId() == 0) {
                int saveAppUrl = appUrlMapper.saveAppUrl(appUrl);
                if(saveAppUrl == 1){
                    return ServerResponse.build("200", ResponseMessage.success);
                } else {
                    return ServerResponse.build("203", ResponseMessage.exception);
                }
            }
             //更新操作
            else{
                //更新数据库
                appUrlMapper.updateAppUrl(appUrl);
                if (!StringUtils.isHasEmpty(appUrl.getUrl())) {                    
                    //同步与该门店绑定的终端机
                    List<String> machines = appUrlMapper.getMachineListByGymAddr(appUrl.getGymAddr());//获取机器列表
                    String[] machineArray = machines.toArray(new String[machines.size()]);
                    //推送结果
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("data", appUrl.getUrl());
                    map.put("type", JiGuanPushConstant.appUrl);                   
                    String result = JSONObject.toJSONString(map); 
                    JiGunag.pushMessage2(machineArray, result,"");
                }
                return ServerResponse.build("200", ResponseMessage.success);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    @Override
    public HashMap<String, Object> getAppUrl(String gymAddr) {
        if (gymAddr == null || "".equals(gymAddr)) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull); 
        }
        try {
            AppUrl appUrl = appUrlMapper.getAppUrlByGymAddr(gymAddr);
            if (appUrl == null) {
                return ServerResponse.build("204", ResponseMessage.isNull);
            }
            return ServerResponse.build("200", ResponseMessage.success,appUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
}
