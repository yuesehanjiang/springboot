package com.alibaba.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.dao.InterfaceInfoMapper;
import com.alibaba.pojo.Machine;
import com.alibaba.service.AdminService;
import com.alibaba.util.ParamInfo;


@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/terminal/")
public class PushsController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private InterfaceInfoMapper infoMapper;
	
	Logger logger=Logger.getLogger(UserController.class);
	
	/**
	 * 修改终端机状态
	 * http://47.106.33.137:9096/goodfield/web/terminal/jpushHeartbeatInfo
	 * http://192.168.5.223:8090/goodfield/web/terminal/jpushHeartbeatInfo
	 * @param {"machine_id":"1", "memory":"","type":""}
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/jpushHeartbeatInfo")
	public HashMap<String, Object> index(@RequestParam("machine_id") String machine_id, @RequestParam("memory") String memory, @RequestParam("type") String type){
		try {
			ParamInfo pInfo = new ParamInfo();
			pInfo.setMachine_id(machine_id);
			adminService.updateMachineStatusonLine(pInfo);	 // 修改在线状态的终端机
			String content = null;
			String memorys = infoMapper.getMemory(machine_id);
			if(memorys != null){
				String[] split = memorys.split(",");
				if("0".equals(type)){
					String str = split[1];
					content = ""+memory+","+str+"";
				} else {
					String str = split[0];
					content = ""+str+","+memory+"";
				}
			}
			// 修改终端机内存大小
			Machine machine = new Machine();
			machine.setMachine_id(machine_id);
			machine.setMemory(content);
			infoMapper.updateMachineMemory(machine);
			// 修改终端机内存大小
			return JsonResult.build("200", "终端机状态修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "终端机状态修改失败！");
		} 
	}
	
}
