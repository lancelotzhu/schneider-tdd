package com.schneider.tdd;

import java.math.BigDecimal;

public interface AccountDao {

	BigDecimal getBalance(long accountId);

	void updateAccount(long accountId, BigDecimal balance);

}
