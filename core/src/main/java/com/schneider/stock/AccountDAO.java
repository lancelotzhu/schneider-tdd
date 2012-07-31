package com.schneider.stock;

import java.math.BigDecimal;

public interface AccountDAO {

	BigDecimal getBalance(Long accountId);

	void updateBalance(Long accountId, BigDecimal i);

}
