package com.schneider.sample;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator target;
	
	@Before
	public void setUp() {
		target = new Calculator();
	}
	
	@Test
	public void testAdd() {
		int base = 5;
		int toAdd = 7;
		Assert.assertTrue(new BigDecimal(12).compareTo(
				target.add(new BigDecimal(base), new BigDecimal(toAdd))) == 0);
	}
	
	@Test
	public void testAddOverflow() {
		int base = Integer.MAX_VALUE;
		int toAdd = 7;
		Assert.assertTrue(target.add(new BigDecimal(base), new BigDecimal(toAdd)).compareTo(
				new BigDecimal(Integer.MAX_VALUE)) > 0);
	}

}
