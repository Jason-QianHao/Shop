package com.qian.FeignApi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface Item {

	/*
	 * 首页展示商品
	 */
	@RequestMapping("/item/getIndexItem")
	public String getIndexItem();
	
	/*
	 * 查询商品
	 */
	@RequestMapping("/item/getItemById")
	public String getItemById(@RequestParam("id") Long id);
}
