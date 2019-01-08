package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.Club;
import com.yun.service.ClubService;
import com.yun.utils.ServerResponse;

/**
 * 
*项目名称：企智云_终端机
*创建作者：瞿黑子
*创建日期：2018年11月15日
*详细描述：门店同步模块，包括增删该接口
 */
@RestController
@RequestMapping("/club")
public class ClubController {
    
    private final static Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private ClubService clubService;
    
    /**
     * 添加新门店信息
     * http://localhost:8091/gym/club/saveClubInfo
     * @param club
     * @return
     */
    @PostMapping("/saveClubInfo")
    public HashMap<String, Object> saveClubInfo(Club club){
        try {
            return clubService.saveClubInfo(club);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 修改门店信息
     * http://localhost:8091/club/updateClubInfo
     * @param club
     * @return
     */
    @PostMapping("/updateClubInfo")
    public HashMap<String, Object> updateClubInfo(Club club){
        try {
            return clubService.updateClubInfo(club);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    /**
     * 删除门店信息
     * http://localhost:8091/club/deleteClubInfo
     * @param gymAddr
     * @return
     */
    @PostMapping("deleteClubInfo")
    public HashMap<String, Object> deleteClubInfo(String gymAddr){
        try {
            return clubService.deleteClubInfo(gymAddr);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    
}
