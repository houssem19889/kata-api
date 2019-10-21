package com.bank.webservice.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bank.webservice.services.AccountService;
import com.bank.webservice.services.TransactionsService;

/**
 * @author Ben hassine Houssem
 *
 */
public class BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionsService transactionsService;

}
