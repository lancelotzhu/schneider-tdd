package com.schneider.webapp.action;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.schneider.tdd.TradeService;

public class BuyStockAction extends BaseAction {
	
	private TradeDTO tradeDTO;
	
	@Autowired
	private TradeService tradeService;
	
	public void setTradeDTO(TradeDTO tradeDTO) {
		this.tradeDTO = tradeDTO;
	}

	public String upload() throws Exception {
		tradeService.buy(1, tradeDTO.getStockCode(), 
			new BigDecimal(tradeDTO.getPrice()), 
			tradeDTO.getQuantity());
	
		return SUCCESS;
	}

}
