package com.qian;

import com.alibaba.fastjson.JSONObject;

public class ResultApi {

	// 响应成功
	public static String success(Object data) {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
	}
	public static String success()
	{
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
	}	
	
	// 响应失败
	public static String error(String msg) {
		return setResult(Constants.HTTP_RES_CODE_500, msg, null);
	}
	
	// redis响应失败
	public static String redisError(String msg) {
		return setResult(Constants.HTTP_RES_CODE_502, msg, null);
	}
	
	// 参数错误
	public static String parmError(String msg) {
		return setResult(Constants.HTTP_RES_CODE_400, msg, null);
	}
	
	// 自定义返回结果
	public static String setResult(Integer code, String msg, Object data) {
		JSONObject res = new JSONObject();
		res.put(Constants.HTTP_RES_CODE_NAME, code);
		res.put(Constants.HTTP_RES_CODE_MSG, msg);
		if(data != null)
			res.put(Constants.HTTP_RES_CODE_DATA, data);
		return res.toJSONString();
	}
}
