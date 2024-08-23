package org.example.common.enums;

import org.example.exception.BaseErrorInfoInterface;

/**
 * exception enum
 */
public enum ExceptionEnum implements BaseErrorInfoInterface {

    SUCCESS("2000", "success!"),
    PARAM_ILLEGAL("4001","param illegal!"),
    NOT_FOUND("4004", "not found resources!"),
    EMAIL_SEND_FAIL("4006", "email send fail!"),
    USER_REGISTER_FAIL("4007", "user register fail!"),
    USER_NOT_EXIST("4008", "user not exist!"),
    INTERNAL_SERVER_ERROR("5000", "internal server error!"),
    SERVER_BUSY("5003","server busy, please try later!");

    /**
     * error code
     */
    private final String errorCode;

    /**
     * error msg
     */
    private final String errorMsg;

    ExceptionEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
