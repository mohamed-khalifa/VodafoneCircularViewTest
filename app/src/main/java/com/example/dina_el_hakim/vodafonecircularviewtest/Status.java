package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

public class Status {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_ERROR_GENERAL = 1;
    public static final int RESULT_ERROR_TIMEOUT = 2;
    public static final int RESULT_ERROR_TOO_MANY_REQUESTS = 3;
    public static final int RESULT_ERROR_NEW_CUSTOMER = 4;
    public static final int RESULT_INVALID_LOGIN = 50;
    public static final int RESULT_ACCOUNT_LOCKED = 51;
    public static final int RESULT_GENERAL_LOGIN_FAILURE = 52;
    public static final int RESULT_NOT_AUTHENTICATED_ERROR = 61;
    public static final int RESULT_ERROR_INVALID_AUTH = 98;
    public static final int RESULT_SERVICE_SUSPENDED = 99;

    @SerializedName("code")
    private Integer mCode;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("fingerPrint")
    private String mFingerPrint;

    public Integer getCode() {
        return mCode;
    }

    public void setCode(Integer code) {
        mCode = code;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public void setFingerPrint(String fingerPrint) {
        mFingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return mFingerPrint;
    }

}
