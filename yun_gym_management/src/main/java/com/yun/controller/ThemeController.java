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
import com.yun.pojo.Theme;
import com.yun.service.ThemeService;
import com.yun.utils.ServerResponse;

@RestController
@RequestMapping("/theme/")
public class ThemeController {

	@Autowired
	private ThemeService themeService;
	 private final static Logger logger = LoggerFactory.getLogger(MachineManagementController.class);
	/**
	 * 添加主题
	 * url: http://localhost:8091/gym/theme/saveTheme
	 * @param {"gymAddr":"", "theme":""}
	 * @return
	 */
	@PostMapping(value = "saveTheme")
	public HashMap<String, Object> saveTheme(@RequestBody Theme theme) {
		try {
			return themeService.saveTheme(theme);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
	/**
	 * 查询主题
	 * url: http://localhost:8091/gym/theme/getTheme
	 * @param {"gymAddr":""}
	 * @return
	 */
	@PostMapping(value = "getTheme")
	public HashMap<String, Object> getTheme(@RequestBody Theme theme) {
		try {
			return themeService.getTheme(theme.getGymAddr());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}
	
}
