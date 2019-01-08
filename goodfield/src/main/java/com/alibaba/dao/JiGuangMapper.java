package com.alibaba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.pojo.Folder;
import com.alibaba.pojo.PushStatus;
import com.alibaba.pojo.UserFolder;
import com.alibaba.util.ParamInfo;

public interface JiGuangMapper {

	Folder selectFileByFloderid(@Param("floderid")String floderid,@Param("machineId")String machineId);  //根据文件夹id 查找可以推送的 文件

	List<String> selectmahineByFloderId(@Param("floderid")String floderid);  //查到所有可以推送的 终端机

	void updatePushStatusByFolerid(@Param("folerid")String folerid);  //修改pushStaus 表的数据
	//根据flowid 和machine——id 来pushStatus 的状态
	void updatePushStatus(@Param("machine_id")String machine_id, 
			@Param("folder_id")String folder_id);
     //看是不是被删除了
	Integer selectStatustoPushStatus(@Param("machine_id")String machine_id,
			@Param("folder_id")String folder_id);
    //看有没有推送成功
	Integer selectStatustoPushStatus1(@Param("machine_id")String machine_id, 
			@Param("folder_id")String folder_id);
    //改为1 
	void updatePushStatusto1(@Param("machine_id")String machine_id, 
			@Param("folder_id")String folder_id);
      //根据终端机的id 查找所有的 数据
	List<Map> selectAll(@Param("machine_id")String machine_id);
   //插入数据
	void insertFolidemachineid(@Param("folder_id")String floderid,
			@Param("machine_id")String machine_id,@Param("isTing") String isTing);
    //g根究  floderid  找表中的所有的数据
	List<PushStatus> selectPushStatusByfloderId(@Param("folder_id")String floerid);
     //找找 floder 表中的数据+
	Folder selectFolder(@Param("folder_id")String id,
			@Param("status")String status,@Param("liveStaus")String liveStaus);
  //给表示  表已经删除
	void updatePushstatus_machine_is_delete(@Param("machieId")String machieId, 
			
			@Param("floderid")String floderid);
   //根据文件夹的id 查找所有pushstaus 数据
	List<Map> selectAllbyFlodrid(@Param("floderid")String floderida);

	
	//删除文件时候的推送 包括停播
	Folder selectFileByFloderidtoDel(@Param("floderid")String floderid, 
			@Param("machineId")Object object);
     
	
	//终端机删除文件时候的推送
	Folder selectFolderByTypeAndFolder(UserFolder userFolder);
    //查找文件
	List<Folder> selectFolderbyFolder(ParamInfo pInfo);

	
	//查文件夹的详情
	Folder selectFolderbyFolderforFile(ParamInfo pInfo);

	
	//找机器ids
	String selectMachineIds(Folder folder);



	

}
