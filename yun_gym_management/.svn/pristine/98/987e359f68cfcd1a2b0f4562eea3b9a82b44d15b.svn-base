package com.yun.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.ThemeMapper;
import com.yun.pojo.Theme;
import com.yun.service.ThemeService;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {

	@Autowired
	private ThemeMapper themeMapper;
	
	/**
	 * 添加主题
	 */
	@Override
	public HashMap<String, Object> saveTheme(Theme theme) {
		if(StringUtils.isHasEmpty(theme.getGymAddr(), theme.getTheme())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String themeInfo = themeMapper.getThemeInfo(theme.getGymAddr());
		if(themeInfo != null){
			themeMapper.updateTheme(theme);
			return ServerResponse.build("200", ResponseMessage.success);
		}
		int saveTheme = themeMapper.saveTheme(theme);
		if(saveTheme > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 查询主题
	 */
	@Override
	public HashMap<String, Object> getTheme(String value) {
		if(StringUtils.isHasEmpty(value)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String theme = themeMapper.getTheme(value);
		if(theme != null){
			return ServerResponse.build("200", ResponseMessage.success, theme);
		} else {
			return ServerResponse.build("204", ResponseMessage.isNull);
		}
	}

}
