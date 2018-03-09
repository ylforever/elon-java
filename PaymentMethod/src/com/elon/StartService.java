package com.elon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StartService {
	public static void main(String[] args) {
		
		//从控制台读入输入的n=11,a1=0,a2=0,a3=5,a4=6,a5=3,a6=2
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String str = reader.readLine();
			String[] splitResults = str.split(",");
			
			//提取转换为Key/Value结构方便查询
			Map<String, Integer> keyValueMap = new HashMap<>();
			for (String pair : splitResults) {
				String[] pairValues = pair.split("=");
				keyValueMap.put(pairValues[0].trim(), Integer.parseInt(pairValues[1].trim()));
			}
			
			int total = keyValueMap.get("n");
			
			Map<Integer, Integer> moneyMap = new HashMap<>();
			moneyMap.put(EnumMoneyType.ONE_YUAN.getValue(), keyValueMap.get(EnumMoneyType.ONE_YUAN.getType()));
			moneyMap.put(EnumMoneyType.FIVE_YUAN.getValue(), keyValueMap.get(EnumMoneyType.FIVE_YUAN.getType()));
			moneyMap.put(EnumMoneyType.TEEN_YUAN.getValue(), keyValueMap.get(EnumMoneyType.TEEN_YUAN.getType()));
			moneyMap.put(EnumMoneyType.TWENTY_YUAN.getValue(), keyValueMap.get(EnumMoneyType.TWENTY_YUAN.getType()));
			moneyMap.put(EnumMoneyType.FIFTY_YUAN.getValue(), keyValueMap.get(EnumMoneyType.FIFTY_YUAN.getType()));
			moneyMap.put(EnumMoneyType.HUNDRED_YUAN.getValue(), keyValueMap.get(EnumMoneyType.HUNDRED_YUAN.getType()));
			
			List<Map<Integer, Integer>> resultList = new PaymentCalc().calcPaymentMethod(total, moneyMap);
			
			List<Map<String, Integer>> resultShowList = new ArrayList<>();
			for (Map<Integer, Integer> result : resultList) {
				Map<String, Integer> resultShowMap = new HashMap<>();
				for (Entry<Integer, Integer> entry : result.entrySet()) {
					resultShowMap.put(EnumMoneyType.getTypeByValue(entry.getKey()), entry.getValue());
				}
				
				resultShowList.add(resultShowMap);
			}
			
			System.out.println("支持的付款方式(钱币种类=张数):" + resultShowList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
