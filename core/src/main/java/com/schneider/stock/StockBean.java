package com.schneider.stock;

import java.math.BigDecimal;

public class StockBean {
	private String stockName;
	
	private BigDecimal intialPrice;
	
	private BigDecimal currentPrice;
	
	private BigDecimal todayMaxPrice;
	
	private BigDecimal todayMinPrice;
	
	private BigDecimal lastEndPrice;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getIntialPrice() {
		return intialPrice;
	}

	public void setIntialPrice(BigDecimal intialPrice) {
		this.intialPrice = intialPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getTodayMaxPrice() {
		return todayMaxPrice;
	}

	public void setTodayMaxPrice(BigDecimal todayMaxPrice) {
		this.todayMaxPrice = todayMaxPrice;
	}

	public BigDecimal getTodayMinPrice() {
		return todayMinPrice;
	}

	public void setTodayMinPrice(BigDecimal todayMinPrice) {
		this.todayMinPrice = todayMinPrice;
	}

	public BigDecimal getLastEndPrice() {
		return lastEndPrice;
	}

	public void setLastEndPrice(BigDecimal lastEndPrice) {
		this.lastEndPrice = lastEndPrice;
	}
	
	
}
