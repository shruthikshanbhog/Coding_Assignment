package com.coding.assignment.entities;

import java.io.Serializable;

import javax.persistence.Id;

public class TradeId implements Serializable{
	@Id
	private String tradeId;
	
	@Id
	private int version;

	public TradeId(String tradeId, int version) {
		this.tradeId = tradeId;
		this.version = version;
	}       
	
	public TradeId(){}
}
