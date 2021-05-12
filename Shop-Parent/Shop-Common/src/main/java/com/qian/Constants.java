package com.qian;

public interface Constants {

	// ResultApi响应结果
	String HTTP_RES_CODE_NAME = "code";
	String HTTP_RES_CODE_MSG = "msg";
	String HTTP_RES_CODE_DATA = "data";
	String HTTP_RES_CODE_200_VALUE = "success";
	String HTTP_RES_CODE_500_VALUE = "fail";
	Integer HTTP_RES_CODE_200 = 200; // 响应成功
	Integer HTTP_RES_CODE_400 = 400; // 参数错误
	Integer HTTP_RES_CODE_500 = 500; // 服务器错误
	Integer HTTP_RES_CODE_502 = 502; // Redis服务器错误
	
	// redis,购物车信息保存时间
	Integer USER_TOKEN_TERMVALIDITY = 60 * 60 * 24 * 90;
	String LOGINTOKEN_OUTTIME_MSG = "用户身份验证失效，重新登录";
	
	// 页面
	String LOGIN = "login";
	String REGIST = "regist";
	String INDEX = "index";
	String ITEMDESC = "itemDesc";
	String CART = "cart";
	String PAY = "pay";
	String PAYSUCCESS = "paySuccess";
	
	// 前端页面req的域键值/错误页面
	String ERROR = "error";
	
	// cookie
	String TOKEN = "token";
	Integer COOKIE_OUTTIME = 60 * 60 * 24 * 3;
	
	// 邮件报文头
	String SMS_MAIL = "sms_mail";
}
