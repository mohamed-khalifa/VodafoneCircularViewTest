package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John McDonnell on 21/07/2014
 */
public class DataPurchase {

    @SerializedName("price")
    private String mPrice;

    @SerializedName("data")
    private String mData;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("period")
    private String mPeriod;

    @SerializedName("sms")
    private String mSms;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("enabled")
    private boolean mEnabled;

    @SerializedName("ctaLabel")
    private String mCTALabel;

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setPeriod(String period) {
        mPeriod = period;
    }

    public String getPeriod() {
        return mPeriod;
    }

    public String getSms() {
        return mSms;
    }

    public void setSms(String sms) {
        mSms = sms;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public String getCTALabel() {
        return mCTALabel;
    }

    public void setCTALabel(String CTALabel) {
        mCTALabel = CTALabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPurchase that = (DataPurchase) o;

        if (mEnabled != that.mEnabled) return false;
        if (mPrice != null ? !mPrice.equals(that.mPrice) : that.mPrice != null) return false;
        if (mData != null ? !mData.equals(that.mData) : that.mData != null) return false;
        if (mDescription != null ? !mDescription.equals(that.mDescription) : that.mDescription != null)
            return false;
        if (mPeriod != null ? !mPeriod.equals(that.mPeriod) : that.mPeriod != null) return false;
        if (mSms != null ? !mSms.equals(that.mSms) : that.mSms != null) return false;
        if (mMessage != null ? !mMessage.equals(that.mMessage) : that.mMessage != null)
            return false;
        return mCTALabel != null ? mCTALabel.equals(that.mCTALabel) : that.mCTALabel == null;

    }
}
