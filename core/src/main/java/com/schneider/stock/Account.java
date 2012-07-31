package com.schneider.stock;

import java.math.BigDecimal;

public class Account {
	
	private Long accountID;

	public Account(Long testAccountId) {
		// TODO Auto-generated constructor stub
		
		accountID = testAccountId;
	}

	public void setAccountDAO(AccountDAO testAccountDAO) {
		// TODO Auto-generated method stub
		
	}

	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long getAccontID(){
		return accountID;
	}
	
	


}
