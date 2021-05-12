package com.qian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.qian.Constants;
import com.qian.service.ItemDescFeign;
import com.qian.service.ItemFeign;
import com.qian.service.PayService;
import com.qian.service.UserFeign;

public class BaseController {

	@Autowired
	protected UserFeign userFeign;
	@Autowired
	protected ItemFeign itemFeign;
	@Autowired
	protected ItemDescFeign itemDescFeign;
	@Autowired
	protected PayService payService;
	
	public String setErrorRequest(HttpServletRequest req, String msg, String address) {
		req.setAttribute(Constants.ERROR, msg);
		return address;
	}
}
