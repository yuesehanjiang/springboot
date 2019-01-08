package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.UserFace;
import com.yun.service.UserFaceService;
import com.yun.utils.ServerResponse;

@RestController
public class UserFaceController {
    
    private final static Logger logger = LoggerFactory.getLogger(UserFaceController.class);

    @Autowired
    private UserFaceService userFaceService;

    /**
     * 添加人脸 https://qizhiyun.whochange.com/gym/machine/saveUserFace
     * 
     * @return
     */
    @PostMapping("/machine/saveUserFace")
    public HashMap<String, Object> saveUserFace(@RequestBody UserFace userFace) {
        try {
            return userFaceService.saveUserFace(userFace);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 根据gymAddr获取健身房的会员人脸信息
     * @param userFace
     * @return
     */
    @PostMapping("/machine/listUserFace")
    public HashMap<String, Object> getListUserFace(@RequestBody UserFace userFace) {
        try {
            return userFaceService.getListUserFace(userFace);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    

}
