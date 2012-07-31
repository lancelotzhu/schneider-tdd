package com.schneider.stock;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class StockQueryTest {

	private StockQuery stockQuery;
	
	@Before
	public void setUp(){
		stockQuery = new StockQuery();
	}
	
	@Test
	public void testQuerySH(){
		String stockCode = "601006";
		StockBean stock = stockQuery.query(stockCode);
		Assert.assertEquals("大秦铁路",stock.getStockName());
		Assert.assertTrue(stock.getIntialPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getLastEndPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getCurrentPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getTodayMaxPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getTodayMinPrice().compareTo(BigDecimal.ZERO)>0);
	}
	
	@Test
	public void testQuerySZ(){
		String stockCode = "002024";
		StockBean stock = stockQuery.query(stockCode);
		Assert.assertEquals("苏宁电器",stock.getStockName());
		Assert.assertTrue(stock.getIntialPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getLastEndPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getCurrentPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getTodayMaxPrice().compareTo(BigDecimal.ZERO)>0);
		Assert.assertTrue(stock.getTodayMinPrice().compareTo(BigDecimal.ZERO)>0);
	}
	
	@After
	public void tearDown(){
		stockQuery = null;
	}
}
