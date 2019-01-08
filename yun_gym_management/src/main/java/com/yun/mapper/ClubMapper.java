package com.yun.mapper;

import com.yun.pojo.Club;

public interface ClubMapper {

    int saveClubInfo(Club club);

    Club getClubByGymAddr(String gymAddr);

    void updateClubInfo(Club club);

    int deleteClubInfo(String gymAddr);

    
}
