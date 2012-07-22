package com.schneider.tdd.service;

import java.math.BigDecimal;

public class StockInfo {

	/**
	 * 股票名字
	 */
	private String stockName;
	
	/**
	 * 今日开盘价
	 */
	private BigDecimal openPrice;
	
	/**
	 * 昨日收盘价
	 */
	private BigDecimal closePrice;
	
	/**
	 * 今日最高价
	 */
	private BigDecimal highestPrice;
	
	/**
	 * 今日最低价
	 */
	private BigDecimal lowestPrice;
	
	/**
	 * 买一报价
	 */
	private BigDecimal buyOnePrice;
	
	/**
	 * 卖一报价
	 */
	private BigDecimal sellOnePrice;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getBuyOnePrice() {
		return buyOnePrice;
	}

	public void setBuyOnePrice(BigDecimal buyOnePrice) {
		this.buyOnePrice = buyOnePrice;
	}

	public BigDecimal getSellOnePrice() {
		return sellOnePrice;
	}

	public void setSellOnePrice(BigDecimal sellOnePrice) {
		this.sellOnePrice = sellOnePrice;
	}
	
}
