package com.bank.webservice.test.junit.web.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.webservice.domain.Account;
import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.web.controllers.HistoryController;

/**
 * @author Ben hassine Houssem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(HistoryController.class)
public class HistoryControllerTests extends BaseControllerTests {
	
	@Autowired
    private MockMvc mvc;
    
    @Test
    public void testGetTransactionHistory() throws Exception {
    	List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();
    	transactions.add(new AccountTransaction(1, 200, new Date(0)));
        given(this.accountService.findAccountById(1L))
                .willReturn(new Account(400));
        given(this.transactionsService.getAccountTransactions(1L)).willReturn(transactions);
        
        this.mvc.perform(get("/transactionsHistory/?accountId=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":true,\"messages\":{},\"errors\":{},\"data\":{\"accountId\":1,\"accountTransactions\":[{\"type\":\"Deposit\",\"amount\":200.0,\"date\":\"1970-01-01T00:00:00.000+0000\"}],\"balance\":\"€400.0\"},\"httpResponseCode\":200}"));
    }
    
    @Test
    public void testGetTransactionHistoryAccountNotFound() throws Exception {
        given(this.accountService.findAccountById(1L))
                .willReturn(null);
        
        this.mvc.perform(get("/transactionsHistory/?accountId=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":false,\"messages\":{\"message\":\"Resource not found\",\"title\":\"Error\"},\"errors\":{},\"data\":{},\"httpResponseCode\":406}"));
    }

}
