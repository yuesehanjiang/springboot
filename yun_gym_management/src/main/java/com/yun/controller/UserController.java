package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.User;
import com.yun.service.UserService;
import com.yun.utils.ServerResponse;

/**
 * 
*项目名称：企智云_终端机
*创建作者：瞿黑子
*创建日期：2018年11月28日
*详细描述：同步会员信息到终端机/会员手环绑定
 */
@RestController
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    
    @Autowired
    private UserService userService;
    
    /**
     * 同步会员信息到终端机
     * https://qizhiyun.whochange.com/gym/user/sendUserToMachine
     * @param user
     * @return
     */
    @PostMapping("/user/sendUserToMachine")
    public HashMap<String, Object> sendUserToMachine(User user){
        if (user == null) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull);
        }
        try {
            return userService.sendUserToMachine(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 会员手环绑定
     * @param users
     * @return
     */
    @PostMapping("/user/bindToBracelet")
    public HashMap<String, Object> bindToBracelet(@RequestBody User user){
        try {
            if (user == null) {
                return ServerResponse.build("201", ResponseMessage.paramIsNull);
            }
            return userService.bindToBracelet(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /*
     * 根据openId获取用户信息 
     */
    @PostMapping("/user/getUserInfo")
    public HashMap<String, Object> getUserInfo(@RequestBody User user){
        try {
            if (user == null) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            return userService.getUserInfo(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
 
}