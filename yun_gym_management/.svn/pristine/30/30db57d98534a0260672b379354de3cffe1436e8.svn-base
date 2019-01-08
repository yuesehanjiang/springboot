package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.service.UserTrainingRecordService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

/**
 * 
 * 项目名称：企智云_终端机 创建作者：瞿黑子 创建日期：2018年11月19日 详细描述：云康数据
 */
@RestController
public class UserTrainingRecordController {

    private final static Logger logger = LoggerFactory.getLogger(UserTrainingRecordController.class);

    @Autowired
    private UserTrainingRecordService userTrainingRecordService;

    /**
     * 排行榜 http://localhost:8091/gym/machine/statisticSportsDatas
     * 
     * @param parameterUtil
     * @return
     */
    @PostMapping("/machine/statisticSportsDatas")
    public HashMap<String, Object> getStatisticSportsDatas(@RequestBody ParameterUtil parameterUtil) {
        try {
            return userTrainingRecordService.getStatisticSportsDatas(parameterUtil);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    /**
     * 用户实时训练数据 http://localhost:8091/gym/machine/trainingRecordDatas
     * 
     * @param parameterUtil
     * @return
     */
    @PostMapping("/machine/trainingRecordDatas")
    public HashMap<String, Object> getTrainingRecordDatas(@RequestBody ParameterUtil parameterUtil) {
        try {
            return userTrainingRecordService.getTrainingRecordDatas(parameterUtil);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

}
