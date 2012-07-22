package com.schneider.tdd.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class StockQueryServiceTest {

	private StockQueryService target = new DefaultStockQueryService();
	
	@Test
	public void queryStockInfoBySHCode() {
		StockInfo stockInfo = target.queryStockInfoByCode("601857");
		assertEquals("中国石油", stockInfo.getStockName());
		assertTrue(stockInfo.getOpenPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getClosePrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getHighestPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getLowestPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getBuyOnePrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getSellOnePrice().compareTo(BigDecimal.ZERO) > 0);
	}
	
	@Test
	public void queryStockInfoBySZCode() {
		StockInfo stockInfo = target.queryStockInfoByCode("002024");
		assertEquals("苏宁电器", stockInfo.getStockName());
		assertTrue(stockInfo.getOpenPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getClosePrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getHighestPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getLowestPrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getBuyOnePrice().compareTo(BigDecimal.ZERO) > 0);
		assertTrue(stockInfo.getSellOnePrice().compareTo(BigDecimal.ZERO) > 0);
	}
	
}
