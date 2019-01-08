package com.yun.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.ClubMapper;
import com.yun.pojo.Club;
import com.yun.service.ClubService;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Override
    public HashMap<String, Object> saveClubInfo(Club club) {
        if(StringUtils.isHasEmpty(club.getAddress(),club.getClubName(),club.getContact(),
                club.getCompanyName(),club.getGymAddr(),club.getGymAbout())){
            return ServerResponse.build("202", ResponseMessage.paramIsNull);
        }
        //查看是否重复
        Club whetherRepeat = clubMapper.getClubByGymAddr(club.getGymAddr());
        if (whetherRepeat != null) {
            return ServerResponse.build("201", ResponseMessage.paramIllegal);
        }
        int saveClubInfo = clubMapper.saveClubInfo(club);
        if(saveClubInfo == 1){
            return ServerResponse.build("200", ResponseMessage.success);
        } else {
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    @Override
    public HashMap<String, Object> updateClubInfo(Club club) {
        try {
            if (club == null) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            if (club.getGymAddr() == null || "".equals(club.getGymAddr())) {
                return ServerResponse.build("202", ResponseMessage.paramIsNull);
            }
            clubMapper.updateClubInfo(club);
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.build("203", ResponseMessage.exception);

        }
    }

    @Override
    public HashMap<String, Object> deleteClubInfo(String gymAddr) {
        if (gymAddr == null || "".equals(gymAddr)) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull);
        }
        int delClub = clubMapper.deleteClubInfo(gymAddr);
        if(delClub == 1){
            return ServerResponse.build("200", ResponseMessage.success);
        } else {
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    
    
}
