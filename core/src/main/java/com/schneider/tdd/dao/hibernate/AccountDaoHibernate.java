package com.schneider.tdd.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.schneider.tdd.dao.AccountDao;
import com.schneider.tdd.model.Account;

@Repository(value="accountDao")
public class AccountDaoHibernate extends GenericDaoHibernate<Account, Long> implements AccountDao {

	public AccountDaoHibernate() {
		super(Account.class);
	}

	public Account create(Account account) {
		return super.save(account);
	}

	public void update(Account account) {
		super.save(account);
	}

	public Account get(Long id) {
		return super.get(id);
	}

	public void delete(Long id) {
		super.remove(id);
	}

	public List<Account> getByUsername(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		return super.findByNamedQuery("getByUsername", map);
	}

}
