package com.schneider.tdd;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TradeServiceTest {
	
	private TradeService target = null;
	
	private TradeDao tradeDao = null;
	
	private AccountDao accountDao = null;
	
	@Before
	public void setUp() {
		target = new TradeServiceImpl();
		tradeDao = EasyMock.createMock(TradeDao.class);
		target.setTradeDao(tradeDao);
		accountDao = EasyMock.createMock(AccountDao.class);
		target.setAccountDao(accountDao);
	}

	@Test
	public void buy() {
		int quantity = 1000;
		String stockCode = "060010";
		long accountId = 1;
		BigDecimal oldBalance = new BigDecimal("10000");
		BigDecimal price = new BigDecimal("8.88");
		
		tradeDao.insertTrade(EasyMock.eq(accountId), EasyMock.eq(stockCode), 
				EasyMock.eq(price), EasyMock.eq(quantity));
		EasyMock.expect(accountDao.getBalance(EasyMock.anyLong())).andStubReturn(oldBalance);
		accountDao.updateAccount(EasyMock.eq(accountId), EasyMock.eq(new BigDecimal("1120.00")));
		
		EasyMock.replay(tradeDao, accountDao);
		target.buy(accountId, stockCode, price, quantity);
		EasyMock.verify(tradeDao, accountDao);
	}
	
}
