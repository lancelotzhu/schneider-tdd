package com.schneider.tdd;

import java.math.BigDecimal;

public interface TradeDao {

	void insertTrade(long accountId, String stockCode, BigDecimal price,
			int quantity);

}
