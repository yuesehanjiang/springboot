package com.yun.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.MachineMapper;
import com.yun.pojo.Machine;
import com.yun.service.MachineService;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class MachineServiceImpl implements MachineService{

    @Autowired
    private MachineMapper machineMapper;

    @Override
    public HashMap<String, Object> initMachine(String machineCode, String emmc_id) {
        try {
            if (StringUtils.isHasEmpty(machineCode,emmc_id)) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull); 
            }
            //添加emmc_id
            machineMapper.updateMachine(machineCode,emmc_id);
            //获取门店地址码和appurl
            Machine machine = machineMapper.getMachineInfo(machineCode);
            HashMap<String, Object> result = new HashMap<>();
            result.put("gymAddr", machine.getGymAddr());
            result.put("url", machine.getUrl());
            result.put("clubName", machine.getClubName());
            return ServerResponse.build("200", ResponseMessage.success,result);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    @Override
    public HashMap<String, Object> machineConnectionCheck(String machineCode) {
        try {
            if (StringUtils.isHasEmpty(machineCode)) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull); 
            }
            machineMapper.updateMachineForStatus(machineCode);
            return ServerResponse.build("200", ResponseMessage.success,"");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
}
