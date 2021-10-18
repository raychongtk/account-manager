package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.payload.GetBalanceResponse;
import com.acmebank.accountmanager.payload.TransferMoneyRequest;
import com.acmebank.accountmanager.payload.TransferMoneyResponse;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author raychong
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{accountNumber}")
    public GetBalanceResponse getBalance(@PathVariable(name = "accountNumber") String accountNumber) {
        BigDecimal balance = accountService.getBalance(accountNumber);

        var response = new GetBalanceResponse();
        response.balance = balance;
        return response;
    }

    @PostMapping("/transfer")
    public TransferMoneyResponse transfer(@RequestBody TransferMoneyRequest request) {
        return accountService.transfer(request.fromAccount, request.toAccount, request.balance);
    }
}
