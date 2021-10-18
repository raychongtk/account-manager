package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author raychong
 */
@Repository
public interface AccountRepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findBankAccountByAccount(String account);
}
