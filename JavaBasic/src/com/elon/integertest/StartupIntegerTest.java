package com.elon.integertest;

public class StartupIntegerTest {
	public static void main(String[] args) {
		Integer i = new Integer(1);
		System.out.println("a: i=" + i);
		change(i);
		System.out.println("c: i=" + i);
		
		System.exit(0);
	}
	
	private static void change(Integer i) {
		int value = i + 2;
		i = new Integer(value);
		System.out.println("b: i=" + i);
	}
}

