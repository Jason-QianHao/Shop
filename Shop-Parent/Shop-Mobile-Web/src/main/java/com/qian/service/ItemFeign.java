package com.qian.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.qian.FeignApi.Item;

@FeignClient("item")
public interface ItemFeign extends Item{

}
