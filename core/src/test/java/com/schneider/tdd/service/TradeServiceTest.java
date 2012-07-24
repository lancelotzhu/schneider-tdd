package com.schneider.tdd.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.schneider.tdd.dao.AccountDao;
import com.schneider.tdd.dao.TradeDao;
import com.schneider.tdd.model.Account;
import com.schneider.tdd.model.Trade;
import com.schneider.tdd.service.impl.TradeServiceImpl;
import com.schneider.tdd.util.Matchers;
import com.schneider.tdd.util.Matchers.BaseMatcher;

public class TradeServiceTest {
	
	private TradeService target = null;
	private TradeDao tradeDao = null;
	private AccountDao accountDao = null;
	private StockQueryService stockQueryService = null;
	
	@Before
	public void setUp() {
		target = new TradeServiceImpl();
		tradeDao = createMock(TradeDao.class);
		target.setTradeDao(tradeDao);
		accountDao = createMock(AccountDao.class);
		target.setAccountDao(accountDao);
		stockQueryService = createMock(StockQueryService.class);
		target.setStockQueryService(stockQueryService);
	}
	
	@Test
	public void buy() {
		Account account = new Account();
		account.setId(-1L);
		account.setBalance(new BigDecimal("100000"));
		expect(accountDao.get(eq(-1L))).andStubReturn(account);
		accountDao.update(Matchers.eq(new BaseMatcher<Account>() {
			public boolean matches(Account actual) {
				return new BigDecimal("100000").subtract(
						new BigDecimal("8.88").multiply(
						new BigDecimal("500"))).compareTo(
						actual.getBalance()) == 0;
			}
			public void appendTo(StringBuffer buffer) {
			}
		}));
		expect(tradeDao.create(Matchers.eq(new BaseMatcher<Trade>() {
			public boolean matches(Trade actual) {
				return new Long(-1L).equals(actual.getAccount().getId())
					&& "601857".equals(actual.getStockCode())
					&& new Integer("500").equals(actual.getQuantity());
			}
			public void appendTo(StringBuffer buffer) {
			}
		}))).andReturn(null).once();
		expect(stockQueryService.queryStockInfoByCode(eq("601857"))).andStubReturn(new StockInfo());
		
		replay(tradeDao, accountDao, stockQueryService);
		target.buy(-1L, "601857", new BigDecimal("8.88"), 500);		
		verify(tradeDao, accountDao, stockQueryService);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buyWithNonExistingAccount() {
		expect(accountDao.get(eq(-1L))).andStubReturn(null);
		
		replay(accountDao);
		target.buy(-1L, "601857", new BigDecimal("8.88"), 500);		
		verify(accountDao);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buyWithNonExistingStock() {
		Account account = new Account();
		account.setId(-1L);
		account.setBalance(new BigDecimal("100000"));
		expect(accountDao.get(eq(-1L))).andStubReturn(account);
		expect(stockQueryService.queryStockInfoByCode(eq("999999"))).andStubReturn(null);
		
		replay(accountDao, stockQueryService);
		target.buy(-1L, "999999", new BigDecimal("8.88"), 500);		
		verify(accountDao, stockQueryService);
	}
}
