package com.yun.service;

import java.util.HashMap;

public interface MachineService {

    HashMap<String, Object> initMachine(String machineCode, String emmc_id);

    HashMap<String, Object> machineConnectionCheck(String machineCode);

}