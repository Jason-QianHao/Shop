package com.qian.FeignApi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemDesc {

	/*
	 * 查询商品详情
	 */
	@RequestMapping("/item/getItemDescById")
	public String getItemDescById(@RequestParam("id") Long id);
}
