package com.qian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qian.FeignApi.Item;
import com.qian.FeignApi.ItemDesc;
import com.qian.service.ItemService;

@RestController
public class ItemController implements Item, ItemDesc{

	@Autowired
	private ItemService itemService;
	
	/*
	 * 首页展示商品（非 Javadoc）
	 * @see com.qian.FeignApi.Item#getIndexItem()
	 */
	@RequestMapping("/getIndexItem")
	@Override
	public String getIndexItem() {
		// TODO 自动生成的方法存根
		return itemService.getIndexItem();
	}

	/*
	 * 按id查询商品（非 Javadoc）
	 * @see com.qian.FeignApi.Item#getItemById(java.lang.Long)
	 */
	@RequestMapping("/getItemById")
	@Override
	public String getItemById(Long id) {
		// TODO 自动生成的方法存根
		return itemService.getItemById(id);
	}

	/*
	 * 按id查询商品详情（非 Javadoc）
	 * @see com.qian.FeignApi.ItemDesc#getItemDescById(java.lang.Long)
	 */
	@RequestMapping("/getItemDescById")
	@Override
	public String getItemDescById(Long id) {
		// TODO 自动生成的方法存根
		return itemService.getItemDescById(id);
	}

}
