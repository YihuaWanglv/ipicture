/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package ipicture.web.pocket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ipicture.web.pocket.model.User;

@Service
public class UserService {
	 @Autowired	 
	 RestTemplate restTemplate;
	
//	 @Autowired
//	 FeignUserService feignUserService;
	 
	 final String SERVICE_NAME="ipicture-service-user";
	 
	 @HystrixCommand(fallbackMethod = "fallbackSearchAll")
	 public List<User> readUserInfo() {
	        return restTemplate.getForObject("http://"+SERVICE_NAME+"/users", List.class);
		 //return feignUserService.readUserInfo();
	 }	 
	 private List<User> fallbackSearchAll() {
		 System.out.println("HystrixCommand fallbackMethod handle!");
		 List<User> ls = new ArrayList<User>();
		 User user = new User();
		 user.setName("TestHystrixCommand");
		 ls.add(user);
		 return ls;
	 }
}
