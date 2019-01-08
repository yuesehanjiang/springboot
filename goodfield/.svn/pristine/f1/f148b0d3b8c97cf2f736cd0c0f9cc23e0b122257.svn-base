package com.alibaba.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.jpush.JiGunag;
import com.alibaba.common.result.JsonResult;
import com.alibaba.common.result.JsonResult2;
import com.alibaba.dao.InterfaceInfoMapper;
import com.alibaba.pojo.Admission;
import com.alibaba.pojo.Binding;
import com.alibaba.pojo.Greetings;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.NomemberImageLibrary;
import com.alibaba.pojo.NonMember;
import com.alibaba.pojo.Role;
import com.alibaba.service.AdminService;
import com.alibaba.service.AuditReportService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.InterfaceService;
import com.alibaba.util.IdCreator;
import com.alibaba.util.MemberShipList;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/ship")
public class InterfaceInfoController {

	@Autowired
	private InterfaceInfoService membershipService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private InterfaceInfoMapper infoMapper;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PushStatusService pushStatusService;
	
	@Autowired
	private AuditReportService auditReportService;
	
	Logger logger=Logger.getLogger(UserController.class);

	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/machineInit
	 * 终端机初始化的时候获取所有门店信息
	 * @return
	 */
	@RequestMapping(value="machineInit", produces="application/json;charset=utf-8")
	public HashMap<String, Object> machineInit() {
		try {
			List<Role> machineInit = infoMapper.machineInit();
			if(machineInit.size() < 1){
				return JsonResult.build("201", "暂无数据!");
			}
			return JsonResult.build("200", "查询成功!", machineInit);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败!");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveMemberShip
	 * 批量添加会员信息
	 * @param   {
					"lists":[
						{"user_id":"111", "stores_id":"111", "user_pic":"111", "user_name":"111", "member_type":"111", "user_type":"111", "user_cards":"111", "face":""},
						{"user_id":"111", "stores_id":"111", "user_pic":"111", "user_name":"111", "member_type":"111", "user_type":"111", "user_cards":"111", "face":""}
					]
				}
	 * @return
	 */
	@RequestMapping(value="saveMemberShip", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveMemberShip(@RequestBody MemberShipList lists) {
		try {
			List<MemberShip> lists2 = lists.getLists();
			List<MemberShip> list = new ArrayList<MemberShip>();
			String contents = "";
			for (MemberShip memberShip : lists2) {
				String cards = memberShip.getUser_cards();
				if(cards.endsWith(",")){
					cards = cards.substring(0, memberShip.getUser_cards().length() - 1);
					memberShip.setUser_cards(cards);
				}
				contents += "'"+memberShip.getUser_id()+"'"+",";
				list.add(memberShip);
			}
			interfaceService.saveMemberShip(list, contents.substring(0, contents.length() - 1));
			return JsonResult.build("200", "新增成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "新增失败，请重试！");
		}
	}
/*	@RequestMapping(value="saveMemberShip", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveMemberShip(@RequestBody MemberShipList lists) {
		try {
			List<MemberShip> lists2 = lists.getLists();
			for (MemberShip memberShip : lists2) {
				membershipService.deleteMembership(memberShip.getUser_id());
				String userIdInfo = membershipService.getUserIdInfo(memberShip);
				if(userIdInfo != null){ 
					continue;
				}
				String cards = memberShip.getUser_cards();
				if(cards.endsWith(",")){
					cards = cards.substring(0, memberShip.getUser_cards().length() - 1);
					memberShip.setUser_cards(cards);
				}
				membershipService.saveMemberShip(memberShip);
			}
			return JsonResult.build("200", "新增成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "新增失败，请重试！");
		}
	}
*/	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveStores
	 * 批量添加门店信息
	 * {
	 * 		"roles":[
	 * 			{"stores_id":"门店id","stores_name":"门店名","stores_city":"门店城市","stores_address":"门店地址"},
	 * 			{"stores_id":"门店id","stores_name":"门店名","stores_city":"门店城市","stores_address":"门店地址"}
	 * 		]
	 * }
	 * @return
	 */
	@RequestMapping(value="saveStores", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveStores(@RequestBody MemberShipList roles) {
		try {
			List<Role> lists2 = roles.getRoles();
			for (Role role : lists2) {
				Role r = adminService.getRoleId2(role);
				if(r != null){
					continue;
				}
				membershipService.saveStores(role);
			}
			return JsonResult.build("200", "新增成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "新增失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveNonMember
	 * 添加非会员数据
	 * {"stores_id":"门店id","user_name":"顾客姓名","user_phone":"手机号","user_in_time":"入场时间"}
	 * @return
	 */
	@RequestMapping(value="saveNonMember", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveNonMember(@RequestBody NonMember member) {
		try {
			int count = membershipService.saveNonMember(member);
			if(count > 0){
				return JsonResult.build("200", "添加成功！");
			} else {
				return JsonResult.build("205", "添加失败，请重试！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}
	
	/**
	 * goodfield/web/ship/saveManagementPic
	 * 添加会员图像库
	 * {"photo_time":"拍照时间", "pic_path":"图像路径", "user_id":"会员id", "running_water":"入场流水号", "member_type":"1"}
	 * @return
	 */
	@RequestMapping(value="saveManagementPic", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveManagementPic(@RequestBody ManagementPic managementPic) {
		try {
			String picSize = adminService.getPicSize();
			int managementPicCount = membershipService.getManagementPicCount(managementPic);
			if(Integer.parseInt(picSize) <= managementPicCount){
				return JsonResult.build("205", "会员图像存量最多限制在"+picSize+"个以内！");
			}
			managementPic.setUser_type(managementPic.getMember_type());
			String uuid = IdCreator.getId(16)+"_"+UUID.randomUUID().toString();
			// 开始判断是否先入场 start
			String admissionBindUuid = membershipService.getAdmissionBindUuid(managementPic.getUser_id().trim());
			if(admissionBindUuid != null){	// 说明是先入场
				Binding binding = new Binding();
				binding.setUser_id(managementPic.getUser_id().trim());
				binding.setUuid(admissionBindUuid);
				managementPic.setUuid(admissionBindUuid);
				membershipService.deleteAdmissionBindUuid(managementPic.getUser_id().trim());
			} else {
				managementPic.setUuid(uuid);
			}
			// 开始判断是否先入场 end
			Integer bindingCount = membershipService.getBindingCount();
			if(bindingCount == 0){
				membershipService.truncateBinding();
			}
			List<String> deleteChao = membershipService.deleteChao();
			if(deleteChao.size() > 0){
				for (String string : deleteChao) {
					membershipService.deleteAdmissionUuid(string);
				}
			}
			Binding binding = new Binding();
			binding.setUser_id(managementPic.getUser_id());
			binding.setUuid(uuid);
			String admissionUuid = membershipService.getAdmissionUuid(managementPic.getUser_id().trim());
			if(admissionUuid != null){
				membershipService.deleteAdmissionUuid(managementPic.getUser_id());
			}
			membershipService.saveBinding(binding);	// 添加拍照绑定的uuid
			int count = membershipService.saveManagementPic(managementPic);
			if(admissionBindUuid != null){
				binding.setUuid(admissionBindUuid);
				String userInTime = membershipService.getUserInTime(binding).replace(".0", "");
				if(userInTime != null){
					if(managementPic.getPhoto_time() != null && userInTime != null){
						if(managementPic.getPhoto_time().compareTo(userInTime) > -1){	// 满足时间条件删除
							Binding bind = new Binding();
							bind.setUser_id(managementPic.getUser_id().trim());
							bind.setUuid(admissionBindUuid);
							membershipService.deleteManagementPicInfo(bind);
						}
					}
				}
			}
			if(count > 0){
				return JsonResult.build("200", "添加成功！");
			} else {
				return JsonResult.build("205", "添加失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}

	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveAdmission
	 *  添加入场人数统计会员信息
	 * {"stores_id":"门店id","user_id":"会员id","user_card":"卡号","user_in_time":"入场时间","user_type":"会员的门店类型","appoint":"是否约课","valid_member":"是否有效会员"}
	 * @return
	 */
	@RequestMapping(value="saveAdmission", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveAdmission(@RequestBody Admission admission) {
		try {
			String admissionUuid = membershipService.getAdmissionUuid(admission.getUser_id());
			ManagementPic mp = new ManagementPic();
			mp.setUuid(admissionUuid);
			List<String> time = auditReportService.getTime(mp);
			for (String string : time) {
				if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).compareTo(string.replace(".0", "")) > 0){
					auditReportService.deleteByUUid(mp);
				}
				break; 
			}
			admission.setUuid(admissionUuid);
			String content = "";
			if(admissionUuid == null){
				String uuid = IdCreator.getId(16)+"_"+UUID.randomUUID().toString();
				content = uuid;
				admission.setUuid(uuid);
			}
			// 添加绑定的uuid入场
			String admissionBindUuid = membershipService.getAdmissionBindUuid(admission.getUser_id().trim());
			if(admissionBindUuid != null){
				membershipService.deleteAdmissionBindUuid(admission.getUser_id().trim());
			}
			Binding binding = new Binding();
			binding.setUser_id(admission.getUser_id());
			binding.setUuid(content);
			if(content != ""){
				membershipService.saveAdmissionBind(binding);
			}
			int count = membershipService.saveAdmission(admission);
			if(count > 0){
				membershipService.deleteAdmissionUuid(admission.getUser_id());
				return JsonResult.build("200", "添加成功！");
			} else {
				return JsonResult.build("205", "添加失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveMemberInfo
	 * 添加单条会员信息
	 * {"stores_id":"门店id","user_id":"会员id","user_name":"会员名","user_cards":"会员卡号","user_pic":"会员头像","user_type":"会员门店类型","member_type":"会员类型"}
	 * @return
	 */
	@RequestMapping(value="saveMemberInfo", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveMemberInfo(@RequestBody MemberShip memberShip) {
		try {
			String cards = "";
			if(memberShip.getUser_cards().endsWith(",")){
				cards = memberShip.getUser_cards().substring(0, memberShip.getUser_cards().length() - 1);
			} else {
				cards = memberShip.getUser_cards();
			}
			memberShip.setUser_cards(cards);
			memberShip.setMember_type("1");
			memberShip.setUser_id(memberShip.getUser_id());
			String content = "'"+memberShip.getUser_id()+"'";
			membershipService.deleteMembership(content);
			int count = membershipService.saveMemberShip2(memberShip);
			if(count > 0){
				return JsonResult.build("200", "添加成功！");
			} else {
				return JsonResult.build("205", "添加失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveNomemberImageLibrary
	 * 添加非会员图像库
	 * {"photo_time":"拍照时间", "pic_path":"图像路径"}
	 * @return
	 */
	@RequestMapping(value="saveNomemberImageLibrary", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveNomemberImageLibrary(@RequestBody NomemberImageLibrary nomemberImageLibrary) {
		try {
			int count = membershipService.saveNomemberImageLibrary(nomemberImageLibrary);
			if(count > 0){
				return JsonResult.build("200", "添加成功！");
			} else {
				return JsonResult.build("205", "添加失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/updateMembershipPic
	 * 修改会员头像
	 * {"user_id":"", "user_pic":"图像路径"}
	 * @return
	 */
	@RequestMapping(value="updateMembershipPic", produces="application/json;charset=utf-8")
	public HashMap<String, Object> updateMembershipPic(@RequestParam("user_id") String user_id,@RequestParam("user_pic") String user_pic, @RequestParam("face") String face) {
		try {
			MemberShip memberShip = new MemberShip();
			memberShip.setUser_id(user_id);
			memberShip.setUser_pic(user_pic);
			memberShip.setFace(face);
			membershipService.updateMembershipPic(memberShip);
			return JsonResult.build("200", "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试！");
		}
	}

	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/updateMembership
	 * 修改会员未曾会员
	 * {"user_id":"", "member_type":""}
	 * @return
	 */
	@RequestMapping(value="updateMembership", produces="application/json;charset=utf-8")
	public HashMap<String, Object> updateMembership(@RequestParam("user_id") String user_id,@RequestParam("member_type") String member_type) {
		try {
			MemberShip memberShip = new MemberShip();
			memberShip.setUser_id(user_id);
			memberShip.setMember_type(member_type);
			int count = membershipService.updateMembership(memberShip);
			if(count > 0){
				return JsonResult.build("200", "修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveManUserId
	 * {"user_id":"", "stores_id":"", "machine_id":""}
	 * 推送问候语
	 * @return
	 * {"user_id":"1b1c1393-1342-4c28-baf5-883bc1412f3e", "stores_id":"a6e8e858-c999-47d5-88ba-289b4f03a8ba", "machine_id":""}
	 */
	@RequestMapping(value="saveManUserId", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveManUserId(@RequestBody ManagementPic managementPic) {
		try {
			if((managementPic.getStores_id() == "" || managementPic.getStores_id().equals("")) || (managementPic.getMachine_id() == "" || managementPic.getMachine_id().equals(""))){
				String uuid = UUID.randomUUID().toString();
				return JsonResult2.build("200", "推送成功!", uuid);
			}
			if(managementPic.getUser_id().trim() != null && managementPic.getUser_id().trim() != "" && !managementPic.getUser_id().trim().equals("")){
				Admission admission = new Admission();
				admission.setUser_id(managementPic.getUser_id());
				admission.setStores_id(managementPic.getStores_id());
				Greetings nonmemberNamePush = membershipService.getNamePush(admission);
				String exclusive = membershipService.getExclusive(admission);
				if(nonmemberNamePush != null){
					String memberName = membershipService.getMemberName(admission);	// 查询会员名称
					if(exclusive != null){
						String [] machine= {managementPic.getMachine_id()};
						JiGunag.pushMessage2(machine, exclusive, memberName);
					} else {
						String [] machine = {managementPic.getMachine_id()};
						JiGunag.pushMessage2(machine, nonmemberNamePush.getName().replace("{{name}}", memberName), memberName);
					}
				}
				return JsonResult2.build("200", "推送成功！", "");
			} else {
				String uuid = UUID.randomUUID().toString();
				return JsonResult2.build("200", "推送成功！", uuid);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			String uuid = UUID.randomUUID().toString();
			return JsonResult2.build("200", "推送成功！", uuid);
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/saveMachine
	 * 添加门店下面终端机的信息 
	 * @param {"machine_id":"","stores_id":"", "memory":"", "type":""}
	 * @return
	 */
	@RequestMapping(value="saveMachine", produces="application/json;charset=utf-8")
	public HashMap<String, Object> saveMachine(@RequestBody Machine machine) {
		try {
			String machineNameInfo = membershipService.getMachineNameInfo(machine); // 查询设置终端机名称
			if(machineNameInfo == null){
				return JsonResult.build("205", "添加失败，该门店不存在！");
			}
			String machineInfo = membershipService.getMachineInfo(machine);
			if (machineInfo != null) {
				return JsonResult.build("205", "添加失败，终端机id已存在");
			}
			List<Machine> getgetMachineMachineNameByStoresId = membershipService.getMachineMachineNameByStoresId(machine);
			int size = getgetMachineMachineNameByStoresId.size();
			//int num = 0;
			if (size < 1) {
				machine.setMachine_name(machineNameInfo + "-" + 1);
			} else {
				String[] machineNameInfo2 = membershipService.getMachineNameInfo2(machine);
				Integer count = null;
				for (String string : machineNameInfo2) {
					count = Integer.parseInt(string.substring(string.lastIndexOf("-") + 1));
				}
				int sum = (count + 1);
				//num = sum-1;
				machine.setMachine_name(machineNameInfo + "-" + sum);
			}
			/*Machine mmm = new Machine();
			mmm.setId(infoMapper.getId(machineNameInfo + "-" + num));
			Machine machi= membershipService.getMachineById(mmm);*/
			/*if(machi.getMachine_id() != null && machi.getMachine_id().indexOf(",") < 0){
				String content = null;
				if(machi.getMachine_id() != null){
					String[] split = machi.getMemory().split(",");
					if("0".equals(machine.getType())){
						content = ""+machine.getMemory()+","+split[1]+"";
					} else {
						content = ""+split[0]+","+machine.getMemory()+"";
					}
				}
				Machine m = new Machine();
				m.setMachine_id(machine.getMachine_id());
				m.setMemory(content);
				m.setId(infoMapper.getId(machineNameInfo + "-" + num));
				infoMapper.updateMachineMemory(m);
				Machine machineById = membershipService.getMachineById(m);
				return JsonResult.build("200", "添加成功！", machineById);
			} else {*/
				int count = membershipService.saveMachine(machine);
				Machine machine2 = new Machine();
				machine2.setId(machine.getId());
				machine2.setMemory(machine.getMemory());
				Machine machineById = membershipService.getMachineById(machine2);
				if (count > 0) {
					return JsonResult.build("200", "添加成功！", machineById);
				} else {
					return JsonResult.build("205", "添加失败，请重试！");
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/updateFolderReceived
	 * 终端机收到文件夹修改状态
	 * {"id":"1"}
	 * @return
	 */
	@RequestMapping(value="updateFolderReceived", produces="application/json;charset=utf-8")
	public HashMap<String, Object> updateFolderReceived(@RequestBody Machine machine) {
		try {
			int count = membershipService.updateFolderReceived(machine.getId());
			if(count > 0){
				return JsonResult.build("200", "服务器收到请求，修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试！");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/offLineWelcome
	 * 离线返回欢迎词
	 * {"machine_id":"0xbe1bf4da_0xbe1bf4c6"}
	 * @return
	 */
	@RequestMapping(value="offLineWelcome", produces="application/json;charset=utf-8")
	public HashMap<String, Object> offLineWelcome(String machine_id){
		try {
			String storesId = membershipService.getStoresId(machine_id);
			Object storesIdByName = membershipService.getStoresIdByName(storesId);
			if(storesIdByName != null){
				return JsonResult.build("200", "查询成功!", storesIdByName, storesId);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {
			return JsonResult.build("205", "查询失败!");
		}
	}
	
	/**
	 * http://192.168.5.223:8080/goodfield/web/ship/getExclusives
	 * {"stores_id":""}
	 * @return
	 */
	@RequestMapping(value="getExclusives", produces="application/json;charset=utf-8")
	public HashMap<String, Object> getExclusives(String stores_id){
		try {
			List<MemberShip> exclusives = membershipService.getExclusives(stores_id);
			if(exclusives.size() < 1){
				return JsonResult.build("200", "暂无数据!");
			}
			return JsonResult.build("200", "查询成功!", exclusives);
		} catch (Exception e) {
			return JsonResult.build("205", "查询失败!");
		}
	}
	
	
	
}
