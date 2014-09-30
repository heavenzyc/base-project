package com.base.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author heaven.zyc 2014年9月18日 下午2:37:12
 */
public class RequestUtils {
	
	public static String dumpHeaders(HttpServletRequest request) {
    	Enumeration<String> names = request.getHeaderNames();
    	List<String> headers = new ArrayList<String>();
    	while(names.hasMoreElements()) {
    		String name = names.nextElement();
    		String value = request.getHeader(name);
			String header = name + ": " + value;
			headers.add(header);
    	}
    	String sep = "\n\t--------------------------------------------------------------";
    	return sep + "\n\t" + StringUtils.join(headers,"\n\t") + sep;
	}
	public static String dumpParamemters(HttpServletRequest request) {
    	Map<String,String[]> paramMap = request.getParameterMap();
    	List<String> params = new ArrayList<String>();
    	for (String paramName : paramMap.keySet()) {
    		String[] values = paramMap.get(paramName);
			if (values == null || values.length == 0) {
				// Do nothing, no values found at all.
				String param = paramName + "=";
				params.add(param);
			}
			else if (values.length > 1) {
				for (String value : values) {
					String param = paramName + "=" + value;
					params.add(param);
				}
			}
			else {
				String param = paramName + "=" + values[0];
				params.add(param);
			}
		}
    	String sep = "\n\t--------------------------------------------------------------";
    	return sep + "\n\t" + StringUtils.join(params,"\n\t") + sep;
	}

}
