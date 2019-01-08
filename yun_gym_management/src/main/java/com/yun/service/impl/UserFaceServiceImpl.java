package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yun.constant.JiGuanPushConstant;
import com.yun.constant.ResponseMessage;
import com.yun.mapper.UserFaceMapper;
import com.yun.pojo.UserFace;
import com.yun.service.UserFaceService;
import com.yun.utils.JiGunag;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
public class UserFaceServiceImpl implements UserFaceService{
    
    @Autowired
    private UserFaceMapper userFaceMapper;

    @Override
    public HashMap<String, Object> saveUserFace(UserFace userFace) {
        try {
            if (StringUtils.isHasEmpty(userFace.getGymAddr(),userFace.getOpenId(),
                    userFace.getFace(),userFace.getFaceImg())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            //是否已存在
            int check = userFaceMapper.checkUserFace(userFace);
            if (check == 1) {
                userFaceMapper.updateUserFace(userFace);
            }else {            
                int insertUserFace = userFaceMapper.saveUserFace(userFace);
                if (insertUserFace != 1) {
                    return ServerResponse.build("203", "添加失败！");
                }
            }
            //推送人脸信息到指定终端机
            List<String> machineList = userFaceMapper.getMachineListByGymAddr(userFace.getGymAddr());
            String[] machineArray = machineList.toArray(new String[machineList.size()]);
            //推送结果
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", userFace);
            map.put("type", JiGuanPushConstant.userFace);                   
            String result = JSONObject.toJSONString(map); 
            JiGunag.pushMessage2(machineArray, result,"");
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    @Override
    public HashMap<String, Object> getListUserFace(UserFace userFace) {
        try {
            if (StringUtils.isHasEmpty(userFace.getGymAddr())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            List<UserFace> list = userFaceMapper.getUserFaceListByGymAddr(userFace.getGymAddr());
            return ServerResponse.build("200", ResponseMessage.success,list);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

}
