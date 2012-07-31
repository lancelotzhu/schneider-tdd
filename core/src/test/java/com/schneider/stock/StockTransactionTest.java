package com.schneider.stock;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class StockTransactionTest {
	
	private StockTransaction testTarget;
	private Account testAccount;
	private AccountDAO testAccountDAO;
	private StockTransactionDAO testStockTransactionDAO;
	
	@Before
	public void setup(){		
		testTarget = new StockTransaction();
	}
	
	@After
	public void tearDown(){		
	}
	
	@Test
	public void createPurchaseTransactionTest(){
		Long accountId = new Long(0);
		String stockCode = "600102";
		int purchaseCount = 1000;
		BigDecimal purchasePrice = new BigDecimal("9.23");
		
		
		testAccountDAO = EasyMock.createMock(AccountDAO.class);
		EasyMock.expect(testAccountDAO.getBalance(accountId)).andReturn(new BigDecimal("10000"));
		
		testAccountDAO.updateBalance(accountId, new BigDecimal("770.00"));
		EasyMock.expect(testAccountDAO.getBalance(accountId)).andReturn(new BigDecimal("770.00"));
		EasyMock.expectLastCall();		

		testStockTransactionDAO = EasyMock.createNiceMock(StockTransactionDAO.class);
		//testStockTransactionDAO.update(accountId, purchasePrice, purchaseCount, stockCode);
		//EasyMock.expectLastCall();
		EasyMock.replay(testAccountDAO);		
		
				
		testAccount = EasyMock.createMock(Account.class);
		EasyMock.expect(testAccount.getBalance()).andReturn(new BigDecimal("770.00")).times(1);
		EasyMock.expect(testAccount.getAccontID()).andReturn(new Long(0)).times(2);
		EasyMock.replay(testAccount);
		testTarget.setAccountDAO(testAccountDAO);
		
				
		BigDecimal balance = testAccount.getBalance();
		
		testTarget.setAccount(testAccount);
		//testTarget.setStockTransactionDAO(testStockTransactionDAO);
		
		testTarget.createPurchaseTransaction(stockCode, purchaseCount, purchasePrice);
		
		
		
		Assert.assertEquals(new BigDecimal("770.00"), testTarget.getTotalAmount());
		
		EasyMock.verify(testAccountDAO,testAccount);
	}
	
	
}
