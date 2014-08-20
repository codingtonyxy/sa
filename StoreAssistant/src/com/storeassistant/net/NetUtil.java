package com.storeassistant.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ÍøÂç¹¤¾ßÀà
 * @author Administrator
 *
 */
public class NetUtil {
	
	private static HttpURLConnection getConnection(String url){
		URL aUrl;
		HttpURLConnection urlConnection = null;
		try {
			aUrl = new URL(url);
			urlConnection = (HttpURLConnection)aUrl.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return urlConnection;
	}
	
	public static HashMap<String, Object> get(String url, HashMap<String, Object> param){
		if(url == null || url.length() <= 0){
			return new HashMap<String, Object>(0);
		}
		if(param != null && param.size() > 0){
			StringBuilder urlSb = new StringBuilder(url);
			urlSb.append("?");
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				if(key == null || key.length() <= 0){
					continue;
				}
				urlSb.append(key);
				urlSb.append("=");
				Object value = param.get(key);
				if(value != null){
					urlSb.append(value);
				}
				urlSb.append("&");
			}
			url = urlSb.toString();
		}
		
		HttpURLConnection conn = getConnection(url);
		if(conn == null){
			return new HashMap<String, Object>(0);
		}
		
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			baos = new ByteArrayOutputStream();
			bos = new BufferedOutputStream(baos);
			bis = new BufferedInputStream(conn.getInputStream());
			byte[] buffer = new byte[512];
			while(bis.read(buffer) != -1){
				bos.write(buffer);
			}
			bos.flush();
			byte[] data = baos.toByteArray();
			String content = new String(data, "utf-8");
			System.out.println("content:"+content);
			if(content == null || content.length() <= 0){
				return new HashMap<String, Object>(0);
			}
			JSONObject json = new JSONObject(content);
			return jsonToMap(json);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.disconnect();
			try {
				bis.close();
				baos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<String, Object>(0);
	}
	
	private static HashMap<String, Object> jsonToMap(JSONObject json){
		if(json == null){
			return new HashMap<String, Object>(0);
		}
		int len = json.length();
		if(len <= 0){
			return new HashMap<String, Object>(0);
		}
		HashMap<String, Object> ret = new HashMap<String, Object>(len);
		Iterator iter = json.keys();
		while(iter.hasNext()){
			Object o = iter.next();
			if(o == null){
				continue;
			}
			String  key = (String)o;
			Object value;
			try {
				value = json.get(key);
			} catch (JSONException e) {
				e.printStackTrace();
				ret.put(key, null);
				continue;
			}
			ret.put(key, value);
		}
		System.out.println("ret"+ret);
		return ret;
	}
	
	public static void main(String[] args) {
		String url = "www.baidu.com";
		HashMap param = null;
		System.out.println(get(url, param));
	}

}
