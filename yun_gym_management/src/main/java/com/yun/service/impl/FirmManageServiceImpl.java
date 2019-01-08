package com.yun.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.FirmManageMapper;
import com.yun.pojo.FirmManage;
import com.yun.service.FirmManageService;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class FirmManageServiceImpl implements FirmManageService{

    @Autowired
    private FirmManageMapper firmManageMapper;

    @Override
    public HashMap<String, Object> saveFirmInfo(FirmManage firmManage) {
        if (StringUtils.isHasEmpty(firmManage.getFirmAddress(),firmManage.getFirmAgent(),firmManage.getFirmName(),firmManage.getPhone())) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull); 
        }
        //查看是否重复
        FirmManage whetherRepeat = firmManageMapper.getFirmByFirmAccount(firmManage.getFirmAccount());
        if (whetherRepeat != null) {
            return ServerResponse.build("201", ResponseMessage.paramIllegal);
        }
        int saveClubInfo = firmManageMapper.saveFirmInfo(firmManage);
        if(saveClubInfo == 1){
            return ServerResponse.build("200", ResponseMessage.success);
        } else {
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    @Override
    public HashMap<String, Object> udpateFirmInfo(FirmManage firmManage) {
        try {
            if (firmManage == null) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            firmManageMapper.udpateFirmInfo(firmManage);
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);

        }
    }

    @Override
    public HashMap<String, Object> deleteFirmInfo(Integer id) {
        int delClub = firmManageMapper.deleteFirmInfo(id);
        if(delClub == 1){
            return ServerResponse.build("200", ResponseMessage.success);
        } else {
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    
    
}


















