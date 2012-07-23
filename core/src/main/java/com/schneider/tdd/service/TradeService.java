package com.schneider.tdd.service;

import java.math.BigDecimal;

import com.schneider.tdd.dao.AccountDao;
import com.schneider.tdd.dao.TradeDao;

public interface TradeService {

	void setTradeDao(TradeDao tradeDao);

	void setAccountDao(AccountDao accountDao);

	void buy(long accountId, String stockCode, BigDecimal price, int quantity);

}
