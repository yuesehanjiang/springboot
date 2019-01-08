package com.yun.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yun.constant.JiGuanPushConstant;
import com.yun.constant.ResponseMessage;
import com.yun.mapper.AppUrlMapper;
import com.yun.mapper.UserMapper;
import com.yun.pojo.AppUrl;
import com.yun.pojo.User;
import com.yun.service.UserService;
import com.yun.utils.JiGunag;
import com.yun.utils.OkHttpUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private AppUrlMapper appUrlMapper;

    @Override
    public HashMap<String, Object> sendUserToMachine(User user) {
        try {
            //查看是否重复
            User check = userMapper.getUserByOpenId(user.getOpenId());
            if (check != null) {
                //更新数据
                userMapper.updateUser(user);
            } else {                
                //保存到数据库            
                int insertUser = userMapper.insertUser(user);
                if (insertUser != 1) {
                    return ServerResponse.build("203", ResponseMessage.exception);
                }
            } 
            //推送到终端机
            if (user.getMachineCode() == null || "".equals(user.getMachineCode())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            //查看是否上传人脸图
            String faceImg = userMapper.getUserFaceImg(user.getOpenId());
            if (!StringUtils.isHasEmpty(faceImg)) {
                user.setFaceImg(faceImg);
            }else {
                user.setFaceImg("");
            }
            String[] machine = {user.getMachineCode()};
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", user); //数据内容
            map.put("type", JiGuanPushConstant.sendUserToMachine); //消息类型
            String result = new Gson().toJson(map);
            JiGunag.pushMessage2(machine, result,"");
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }





    @SuppressWarnings("unchecked")

	@Override

    public HashMap<String, Object> bindToBracelet(User user) {
        try {
            if (StringUtils.isHasEmpty(user.getOpenId(),user.getBracelet(),user.getGymAddr())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            //获取指定小程序的绑定url,没有则默认使用云康小程序绑定接口
            String braceletUrl = "https://xcxcs.whochange.com/shuibian_api/UserController/saveEquipmentByOpenId";
            AppUrl appUrl = appUrlMapper.getAppUrlByGymAddr(user.getGymAddr());
            if (appUrl != null) {                
                if (!StringUtils.isHasEmpty(appUrl.getBraceletUrl())) {
                    braceletUrl = appUrl.getBraceletUrl();
                }
                
            }
            HashMap<String, String> params = new HashMap<>();
            params.put("openId", user.getOpenId());   //用户openId
            params.put("equipmentId", user.getBracelet()); //手环编码
            String result = OkHttpUtil.post(braceletUrl, params);
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result, map.getClass());
            double status = (double) map.get("status");
            if (status == 1.0) {
                return ServerResponse.build("200", ResponseMessage.success);
            }else {                
                return ServerResponse.build("203", "手环绑定失败");
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }


    @Override
    public HashMap<String, Object> getUserInfo(@RequestBody User user) {
        try {
            if (StringUtils.isHasEmpty(user.getOpenId())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull); 
            }
            User info = userMapper.getUserInfo(user.getOpenId());
            info.setOpenId(user.getOpenId());
            String[] machine = {user.getMachineCode()};
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", info); //数据内容
            map.put("type", JiGuanPushConstant.sendUserToMachine); //消息类型
            String result = new Gson().toJson(map);
            JiGunag.pushMessage2(machine, result,"");
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
   
}