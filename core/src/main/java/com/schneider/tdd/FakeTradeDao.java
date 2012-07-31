package com.schneider.tdd;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

@Repository(value = "tradeDao")
public class FakeTradeDao implements TradeDao {

	public void insertTrade(long accountId, String stockCode, BigDecimal price,
			int quantity) {
	}

}
