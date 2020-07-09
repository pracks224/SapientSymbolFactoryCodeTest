package com.sapient;

import com.sapient.knowledge.RomanSymbols;
import com.sapient.knowledge.Symbols;

public class MainCompute {
	public static void main(String[] args) {
		RomanSymbols romanSymbols = new RomanSymbols();
		Symbols symbols = new Symbols(romanSymbols.getPlanetSymbolMap());
        SymbolRules symbolRules = new SymbolRules();
        Long value = null;
        try {
        	value = symbolRules.calculateValue(symbols, "MCMXLIV");//MCMXLIV //XXXX
        }catch(Exception ex) {
        	System.out.println("|| In Exception ||"+ex);
        }
        System.out.println(value);		
		
	}

}
