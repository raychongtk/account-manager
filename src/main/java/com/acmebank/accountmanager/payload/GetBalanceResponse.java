package com.acmebank.accountmanager.payload;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author raychong
 */
public class GetBalanceResponse {
    @NotNull
    public BigDecimal balance;
}
