package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yun.constant.OSSConstant;
import com.yun.constant.ResponseMessage;
import com.yun.mapper.CoachMapper;
import com.yun.pojo.Coach;
import com.yun.service.CoachService;
import com.yun.utils.OSSUtil;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class CoachServiceImpl implements CoachService {

	@Autowired
	private CoachMapper coachMapper;
	
	/**
	 * 新增教练
	 */
	@Override
	public HashMap<String, Object> saveCoach(Coach coach, MultipartFile file) {
		if(StringUtils.isHasEmpty(coach.getName(), coach.getSex(),coach.getIntroduction()
				,coach.getGymAddr()) || file == null){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String verificationStr = StringUtils.verificationStr(file.getOriginalFilename(), coach.getName(), null, coach.getIntroduction(), coach.getTrainContent());
		if(!verificationStr.equals("success")){
			return ServerResponse.build("203", verificationStr);
		}
		int coachCountByGymAddr = coachMapper.getCoachCountByGymAddr(coach.getGymAddr());
		if(coachCountByGymAddr >= 50){
			String minCreateTime = coachMapper.getMinCreateTime(coach.getGymAddr());
			Coach co = new Coach();
			co.setGymAddr(coach.getGymAddr());
			co.setCreateTime(minCreateTime);
			int count = coachMapper.deleteCoachByCreate(co);	
			if(count < 1){
				return ServerResponse.build("203", ResponseMessage.exception);
			}
		}
		String fileName = null;
		try {
			fileName = StringUtils.replaceStr(file.getOriginalFilename());
			fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			// 获取输出流
			String imagePath2 = "https://"+OSSConstant.bucketName+".oss-cn-shenzhen.aliyuncs.com/"+OSSConstant.coachShowImageFold+"/" + fileName;
			OSSUtil.uploadObjectOSS(file, ""+OSSConstant.bucketName+"", ""+OSSConstant.coachShowImageFold+"/");
			coach.setImage(imagePath2);
			int saveUserShow = coachMapper.saveCoach(coach);
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
	 * 查询所有教练
	 */
	@Override
	public HashMap<String, Object> listCoachInfo(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getPageNum(), parameterUtil.getPageSize(), parameterUtil.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameterUtil.getPageNum(), parameterUtil.getPageSize());
		parameterUtil.setPageNum(calculationPage.get("pageNum")+"");
		parameterUtil.setPageSize(calculationPage.get("pageSize")+"");
		List<Coach> listCoachInfo = coachMapper.listCoachInfo(parameterUtil);
		if(listCoachInfo.size() < 1){
			return ServerResponse.build("200", ResponseMessage.isNull, listCoachInfo);
		}
		int coachCount = coachMapper.getCoachCount(parameterUtil);
		return ServerResponse.build("200", ResponseMessage.success, listCoachInfo, coachCount+"", calculationPage.get("pNum")+"", parameterUtil.getPageSize());
	}

	/**
	 * 根据id查询教练
	 */
	@Override
	public HashMap<String, Object> getCoachById(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		Coach coachById = coachMapper.getCoachById(value);
		if(coachById != null){
			return ServerResponse.build("200", ResponseMessage.success, coachById);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 删除教练
	 */
	@Override
	public HashMap<String, Object> delCoach(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int delCoach = coachMapper.delCoach(value);
		if(delCoach > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 修改教练
	 */
	@Override
	public HashMap<String, Object> updateCoach(Coach coach, MultipartFile file) {
		if(StringUtils.isHasEmpty(coach.getName(), coach.getSex(), coach.getIntroduction(), 
				coach.getId()+"")){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String verificationStr = StringUtils.verificationStr(null, coach.getName(), null, coach.getIntroduction(), coach.getTrainContent());
		if(!verificationStr.equals("success")){
			return ServerResponse.build("203", verificationStr);
		}
		String fileName = null;
		String filePath = null;
		try {
			if(file != null){
				if(file.getOriginalFilename().length() > 100){
					return ServerResponse.build("203", "图片名字不能超过100个字符");
				}
				fileName = StringUtils.replaceStr(file.getOriginalFilename());
			} else {
				fileName = StringUtils.replaceStr(coach.getImage());
			}
			filePath = fileName;
			if(file != null){
				fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			}
			// 获取输出流
			String imagePath2 = "https://"+OSSConstant.bucketName+".oss-cn-shenzhen.aliyuncs.com/"+OSSConstant.coachShowImageFold+"/" + fileName;
			if(file != null){
				OSSUtil.uploadObjectOSS(file, ""+OSSConstant.bucketName+"", ""+OSSConstant.coachShowImageFold+"/");
			}
			if(file != null){
				coach.setImage(imagePath2);
			} else {
				coach.setImage(filePath);
			}
			coachMapper.updateCoach(coach);
			return ServerResponse.build("200", ResponseMessage.success);
		} catch (Exception e) {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 修改点赞数
	 */
	@Override
	public HashMap<String, Object> addVote(String id) {
		if(StringUtils.isHasEmpty(id)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int updateVote = coachMapper.addVote(id);
		if(updateVote > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

}