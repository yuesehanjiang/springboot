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
import com.yun.pojo.Coach;
import com.yun.service.CoachService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/coach/")
public class CoachController {

	@Autowired
	private CoachService coachService;
    private final static Logger logger=Logger.getLogger(CoachController.class);
	
	/**


	 * 修改点赞数
	 * url: http://localhost:8091/gym/coach/addVote
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "addVote")
	public HashMap<String, Object> addVote(@RequestBody Coach coach) {
		try {
			return coachService.addVote(coach.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**

	 * 查询所有教练 
	 * url: http://localhost:8091/gym/coach/listCoachInfo
	 * @param {"pageNum":"", "pageSize":"", "gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "listCoachInfo")
	public HashMap<String, Object> listCoachInfo(@RequestBody ParameterUtil parameterUtil) {
		try {
			return coachService.listCoachInfo(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 新增教练 
	 * url: http://localhost:8091/gym/coach/saveCoach
	 * @param {"name":"", "sex":"", "image":"", "introduction":"", "trainContent":"", "gymAddr":"", "rank":""}
	 * @return
	 */
	@PostMapping(value = "saveCoach")
	public HashMap<String, Object> saveCoach(Coach coach, MultipartFile file) {
		try {
			return coachService.saveCoach(coach, file);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 删除教练 
	 * url: http://localhost:8091/gym/coach/delCoach
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "delCoach")
	public HashMap<String, Object> delCoach(@RequestBody Coach coach) {
		try {
			return coachService.delCoach(coach.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 根据id查询教练 
	 * url: http://localhost:8091/gym/coach/getCoachById
	 * @param {"id":""}
	 * @return
	 */
	@PostMapping(value = "getCoachById")
	public HashMap<String, Object> getCoachById(@RequestBody Coach coach) {
		try {
			return coachService.getCoachById(coach.getId()+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 修改教练 
	 * url: http://localhost:8091/gym/coach/updateCoach
	 * @param {"id":"", "name":"", "sex":"", "introduction":"", "trainContent":"", "rank":""}
	 * @return
	 */
	@PostMapping(value = "updateCoach")
	public HashMap<String, Object> updateCoach(Coach coach, MultipartFile file) {
		try {
			return coachService.updateCoach(coach, file);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
}
