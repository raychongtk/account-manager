package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.domain.BankAccount;
import com.acmebank.accountmanager.expcetion.BadRequestException;
import com.acmebank.accountmanager.payload.TransferMoneyResponse;
import com.acmebank.accountmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static com.acmebank.accountmanager.payload.TransferMoneyResponse.ErrorCode.ACCOUNT_NOT_EXISTS;
import static com.acmebank.accountmanager.payload.TransferMoneyResponse.ErrorCode.BALANCE_NOT_ENOUGH;

/**
 * @author raychong
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public BigDecimal getBalance(String account) {
        Optional<BankAccount> bankAccountOptional = findBankAccountByAccountNumber(account);
        if (bankAccountOptional.isEmpty()) throw new BadRequestException("account not exists");
        BankAccount bankAccount = bankAccountOptional.get();
        return bankAccount.balance;
    }

    @Transactional
    public TransferMoneyResponse transfer(String fromAccount, String toAccount, BigDecimal balance) {
        Optional<BankAccount> fromBankAccountOptional = findBankAccountByAccountNumber(fromAccount);
        if (fromBankAccountOptional.isEmpty()) return TransferMoneyResponse.failed(ACCOUNT_NOT_EXISTS);
        Optional<BankAccount> toBankAccountOptional = findBankAccountByAccountNumber(toAccount);
        if (toBankAccountOptional.isEmpty()) return TransferMoneyResponse.failed(ACCOUNT_NOT_EXISTS);

        BankAccount fromBankAccount = fromBankAccountOptional.get();
        boolean enoughBalance = fromBankAccount.balance.compareTo(balance) >= 0;
        if (!enoughBalance) return TransferMoneyResponse.failed(BALANCE_NOT_ENOUGH);

        BankAccount toBankAccount = toBankAccountOptional.get();
        fromBankAccount.balance = fromBankAccount.balance.subtract(balance);
        toBankAccount.balance = toBankAccount.balance.add(balance);
        accountRepository.save(fromBankAccount);
        accountRepository.save(toBankAccount);

        return TransferMoneyResponse.success();
    }

    private Optional<BankAccount> findBankAccountByAccountNumber(String account) {
        return accountRepository.findBankAccountByAccount(account);
    }
}
