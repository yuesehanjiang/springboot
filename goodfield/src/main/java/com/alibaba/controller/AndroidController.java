package com.alibaba.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.pojo.MemberInfo;
import com.alibaba.service.AndroidService;
import com.alibaba.util.ParamInfo;


@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/android/")
public class AndroidController {

	@Autowired
	private AndroidService androidService;
	
	Logger logger=Logger.getLogger(UserController.class);

	
	/**
	 * 查询安卓需要统计的数据
	 * url : http://localhost:8080/goodfield/web/android/getAdnroidStores
	 * @param {"machine_id":"device004"}
	 * @return
	 */
	@RequestMapping("/getAdnroidStores")
	public HashMap<String, Object> getAdnroidStores(@RequestBody ParamInfo pInfo){
		try {
			String storesName = androidService.getStoresName(pInfo);
			if(storesName != null){
				pInfo.setStores_id(storesName);
				ParamInfo adnroidStores = androidService.getAdnroidStores(pInfo);
				return JsonResult.build("200", "查询成功", adnroidStores);
			} else {
				return JsonResult.build("201", "暂无数据");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	/**
	 * 查询安卓需要统计的数据
	 * url : http://localhost:8080/goodfield/web/android/getdateTimeStores
	 * @param {"machine_id":"device004"}
	 * @return
	 */
	@RequestMapping("/getdateTimeStores")
	public HashMap<String, Object> getdateTimeStores(@RequestParam("machine_id") String  machine_id){
		try {
			ParamInfo pInfo = new ParamInfo();
			pInfo.setMachine_id(machine_id);
			String storesName = androidService.getStoresName(pInfo);
			pInfo.setStores_id(storesName);
			int adnroidCount = androidService.getAdnroidCount(pInfo);	// 查询门店总人数
			String storesname = androidService.storesName(pInfo);	// 查询门店总人数
			if(storesName != null){
				pInfo.setStores_id(storesName);
				List<MemberInfo> lists = new ArrayList<MemberInfo>();
				/* 获取半个月的日期 */
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				for(int i = 15; i >= 1; i--){
				    String startTime = df.format(new Date().getTime()-i*24*60*60*1000);
				    MemberInfo memberInfo = new MemberInfo();
			    	pInfo.setDatetime(startTime);
			    	MemberInfo getdateTimeStores = androidService.getdateTimeStores(pInfo);
			    	memberInfo.setDateTime(startTime);
			    	memberInfo.setMember(getdateTimeStores.getMember());
			    	memberInfo.setNonmember(getdateTimeStores.getNonmember());
			    	memberInfo.setMemberformer(getdateTimeStores.getMemberformer());
			    	lists.add(memberInfo);
				}
				/* 获取半个月的日期 */
				return JsonResult.build("200", "查询成功", lists, adnroidCount, storesname);
			} else {
				return JsonResult.build("201", "暂无数据");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	
	
}
