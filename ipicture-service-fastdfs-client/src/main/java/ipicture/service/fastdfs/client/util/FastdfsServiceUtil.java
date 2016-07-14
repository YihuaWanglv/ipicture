package ipicture.service.fastdfs.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public class FastdfsServiceUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FastdfsServiceUtil.class);
	
	/* 错误码 100：没有文件被上传 */
	public static final String ERR_NO_FILE_UPLOAD = "100";
	
	/* 错误码 200：文件类型不支持 */
	public static final String ERR_UNSUPPORTED_FILE_TYPE = "200";
	
	/* 错误码 300：文件大小超出限制 */
	public static final String ERR_FILE_TOO_BIG = "300";
	
	/* 错误码 400：文件不存在 */
	public static final String ERR_FILE_NOT_FOUND = "400";
	
	/* 错误码 500：应用程序发生异常 */
	public static final String ERR_EXCEPTION_FOUND = "500";
	
	/* 错误码 600：参数错误 */
	public static final String ERR_PARAM_ERROR = "600";
	
	/* 上传结果：成功 */
	public static final String UPLOAD_RESULT_SUCCESS = "success";
	
	/* 上传结果：失败 */
	public static final String UPLOAD_RESULT_FAIL = "fail";
	
	private static final String err = "FastDfs上传出现异常!!";
	
	/**
	 * 构造函数私有化
	 */
	private FastdfsServiceUtil() {}
	
	/**
	 * 上传文件到 FastDFS 服务器
	 * 
	 */
	public static  List<Map<String, Object>> doUpload(MultipartHttpServletRequest request, String fieldName) {
		List<Map<String, Object>> uploadResult = new ArrayList<Map<String, Object>>();
   
		Iterator<String> iter = request.getFileNames();
		while(iter.hasNext()) {
		
		List<MultipartFile> multipartFiles = request.getFiles(iter.next());
		
		/* 没有文件被上传 */
		if (multipartFiles == null || multipartFiles.isEmpty()) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result", UPLOAD_RESULT_FAIL);
			result.put("errno", ERR_NO_FILE_UPLOAD);
			
			uploadResult.add(result);
			
			return uploadResult;
		}
		//获得存储连接
		//获得存储连接
		StorageClient storageClient = getStorageClient();
		
		if (storageClient == null) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result", UPLOAD_RESULT_FAIL);
			result.put("errno", ERR_EXCEPTION_FOUND);
			
			uploadResult.add(result);
			
			return uploadResult;
		}
		
		String tempDir = System.getProperty("java.io.tmpdir");
		
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("result", UPLOAD_RESULT_FAIL);
				result.put("errno", ERR_NO_FILE_UPLOAD);
				
				uploadResult.add(result);
				
				return uploadResult;
			}
			
			String originalFileName = multipartFile.getOriginalFilename();//新加的,获取文件名
			
			String tempFileExtension = ObjectUtil.getExtension(multipartFile.getOriginalFilename());
			
			String tempFileName = tempDir + File.separator + UUID.randomUUID().toString() + "." + tempFileExtension;
			
			File tempFile = new File(tempFileName);
			
			try {
				multipartFile.transferTo(tempFile);
			} catch (Exception e) {
				e.printStackTrace();
				
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("result", UPLOAD_RESULT_FAIL);
				result.put("errno", ERR_FILE_NOT_FOUND);
				
				uploadResult.add(result);
				
				continue;
			}
			
			try {
				String[] fdfsUploadResult = storageClient.upload_file(tempFileName, null, null);
				String groupName = fdfsUploadResult[0];
				String remoteFileName = fdfsUploadResult[1];
				
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("result", UPLOAD_RESULT_SUCCESS);
				String filePath = "/" + groupName + "/" + remoteFileName;
				result.put("filePath", filePath);
				result.put("fileSize", multipartFile.getSize());
				result.put("originalFileName", originalFileName);//新加的,获取文件名
			
				uploadResult.add(result);
				System.out.println("gorupName:" +groupName +" ; remoteFIleName:" + remoteFileName);
			
			} catch (Exception e) {
//				e.printStackTrace();
				logger.error(err, e);
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("result", UPLOAD_RESULT_FAIL);
				result.put("errno", ERR_EXCEPTION_FOUND);
				
				uploadResult.add(result);
				
				throw new CFException(err, e);
//				continue;
			}
			
			tempFile.delete();
		}
		}
		
		return uploadResult;
	}
	
	/**
	 * 
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 * @return 返回Null则为失败
	 */
	public static String[] uploadFile(File file, String fileName) {
		FileInputStream fis = null;
		try {
			NameValuePair[] meta_list = null; // new NameValuePair[0];
			fis = new FileInputStream(file);
			byte[] file_buff = null;
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}
			StorageClient storageClient = getStorageClient();  
			//String fileid = storageClient.upload_file1(file_buff, getFileExt(fileName), meta_list);
			String[] fileid =storageClient.upload_file(file_buff, getFileExt(fileName), meta_list);
			return fileid;
		} catch (Exception ex) {
			logger.error(err, ex);
			return null;
		}finally{
			if (fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(err, e);
				}
			}
		}
	}
	
	
	public static Map<String, Object> uploadFile(byte[] b, String name, String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			NameValuePair[] meta_list = null;
			StorageClient storageClient = FastdfsServiceUtil.getStorageClient();
			String[] fileid = storageClient.upload_file(b, type, meta_list);
			
			String groupName = fileid[0];
			String remoteFileName = fileid[1];
			
			result.put("result", UPLOAD_RESULT_SUCCESS);
			String filePath = "/" + groupName + "/" + remoteFileName;
			result.put("filePath", filePath);
			result.put("originalFileName", name);//新加的,获取文件名
		
			logger.info("gorupName:" +groupName +" ; remoteFIleName:" + remoteFileName);
			
		} catch (IOException e) {
			e.printStackTrace();
			result.put("result", UPLOAD_RESULT_FAIL);
			result.put("message", e.getMessage());
		} catch (MyException e) {
			e.printStackTrace();
			result.put("result", UPLOAD_RESULT_FAIL);
			result.put("message", e.getMessage());
		}
		
		
		return result;
	}
	
	/**
	 * 文件下载
	 */
	public static byte[] download(String url)  {
		byte[] b = null;
		String[] str = url.split("/");
		String group_name =str[1];
		String remote_filename = url.replace("/"+group_name+"/", "");
		//获得存储连接
		StorageClient storageClient = getStorageClient();
		try {
			if (storageClient != null) {
				System.out.println("group_name:" + group_name.trim()+ "remote_FileName:" +remote_filename.trim());
				b = storageClient.download_file(group_name.trim(), remote_filename.trim());
				System.out.println("读取字节大小:" + b.length);
			}	
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(err, e);
			throw new CFException(err, e);
		}
		return b;
	}
	
	public static int delFile(String url) {
		int b = 0;
		String[] str = url.split("/");
		String group_name =str[1];
		String remote_filename = url.replace("/"+group_name+"/", "");
		
		StorageClient storageClient = getStorageClient();
		
		if(storageClient != null) {
			try {
				b = storageClient.delete_file(group_name.trim(), remote_filename.trim());
			} catch (IOException e) {
				e.printStackTrace();
				throw new CFException(err, e);
			} catch (MyException e) {
				logger.error(err, e);
				throw new CFException(err, e);
			}
		}
		return b;
	}
	
	/**
	 * 取得 org.csource.fastdfs.StorageClient 对象
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @return
	 */
	private static StorageClient getStorageClient() {
		/* 读取 Fastdfs 配置文件 */
		Properties properties = ObjectUtil.loadPropertiesFromResourceFile("fastdfs.properties");
		if (properties.isEmpty()) {
			return null;
		}
		
		ClientGlobal.setG_charset("UTF-8");
		
		TrackerClient trackerClient = null;
		TrackerServer trackerServer = null;
		StorageClient storageClient = null;
		
		try {
			String[] trackerServers = properties.get("trackerServers").toString().split(",");
			
			InetSocketAddress[] addresses = new InetSocketAddress[trackerServers.length];
			
			for (int i = 0; i < trackerServers.length; i++) {
				String[] tempArray = trackerServers[i].split(":");
				
				addresses[i] = new InetSocketAddress(InetAddress.getByAddress(ipAddressToByteArray(tempArray[0])), Integer.parseInt(tempArray[1]));
			}
			
			TrackerGroup trackerGroup = new TrackerGroup(addresses);
			
			trackerClient = new TrackerClient(trackerGroup);
			
			trackerServer = trackerClient.getConnection();
			
			storageClient = new StorageClient(trackerServer, null);
			return storageClient;
		} catch (Exception e) {
			logger.error(err, e);
			throw new CFException(err, e);
//			return null;
		}
	}
	
	
	
	/**
	 * 将 ip 地址转换成 byte 数组
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param ipAddress ip 地址
	 * @return
	 */
	private static byte[] ipAddressToByteArray(String ipAddress) {
		if (ipAddress == null || !ipAddress.matches("^[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}$")) {
			return null;
		}
		
		String[] tempArray = ipAddress.split("\\.");
		byte[] bytes = new byte[4];
		
		for (int i = 0; i < 4; i++) {
			bytes[i] = (byte) (Integer.parseInt(tempArray[i]) & 0xFF);
		}
		
		return bytes;
	}
	
	/**
	 * 获取文件后缀名（不带点）.
	 * 
	 * @return 如："jpg" or "".
	 */
	private static String getFileExt(String fileName) {
		if (DataUtil.isEmpty(fileName) || !fileName.contains(".")) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
		}
	}
}
