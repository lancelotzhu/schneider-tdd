package com.schneider.tdd.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private TradeDao tradeDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
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

	@Transactional
	public void autoTrade() {
		List<Trade> trades = tradeDao.getAllUncomplete();
		Map<String, List<Trade>> buyTrades = new HashMap<String, List<Trade>>();
		Map<String, List<Trade>> sellTrades = new HashMap<String, List<Trade>>();
		
		for (Trade trade : trades) {
			if ("买入".equals(trade.getOperation())) {
				List<Trade> list = buyTrades.get(trade.getStockCode());
				if (list == null) {
					list = new LinkedList<Trade>();
				}
				int pos;
				for (pos = 0; pos < list.size(); pos++) {
					Trade item = list.get(pos);
					if (item.getRequestedPrice().compareTo(trade.getRequestedPrice()) < 0) {
						break;
					} else if (item.getRequestedPrice().compareTo(trade.getRequestedPrice()) == 0) {
						if (item.getDateCreated().after(trade.getDateCreated())){
							break;
						}
					}
				}
				list.add(pos, trade);
				buyTrades.put(trade.getStockCode(), list);
			} else if ("卖出".equals(trade.getOperation())) {
				List<Trade> list = sellTrades.get(trade.getStockCode());
				if (list == null) {
					list = new LinkedList<Trade>();
				}
				int pos;
				for (pos = 0; pos < list.size(); pos++) {
					Trade item = list.get(pos);
					if (item.getRequestedPrice().compareTo(trade.getRequestedPrice()) > 0) {
						break;
					} else if (item.getRequestedPrice().compareTo(trade.getRequestedPrice()) == 0) {
						if (item.getDateCreated().after(trade.getDateCreated())){
							break;
						}
					}
				}
				list.add(pos, trade);
				sellTrades.put(trade.getStockCode(), list);
			}
		}
		
		for (String stockCode: buyTrades.keySet()) {
			for (Trade buy : buyTrades.get(stockCode)) {
				for (Trade sell : sellTrades.get(stockCode)) {
					if (buy.getRequestedPrice().compareTo(sell.getRequestedPrice()) >= 0 &&
						buy.getQuantity() <= sell.getQuantity()) {
						buy.setTransactedPrice(buy.getRequestedPrice());
						buy.setStatus("已成");
						tradeDao.update(buy);
						deductBuyer(buy);
						sell.setTransactedPrice(buy.getRequestedPrice());
						sell.setStatus("已成");
						tradeDao.update(sell);
						deductSeller(sell);
					}
				}
			}
		}		
	}

	private void deductBuyer(Trade buy) {
		Account account = buy.getAccount();
		BigDecimal fee = buy.getTransactedPrice().multiply(new BigDecimal("0.00003"));
		fee = fee.compareTo(new BigDecimal("5")) < 0 ? new BigDecimal("5") : fee;
		account.setBalance(account.getBalance().subtract(buy.getTransactedPrice().
				multiply(new BigDecimal(buy.getQuantity()))).subtract(fee));
		accountDao.update(account);
		
		Account system = accountDao.get(-2L);
		system.setBalance(system.getBalance().add(fee));
		accountDao.update(system);
	}
	
	private void deductSeller(Trade sell) {
		Account account = sell.getAccount();
		BigDecimal fee = sell.getTransactedPrice().multiply(new BigDecimal("0.00003"));
		fee = fee.compareTo(new BigDecimal("5")) < 0 ? new BigDecimal("5") : fee;
		account.setBalance(account.getBalance().add(sell.getTransactedPrice().
				multiply(new BigDecimal(sell.getQuantity()))).subtract(fee));
		accountDao.update(account);
		
		Account system = accountDao.get(-2L);
		system.setBalance(system.getBalance().add(fee));
		accountDao.update(system);
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
