package com.alibaba.controller.jiguang;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.util.ParamInfo;
@RestController
public class FolderDelController {
	
	@Autowired
	FileUploadService fileUploadService;
    
	
	Logger logger=Logger.getLogger(UserController.class);
   //三屏 下拉按钮的控制  在终端机上删除
	@RequestMapping(value = "folderDelIs", method = RequestMethod.POST)
	public Map<String, Object> getFoldersInfoNews(
			@RequestBody ParamInfo pInfo) {
		try {
	        // 查找被删除的数据
		 		List<LinkedHashMap<String, Object>> maps = fileUploadService
						.selectByDelFolderGolal( pInfo.getUser_id(),1);
		 		
		 		if(null!=maps && maps.size()>0) {
		 			 Object numObject = 0;    //0未读  1 已读
		 			return JsonResult.build("200", "查找成功",numObject);
		 		}
		 		
		}catch(Exception e) {
			
			logger.error(e.getMessage());
			
			
			e.printStackTrace();
		}
		Object numObject = 1;
		return JsonResult.build("200", "查找成功",numObject);
	}
	
	
	//2点击以后 修改状态
	
	@RequestMapping(value = "folderDelIs_dian", method = RequestMethod.POST)
	public Map<String, Object> getFoldersInfoNews1223(
			@RequestBody ParamInfo pInfo) {
		try {
	        // 查找被删除的数据
		 		List<LinkedHashMap<String, Object>> maps = fileUploadService
						.selectByDelFolderGolal( pInfo.getUser_id(),1);
		 		
		 		 if(null!=maps && maps.size()>0) {
		 		    fileUploadService.updateIsDian(maps);
		 		 }
		 		
		 			return JsonResult.build("200", "修改成功");
		 		
		 		
		}catch(Exception e) {
			
			logger.error(e.getMessage());
			
			
			e.printStackTrace();
		}
		
		return JsonResult.build("202", "修改失败");
	}
	
	
}
