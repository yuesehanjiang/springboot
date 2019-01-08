package com.alibaba.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 阿里云对象存储oss工具类
 * 
 * @author mayn 
 *
 */
public class OSSUtil {

	private static OSSClient ossClient = new OSSClient(
			"http://oss-cn-shenzhen.aliyuncs.com", "LTAIj6VwurVuWjsD",
			"MHIXLKKUXWFMCzuewlMkBgioaAjhGN");

	/**
	 * 创建存储空间
	 * @param ossClient OSS连接
	 * @param bucketName 存储空间
	 * @return
	 */
	public static String createBucketName(String bucketName) {
		// 存储空间
		final String bucketNames = bucketName;
		if (!ossClient.doesBucketExist(bucketName)) {
			// 创建存储空间
			Bucket bucket = ossClient.createBucket(bucketName);
			System.out.println("创建成功！");
			return bucket.getName();
		}
		return bucketNames;
	}

	/**
	 * 删除存储空间buckName
	 * @param ossClient oss对象
	 * @param bucketName 存储空间
	 */
	public static void deleteBucket(OSSClient ossClient, String bucketName) {
		ossClient.deleteBucket(bucketName);
		System.out.println("删除成功！");
	}

	/**
	 * 创建文件夹
	 * @param client
	 */
	public static void createFolder(OSSClient client, String folderName) {
		client.putObject("tianjiulongoa1", folderName, new ByteArrayInputStream(new byte[0]));
		System.out.println("创建成功！");
	}

	/**
	 * 删除文件夹
	 * @param client
	 */
	public static void deleteFolder(String folderName) {
		boolean isObjectExist = ossClient.doesObjectExist("tianjiulongoa1", folderName);
		if (isObjectExist) {
			ossClient.deleteObject("tianjiulongoa11", folderName);
			System.out.println("删除成功！");
		} else {
			System.out.println("文件夹不存在，无法删除！");
		}

	}

	/**
	 * @Description: OSS单文件上传
	 * @param file
	 * @param fileType
	 *            文件后缀
	 * @return String 文件地址
	 */
	public static void uploadFile(InputStream filePath) {
//		ossClient.putObject("tianjiulongoa1", "goodfield/", filePath);
		ossClient.putObject("tianjiulongoa1", "goodfield/", filePath, null);
		ossClient.shutdown();
	}

	/**
	 * @MethodName: deleteFile
	 * @Description: 删除指定目录下面的文件
	 * @param fileUrl
	 *            需要删除的文件url
	 */
	public static void deleteFile(String filePath, String fileName) {
		ossClient.deleteObject("tianjiulongoa1", "1537180328148middle.jpg");
		System.out.println("删除成功！");
	}

	
	
	/**
	 * 获取oss某个目录下的所有文件信息和文件个数
	 * 
	 * @param prefix
	 */
	public static String getFileInfo(String prefix) {
		// 构造ListObjectsRequest请求
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest("tianjiulongoa1");
		// 列出fun目录下的所有文件和文件夹
		listObjectsRequest.setPrefix(prefix);
		ObjectListing listing = ossClient.listObjects(listObjectsRequest);
		// OSSobjectSummary下包含目录下所有的文件，不包含子目录
		List<OSSObjectSummary> objectSummaries = listing.getObjectSummaries();
		int size = (objectSummaries.size() - 1);
		System.err.println("=================文件个数" + size); // 获取某个文件夹下的文件个数
		String name = "";
		for (OSSObjectSummary objectSummary : objectSummaries) { // 循环获取文件信息
			String key = objectSummary.getKey();
			System.err.println(key);
			name = key;
		}
		return name;
	}
	

	/**
	 * 上传图片至OSS
	 * @param ossClient  oss连接
	 * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
	 * @param bucketName  存储空间
	 * @param folder 模拟文件夹名 如"qj_nanjing/"
	 * @return String 返回的唯一MD5数字签名
	 * @throws IOException 
	 * */
	public static  String uploadObject2OSS(MultipartFile  file, String bucketName, String folder)  {
		String resultStr = null;
		try {
			//以输入流的形式上传文件
			InputStream is = file.getInputStream();
			//文件名
			String fileName = file.getOriginalFilename().replace("~", "").replace("%", "").replace("{", "").replace("}", "").replace("(", "").
					replace(")", "").replace("【", "").replace("】", "").replace("[", "").replace("]", "").replace("&", "").replace("^", "").replace("!", "")
					.replace("@", "").replace("#", "").replace("`", "").replace("=", "").replace("+", "").replace(";", "").replace(",", "").replace("；", "")
					.replace("‘", "").replace("。", "").replace("，", "").replace("·", "").replace("、", "").replace(" ", "");
			//文件大小
			Long fileSize = file.getSize();
			//创建上传Object的Metadata   
			ObjectMetadata metadata = new ObjectMetadata();
			//上传的文件的长度
			metadata.setContentLength(is.available());  
			//指定该Object被下载时的网页的缓存行为
			metadata.setCacheControl("no-cache"); 
			//指定该Object下设置Header
			metadata.setHeader("Pragma", "no-cache");  
			//指定该Object被下载时的内容编码格式
			metadata.setContentEncoding("utf-8");  
			//文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
			//指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");  
			//上传文件   (上传文件流的形式)
			PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);  
			//解析结果
			resultStr = putResult.getETag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}
	
	
	/**
	 * 上传图片至OSS
	 * @param ossClient  oss连接
	 * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
	 * @param bucketName  存储空间
	 * @param folder 模拟文件夹名 如"qj_nanjing/"
	 * @return String 返回的唯一MD5数字签名
	 * @throws IOException 
	 * */
	public static  String uploadObject2OSSs(MultipartFile[]  file, String bucketName, String folder)  {
		String resultStr = null;
		try {
			for (int i = 0; i < file.length; i++) {
				MultipartFile files = file[i];
				//以输入流的形式上传文件
				InputStream is = files.getInputStream();
				//文件名
				String fileName = files.getOriginalFilename().replace("~", "").replace("%", "").replace("{", "").replace("}", "").replace("(", "").
						replace(")", "").replace("【", "").replace("】", "").replace("[", "").replace("]", "").replace("&", "").replace("^", "").replace("!", "")
						.replace("@", "").replace("#", "").replace("`", "").replace("=", "").replace("+", "").replace(";", "").replace(",", "").replace("；", "")
						.replace("‘", "").replace("。", "").replace("，", "").replace("·", "").replace("、", "").replace(" ", "");
				//文件大小
				Long fileSize = files.getSize();
				//创建上传Object的Metadata   
				ObjectMetadata metadata = new ObjectMetadata();
				//上传的文件的长度
				metadata.setContentLength(is.available());  
				//指定该Object被下载时的网页的缓存行为
				metadata.setCacheControl("no-cache"); 
				//指定该Object下设置Header
				metadata.setHeader("Pragma", "no-cache");  
				//指定该Object被下载时的内容编码格式
				metadata.setContentEncoding("utf-8");  
				//文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
				//指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
				metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");  
				//上传文件   (上传文件流的形式)
				PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);  
				//解析结果
				resultStr = putResult.getETag();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}
	
}
