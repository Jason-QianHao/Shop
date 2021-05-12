package com.qian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qian.entity.ItemEntity;

public interface ItemMapper {

	/*
	 * 通过cid 联合查询特定类目的商品集合
	 */
	@Select("select a.* from `shop_item` as a inner join `shop_item_Category` as b on "
			+ "a.cid=b.id where b.id = #{cid}")
	public List<ItemEntity> getItemListByCid(@Param("cid") Long cid);
	
	/*
	 * 通过id查询商品
	 */
	@Select("select * from `shop_item` where id = #{id}")
	public ItemEntity getItemById(@Param("id") Long id);
}
