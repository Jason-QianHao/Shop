package com.qian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.qian.entity.ItemCategoryEntity;

public interface ItemCategoryMapper {

	/*
	 * 查询所有类目
	 */
	@Select("select * from `shop_item_category`")
	public List<ItemCategoryEntity> getAllItemCategory();
}
