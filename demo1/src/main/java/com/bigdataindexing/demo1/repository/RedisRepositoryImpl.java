package com.bigdataindexing.demo1.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepositoryImpl implements RedisRepository{
	
	private static final String KEY = "Plan";
	
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, JSONObject> hashOperations;
	private ValueOperations<String, Object> ops;
	
	@Autowired
	public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
		ops = redisTemplate.opsForValue();
	}

	@Override
	public void save(JSONObject json) {
		ops.set(KEY, json.toString());	
	}

	

	@Override
	public Map<String, String> findall() {
		Set<String> redisKeys = redisTemplate.keys("*");
		// Store the keys in a List
		Map<String, String> map = new HashMap();
		Iterator<String> it = redisKeys.iterator();
		while (it.hasNext()) {
		       String key = it.next();
		       String value =  (String) ops.get(key);
		       map.put(key, value);
		}
		return map;
	}

	@Override
	public void delete(String id) {
		redisTemplate.delete(id);
	}

	@Override
	public String find(String id) {
		
		return (String) ops.get(id);
	}

}
