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
import com.yun.pojo.MachineManagement;
import com.yun.service.MachineManagementService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/machinemanagement/")
public class MachineManagementController {

    private final static Logger logger = LoggerFactory.getLogger(MachineManagementController.class);

    @Autowired
    private MachineManagementService machineManagementService;

    /**
     * 查询终端机管理 url:
     * http://localhost:8091/gym/machinemanagement/listMachineManagement
     * 
     * @param {"gymAddr":"",
     *            "pageNum":"0", "pageSize":"10"}
     * @return
     */
    @PostMapping(value = "listMachineManagement")
    public HashMap<String, Object> listMachineManagement(@RequestBody ParameterUtil parameter) {
        try {
            return machineManagementService.listMachineManagement(parameter);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    /**
     * 新增终端机 url:
     * http://localhost:8091/gym/machinemanagement/saveMachineManagement
     * 
     * @param {"machineId":"",
     *            "memoryCap":"", "version":"", "productionTime":""}
     * @return
     */
    @PostMapping(value = "saveMachineManagement")
    public HashMap<String, Object> saveMachineManagement(@RequestBody MachineManagement management) {
        try {
            return machineManagementService.saveMachineManagement(management);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
	
}
