package com.qian.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qian.entity.ItemDescEntity;

public interface ItemDescMapper {

	@Select("select * from `shop_item_desc` where id = #{id}")
	public ItemDescEntity getItemDesc(@Param("id") Long id);
}
