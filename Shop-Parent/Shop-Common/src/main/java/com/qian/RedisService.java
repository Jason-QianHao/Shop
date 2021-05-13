package com.qian;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/*
	 * 字符串类型操作
	 */
	/**
	 * redisTemplate操作普通字符串(存值)
	 * 
	 * @param key
	 * @param value
	 */
	public void redisSetString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * redisTemplate操作普通字符串 （取值）
	 * 
	 * @param key
	 */
	public Object redisGetString(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	
	/*
	 * 列表类型操作
	 */
	/**
     * 将一个list集合存放到redis当中
     * 
     * @param key
     */
    public void redisSetList(String key, List<String> list) {
        for (String s : list) {
            // 从当前的数据 向右添加 
            // redisTemplate.opsForList().rightPush(key, integer);
            // 从当前的数据 向左添加 
            redisTemplate.opsForList().leftPush(key, s);
        }
    }
    
    /**
     * 获取list(获取0 -len 索引的数据)
     * 
     * @param key
     * @return
     */
    public Object getList(String key) {
    	Long len = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key, 0, len);
    }
    
    /**
     * 获取list指定key的长度
     * 
     * @param key
     * @return
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
    
    
	/*
	 * 哈希类型操作
	 */
    /**
     * 将map存放到reids
     *
     * @param key
     */
    public void setHash(String key, Map<String, List<Long>> hashMap) {
//        Map<String, String> hashMap = new HashMap();
        //使用RedisTemplate  有些情况会乱码
//        hashMap.put("redis", "redis");
//        hashMap.put("mysql", "mysql");
        for (Entry<String, List<Long>> keyValue : hashMap.entrySet()) {
            redisTemplate.opsForHash().put(key, keyValue.getKey(), keyValue.getValue());
        }
    }
    
    /**
     * 获取指定key1的值
     * 
     * @param key
     * @param key1
     * @return
     */
    public Object getHash(String key, String key1) {
        // 检测 是否 存在该键
        boolean isKey = redisTemplate.opsForHash().hasKey(key, key1);
        return redisTemplate.opsForHash().get(key, key1);
    }
    
    /**
     * 获取指定key的所有值
     * 
     * @param key
     * 
     * @return
     */
    public Object getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * 根据具体key移除具体的值
     * 
     * @param key
     * 
     * @return
     */
    public void removeKey(String key, String key1) {
        redisTemplate.opsForHash().delete(key, key1);
    }
    
    /**
     * 移除key值 则key里面的所有值都被移除
     * 
     * @param key
     * 
     * @return
     */
    public void removeStringKey(String key) {
        redisTemplate.delete(key);
    }
    
    
	/*
	 * 集合类型操作
	 */
    /**
     * set存入redis中
     * 
     * @param key
     */
    public void setSet(String key) {
        Set<Object> set = new HashSet();
        set.add("setKey");
        set.add("tesetKey");
        for (Object object : set) {
            redisTemplate.opsForSet().add(key, object);
        }
    }
    
    /**
     * 从redis中取出set
     * 
     * @param key
     * @return
     */
    public Object getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }
    
    
	/*
	 * 有序集合类型操作
	 */
    /**
     * sortset存入redis中
     * 
     * @param key
     */
    public void setZSet(String key) {
        Set<Object> set = new HashSet();
        set.add("setKey");
        set.add("tesetKey");
        int i = 0;
        for (Object object : set) {
            i++;
            redisTemplate.opsForZSet().add(key, object, i);
        }
    }
    
    /**
     * 从redis中取出sortset
     * 
     * @param key
     * @return
     */
    public Object getZSet(String key) {
        Long size = redisTemplate.opsForZSet().size(key);
        return redisTemplate.opsForZSet().rangeByScore(key, 0, size);
    }
    
    
    /*
     * RedisTemplate操作5种基本类型数据，有一些共同的API 
     */
    /**
     * 
     * 指定缓存失效时间
     * 
     * @param key
     *            键
     * 
     * @param time
     *            时间(秒)
     * 
     * @return
     * 
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 
     * 判断key是否存在
     * 
     * @param key
     *            键
     * 
     * @return true 存在 false不存在
     * 
     */
    public boolean checkKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 移除key
     * @param key
     */
    public void removeKey(String key) {
        redisTemplate.delete(key);
    }
}