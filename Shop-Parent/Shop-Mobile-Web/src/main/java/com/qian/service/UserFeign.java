package com.qian.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.qian.FeignApi.User;

@FeignClient("member")
public interface UserFeign extends User{

}
