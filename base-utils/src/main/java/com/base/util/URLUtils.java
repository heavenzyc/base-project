package com.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author heaven.zyc 2014年6月12日 下午10:26:55
 */
public class URLUtils {
	
	private static final Logger log = LoggerFactory.getLogger(URLUtils.class);
	
	public static String encodeUrl(String url) {
		try {
			url = URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Error on encoding url:" + url,e);
			return url;
		}
		return url;
	}
	
	public static String decode(String str) {
		try {
			str = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Error on decoding url:" + str,e);
			return str;
		}
		return str;
	}
	
	public static String getUrlByRequest(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		try {
			return initUrl(url, request.getParameterMap());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(),e);
			return url + "?" + request.getQueryString();
		}
	}
	
	public static String initUrl(String url, Map<String, String[]> params) throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }
    
    public static String map2Url(Map<String, String[]> map) throws UnsupportedEncodingException {
        if (null == map || map.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (String key : map.keySet()) {
	        String[] values = map.get(key);
	        for (String value : values) {
	        	if (isfist) {
	        		isfist = false;
		        } else {
		            url.append("&");
		        }
	        	url.append(key).append("=");
		        if (StringUtils.isNotEmpty(value)) {
		            url.append(URLEncoder.encode(value, "utf-8"));
		        }
			}
		}
        return url.toString();
    }

}
