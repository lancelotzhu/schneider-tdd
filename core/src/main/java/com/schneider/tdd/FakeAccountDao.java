package com.schneider.tdd;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository(value = "accountDao")
public class FakeAccountDao implements AccountDao {
	
	private Map<Long, BigDecimal> data = new HashMap<Long, BigDecimal>();
	
	public FakeAccountDao() {
		data.put(1L, new BigDecimal("10000"));
	}

	public BigDecimal getBalance(long accountId) {
		return data.get(accountId);
	}

	public void updateAccount(long accountId, BigDecimal balance) {
		data.put(accountId, balance);
	}

}
