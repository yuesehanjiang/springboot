package com.yun.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.AppUrl;

@Mapper
public interface AppUrlMapper {

    int saveAppUrl(AppUrl appUrl);

    void updateAppUrl(AppUrl appUrl);

    List<String> getMachineListByGymAddr(String gymAddr);

    AppUrl getAppUrlByGymAddr(String gymAddr);

}
