package com.bank.webservice.test.junit.web.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.webservice.domain.Account;
import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.enums.TransactionType;
import com.bank.webservice.rest.models.UserTransaction;
import com.bank.webservice.web.controllers.WithdrawalController;
import com.google.gson.Gson;

/**
 * @author Ben hassine Houssem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WithdrawalController.class)
public class WithdrawalControllerTests extends BaseControllerTests {
	
	@Test
    public void testWithdrawalExceedsCurrentBalance() throws Exception {
		
		UserTransaction userTransaction = new UserTransaction(1L,50000);
    	Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
        
        given(this.accountService.findAccountById(1L)).willReturn(new Account(40000));
        
        this.mvc.perform(post("/withdrawal/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":false,\"messages\":{\"message\":\"You have insufficient funds\",\"title\":\"Error\"},\"errors\":{},\"data\":{},\"httpResponseCode\":406}"));
		
	}
	
	
	@Test
    public void testSuccessfulWithdrawal() throws Exception {
		
    	AccountTransaction transaction = new AccountTransaction(TransactionType.WITHDRAWAL.getId(), 5000, new Date());
    	AccountTransaction transaction2 = new AccountTransaction(TransactionType.WITHDRAWAL.getId(), 7500, new Date());
    	
    	List<AccountTransaction> list = new ArrayList<>();
    	list.add(transaction);
    	list.add(transaction2);
    	
    	UserTransaction userTransaction = new UserTransaction(1L, 1000);
    	Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
        
        given(this.accountService.findAccountById(1L)).willReturn(new Account(70000));  
        
        
        when(this.transactionsService.addAccountTransaction(any(Long.class),any(AccountTransaction.class))).thenReturn(transaction);
        when(this.accountService.createAccount(any(Long.class), any(Double.class))).thenReturn(new Account(400));
        
        this.mvc.perform(post("/withdrawal/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":true,\"messages\":{\"message\":\"Withdrawal sucessfully Transacted\",\"title\":\"\"},\"errors\":{},\"data\":{},\"httpResponseCode\":200}"));
		
	}	

}
