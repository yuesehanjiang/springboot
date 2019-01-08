package com.yun.service;

import java.util.HashMap;

import com.yun.pojo.FirmManage;

public interface FirmManageService {

    HashMap<String, Object> saveFirmInfo(FirmManage firmManage);

    HashMap<String, Object> udpateFirmInfo(FirmManage firmManage);

    HashMap<String, Object> deleteFirmInfo(Integer id);

}
