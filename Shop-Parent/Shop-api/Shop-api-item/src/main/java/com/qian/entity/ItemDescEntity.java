package com.qian.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDescEntity {

	/*
	`id` bigint(20) NOT NULL COMMENT '商品ID',
  	`itemdesc` text COMMENT '商品描述',
	`created` TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP COMMENT'自动插入，创建时间',
	`updated` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT'自动插入，修改时间',
	 */
	private Long id;
	private String itemdesc;
	private Timestamp created;
	private Timestamp updated;
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return itemdesc;
	}
}
