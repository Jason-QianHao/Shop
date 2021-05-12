package com.qian.entity;

import java.sql.Timestamp;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class ItemCategoryEntity {

	/*
	  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
	  `name` varchar(50) not NULL COMMENT '类目名称',
	  `img` varchar(150) not NULL COMMENT '图片地址',
	  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
	  `created` TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP COMMENT'自动插入，创建时间',
	  `updated` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT'自动插入，修改时间',
	 */
	private Long id;
	private String name;
	private String img;
	private Integer status;
	private Timestamp created;
	private Timestamp updated;
}
