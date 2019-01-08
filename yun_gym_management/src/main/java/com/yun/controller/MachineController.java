package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.service.MachineService;
import com.yun.utils.ServerResponse;


/**
 * 
*项目名称：企智云_终端机
*创建作者：瞿黑子
*创建日期：2018年11月18日
*详细描述：终端机管理
 */
@RestController
@RequestMapping("/machine/")
public class MachineController {

    private final static Logger logger = LoggerFactory.getLogger(MachineController.class);
    
    @Autowired
    private MachineService machineService;
    
    /**
     * 终端机初始化：
     *   (1)发送终端机emmc_id保存，
     *   (2)获取当前终端机的绑定信息
     * @param machineCode
     * @param emmc_id
     * @return
     */
    @PostMapping("/initMachine")
    public HashMap<String, Object> initMachine(String machineCode,String emmc_id){
        try {
            return machineService.initMachine(machineCode,emmc_id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    } 
    
    /**
     * 心跳
     * @param machineCode
     * @return
     */
    @PostMapping("/machineConnectionCheck")
    public HashMap<String, Object> machineConnectionCheck(String machineCode){
        try {
            return machineService.machineConnectionCheck(machineCode);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    } 
    
    
    
}