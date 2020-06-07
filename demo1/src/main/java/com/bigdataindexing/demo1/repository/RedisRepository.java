package com.bigdataindexing.demo1.repository;

import java.util.Map;

import org.json.JSONObject;

public interface RedisRepository {

	void save(JSONObject json);
	String find(String id);
	Map<String, String> findall();
	void delete(String id);
}
