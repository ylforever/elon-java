package com.elon.springbootdemo.model;

import com.elon.springbootdemo.constant.EnumSexType;

public class User
{
	private int userId = -1;

	private String name = "";
	
	private int age = -1;
	
	private EnumSexType sexType = EnumSexType.NA;
	
	@Override
	public String toString()
	{
		return "name:" + name + "|age:" + age;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public EnumSexType getSexType() {
		return sexType;
	}

	public void setSexType(EnumSexType sexType) {
		this.sexType = sexType;
	}
}
