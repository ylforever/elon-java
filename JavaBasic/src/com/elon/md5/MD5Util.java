package com.elon.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法，将一串字符串加密转换为32位数据+大写字母的字符串。
 * 
 * @author elon
 * @version 2018年4月5日
 */
public class MD5Util {
	
	/**
	 * 定义16进制包含的字符，用于加密串转换
	 */
	private static final char[] hexDigints = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	/**
	 * 加密后返回十六进制的密文。
	 * 
	 * @param input 输入字符串
	 * @return 密文
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encrypt(String input) throws NoSuchAlgorithmException {
		
		/**
		 * 获取MD5摘要加密算法实例
		 */
		MessageDigest md5Intance = MessageDigest.getInstance("MD5");
		
		md5Intance.update(input.getBytes());
		byte[] md5Encrypt = md5Intance.digest();
		
		/**
		 * MD5算法提取固定长度(16位)摘要信息。结果拆分为高四位和低四位，长度是摘要信息的两倍(32位)
		 */
		char[] results = new char[md5Encrypt.length * 2];
		
		int j = 0;
		for (int i = 0; i < md5Encrypt.length; ++i) {
			
			/**
			 * 每个byte是8位，取高四位或者低四位，最多是15（1111）。用作下标从hexDigints中取值，不会越界。
			 */
			results[j++] = hexDigints[(md5Encrypt[i] >>> 4) & 0xF]; //取每个字符高四位数字下标对应的十六进制字符
			results[j++] = hexDigints[md5Encrypt[i] & 0xF]; //取低四位对应的字符
		}
		
		return String.valueOf(results);
		
	}
}
