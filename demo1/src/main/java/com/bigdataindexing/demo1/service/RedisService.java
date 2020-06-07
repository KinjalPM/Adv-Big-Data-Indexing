package com.bigdataindexing.demo1.service;

import java.util.Map;

import org.json.JSONObject;

public interface RedisService {

	void save(JSONObject json);
	String find(String id);
	Map<String, String> findall();
	void delete(String id);
	
}
