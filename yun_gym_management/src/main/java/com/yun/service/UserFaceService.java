package com.yun.service;

import java.util.HashMap;

import com.yun.pojo.UserFace;

public interface UserFaceService {

    HashMap<String, Object> saveUserFace(UserFace userFace);

    HashMap<String, Object> getListUserFace(UserFace userFace);

}
