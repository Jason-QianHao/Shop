package com.qian.service;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegisterMailBoxProductor {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	/*
	 * 发送邮件到ActiveMq队列
	 */
	public void sendMessage(Destination destination, String msgJson) {
		jmsMessagingTemplate.convertAndSend(destination, msgJson);
	}
}
