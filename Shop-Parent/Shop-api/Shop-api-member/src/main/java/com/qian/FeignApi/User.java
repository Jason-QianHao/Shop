package com.qian.FeignApi;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qian.entity.UserEntity;

public interface User {

	// 注意，这里后面通过feign被继承，需要带上工程的Context-path
	
	/*
	 * 注册
	 */
	@RequestMapping("/member/regist")
	public String regist(@RequestBody UserEntity userEntity);
	
	/*
	 * 登录
	 *   登录后需要将key-value放入redis
	 */
	@RequestMapping("/member/login")
	public String login(@RequestBody UserEntity userEntity, @RequestParam("name") String name);
	
	/*
	 * 通过token查询用户
	 */
	@RequestMapping("/member/getUserBytoken")
	public String getUserBytoken(@RequestParam("token") String token);
	
	/*
	 * 使用openId关联用户信息
	 */
	@RequestMapping("/member/loginByOpenId")
	public String loginByOpenId(@RequestParam("openId") String openId);
}
