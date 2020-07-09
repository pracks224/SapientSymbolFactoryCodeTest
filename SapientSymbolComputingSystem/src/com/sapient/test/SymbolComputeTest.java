package com.sapient.test;
import org.junit.Assert;
import org.junit.Test;

import com.sapient.SymbolRules;
import com.sapient.exceptions.RuleViolationException;
import com.sapient.exceptions.UnknownSymbolException;
import com.sapient.knowledge.RomanSymbols;
import com.sapient.knowledge.Symbols;


public class SymbolComputeTest {

	@Test
	public void doesGetSymbolValueCorrectly(){
		
		RomanSymbols romanSymbols = new RomanSymbols();
		Symbols symbols = new Symbols(romanSymbols.getPlanetSymbolMap());
		
		SymbolRules symbolRules = new SymbolRules();
		Boolean thrown = false;
		Long value = null;
		try {
			value = symbolRules.calculateValue(symbols, "MMVI");
		} catch (UnknownSymbolException e) {
		}catch (RuleViolationException e){
		}
		
		Assert.assertEquals("2006", value.toString());
	}
	
	@Test
	public void doesCatchUnknownSymbolExceptionCorrectly(){
		
		RomanSymbols romanSymbols = new RomanSymbols();
		Symbols symbols = new Symbols(romanSymbols.getPlanetSymbolMap());
		
		SymbolRules symbolRules = new SymbolRules();
		Boolean thrown = false;
		Long value;
		try {
			value = symbolRules.calculateValue(symbols, "MAAAMVI");
		} catch (UnknownSymbolException e) {
			thrown = true;
		}catch (RuleViolationException e){
			
		}
		
		Assert.assertTrue(thrown);
	}
	
	@Test
	public void doesCatchRuleViolationExceptionCorrectly(){
		
		RomanSymbols romanSymbols = new RomanSymbols();
		Symbols symbols = new Symbols(romanSymbols.getPlanetSymbolMap());
		
		SymbolRules symbolRules = new SymbolRules();
		Boolean thrown = false;
		Long value;
		try {
			value = symbolRules.calculateValue(symbols, "MMMM");
		} catch (UnknownSymbolException e) {
			System.out.println("Unknown Exception Thrown"+e);
			thrown = true;
		}catch (RuleViolationException e){
			System.out.println("RuleViolationException"+e);
			thrown = true;
		}
		
		Assert.assertTrue(thrown);
	}
	
	
}

