package com.bigdataindexing.demo1;

import javax.annotation.PostConstruct;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigdataindexing.demo1.repository.RedisRepository;

import org.everit.json.schema.ValidationException;

@Component
public class InitialSetup {
	
	@Autowired
	private RedisRepository repository;

//	@PostConstruct
//	private void init() {
//		JSONObject jsonSchema = new JSONObject(
//				new JSONTokener(InitialSetup.class.getResourceAsStream("/jsonschema.json")));
//		
//		JSONObject jsonSubject = new JSONObject(
//				new JSONTokener(InitialSetup.class.getResourceAsStream("/use_case.json")));
//		
//		Schema schema = SchemaLoader.load(jsonSchema);
//		try {
//		schema.validate(jsonSubject);
//		repository.save(jsonSubject);
//		} catch (ValidationException e) {
//			System.out.println("Invaid JSON" + e.getMessage());
//		}
//	}
}
