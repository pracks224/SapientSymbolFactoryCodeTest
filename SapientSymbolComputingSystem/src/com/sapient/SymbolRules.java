package com.sapient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.exceptions.RuleViolationException;
import com.sapient.exceptions.UnknownSymbolException;
import com.sapient.knowledge.RomanSymbols;
import com.sapient.knowledge.Symbols;

public class SymbolRules {

	private static final List<String> NON_REPEATING_SYMBOLS = Arrays.asList("D", "L", "V");

	private static final List<String> REPEATING_SYMBOLS = Arrays.asList("I", "X", "M", "C");

	private static final int MAX_COUNT_REPEATING = 3;

	private static final int MAX_COUNT_NON_REPEATING = 1;

	public static Map<String, Integer> repeatableSymbolCount = getRepeatableLiteralsCount();

	public static Map<String, Integer> nonRepeatableSymbolCount = getNonRepeatableLiteralsCount();

	public static Map<String, Integer> getRepeatableLiteralsCount() {
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put(RomanSymbols.ValidSymbols.I.toString(), 0);
				put(RomanSymbols.ValidSymbols.X.toString(), 0);
				put(RomanSymbols.ValidSymbols.C.toString(), 0);
				put(RomanSymbols.ValidSymbols.M.toString(), 0);
			}
		};
		return map;
	}

	public static Map<String, Integer> getNonRepeatableLiteralsCount() {
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put(RomanSymbols.ValidSymbols.D.toString(), 0);
				put(RomanSymbols.ValidSymbols.L.toString(), 0);
				put(RomanSymbols.ValidSymbols.V.toString(), 0);
			}
		};
		return map;
	}

	public static Boolean isValid(String command) {
		return null;
	}

	public Long calculateValue(Symbols merchantSymbols, String roman)
			throws UnknownSymbolException, RuleViolationException {
		if (roman == null) {
			return null;
		}
		Long decimal = 0L;
		Long lastNumber = 0L;
		for (int i = roman.length() - 1; i >= 0; i--) {
			char ch = roman.charAt(i);
			String symbol = String.valueOf(ch);
			if (RomanSymbols.ValidSymbols.getValidSymbol(symbol) == null) {
				throw new UnknownSymbolException("Unknown symbol encountered : " + symbol);
			}
			if (REPEATING_SYMBOLS.contains(symbol) && repeatableSymbolCount.get(symbol) < MAX_COUNT_REPEATING) {
				repeatableSymbolCount.put(symbol, repeatableSymbolCount.get(symbol) + 1);
				decimal = checkRoman(merchantSymbols.getSymbolValueByName(symbol), lastNumber, decimal);
				lastNumber = merchantSymbols.getSymbolValueByName(symbol);
			} else if (NON_REPEATING_SYMBOLS.contains(symbol)
					&& nonRepeatableSymbolCount.get(symbol) < MAX_COUNT_NON_REPEATING) {
				nonRepeatableSymbolCount.put(symbol, nonRepeatableSymbolCount.get(symbol) + 1);
				repeatableSymbolCount.replaceAll((k,v)->0);
				decimal = checkRoman(merchantSymbols.getSymbolValueByName(symbol), lastNumber, decimal);
				lastNumber = merchantSymbols.getSymbolValueByName(symbol);
			} else
				throw new RuleViolationException("Max symbol count exceeded for " + symbol);
		}
		return decimal;

	}

	private Long checkRoman(Long TotalDecimal, Long LastRomanLetter, Long LastDecimal) {
		if (LastRomanLetter > TotalDecimal) {
			return LastDecimal - TotalDecimal;
		} else {
			return LastDecimal + TotalDecimal;
		}

	}

}
