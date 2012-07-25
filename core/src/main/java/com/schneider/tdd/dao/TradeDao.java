package com.schneider.tdd.dao;

import java.util.List;

import com.schneider.tdd.model.Trade;

public interface TradeDao {

	Trade create(Trade trade);

	void update(Trade trade);

	void delete(Long id);

	Trade get(Long id);

	List<Trade> getAllUncomplete();

}
