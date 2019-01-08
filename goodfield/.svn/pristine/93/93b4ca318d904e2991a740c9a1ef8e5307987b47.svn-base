package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.AuditReportMapper;
import com.alibaba.pojo.Admission;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.NomemberImageLibrary;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Service
public class AuditReportService {

	@Autowired
	private AuditReportMapper auditReportMapper;
	
	/**
	 * 删除无效的图片地址
	 * @param pInfo
	 * @return
	 */
	public int delNomemberImageLibrary(String imageUrl){
		return auditReportMapper.delNomemberImageLibrary(imageUrl);
	}
	
	/**
	 * 删除吧
	 * @param pInfo
	 * @return
	 */
	public List<ParamInfo> getAuditReportUnread(ParamInfo pInfo){
		return auditReportMapper.getAuditReportUnread(pInfo);
	}
	
	/**
	 * 删除吧
	 * @param pInfo
	 * @return
	 */
	public int deleteByUUid(ManagementPic managementPic){
		return auditReportMapper.deleteByUUid(managementPic);
	}
	
	/**
	 * 查询时间
	 * @param pInfo
	 * @return
	 */
	public List<String> getTime(ManagementPic managementPic){
		return auditReportMapper.getTime(managementPic);
	}
	
	/**
	 * 修改时间
	 * @param pInfo
	 * @return
	 */
	public int updateUserInTime(Admission managementPic){
		return auditReportMapper.updateUserInTime(managementPic);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 再次修改调整图像
	 * @param pInfo
	 * @return
	 */
	public int updateJustTheImage(ManagementPic managementPic){
		return auditReportMapper.updateJustTheImage(managementPic);
	}
	
	/**
	 * 点击调整图像的时候传参数过去
	 * @param pInfo
	 * @return
	 */
	public ManagementPic getadjustTheImageById(ManagementPic managementPic){
		return auditReportMapper.getadjustTheImageById(managementPic);
	}

	/**
	 * 调整完毕
	 * @param pInfo
	 * @return
	 */
	public int adjustTheImage(ManagementPic managementPic){
		return auditReportMapper.adjustTheImage(managementPic);
	}
	
	/**
	 * 查询稽核报告
	 * @param pInfo
	 * @return
	 */
	public List<ParamInfo> getAuditReportInfo(ParamInfo pInfo){
		return auditReportMapper.getAuditReportInfo(pInfo);
	}
	
	/**
	 * 查询稽核报告门店列表信息
	 * @param pInfo
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo pInfo){
		return auditReportMapper.getUserRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public int getCount(ParamInfo pInfo){
		return auditReportMapper.getCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public int getCount2(ParamInfo pInfo){
		return auditReportMapper.getCount2(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public int getCount3(ParamInfo pInfo){
		return auditReportMapper.getCount3(pInfo);
	}
	
	/**
	 * 查询非会员图像库
	 * @param pInfo
	 * @return
	 */
	public List<NomemberImageLibrary> getNomemberImageLibraryInfo(ParamInfo pInfo){
		return auditReportMapper.getNomemberImageLibraryInfo(pInfo);
	}
	
	/**
	 * 查询30分钟以内的调整图像库
	 * @param pInfo
	 * @return
	 */
	public List<NomemberImageLibrary> getNomemberImageInterval(ParamInfo pInfo){
		return auditReportMapper.getNomemberImageInterval(pInfo);
	}
	
	/**
	 * 调整完毕，修改状态
	 * @param pInfo
	 * @return
	 */
	public int updateAdmissionValidMember(ParamInfo pInfo){
		return auditReportMapper.updateAdmissionValidMember(pInfo);
	}
	
	/**
	 * 调整完毕，修改状态
	 * @param pInfo
	 * @return
	 */
	public int updatemanagementPicPath(ParamInfo pInfo){
		return auditReportMapper.updatemanagementPicPath(pInfo);
	}
	
}
