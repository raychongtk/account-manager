package com.acmebank.accountmanager.util;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author raychong
 */
public class BalanceCalculator {
    @NotNull
    public BigDecimal add(@NotNull BigDecimal origin, @NotNull BigDecimal valueToAdd) {
        return origin.add(valueToAdd);
    }

    @NotNull
    public BigDecimal subtract(@NotNull BigDecimal origin, @NotNull BigDecimal valueToSubtract) {
        return origin.subtract(valueToSubtract);
    }
}
