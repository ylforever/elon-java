package com.elon.springbootdemo.constant;

/**
 * 性别类型定义。
 * 
 * @author elon
 * @version 2018年4月2日
 */
public enum EnumSexType {

	NA(-1),		//无效值
	
	MAN(1),		//男
	
	WOMAN(2);   //女
	
	private int type; 
	
	private EnumSexType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	public static EnumSexType int2Enum(int sexType) {
		EnumSexType[] types = EnumSexType.values();
		for (EnumSexType type : types) {
			if (type.getType() == sexType) {
				return type;
			}
		}
		
		return EnumSexType.NA;
	}
}
