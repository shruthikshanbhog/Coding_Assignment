package com.coding.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.assignment.entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, String>{


	public List<Trade> findByTradeIdAndVersion(String tradeId,int version);

	public List<Trade> findByTradeId(String tradeId);

	public List<Trade> findByMaturityDate(String format);
	
}
