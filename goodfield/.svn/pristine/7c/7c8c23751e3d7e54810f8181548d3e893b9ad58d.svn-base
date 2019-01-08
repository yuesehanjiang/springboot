package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface FolderMapper {

	String getMachineIdss(Machine m);
	
	List<String> getNameExistence2(File file);	//  查询文件名是否已存在
	
	List<String> getNameExistence(File file);	//  查询文件名是否已存在
	
	List<File> deleteOssFile(File file);	//  批量删除文件的时候oss同步删除
	
	int deleteFolderById(Folder folder);	//  文件夹删除，文件夹里的文件同步删除
	
	List<String> getFolderName(Folder folder);	//  查询文件夹名称
	
	List<String> getFilesIdInfo(File file);	//  查询已推送的文件id
	
	int updateFileName(File file);	// 重命名
	
	String getFileSize(File file);	// 查询某个文件夹下面文件的大小
	
	Folder getMachineIds2(Folder folder);	// 查询所有文件夹下的终端机2
	
	String getFolderUnlimitedTime(Folder folder);	// 查询不限时间的文件夹
	String getFolderUnlimitedTime2();	// 查询不限时间的文件夹

	String getFolderContentType(ParamInfo pInfo);	// 查询该文件夹内容的类型
	
	Folder getFoldersByIds(ParamInfo pInfo);		// 查询文件夹所有的id 
	
	Folder getFolderDetailsTopInfo(ParamInfo pInfo);	// 没有文件的时候查询文件夹头部信息
	
	int updateFolderStatusInfos(ParamInfo pInfo);	// 根据结束时间批量修改文件夹播放状态
	
	List<Folder> getTimes();	// 查询所有时间段
	
	Folder getMachineIds();	// 查询所有文件夹下的终端机
	
	List<Role> getstoresNameMachine(ParamInfo pInfo);	// 查询三屏管理门店列表以及终端机
	
	List<Folder> getFoldersInfoNews(ParamInfo pInfo);	// 查询文件夹 
	
	int updateFolderStatus(Folder folder);	// 修改文件夹播放状态
	
	List<Machine> getMachineInfo(Machine m);	// 查询要推送的数据
	
	List<Role> getUserRoleRelationInfo(ParamInfo pInfo);	// 查询三屏管理门店列表信息
	
	Folder getFolderDetailsFilesInfo(ParamInfo pInfo);	// 查询文件夹详情以及文件列表
	
	int getCount(ParamInfo pInfo);  // 查询总数
	
	int getCount2(ParamInfo pInfo);  // 查询总数
	
	List<Folder> getFolderTimes(Folder folder);	// 查询所有文件夹下的时间段
	
	List<Folder> getFolderTimes2(Folder folder);	// 查询所有文件夹下的时间段 修改
	
	List<Folder> getRoleIdInfoRepeat(Folder folder);	// 查询门店不能重复
	
	int saveFolder(Folder folder);	// 创建文件夹
	
	int deleteFolder(Folder folder);	// 删除文件
	
	Folder getFolderById(Folder folder);	// 查询要修改的文件夹 
	
	int updateFolder(Folder folder);	// 编辑文件夹
	
	int deleteFolderFiles(File file);	// 批量删除文件
	
	int uploadFiles(File lists);	  // 上传文件

	String getFolderUnlimitedTimeNoTime(Folder folder);
	
}
