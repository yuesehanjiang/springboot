package com.yun.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.User;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User getUserByOpenId(String openId);

    void updateUser(User user);

    String getUserFaceImg(String openId);

    User getUserInfo(String openId);

	
	
}