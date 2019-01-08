package com.yun.service;

import java.util.HashMap;

import com.yun.pojo.AppUrl;

public interface AppUrlService {

    HashMap<String, Object> saveAppUrl(AppUrl appUrl);

    HashMap<String, Object> getAppUrl(String gymAddr);

}
