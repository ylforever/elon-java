package com.elon;

import com.google.gson.Gson;

public class StartService
{
	public static void main(String[] args)
	{
		String str = "Hello ANT!!!";
		Gson gson = new Gson();
		String gsonStr = gson.toJson(str);
		System.out.println(gsonStr);
		
		boolean a = JavaTemplate.<Integer>getValue(Integer.class, 100);
		
		while (true)
		{
			try
			{
				Thread.sleep(3000);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
