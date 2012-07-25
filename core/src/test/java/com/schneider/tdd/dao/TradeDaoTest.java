package com.schneider.tdd.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.schneider.tdd.model.Trade;

public class TradeDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private TradeDao target = null;
	
	@Autowired
	private AccountDao accountDao = null;
	
	@Test
	public void testCRUD() {
		Trade trade = new Trade();
		trade.setStockCode("601857");
		trade.setStockName("中国石油");
		trade.setOperation("买入");
		trade.setAccount(accountDao.get(-1L));
		trade.setRequestedPrice(BigDecimal.TEN);
		trade.setQuantity(100);
		trade.setStatus("已报");
		trade.setDateCreated(new Date());
		trade = target.create(trade);
		
		trade.setStatus("已成");
		target.update(trade);		
		trade = target.get(trade.getId());
		assertEquals("已成", trade.getStatus());
		
		target.delete(trade.getId());
		try {
			target.get(trade.getId());
		} catch (ObjectRetrievalFailureException ignored) {
		}
	}
	
	@Test
	public void getAllUncomplete() {
		List<Trade> trades = target.getAllUncomplete();
		for (Trade trade : trades) {
			assertEquals("已报", trade.getStatus());
		}
	}

}
