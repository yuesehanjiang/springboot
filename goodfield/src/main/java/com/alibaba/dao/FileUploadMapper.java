package com.alibaba.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.experimental.PackagePrivate;

import org.apache.ibatis.annotations.Param;

import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.UserFolder;

public interface FileUploadMapper {
	// 改变 终端机的状态
	void updatePushStatus(@Param("floder") String machineId, @Param("staus") String staus);

	// 删除pushtatus中的 machineId 和 folderId
	void deletePushStatus(@Param("machieId") String machieId, @Param("folderId") String folderId);

	// 该问价夹表的数据s
	void updateFolder_live_status(@Param("folderid") String folderid);

	// 得到文件夹中文件的数量
	Integer selectCountFile(@Param("folderid") String folderId);

	// 改吧pushstatu中
	void update_isDelete_statusi(@Param("machineId") String machineId, @Param("folderid") String floderid);

	// 启用时候的 推送s
	Folder selectFileByFloderid(@Param("floderId") String floderId, @Param("machineId") Object object);

	// gaifile 表中数据
	void updateFile_pushIs(@Param("folderId") String folderId, @Param("pushis") String pushis);

	// 改floder表的状态
	void updateFolderStatus(@Param("folderId") String floderid, @Param("status") String status,
			@Param("isTing") String isTing);

	// 根据 门店的id 来查找
	List<Folder> selectFolderBYStoreid(@Param("gymAddr") String stores_id, @Param("screenType") String type,
			@Param("floderId") String floderId, @Param("unTime") String unTime);

	// 让pushtatus 表中的isting改变
	void updatePushStatsIsting(@Param("floderid") String floderid, @Param("isTing") String string);

	// 拿到所有的filePath
	List<String> selectFileByfolderId(@Param("floderid") String folderid, @Param("fid") String fid);

	// 找存在的问价夹
	List<Folder> selectFolerIsLive(@Param("liveStauts") String liveStatus);

	// 查出过期数据
	List<LinkedHashMap<String, Object>> selectDateFolder();

	// 终端机删除文件时候的 监控
	void insertPushstaus(@Param("machine_id") String machine_id, @Param("folder_id") String folder_id,
			@Param("type") String type, @Param("is_terminal_del") String is_terminal_del);

	// 查找被删除的数据 给前端使用
	List<LinkedHashMap<String, Object>> selectMacheIdAndName(@Param("folder_id") String folder_id,
			@Param("type") String type, @Param("userId") String userId);

	// 该machine表数据
	void pushTerminaIsDel(@Param("floderId") String floderId, @Param("machineId") String machineId);

	// 查找所有被删除的数据
	List<LinkedHashMap<String, Object>> selectByDelFolder(@Param("type") String type, @Param("user_id") String user_id,
			@Param("folderId") String floderId);

	// 处理我是不是看了已经删除的弹框
	void updatePushStatustoIdo(@Param("type") String string, @Param("folder_id") String folder_id);

	// 没有读的已经删除的数据

	List<LinkedHashMap<String, Object>> selectByDelFolderGolal(@Param("user_id") String user_id,
			@Param("idian") int idian);

	// 修改我点击的状态
	void updateIsDian(@Param("id") Integer id);

	// 删除stuts 为1 的 数据
	void delete_by_staus(@Param("status") int status);

	// 上传文件
	int upload(File file);

	// 批量插入图片的上chuang
	int uploadByList(@Param("list") List<File> lists);

	// 文件jia 的名字 是不是重复
	List<String> selectFolerIsLiveByFloderId(Folder folder);

	//看文件名字 是不是重复
	List<String> selectFileIsLiveByFloderIdAndFolderId(Folder folder);

	
	
	

}
