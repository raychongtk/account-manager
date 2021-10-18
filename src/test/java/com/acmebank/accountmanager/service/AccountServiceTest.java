package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.domain.BalanceType;
import com.acmebank.accountmanager.domain.BankAccount;
import com.acmebank.accountmanager.payload.TransferMoneyResponse;
import com.acmebank.accountmanager.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author raychong
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        var account1 = new BankAccount();
        account1.id = UUID.randomUUID().toString();
        account1.account = "12345678";
        account1.balance = new BigDecimal("1000000");
        account1.balanceType = BalanceType.HKD;
        account1.createdTime = ZonedDateTime.now();

        var account2 = new BankAccount();
        account2.id = UUID.randomUUID().toString();
        account2.account = "88888888";
        account2.balance = new BigDecimal("1000000");
        account2.balanceType = BalanceType.HKD;
        account2.createdTime = ZonedDateTime.now();

        Mockito.when(accountRepository.findBankAccountByAccount(account1.account))
               .thenReturn(Optional.of(account1));

        Mockito.when(accountRepository.findBankAccountByAccount(account2.account))
               .thenReturn(Optional.of(account2));
    }

    @Test
    public void testTransfer() {
        TransferMoneyResponse response = accountService.transfer("12345678", "88888888", BigDecimal.ONE);

        assertThat(response.success).isEqualTo(true);
        assertThat(response.errorCode).isNull();
    }

    @Test
    public void testTransferToInvalidAccount() {
        TransferMoneyResponse response = accountService.transfer("12345678", "888888881", BigDecimal.ONE);

        assertThat(response.success).isEqualTo(false);
        assertThat(response.errorCode).isEqualTo(TransferMoneyResponse.ErrorCode.ACCOUNT_NOT_EXISTS);
    }

    @Test
    public void testTransferWithoutEnoughMoney() {
        TransferMoneyResponse response = accountService.transfer("12345678", "88888888", new BigDecimal("1237182738172831"));

        assertThat(response.success).isEqualTo(false);
        assertThat(response.errorCode).isEqualTo(TransferMoneyResponse.ErrorCode.BALANCE_NOT_ENOUGH);
    }
}
