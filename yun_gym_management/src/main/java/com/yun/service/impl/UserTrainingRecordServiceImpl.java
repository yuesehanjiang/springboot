package com.yun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.yun.constant.ResponseMessage;
import com.yun.service.UserTrainingRecordService;
import com.yun.utils.OkHttpUtil;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;


@Service
public class UserTrainingRecordServiceImpl implements UserTrainingRecordService {

    @SuppressWarnings("unchecked")
	@Override
    public HashMap<String, Object> getStatisticSportsDatas(ParameterUtil parameterUtil) {
        if (StringUtils.isHasEmpty(parameterUtil.getGymAddr(),parameterUtil.getPageNum(),parameterUtil.getPageSize(),
                parameterUtil.getTimeType(),parameterUtil.getTrainType())) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull);
        }
        String url = "http://47.107.56.215:8080/statistic/statisticSportsDatas";

        // flag：为获取卡路里或者里程统计标识，kcal为卡路里，mile为里程，必填；
        // dateFlag：为日期标识，day为日统计、week为周统计、month为月统计、year为年统计，必填；
        // sportsType：为运动类型标识，仅作用于里程统计，111为跑步机，112为动感单车，非必填；
        HashMap<String, String> params = new HashMap<>();
        params.put("dateFlag", parameterUtil.getTimeType());
        params.put("gymAddr", parameterUtil.getGymAddr());
        params.put("limit", parameterUtil.getPageSize());
        params.put("page", parameterUtil.getPageNum());
        
        // trainType //训练类型：1:总卡；2：跑步机；3：动感单车
        if ("1".equals(parameterUtil.getTrainType())) {
            params.put("flag", "kcal");
            params.put("sportsType", "111");
        } else if ("2".equals(parameterUtil.getTrainType())) {
            params.put("flag", "mile");
            params.put("sportsType", "111");
        } else if ("3".equals(parameterUtil.getTrainType())) {
            params.put("flag", "mile");
            params.put("sportsType", "112");
        }

        ArrayList<Object> data = null;
        double count = 0;
        String result = OkHttpUtil.post(url, params);
        if (result != "") {
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result, map.getClass());
            data = (ArrayList<Object>) map.get("data");
            count = (double) map.get("count");
        }
        return ServerResponse.build("200", ResponseMessage.success, data, count + "", parameterUtil.getPageNum(),
                parameterUtil.getPageSize());
    }

    @SuppressWarnings("unchecked")
	@Override
    public HashMap<String, Object> getTrainingRecordDatas(ParameterUtil parameterUtil) {
        if (StringUtils.isHasEmpty(parameterUtil.getDateTime(),parameterUtil.getOpenId())) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull);
        }
        String url = "http://47.107.56.215:8080/history/trainingRecordDatas";
        HashMap<String, String> params = new HashMap<>();
        params.put("openId", parameterUtil.getOpenId());
        params.put("date", parameterUtil.getDateTime());

        Gson gson = new Gson();
        String result = OkHttpUtil.post(url, params);

        HashMap<String, Object> resultMap = new HashMap<>();
        ArrayList<Object> data = null;
        LinkedTreeMap<String, Object> total = null;

        if (result != "") {
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result, map.getClass());
            data = (ArrayList<Object>) map.get("selectDay");
            total = (LinkedTreeMap<String, Object>) map.get("total");
        }
        resultMap.put("selectDay", data);
        resultMap.put("sum", total);
        return ServerResponse.build("200", ResponseMessage.success, resultMap);
    }

}
