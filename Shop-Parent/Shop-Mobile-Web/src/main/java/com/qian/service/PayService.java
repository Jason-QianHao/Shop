package com.qian.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class PayService{

	/*
	 * 支付宝付款
	 */
	public String aliPay(HttpServletRequest req, BigInteger sum) {
		// TODO 自动生成的方法存根
		req.setAttribute("sum", sum);
		return "pay";
	}
}
