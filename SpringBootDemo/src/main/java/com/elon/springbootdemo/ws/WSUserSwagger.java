package com.elon.springbootdemo.ws;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="swagger-demo")
@Api(value="WSUserSwagger", description="用户信息管理")
public class WSUserSwagger {
	
	@ApiOperation(value="添加用户", notes="添加用户")
	@RequestMapping(value="/v1/user", method=RequestMethod.POST)
	public String addUser(@RequestBody String userInfo) {
		return "Add user:" + userInfo;
	}
	
	@ApiOperation(value = "根据名称查询用户", notes = "根据名称查询用户")
	@RequestMapping(value = "/v1/user", method = RequestMethod.GET)
	public String queryUserByName(@RequestParam("name") String name, @RequestHeader("age") int age) {
		return name + age;
	}
	
	@ApiOperation(value="删除用户", notes="删除用户")
	@RequestMapping(value="/v1/user/{name}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable("name") String name) {
		return "delete " + name;
	}
}
