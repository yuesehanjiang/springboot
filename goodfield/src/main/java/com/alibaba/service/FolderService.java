package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.FolderMapper;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.Role;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.ParamInfo;
import com.yun.utils.OosFileupload;

@Service
public class FolderService {

	@Autowired
	private FolderMapper folderMapper;
	
	@Autowired
	JiGuangService  jiGuangService;
	
	@Autowired
	FileUploadService fileUploadService;
	
	/**
	 * 查询文件名是否已存在
	 * @param folder
	 * @return
	 */
	public String getMachineIdss(Machine m) {
		return folderMapper.getMachineIdss(m);
	}
	/**
	 * 查询文件名是否已存在
	 * @param folder
	 * @return
	 */
	public List<String> getNameExistence2(File file) {
		return folderMapper.getNameExistence2(file);
	}
	/**
	 * 查询文件名是否已存在
	 * @param folder
	 * @return
	 */
	public List<String> getNameExistence(File file) {
		return folderMapper.getNameExistence(file);
	}
	
	/**
	 * 批量删除文件的时候oss同步删除
	 * @param folder
	 * @return
	 */
	public List<File> deleteOssFile(File file) {
		return folderMapper.deleteOssFile(file);
	}
	
	/**
	 * 件夹删除，文件夹里的文件同步删除
	 * @param folder
	 * @return
	 */
	public int deleteFolderById(Folder folder) {
		//删除oos中的文件
	    List<String> filePath=	fileUploadService.selectFileByfolderId(folder.getId(),null);
		  for (int i = 0; i < filePath.size(); i++) {
			 OosFileupload.deleteProject("advertisa", 
					filePath.get(i).substring(filePath.get(i).lastIndexOf("/")+1));
		}
		
		
		//修改  `push_status`数据   把 isdat  设为已经删除
    	jiGuangService.updatePushStatusByFolerid(folder.getId());
	//改变 floder 表的值
    	fileUploadService.updateFolder_live_status(folder.getId()); 
    	fileUploadService.updateFile_pushIs(folder.getId(), "0");
		return folderMapper.deleteFolderById(folder);
	}
	
	/**
	 * 查询文件夹名称id
	 * @param folder
	 * @return
	 */
	public List<String> getFolderName(Folder folder) {
		return folderMapper.getFolderName(folder);
	}
	
	/**
	 * 查询已推送的文件id
	 * @param folder
	 * @return
	 */
	public List<String> getFilesIdInfo(File file) {
		return folderMapper.getFilesIdInfo(file);
	}
	
	/**
	 * 重命名
	 * @param folder
	 * @return
	 */
	public int updateFileName(File file) {
		return folderMapper.updateFileName(file);
	}
	
	/**
	 * 查询某个文件夹下面文件的大小
	 * @param folder
	 * @return
	 */
	public String getFileSize(File file) {
		return folderMapper.getFileSize(file);
	}
	
	/**
	 * 查询所有文件夹下的终端机2
	 * @param folder
	 * @return
	 */
	public Folder getMachineIds2(Folder folder) {
		return folderMapper.getMachineIds2(folder);
	}
	
	/**
	 * 查询不限时间的文件夹
	 * @param folder
	 * @return
	 */
	public String getFolderUnlimitedTime(Folder folder) {
		return folderMapper.getFolderUnlimitedTime(folder);
	}
	/**
	 * 查询不限时间的文件夹
	 * @param folder
	 * @return
	 */
	public String getFolderUnlimitedTime2() {
		return folderMapper.getFolderUnlimitedTime2();
	}
	
	/**
	 * 查询该文件夹内容的类型
	 * @param folder
	 * @return
	 */
	public String getFolderContentType(ParamInfo pInfo) {
		return folderMapper.getFolderContentType(pInfo);
	}
	
	/**
	 * 查询文件夹所有的id 
	 * @param folder
	 * @return
	 */
	public Folder getFoldersByIds(ParamInfo pInfo) {
		return folderMapper.getFoldersByIds(pInfo);
	}
	
	/**
	 * 没有文件的时候查询文件夹头部信息
	 * @param folder
	 * @return
	 */
	public Folder getFolderDetailsTopInfo(ParamInfo pInfo) {
		return folderMapper.getFolderDetailsTopInfo(pInfo);
	}
	
