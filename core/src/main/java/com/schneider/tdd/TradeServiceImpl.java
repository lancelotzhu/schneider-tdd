package com.schneider.tdd;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "tradeService")
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	private TradeDao tradeDao;
	
	@Autowired
	private AccountDao accountDao;
	
	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void buy(long accountId, String stockCode, BigDecimal price,
			int quantity) {
		tradeDao.insertTrade(accountId, stockCode, price, quantity);
		
		BigDecimal currBalance = accountDao.getBalance(accountId);
		BigDecimal expense = price.multiply(new BigDecimal(quantity));
		accountDao.updateAccount(accountId, currBalance.subtract(expense));
		
	}

}
