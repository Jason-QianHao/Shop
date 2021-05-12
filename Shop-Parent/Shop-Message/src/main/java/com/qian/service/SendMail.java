package com.qian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMail {

	@Autowired
	private SMSMailService smsMailService;
	
	@JmsListener(destination = "message_queue")
	public void sendMail(String msgJson) {
		if(msgJson == null) {
			return;
		}
		log.info("SendMail/sendMail, 收到Mq消息：" + msgJson);
		JSONObject root = JSON.parseObject(msgJson);
		JSONObject header = root.getJSONObject("header");
		JSONObject content = root.getJSONObject("content");
		String interfaceType = header.getString("interfaceType");
		if(!interfaceType.equals(Constants.SMS_MAIL)) {
			log.info("SendMail/sendMail, 接受邮件信息错误");
			return;
		}
		smsMailService.RecieveMQAndSendMessage(content);
	}
}
