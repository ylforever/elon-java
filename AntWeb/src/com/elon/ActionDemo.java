package com.elon;

import com.google.gson.Gson;

public class ActionDemo
{
	public void excute()
	{
		String str = "Excute task.";
		System.out.println(new Gson().toJson(str));
	}
}
