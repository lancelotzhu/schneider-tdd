package com.schneider.tdd.dao;

import java.util.List;

import com.schneider.tdd.model.Account;

public interface AccountDao {

	Account create(Account account);

	void update(Account account);

	Account get(Long id);

	void delete(Long id);
	
	List<Account> getByUsername(String username);

}
