package com.alibaba.service.isservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.listener.PutObjectProgressListener;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.PushStatus;
import com.alibaba.util.ParamInfo;

public interface JiGuangService {
	
	
	  //folder 表中的数据s
	   public   Folder selectFolder(String id,String status,String liveStatus) ;
   //普通推送
	Map<String, Object> pushFile(String  floderid) throws Exception ;

	void updatePushStatusByFolerid(String id);//
	
	//2 删除文件家的推送
	
	Map<String, Object> pushFileByDelFolder(String  floderid) throws Exception ;

	
	
	//根据flowid 和machine——id 来pushStatus 的状态
	void updatePushStatus(String machine_id, String folder_id);
    //终端机上线  调用这个接口
	HashMap<String, Object> onLine(String machine_id, String folder_id)throws Exception;
	
	
     //插入数据
	void insertPushStatus(String id, String machine_id);
     //修改问价夹的逻辑
	  Map<String, Object> pushFilebyMachine(String floderid,String machineId) throws Exception;
	
	
	//找找 朴实status 中的数据
	List<PushStatus> selectPushStatusByfloderId(String floerid);
	
	
	void updatePushstatus_machine_is_delete(String string, String id);
	// 根据终端机的id 来查找所有的数据
	 Map<String, Object> onLineByFolserId(Object object, String floderida) throws Exception ;
	
	 
	 Map<String, Object> updateFile(Folder folder) throws Exception;
	 
	 
	 //处理启用的推送
	public Map<String, Object> pushFileStop(String floderId)throws   Exception;
	
	
	//删除时候 推送
	public Map<String, Object> pushFileByDel(String id) throws Exception;
	
	
	//带进度条的上传
	public void fileUpload(Long totalSize,String userToken,
			String objectName,InputStream inputStream,PutObjectProgressListener putObjectProgressListener);
	
	//查找文件
	public List<Folder> selectFolderbyFolder(ParamInfo pInfo);
	
	
	//查文件夹的详情
	public Folder selectFolderbyFolderforFile(ParamInfo pInfo);
	
	//查找机器ids
	public String selectMachineIds(Folder folder);
	 

}