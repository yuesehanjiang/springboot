package com.yun.service;

import java.util.HashMap;

import com.yun.pojo.User;

public interface UserService {

    HashMap<String, Object> sendUserToMachine(User user);

    HashMap<String, Object> bindToBracelet(User user);

    HashMap<String, Object> getUserInfo(User user);

}