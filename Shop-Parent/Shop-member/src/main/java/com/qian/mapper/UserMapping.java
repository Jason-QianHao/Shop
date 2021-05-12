package com.qian.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qian.entity.UserEntity;

public interface UserMapping {

	/*
	 * 新增用户
	 */
	@Insert("insert into `shop_user` (`username`, `password`, `phone`, `email`, `openId`) values(#{username}, #{password}, #{phone}, #{email}, "
			+ "#{openId})")
	public void save(UserEntity userEntity);
	
	/*
	 * 通过username和password查询用户
	 */
	@Select("select * from `shop_user` where username=#{username}")
	public UserEntity getUserByUsername(@Param("username") String username);
	
	/*
	 * 通过phone和password查询用户
	 */
	@Select("select * from `shop_user` where phone=#{phone}")
	public UserEntity getUserByPhone(@Param("phone") String phone);
	
	/*
	 * 通过email和password查询用户
	 */
	@Select("select * from `shop_user` where email=#{email}")
	public UserEntity getUserByEmail(@Param("email") String email);
	
	/*
	 * 通过数据库Id查找用户
	 */
	@Select("select * from `shop_user` where id=#{id}")
	public UserEntity getUserById(@Param("id") int id);
	
	/*
	 * 通过openId查询用户
	 */
	@Select("select * from `shop_user` where openId=#{openId}")
	public UserEntity getUserByOpenId(@Param("openId") String openId);
}
