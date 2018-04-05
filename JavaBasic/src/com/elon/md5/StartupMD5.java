package com.elon.md5;

import java.security.NoSuchAlgorithmException;

public class StartupMD5 {
	public static void main(String[] args) {
		try {
			System.out.println(MD5Util.encrypt("123"));
			System.out.println(MD5Util.encrypt("a"));
			System.out.println(MD5Util.encrypt(""));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
