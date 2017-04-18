package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rocio Ortega on 25/04/16.
 */
public class AllowanceValue {

    @SerializedName("type")
    private Type mType;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("dashboardTitle")
    private String mDashboardTitle;

    @SerializedName("dashboardSubTitle")
    private String mDashboardSubtitle;

    @SerializedName("usageTitle")
    private String mUsageTitle;

    @SerializedName("usageSubTitle")
    private String mUsageSubTitle;

    @SerializedName("usageUsed")
    private String mUsageUsed;

    @SerializedName("percentageRemaining")
    private float mPercentageRemaining;

    @SerializedName("state")
    private State mState;

    @SerializedName("displayType")
    private List<DisplayType> mDisplayTypes;

    @SerializedName("riderMessage")
    private String mRiderMessage;

    @SerializedName("warningMessage")
    private String mWarningMessage;

    @SerializedName("unlimited")
    private boolean mUnlimited;

    @SerializedName("usedMb")
    private float mUsedMb;

    @SerializedName("remainingMb")
    private float mRemainingMb;

    @SerializedName("includedMb")
    private float mIncludedMb;

    // added VDNA-729
    @SerializedName("titleText")
    private String mTitleText;

    @SerializedName("subTitleText")
    private String mSubTitleText;

    @SerializedName("usageLeft")
    private String mUsageLeft;

    @SerializedName("sectionIndex")
    public int mSectionIndex;


    public String getUsageLeft() {
        return mUsageLeft;
    }

    public void setUsageLeft(String usageLeft) {
        mUsageLeft = usageLeft;
    }

    public String getSubTitleText() {
        return mSubTitleText;
    }

    public void setSubTitleText(String subTitleText) {
        mSubTitleText = subTitleText;
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String titleText) {
        mTitleText = titleText;
    }

    public Type getAllowanceType() {
        return mType;
    }

    public void setAllowanceType(Type type) {
        mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDashboardTitle() {
        return mDashboardTitle;
    }

    public void setDashboardTitle(String dashboardTitle) {
        mDashboardTitle = dashboardTitle;
    }

    public String getDashboardSubtitle() {
        return mDashboardSubtitle;
    }

    public void setDashboardSubtitle(String dashboardSubtitle) {
        mDashboardSubtitle = dashboardSubtitle;
    }

    public String getUsageTitle() {
        return mUsageTitle;
    }

    public void setUsageTitle(String usageTitle) {
        mUsageTitle = usageTitle;
    }

    public String getUsageSubTitle() {
        return mUsageSubTitle;
    }

    public void setUsageSubTitle(String usageSubTitle) {
        mUsageSubTitle = usageSubTitle;
    }

    public String getUsageUsed() {
        return mUsageUsed;
    }

    public void setUsageUsed(String usageUsed) {
        mUsageUsed = usageUsed;
    }

    public int getSectionIndex() {
        return mSectionIndex;
    }

    public float getPercentageRemaining() {
        if (mPercentageRemaining > 100) {
            return 100;
        } else if (mPercentageRemaining < 0) {
            return 0;
        }

        return mPercentageRemaining;
    }

    public void setPercentageRemaining(float percentageRemaining) {
        mPercentageRemaining = percentageRemaining;
    }

    public List<DisplayType> getDisplayTypes() {
        return mDisplayTypes;
    }

    public void setDisplayTypes(List<DisplayType> displayTypes) {
        mDisplayTypes = displayTypes;
    }

    public boolean supportsDashboard() {
        return mDisplayTypes != null && mDisplayTypes.contains(DisplayType.DASHBOARD);
    }

    public boolean supportsUsage() {
        return mDisplayTypes != null && mDisplayTypes.contains(DisplayType.USAGE);
    }

    public boolean isUnlimited() {
        return mUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        mUnlimited = unlimited;
    }

    public float getUsedMb() {
        return mUsedMb;
    }

    public void setUsedMb(float usedMb) {
        mUsedMb = usedMb;
    }

    public float getRemainingMb() {
        return mRemainingMb;
    }

    public void setRemainingMb(float remainingMb) {
        mRemainingMb = remainingMb;
    }

    public float getIncludedMb() {
        return mIncludedMb;
    }

    public void setIncludedMb(float includedMb) {
        mIncludedMb = includedMb;
    }

    public String getRiderMessage() {
        return mRiderMessage;
    }

    public void setRiderMessage(String riderMessage) {
        mRiderMessage = riderMessage;
    }

    public String getWarningMessage() {
        return mWarningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        mWarningMessage = warningMessage;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public static enum Type {
        @SerializedName("group_data")
        GROUP_DATA,

        @SerializedName("data")
        DATA,

        @SerializedName("voice")
        VOICE,

        @SerializedName("sms")
        SMS,

        @SerializedName("sms_and_voice")
        VOICE_AND_SMS,

        @SerializedName("broadband")
        BROADBAND,

        @SerializedName("anytime_voice")
        ANYTIME_VOICE,

        @SerializedName("mobile_voice")
        MOBILE_VOICE,

        @SerializedName("international_voice")
        INT_VOICE,

        @SerializedName("weekend_voice")
        WEEKEND_VOICE,

        @SerializedName("landline_voice")
        LANDLINE_VOICE,

        @SerializedName("mms")
        MMS
    }

    public static enum DisplayType {
        USAGE, DASHBOARD
    }

    public static enum State {
        OK,
        WARN,
        ALERT
    }

    @Override
    public String toString() {
        return "type " + mType + "; isUnlimited " + mUnlimited;
    }
}
