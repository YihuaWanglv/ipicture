package ipicture.service.fastdfs.client.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class StringUtil  {
	/* 随机字符串生成的方式（由字母和数字组成） */
	public static final int RANDOM_TYPE_NORMAL = 1;
	/* 随机字符串生成的方式（全部由数字组成） */
	public static final int RANDOM_TYPE_ALNUM = 2;
	/* 随机字符串生成的方式（全部由字母组成） */
	public static final int RANDOM_TYPE_ALPHA = 3;

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if (null != str && !"".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmptyORNull(String str) {
        return null != str && !"".equals(str) && !"null".equals(str);
    }
	
	public static String numberFormat(Double number, String format) {
		if (number == null || format == null || "".equals(format)) {
			return "";
		}
		
		try {
			DecimalFormat df = new DecimalFormat(format);
			
			return df.format(number);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String hideString(String input, int startPos, int endPos) {
		if (isEmpty(input)) {
			return input;
		}
		
		int len = input.length();
		
		if (startPos < 0 || endPos > (len - 1)) {
			return input;
		}
		
		char[] chars = input.toCharArray();
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < chars.length; i++) {
			if (i < startPos || i > endPos) {
				sb.append(chars[i]);
			}
			else {
				sb.append('*');
			}
		}
		
		return sb.toString();
	}
	
	
	public static String keepSecretString(String firstName, String lastName ) {
		
		String keepSecretString   = firstName +" "+ lastName;
		StringBuffer sb = new StringBuffer();
		if(keepSecretString.length() < keepSecretString.getBytes().length){
			keepSecretString = keepSecretString.replace(" ", "");
			//中文判断
			char[] chars = keepSecretString.toCharArray();
			System.out.println(chars.length);
			for (int i = 0; i < chars.length; i++) {
				if (i < 1 ) {
					sb.append(chars[i]);
				}
				else {
					sb.append('*');
				}
			}
		}else{
			
			if (StringUtil.isNotEmpty(firstName) && StringUtil.isNotEmpty(lastName) ) {
				char[] firstNameChars = firstName.toCharArray();
				char[] lastNameChars = lastName.toCharArray();
				for (int i = 0; i < firstNameChars.length; i++) {
					if (i < 1 || i > firstNameChars.length ) {
						sb.append(firstNameChars[i]);
					}
					else {
						sb.append('*');
					}
				}
				sb.append(" ");
				for (int i = 0; i < lastNameChars.length; i++) {
					if (i < 0 || i > lastNameChars.length-2 ) {
						sb.append(lastNameChars[i]);
					}
					else {
						sb.append('*');
					}
				}
			}
			else if(StringUtil.isEmpty(firstName)||StringUtil.isEmpty(lastName)) {
				keepSecretString = keepSecretString.replace(" ", "");
				char[] chars = keepSecretString.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (i < 1 || i > chars.length-2 ) {
						sb.append(chars[i]);
					}
					else {
						sb.append('*');
					}
				}
			}
		}
		return sb.toString();
		 
	}
	
	/**
	 * 格式化输出电话号码和传真号码
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param str 待处理的字符串
	 * @return
	 */
	public static String getFriendlyPhoneNumber(String str) {
		if (isEmpty(str)) {
			return "";
		}
		
		Pattern pattern;
		Matcher matcher;
		
		String countryCode = "";
		String areaCode = "";
		String number = "";
		
		pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)[-]+([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			areaCode = matcher.group(2);
			number = matcher.group(3);
			return "(+" + countryCode + ")" + areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)[-]+([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			areaCode = matcher.group(1);
			number = matcher.group(2);
			return areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			areaCode = matcher.group(2);
			number = matcher.group(3);
			return "(+" + countryCode + ")" + areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			areaCode = matcher.group(1);
			number = matcher.group(2);
			return areaCode + "-" + number;
		}
		
		return str;
	}
	
	/**
	 * 格式化输出手机号码
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param str 待处理的字符串
	 * @return
	 */
	public static String getFriendlyMobileNumber(String str) {
		if (isEmpty(str)) {
			return "";
		}
		
		Pattern pattern;
		Matcher matcher;
		
		String countryCode = "";
		String number = "";
		
		pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			number = matcher.group(2);
			return "(+" + countryCode + ")" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			number = matcher.group(2);
			return "(+" + countryCode + ")" + number;
		}
		
		return str;
	}
	
	public static String getImageAbslouteUrl(String url, String mediaPath) {
		if (!isNotEmpty(url) || !isNotEmpty(mediaPath)) {
			return "";
		}
		if (url.startsWith("/")) {
			return mediaPath + url;
		}
		return mediaPath + "/" + url;
	}
	
	public static String replaceSign(String str) {
		if (isNotEmpty(str) && str.indexOf(",") != -1) {
			str = str.replaceAll(",", "\\|");
		}
		return str;
	}
	
	public static String replaceSign(String str, String from, String to) {
		if (isNotEmpty(str) && str.indexOf(from) != -1) {
			str = str.replaceAll(from, to);
		}
		return str;
	}
	/**
	 * 生成随机字符串
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param len 随机字符串的长度
	 * @return
	 */
	public static String getRandomString(int len) {
		return getRandomString(len, RANDOM_TYPE_NORMAL);
	}
	
	/**
	 * 按指定的方式生成随机字符串
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param len 随机字符串的长度
	 * @param randomType 生成随机字符串的方式 
	 * @return
	 */
	public static String getRandomString(int len, int randomType) {
		if (len < 1) {
			return "";
		}
		
		String str;
		
		if (randomType == RANDOM_TYPE_ALNUM) {
			str = "3456789987654334567899876543";
		}
		else if (randomType == RANDOM_TYPE_ALPHA) {
			str = "abcdefghjkmnpqrstuvwxyYXWVUTSRQPNMKJHGFEDCBAyxwvutsrqpnmkjhgfedcbaABCDEFGHJKMNPQRSTUVWXY";
		}
		else {
			str = "yxwvutsrqpnmkjhgfedcba3456789ABCDEFGHJKMNPQRSTUVWXY99876543ABCDEFGHJKMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy";
		}
		
		Random rnd = new Random();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; i++) {
			int index = rnd.nextInt(str.length());
			sb.append(str.charAt(index));
		}
		
		return sb.toString();
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static boolean isBlank(final String str) {
		
		int length;
		if (str == null || (length = str.length()) == 0) {
			return true;
		}
		
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
		
	}
	
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}
	
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	public static byte[] getUtf8Bytes(String str){
		if(hasLength(str)){
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {;}
		}
		return null;
	}
	
	public static String getStringFromUtf8Bytes(byte[] tmpArray) {
		if(tmpArray != null && tmpArray.length > 0){
			try {
				return new String(tmpArray,"UTF-8");
			} catch (UnsupportedEncodingException e) {;}
		}
		return null;
	}
	
	public static boolean equals(String str1, String str2) {
		if(str1==null && str2 == null){
			return true;
		}
		
		if(str1 == null || str2 == null){
			return false;
		}
		
		return str1.equals(str2);
	}
	
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
//    	return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    	return Base64.encodeBase64String(out.toByteArray());
    }
    
    public static String gunzip(String compressedStr){
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
    
    public static String getEmailDomain(String email) {
    	return StringUtil.isEmpty(email) ? null : email.substring(email.indexOf("@")+1);
    }
    
	public static int getPasswordLevel(String value) {
		String level1 = "\\d+|[a-zA-Z]+|[\\w+]{6,8}";
		String level3 = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,32})";
		if (Pattern.matches(level3, value)) {
			return 3;
		}
		if (Pattern.matches(level1, value)) {
			return 1;
		}
		return 2;
	}
}

