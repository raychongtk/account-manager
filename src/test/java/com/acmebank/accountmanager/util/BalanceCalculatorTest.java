package com.acmebank.accountmanager.util;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author raychong
 */
public class BalanceCalculatorTest {
    private final BalanceCalculator balanceCalculator = new BalanceCalculator();

    @Test
    public void testAddBalance() {
        BigDecimal balance = balanceCalculator.add(BigDecimal.TEN, BigDecimal.ONE);
        assertThat(balance).isEqualTo(BigDecimal.valueOf(11));
    }

    @Test
    public void testSubtractBalance() {
        BigDecimal balance = balanceCalculator.subtract(BigDecimal.TEN, BigDecimal.ONE);
        assertThat(balance).isEqualTo(BigDecimal.valueOf(9));
    }
}
