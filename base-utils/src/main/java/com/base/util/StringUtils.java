package com.base.util;

import org.apache.commons.lang.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    private static final String NUM_S = "0123456789";
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 截取文字safe 中文
     * 
     * @param @param string
     * @param @param length
     * @param @param more like `...`,`>>>`
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String subCn(String string, int length, String more) {
        if (StringUtils.isNotEmpty(string)) {
            char[] chars = string.toCharArray();
            if (chars.length > length) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    sb.append(chars[i]);
                }
                sb.append(more);
                return sb.toString();
            }
        }
        return string;
    }

    /**
     * 生成一个10位的tonken用于http cache
     * 
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getTonken() {
        return RandomStringUtils.random(10, NUM_S);
    }

    /**
     * 生成随机数
     * 
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String randomPwd(int count) {
        return RandomStringUtils.random(count, STR_S);
    }

    /**
     * 百度获ip获取到的城市处理 供大众点评 暂用，以后会用类是分词的技术，好吧完全没有算法的概念
     * 
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String cityMatcher(String city) {
        String regex = "(.+)[市|省|自治区]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(city);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    /**
     * 进行字符规格化（全角转半角，大写转小写处理）
     * 
     * @param input
     * @return char
     */
    public static char regularize(char input) {
        if (input == 12288) {
            input = (char) 32;
        } else if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        } else if (input >= 'A' && input <= 'Z') {
            input += 32;
        }
        return input;
    }

    /**
     * 字符串全角转半角
     * 
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String togglecase(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            sb.append(regularize(string.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 功能描述: 生成sql占位符 ?,?,?
     * 
     * @param @param size
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String sqlHolder(int size) {
        String[] paras = new String[size];
        Arrays.fill(paras, "?");
        return StringUtils.join(paras, ",");
    }

    /**
     * 计算文字长度-.-无中文问题
     *
     * @param @param string
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int getLength(String string) {
        if (isBlank(string)) {
            return 0;
        } else {
            char[] strChars = string.toCharArray();
            return strChars.length;
        }
    }

    /**
     * 将字符串中特定模式的字符转换成map中对应的值,
     * 
     * @param s
     *            需要转换的字符串
     * @param map
     *            转换所需的键值对集合
     * @return 转换后的字符串
     */
    public static String replace(String s, Map<String, ?> map) {
        StringBuilder sb = new StringBuilder((int) (s.length() * 1.5));
        int cursor = 0;
        for (int start, end; (start = s.indexOf("${", cursor)) != -1 && (end = s.indexOf("}", start)) != -1;) {
            sb.append(s.substring(cursor, start));
            String key = s.substring(start + 2, end);
            sb.append(map.get(StringUtils.trim(key)));
            cursor = end + 1;
        }
        sb.append(s.substring(cursor, s.length()));
        return sb.toString();
    }

    /**
     * AES算法密钥生成器.
     * 
     * @return 生成的密钥 它是一个32个字符的16进制字符串.
     */
    @SuppressWarnings("unused")
    public static String AESKey() {
        try {
            // Get the KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // 192 and 256 bits may not be available
            // Generate the secret key specs.
            SecretKey key = keyGenerator.generateKey();
            byte[] raw = key.getEncoded();
            return byteArr2HexStr(raw);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 使用AES算法解密字符串. AES加密算法（美国国家标准局倡导的AES即将作为新标准取代DES）
     * 
     * @param encrypted
     *            要解密的字符串
     * @param rawKey
     *            密钥字符串, 要求为一个32位(或64位，或128位)的16进制数的字符串,否则会出错. 可以使用
     *            {@link #AESKey()}方法生成一个密钥,
     * @return 解密之后的字符串
     * @see #AESEncrypt(String, String)
     */
    public static String AESDecrypt(String encrypted, String rawKey) {
        byte[] tmp = hexStr2ByteArr(encrypted);
        byte[] key = hexStr2ByteArr(rawKey);
        try {
            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(tmp);
            return new String(decrypted);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 使用AES算法加密字符串.
     * 
     * @param message
     *            要加密的字符串.
     * @param rawKey
     *            密钥字符串, 要求为一个32位(或64位，或128位)的16进制数的字符串,否则会出错. 可以使用
     *            {@link #AESKey()}方法生成一个密钥,
     * @return 加密之后的字符串
     * @see #AESDecrypt(String, String)
     */
    public static String AESEncrypt(String message, String rawKey) {
        byte[] key = hexStr2ByteArr(rawKey);
        try {
            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            return byteArr2HexStr(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用DES算法解密字符串.
     * 
     * @param encrypted
     *            要解密的字符串.
     * @param rawKey
     *            密钥字符串, 可以为任意字符, 但最长不得超过8个字符(如最超过，后面的字符会被丢弃).
     * @return 解密之后的字符串.
     * @see #DESEncrypt(String, String)
     */
    public static String DESDecrypt(String encrypted, String rawKey) {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] arrBTmp = rawKey.getBytes();
        byte[] arrB = new byte[8]; // 创建一个空的8位字节数组（默认值为0）
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
            // 将原始字节数组转换为8位
            arrB[i] = arrBTmp[i];
        try {
            Key key = new SecretKeySpec(arrB, "DES");// 生成密钥
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(hexStr2ByteArr(encrypted)));
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 使用DES算法加密字符串.
     * 
     * @param message
     *            要加密的字符串.
     * @param rawKey
     *            密钥字符串, 可以为任意字符, 但最长不得超过8个字符(如最超过，后面的字符会被丢弃).
     * @return 加密之后的字符串.
     * @see #DESDecrypt(String, String)
     */
    public static String DESEncrypt(String message, String rawKey) {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] arrBTmp = rawKey.getBytes();
        byte[] arrB = new byte[8]; // 创建一个空的8位字节数组（默认值为0）
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
            // 将原始字节数组转换为8位
            arrB[i] = arrBTmp[i];
        try {
            Key key = new SecretKeySpec(arrB, "DES");// 生成密钥
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return byteArr2HexStr(cipher.doFinal(message.getBytes()));
        } catch (Exception e) {

            return null;
        }
    }

    public static String md5(String message) {

        return MD5Encrypt(MD5Encrypt(message, 32), 32);
    }

    /**
     * 使用MD5算法加密字符串.
     * 
     * @param message
     *            要加密的字符串.
     * @param length
     *            指定返回加密后字符串长度，其值必须是16或者32.
     * @return 加密之后的字符串
     */
    public static String MD5Encrypt(String message, int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            String str32 = buf.toString().toUpperCase();
            if (length == 32)
                return str32;
            else if (length == 16)
                return str32.substring(8, 24);
            else
                throw new Exception("mod5 encrypt fail");
        } catch (Exception e) {
            return null;
        }
    }

    public static String encode(String sign) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(sign.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * Turns array of bytes into string
     * 
     * @param buf
     *            Array of bytes to convert to hex string
     * @return Generated hex string
     */
    private static String byteArr2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                sb.append("0");

            sb.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[]
     * buf)互为可逆的转换过程
     * 
     * @param src
     *            需要转换的字符串
     * @return 转换后的byte数组
     */
    private static byte[] hexStr2ByteArr(String src) {
        if (src.length() < 1) {
            return null;
        }
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);

            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

    public static boolean isEmpty(String... args) {
        boolean flag = false;
        for (String arg : args) {
            if (isEmpty(arg)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 随机字符串数生成方法
     * 
     * @param length
     * @return
     */
    public static String getRandomKey(Integer length) {
        String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
                "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                "y", "z" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    /**
     * 随机产生8位，由数字和字符组成的字符串，字符串为小写
     * 
     * @return
     */
    public static String getRandomKey() {
        return getRandomKey(8);
    }

    /**
     * 随机产生数字类型的字符串
     * 
     * @param length
     *            字符串长度
     * @return
     */
    public static String getRandomNumberKey(Integer length) {
        String[] randomValues = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        StringBuffer vcodeString = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            vcodeString.append(randomValues[random.nextInt(10)]);
        }
        return vcodeString.toString();
    }
}
