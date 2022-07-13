package com.coding.assignment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coding.assignment.entities.Trade;
import com.coding.assignment.exceptions.InvalidInputException;
import com.coding.assignment.exceptions.NoSuchElementException;
import com.coding.assignment.repository.TradeRepository;
@Service
public class TradeService {

	@Autowired
	public TradeRepository tradeRepository;
	/**
	 * This service is responsible for validating maturity date and saving all items into the Trade store.
	 * @param trade
	 * @throws InvalidInputException
	 */
	public void addTradeItem(Trade trade) throws InvalidInputException{
		//check the validation for maturity date
		 
         Date end = null;
         Date start = null;
		try {
			 start = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
	                 .parse(trade.getMaturityDate());
			end = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
			         .parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(start.compareTo(end) < 0)
		{
			throw new InvalidInputException("Maturity date cannot be less than today");
		}
		
		
		List<Trade> existingTrades = tradeRepository.findByTradeId(trade.getTradeId());
		if(existingTrades != null && existingTrades.size() > 0)
		{
		List<Trade> existingTradesVersion = tradeRepository.findByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());
		if(existingTradesVersion != null && existingTradesVersion.size() > 0)
		{
			tradeRepository.save(trade);
			return;
		}
		int count = (int) existingTrades.stream().filter(t->t.getVersion() > trade.getVersion()).count();
		if(count > 0)
			throw new InvalidInputException("Higher Version for the same item available , hence discarding  ");
		}
		tradeRepository.save(trade);
	}
	
	/**
	 * 
	 * @param tradeId
	 * @return
	 * @throws NoSuchElementException
	 */
	public List<Trade> getTradeItem(String tradeId) throws NoSuchElementException{
		List<Trade> trades = tradeRepository.findByTradeId(tradeId);
		if(trades != null && trades.size() > 0)
			return trades;
		else 
		{
			throw new NoSuchElementException("Given item is not present in the store!");
		}
	}
	
	//@Scheduled(cron = "@hourly")
	/**
	 * @Purpose : This job is scheduled to run everyday mid-night at 11:59 pm to set the expiry to true 
	 */
	@Scheduled(cron = "0 59 23 ? * *")
	public void updateExpiry()
	{
		System.out.println("Ezecuting scheduled job ");
		List<Trade> tradesExpiringToday = tradeRepository.findByMaturityDate(new SimpleDateFormat("dd/MM/yyyy"). format(new Date()));
		if(tradesExpiringToday != null && tradesExpiringToday.size() > 0)
		{
			
			for(Trade trade : tradesExpiringToday){
				System.out.println("trade expring today is "+ trade.getTradeId());
				trade.setExpired(true);
				tradeRepository.save(trade);
			}
		}
	}

}
