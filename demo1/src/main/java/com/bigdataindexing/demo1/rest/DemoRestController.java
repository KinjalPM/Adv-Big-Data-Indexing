package com.bigdataindexing.demo1.rest;


import java.util.HashMap;
import java.util.Map;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigdataindexing.demo1.InitialSetup;
import com.bigdataindexing.demo1.service.RedisService;

@RestController
public class DemoRestController {
	
	private RedisService redisService;
	
	@Autowired
	public DemoRestController(RedisService redisService) {
		this.redisService = redisService;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> readAllRedis(){
		Map<String, String> map = redisService.findall();
		return new ResponseEntity<>( map, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readOneRedis(@PathVariable String id){
		String value = redisService.find(id);
		Map<String, String> map = new HashMap<>();
		map.put(id, value);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createRedis(@RequestBody String json){
		JSONObject jsonSchema = new JSONObject(
				new JSONTokener(InitialSetup.class.getResourceAsStream("/jsonschema.json")));
		JSONObject input = new JSONObject(json);
		
		Schema schema = SchemaLoader.load(jsonSchema);
		try {
		schema.validate(input);
		redisService.save(input);
		} catch (ValidationException e) {
			System.out.println("Invaid JSON" + e.getMessage());
			return new ResponseEntity<>("Invalid Json: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		Map<String, String> map = new HashMap();
		map.put("Success", "Object created successfully");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRedis(@PathVariable String id){
		redisService.delete(id);
		Map<String, String> map = new HashMap();
		map.put("Success", "Object deleted successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	

}
