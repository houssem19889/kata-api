package com.bank.webservice.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.webservice.domain.Account;
import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.rest.models.TransactionInformation;
import com.bank.webservice.shared.web.StandardJsonResponse;
import com.bank.webservice.shared.web.StandardJsonResponseImpl;

/**
 * @author Ben hassine Houssem
 *
 */
@RestController
@RequestMapping(value = "/transactionsHistory")
public class HistoryController extends BaseController {

	@RequestMapping(value = "/",params = {"accountId"}, method = RequestMethod.GET)
	public @ResponseBody StandardJsonResponse getHistory(@RequestParam(value = "accountId") Long accountId) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		HashMap<String, Object> responseData = new HashMap<String, Object>();

		try {
			Account account = accountService.findAccountById(accountId);

			if (account != null) {
				List<AccountTransaction> accountTransactions = transactionsService.getAccountTransactions(accountId);
				List<TransactionInformation> TransactionInformations = accountTransactions.stream().map(accountTransaction-> new TransactionInformation(accountTransaction.getType()==1? "Deposit":"Withdrawal", accountTransaction.getAmount(), accountTransaction.getDate())).collect(Collectors.toList());
				responseData.put("accountTransactions",TransactionInformations);
				responseData.put("accountId", accountId);
				responseData.put("balance", "€" + account.getAmount());

				jsonResponse.setSuccess(true);
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
			} else {
				jsonResponse.setSuccess(false, "Error", "Resource not found");
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
			}

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
