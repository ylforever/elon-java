package com.elon.springbootdemo.mapper;

import org.apache.ibatis.annotations.Param;

import com.elon.springbootdemo.model.User;

public class UserProvider
{
	public String insertUser(@Param("user") User user)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("insert into tbl_user (name, age) values (");
		sb.append("#{user.name}, #{user.age}");
		sb.append(")");
		
		return sb.toString();
	}
}
