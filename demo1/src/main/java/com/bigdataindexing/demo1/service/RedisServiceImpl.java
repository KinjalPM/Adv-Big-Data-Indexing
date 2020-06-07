package com.bigdataindexing.demo1.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdataindexing.demo1.repository.RedisRepository;

@Service
public class RedisServiceImpl implements RedisService{
	
	private RedisRepository redisRepository;
	
	@Autowired
	public RedisServiceImpl(RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}

	@Override
	public void save(JSONObject json) {
		redisRepository.save(json);
	}

	@Override
	public String find(String id) {
		
		return redisRepository.find(id);
	}

	@Override
	public Map<String, String> findall() {
		
		return redisRepository.findall();
	}

	@Override
	public void delete(String id) {
		redisRepository.delete(id);
	}

}
