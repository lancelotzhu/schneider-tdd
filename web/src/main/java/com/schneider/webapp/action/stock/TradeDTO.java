package com.schneider.webapp.action.stock;

public class TradeDTO {
	
	private String stockCode;
	
	private String stockName;
	
	private String requestedPrice;
	
	private Integer quantity;
	
	private String accountBalance;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getRequestedPrice() {
		return requestedPrice;
	}

	public void setRequestedPrice(String requestedPrice) {
		this.requestedPrice = requestedPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

}
