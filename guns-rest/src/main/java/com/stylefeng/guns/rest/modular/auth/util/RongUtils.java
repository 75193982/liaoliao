package com.stylefeng.guns.rest.modular.auth.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;



public class RongUtils {
	private static final String APPKEY = "RC-App-Key";
	private static final String NONCE = "RC-Nonce";
	private static final String TIMESTAMP = "RC-Timestamp";
	private static final String SIGNATURE = "RC-Signature";
 
	public static String getToken(String id) {
 
		String nonce = String.valueOf(Math.random() * 1000000);
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		StringBuilder toSign = new StringBuilder("IqGIA0sHd7rQG").append(nonce).append(timestamp);
		String sign = CodeUtil.hexSHA1(toSign.toString());
 
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(APPKEY, "");
		headers.put(NONCE, nonce);
		headers.put(TIMESTAMP, timestamp);
		headers.put(SIGNATURE, sign);
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		String doPost = null;
		try {
			doPost = HttpClientUtil.doPost("http://api.cn.ronghub.com/user/getToken.json", id, sign,nonce,timestamp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		com.alibaba.fastjson.JSONObject parseObject = JSON.parseObject(doPost);
 
		return parseObject.getString("token");
 
	}
	
	
	public static void main(String[] args) {
		getToken("123456");
	}
}
