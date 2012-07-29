package com.schneider.webapp.action.stock;

import com.opensymphony.xwork2.Preparable;
import com.schneider.tdd.service.StockInfo;
import com.schneider.tdd.service.StockQueryService;
import com.schneider.tdd.service.TradeService;
import com.schneider.webapp.action.BaseAction;

public class BuyAction extends BaseAction implements Preparable {
 
	private static final long serialVersionUID = -1983699178646007829L;
	
	private TradeDTO tradeDTO;
	
	private TradeService tradeService;
	
	private StockQueryService stockQueryService;
	
	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	public void setStockQueryService(StockQueryService stockQueryService) {
		this.stockQueryService = stockQueryService;
	}

	public TradeDTO getTradeDTO() {
		return tradeDTO;
	}

	public void setTradeDTO(TradeDTO tradeDTO) {
		this.tradeDTO = tradeDTO;
	}

	/**
     * Default: just returns "success"
     *
     * @return "success"
     */
    public String execute() {
        return SUCCESS;
    }
    
	/**
     * Retrieve stock info
     *
     * @return "success"
     */
    public String refresh() {
    	String stockCode = tradeDTO.getStockCode();
    	
    	StockInfo stockInfo = stockQueryService.queryStockInfoByCode(stockCode);
    	tradeDTO.setStockName(stockInfo.getStockName());
    	tradeDTO.setRequestedPrice(stockInfo.getBuyOnePrice().toString());
    	
        return SUCCESS;
    }
    
	/**
     * Place one order
     *
     * @return "success"
     */
    public String placeOrder() {
    	
        return SUCCESS;
    }

	public void prepare() throws Exception {
		if (tradeDTO == null) {
			tradeDTO = new TradeDTO();
		}
		tradeDTO.setAccountBalance("10000");
	}

}
