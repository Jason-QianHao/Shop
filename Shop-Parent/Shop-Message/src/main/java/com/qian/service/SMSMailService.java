package com.qian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SMSMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;
	
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
	public void RecieveMQAndSendMessage(JSONObject jsonObject) {
		String mail = jsonObject.getString("mail");
		String userName = jsonObject.getString("userName");
		// 发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(mail);
		message.setSubject("千氏商场用户注册成功");
		message.setText("欢迎" + userName + "! \r\n 您已经成功注册本商场用户，请尽情欣赏~");
		javaMailSender.send(message);
		log.info("SMSMailService/RecieveMQAndSendMessage, 发送邮件给" + userName);
	}
}
