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
@RequestMapping(value = "/withdrawal")
public class WithdrawalController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody StandardJsonResponse makeWithDrawal(@RequestBody UserTransaction userTransaction) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		final Long accountId =  userTransaction.getAccountId();

		try {

			// check balance
			double balance = accountService.findAccountById(accountId).getAmount();
			if (userTransaction.getAmount() > balance) {
				jsonResponse.setSuccess(false, "Error", "You have insufficient funds");
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
				return jsonResponse;
			}

			Account account = accountService.findAccountById(accountId);

			AccountTransaction accountTransaction = new AccountTransaction(TransactionType.WITHDRAWAL.getId(),
					userTransaction.getAmount(), new Date());
			transactionsService.addAccountTransaction(accountId, accountTransaction);

			double newBalance = account.getAmount() - accountTransaction.getAmount();
			account.setAmount(newBalance);

			jsonResponse.setSuccess(true, "", "Withdrawal sucessfully Transacted");
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