	/**
	 * 根据结束时间批量修改文件夹播放状态
	 * @param folder
	 * @return
	 */
	public int updateFolderStatusInfos(ParamInfo pInfo) {
		return folderMapper.updateFolderStatusInfos(pInfo);
	}
	
	/**
	 * 查询所有时间段
	 * @param folder
	 * @return
	 */
	public List<Folder> getTimes() {
		return folderMapper.getTimes();
	}
	
	/**
	 * 查询所有文件夹下的终端机
	 * @param folder
	 * @return
	 */
	public Folder getMachineIds() {
		return folderMapper.getMachineIds();
	}
	
	/**
	 * 查询三屏管理门店列表以及终端机
	 * @param folder
	 * @return
	 */
	public List<Role> getstoresNameMachine(ParamInfo pInfo) {
		return folderMapper.getstoresNameMachine(pInfo);
	}
	
	/**
	 * 查询文件夹
	 * @param folder
	 * @return
	 */
	public List<Folder> getFoldersInfoNews(ParamInfo pInfo){
		return folderMapper.getFoldersInfoNews(pInfo);
	}
	
	/**
	 * 修改文件夹播放状态
	 * @param folder
	 * @return
	 */
	public int updateFolderStatus(Folder folder){
		return folderMapper.updateFolderStatus(folder);
	}
	
	/**
	 * 查询要推送的数据
	 * @param folder
	 * @return
	 */
	public List<Machine> getMachineInfo(Machine m){
		return folderMapper.getMachineInfo(m);
	}
	
	/**
	 * 查询三屏管理门店列表信息
	 * @param folder
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo pInfo){
		return folderMapper.getUserRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询文件夹详情以及文件列表
	 * @param folder
	 * @return
	 */
	public Folder getFolderDetailsFilesInfo(ParamInfo pInfo){
		return folderMapper.getFolderDetailsFilesInfo(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param folder
	 * @return
	 */
	public int getCount(ParamInfo pInfo){
		return folderMapper.getCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param folder
	 * @return
	 */
	public int getCount2(ParamInfo pInfo){
		return folderMapper.getCount2(pInfo);
	}
	
	/**
	 * 查询所有文件夹下的时间段
	 * @param folder
	 * @return
	 */
	public List<Folder> getFolderTimes(Folder folder){
		return folderMapper.getFolderTimes(folder);
	}
	
	/**
	 * 查询所有文件夹下的时间段   修改
	 * @param folder
	 * @return
	 */
	public List<Folder> getFolderTimes2(Folder folder){
		return folderMapper.getFolderTimes2(folder);
	}
	
	/**
	 * 查询门店不能重复
	 * @param folder
	 * @return
	 */
	public List<Folder> getRoleIdInfoRepeat(Folder folder){
		return folderMapper.getRoleIdInfoRepeat(folder);
	}
	
	/**
	 * 创建文件夹
	 * @param folder
	 * @return
	 */
	public int saveFolder(Folder folder){
		return folderMapper.saveFolder(folder);
	}
	
	/**
	 * 删除文件夹
	 * @param folder
	 * @return
	 */
	public int deleteFolder(Folder folder){
		return folderMapper.deleteFolder(folder);
	}
	
	/**
	 * 查询要修改的文件夹
	 * @param folder
	 * @return
	 */
	public Folder getFolderById(Folder folder){
		return folderMapper.getFolderById(folder);
	}
	
	/**
	 * 编辑文件夹
	 * @param folder
	 * @return
	 */
	public int updateFolder(Folder folder){
		return folderMapper.updateFolder(folder);
	}
	
	/**
	 * 批量删除文件
	 * @param folder
	 * @return
	 */
	public int deleteFolderFiles(File file){
		return folderMapper.deleteFolderFiles(file);
	}
	
	/**
	 * 上传文件
	 * @param folder
	 * @return
	 */
	public int uploadFiles(File lists){
		return folderMapper.uploadFiles(lists);
	}
	
	
	public String getFolderUnlimitedTimeNoTime(Folder folder) {
		// TODO Auto-generated method stub
		return folderMapper.getFolderUnlimitedTimeNoTime(folder);
	}
	
}
