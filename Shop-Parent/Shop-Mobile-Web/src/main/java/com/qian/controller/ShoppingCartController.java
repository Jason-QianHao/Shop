package com.qian.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;
import com.qian.entity.ItemEntity;
import com.qian.service.ShoppingCartService;
import com.qian.utils.CookieUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ShoppingCartController extends BaseController{

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	/*
	 * 购物车页面显示
	 */
	@RequestMapping("/shoppingCart")
	public String shoppingCart(HttpServletRequest req) {
		//查询redis并显示
		try {
			String loginToken = CookieUtil.getCookieByName(req, Constants.TOKEN);
			if(loginToken != null) {
				log.info("CartController/shoppingCart, 查询到cookie，开始查询购物车");
				String showCartResult = shoppingCartService.showCart(loginToken);
				JSONObject showCartObject = JSON.parseObject(showCartResult);
				if(!showCartObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
					String msg = showCartObject.getString(Constants.HTTP_RES_CODE_MSG);
					if(msg.equals(Constants.LOGINTOKEN_OUTTIME_MSG)) {
						// openId过期，需要重新登录
						return setErrorRequest(req, Constants.LOGINTOKEN_OUTTIME_MSG, Constants.LOGIN);
					}else {
						return setErrorRequest(req, msg, Constants.CART);
					}
				}
				// 查询成功,且这里有物品
				String itemsJson = showCartObject.getString(Constants.HTTP_RES_CODE_DATA);
				JSONObject cart = JSON.parseObject(itemsJson);
				String itemsIdStr = cart.getString(Constants.CART);
				List<Long> itemsIdList = JSON.parseArray(itemsIdStr, Long.class);
				try {
					List<ItemEntity> itemList = new ArrayList<>();
					for(Long id : itemsIdList) {
						// 根据每个id查询物品信息
						String itemByIdJson = itemFeign.getItemById(id);
						JSONObject itemObject = JSON.parseObject(itemByIdJson);
						if(!itemObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
							String msg = itemObject.getString(Constants.HTTP_RES_CODE_MSG);
							log.info("ShoppingCartController/shoppingCart, 没有该ItemId{}的商品，" + msg);
						}else {
							ItemEntity item = itemObject.getObject(Constants.HTTP_RES_CODE_DATA, ItemEntity.class);
							itemList.add(item);
						}
					}
					req.setAttribute("itemList", itemList);
					log.info("ShoppingCartController/shoppingCart, 查询购物车成功，" + itemList);
					return Constants.CART;
				} catch (Exception e) {
					// TODO: handle exception
					log.info("ShoppingCartController/shoppingCart, 未知错误，", e);
					return setErrorRequest(req, "未知错误", Constants.ERROR);
				}
			}else {
				log.info("CartController/shoppingCart, “token” 的cookie已经过期，跳转登录页面");
				return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("CartController/shoppingCart, cookie为空，跳转登录页面,", e);
			return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
		}
	}
	
	/*
	 * 加入购物车
	 */
	@RequestMapping("/addShopping")
	public String addShopping(HttpServletRequest req ,Long id) {
		try {
			String loginToken = CookieUtil.getCookieByName(req, Constants.TOKEN);
			if(loginToken == null) {
				log.info("CartController/addShopping, “token” 的cookie已经过期，跳转登录页面");
				return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
			}
			log.info("CartController/addShopping, 查询到cookie，开始加入购物车");
			String addCartByItemId = shoppingCartService.addCartByItemId(loginToken, id);
			JSONObject addCartObject = JSON.parseObject(addCartByItemId);
			if(!addCartObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				String msg = addCartObject.getString(Constants.HTTP_RES_CODE_MSG);
				if(msg.equals(Constants.LOGINTOKEN_OUTTIME_MSG)) {
					// openId过期，需要重新登录
					return setErrorRequest(req, Constants.LOGINTOKEN_OUTTIME_MSG, Constants.LOGIN);
				}else {
					return CartBesideError(req, msg);
				}
			}
			// 加入购物车成功，跳转购物车页面
			log.info("CartController/addShopping, 加入购物车成功，跳转购物车页面");
			return "redirect:/shoppingCart";
		} catch (Exception e) {
			// TODO: handle exception
			log.info("CartController/addShopping, cookie为空，跳转登录页面,", e);
			return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
		}
	}
	
	/*
	 * 删除物品
	 */
	@RequestMapping("/deleteByItemId")
	public String deleteByItemId(HttpServletRequest req, Long id) {
		try {
			String loginToken = CookieUtil.getCookieByName(req, Constants.TOKEN);
			if(loginToken == null) {
				log.info("CartController/deleteByItemId, “token” 的cookie已经过期，跳转登录页面");
				return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
			}
			log.info("CartController/deleteByItemId, 查询到cookie，开始删除物品");
			String deleteById = shoppingCartService.deleteById(loginToken, id);
			JSONObject addCartObject = JSON.parseObject(deleteById);
			if(!addCartObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				String msg = addCartObject.getString(Constants.HTTP_RES_CODE_MSG);
				if(msg.equals(Constants.LOGINTOKEN_OUTTIME_MSG)) {
					// openId过期，需要重新登录
					return setErrorRequest(req, Constants.LOGINTOKEN_OUTTIME_MSG, Constants.LOGIN);
				}else {
					return CartBesideError(req, msg);
				}
			}
			// 删除物品成功，跳转购物车页面
			log.info("CartController/deleteByItemId, 删除物品成功，跳转购物车页面");
			return "redirect:/shoppingCart";
		} catch (Exception e) {
			// TODO: handle exception
			log.info("CartController/deleteByItemId, cookie为空，跳转登录页面,", e);
			return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
		}
	}
	
	public String CartBesideError(HttpServletRequest req, String msg) {
		req.setAttribute("error", msg);
		return shoppingCart(req);
	}
	
	/*
	 * 跳转支付
	 */
	@RequestMapping("/localPay")
	public String localPay(HttpServletRequest req) {
		int index = 1;
		BigInteger sum = BigInteger.valueOf(0L);
		String price = req.getParameter("price" + index);
		while(price != null) {
			BigInteger bigPrice = BigInteger.valueOf(Long.valueOf(price));
			sum = sum.add(bigPrice);
			price = req.getParameter("price" + ++index);
		}
		if(sum.equals(BigInteger.valueOf(0L))) {
			log.info("PayController/localPay, 购物车很空，请先购物");
			return CartBesideError(req, "购物车很空，请先购物");
		}
		return payService.aliPay(req, sum);
	}
	
	/*
	 * 支付成功
	 */
	@RequestMapping("/paySuccess")
	public String paySuccess() {
		return Constants.PAYSUCCESS;
	}
	
	@RequestMapping("/afterPay")
	public String afterPay(HttpServletRequest req) {
		// 购物成功，直接清空购物车
		try {
			String loginToken = CookieUtil.getCookieByName(req, Constants.TOKEN);
			if(loginToken == null) {
				log.info("CartController/afterPay, “token” 的cookie已经过期，跳转登录页面");
				return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
			}
			log.info("CartController/afterPay, 查询到cookie，开始删除物品");
			String deleteAll = shoppingCartService.deleteAll(loginToken);
			JSONObject deleteAllObject = JSON.parseObject(deleteAll);
			if(!deleteAllObject.getInteger(Constants.HTTP_RES_CODE_NAME).equals(Constants.HTTP_RES_CODE_200)) {
				String msg = deleteAllObject.getString(Constants.HTTP_RES_CODE_MSG);
				if(msg.equals(Constants.LOGINTOKEN_OUTTIME_MSG)) {
					// openId过期，需要重新登录
					return setErrorRequest(req, Constants.LOGINTOKEN_OUTTIME_MSG, Constants.LOGIN);
				}else {
					return CartBesideError(req, msg);
				}
			}
			// 删除物品成功，跳转主页页面
			log.info("CartController/afterPay, 删除物品成功");
			return "redirect:/index";
		} catch (Exception e) {
			// TODO: handle exception
			log.info("CartController/afterPay, cookie为空，跳转登录页面,", e);
			return setErrorRequest(req, "身份验证信息失效，重新登录", Constants.LOGIN);
		}
	}
}
