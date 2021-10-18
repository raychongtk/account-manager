package com.acmebank.accountmanager;

import com.acmebank.accountmanager.domain.BalanceType;
import com.acmebank.accountmanager.domain.BankAccount;
import com.acmebank.accountmanager.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class AccountManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAccounts(AccountRepository repository) {
        return args -> {
            Optional<BankAccount> accountOptional1 = repository.findBankAccountByAccount("12345678");
            Optional<BankAccount> accountOptional2 = repository.findBankAccountByAccount("88888888");

            if (accountOptional1.isPresent() && accountOptional2.isPresent()) return;

            // init data or data corrupted and need to re-init
            repository.deleteAll();
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

            repository.save(account1);
            repository.save(account2);
        };
    }
}
