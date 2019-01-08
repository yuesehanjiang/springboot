package com.yun.service;

import java.util.HashMap;

import com.yun.utils.ParameterUtil;

public interface UserTrainingRecordService {

    HashMap<String, Object> getStatisticSportsDatas(ParameterUtil parameterUtil);

    HashMap<String, Object> getTrainingRecordDatas(ParameterUtil parameterUtil);

}
