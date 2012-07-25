package com.schneider.tdd.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.schneider.tdd.dao.TradeDao;
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

	public List<Trade> getAllUncomplete() {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("status", "已报");
		return this.findByNamedQuery("getAllUncomplete", queryParams);
	}

}
