package com.coding.assignment;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coding.assignment.controller.TradeController;
import com.coding.assignment.entities.Trade;
import com.coding.assignment.exceptions.InvalidInputException;
import com.coding.assignment.repository.TradeRepository;
import com.coding.assignment.service.TradeService;

import junit.framework.Assert;



@RunWith( SpringRunner.class )
public class AssignmentApplicationTests {

	@Autowired
	TradeRepository tradeRepo;
	

    private MockMvc mockMvc;
	
    @Mock
    @SpyBean
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;
	
	 @Rule
	    public ExpectedException thrown = ExpectedException.none();
	
	 
	 @Before
	    public void initController() {
		 MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
	    }
	 
	 
	@Test
	public void testCreate()
	{
		Trade t = new Trade();
		t.setTradeId("T1");
		t.setBookId("B1");
		t.setVersion(1);
		t.setMaturityDate("13/07/2022");
		t.setExpired(false);
		tradeRepo.save(t);
		Assert.assertNotNull(tradeRepo.findByTradeId("T1"));
		//assertNotNull(tradeRepo.findByTradeId("T1"));
	}

	@Test
	public void testMaturityDateLessThanToday() 
	{
		
		Trade t = new Trade();
		t.setTradeId("T2");
		t.setBookId("B1");
		t.setVersion(1);
		t.setMaturityDate("11/07/2022");
		t.setExpired(false);
		
		try{
			tradeService.addTradeItem(t);
		}
		catch(InvalidInputException e){
			Assert.assertEquals(e, "Maturity date cannot be less than today");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() throws Exception {
		Trade t = new Trade();
		t.setTradeId("T1");
		t.setBookId("B1");
		t.setVersion(1);
		t.setMaturityDate("13/07/2022");
		t.setExpired(false);
		ResponseEntity<String> responseEntity = tradeController.addTrade(t);
        
        
        Assert.assertTrue(responseEntity.getStatusCodeValue() == 200);
	}
	
	
}
