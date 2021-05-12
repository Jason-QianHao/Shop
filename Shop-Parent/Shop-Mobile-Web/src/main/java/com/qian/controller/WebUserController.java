package com.qian.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;
import com.qian.entity.UserEntity;
import com.qian.utils.CookieUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebUserController extends BaseController{

	/*
	 * 登录
	 */
	@RequestMapping("/")
	public String localLogin(HttpServletRequest req) {
		//查询cookie登录
		try {
			String cookieByName = CookieUtil.getCookieByName(req, Constants.TOKEN);
			if(cookieByName != null) {
				log.info("WebUserController/localLogin, 查询到cookie，直接登录主页");
				return "redirect:/index";
			}else {
				log.info("WebUserController/localLogin, “token” 的cookie已经过期，跳转登录页面");
				return Constants.LOGIN;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("WebUserController/localLogin, cookie为空，跳转登录页面");
			return Constants.LOGIN;
		}
	}
	@RequestMapping("/loginPage")
	public String loginPage() {
		return Constants.LOGIN;
	}
	
	@RequestMapping("/login")
	public String login(UserEntity userEntity, String name, HttpServletRequest request, HttpServletResponse response) {
		try {
			String loginResult = userFeign.login(userEntity, name);
			JSONObject loginObject = JSON.parseObject(loginResult);
			String msg = loginObject.getString(Constants.HTTP_RES_CODE_MSG);
			// 登录出错
			if(!loginObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				//redisError
				if(loginObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_502)) {
					// Redis等服务器处理错误，但不影响功能，只记录日志，不做用户界面处理
					log.info("WebUserController/login, 服务器处理错误: " + msg);
				}else {
					// 登录失败500
					log.info("WebUserController/login, 登录错误: " + msg);
					return setErrorRequest(request, msg, Constants.LOGIN);
				}
			}
			// 登录成功200，DATA中有loginToken，存放Cookie
			try {
				String loginToken = loginObject.getString(Constants.HTTP_RES_CODE_DATA);
				CookieUtil.addCookie(response, Constants.TOKEN, loginToken, Constants.COOKIE_OUTTIME);
				log.info("WebUserController/login, 存储Cookie成功，登录成功");
			} catch (Exception e) {
				// TODO: handle exception
				log.info("WebUserController/login, 存储Cookie失败，继续登录，错误：", e);
			}
			return "redirect:/index";
		} catch (Exception e) {
			// TODO: handle exception
			log.info("WebUserController/login, 登录失败: ", e);
			return setErrorRequest(request, "登录失败", Constants.LOGIN);
		}
	}
	
	/*
	 * 注册
	 */
	@RequestMapping("/localRegist")
	public String localRegist() {
		return Constants.REGIST;
	}
	@RequestMapping("/regist")
	public String regist(UserEntity userEntity, HttpServletRequest request) {
		try {
			String regist = userFeign.regist(userEntity);
			JSONObject registResult = JSON.parseObject(regist);
			String msg = registResult.getString(Constants.HTTP_RES_CODE_MSG);
			// 注册失败，返回注册页面
			if(!registResult.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				log.info("WebUserController/regist, 注册失败, 跳转注册页面" + msg);
				return setErrorRequest(request, msg, Constants.REGIST);
			}
			// 注册成功，返回登录页面
			log.info("WebUserController/regist, 注册成功, 跳转登录页面");
			return Constants.LOGIN;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("WebUserController/regist, 注册失败: ", e);
			return setErrorRequest(request, "注册失败", Constants.REGIST);
		}
	}
}
