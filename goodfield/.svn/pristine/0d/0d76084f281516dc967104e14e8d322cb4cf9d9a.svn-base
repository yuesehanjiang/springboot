package com.alibaba.controller.jiguang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.dao.CountMapper;
import com.alibaba.pojo.User;
import com.alibaba.service.AdminService;
import com.alibaba.service.UserService;
import com.alibaba.util.MD5Util;
import com.alibaba.util.ParamInfo;
/**
 * 终端机  统计功能
 * @author QZY
 *
 */
@Transactional(rollbackFor = Exception.class)
@Controller
public class FirstPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	
	@Autowired
	CountMapper countMapper;
	
	private final static String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
	
	private final static String regexPhone = "^(10|11|12|13|14|15|16|17|18|19)\\d{9}$";

	Logger logger=Logger.getLogger(UserController.class);

	/*	
	//根据终端机的 id 查找该门店所有的会员
	@ResponseBody
	@RequestMapping(value="countUser" ,method = RequestMethod.POST)
	 public Map getCounts(String machineId) {
		
		  //该公司的 所有会员数据
		   Integer countInteger=countMapper.selectAllcount(machineId);
		   System.err.println(123);
		  //2 15天以内的曾会员和会员的数据s
		   
		  List<Map> noMember= countMapper.selectMebers(0,machineId);  //曾会员
		  
		  List<Map>   Member= countMapper.selectMebers(1,machineId);   //会员
		
		 //3 15天内 非会员信息s
		  List<Map> nonMember=  countMapper.slectNonMember(machineId);
		  
		  
		  Map map=new HashMap<String ,Object>();
		   map.put("all",countInteger);
		   map.put("noMember", noMember);
		   map.put("Member", Member);
		   map.put("nonMember", nonMember);
		  
		  
		  
		return  map;
	 }
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/download/video2.mp4",method = RequestMethod.GET)
    public String downloadAbilityTemplate(String fileName,HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");//设置编码
        
        response.setContentType("multipart/form-data");//设置类型           response.setContentType("application/vnd.ms-excel");  excel下载
      //  String typeString= fileName.substring(fileName.lastIndexOf("."));
        
        
        response.setHeader("Content-Disposition", "attachment;fileName=video2.mp4");                                       //设置响应头
        try {

        InputStream inputStream = 
        		new FileInputStream(new File("MP4文件\\video2.mp4"));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
        }//边读模板文件边写入输出流s
        os.close();
        inputStream.close();//关流
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;                //注意此时return nulls
}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/download/video3.mp4",method = RequestMethod.GET)
    public String downloadAbilityTemplate1(String fileName,HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");//设置编码
        
        response.setContentType("multipart/form-data");//设置类型           response.setContentType("application/vnd.ms-excel");  excel下载
      //  String typeString= fileName.substring(fileName.lastIndexOf("."));
        
        
        response.setHeader("Content-Disposition", "attachment;fileName=video3.mp4");                                       //设置响应头
        try {

        InputStream inputStream = 
        		new FileInputStream(new File("MP4文件\\video3.mp4"));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
        }//边读模板文件边写入输出流s
        os.close();
        inputStream.close();//关流
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;                //注意此时return nulls
}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/download/bottom.jpg",method = RequestMethod.GET)
    public String downloadAbilityTemplate2(String fileName,HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");//设置编码
        
        response.setContentType("multipart/form-data");//设置类型           response.setContentType("application/vnd.ms-excel");  excel下载
        //String typeString= fileName.substring(fileName.lastIndexOf("."));
        
        
        response.setHeader("Content-Disposition", "attachment;fileName=bottom.jpg");                                       //设置响应头
        try {

        InputStream inputStream = 
        		new FileInputStream(new File("MP4文件\\bottom.jpg"));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
        }//边读模板文件边写入输出流s
        os.close();
        inputStream.close();//关流
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;                //注意此时return nulls
}
	
	
	@ResponseBody
	@RequestMapping(value = "/download/middle.jpg",method = RequestMethod.GET)
    public String downloadAbilityTemplate3(String fileName,HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");//设置编码
        
        response.setContentType("multipart/form-data");//设置类型           response.setContentType("application/vnd.ms-excel");  excel下载
      //  String typeString= fileName.substring(fileName.lastIndexOf("."));
        
        
        response.setHeader("Content-Disposition", "attachment;fileName=middle.jpg");                                       //设置响应头
        try {

        InputStream inputStream = 
        		new FileInputStream(new File("MP4文件\\middle.jpg"));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
        }//边读模板文件边写入输出流s
        os.close();
        inputStream.close();//关流
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;                //注意此时return nulls
}
	
	
*/	

	
        @ResponseBody
		@RequestMapping(value = "/web/user/userLogin1", method = RequestMethod.POST)
		public HashMap<String, Object> userLogin1(@RequestBody ParamInfo paramInfo) {
			try {
				if (!paramInfo.getPassword().matches(regex)) {
					return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
				}
				List<Map> selectMaps =
						
						countMapper.selectMaps(paramInfo.getAccount(), paramInfo.getMachine_id());
				
				if(null == selectMaps  || selectMaps.size()==0) {
					return JsonResult.build("205", "该终端机和用户不对应");
				}
				
				
				User user = new User();
				user.setPassword(MD5Util.generate(paramInfo.getPassword()));
				user.setAccount(paramInfo.getAccount());
				User userLogin = userService.userLogin(user);
				if (userLogin != null) { // 验证身份通过
					String pic = adminService.getPicInfo();
					String enterprisename = adminService.getEnterpriseNameInfo();
					userLogin.setAdminPic(pic);
					userLogin.setEnterpriseName(enterprisename);
					try {
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return JsonResult.build("200", "登陆成功！", userLogin);
				} else {
					return JsonResult.build("205", "登陆失败！", userLogin);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return JsonResult.build("205", "登陆失败，请重试！");
			}
		}

	
	
}
