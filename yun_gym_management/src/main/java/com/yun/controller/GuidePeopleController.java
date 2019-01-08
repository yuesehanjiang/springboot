package com.yun.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yun.constant.ResponseMessage;
import com.yun.pojo.GuidePeople;
import com.yun.service.GuidePeopleService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/guidePeople/")
public class GuidePeopleController {

	@Autowired
	private GuidePeopleService guidePeopleService;
	
	private final static Logger logger = LoggerFactory.getLogger(UserFaceController.class);
	
	/**
	 * 查询指引人
	 * url: http://localhost:8091/gym/guidePeople/listGuidePeople
	 * @param {"gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "listGuidePeople")
	public HashMap<String, Object> listGuidePeople(@RequestBody ParameterUtil parameterUtil) {
		try {
			return guidePeopleService.listGuidePeople(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 提供终端机的指引人
	 * url: http://localhost:8091/gym/guidePeople/listGuidePeopleTerminal
	 * @param {"gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "listGuidePeopleTerminal")
	public HashMap<String, Object> listGuidePeopleTerminal(@RequestBody GuidePeople guidePeople) {
		try {
			return guidePeopleService.listGuidePeopleTerminal(guidePeople);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 删除指引人
	 * url: http://localhost:8091/gym/guidePeople/deleteGuidePeopleById
	 * @param {"gymAddr":"", "id":""}
	 * @return
	 */
	@PostMapping(value = "deleteGuidePeopleById")
	public HashMap<String, Object> deleteGuidePeopleById(@RequestBody ParameterUtil parameterUtil) {
		try {
			return guidePeopleService.deleteGuidePeopleById(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 新增指引人
	 * url: http://localhost:8091/gym/guidePeople/saveGuidePeople
	 * @param {"gymAddr":"", "sex":"", "name":"", "code":""}
	 * @return
	 */
	@PostMapping(value = "saveGuidePeople")
	public HashMap<String, Object> saveGuidePeople(@RequestBody GuidePeople guidePeople) {
		try {
			return guidePeopleService.saveGuidePeople(guidePeople);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询要修改的指引人信息
	 * url: http://localhost:8091/gym/guidePeople/getGuidePeopleById
	 * @param {"gymAddr":"", "id":""}
	 * @return
	 */
	@PostMapping(value = "getGuidePeopleById")
	public HashMap<String, Object> getGuidePeopleById(@RequestBody ParameterUtil parameterUtil) {
		try {
			return guidePeopleService.getGuidePeopleById(parameterUtil);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 修改指引人信息
	 * url: http://localhost:8091/gym/guidePeople/updateGuidePeopleById
	 * @param {"gymAddr":"", "id":"", "code":"", "sex":"", "name":""}
	 * @return
	 */
	@PostMapping(value = "updateGuidePeopleById")
	public HashMap<String, Object> updateGuidePeopleById(@RequestBody GuidePeople guidePeople) {
		try {
			return guidePeopleService.updateGuidePeopleById(guidePeople);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
}
