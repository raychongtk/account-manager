package com.acmebank.accountmanager.payload;

import javax.validation.constraints.NotNull;

/**
 * @author raychong
 */
public class TransferMoneyResponse {
    public static TransferMoneyResponse success() {
        var response = new TransferMoneyResponse();
        response.success = Boolean.TRUE;
        return response;
    }

    public static TransferMoneyResponse failed(ErrorCode errorCode) {
        var response = new TransferMoneyResponse();
        response.success = Boolean.FALSE;
        response.errorCode = errorCode;
        return response;
    }

    @NotNull
    public Boolean success;

    public ErrorCode errorCode;

    public enum ErrorCode {
        ACCOUNT_NOT_EXISTS,
        BALANCE_NOT_ENOUGH
    }
}
