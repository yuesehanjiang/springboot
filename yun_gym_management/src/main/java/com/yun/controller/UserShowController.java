package com.yun.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.UserShow;
import com.yun.service.UserShowService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/userShow/")
public class UserShowController {

	@Autowired
	private UserShowService userShowService;
	private static final Logger logger=Logger.getLogger(UserShowController.class);
	
	/**
	 * 查询未审核的数据总数
	 * url: http://localhost:8091/gym/userShow/getTipsInfoCount
	 * @param {"gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "getTipsInfoCount")
	public HashMap<String, Object> getTipsInfoCount(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.getTipsInfoCount(parameterUtil.getGymAddr());
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 删除已审核的数据
	 * url: http://localhost:8091/gym/userShow/delExamineAdoptInfo
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "delExamineAdoptInfo")
	public HashMap<String, Object> delExamineAdoptInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.delExamineAdoptInfo(parameterUtil.getId());
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询要待审核的数据
	 * url: http://localhost:8091/gym/userShow/getExamineInfo
	 * @param {"pageNum":"", "pageSize":"", "gymAddr":"", "keyword":""}
	 * @return
	 */
	@PostMapping(value = "getExamineInfo")
	public HashMap<String, Object> getExamineInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.getExamineInfo(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询审核的详情
	 * url: http://localhost:8091/gym/userShow/getExamineDetailsInfo
	 * @param {"id":"", "gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "getExamineDetailsInfo")
	public HashMap<String, Object> getExamineDetailsInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.getExamineDetailsInfo(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询已审核的数据
	 * url: http://localhost:8091/gym/userShow/getExamineAdoptInfo
	 * @param {"pageNum":"", "pageSize":"", "gymAddr":"", "keyword":"", "examine":""}
	 * @return
	 */
	@PostMapping(value = "getExamineAdoptInfo")
	public HashMap<String, Object> getExamineAdoptInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.getExamineAdoptInfo(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 同意审核
	 * url: http://localhost:8091/gym/userShow/examineUserShow
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "examineUserShow")
	public HashMap<String, Object> examineUserShow(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.examineUserShow(parameterUtil.getId());
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 拒绝审核
	 * url: http://localhost:8091/gym/userShow/examineRefuseUserShow
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "examineRefuseUserShow")
	public HashMap<String, Object> examineRefuseUserShow(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.examineRefuseUserShow(parameterUtil.getId());
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询会员秀
	 * url: http://localhost:8091/userShow/listUserShowInfo
	 * @param {"pageNum":"", "pageSize":"", "gymAddr":"", "sex":"", "keyword":""}
	 * @return
	 */
	@PostMapping(value = "listUserShowInfo")
	public HashMap<String, Object> listUserShowInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return userShowService.listUserShowInfo(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 删除会员秀
	 * url: http://localhost:8091/userShow/delUserShow
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "delUserShow")
	public HashMap<String, Object> delUserShow(@RequestBody UserShow userShow) {
		try {
			return userShowService.delUserShow(userShow.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询要修改的会员秀
	 * url: http://localhost:8091/userShow/getUserShow
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "getUserShow")
	public HashMap<String, Object> getUserShow(@RequestBody UserShow userShow) {
		try {
			return userShowService.getUserShow(userShow.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 修改会员秀
	 * url: http://localhost:8091/userShow/updateUserShow
	 * @param {"nickname":"", "image":"", "sex":"", "signature":"", "id":"", "gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "updateUserShow")
	public HashMap<String, Object> updateUserShow(UserShow userShow, MultipartFile file) {
		try {
			return userShowService.updateUserShow(userShow, file);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 新增会员秀
	 * url: http://localhost:8091/userShow/saveUserShow
	 * @param {"nickname":"", "sex":"", "image":"", "signature":"", "gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "saveUserShow")
	public HashMap<String, Object> saveUserShow(UserShow userShow, MultipartFile file) {
		try {
			return userShowService.saveUserShow(userShow, file);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
}