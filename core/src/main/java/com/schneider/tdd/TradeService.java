package com.schneider.tdd;

import java.math.BigDecimal;

public interface TradeService {

	void setTradeDao(TradeDao tradeDao);

	void setAccountDao(AccountDao accountDao);

	void buy(long accountId, String stockCode, BigDecimal price, int quantity);

}
