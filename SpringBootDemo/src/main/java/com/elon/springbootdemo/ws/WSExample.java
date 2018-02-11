package com.elon.springbootdemo.ws;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elon.springbootdemo.mapper.UserMapper;
import com.elon.springbootdemo.model.User;

@RestController  
//@EnableAutoConfiguration
@RequestMapping(value="example")
public class WSExample
{
	private Logger log = LogManager.getLogger(WSExample.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="/v1/query-user", method=RequestMethod.GET)
	public List<User> queryUser(@RequestParam(value="user", required=true) String user) 
	{
		log.info("[INFO]user info:" + user);
		log.warn("[WARN]user info:" + user);
		log.error("[ERROR]user info:" + user);
		
		return userMapper.getUsers();
	}
	
	@RequestMapping(value="/v1/add-user", method=RequestMethod.POST)
	public void addUserV1(@RequestBody User user)
	{
		System.out.println(user);
		userMapper.insertUser(user);
		System.out.println("user id:" + user.getUserId());
	}
	
	
	@RequestMapping(value="/v3/add-user", method=RequestMethod.POST)
	public void addUserV3(@RequestBody User user)
	{
		System.out.println(user);
//		userMapper.inertUserByProvider(user);
	}
	
}
