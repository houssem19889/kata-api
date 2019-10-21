package com.bank.webservice.test.junit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.enums.TransactionType;
import com.bank.webservice.exceptions.AccountNotFoundException;
import com.bank.webservice.services.TransactionsService;

/**
 * @author Ben hassine Houssem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionsServiceTests {

	@Autowired
	TransactionsService transactionsService;

	@Test
	public void testGetAccountTransactions() {
		List<AccountTransaction> transactions = transactionsService.getAccountTransactions(1L);
		assertThat(transactions.get(0)).isNotNull();
		assertThat(transactions.get(0).getType()).isEqualTo(TransactionType.DEPOSIT.getId());
		assertThat(transactions.get(0).getAmount()).isEqualTo(100);
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void testGetAccountTransactionsAccountNotFoundException() {
		transactionsService.getAccountTransactions(5L);
	}
	
	@Test
	public void testAddAccountTransaction() {
		AccountTransaction accountTransaction = new AccountTransaction(1, 100, new Date(0));
		transactionsService.addAccountTransaction(1L, accountTransaction);
		List<AccountTransaction> transactions = transactionsService.getAccountTransactions(1L);
		assertThat(transactions.size()).isEqualTo(2);
	}

}
