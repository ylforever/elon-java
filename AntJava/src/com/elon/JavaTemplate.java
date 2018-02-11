package com.elon;

public class JavaTemplate
{
	public static <T extends Number> boolean getValue(Class<T> type , T a)
	{
		T b = type.cast(a);
		return b.equals(100);
	}
}
