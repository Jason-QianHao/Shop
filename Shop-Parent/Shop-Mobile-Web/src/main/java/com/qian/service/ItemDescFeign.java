package com.qian.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.qian.FeignApi.ItemDesc;

@FeignClient("item")
public interface ItemDescFeign extends ItemDesc{

}
