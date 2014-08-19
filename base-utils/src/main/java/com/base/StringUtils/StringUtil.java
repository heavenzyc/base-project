package com.base.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类 Copyright (c) 2012 by PreTang All rights reserved.
 * 
 * @author HuangCheng
 * @date 2012-7-9
 */
public class StringUtil {

	/**
	 * 是否为半角字符串
	 * 
	 * @param param
	 * @return true为半角，反正为全角
	 * @author HuangCheng
	 * @date 2012-5-12
	 */
	public static boolean isAllHalf(String param) {
		char[] chs = param.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (!(('\uFF61' <= chs[i]) && (chs[i] <= '\uFF9F'))
					&& !(('\u0020' <= chs[i]) && (chs[i] <= '\u007E'))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用户账号正则表达式判断 1、必须以半角的字母开头 2、不能包括任何标点符号 3、长度不超过10位 4、不能为空 5、不能有中文
	 * 
	 * @param value
	 * @return 通过规则为true, 反之为false
	 * @author HuangCheng
	 * @date 2012-5-12
	 */
	public static Boolean isUserIdOk(String value) {
		if (isAllHalf(value) && value.length() > 0 && value.length() < 11) {
			String userRegEx = "^[a-z]+[0-9a-z]*";
			Pattern useridPattern = Pattern.compile(userRegEx);
			Matcher m = useridPattern.matcher(value);
			return m.matches();
		}
		return false;
	}

	/**
	 * 判断用户真实姓名 1.只能是中文 2.中文个数只能是2-6个
	 * 
	 * @param value
	 * @return Boolean
	 * @author HuangCheng
	 * @date 2012-5-18
	 */
	public static Boolean isUserRealNameOk(String value) {
		String regEx = "[\\u4e00-\\u9fa5]{2,6}";
		Pattern sChinese = Pattern.compile(regEx);
		Matcher m = sChinese.matcher(value);
		return m.matches();
	}

	/**
	 * 判断字符串是否以下面的格式开始 gfb电话号码 大小写不敏感
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isGFBTask(String value) {
		String userRegEx = "^[Gg][Ff][Bb]1([0-9]{10})";
		Pattern useridPattern = Pattern.compile(userRegEx);
		Matcher m = useridPattern.matcher(value);
		return m.matches();
	}

	public static Boolean isTshu(String value) {
		String userRegEx = "[0-9a-z]{6,20}";
		Pattern useridPattern = Pattern.compile(userRegEx);
		Matcher m = useridPattern.matcher(value);
		return m.matches();
	}

	/**
	 * 手机号码正则判断,只能是11位数字
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isPhoneNumber(String value) {
		String userRegEx = "^1([0-9]{10})";
		Pattern useridPattern = Pattern.compile(userRegEx);
		Matcher m = useridPattern.matcher(value);
		return m.matches();
	}

	/**
	 * 根据key从cookieValue中取出value
	 * 
	 * @param cookieValue
	 *            (key1_value1:key2_value2)
	 * @param key
	 * @return
	 */
	public static Integer getValueInCookieByKey(String cookieValue, Integer key) {
		String[] strs = cookieValue.split("_");
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null && !strs[i].equals("")) {
				String[] res = strs[i].split(":");
				if (res.length == 2) {
					int bId = Integer.parseInt(res[0]);
					int value = Integer.parseInt(res[1]);
					if (bId == key) {
						return value;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 不是手机号码返回true
	 * 
	 * @param phone
	 * @return
	 */
	public static Boolean isNotPhoneNumber(String phone) {
		return !isPhoneNumber(phone);
	}

	/**
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * <p/>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 转换为字符串,判断null
	 * 
	 * @param str
	 * @return
	 */
	public static String filter(Object str) {
		return str == null ? "" : str.toString();
	}

	/**
	 * 将数字转换成36位(字母+数字混合)进制的字符串
	 * 
	 * @param x
	 * @return
	 */
	public static String numberToLetter(long x) {
		if (x < 0) {
			// 出错了.
			return null;
		}
		if (x < 10) {
			return x + "";
		}
		if (x < 36) {
			return String.valueOf((char) ('A' + x - 10));
		}
		return numberToLetter(x / 36) + numberToLetter(x % 36);
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 判断是否存在emoji表情符号
	 * 
	 * @param source
	 * @return
	 */
	public static boolean containsEmoji(String source) {
		if (isBlank(source)) {
			return false;
		}
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}
		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}
	/**
	 * 分析URL
	 * @param url
	 * @return map集合
	 */
	public static Map<String, Object> analysisUrl(String url) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (isNotBlank(url)) {// 如果URL不是空字符串
			url = url.substring(url.indexOf('?') + 1);
			String paramaters[] = url.split("&");
			for (String param : paramaters) {
				String values[] = param.split("=");
				paramMap.put(values[0], values[1]);
			}
		}
		return paramMap;
	}
}