package com.yun.mapper;

import java.util.List;

import com.yun.pojo.UserFace;

public interface UserFaceMapper {

    int saveUserFace(UserFace userFace);

    int checkUserFace(UserFace userFace);

    void updateUserFace(UserFace userFace);

    List<String> getMachineListByGymAddr(String gymAddr);

    List<UserFace> getUserFaceListByGymAddr(String gymAddr);

	
	
}
