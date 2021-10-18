package com.acmebank.accountmanager.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author raychong
 */
public class TransferMoneyRequest {
    @NotNull
    @NotBlank
    public String fromAccount;

    @NotNull
    @NotBlank
    public String toAccount;

    @NotNull
    @Min(1)
    public BigDecimal balance;
}
