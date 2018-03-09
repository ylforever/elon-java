package com.elon;

/**
 * 钱币种类枚举定义。
 * 
 * @author elon
 * @version 2018年3月9日
 */
public enum EnumMoneyType {
	ONE_YUAN("a1", 1),
	
	FIVE_YUAN("a2", 5),
	
	TEEN_YUAN("a3", 10),
	
	TWENTY_YUAN("a4", 20),
	
	FIFTY_YUAN("a5", 50),
	
	HUNDRED_YUAN("a6", 100);
	
	/**
	 * 币值类型
	 */
	private String type;
	
	/**
	 * 币值
	 */
	private int value;
	
	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	private EnumMoneyType(String type, int value) {
		this.type = type;
		this.value = value;
	}

	public static int getValueByType(String type)
	{
		EnumMoneyType[] values = EnumMoneyType.values();
		for (EnumMoneyType v : values) {
			if (v.type.equals(type)) {
				return v.value;
			}
		}
		
		return 0;
	}
	
	public static String getTypeByValue(int value) {
		EnumMoneyType[] values = EnumMoneyType.values();
		for (EnumMoneyType v : values) {
			if (v.value == value) {
				return v.type;
			}
		}
		
		return "";
	}
}
