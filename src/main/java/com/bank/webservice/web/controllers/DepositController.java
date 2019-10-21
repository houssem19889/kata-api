package com.bank.webservice.web.controllers;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.webservice.domain.Account;
import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.enums.TransactionType;
import com.bank.webservice.rest.models.UserTransaction;
import com.bank.webservice.shared.web.StandardJsonResponse;
import com.bank.webservice.shared.web.StandardJsonResponseImpl;

/**
 * @author Ben hassine Houssem
 * 
 */
@RestController
@RequestMapping("/deposit")
public class DepositController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody StandardJsonResponse makeDeposit(@RequestBody UserTransaction userTransaction) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		final Long accountId =  userTransaction.getAccountId();

		try {

			@SuppressWarnings("unused")
			double total = 0;

			AccountTransaction accountTransaction = new AccountTransaction(TransactionType.DEPOSIT.getId(),
					userTransaction.getAmount(), new Date());
			transactionsService.addAccountTransaction(accountId, accountTransaction);

			Account account = accountService.findAccountById(accountId);
			double newBalance = account.getAmount() + accountTransaction.getAmount();
			account.setAmount(newBalance);
			jsonResponse.setSuccess(true, "", "Deposit sucessfully Transacted");

			jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.DEFAULT_MSG_NAME_VALUE);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return jsonResponse;
		}

		return jsonResponse;
	}

}
