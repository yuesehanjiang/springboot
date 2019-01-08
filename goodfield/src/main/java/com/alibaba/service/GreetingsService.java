package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.GreetingsMapper;
import com.alibaba.pojo.Greetings;
import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Service
public class GreetingsService {

	@Autowired
	private GreetingsMapper greetingsMapper;
	
	/**
	 * 获取通用问候语绑定的门店	
	 * @param greetings
	 * @return
	 */
	public String getGreetingsName(String id) {
		return greetingsMapper.getGreetingsName(id);
	}

	/**
	 * 获取通用问候语绑定的门店	
	 * @param greetings
	 * @return
	 */
	public String getRoleIds(String id) {
		return greetingsMapper.getRoleIds(id);
	}
	
	/**
	 * 根据门店id查询终端机id
	 * @param greetings
	 * @return
	 */
	public List<String> getStoresIdByMachine(String stores_id) {
		return greetingsMapper.getStoresIdByMachine(stores_id);
	}
	
	/**
	 * 根据会员id查询门店id
	 * @param greetings
	 * @return
	 */
	public String getMemberByIdStoresId(String user_id) {
		return greetingsMapper.getMemberByIdStoresId(user_id);
	}
	
	/**
	 * 查询某个问候语所有的门店信息
	 * @param greetings
	 * @return
	 */
	public String getGreetingsInfos(Greetings greetings) {
		return greetingsMapper.getGreetingsInfos(greetings);
	}
	
	/**
	 * 设置group_concat存储大小
	 * @param greetings
	 * @return
	 */
	public void setGroupConcatMaxLen() {
		greetingsMapper.setGroupConcatMaxLen();
	}
	
	/**
	 * 查询要修改的专属问候语
	 * @param greetings
	 * @return
	 */
	public MemberShip getClusiveGreetingsInfoById(ParamInfo pInfo){
		return greetingsMapper.getClusiveGreetingsInfoById(pInfo);
	}
	
	/**
	 * 查询所有问候语
	 * @param greetings
	 * @return
	 */
	public List<Greetings> getGreetingsInfoList(ParamInfo pInfo){
		return greetingsMapper.getGreetingsInfoList(pInfo);
	}
	
	/**
	 * 查询问候语门店列表信息
	 * @param greetings
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo pInfo){
		return greetingsMapper.getUserRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询匹配的门店名
	 * @param greetings
	 * @return
	 */
	public List<Greetings> getGreetingRoleName(ParamInfo pInfo){
		return greetingsMapper.getGreetingRoleName(pInfo);
	}
	
	/**
	 * 添加问候语
	 * @param greetings
	 * @return
	 */
	public int addGreetingsInfo(ParamInfo pInfo){
		return greetingsMapper.addGreetingsInfo(pInfo);
	}
	
	/**
	 * 删除问候语
	 * @param greetings
	 * @return
	 */
	public int deleteGreetingsInfo(Greetings greetings){
		return greetingsMapper.deleteGreetingsInfo(greetings);
	}
	
	/**
	 * 查询要修改的问候语
	 * @param greetings
	 * @return
	 */
	public Greetings getGreetingsInfo(Greetings greetings){
		return greetingsMapper.getGreetingsInfo(greetings);
	}
	/**
	 * 查询要修改的问候语
	 * @param greetings
	 * @return
	 */
	public Greetings getGreetingsInfo2(Greetings greetings){
		return greetingsMapper.getGreetingsInfo2(greetings);
	}
	
	/**
	 * 超级逻辑判断
	 * @param greetings
	 * @return
	 */
	public String getOtherRoleId(ParamInfo greetings){
		return greetingsMapper.getOtherRoleId(greetings);
	}
	
	/**
	 * 超级逻辑判断2
	 * @param greetings
	 * @return
	 */
	public Greetings getOtherRoleIdAll(ParamInfo greetings){
		return greetingsMapper.getOtherRoleIdAll(greetings);
	}
	
	/**
	 * 修改的问候语
	 * @param greetings
	 * @return
	 */
	public int updateGreetingsInfo(ParamInfo pInfo){
		return greetingsMapper.updateGreetingsInfo(pInfo);
	}
	
	/**
	 * 查询专属问候语
	 * @param greetings
	 * @return
	 */
	public List<MemberShip> getClusiveGreetingsInfo(ParamInfo pInfo){
		return greetingsMapper.getClusiveGreetingsInfo(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param greetings
	 * @return
	 */
	public int getCount(ParamInfo pInfo){
		return greetingsMapper.getCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param greetings
	 * @return
	 */
	public int getCountGreetingsInfo(ParamInfo pInfo){
		return greetingsMapper.getCountGreetingsInfo(pInfo);
	}
	
	/**
	 * 删除专属问候语
	 * @param clusiveGreetings
	 * @return
	 */
	public int delClusiveGreetingsInfoById(MemberShip clusiveGreetings){
		return greetingsMapper.delClusiveGreetingsInfoById(clusiveGreetings);
	}
	
	/**
	 * 添加专属问候语
	 * @param clusiveGreetings
	 * @return
	 */
	public int addClusiveGreetingsInfoById(MemberShip clusiveGreetings){
		return greetingsMapper.addClusiveGreetingsInfoById(clusiveGreetings);
	}
	
}
