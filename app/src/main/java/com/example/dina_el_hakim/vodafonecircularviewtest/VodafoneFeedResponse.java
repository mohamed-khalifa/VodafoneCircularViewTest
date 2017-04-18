package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.dina_el_hakim.vodafonecircularviewtest.Status.RESULT_ERROR_GENERAL;
import static com.example.dina_el_hakim.vodafonecircularviewtest.Status.RESULT_ERROR_NEW_CUSTOMER;
import static com.example.dina_el_hakim.vodafonecircularviewtest.Status.RESULT_ERROR_TIMEOUT;
import static com.example.dina_el_hakim.vodafonecircularviewtest.Status.RESULT_ERROR_TOO_MANY_REQUESTS;

public class VodafoneFeedResponse {

    @SerializedName("status")
    private Status mHttpStatus;

    @SerializedName("accountId")
    private String mAccountId;

    @Expose(serialize = false)
    private boolean mCachedVersion;

    @Expose(serialize = false, deserialize = false)
    private String mResponseJson;

    public int getStatusCode() {
        return (getStatus() != null) ? getStatus().getCode() : -1;
    }

    public boolean isResponseSuccess() {
        return (getStatusCode() == Status.RESULT_SUCCESS);
    }

    public boolean isResponseErrorInvalidAuth() {
        return (getStatusCode() == Status.RESULT_ERROR_INVALID_AUTH);
    }

    public boolean isServiceSuspended() {
        return (getStatusCode() == Status.RESULT_SERVICE_SUSPENDED);
    }

    public boolean isError123() {
        return ((getStatusCode() == RESULT_ERROR_GENERAL) ||
                (getStatusCode() == RESULT_ERROR_TIMEOUT) ||
                (getStatusCode() == RESULT_ERROR_TOO_MANY_REQUESTS));
    }

    public boolean isError4() {
        return getStatusCode() == RESULT_ERROR_NEW_CUSTOMER;
    }

    public boolean isRigError() {
        return "RIG_ERROR".equals(mHttpStatus.getStatus());
    }

    public Status getStatus() {
        return mHttpStatus;
    }

    public void setStatus(Status status) {
        this.mHttpStatus = status;
    }

    public boolean isCachedVersion() {
        return mCachedVersion;
    }

    public void setCachedVersion(boolean cachedVersion) {
        this.mCachedVersion = cachedVersion;
    }

    public String getResponseJson() {
        return mResponseJson;
    }

    public void setResponseJson(String responseJson) {
        mResponseJson = responseJson;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public void setAccountId(String mAccountId) {
        this.mAccountId = mAccountId;
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(this);
    }

}
