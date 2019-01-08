package com.yun.utils;

import java.io.File;
import java.util.List;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

public class OosFileupload {
	static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	static String accessKeyId = "LTAIncw5TlYKR4SL";
	static String accessKeySecret = "YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui";
	static String bucketName = "tianjiulongoa1";

	// 创建OSSClient实例
	static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

	// 创建bucket
	public static void createBucket(String bucketName) {
		if (bucketName != null) {
			ossClient.createBucket(bucketName);
		}
	}

	// 列举bucket
	public static List<Bucket> listBucket() {
		List<Bucket> buckets = ossClient.listBuckets();
		if (buckets == null) {
			return null;
		}
		return buckets;
	}

	// 删除bucket
	public static void deleteBucket(String bucketName) {
		if (bucketName != null) {
			ossClient.deleteBucket(bucketName);
		}
	}

	// 判断Bucket是否存在
	public static boolean checkBucket(String bucketName) {
		if (bucketName == null) {
			return false;
		}
		boolean exists = ossClient.doesBucketExist(bucketName);
		return exists;
	}

	// 上传本地文件
	public static void upload(String bucketName, String key, File file) {
		if (bucketName != null & key != null & file != null) {
			ossClient.putObject(bucketName, key, file);
		}
	}

	// 下载到本地文件
	public static void download(String bucketName, String key, File file) {
		if (bucketName != null & key != null & file != null) {
			ossClient.getObject(new GetObjectRequest(bucketName, key), file);
		}
		ossClient.shutdown();
	}

	// 判断文件是否存在
	public static boolean checkObject(String bucketName, String key) {
		if (bucketName != null & key != null) {
			boolean found = ossClient.doesObjectExist(bucketName, key);
			ossClient.shutdown();
			return found;
		}
		return false;
	}

	// 列举bucket下的Object,maxKeys设置最大返回条数
	public static List<OSSObjectSummary> getListObjects(String bucketName, int maxKeys) {
		if (bucketName != null & !"".equals(maxKeys)) {
			ObjectListing objectListing = ossClient
					.listObjects(new ListObjectsRequest(bucketName).withMaxKeys(maxKeys));
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			ossClient.shutdown();
			return sums;
		}
		return null;
	}

	// 返回指定前缀的object
	public static List<OSSObjectSummary> getlistObjectByPrefix(String bucketName, String keyMarker) {
		if (bucketName != null & !"".equals(keyMarker)) {
			ObjectListing objectListing = ossClient
					.listObjects(new ListObjectsRequest(bucketName).withMarker(keyMarker));
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			return sums;
		}
		ossClient.shutdown();
		return null;
	}

	// 分页获取所有Object
	public static List<OSSObjectSummary> getlistObjectByPage(String bucketName, int maxKeys) {
		ObjectListing objectListing = null;
		String nextMarker = "";
		do {
			objectListing = ossClient
					.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			nextMarker = objectListing.getNextMarker();
			return sums;
		} while (objectListing.isTruncated());
	}

	// 分页获取所有特定Object后的Object
	public static List<OSSObjectSummary> getListObjectForObejctByPage(String bucketName, int maxKeys,
			String nextMarker) {
		ObjectListing objectListing = null;
		do {
			objectListing = ossClient
					.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			nextMarker = objectListing.getNextMarker();
			return sums;
		} while (objectListing.isTruncated());
	}

	// 分页所有获取指定前缀的Object
	public static List<OSSObjectSummary> getListObjectForPrefixByPage(String bucketName, int maxKeys, String nextMarker,
			String keyPrefix) {
		ObjectListing objectListing = null;
		do {
			objectListing = ossClient.listObjects(new ListObjectsRequest("<bucketName>").withPrefix(keyPrefix)
					.withMarker(nextMarker).withMaxKeys(maxKeys));
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			for (OSSObjectSummary s : sums) {
				System.out.println("\t" + s.getKey());
			}
			nextMarker = objectListing.getNextMarker();
			return sums;
		} while (objectListing.isTruncated());
	}

	// 删除单个文件
	public static void deleteProject(String bucketName, String key) {
		ossClient.deleteObject(bucketName, key);
	}
	
	public static void main(String[] args) {
        
	    deleteProject("tianjiulongoa", "1537180482442middle.jpg");
	    
    }
	
	
	
	
	// 删除多个文件
	public static void deleteProjects(String bucketName, List<String> keys) {
		DeleteObjectsResult deleteObjectsResult = ossClient
				.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
		List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
	}

	// 获取所有object
	public static ObjectListing getAllObject(String bucketName) {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
		ObjectListing listing = ossClient.listObjects(listObjectsRequest);
		return listing;
	}
  
}
