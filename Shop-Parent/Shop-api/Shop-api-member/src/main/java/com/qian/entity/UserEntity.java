package com.qian.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntity {

	/*
	`id` int(20) NOT NUll  auto_increment COMMENT'主键（自增长）',
	`username` VARCHAR(50) NOT NULL COMMENT'用户名',
	`password` VARCHAR(32) NOT NULL COMMENT'密码，加密存储',
	`phone` VARCHAR(20) DEFAULT NULL COMMENT'手机号',
	`email` VARCHAR(50) DEFAULT NULL COMMENT'邮箱',
	`openId` VARCHAR(100) DEFAULT NULL COMMENT'登录Id',
	`created` TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP COMMENT'自动插入，创建时间',
	`updated` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT'自动插入，修改时间',
	 */
	private int id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String openId;
	private Timestamp created;
	private Timestamp updated;
}
