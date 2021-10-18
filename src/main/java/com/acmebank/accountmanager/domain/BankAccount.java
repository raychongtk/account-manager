package com.acmebank.accountmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author raychong
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "account_number", columnNames = {"account"})})
public class BankAccount {
    @Id
    public String id;

    @Column(nullable = false)
    public String account;

    @Column(nullable = false)
    public BigDecimal balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public BalanceType balanceType;

    @Column(nullable = false)
    public ZonedDateTime createdTime;

    @Column
    public ZonedDateTime updatedTime;
}
