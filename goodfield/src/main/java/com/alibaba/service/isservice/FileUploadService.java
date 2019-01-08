package com.alibaba.service.isservice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.UserFolder;

public interface FileUploadService {
	public Map<String, Object> startMachine(String floderid, String status)
			throws Exception;

	public Map<String, Object> pushByMachine(String machineId);

	// 插入前的修改

	public void insertDelPushstatus(String folderId, String isTing);

	public void deletePushStatus(String machieId, String folderId);

	// 该 folder live_status的 数据
	public void updateFolder_live_status(String id);

	// 得到文件夹中文件的 数量
	public Integer selectCountFile(String folderId);

	// 处理启用的逻辑
	public Map<String, Object> stopMachine(String floderId, String string)
			throws Exception;

	// 推送按钮的前期准备
	public void insertDelPushstatus_updatePushis(String folderId);

	// 该便 file 中 push is的数据
	public void updateFile_pushIs(String folderId, String pushis);
    //删除文件
	public void delFiles(File file);
    //根据门店的 id 查找 文件
	public List<Folder> selectFolderBYStoreid(String stores_id,String type,String floderId,String unTime);

	
	  //拿到所有 的Filepath的名字
	public List<String> selectFileByfolderId(String folderid,String fid);

	
	//找存在的文件夹
	public List<Folder> selectFolerIsLive(String string);
  
	
	//删除过期 半个月的 数据
	public void delexpireDateFolder();
   //终端机 删除文件
	
	public HashMap<String, Object> terminaIsDel(String machine_id,
			String folder_id, String type);

	
	//前端页面  确定是不是已经删除
	public List<LinkedHashMap<String,Object>> selectTerminaIsDel(String folder_id,
			String type,String userId);

	
	//终端机把文件删除时候的推送
	public Map<String, Object> pushTerminaIsDel(
			UserFolder userFolder);

	
	//查找所有被删除的数据
	public List<LinkedHashMap<String, Object>> selectByDelFolder(String type,
			String user_id,String floderId);

	
	//没有读的被删除的文件夹
	public List<LinkedHashMap<String, Object>> selectByDelFolderGolal(
			String user_id, int i);

	
	//修改点击的状态
	public void updateIsDian(List<LinkedHashMap<String, Object>> maps);

	
	
	//上传文件
	public int upload(File file);

	
	//批量插入图片的上传
	public int uploadByList(List<File> lists);

	
	
	//文件的名字是不是 chongfu
	public List<String> selectFolerIsLiveByFloderId(Folder folder);

}