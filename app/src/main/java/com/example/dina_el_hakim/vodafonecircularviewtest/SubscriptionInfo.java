package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionInfo extends VodafoneFeedResponse {
//
//    private AccountActivity.AccountType accountType;
//
//    public static enum AccountSegment {
//        // PrePay
//        PREPAY,
//        // PostPay
//        CONSUMER, BROADBAND, LANDLINE,
//        // Business
//        SME, CORPORATE, DISE, CONSUMERSME
//    }
//
//    public enum BillingPlatform {
//        NEWCO, HERITAGE
//    }
//
//    @SerializedName("billPlatform")
//    private BillingPlatform mBillingPlatform;
//
//    @SerializedName("segment")
//    private AccountSegment mSegment;
//
//    @SerializedName("prepay")
//    private PrePayAccount mPrepayAccount;
//
//    @SerializedName("notifications")
//    private ArrayList<Notification> mNotifications = new ArrayList<>();
//
//    @SerializedName("postpay")
//    private PostPayAccount mPostpayAccount;
//
//    @SerializedName("corporate")
//    private BusinessAccount mBusinessAccount;
//
//    private long mLastUpdatedTime;
//
//    public BillingPlatform getBillingPlatform() {
//        return mBillingPlatform;
//    }
//
//    public void setBillingPlatform(BillingPlatform billingPlatform) {
//        mBillingPlatform = billingPlatform;
//    }
//
//    public AccountSegment getSegment() {
//        return mSegment;
//    }
//
//    public void setSegment(AccountSegment segment) {
//        mSegment = segment;
//    }
//
//    public PrePayAccount getPrepayAccount() {
//        return mPrepayAccount;
//    }
//
//    public void setPrepayAccount(PrePayAccount prepayAccount) {
//        mPrepayAccount = prepayAccount;
//    }
//
//    public PostPayAccount getPostpayAccount() {
//        return mPostpayAccount;
//    }
//
//    public void setPostpayAccount(PostPayAccount postpayAccount) {
//        mPostpayAccount = postpayAccount;
//    }
//
//    public BusinessAccount getBusinessAccount() {
//        return mBusinessAccount;
//    }
//
//    public void setBusinessAccount(BusinessAccount businessAccount) {
//        mBusinessAccount = businessAccount;
//    }
//
//    public long getLastUpdatedTime() {
//        return mLastUpdatedTime;
//    }
//
//    public void setLastUpdatedTime(long lastUpdatedTime) {
//        mLastUpdatedTime = lastUpdatedTime;
//    }
//
//    public Account<?> getAccount() {
//        if (isPrepayAccount()) {
//            return getPrepayAccount();
//        } else if (isPostpayAccount()) {
//            return getPostpayAccount();
//        } else if (isBusinessAccount()) {
//            return getBusinessAccount();
//        }
//        return null;
//    }
//
//    public boolean isBusinessAccount() {
//        return (mSegment != null
//                && (mSegment.equals(AccountSegment.SME) || mSegment.equals(AccountSegment.CORPORATE)
//                || mSegment.equals(AccountSegment.DISE) || mSegment.equals(AccountSegment.CONSUMERSME))
//                && mBusinessAccount != null);
//    }
//
//    public boolean isPrepayAccount() {
//        return (mSegment != null
//                && mSegment.equals(AccountSegment.PREPAY)
//                && mPrepayAccount != null);
//    }
//
//    public boolean isPostpayAccount() {
//        return (mSegment != null
//                && (mSegment.equals(AccountSegment.CONSUMER) || mSegment.equals(AccountSegment.LANDLINE)
//                || mSegment.equals(AccountSegment.BROADBAND))
//                && mPostpayAccount != null);
//    }
//
//    public boolean isHeritagePlatform() {
//        return (mBillingPlatform != null && mBillingPlatform.equals(BillingPlatform.HERITAGE));
//    }
//
//    public boolean isNewCoPlatform() {
//        return (mBillingPlatform != null && mBillingPlatform.equals(BillingPlatform.NEWCO));
//    }
//
//    public boolean hasExtras() {
//        Account<?> account = getAccount();
//        return (account != null) && account.hasExtras();
//    }
//
//    public List<Extra> getExtras() {
//        return (hasExtras()) ? getAccount().getExtras() : new ArrayList<Extra>();
//    }
//
//    public List<Extra> getExtras(Extra.Status status) {
//        return (hasExtras()) ? getAccount().getExtras(status) : new ArrayList<Extra>();
//    }
//
//    public boolean hasAllowance() {
//        Account<?> account = getAccount();
//        return (account != null) && getAccount().getAllowance() != null;
//    }
//
//    public Allowance getAllowance() {
//        return (hasAllowance()) ? getAccount().getAllowance() : null;
//    }
//
//    public AccountInfoDetail getAccountInfoDetail() {
//        Account<?> account = getAccount();
//        return (account != null) ? getAccount().getAccountInfoDetail() : null;
//    }
//
//    public ArrayList<MenuItem> getMenuItems() {
//        return getAccount().getMenuItems();
//    }
//
//    public String getTitleFromTarget(String target) {
//        for (MenuItem menuItem : getMenuItems()) {
//            if (target.equals(menuItem.getTarget())) {
//                return menuItem.getPrimaryTitle();
//            } else if (!menuItem.getSubMenuItems().isEmpty()) {
//                for (MenuItem subMenuItem : menuItem.getSubMenuItems()) {
//                    if (target.equals(subMenuItem.getTarget())) {
//                        return subMenuItem.getPrimaryTitle();
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    public ArrayList<Notification> getNotifications() {
//        return mNotifications;
//    }
//
//    public void setNotifications(ArrayList<Notification> notifications) {
//        this.mNotifications = notifications;
//    }
}
