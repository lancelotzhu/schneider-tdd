package com.schneider.tdd.service;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.schneider.tdd.dao.AccountDao;
import com.schneider.tdd.dao.TradeDao;
import com.schneider.tdd.model.Account;
import com.schneider.tdd.model.Trade;
import com.schneider.tdd.service.impl.TradeServiceImpl;

public class TradeServiceAutoTradeTest {
	
	private TradeService target = null;
	private TradeDao tradeDao = null;
	private AccountDao accountDao = null;
	private StockQueryService stockQueryService = null;

	@Before
	public void setUp() {
		target = new TradeServiceImpl();
		tradeDao = createNiceMock(TradeDao.class);
		target.setTradeDao(tradeDao);
		accountDao = createNiceMock(AccountDao.class);
		target.setAccountDao(accountDao);
		stockQueryService = createNiceMock(StockQueryService.class);
		target.setStockQueryService(stockQueryService);
	}
	
	@Test
	public void autoTrade() {
		Account system = new Account();
		system.setId(-2L);
		system.setBalance(BigDecimal.ZERO);
		expect(accountDao.get(eq(-2L))).andStubReturn(system);
		
		Account buyer = new Account();
		buyer.setId(1L);
		buyer.setBalance(new BigDecimal("10000"));
		expect(accountDao.get(eq(1L))).andStubReturn(buyer);
		
		Account seller = new Account();
		seller.setId(2L);
		seller.setBalance(new BigDecimal("10000"));
		expect(accountDao.get(eq(2L))).andStubReturn(seller);

		List<Trade> uncompleteTrades = new ArrayList<Trade>();
		Trade trade1 = new Trade();
		trade1.setAccount(buyer);
		trade1.setOperation("买入");
		trade1.setStockCode("601857");
		trade1.setRequestedPrice(new BigDecimal("8.88"));
		trade1.setQuantity(1000);
		trade1.setStatus("已报");
		uncompleteTrades.add(trade1);
		Trade trade2 = new Trade();
		trade2.setAccount(seller);
		trade2.setOperation("卖出");
		trade2.setStockCode("601857");
		trade2.setRequestedPrice(new BigDecimal("8.88"));
		trade2.setQuantity(1000);
		trade2.setStatus("已报");
		uncompleteTrades.add(trade2);
		expect(tradeDao.getAllUncomplete()).andStubReturn(uncompleteTrades);
		
		replay(tradeDao, accountDao, stockQueryService);
		target.autoTrade();
		assertEquals("已成", trade1.getStatus());
		assertEquals(trade1.getRequestedPrice(), trade1.getTransactedPrice());
		assertEquals("已成", trade2.getStatus());
		assertEquals(trade2.getRequestedPrice(), trade2.getTransactedPrice());
		assertTrue(BigDecimal.TEN.compareTo(system.getBalance()) == 0);
		assertTrue(new BigDecimal("1115").compareTo(buyer.getBalance()) == 0);
		assertTrue(new BigDecimal("18875").compareTo(seller.getBalance()) == 0);
	}
}
