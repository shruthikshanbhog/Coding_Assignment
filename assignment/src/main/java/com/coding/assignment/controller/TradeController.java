package com.coding.assignment.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.assignment.entities.Trade;
import com.coding.assignment.exceptions.InvalidInputException;
import com.coding.assignment.exceptions.NoSuchElementException;
import com.coding.assignment.service.TradeService;


/**
 * 
 * 
 * Purpose : This controller is responsible for adding items to the Trade Store
 *
 */
@RestController
@RequestMapping (value="/Trade")
public class TradeController {

	@Autowired
	public TradeService tradeService;
	
	Logger logger = LoggerFactory.getLogger(TradeController.class);
	/**
	 * @Purpose : To add item to the trade store post validations
	 * @param trade
	 * @return
	 * @throws InvalidInputException
	 */
	@PostMapping (value="/addItem")
	public ResponseEntity<String> addTrade(@RequestBody Trade trade) throws InvalidInputException{
		logger.info("Inside addTrade");
		tradeService.addTradeItem(trade);
		return ResponseEntity.ok(trade.getTradeId());
	}
	
	/**
	 * @purpose To fetch list of items avaliable in the Trade store using the tradeId.
	 * @param tradeId
	 * @return
	 * @throws NoSuchElementException
	 */
	@GetMapping (value="/getItem/{tradeId}")
	public ResponseEntity<List<Trade>> getTrade(@PathVariable String tradeId) throws NoSuchElementException{
		List<Trade> trades = tradeService.getTradeItem(tradeId);
		return new ResponseEntity<List<Trade>>(trades,HttpStatus.OK);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String handleNoSuchElementException()
	{
		return "No such element present ";
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public String handleInvalidInputException(InvalidInputException e)
	{
		return e.getMessage();
	}
}
