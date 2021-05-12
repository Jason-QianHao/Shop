package com.qian.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.ResultApi;
import com.qian.entity.ItemCategoryEntity;
import com.qian.entity.ItemDescEntity;
import com.qian.entity.ItemEntity;
import com.qian.mapper.ItemCategoryMapper;
import com.qian.mapper.ItemDescMapper;
import com.qian.mapper.ItemMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemService {

	@Autowired
	private ItemCategoryMapper itemCategoryMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	/*
	 * 首页展示商品
	 */
	public String getIndexItem() {
		List<ItemCategoryEntity> allItemCategory = itemCategoryMapper.getAllItemCategory();
		Map<String, Object> root = new HashMap<>(); // 这里不适用Json，是因为后面可以方便直接传给前端页面的参数
		if(allItemCategory != null) {
			for(ItemCategoryEntity itemCategoryEntity : allItemCategory) {
				Long id = itemCategoryEntity.getId();
				String name = itemCategoryEntity.getName();
				// 该类目下的商品集合
				List<ItemEntity> itemListByCid = itemMapper.getItemListByCid(id);
				log.info("ItemService/getIndexItem, 按类目分类查询对应商品：" + itemListByCid);
				if(!itemListByCid.isEmpty()) {
					root.put(name, itemListByCid);
				}
			}
		}
		return ResultApi.success(root);
	}
	
	/*
	 * 按Id查询商品
	 */
	public String getItemById(Long id) {
		ItemEntity itemById = itemMapper.getItemById(id);
		if(itemById == null) {
			log.info("ItemService/getItemById, 没有查到Id为" + id + "的商品");
			return ResultApi.error("没有查到该商品");
		}else {
			log.info("ItemService/getItemById, 查到Id为" + id + "的商品 :" + itemById);
			return ResultApi.success(itemById);
		}
	}
	
	/*
	 * 按id查询商品详情
	 */
	public String getItemDescById(Long id) {
		ItemDescEntity itemDesc = itemDescMapper.getItemDesc(id);
		if(itemDesc == null) {
			log.info("ItemService/getItemDescById, 没有查到Id为" + id + "的商品详情");
			return ResultApi.error("没有查到该商品详情");
		}else {
			log.info("ItemService/getItemDescById, 查到Id为" + id + "的商品详情 :" + itemDesc);
			return ResultApi.success(itemDesc);
		}
	}
}
