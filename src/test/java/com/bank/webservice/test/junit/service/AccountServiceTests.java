package com.bank.webservice.test.junit.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.webservice.domain.Account;
import com.bank.webservice.exceptions.AccountExistException;
import com.bank.webservice.exceptions.AccountNotFoundException;
import com.bank.webservice.services.AccountService;

/**
 * @author Ben hassine Houssem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTests {
	
	
	@Autowired 
	AccountService accountService;
	
	@Test
	public void testFindAccountById() {
    	accountService.createAccount(3L,400);
		Account account = this.accountService.findAccountById(3L);
		assertThat(account.getAmount()).isEqualTo(400.0);
		
	}
	
	
	@Test(expected = AccountNotFoundException.class)
	public void testFindAccountByIdAccountNotFoundException() {
		accountService.findAccountById(5L);
	}
	
	@Test
	public void testCreateAccount() {
    	accountService.createAccount(4L,400);
		Account account = this.accountService.findAccountById(4L);
		assertThat(account.getAmount()).isEqualTo(400.0);
		
	}
	
	@Test(expected = AccountExistException.class)
	public void testCreateAccountAccountExistException() {
	   accountService.createAccount(1L,400);
	}
	
	
	
}

