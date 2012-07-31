package com.schneider.stock;

import java.math.BigDecimal;

public interface StockTransactionDAO {

	void update(Long accountId, BigDecimal purchasePrice, int purchaseCount,
			String stockCode);

}
