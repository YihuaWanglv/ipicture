package ipicture.service.fastdfs.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DataUtil {

    /**
     * 用于将号码分割开的方法<br/>
     * 例如：<br/>
     * (+86)020-223005032 分割为 +86 020 223005032<br/>
     * 020-223005032 分割为 020 223005032<br/>
     * 分别返回：： countryNum 国家代号 , zoneNum 区号 , codeNum 核心号码
     */
    public static Map<String, String> splitNumber(String number) {

        Map<String, String> result = new HashMap<String, String>();

        if (isEmpty(number))
            return result;

        int index1 = -1;
        int index2 = -1;

        // Step1 ： 处理括号内容
        if ((index1 = number.indexOf("(")) != -1) {

            index2 = number.indexOf(")");

            if (index2 == -1)
                throw new IllegalArgumentException("The number is illegal");

            result.put("countryNum", number.substring(index1 + 1, index2));
            number = number.substring(index2 + 1);
        }

        // Step2 : 处理区号
        index1 = -1;
        if ((index2 = number.indexOf("-")) != -1) {

            result.put("zoneNum", number.substring(index1 + 1, index2));
            number = number.substring(index2 + 1);

        }

        // Step3 ：处理最后部分
        result.put("codeNum", number);

        return result;
    }

    /**
     * 还原密文!
     * 这个方法主要是用于还原被我们修改了的"+"号  和 "="号 将+号转成@
     *
     * @param ciphertext
     * @return
     * @author zl
     */
    public static String remaskCiphertext(String ciphertext) {
        return ciphertext.replaceAll("@", "\\+").replaceAll("_", "=");
    }

    /**
     * 修改密文!
     * 由于加密生成密文里面含有url不支持的字符，"+"号  和 "="号
     * 因此需要把他们给它先替换掉!! 将+号转成@  =号转成 _ 编码需要
     *
     * @param ciphertext
     * @return
     * @author zl
     */
    public static String maskCiphertext(String ciphertext) {
        return ciphertext.replaceAll("\\+", "@").replaceAll("=", "_");
    }

    /**
     * 简易信息加密
     *
     * @return
     */
    public static String encode(String plaintext) {
        if (isEmpty(plaintext))
            return null;
        try {
            StringBuilder ciphertext = new StringBuilder();
            ciphertext.append(StringUtil.getRandomString(6));
            ciphertext.append(maskCiphertext(symmetricEncryptoDES(plaintext)));
            ciphertext.append(StringUtil.getRandomString(6));
            return ciphertext.toString();
        } catch (Exception e) {
            return plaintext;
        }
    }

    /**
     * 格式化传真，电话号码，手机号码
     * <p/>
     * 格式为 (+86)020-34782999
     *
     * @param countryNo 国家代号
     * @param zoneNo    区号
     * @param codeNum   核心号码
     * @return
     * @author Jelin
     */
    public static String mergeNumber(String countryNo, String zoneNo,
                                     String codeNum) {
        StringBuilder sb = new StringBuilder();
        if (!isEmpty(countryNo))
            sb.append("(" + countryNo.trim() + ")");
        if (!isEmpty(zoneNo))
            sb.append(zoneNo.trim() + "-");
        if (!isEmpty(codeNum))
            sb.append(codeNum.trim());
        return sb.toString();
    }

    /**
     * 获得指定长度的数字的字符串 左边补"0"
     * <p/>
     * 第一个参数是原来的数字 第二个参数才是期望的长度
     *
     * @param num
     * @param Length
     * @return
     */
    public static String getAssignLengthNum(Integer num, Integer Length) {
        return getAssignLengthNum(String.valueOf(num), Length);
    }

    public static String getAssignLengthNum(String num, Integer Length) {
        StringBuilder reseult = new StringBuilder();
        reseult.append(num);
        if (reseult.length() < Length) {
            for (int i = reseult.length() + 1; i <= Length; i++) {
                reseult.insert(0, "0");
            }
        }
        return reseult.toString();
    }

    public static String getAssignLengthNumByRight(String num, Integer Length) {
        StringBuilder reseult = new StringBuilder();
        reseult.append(num);
        if (reseult.length() < Length) {
            for (int i = reseult.length() + 1; i <= Length; i++) {
                reseult.append("0");
            }
        }
        return reseult.toString();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param args
     */
    public static boolean isNumber(String num) {

        if (isEmpty(num))
            return false;

        return num.matches("^([-+]?)(([0-9]+)([.]([0-9]+))?)$");
    }

    public static void main(String[] args) {
        // System.out.println(symmetricEncryptoDES(UUID.randomUUID().toString()));
        // System.out.println(symmetricDecryptoDES("MizfjA7wY5lwXiCeFOOYGkZqrlwYe3LPkyXdJcEInl3+oTIzzJC+Z9KT/0E5rqB72sf3KmfJstk="));

        // System.out.println(mergeNumber("020", null, "345132133"));

        // System.out.println(splitNumber("(+86)020-223005032"));
        // System.out.println(splitNumber("020-223005032"));
        // System.out.println(splitNumber("223005032"));
        // System.out.println(splitNumber(null).size());

        // System.out.println(isNumber("+34"));
        // System.out.println(isNumber("-34"));
        // System.out.println(isNumber("-340"));
        // System.out.println(isNumber("03.04"));
        // System.out.println(isNumber("-3.+4"));
        // System.out.println(isNumber("-3.4"));
        // System.out.println(isNumber("-34"));
        // System.out.println(isNumber("-34"));

//        String mw = "tRXjfExKv8yH5lSod0VM9WFaVDIianRBReQivYAgliNZ94y6NCnWug__"
//                .replaceAll("@", "\\+").replaceAll("_", "=");
//        System.out.println(DataUtil.symmetricDecryptoDES(mw));
    	
//    	BigDecimal a = new BigDecimal(10);
//    	BigDecimal r = a.pow(4);
//    	System.err.println(r);
    	
//    	System.err.println(getRandomNumber(5).toString());

    }

    /**
     * 获得填充基本值的对象，主要是用于测试
     * <p/>
     * version 0.1
     *
     * @author Jelin
     */
    public static Object getFillValueObject(Class clazz) throws Exception {

        Object obj = clazz.newInstance();

        if (!DataUtil.isEmpty(clazz.getMethods())) {
            for (Method method : clazz.getMethods()) {
                if (method.getName().startsWith("set")) {

                    Type[] types = method.getParameterTypes();
                    if (DataUtil.isEmpty(types))
                        throw new RuntimeException("Create Object fail!!!");

                    if (types[0].equals(String.class)) {
                        method.invoke(obj, "aaa");
                    } else if (types[0].equals(Integer.class)) {
                        method.invoke(obj, 222);
                    } else if (types[0].equals(Date.class)) {
                        method.invoke(obj, new Date());
                    } else if (types[0].equals(Long.class)) {
                        method.invoke(obj, 11L);
                    } else if (types[0].equals(Double.class)) {
                        method.invoke(obj, 0.11d);
                    }

                }
            }
        }

        return obj;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || ("".equals(str)) || (str.trim().length() == 0);
    }

    /**
     * 判断字符串是否为空空格也算空字符串
     *
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(String str) {
        return str == null || (str.trim().length() == 0);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof String)
            return obj == null || ("".equals(obj));
        else if (obj instanceof Collection) {
            return obj == null || ((Collection) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return obj == null || ((Object[]) obj).length == 0;
        } else
            return obj == null;
    }

    /**
     * 字符串转成数组
     *
     * @return
     */
    public static String[] splitString(String str, String reg) {
        if (isEmpty(str))
            return null;
        return str.split(reg);
    }

    /**
     * 字符串数组转成字符串
     *
     * @param strs
     * @param reg
     * @return
     */
    public static String ArrayToString(String[] strs, String reg) {
        if (strs.length == 1)
            return strs[0];
        String result = "";
        for (int i = 0; i < strs.length; i++) {
            if (i > 0)
                result += reg;
            result += strs[i];
        }
        return result;
    }

    /**
     * 若字符串为空则返回一个空字符串""
     *
     * @return
     */
    public static String trimNullString(String str) {
        if (isEmpty(str))
            return "";
        else
            return str;
    }

    /**
     * 若字符串为空则返回一个"#"
     *
     * @return
     */
    public static String trimNullWell(String str) {
        if (isEmpty(str))
            return "#";
        else
            return str;
    }

    /**
     * 若字符串为空则返回一个"#"
     *
     * @return
     */
    public static String trimNullWell(Object obj) {
        if (isEmpty(obj))
            return "#";
        else
            return (String) obj;
    }

    /**
     * DES加密对外接口.<br />
     */
    public static String symmetricEncryptoDES(String pwOfString) {
        String output = null;
        if (null != pwOfString) {
            try {
                byte[] afterChange = symmetricEncryptoDES(pwOfString
                        .getBytes("UTF8"));
                output = new String(Base64.encodeBase64(afterChange));
                return output;
            } catch (Exception e) {
                throw new RuntimeException("DES加密失败," + e.getMessage());
            }
        }
        return output;
    }

    /**
     * DES解密对外接口.<br/>
     */
    public static String symmetricDecryptoDES(String input) {
        String output = null;
        if (null != input) {
            try {
                byte[] sourceByte = Base64.decodeBase64(input.getBytes());
                output = new String(symmetricDecryptoDES(sourceByte), "UTF8");
            } catch (Exception e) {
                throw new RuntimeException("DES解密失败," + e.getMessage());
            }
        }
        return output;
    }

    /**
     * DES对称加密方法
     */
    private static byte[] symmetricEncryptoDES(byte[] byteSource)
            throws IllegalBlockSizeException, BadPaddingException, IOException,
            NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException, NoSuchPaddingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int mode = Cipher.ENCRYPT_MODE;
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        byte[] keyData = {1, 9, 8, 2, 0, 8, 2, 2};
        DESKeySpec keySpec = new DESKeySpec(keyData);
        Key key = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        int blockSize = cipher.getBlockSize();
        int position = 0;
        int length = byteSource.length;

        boolean more = true;
        while (more) {
            if (position + blockSize <= length) {
                baos.write(cipher.update(byteSource, position, blockSize));
                position += blockSize;
            } else {
                more = false;
            }
        }

        if (position < length) {
            baos.write(cipher.doFinal(byteSource, position, length - position));
        } else {
            baos.write(cipher.doFinal());
        }

        return baos.toByteArray();
    }

    /**
     * DES对称解密方法
     */
    private static byte[] symmetricDecryptoDES(byte[] byteSource)
            throws NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException, NoSuchPaddingException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int mode = Cipher.DECRYPT_MODE;
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        byte[] keyData = {1, 9, 8, 2, 0, 8, 2, 2};
        DESKeySpec keySpec = new DESKeySpec(keyData);
        Key key = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        int blockSize = cipher.getBlockSize();
        int position = 0;
        int length = byteSource.length;

        boolean more = true;
        while (more) {
            if (position + blockSize <= length) {
                baos.write(cipher.update(byteSource, position, blockSize));
                position += blockSize;
            } else {
                more = false;
            }
        }
        if (position < length) {
            baos.write(cipher.doFinal(byteSource, position, length - position));
        } else {
            baos.write(cipher.doFinal());
        }
        return baos.toByteArray();
    }

    /**
     * 该方法不负责邮件的正则匹配，一般那个交给js，或者由其他方法提供
     *
     * @param email
     * @return
     */
    public static String proEmail(String email) {
        if (isEmpty(email))
            return null;
        String[] ems = email.split("@");
        if (ems[0].length() <= 1) {
            return "***@" + ems[1];
        }
        return ems[0].charAt(0) + "***@" + ems[1];
    }

    /**
     * 正则验证格式
     *
     * @param regex
     * @param str
     * @return
     * @author zhangbingyue
     * @version 创建时间 2013-4-11 下午9:53:45
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};

    /**
     * 将十进制的数字转换为指定进制的字符串。
     *
     * @param i      十进制的数字。
     * @param system 指定的进制，常见的2/8/16/32。
     * @return 转换后的字符串。
     */
    public static String numericToString(int i, int system) {
        long num = 0;
        if (i < 0) {
            num = ((long) 2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int) (num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int) (num % system)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 用于Integer的列表装换成逗号分隔的字符串 ， 例如 ： 1,2,3
     *
     * @param list
     * @return
     */
    public static String intsToString(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < list.size(); index++) {
            Integer str = list.get(index);
            builder.append(str);
            if (index != list.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 用于Integer的列表装换成逗号分隔的字符串 ， 例如 ： 1,2,3
     *
     * @param list
     * @return
     */
    public static String intsToString(Integer... list) {
        if (isEmpty(list))
            return null;
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < list.length; index++) {
            Integer str = list[index];
            builder.append(str);
            if (index != list.length - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 用于将String的列表转换成逗号分隔的字符串   Hel,Xx,Yy
     *
     * @param list
     * @return
     */
    public static String strsToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < list.size(); index++) {
            String str = list.get(index);
            builder.append(str);
            if (index != list.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static void convertRealFilePath(String domainPrefix, String[] paths) {
        if (isEmpty(domainPrefix) || isEmpty(paths))
            return;
        for (int i = 0; i < paths.length; i++) {
            if (!isEmpty(paths[i])) {
                paths[i] = domainPrefix + paths[i];
            }
        }
    }

    /**
     * 字符串转成字符串
     *
     * @param strs
     * @param reg
     * @return
     */
    public static String listToString(List<?> list, String reg) {
        if (isEmpty(list) || isEmpty(reg))
            return null;

        if (list.size() == 1)
            return String.valueOf(list.get(0));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0)
                result.append(reg);
            result.append(list.get(i));
        }
        return result.toString();
    }
    
    /**
     * 获取一个随机数.可传入bit位数，如5则表示10000-99999的数字区间。位数不限，默认为4位
     * @Title: getRandomNumber
     * @param bit 随机数的位数。如5则表示10000-99999的数字区间，位数不限，默认为4位
     * @return
     */
    public static BigInteger getRandomNumber(Integer bit) {
    	return new BigDecimal(Math.random()).multiply(new BigDecimal(10).pow(isEmpty(bit) ? 4 : bit)).toBigInteger();
    }
    
    /**
     * 验证邮箱格式
     * @Title: emailFormat
     * @param email
     * @return
     */
    public static boolean emailFormat(String email) {
    	Boolean isFlag = Boolean.FALSE;
    	if(StringUtil.isEmpty(email)){
    		return isFlag;
    	}
         
         if(email.indexOf("@") < 0) {//格式,不符合log日志
         //	logger.info("Invalid email [ " + remail +"] ");
         	isFlag = Boolean.FALSE;
         } else {
 			if(email.indexOf("'") > 0) {
 				email.replaceAll("'", "");
 				
 			}
 			isFlag = Boolean.TRUE;
 		}
        // logger.info("isValidEmail :" + isFlag);
         return isFlag;
        
    }

}
