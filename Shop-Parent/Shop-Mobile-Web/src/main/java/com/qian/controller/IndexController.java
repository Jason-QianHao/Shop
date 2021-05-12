package com.qian.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;
import com.qian.entity.ItemDescEntity;
import com.qian.entity.ItemEntity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController extends BaseController{

	/*
	 * shop商场首页
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")
	public String index(HttpServletRequest req) {
		// 这里try是因为可能RPC调用错误
		try {
			// 查询所有商品
			String indexItem = itemFeign.getIndexItem();
			JSONObject itemJson = JSON.parseObject(indexItem);
			Map<String, Object> mapItem = (Map<String, Object>)itemJson.get(Constants.HTTP_RES_CODE_DATA);
			req.setAttribute("mapItem", mapItem);
			// 封装参数后，跳转主页
			log.info("IndexController/index, 查询商品成功，前往首页");
			return Constants.INDEX;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("IndexController/index, 查询商品失败，前往失败页,", e);
			return setErrorRequest(req, "获取“千氏商场”首页失败", Constants.ERROR);
		}
	}
	
	
	/*
	 * 商品详情
	 */
	@RequestMapping("/itemDesc")
	public String itemDesc(HttpServletRequest req, Long id) {
		try {
			// 根据id查询商品
			String itemById = itemFeign.getItemById(id);
			JSONObject itemJson = JSON.parseObject(itemById);
			// 查询失败
			if(!itemJson.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				String msg = itemJson.getString(Constants.HTTP_RES_CODE_MSG);
				log.info("IndexController/itemDesc, 查询商品失败，跳转失败页面，msg:" + msg);
				return setErrorRequest(req, msg, Constants.ERROR);
			}
			// 取出商品
			ItemEntity item = itemJson.getObject(Constants.HTTP_RES_CODE_DATA, ItemEntity.class);
			req.setAttribute("item", item);
			
			// 查询商品详情
			String itemDescById = itemDescFeign.getItemDescById(item.getDescId());
			JSONObject itemDescJson = JSON.parseObject(itemDescById);
			// 查询商品详情失败
			if(!itemDescJson.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				String msg = itemDescJson.getString(Constants.HTTP_RES_CODE_MSG);
				log.info("IndexController/itemDesc, 查询商品详情失败，跳转失败页面，msg:" + msg);
				return setErrorRequest(req, msg, Constants.ERROR);
			}
			// 取出商品详情
			ItemDescEntity itemDesc = itemDescJson.getObject(Constants.HTTP_RES_CODE_DATA, ItemDescEntity.class);
			req.setAttribute("itemDesc", itemDesc);
			return Constants.ITEMDESC;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("IndexController/itemDesc, 查询商品详情失败，前往失败页,", e);
			return setErrorRequest(req, "获取商品详情失败失败", Constants.ERROR);
		}
	}
}
