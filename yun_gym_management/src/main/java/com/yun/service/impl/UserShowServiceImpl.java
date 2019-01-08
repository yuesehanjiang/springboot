package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yun.constant.OSSConstant;
import com.yun.constant.ResponseMessage;
import com.yun.mapper.UserShowMapper;
import com.yun.pojo.UserShow;
import com.yun.service.UserShowService;
import com.yun.utils.OSSUtil;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class UserShowServiceImpl implements UserShowService {

	@Autowired
	private UserShowMapper userShowMapper;
	
	/**
	 * 新增会员秀
	 */
	@Override
	public HashMap<String, Object> saveUserShow(UserShow userShow, MultipartFile file) {
		if(StringUtils.isHasEmpty(userShow.getGymAddr(), userShow.getNickname(), 
				userShow.getSex()) || file == null){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String verificationStr = StringUtils.verificationStr(file.getOriginalFilename(), userShow.getNickname(), userShow.getSignature(), null, null);
		if(!verificationStr.equals("success")){
			return ServerResponse.build("203", verificationStr);
		}
		int userShowCountByGymAddr = userShowMapper.getUserShowCountByGymAddr(userShow.getGymAddr());
		if(userShowCountByGymAddr >= 100){
			String minCreateTime = userShowMapper.getMinCreateTime(userShow.getGymAddr());
			UserShow us = new UserShow();
			us.setGymAddr(userShow.getGymAddr());
			us.setCreateTime(minCreateTime);
			int count = userShowMapper.deleteUserShowByCreate(us);
			if(count < 1){
				return ServerResponse.build("203", ResponseMessage.exception);
			}
		}
		String fileName = null;
		try {
			fileName = StringUtils.replaceStr(file.getOriginalFilename());
			fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			// 获取输出流
			String imagePath2 = "https://"+OSSConstant.bucketName+".oss-cn-shenzhen.aliyuncs.com/"+OSSConstant.userShowImageFold+"/" + fileName;
			OSSUtil.uploadObjectOSS(file, ""+OSSConstant.bucketName+"", ""+OSSConstant.userShowImageFold+"/");
			userShow.setImage(imagePath2);
			int saveUserShow = userShowMapper.saveUserShow(userShow);
			if(saveUserShow > 0){
				return ServerResponse.build("200", ResponseMessage.success);
			} else {
				return ServerResponse.build("203", ResponseMessage.exception);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询会员秀
	 */
	@Override
	public HashMap<String, Object> listUserShowInfo(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getPageNum(), parameterUtil.getPageSize(), parameterUtil.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameterUtil.getPageNum(), parameterUtil.getPageSize());
		parameterUtil.setPageNum(calculationPage.get("pageNum")+"");
		parameterUtil.setPageSize(calculationPage.get("pageSize")+"");
		List<UserShow> listUserExperience = userShowMapper.listUserShowInfo(parameterUtil);
		if(listUserExperience.size() < 1){
			return ServerResponse.build("200", ResponseMessage.isNull, listUserExperience);
		}
		int userShowCount = userShowMapper.getUserShowCount(parameterUtil);
		return ServerResponse.build("200", ResponseMessage.success, listUserExperience, userShowCount+"", calculationPage.get("pNum")+"", parameterUtil.getPageSize());
	}

	/**
	 * 删除会员秀
	 */
	@Override
	public HashMap<String, Object> delUserShow(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int delUserShow = userShowMapper.delUserShow(value);
		if(delUserShow > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 查询要修改的会员秀
	 */
	@Override
	public HashMap<String, Object> getUserShow(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		UserShow userShow = userShowMapper.getUserShow(value);
		if(userShow != null){
			return ServerResponse.build("200", ResponseMessage.success, userShow);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 修改会员秀
	 */
	@Override
	public HashMap<String, Object> updateUserShow(UserShow userShow, MultipartFile file) {
		if(StringUtils.isHasEmpty(userShow.getGymAddr(), userShow.getNickname(), 
				userShow.getSex())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String fileName = null;
		String filePath = null;
		try {
			if(file == null){
				fileName = StringUtils.replaceStr(userShow.getImage());
			} else {
				if(file.getOriginalFilename().length() > 100){
					return ServerResponse.build("203", "图片名字不能超过100个字符");
				}
				fileName = StringUtils.replaceStr(file.getOriginalFilename());
			}
			String verificationStr = StringUtils.verificationStr(null, userShow.getNickname(), userShow.getSignature(), null, null);
			if(!verificationStr.equals("success")){
				return ServerResponse.build("203", verificationStr);
			}
			filePath = fileName;
			if(file != null){
				fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			}
			// 获取输出流
			String imagePath2 = "https://"+OSSConstant.bucketName+".oss-cn-shenzhen.aliyuncs.com/"+OSSConstant.userShowImageFold+"/" + fileName;
			if(file != null){
				OSSUtil.uploadObjectOSS(file, ""+OSSConstant.bucketName+"", ""+OSSConstant.userShowImageFold+"/");
			}
			if(file != null){
				userShow.setImage(imagePath2);
			} else {
				userShow.setImage(filePath);
			}
			userShowMapper.updateUserShow(userShow);
			return ServerResponse.build("200", ResponseMessage.success);
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 查询待审核的数据
	 */
	@Override
	public HashMap<String, Object> getExamineInfo(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getPageNum(), parameterUtil.getPageSize(), parameterUtil.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameterUtil.getPageNum(), parameterUtil.getPageSize());
		parameterUtil.setPageNum(calculationPage.get("pageNum")+"");
		parameterUtil.setPageSize(calculationPage.get("pageSize")+"");
		List<UserShow> examineInfo = userShowMapper.getExamineInfo(parameterUtil);
		if(examineInfo.size() < 1){
			return ServerResponse.build("200", ResponseMessage.isNull, examineInfo);
		}
		int userShowCount = userShowMapper.getExamineInfoCount(parameterUtil);
		return ServerResponse.build("200", ResponseMessage.success, examineInfo, userShowCount+"", calculationPage.get("pNum")+"", parameterUtil.getPageSize());
	}

	/**
	 * 查询已审核的数据
	 */
	@Override
	public HashMap<String, Object> getExamineAdoptInfo(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getPageNum(), parameterUtil.getPageSize(), parameterUtil.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameterUtil.getPageNum(), parameterUtil.getPageSize());
		parameterUtil.setPageNum(calculationPage.get("pageNum")+"");
		parameterUtil.setPageSize(calculationPage.get("pageSize")+"");
		List<UserShow> examineInfo = userShowMapper.getExamineAdoptInfo(parameterUtil);
		if(examineInfo.size() < 1){
			return ServerResponse.build("200", ResponseMessage.isNull, examineInfo);
		}
		int userShowCount = userShowMapper.getExamineAdoptInfoCount(parameterUtil);
		return ServerResponse.build("200", ResponseMessage.success, examineInfo, userShowCount+"", calculationPage.get("pNum")+"", parameterUtil.getPageSize());
	}

	/**
	 * 同意审核
	 */
	@Override
	public HashMap<String, Object> examineUserShow(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int examineUserShow = userShowMapper.examineUserShow(value);
		if(examineUserShow > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 查询审核的详情
	 */
	@Override
	public HashMap<String, Object> getExamineDetailsInfo(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getGymAddr(), parameterUtil.getId())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		UserShow examineDetailsInfo = userShowMapper.getExamineDetailsInfo(parameterUtil);
		if(examineDetailsInfo != null){
			return ServerResponse.build("200", ResponseMessage.success, examineDetailsInfo);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 拒绝审核
	 */
	@Override
	public HashMap<String, Object> examineRefuseUserShow(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int examineUserShow = userShowMapper.examineRefuseUserShow(value);
		if(examineUserShow > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 *  删除已审核的数据
	 */
	@Override
	public HashMap<String, Object> delExamineAdoptInfo(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int delExamineAdoptInfo = userShowMapper.delExamineAdoptInfo(value);
		if(delExamineAdoptInfo > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		}else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 *  查询未审核的数据总数
	 */
	@Override
	public HashMap<String, Object> getTipsInfoCount(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int tipsInfoCount = userShowMapper.getTipsInfoCount(value);
		return ServerResponse.build("200", ResponseMessage.success, tipsInfoCount);
	}

}
