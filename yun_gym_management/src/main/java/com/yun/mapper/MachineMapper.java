package com.yun.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yun.pojo.Machine;

@Mapper
public interface MachineMapper {

    void updateMachine(@Param("machineCode")String machineCode,@Param("emmc_id")String emmc_id);

    Machine getMachineInfo(String machineCode);

    void updateMachineForStatus(String machineCode);

}
