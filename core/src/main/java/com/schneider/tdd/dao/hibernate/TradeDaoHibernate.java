package com.schneider.tdd.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.schneider.tdd.dao.TradeDao;
import com.schneider.tdd.model.Account;
import com.schneider.tdd.model.Trade;

@Repository(value="tradeDao")
public class TradeDaoHibernate extends GenericDaoHibernate<Trade, Long> implements TradeDao {

	public TradeDaoHibernate() {
		super(Trade.class);
	}

	public Trade create(Trade trade) {
		return super.save(trade);
	}

	public void update(Trade trade) {
		super.save(trade);
	}

	public void delete(Long id) {
		super.remove(id);
	}
	
	public Trade get(Long id) {
		return super.get(id);
	}

	@SuppressWarnings("unchecked")
	public List<Trade> getByAccountId(Long accountId) {
		Trade example = new Trade();
		Account account = new Account();
		account.setId(accountId);
		example.setAccount(account);
		return this.getHibernateTemplate().findByExample(example);
	}

}
