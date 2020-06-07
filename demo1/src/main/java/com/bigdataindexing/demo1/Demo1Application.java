package com.bigdataindexing.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import javax.servlet.Filter;

@SpringBootApplication
public class Demo1Application {

	@Bean
	public Filter filter(){
	ShallowEtagHeaderFilter sf = new ShallowEtagHeaderFilter();
		return sf;
		
	}
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}

