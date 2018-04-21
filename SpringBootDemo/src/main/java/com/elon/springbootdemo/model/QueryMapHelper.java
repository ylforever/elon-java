package com.elon.springbootdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询辅助处理类。将查询的字段转换为Map结构。
 * 
 * @author elon
 * @version 2018年4月21日
 */
public class QueryMapHelper<K, V> {
	
	/**
	 * 用作Key的字段值
	 */
	private K key;

	/**
	 * 用作value的字段值
	 */
	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * 将查询结果由列表转换为Map结构
	 * @param resultList 查询结构列表
	 * @return Map结果
	 */
	public static <K, V> Map<K, V> toMap(List<QueryMapHelper<K, V>> resultList) {

		Map<K, V> valueMap = new HashMap<>();
		resultList.forEach((result) -> valueMap.put(result.getKey(), result.getValue()));
		return valueMap;
	}
}
