package com.elon.springbootdemo.ws;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elon.springbootdemo.constant.EnumSexType;
import com.elon.springbootdemo.mapper.UserMapperV2;
import com.elon.springbootdemo.model.QueryMapHelper;
import com.elon.springbootdemo.model.User;

@RestController
@RequestMapping(value="WSUserV2")
public class WSUserV2 {
	
	@Autowired
	private UserMapperV2 userMapper;
	
	/**
	 * 插入用户数据。
	 * 
	 * @param user
	 */
	@RequestMapping(value="/v2/add-user", method=RequestMethod.POST)
	public void addUserV2(@RequestBody User user)
	{
		user.setSexType(EnumSexType.MAN);
		userMapper.insertUser(user);
	}
	
	/**
	 * 查询所有的用户信息列表。
	 * 
	 * @return
	 */
	@RequestMapping(value="/v2/query-user", method=RequestMethod.GET)
	public List<User> queryAllUser() 
	{
		return userMapper.getAllUsers();
	}
	
	@RequestMapping(value="/v2/query-user-age", method=RequestMethod.GET)
	public Map<Integer, Integer> queryUserAge(){
		
		/**
		 * 将查询结果列表转换为Map结构
		 */
		Map<Integer, Integer> userAgeMap = QueryMapHelper.toMap(userMapper.queryUserAge());
		return userAgeMap;
	}
}
