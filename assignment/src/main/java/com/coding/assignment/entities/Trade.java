package com.coding.assignment.entities;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TradeId.class)
public class Trade {

	@Id
	private String tradeId;
	@Id
	private int version;
	@Column
	private String counterPartId;
	@Column
	private String bookId;
	@Column
	private String maturityDate;
	@Column
	private String createdDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	@Column
	private Boolean expired = false;
	
	public Trade(String tradeId, int version, String counterPartId, String bookId, String maturityDate, String createdDate,
			Boolean expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartId = counterPartId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartId() {
		return counterPartId;
	}

	public void setCounterPartId(String counterPartId) {
		this.counterPartId = counterPartId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", counterPartId=" + counterPartId + ", bookId="
				+ bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired
				+ "]";
	}
	
	
}
