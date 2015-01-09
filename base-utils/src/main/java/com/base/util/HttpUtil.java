package com.base.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http操作工具类
 *
 * @author xu
 *
 */
public class HttpUtil {
    /** 日志 */
    Logger log = Logger.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    /** 因http请求容易阻塞，获取请求工具类时使用不同实例操作 */
    public static HttpUtil getInstance() {
        return new HttpUtil();
    }

    /**
     * get请求并返回数据
     *
     * @param url
     * @return
     */
    public String getAndReceive(String url, Map<String, String> header) throws Exception {
        log.info("getAndReceive url=" + url);
        HttpClient client = null;
        GetMethod getMethod = null;
        try {
            client = new HttpClient();
            // 请求超时时间
            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
            // 读取超时时间
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);
            // 请求方法
            getMethod = new GetMethod(url);
            getMethod.setRequestHeader("Connection", "close");
            getMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            getMethod.getParams().setContentCharset("utf-8");
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            // 设置请求头
            if (header != null) {
                for (Entry<String, String> e : header.entrySet()) {
                    getMethod.setRequestHeader(e.getKey(), e.getValue());
                }
            }
            // 执行请求
            int statusCode = client.executeMethod(getMethod);
            // 检查http状态
            log.debug("getAndReceive statusCode=" + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("getAndReceive fail,status code=" + statusCode);
                getMethod.abort();
                return null;
            }
            // 获取返回信息
            return getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            if (getMethod != null) {
                getMethod.abort();
            }
            e.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
            log.debug("postAndReceive closed,url=" + url);
        }
        return "";
    }

    /**
     * post请求并返回数据
     *
     * @param url
     * @param data
     * @return
     */
    public String postAndReceive(String url, String data) throws Exception {
        log.info("postAndReceive,url=" + url + ",data=" + data);
        HttpClient client = null;
        PostMethod postMethod = null;
        try {
            client = new HttpClient();
            // 请求超时时间
            client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
            // 读取超时时间
            client.getHttpConnectionManager().getParams().setSoTimeout(10000);
            // 请求方法
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Connection", "close");
            postMethod.addRequestHeader("Content-Type", "text/json;charset=UTF-8");
            postMethod.getParams().setContentCharset("utf-8");
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            // 设置请求内容
            postMethod.setRequestEntity(new StringRequestEntity(data, "text/json", "UTF-8"));
            // 执行请求
            client.executeMethod(postMethod);
            // 检查http状态
            int statusCode = postMethod.getStatusLine().getStatusCode();
            // 检查http状态
            log.debug("postAndReceive statusCode=" + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("postAndReceive fail,status code=" + statusCode);
                postMethod.abort();
                return null;
            }
            // 读取返回数据
            InputStream resStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"utf-8"));
            StringBuffer resBuffer = new StringBuffer();
            String resTemp = "";
            while ((resTemp = br.readLine()) != null) {
                resBuffer.append(resTemp);
            }
            return resBuffer.toString();
        } catch (Exception e) {
            if (postMethod != null) {
                postMethod.abort();
            }
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
            log.debug("postAndReceive closed,url=" + url);
        }
        return "";
    }


    public String doPost(String url, JSONObject params, Map<String, String> header) throws Exception{
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        // sb.substring(0, sb.length() - 2);
        System.out.println("url     : " + url);
        System.out.println("header  : " + header);
        System.out.println("data    : " + params);

        // 使用POST方法
        String reciveStr = null;
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(80000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(80000);
            postMethod.setRequestHeader("Connection", "close");
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            postMethod.getParams().setContentCharset("utf-8");
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            // 填入各个表单域的值
            postMethod.addParameter("data", params.toString());
            // 将表单的值放入postMethod中
            if (header != null) {
                for (Entry<String, String> e : header.entrySet()) {
                    postMethod.setRequestHeader(e.getKey(), e.getValue());
                }
            }
            // 执行postMethod
            int statusCode = httpClient.executeMethod(postMethod);

            // 打印服务器返回的状态
            System.out.println("code    : " + statusCode);
            reciveStr = postMethod.getResponseBodyAsString();
            // 释放连接
            postMethod.releaseConnection();
        }catch(ConnectTimeoutException e){
            System.out.println("连接超时");
            throw new Exception();
        }catch(SocketTimeoutException e){
            System.out.println("线程超时");
            throw new Exception();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reciveStr;
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isNetFileAvailable(String strUrl) {
        InputStream netFileInputStream = null;
        try {
            URL url = new URL(strUrl);
            URLConnection urlConn = url.openConnection();
            netFileInputStream = urlConn.getInputStream();
            if (null != netFileInputStream) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (IOException e) {
            return false;
        }
        finally {
            try {
                if (netFileInputStream != null){
                    netFileInputStream.close();
                }
            }
            catch (IOException e) {

            }
        }
    }

}
