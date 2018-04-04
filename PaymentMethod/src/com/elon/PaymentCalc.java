package com.elon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

/**
 * 计算支付方式。
 * 
 * @author elon
 * @version 2018年3月5日
 */
public class PaymentCalc {
	
	/**
	 * 计算支持的付款方式。
	 * 
	 * @param totalMoney 总付款金额.
	 * @param moneyMap 持有的钱币信息 .Map<币值分类, 数量>
	 * @return 付款方式。List<Map<币值分类, 数量>>
	 */
	public List<Map<Integer, Integer>> calcPaymentMethod(int totalMoney, Map<Integer, Integer> moneyMap){
		
		List<List<Integer>> payList = new ArrayList<List<Integer>>();
		List<Integer> currencyTypeList = new ArrayList<>();
		currencyTypeList.addAll(moneyMap.keySet());
		Collections.sort(currencyTypeList);
		
		payList = calcPayMethodByCurrency(totalMoney, currencyTypeList);
		return judgePayNum(payList, moneyMap);
	}
	
	/**
	 * 根据币种计算所有可能的支付方式。
	 * 
	 * @param totalMoney 总金额
	 * @param currencyList 币种列表
	 * @return 支持的支付方式
	 */
	private List<List<Integer>> calcPayMethodByCurrency(int totalMoney, List<Integer> currencyList){
		
		List<List<Integer>> payList = new ArrayList<List<Integer>>();
		
		//计算右子树
		Stack<Integer> payMethod = new Stack<>();
		payList.addAll(calcChildPayMethod(totalMoney - currencyList.get(0) , currencyList, payMethod, 
				EnumChildType.RIGHT));
		
		//计算左子树
		List<Integer> leftCurrencyTypeList = new ArrayList<>();
		leftCurrencyTypeList.addAll(currencyList);
		leftCurrencyTypeList.remove(0);
		payList.addAll(calcChildPayMethod(totalMoney, leftCurrencyTypeList, new Stack<Integer>(), 
				EnumChildType.LEFT));
		
		return payList;
	}

	/**
	 * 递归计算子树节点。这个函数中没一步操作,入栈、出栈，判断处理的顺序都很重。
	 * 
	 * @param totalMoney 总金额
	 * @param currencyList 币种数量列表
	 * @param payMethod 支付方式列表
	 * @param lOrR 当前处理的是左子树还是右子树
	 * @return 支付方式列表
	 */
	private List<List<Integer>> calcChildPayMethod(int totalMoney, List<Integer> currencyList,
			Stack<Integer> payMethod, EnumChildType lOrR) {

		List<List<Integer>> payList = new ArrayList<List<Integer>>();

		if (lOrR == EnumChildType.RIGHT && !currencyList.isEmpty()) {
			payMethod.push(currencyList.get(0));
		}

		if (totalMoney == 0) {
			List<Integer> payCopy = new ArrayList<>();
			payCopy.addAll(payMethod);
			payList.add(payCopy);
			return payList;
		}

		if (totalMoney < 0 || currencyList.isEmpty()) {
			return payList;
		}

		List<Integer> moneyTypeListLeft = new ArrayList<>();
		moneyTypeListLeft.addAll(currencyList);
		moneyTypeListLeft.remove(0);
		
		//递归处理右子树
		payList.addAll(calcChildPayMethod(totalMoney - currencyList.get(0), currencyList, payMethod, 
				EnumChildType.RIGHT));

		//由于上一步是最后处理右子树，且currencyList不为空，有入栈操作。执行完后需要 先出栈在处理左子树。
		payMethod.pop();

		//递归处理左子树
		payList.addAll(calcChildPayMethod(totalMoney, moneyTypeListLeft, payMethod, EnumChildType.LEFT));

		return payList;
	}
	
	/**
	 * 根据手上持有的货币判断实际能支持哪些付费方式。
	 * 
	 * @param payList
	 *            按币种计算所有可能的付费方式
	 * @param moneyMap
	 *            持久的货币信息
	 * @return 实际支持的支付方式
	 */
	private List<Map<Integer, Integer>> judgePayNum(List<List<Integer>> payList, Map<Integer, Integer> moneyMap) {

		List<Map<Integer, Integer>> fitPayMap = new ArrayList<>();
		List<Map<Integer, Integer>> allPayMap = new ArrayList<>();

		for (List<Integer> pay : payList) {

			Map<Integer, Integer> onePayMap = new HashMap<>();
			for (int money : pay) {
				if (!onePayMap.containsKey(money)) {
					onePayMap.put(money, 0);
				}

				onePayMap.put(money, onePayMap.get(money) + 1);
			}

			allPayMap.add(onePayMap);
		}

		for (Map<Integer, Integer> payMap : allPayMap) {
			boolean bFit = true;
			for (Entry<Integer, Integer> entry : payMap.entrySet()) {
				if (!moneyMap.containsKey(entry.getKey()) || moneyMap.get(entry.getKey()) < entry.getValue()) {
					bFit = false;
					break;
				}
			}
			
			if (bFit) {
				fitPayMap.add(payMap);
			}
		}

		return fitPayMap;
	}
}
