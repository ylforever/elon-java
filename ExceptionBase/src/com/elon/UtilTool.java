package com.elon;

import java.io.PrintWriter;
import java.io.StringWriter;

public class UtilTool
{
	/**
	 * 获取异常的调用堆栈信息。
	 * 
	 * @return 调用堆栈
	 */
	public static String toStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		try
		{
			e.printStackTrace(pw);
			return sw.toString();
		}
		catch(Exception e1)
		{
			return "";
		}
	}
}
