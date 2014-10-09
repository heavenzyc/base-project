package com.base.json;
/*
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;*/

/**
 * json结果
 * @author yangfan 2014年6月3日 上午1:18:54
 */
public class JsonResult {
	
	/*private final Map<String, Object> map = new LinkedHashMap<String, Object>();
	
	private final Map<String, Object> data = new LinkedHashMap<String, Object>();
	
	private JSONSerializer serializer ;
	
	public JsonResult() {
		serializer = new JSONSerializer().transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), Date.class).exclude("*.class") ;
		this.map.put("success", true);
	}
	
	*//**
	 * 加入成功标识
	 * @return
	 *//*
	public static JsonResult success() {
		JsonResult a = new JsonResult();
		a.map.put("success", true);
		return a;
	}
	
	*//**
	 * 加入失败标识
	 * @return
	 *//*
	public static JsonResult fail() {
		JsonResult a = new JsonResult();
		a.map.put("success", false);
		return a;
	}
	
	public static JsonResult fail(String msg) {
		JsonResult a = fail().msg(msg);
		return a;
	}
	
	public JsonResult setSuccess() {
		this.map.put("success", true);
		return this;
	}
	
	public JsonResult setFail() {
		this.map.put("success", false);
		return this;
	}
	
	public JsonResult setFail(String msg) {
		this.setFail();
		this.msg(msg);
		return this;
	}
	
	*//**
	 * 添加消息
	 * @param msg
	 * @return
	 *//*
	public JsonResult msg(String msg) {
		this.map.put("msg", msg);
		return this;
	}
	
//	*//**
//	 * 自定义key，value，加入json
//	 * @param key
//	 * @param value
//	 * @return CtJson
//	 *//*
//	public JsonResult add(String key, Object value){
//		this.data.put(key, value) ;
//		return this;
//	}
	
	*//**
	 * 在data字段中加入自定义属性
	 * @param key
	 * @param value
	 * @return
	 *//*
	public JsonResult data(String key, Object value) {
		this.data.put(key, value);
		return this;
	}
	
	*//**
	 * 添加data对象
	 * @param data
	 * @return
	 *//*
	public JsonResult data(Object data) {
		this.map.put("data", data);
		return this;
	}
	
	*//**
	 * 排除序列化字段（格式依照json）
	 * @param name
	 * @return
	 *//*
	public JsonResult exclude(String... name){
		this.serializer.exclude(name) ;
		return this ;
	}
	
	*//**
	 * 包含序列化字段（格式依照json）
	 * @param name
	 * @return
	 *//*
	public JsonResult include(String... name){
		this.serializer.include(name) ;
		return this ;
	}
	
	*//**
	 * 格式化所有日期字段
	 * @param format 日期格式
	 * @return CtJson
	 *//*
	public JsonResult dateFormat(String format){
		Assert.hasText("请填写有效的日期格式:" + format) ;
		this.serializer.transform(new DateTransformer(format), Date.class);
		return this;
	}
	
	*//**
	 * 格式化制定日期字段
	 * @param format 日期格式
	 * @param name 需要格式化的字段
	 * @return CtJson
	 *//*
	public JsonResult dateFormat(String format , String... name){
		Assert.hasText("请填写有效的日期格式:" + format) ;
		this.serializer.transform(new DateTransformer(format), name);
		return this;
	}
	
	
	*//**
	 * 执行json转换，返回json字符串(该方法最终执行)
	 * @return String
	 *//*
	public String toJson(){
		if(!map.containsKey("data")) {
			this.data(data);
		}
		return this.serializer.deepSerialize(map) ;
	}
	
	
	public String toString(){
		return toJson() ;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("name", "jack");
		m1.put("age", "22");
		m1.put("date", new Date());
		m1.put("date2", new Date());
		
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("name", "sam");
		m2.put("age", "33");
		m2.put("date", new Date());
		m2.put("date2", new Date());
		
		list.add(m1);
		list.add(m2);
		
		System.out.println(JsonResult.success()
				.data(list)
				.dateFormat("yyyy-MM-dd")
				.dateFormat("yyyy-MM-dd HH:mm:ss.S", "data.date2"));
		
		System.out.println(JsonResult.success().data("aaa", "bbb"));
	}*/
}
