package com.qian.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemEntity {

	/*
	  `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
	  `title` varchar(100) NOT NULL COMMENT '商品标题',
	  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
	  `price` bigint(20) NOT NULL COMMENT '商品价格，单位为：分',
	  `num` int(10) NOT NULL COMMENT '库存数量',
	  `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
	  `image` varchar(500) not NULL COMMENT '商品图片',
	  `desc_id` bigint(10) NOT NULL COMMENT '商品详情Id',
	  `cid` bigint(10) NOT NULL COMMENT '所属栏目',
	  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
	  `created` TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP COMMENT'自动插入，创建时间',
	  `updated` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT'自动插入，修改时间',
	 */
	private Long id;
	private String title;
	private String sellPoint;
	private BigInteger price; // 以分为单位，转换元需要除以100
	private Integer num;
	private String barcode;
	private String image;
	private Long descId;
	private Long cid;
	private Byte status;
	private Timestamp created;
	private Timestamp updated;
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return "title: " + title + ", sellPoint: " + sellPoint + ", price: " + price + 
				", cid: " + cid + ", status: " + status + "\r\n";
	}
}
