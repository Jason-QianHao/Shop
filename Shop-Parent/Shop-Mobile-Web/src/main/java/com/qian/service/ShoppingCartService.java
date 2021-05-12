package com.qian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qian.Constants;
import com.qian.RedisService;
import com.qian.ResultApi;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartService {

	@Autowired
	private RedisService redisService;

	/*
	 * redis中购物车结构 key: userOpenId value: { "cart":["itemId1",
	 * "itemId2","itemId3"...] }
	 */

	/*
	 * 购物车查询
	 */
	public String showCart(String loginToken) {
		String openId = checkOpenId(loginToken);
		if (openId == null) {
			// redis存储的openid过期了，需要重新存储
			log.info("ShoppingCartService/showCart, 用户openId过期，重新登录");
			return ResultApi.error(Constants.LOGINTOKEN_OUTTIME_MSG);
		} else if (openId.endsWith(Constants.ERROR)) {
			// redis服务器错误
			log.info("ShoppingCartService/showCart, 查询redis失败, " + openId);
			return ResultApi.error("查询购物车失败");
		}
		// 用户身份有效，继续查询相关购物信息
		String itemsJson = (String) redisService.get(openId);
		if (itemsJson == null) {
			// redis存储的购物车信息过期了
			log.info("ShoppingCartService/showCart, 购物车信息过期或没有物品");
			return ResultApi.error("购物车信息过期或没有物品");
		} else {
			log.info("ShoppingCartService/showCart, 查询用户购物车信息成功: " + itemsJson);
			return ResultApi.success(itemsJson);
		}
	}

	/*
	 * 加入购物车
	 */
	public String addCartByItemId(String loginToken, Long itemId) {
		// 检查登录信息
		String openId = checkOpenId(loginToken);
		if (openId == null) {
			// redis存储的openid过期了，需要重新存储
			log.info("ShoppingCartService/addCartByItemId, 用户openId过期，重新登录");
			return ResultApi.error(Constants.LOGINTOKEN_OUTTIME_MSG);
		} else if (openId.endsWith(Constants.ERROR)) {
			// redis存储的openid过期了，需要重新存储
			log.info("ShoppingCartService/addCartByItemId, 查询redis失败, " + openId);
			return ResultApi.error("查询购物车失败");
		}
		// 用户身份有效，继续查询相关购物信息
		String itemsJson = (String) redisService.get(openId);
		log.info("ShoppingCartService/addCartByItemId, 查询用户购物车信息成功: " + itemsJson);
		List<Long> itemsIdList;
		if (itemsJson == null) {
			// redis存储的购物车信息过期了
			log.info("ShoppingCartService/addCartByItemId, 购物车信息过期或没有物品");
			itemsIdList = new ArrayList<>();
			itemsIdList.add(itemId);
		} else {
			JSONObject cart = JSON.parseObject(itemsJson);
			String itemsIdStr = cart.getString(Constants.CART);
			itemsIdList = JSON.parseArray(itemsIdStr, Long.class);
			for (Long id : itemsIdList) {
				if (id == itemId) {
					log.info("ShoppingCartService/addCartByItemId, 该物品已经在购物车中");
					return ResultApi.error("该物品已经在购物车中");
				}
			}
			itemsIdList.add(itemId);
		}
		JSONObject newcart = new JSONObject();
		newcart.put(Constants.CART, JSON.toJSONString(itemsIdList));
		redisService.setStr(openId, newcart.toJSONString(), Constants.USER_TOKEN_TERMVALIDITY);
		log.info("ShoppingCartService/addCartByItemId, itemId为{}的商品加入购物车成功", itemId);
		return ResultApi.success();

	}


	/*
	 * 按照id删除物品
	 */
	public String deleteById(String loginToken, Long id) {
		try {
			String openId = checkOpenId(loginToken);
			if (openId == null) {
				// redis存储的openid过期了，需要重新存储
				log.info("ShoppingCartService/deleteById, 用户openId过期，重新登录");
				return ResultApi.error(Constants.LOGINTOKEN_OUTTIME_MSG);
			} else if (openId.endsWith(Constants.ERROR)) {
				// redis存储的openid过期了，需要重新存储
				log.info("ShoppingCartService/deleteById, 查询redis失败, " + openId);
				return ResultApi.error("删除物品失败");
			}
			// 用户身份有效，继续查询相关购物信息
			String itemsJson = (String) redisService.get(openId);
			log.info("ShoppingCartService/deleteById, 查询用户购物车信息成功: " + itemsJson);
			if (itemsJson == null) {
				// redis存储的购物车信息过期了
				log.info("ShoppingCartService/deleteById, 购物车信息过期或没有物品");
				return ResultApi.error("物品已经过期");
			}
			JSONObject cart = JSON.parseObject(itemsJson);
			String itemsIdStr = cart.getString(Constants.CART);
			List<Long> itemsIdList = JSON.parseArray(itemsIdStr, Long.class);
			itemsIdList.remove(id);
			log.info("ShoppingCartService/deleteById, 删除id为{}的物品成功", id);
			// 刷新Redis
			JSONObject newcart = new JSONObject();
			newcart.put(Constants.CART, JSON.toJSONString(itemsIdList));
			redisService.setStr(openId, newcart.toJSONString(), Constants.USER_TOKEN_TERMVALIDITY);
			log.info("ShoppingCartService/deleteById, itemId为{}的商品加入购物车成功", id);
			return ResultApi.success();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("ShoppingCartService/deleteById, 删除失败，", e);
			return ResultApi.error("删除物品失败");
		}
	}
	
	/*
	 * 清空购物车
	 */
	public String deleteAll(String loginToken) {
		try {
			String openId = checkOpenId(loginToken);
			if (openId == null) {
				// redis存储的openid过期了，需要重新存储
				log.info("ShoppingCartService/deleteAll, 用户openId过期，重新登录");
				return ResultApi.error(Constants.LOGINTOKEN_OUTTIME_MSG);
			} else if (openId.endsWith(Constants.ERROR)) {
				// redis存储的openid过期了，需要重新存储
				log.info("ShoppingCartService/deleteAll, 查询redis失败, " + openId);
				return ResultApi.error("删除物品失败");
			}
			// 用户身份有效，继续查询相关购物信息
			redisService.delete(openId);
			log.info("ShoppingCartService/deleteAll, 清空购物车信息成功");
			return ResultApi.success();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("ShoppingCartService/deleteAll, 清空购物车信息失败");
			return ResultApi.error("清空购物车信息失败");
		}
	}
	
	/*
	 * 判断用户信息是否过期
	 */
	public String checkOpenId(String loginToken) {
		try {
			String openId = null;
			openId = (String) redisService.get(loginToken);
			log.info("ShoppingCartService/showCart, 查询redis成功,openId: " + openId);
			return openId;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("ShoppingCartService/showCart, 查询redis失败, ", e);
			return Constants.ERROR + e.toString();
		}
	}
}
