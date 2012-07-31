package com.schneider.stock;

import java.math.BigDecimal;

public class StockTransaction {
	
	private AccountDAO accountDAO;
	private Account account;

	public StockTransaction() {
		// TODO Auto-generated constructor stub
//		accountDAO = accountDaoFactory.createAccountDao();
	}
	
	

	public void createPurchaseTransaction(String stockCode, int purchaseCount,
			BigDecimal purchasePrice) {
		// TODO Auto-generated method stub
		Long accountID = account.getAccontID();
		BigDecimal currentBalance = accountDAO.getBalance(accountID);
		
		accountDAO.updateBalance(accountID, currentBalance.subtract(purchasePrice.multiply( new BigDecimal(String.valueOf(purchaseCount)))));
		
	}

	public void setAccountDAO(AccountDAO testAccountDAO) {
		// TODO Auto-generated method stub
		
		accountDAO = testAccountDAO;
		
	}

	public BigDecimal getTotalAmount() {
		// TODO Auto-generated method stub
		return accountDAO.getBalance(account.getAccontID());
	}



	public void setAccount(Account testAccount) {
		// TODO Auto-generated method stub
		account = testAccount;
	}
	
	public Account getAccount() {
		return account;
	}
	
	
	

}
