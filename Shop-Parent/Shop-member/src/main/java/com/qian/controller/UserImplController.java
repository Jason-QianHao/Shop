package com.qian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qian.ResultApi;
import com.qian.FeignApi.User;
import com.qian.entity.UserEntity;
import com.qian.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserImplController implements User{
	
	@Autowired
	private UserService userService;

	/*
	 * 注册（非 Javadoc）
	 * @see com.qian.FeignApi.User#regist(com.qian.entity.UserEntity)
	 */
	@RequestMapping("/regist") // 这里需要覆盖User接口中的RequestMapping
	@Override
	public String regist(@RequestBody UserEntity userEntity) {
		// TODO 自动生成的方法存根
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		String email = userEntity.getEmail();
		String phone = userEntity.getPhone();
		if(username.equals("") || username == null) {
			log.info("UserImplController/regist, 用户名为空");
			return ResultApi.error("用户名不能为空");
		}
		if(password.equals("") || password == null) {
			log.info("UserImplController/regist, 密码为空");
			return ResultApi.error("密码不能为空");
		}
		if(email.equals("") || email == null) {
			log.info("UserImplController/regist, 邮箱为空");
			return ResultApi.error("邮箱不能为空");
		}
		if(phone.equals("") || phone == null) {
			log.info("UserImplController/regist, 手机号为空");
			return ResultApi.error("手机号不能为空");
		}
		String registResult = userService.regist(userEntity);
		return registResult;
	}

	/*
	 * 登录（非 Javadoc）
	 * @see com.qian.FeignApi.User#login(com.qian.entity.UserEntity, java.lang.String)
	 */
	@RequestMapping("/login")
	@Override
	public String login(@RequestBody UserEntity userEntity, String name) {
		// TODO 自动生成的方法存根
		String password = userEntity.getPassword();
		if(name.equals("") || name == null) {
			log.info("UserImplController/login, 用户名为空");
			return ResultApi.error("用户名不能为空");
		}
		if(password.equals("") || password == null) {
			log.info("UserImplController/login, 密码为空");
			return ResultApi.error("密码不能为空");
		}
		String loginResult = userService.login(userEntity, name);
		return loginResult;
	}

	@Override
	public String getUserBytoken(String token) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String loginByOpenId(String openId) {
		// TODO 自动生成的方法存根
		return null;
	}

}
