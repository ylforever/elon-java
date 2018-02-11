package com.elon.springbootdemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elon.springbootdemo.model.User;

@Mapper
public interface UserMapper
{
	@Insert("insert into tbl_user (name, age) values (#{name}, #{age})")
	@Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="id")
	void insertUser(User user);
	
	@Results(value= {
			@Result(property="userId", column="id"),
			@Result(property="name", column="name"),
			@Result(property="age", column="age")
	})
	@Select("select id, name, age from tbl_user")
	List<User> getUsers();
	
//	@InsertProvider(type=UserProvider.class, method="")
//	void inertUserByProvider(@Param("user") User user);
} 
