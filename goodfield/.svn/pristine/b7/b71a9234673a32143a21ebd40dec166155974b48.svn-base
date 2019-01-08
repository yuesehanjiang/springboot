package com.alibaba.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文件实体
 * @author mayn
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)  
public class File implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String fid;			// 文件id
	private String fileName;	// 文件名
	private String filePath;	// 文件路径
	private String folderId;	// 关联文件夹id
	private String file_size;	// 文件大小
	private String user_id;
	private String userToken;
	private String push_is;
	private Date create_time;
	private String terminal_received;	// 0未收到1收到
	private String machine_id;
	private String content;
	private String loginStatus;
	private String originalName;
	private String replay;
	
	
	private String  fileSize; 
	private String  pushIs;
	
     private String gymAddr;
	
	public static void main(String[] args) {
		//new File().setContent("").setLoginStatus("");
	}
	
}
