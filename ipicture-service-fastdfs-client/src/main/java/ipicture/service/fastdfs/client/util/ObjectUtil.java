package ipicture.service.fastdfs.client.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
  
	private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);
	/** 
	 * �����е�object ת��Ϊjson string
	 * @param object
	 * @return 
	 */
	public static String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("object write to json error :" +object);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("object write to json error :" +object);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("object write to json error :" +object);
			e.printStackTrace();
		}
		return null;
	}
	
	//JSONתΪOBJECT
    public <T> T fromJson(String jsonString, Class<T> clazz) {  
    	
        if ("".equals(getString(jsonString))) {  
            return null;  
        }  
  
        try {  
        	ObjectMapper mapper = new ObjectMapper();  
            return mapper.readValue(jsonString, clazz);  
        } catch (IOException e) {  
            logger.warn("parse json string error:" + jsonString, e);  
            return null;  
        }  
    }
	
    public static String getString(Object obj){
		return getString(obj, "");
	}
	 
	public static String getString(Object obj, String str) {
		return obj==null||"null".equals(obj)?str:String.valueOf(obj);
	}
	
	/**
	 * integer ������null ͬʱ ������
	 */
	public static boolean isNotNullAndGreaterZero(Integer num){
		return !isNullForInt(num) && num > 0;
	}
	public static boolean isNullForInt(Integer num) {
		return num==null;
	}
	
	/**
	 * �ı�ѹ��
	 * @param primStr
	 * @return
	 */
	 /*public static String gzip(String primStr) {
	    	if (primStr == null || primStr.length() == 0) {
	    		return primStr;
	    	}

	    	ByteArrayOutputStream out = new ByteArrayOutputStream();

	    	GZIPOutputStream gzip=null;
	    	try {
	    		gzip = new GZIPOutputStream(out);
	    		gzip.write(primStr.getBytes());
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}finally{
	    		if(gzip!=null){
	    			try {gzip.close();} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	}
//	    	return new sun.misc.BASE64Encoder().encode(out.toByteArray());
	    	return Base64.encodeBase64String(out.toByteArray());
	    }*/
	    

	    /*public static String gunzip(String compressedStr){
	    	if(compressedStr==null){
	    		return null;
	    	}
	    	
	    	 ByteArrayOutputStream out= new ByteArrayOutputStream();
	    	 ByteArrayInputStream in=null;
		   	 GZIPInputStream ginzip=null;
		   	 byte[] compressed=null;
		   	 String decompressed = null;
		   	 try {
		//   	 compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
		   	     compressed	= Base64.decodeBase64(compressedStr);
		
		   	     in=new ByteArrayInputStream(compressed);
		   	     ginzip=new GZIPInputStream(in);
		
		   	     byte[] buffer = new byte[1024];
		   	     int offset = -1;
		   	     while ((offset = ginzip.read(buffer)) != -1) {
		   	    	 out.write(buffer, 0, offset);
		   	     }
		   	     decompressed=out.toString();
		   	 } 
		   	 catch (IOException e) {
		   		 e.printStackTrace();
		   	 } finally {
		   		 if (ginzip != null) {
		   			 try {ginzip.close();} catch (IOException e) {;}
		   		 }
		   		 if (in != null) {
		   			 try {
		   				 in.close();
		   			 } catch (IOException e) {;}
		   		 }
		   		 if (out != null) {
		   			 try {
		   				 out.close();
		   			 } catch (IOException e) {;}
		   		 }
		   	}

	   	 return decompressed;
	    }*/
	     
	public static boolean isEmpty(Object obj) {
		return obj == null ? true :false;
	}
	
	/**
	 * ��ȡ properties �ļ�
	 * 
	 * @param resourceFileName ��Դ�ļ���
	 * @return
	 */
	public static Properties loadPropertiesFromResourceFile(String resourceFileName) {
		Properties properties = new Properties();
		
		InputStream in = null;
		
		try {
			in = ObjectUtil.class.getClassLoader().getResourceAsStream(resourceFileName);
			
			properties.load(in);
			
			return properties;
		} catch (Exception e) {
			return properties;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * ȡ���ļ�����չ���磺jpg
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param fileName �ļ���
	 * @return
	 */
	public static String getExtension(String fileName) {
		if (fileName == null || "".equals(fileName) || !fileName.contains(".")) {
			return "";
		}
		
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
	
	/**
	 * ȡ���ļ�����չ���磺jpg
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param file java.io.File ����
	 * @return
	 */
	public static String getExtension(File file) {
		try {
			if (file == null || !file.isFile()) {
				return "";
			}
			
			return getExtension(file.getName());
		} catch (Exception e) {
			e.printStackTrace();
			
			return "";
		}
	}
	
	/**
	 * �ж� string �Ƿ�Ϊnull 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取手机随机码
	 * @return
	 */
	public static Integer getRandomNum() {
		String num = "";
		while(num.length() != 6) {
			num =String.valueOf((int)(Math.random()*999999));
		}
		return Integer.parseInt(num);
	}
}
