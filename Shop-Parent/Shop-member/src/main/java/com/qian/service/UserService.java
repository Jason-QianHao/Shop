package com.qian.service;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;
import com.qian.RedisService;
import com.qian.ResultApi;
import com.qian.entity.UserEntity;
import com.qian.mapper.UserMapping;
import com.qian.utils.MD5Util;
import com.qian.utils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserMapping userMapping;
	@Autowired
	private RedisService redisService;
	@Autowired
	private RegisterMailBoxProductor registerMailBoxProductor;
	@Value("${message.queue}")
	private String MESSAGE_QUEUE;
	
	/*
	 * 用户注册
	 */
	public String regist(UserEntity userEntity) {
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		userEntity.setPassword(MD5Util.MD5(phone + password));
		userEntity.setOpenId(TokenUtil.getToken());
		// 插入数据库
		try {
			userMapping.save(userEntity);
			log.info("UserService/regist, 新增用户插入数据库成功");
			// 新增用户成功后，发送邮件通知
				// 定义队列
			ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGE_QUEUE);
				// 组装报文
			String message = message(userEntity.getEmail(), userEntity.getUsername());
				// 发送邮件
			registerMailBoxProductor.sendMessage(activeMQQueue, message);
			log.info("UserService/regist, 发送注册邮件：" + message);
			return ResultApi.success();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("UserService/regist, 新增用户插入数据库失败！，", e);
			return ResultApi.error("注册失败");
		}
	}
	
	/*
	 * 自定义邮件报文：
	 * {
	 * 	"header":{
	 * 				"interfaceType":"接口类型"
	 * 			},
	 * 	"content":{
	 * 				"mail":"",
	 * 				"userName":""
	 * 			}
	 * }
	 */
	private String message(String mail, String username) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", Constants.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("mail", mail);
		content.put("userName", username);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}
	
	/*
	 * 用户登录
	 */
	public String login(UserEntity userEntity, String name) {
		String password = userEntity.getPassword();
		try {
			UserEntity userByUsername = userMapping.getUserByUsername(name);
			UserEntity userByEmail = userMapping.getUserByEmail(name);
			UserEntity userByPhone = userMapping.getUserByPhone(name);
			UserEntity user;
			boolean flag = false;
			if(userByUsername != null) {
				log.info("UserService/login, 通过username登录");
				String pass = MD5Util.MD5(userByUsername.getPhone() + password);
				if(pass.equals(userByUsername.getPassword())) {
					flag = true;
				}
				user = userByUsername;
			}else if(userByEmail != null) {
				log.info("UserService/login, 通过email登录");
				String pass = MD5Util.MD5(userByEmail.getPhone() + password);
				if(pass.equals(userByEmail.getPassword())) {
					flag = true;
				}
				user = userByEmail;
			}else if(userByPhone != null) {
				log.info("UserService/login, 通过phone登录");
				String pass = MD5Util.MD5(name + password);
				if(pass.equals(userByPhone.getPassword())) {
					flag = true;
				}
				user = userByPhone;				
			}else {
				log.info("UserService/login, 账号不存在，请先注册");
				return ResultApi.error("账号不存在, 请先注册");
			}
			if(!flag) {
				log.info("UserService/login, 账号或密码错误");
				return ResultApi.error("账号或密码错误");
			}
			// 登录成功后，存储redis
			String loginToken  = TokenUtil.getToken();
			try {
				redisService.setStr(loginToken, user.getOpenId(), Constants.USER_TOKEN_TERMVALIDITY);
				log.info("UserService/login, 登录成功，账号：{}，loginToken:{}", name, loginToken);
				return ResultApi.success(loginToken);
			} catch (Exception e) {
				// TODO: handle exception
				log.info("UserService/login, Redis存储失败", e);
				return ResultApi.redisError("Redis存储失败"); // 可以登录成功，但不能缓存
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("UserService/login, 登录错误, ", e);
			return ResultApi.error("系统错误，登录失败");
		}
	}
}
