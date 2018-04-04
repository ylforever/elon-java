package com.elon.springbootdemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elon.springbootdemo.constant.EnumSexTypeHandler;
import com.elon.springbootdemo.model.User;

/**
 * 用户数据管理Mapper。
 * 
 * @author elon
 * @version 2018年4月2日
 */
public interface UserMapperV2 {
	
	/**
	 * 插入用户数据。
	 * 
	 * @param user
	 */
	@Insert("insert into tbl_user ("
			+ "name,"
			+ "age,"
			+ "sex"
			+ ") values ("
			+ "#{name}, "
			+ "#{age},"
			+ "#{sexType, typeHandler=com.elon.springbootdemo.constant.EnumSexTypeHandler})")
	@Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="id")
	void insertUser(User user);
	
	/**
	 * 获取所有的用户数据。
	 * 
	 * @return
	 */
	@Select("select id, name, age, sex from tbl_user")
	@Results({
		@Result(property="userId", column="id"),
		@Result(property="name", column="name"),
		@Result(property="age", column="age"),
		@Result(property="sexType", column="sex", typeHandler=EnumSexTypeHandler.class)
	})
	List<User> getAllUsers();
}
