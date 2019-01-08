package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.Admission;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.NomemberImageLibrary;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface AuditReportMapper {

	int delNomemberImageLibrary(String imageUrl);	// 删除无效的图片地址
	
	int deleteByUUid(ManagementPic managementPic);	// 删除吧
	
	List<String> getTime(ManagementPic managementPic);	// 查询时间
	
	int updateUserInTime(Admission managementPic);	// 修改时间
	
	
	
	
	
	
	
	
	
	
	
	
	int updateJustTheImage(ManagementPic managementPic);	// 再次修改调整图像
	
	ManagementPic getadjustTheImageById(ManagementPic managementPic);	// 点击调整图像的时候传参数过去
	
	List<ParamInfo> getAuditReportInfo(ParamInfo pInfo);	// 查询稽核报告
	List<ParamInfo> getAuditReportUnread(ParamInfo pInfo);	// 查询是否已读
	
	int adjustTheImage(ManagementPic managementPic);	// 调整完毕
	
	List<Role> getUserRoleRelationInfo(ParamInfo pInfo);	// 查询稽核报告门店列表信息
	
	int getCount(ParamInfo pInfo);	// 查询总数
	
	int getCount2(ParamInfo pInfo);	// 查询总数
	
	int getCount3(ParamInfo pInfo);	// 查询总数
	
	List<NomemberImageLibrary> getNomemberImageLibraryInfo(ParamInfo pInfo);	// 查询非会员图像库
	
	List<NomemberImageLibrary> getNomemberImageInterval(ParamInfo pInfo);	// 查询30分钟以内的调整图像库

	int updateAdmissionValidMember(ParamInfo pInfo);	// 调整完毕，修改状态

	int updatemanagementPicPath(ParamInfo pInfo);		// 调整完毕，修改状态
	
}
