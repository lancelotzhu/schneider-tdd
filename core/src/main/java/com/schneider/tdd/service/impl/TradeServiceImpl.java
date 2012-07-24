package com.schneider.tdd.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schneider.tdd.dao.AccountDao;
import com.schneider.tdd.dao.TradeDao;
import com.schneider.tdd.model.Account;
import com.schneider.tdd.model.Trade;
import com.schneider.tdd.service.StockQueryService;
import com.schneider.tdd.service.TradeService;

@Service(value="tradeService")
public class TradeServiceImpl implements TradeService {
	private TradeDao tradeDao;
	private AccountDao accountDao;
	private StockQueryService stockQueryService;

	@Transactional
	public void buy(long accountId, String stockCode, BigDecimal price, int quantity) {
		Account account = accountDao.get(accountId);
		if (account == null) {
			throw new IllegalArgumentException("account doesn't exist!");
		}				
		
		if (stockQueryService.queryStockInfoByCode(stockCode) == null) {
			throw new IllegalArgumentException("stock code doesn't exist!");
		}
		
		Trade trade = new Trade();
		trade.setAccount(account);
		trade.setStockCode(stockCode);
		trade.setRequestedPrice(price);
		trade.setQuantity(quantity);
		tradeDao.create(trade);
		
		account.setBalance(account.getBalance().subtract(price.multiply(new BigDecimal(quantity))));
		accountDao.update(account);
	}

	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setStockQueryService(StockQueryService stockQueryService) {
		this.stockQueryService = stockQueryService;
	}

}
