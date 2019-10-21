package com.bank.webservice.test.junit.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.webservice.services.AccountService;
import com.bank.webservice.services.TransactionsService;

/**
 * @author Ben hassine Houssem
 *
 */
public class BaseControllerTests {
	
	@Autowired
    protected MockMvc mvc;

    @MockBean
    protected AccountService accountService;
    
    @MockBean
    protected TransactionsService transactionsService;

}
