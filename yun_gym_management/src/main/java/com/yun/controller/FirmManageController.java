package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.FirmManage;
import com.yun.service.FirmManageService;
import com.yun.utils.ServerResponse;

/**
 * 
*项目名称：企智云_终端机
*创建作者：瞿黑子
*创建日期：2018年11月16日
*详细描述：企业信息数据同步
 */
@RestController
@RequestMapping("/firmManage/")
public class FirmManageController {
    
    private final static Logger logger = LoggerFactory.getLogger(AppUrlController.class);
    

    @Autowired
    private FirmManageService firmManageService;
    
    /**
     * 添加
     * localhost:8091/firmManage/saveFirmInfo
     * @param firmManage
     * @return
     */
    @PostMapping("saveFirmInfo")
    public HashMap<String, Object> saveFirmInfo(FirmManage firmManage){
        try {
            return firmManageService.saveFirmInfo(firmManage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 修改
     * localhost:8091/firmManage/udpateFirmInfo
     * @param firmManage
     * @return
     */
    @PostMapping("udpateFirmInfo")
    public HashMap<String, Object> udpateFirmInfo(FirmManage firmManage){
        try {
            return firmManageService.udpateFirmInfo(firmManage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 删除
     * localhost:8091/firmManage/deleteFirmInfo
     * @param id
     * @return
     */
    @PostMapping("deleteFirmInfo")
    public HashMap<String, Object> deleteFirmInfo(Integer id){
        try {
            return firmManageService.deleteFirmInfo(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
}




















