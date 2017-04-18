package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VodafoneAppConfig extends StoredConfiguration {

    protected VodafoneAppConfig(Context context, SharedPreferences prefs) {
        super(context, prefs);
    }

    @Override
    protected void onReadConfiguration(SharedPreferences prefs) {

    }

    @Override
    protected void onSaveConfiguration(Editor editor) {

    }

//
    //    @Override
//    protected void onSaveConfiguration(Editor editor) {
//    private static final String TAG = VodafoneAppConfig.class.getSimpleName();
//
//    private static final String DEFAULT_HARDCODED_MSISDN = "447000000103";
//    private static final String DEFAULT_HARDCODED_IMSI = "123456789012345";
//
//    // Network preference keys
//    public static final String BUILD_HARDCODED = "build.hardcoded";
//    public static final String BUILD_HARDCODED_OS = "build.hardcoded_os";
//    public static final String BUILD_HARDCODED_MANUFACTURER = "build.hardcoded_manufacturer";
//    public static final String BUILD_HARDCODED_MODEL = "build.hardcoded_model";
//    public static final String BUILD_HARDCODED_MSISDN = "build.hardcoded_msisdn";
//    public static final String BUILD_HARDCODED_IMSI = "build.hardcoded_imsi";
//    public static final String BUILD_HARDCODED_MCC = "build.hardcoded_mcc";
//    public static final String BUILD_HARDCODED_MNC = "build.hardcoded_mnc";
//    public static final String BUILD_SHOW_STRING_KEYS = "build.show_string_keys";
//    public static final String BUILD_PROTECT_CUSTOMER_DATA = "build.protect_customer_data";
//    public static final String BUILD_ONLINE_CONTEXT_URL = "build.online_context_url";
//    public static final String NETWORK_HOST_ID = "network.host_id";
//    public static final String NETWORK_CACHED_MSISDN = "network.cached_msisdn";
//    public static final String NETWORK_CACHED_IMSI = "network.cached_imsi";
//    public static final String NETWORK_CACHED_MCC = "network.cached_mcc";
//    public static final String NETWORK_CACHED_MNC = "network.cached_mnc";
//    public static final String NETWORK_ENCRYPTED_MSISDN = "network.encrypted_msisdn";
//    public static final String SECURITY_TOKEN = "network.security_token";
//    public static final String SECURITY_TOKEN_LAST_UPDATED_DATED = "network.security_token_last_updated_date";
//    public static final String SECURITY_TOKEN_MIN_VALIDITY = "network.security_token_min_validity";
//    public static final String SECURITY_TOKEN_MAX_VALIDITY = "network.security_token_max_validity";
//    public static final String VERSION_APP = "version.app";
//    public static final String VERSION_CACHED_ASSETS = "version.cachedassets";
//    public static final String VERSION_CONTENT = "version.content";
//    public static final String VERSION_GEO_OFFER = "version.geo_offer";
//    public static final String VERSION_HELP = "version.help";
//    public static final String VERSION_CONFIG = "version.config";
//    public static final String KILLSWITCH_DATE = "version.last_killswitch_date";
//    public static final String PREEMPTIVE_SHARER_DATA_FETCH = "network.preemptive_sharer_data_fetch";
//    public static final String MAX_SHARER_DATA_LOCK = "network.max_sharer_data_lock";
//    public static final String SHARER_DETAIL_FINGERPRINT = "version.sharer_detail_fingerprint";
//    public static final String CONFIG_LAST_UPDATED = "config_last_updated";
//    public static final String ACCOUNT_ID = "account_id";
//    public static final String ACCOUNT_LAST_UPDATED = "account_last_updated";
//    public static final String SHARER_DETAIL_LAST_UPDATED = "sharer_detail_last_updated";
//    public static final String USER_PIN_SETUP = "user.pin_setup";
//
//    // App preference keys
//    public static final String APP_LAST_OPEN_VERSION = "app.last_open_version";
//    public static final String APP_RATING_SHOW = "app.rating_show";
//    public static final String APP_RATING_TIME = "app.rating_time";
//    public static final String APP_LAST_RATING_INCREMENT_TIME = "app.last_rating_increment_time";
//    public static final String APP_APP_OPENS = "app.app_opens";
//    public static final String APP_RATING_COUNT = "app.rating_count";
//    public static final String APP_MIGRATED = "app.migrated";
//    public static final String KILLSWITCH_ACTIVATED = "app.should_activate_killswitch";
//    public static final String SERVICE_LAST_ASSETS_UPDATE = "service.last_assets_update";
//    public static final String USER_LAST_GEO_OFFER_TIME = "user.last_geo_offer_time";
//    public static final String USER_LAST_GEO_OFFER_ID = "user.last_geo_offer_id";
//    public static final String USER_LAST_PROMOTION_ID = "user.last_promotion_id";
//    public static final String USER_TERMS_ACCEPTED = "user.terms_accepted";
//    public static final String USER_SPLASH_TUTORIAL_COMPLETED = "user.splash_tutorial_completed";
//    public static final String APP_UPGRADE_REMINDER_HIDE_DATE = "app.upgrade_reminder_hide_date";
//    public static final String APP_PENDING_DATA_LIMIT_CHANGES = "app.pending_limit_changes";
//    public static final String APP_PENDING_DATA_PURCHASES = "app.pending_data_purchases";
//    public static final String APP_PENDING_EXTRAS = "app.pending_extras";
//    public static final String USER_USERNAME = "user.username";
//    public static final String APP_DISMISSED_NOTIFICATIONS = "app.dismissed_notifications";
//    public static final String APP_PIN_TUTORIAL_LATER = "app.pin_tutorial_later";
//    public static final String APP_PERIOD_ZERO_INVOICE_ID = "app.period_zero_invoice_id";
//    public static final String APP_BILLSUMMARY_NOTIFICATIONS = "app.bill_summary_notifications";
//    public static final String APP_PUSH_NOTIFICATIONS_ENABLED = "app.push_notifications_enabled";
//    public static final String APP_FIRST_RUN = "app.first_run";
//    public static final String APP_REQEST_LIST_PERMISSIONS = "app.request_list_permissions";
//    public static final String NETPERFORM_SERVICE_OPTED_IN = "netperform.service_opted_in";
//    public static final String IS_MSISDN_CHANGED = "app.is_msisdn_changed";
//
//    private boolean mShowStringKeys;
//    private boolean mProtectCustomerData;
//    private boolean mHardcoded;
//    private boolean mKillSwitchActivated;
//    private boolean mPreemptiveSharerDataFetch;
//    private boolean mPinSetup;
//    private String mHardcodedOS;
//    private String mHardcodedManufacturer;
//    private String mHardcodedModel;
//    private String mHardcodedMSISDN;
//    private String mHardcodedIMSI;
//    private String mHardcodedMCC;
//    private String mHardcodedMNC;
//    private String mCachedMSISDN;
//    private String mCachedIMSI;
//    private String mCachedMCC;
//    private String mCachedMNC;
//    private String mEncryptedMSISDN;
//    private String mSecurityToken;
//    private long mSecurityTokenLastUpdatedDate;
//    private long mTokenRefreshDate;
//    private long mTokenExpiryDate;
//    private long mMaxSharerDataLock;
//    private long mConfigLastUpdatedDate;
//    private String mAccountID;
//    private long mAccountLastUpdatedDate;
//    private long mSharerDetailLastUpdatedDate;
//    private String mVersionApp;
//    private String mVersionCachedAssets;
//    private String mVersionContent;
//    private String mVersionGeoOffer;
//    private String mVersionHelp;
//    private String mKillSwitchDate;
//    private String mVersionConfig;
//    private String mSharerDetailFingerprint;
//    private String mCurrentHostUrl;
//    private boolean mAppRatingShow;
//    private long mAppOpens;
//    private long mRatingCount;
//    private long mLastAppRatingIncrementTime;
//    private long mAppRatingTime;
//    private String mLastOpenAppVersion;
//    private String mLastCachedAssetsUpdate;
//    private int mLastViewedPromotionId;
//    private int mLastViewedGeoOfferId;
//    private Map<String, Long> mLastGeoOfferTime;
//    private boolean mTermsAndConditionsAccepted;
//    private boolean mSplashTutorialCompleted;
//    private boolean mMigrated;
//    private long mUpgradeReminderHideDate;
//    private String mUsername;
//    private HashMap<String, HashMap<Integer, Long>> mDismissedNotifications;
//    private Map<String, String> mBillSummaryNotifications;
//    private Set<String> mPendingDataLimitChanges;
//    private Set<String> mPendingDataPurchases;
//    private Set<String> mPendingExtras;
//    private boolean mPinTutorialLater;
//    private String mPeriodZeroInvoiceId;
//    private boolean mPushNotificationsEnabled;
//    private boolean mIsAppFirstRun;
//    private boolean mAreListOfPermissionsRequested;
//    private boolean mNetPerformServiceOptedIn;
//    private boolean mIsMsisdnChanged;
//    private String mOnlineContextUrl;
//
//    public VodafoneAppConfig(Context context) {
//        super(context, context.getSharedPreferences(VodafoneAppConfig.class.getSimpleName(), Context.MODE_PRIVATE));
//    }
//
//    public static VodafoneAppConfig get() {
//        return vodafoneAppConfig();
//    }
//
//    public NetworkHost getNetworkHost() {
//        return UrlFactory.getNetworkHost();
//    }
//
//    @Override
//    protected void onReadConfiguration(SharedPreferences prefs) {
////
////        if (Global.isConfigurableBuild()) {
////            setHardcoded(prefs.getBoolean(BUILD_HARDCODED, Global.isConfigurableBuild()));
////            setHardcodedDeviceOs(prefs.getString(BUILD_HARDCODED_OS, Build.VERSION.RELEASE));
////            setHardcodedManufacturer(prefs.getString(BUILD_HARDCODED_MANUFACTURER, null));
////            setHardcodedDeviceModel(prefs.getString(BUILD_HARDCODED_MODEL, null));
////            setHardcodedMSISDN(prefs.getString(BUILD_HARDCODED_MSISDN, DEFAULT_HARDCODED_MSISDN));
////            setHardcodedIMSI(prefs.getString(BUILD_HARDCODED_IMSI, DEFAULT_HARDCODED_IMSI));
////            setHardcodedMCC(prefs.getString(BUILD_HARDCODED_MCC, null));
////            setShowStringKeys(prefs.getBoolean(BUILD_SHOW_STRING_KEYS, false));
////            setProtectCustomerData(prefs.getBoolean(BUILD_PROTECT_CUSTOMER_DATA, true));
////            setOnlineContextUrl(prefs.getString(BUILD_ONLINE_CONTEXT_URL, OnlineContextUrlFactory.URL_DEFAULT_TEMPLATE));
////        }
////
////        mCachedMSISDN = prefs.getString(NETWORK_CACHED_MSISDN, null);
////        mCachedIMSI = prefs.getString(NETWORK_CACHED_IMSI, TelephonyUtil.getIMSIFromDevice(getContext()));
////        mCachedMCC = prefs.getString(NETWORK_CACHED_MCC, TelephonyUtil.getMCCFromDevice(getContext()));
////        mCachedMNC = prefs.getString(NETWORK_CACHED_MNC, TelephonyUtil.getMNCFromDevice(getContext()));
////        mEncryptedMSISDN = prefs.getString(NETWORK_ENCRYPTED_MSISDN, null);
////        mSecurityToken = prefs.getString(SECURITY_TOKEN, null);
////        mSecurityTokenLastUpdatedDate = prefs.getLong(SECURITY_TOKEN_LAST_UPDATED_DATED, 0);
////        mTokenRefreshDate = prefs.getLong(SECURITY_TOKEN_MIN_VALIDITY, ConfigInfo.DEFAULT_MIN_SECURITY_TOKEN);
////        mTokenExpiryDate = prefs.getLong(SECURITY_TOKEN_MAX_VALIDITY, ConfigInfo.DEFAULT_MAX_SECURITY_TOKEN);
////        mVersionApp = prefs.getString(VERSION_APP, null);
////        mVersionCachedAssets = prefs.getString(VERSION_CACHED_ASSETS, null);
////        mVersionContent = prefs.getString(VERSION_CONTENT, null);
////        mVersionGeoOffer = prefs.getString(VERSION_GEO_OFFER, null);
////        mVersionHelp = prefs.getString(VERSION_HELP, null);
////        mVersionConfig = prefs.getString(VERSION_CONFIG, null);
////        mKillSwitchDate = prefs.getString(KILLSWITCH_DATE, null);
////        mKillSwitchActivated = prefs.getBoolean(KILLSWITCH_ACTIVATED, false);
////        mPreemptiveSharerDataFetch = prefs.getBoolean(PREEMPTIVE_SHARER_DATA_FETCH, false);
////        mMaxSharerDataLock = prefs.getLong(MAX_SHARER_DATA_LOCK, 0);
////        mSharerDetailFingerprint = prefs.getString(SHARER_DETAIL_FINGERPRINT, null);
////        mCurrentHostUrl = prefs.getString(NETWORK_HOST_ID, null);
////        mAccountID = prefs.getString(ACCOUNT_ID, null);
////        mAccountLastUpdatedDate = prefs.getLong(ACCOUNT_LAST_UPDATED, 0);
////        mSharerDetailLastUpdatedDate = prefs.getLong(SHARER_DETAIL_LAST_UPDATED, 0);
////        mConfigLastUpdatedDate = prefs.getLong(CONFIG_LAST_UPDATED, 0);
////        mPinSetup = prefs.getBoolean(USER_PIN_SETUP, false);
////
////        mLastOpenAppVersion = prefs.getString(APP_LAST_OPEN_VERSION, BuildConfig.VERSION_NAME);
////        mLastCachedAssetsUpdate = prefs.getString(SERVICE_LAST_ASSETS_UPDATE, null);
////        mAppRatingShow = prefs.getBoolean(APP_RATING_SHOW, true);
////        mAppRatingTime = prefs.getLong(APP_RATING_TIME, 0);
////        mAppOpens = prefs.getLong(APP_APP_OPENS, 0);
////        mLastAppRatingIncrementTime = prefs.getLong(APP_LAST_RATING_INCREMENT_TIME, 0);
////        mRatingCount = prefs.getLong(APP_RATING_COUNT, 0);
////        mMigrated = prefs.getBoolean(APP_MIGRATED, false);
////        mLastViewedPromotionId = prefs.getInt(USER_LAST_PROMOTION_ID, -1);
////        mLastViewedGeoOfferId = prefs.getInt(USER_LAST_GEO_OFFER_ID, -1);
////        mUpgradeReminderHideDate = prefs.getLong(APP_UPGRADE_REMINDER_HIDE_DATE, 0);
////        mUsername = prefs.getString(USER_USERNAME, null);
////        try {
////            String dismissedPromotions = prefs.getString(APP_DISMISSED_NOTIFICATIONS, null);
////            if (!TextUtils.isEmpty(dismissedPromotions)) {
////                mDismissedNotifications = new Gson().fromJson(dismissedPromotions, new TypeToken<HashMap<String, HashMap<Integer, Long>>>() {
////                }.getType());
////            } else {
////                mDismissedNotifications = new HashMap<>();
////            }
////        } catch (ClassCastException e) {
////            mDismissedNotifications = new HashMap<>();
////        } catch (RuntimeException e) {
////            mDismissedNotifications = new HashMap<>();
////        } catch (Exception e) {
////            mDismissedNotifications = new HashMap<>();
////        }
////
////        String billSummaryNotifications = prefs.getString(APP_BILLSUMMARY_NOTIFICATIONS, null);
////        if (!TextUtils.isEmpty(billSummaryNotifications)) {
////            mBillSummaryNotifications = new Gson().fromJson(billSummaryNotifications, new TypeToken<Map<String, String>>() {
////            }.getType());
////        } else {
////            mBillSummaryNotifications = null;
////        }
////
////        mPinTutorialLater = prefs.getBoolean(APP_PIN_TUTORIAL_LATER, false);
////        mPeriodZeroInvoiceId = prefs.getString(APP_PERIOD_ZERO_INVOICE_ID, null);
////
////        String geoMccJson = prefs.getString(USER_LAST_GEO_OFFER_TIME, null);
////        if (!TextUtils.isEmpty(geoMccJson)) {
////            mLastGeoOfferTime = new Gson().fromJson(geoMccJson, new TypeToken<Map<String, Long>>() {
////            }.getType());
////        } else {
////            mLastGeoOfferTime = null;
////        }
////
////        mTermsAndConditionsAccepted = prefs.getBoolean(USER_TERMS_ACCEPTED, false);
////        mSplashTutorialCompleted = prefs.getBoolean(USER_SPLASH_TUTORIAL_COMPLETED, false);
////
////        mPendingDataLimitChanges = prefs.getStringSet(APP_PENDING_DATA_LIMIT_CHANGES, new HashSet<String>());
////        mPendingDataPurchases = prefs.getStringSet(APP_PENDING_DATA_PURCHASES, new HashSet<String>());
////        mPendingExtras = prefs.getStringSet(APP_PENDING_EXTRAS, new HashSet<String>());
////        mPushNotificationsEnabled = prefs.getBoolean(APP_PUSH_NOTIFICATIONS_ENABLED, false);
////        mIsAppFirstRun = prefs.getBoolean(APP_FIRST_RUN, false);
////        mAreListOfPermissionsRequested = prefs.getBoolean(APP_REQEST_LIST_PERMISSIONS, false);
////        mNetPerformServiceOptedIn = prefs.getBoolean(NETPERFORM_SERVICE_OPTED_IN, false);
////        mIsMsisdnChanged = prefs.getBoolean(IS_MSISDN_CHANGED, false);
////    }
////
//    @Override
//    protected void onSaveConfiguration(Editor editor) {
////        if (Global.isConfigurableBuild()) {
////            editor.putBoolean(BUILD_HARDCODED, isConfigurableAndMsisdnHardcoded());
////            editor.putString(BUILD_HARDCODED_OS, mHardcodedOS);
////            editor.putString(BUILD_HARDCODED_MANUFACTURER, mHardcodedManufacturer);
////            editor.putString(BUILD_HARDCODED_MODEL, mHardcodedModel);
////            editor.putString(BUILD_HARDCODED_MSISDN, mHardcodedMSISDN);
////            editor.putString(BUILD_HARDCODED_IMSI, mHardcodedIMSI);
////            editor.putString(BUILD_HARDCODED_MCC, mHardcodedMCC);
////            editor.putString(BUILD_HARDCODED_MNC, mHardcodedMNC);
////            editor.putBoolean(BUILD_SHOW_STRING_KEYS, shouldShowStringKeys());
////            editor.putBoolean(BUILD_PROTECT_CUSTOMER_DATA, mProtectCustomerData);
////            editor.putString(BUILD_ONLINE_CONTEXT_URL, mOnlineContextUrl);
////        }
////
////        editor.putBoolean(KILLSWITCH_ACTIVATED, isKillSwitchActivated());
////        editor.putBoolean(PREEMPTIVE_SHARER_DATA_FETCH, mPreemptiveSharerDataFetch);
////        editor.putBoolean(USER_PIN_SETUP, mPinSetup);
////        editor.putString(KILLSWITCH_DATE, getKillSwitchDate());
////        editor.putString(NETWORK_HOST_ID, mCurrentHostUrl);
////        editor.putString(NETWORK_CACHED_MSISDN, mCachedMSISDN);
////        editor.putString(NETWORK_CACHED_IMSI, mCachedIMSI);
////        editor.putString(NETWORK_CACHED_MCC, mCachedMCC);
////        editor.putString(NETWORK_CACHED_MNC, mCachedMNC);
////        editor.putString(NETWORK_ENCRYPTED_MSISDN, mEncryptedMSISDN);
////        editor.putString(SECURITY_TOKEN, mSecurityToken);
////        editor.putLong(SECURITY_TOKEN_LAST_UPDATED_DATED, mSecurityTokenLastUpdatedDate);
////        editor.putLong(SECURITY_TOKEN_MIN_VALIDITY, mTokenRefreshDate);
////        editor.putLong(SECURITY_TOKEN_MAX_VALIDITY, mTokenExpiryDate);
////        editor.putLong(MAX_SHARER_DATA_LOCK, mMaxSharerDataLock);
////        editor.putString(VERSION_APP, mVersionApp);
////        editor.putString(VERSION_CACHED_ASSETS, mVersionCachedAssets);
////        editor.putString(VERSION_CONTENT, mVersionContent);
////        editor.putString(VERSION_GEO_OFFER, mVersionGeoOffer);
////        editor.putString(VERSION_HELP, mVersionHelp);
////        editor.putString(VERSION_CONFIG, mVersionConfig);
////        editor.putString(SHARER_DETAIL_FINGERPRINT, mSharerDetailFingerprint);
////        editor.putString(ACCOUNT_ID, mAccountID);
////        editor.putLong(ACCOUNT_LAST_UPDATED, mAccountLastUpdatedDate);
////        editor.putLong(SHARER_DETAIL_LAST_UPDATED, mSharerDetailLastUpdatedDate);
////        editor.putLong(CONFIG_LAST_UPDATED, mConfigLastUpdatedDate);
////
////        editor.putString(APP_LAST_OPEN_VERSION, mLastOpenAppVersion);
////        editor.putString(SERVICE_LAST_ASSETS_UPDATE, mLastCachedAssetsUpdate);
////        editor.putBoolean(APP_RATING_SHOW, mAppRatingShow);
////        editor.putLong(APP_RATING_TIME, mAppRatingTime);
////        editor.putLong(APP_APP_OPENS, mAppOpens);
////        editor.putLong(APP_LAST_RATING_INCREMENT_TIME, mLastAppRatingIncrementTime);
////        editor.putLong(APP_RATING_COUNT, mRatingCount);
////        editor.putInt(USER_LAST_PROMOTION_ID, mLastViewedPromotionId);
////        editor.putInt(USER_LAST_GEO_OFFER_ID, mLastViewedGeoOfferId);
////        editor.putString(USER_LAST_GEO_OFFER_TIME, (mLastGeoOfferTime != null) ? new Gson().toJson(mLastGeoOfferTime) : null);
////        editor.putBoolean(USER_TERMS_ACCEPTED, mTermsAndConditionsAccepted);
////        editor.putBoolean(USER_SPLASH_TUTORIAL_COMPLETED, mSplashTutorialCompleted);
////        editor.putStringSet(APP_PENDING_DATA_LIMIT_CHANGES, mPendingDataLimitChanges);
////        editor.putStringSet(APP_PENDING_DATA_PURCHASES, mPendingDataPurchases);
////        editor.putStringSet(APP_PENDING_EXTRAS, mPendingExtras);
////        editor.putBoolean(APP_MIGRATED, mMigrated);
////        editor.putLong(APP_UPGRADE_REMINDER_HIDE_DATE, mUpgradeReminderHideDate);
////        editor.putString(USER_USERNAME, mUsername);
////        editor.putString(APP_DISMISSED_NOTIFICATIONS, (mDismissedNotifications != null) ? new Gson().toJson
////                (mDismissedNotifications) : null);
////        editor.putBoolean(APP_PIN_TUTORIAL_LATER, mPinTutorialLater);
////        editor.putString(APP_PERIOD_ZERO_INVOICE_ID, mPeriodZeroInvoiceId);
////        editor.putString(APP_BILLSUMMARY_NOTIFICATIONS, (mBillSummaryNotifications != null) ? new Gson().toJson
////                (mBillSummaryNotifications) : null);
////        editor.putBoolean(APP_PUSH_NOTIFICATIONS_ENABLED, mPushNotificationsEnabled);
////        editor.putBoolean(APP_FIRST_RUN, mIsAppFirstRun);
////        editor.putBoolean(APP_REQEST_LIST_PERMISSIONS, mAreListOfPermissionsRequested);
////        editor.putBoolean(NETPERFORM_SERVICE_OPTED_IN, mNetPerformServiceOptedIn);
////        editor.putBoolean(IS_MSISDN_CHANGED, mIsMsisdnChanged);
////    }
////
////    public boolean hasIMSIChanged() {
////        if (isConfigurableAndMsisdnHardcoded() && !TextUtils.isEmpty(mHardcodedIMSI)) {
////            return false;
////        }
////
////        String currentIMSI = TelephonyUtil.getIMSIFromDevice(getContext());
////
////        return !TextUtils.isEmpty(currentIMSI) && !TextUtils.isEmpty(getIMSI())
////                && !TextUtils.equals(currentIMSI, getIMSI());
////    }
////
////    public boolean isConfigurableAndMsisdnHardcoded() {
////        // Always disabled if not a configurable build
////        return Global.isConfigurableBuild() && mHardcoded;
////    }
////
////    public VodafoneAppConfig setHardcoded(boolean hardcoded) {
////        mHardcoded = hardcoded;
////        return this;
//    }
//
//    public boolean shouldShowStringKeys() {
//        // Always disabled if not a configurable build
//        if (!Global.isConfigurableBuild()) {
//            return false;
//        }
//        return mShowStringKeys;
//    }
//
//    public VodafoneAppConfig setShowStringKeys(boolean showStringKeys) {
//        mShowStringKeys = showStringKeys;
//        return this;
//    }
//
//    public boolean shouldProtectCustomerData() {
//        return mProtectCustomerData;
//    }
//
//    public VodafoneAppConfig setProtectCustomerData(boolean protectCustomerData) {
//        mProtectCustomerData = protectCustomerData;
//        return this;
//    }
//
//    public VodafoneAppConfig setOnlineContextUrl(String onlineContextUrl) {
//        mOnlineContextUrl = onlineContextUrl;
//        return this;
//    }
//
//    public String getOnlineContextUrl() {
//        return mOnlineContextUrl;
//    }
//
//    public VodafoneAppConfig setKillSwitchDate(String killSwitchDate) {
//        mKillSwitchDate = killSwitchDate;
//        return this;
//    }
//
//    public String getKillSwitchDate() {
//        return mKillSwitchDate;
//    }
//
//    public VodafoneAppConfig setKillSwitchActivated(boolean killSwitchActivated) {
//        mKillSwitchActivated = killSwitchActivated;
//        return this;
//    }
//
//    public boolean isKillSwitchActivated() {
//        return mKillSwitchActivated;
//    }
//
//    public VodafoneAppConfig setHardcodedDeviceOs(String os) {
//        mHardcodedOS = os;
//        return this;
//    }
//
//    public String getDeviceOs() {
//        if (isConfigurableAndMsisdnHardcoded() && StringUtil.isNotNullOrBlank(mHardcodedOS)) {
//            return mHardcodedOS;
//        }
//        return Build.VERSION.RELEASE;
//    }
//
//    public VodafoneAppConfig setHardcodedManufacturer(String manufacturer) {
//        mHardcodedManufacturer = manufacturer;
//        return this;
//    }
//
//    public String getDeviceManufacturer() {
//        if (isConfigurableAndMsisdnHardcoded() && StringUtil.isNotNullOrBlank(mHardcodedManufacturer)) {
//            return mHardcodedManufacturer;
//        }
//        return Build.MANUFACTURER;
//    }
//
//    public VodafoneAppConfig setHardcodedDeviceModel(String model) {
//        mHardcodedModel = model;
//        return this;
//    }
//
//
//    public String getDeviceModel() {
//        if (isConfigurableAndMsisdnHardcoded() && StringUtil.isNotNullOrBlank(mHardcodedModel)) {
//            return mHardcodedModel;
//        }
//        return Build.MODEL;
//    }
//
//    public VodafoneAppConfig setHardcodedMSISDN(String msidsn) {
//        mHardcodedMSISDN = msidsn;
//        return this;
//    }
//
//    public VodafoneAppConfig setCachedMSISDN(String msidsn) {
//        mCachedMSISDN = msidsn;
//        return this;
//    }
//
//    public String getMSISDN() {
//        NetworkGroup currentNetworkGroup = getCurrentNetworkGroup();
//        if (currentNetworkGroup == NetworkGroup.STAGING || currentNetworkGroup == NetworkGroup.RETAIL) {
//            if (isConfigurableAndMsisdnHardcoded()) {
//                return mHardcodedMSISDN;
//            }
//        }
//
//        return mCachedMSISDN;
//
//    }
//
//
//    public VodafoneAppConfig setHardcodedIMSI(String imsi) {
//        mHardcodedIMSI = imsi;
//        return this;
//    }
//
//    public String getIMSI() {
//        NetworkGroup currentNetworkGroup = getCurrentNetworkGroup();
//        if (currentNetworkGroup == NetworkGroup.STAGING || currentNetworkGroup == NetworkGroup.RETAIL) {
//            if (isConfigurableAndMsisdnHardcoded()) {
//                return mHardcodedIMSI;
//            }
//        }
//        return mCachedIMSI;
//    }
//
//    public VodafoneAppConfig setCachedIMSI(String imsi) {
//        mCachedIMSI = imsi;
//        return this;
//    }
//
//    public VodafoneAppConfig setHardcodedMCC(String mcc) {
//        mHardcodedMCC = mcc;
//        return this;
//    }
//
//    public String getMCC() {
//        if (isConfigurableAndMsisdnHardcoded() && StringUtil.isNotNullOrBlank(mHardcodedMCC)) {
//            return mHardcodedMCC;
//        }
//        return mCachedMCC;
//    }
//
//    public VodafoneAppConfig setHardcodedMNC(String mnc) {
//        mHardcodedMNC = mnc;
//        return this;
//    }
//
//    public String getMNC() {
//        if (isConfigurableAndMsisdnHardcoded() && StringUtil.isNotNullOrBlank(mHardcodedMNC)) {
//            return mHardcodedMNC;
//        }
//        return mCachedMNC;
//    }
//
//    public String getEncryptedMSISDN() {
//        return mEncryptedMSISDN;
//    }
//
//    public VodafoneAppConfig setEncryptedMSISDN(String encryptedMSISDN) {
//        mEncryptedMSISDN = encryptedMSISDN;
//        return this;
//    }
//
//    public String getSecurityToken() {
//        return mSecurityToken;
//    }
//
//    public VodafoneAppConfig setSecurityToken(String securityToken) {
//        mSecurityToken = securityToken;
//        return this;
//    }
//
//    public VodafoneAppConfig setSecurityTokenLastUpdatedDate(long securityTokenLastUpdatedDate) {
//        mSecurityTokenLastUpdatedDate = securityTokenLastUpdatedDate;
//        return this;
//    }
//
//    public long getSecurityTokenLastUpdatedDate() {
//        return mSecurityTokenLastUpdatedDate;
//    }
//
//    public long getTokenRefreshDate() {
//        return mTokenRefreshDate;
//    }
//
//    public VodafoneAppConfig setTokenRefreshDate(long tokenRefreshDate) {
//        mTokenRefreshDate = tokenRefreshDate;
//        return this;
//    }
//
//    public long getTokenExpiryDate() {
//        return mTokenExpiryDate;
//    }
//
//    public VodafoneAppConfig setTokenExpiryDate(long tokenExpiryDate) {
//        mTokenExpiryDate = tokenExpiryDate;
//        return this;
//    }
//
//    public VodafoneAppConfig setAccountID(String accountID) {
//        mAccountID = accountID;
//        return this;
//    }
//
//    public String getAccountID() {
//        return mAccountID;
//    }
//
//    public long getAccountLastUpdatedDate() {
//        return mAccountLastUpdatedDate;
//    }
//
//    public VodafoneAppConfig setAccountLastUpdatedDate(long accountLastUpdatedDate) {
//        mAccountLastUpdatedDate = accountLastUpdatedDate;
//        return this;
//    }
//
//    public long getSharerDetailLastUpdatedDate() {
//        return mSharerDetailLastUpdatedDate;
//    }
//
//    public VodafoneAppConfig setSharerDetailLastUpdateDate(long sharerDetailLastUpdateDate) {
//        mSharerDetailLastUpdatedDate = sharerDetailLastUpdateDate;
//        return this;
//    }
//
//    public long getConfigLastUpdatedDate() {
//        return mConfigLastUpdatedDate;
//    }
//
//    public VodafoneAppConfig setConfigLastUpdatedDate(long configLastUpdatedDate) {
//        mConfigLastUpdatedDate = configLastUpdatedDate;
//        return this;
//    }
//
//    public String getVersionApp() {
//        return mVersionApp;
//    }
//
//    public VodafoneAppConfig setVersionApp(String versionApp) {
//        mVersionApp = versionApp;
//        return this;
//    }
//
//    public String getVersionCachedAssets() {
//        return mVersionCachedAssets;
//    }
//
//    public VodafoneAppConfig setVersionCachedAssets(String versionCachedAssets) {
//        mVersionCachedAssets = versionCachedAssets;
//        return this;
//    }
//
//    public String getVersionContent() {
//        return mVersionContent;
//    }
//
//    public VodafoneAppConfig setVersionContent(String versionContent) {
//        mVersionContent = versionContent;
//        return this;
//    }
//
//    public String getVersionGeoOffer() {
//        return mVersionGeoOffer;
//    }
//
//    public VodafoneAppConfig setVersionGeoOffer(String versionGeoOffer) {
//        mVersionGeoOffer = versionGeoOffer;
//        return this;
//    }
//
//    public String getVersionHelp() {
//        return mVersionHelp;
//    }
//
//    public VodafoneAppConfig setVersionConfig(String versionConfig) {
//        mVersionConfig = versionConfig;
//        return this;
//    }
//
//    public String getVersionConfig() {
//        return mVersionConfig;
//    }
//
//    public VodafoneAppConfig setVersionHelp(String versionHelp) {
//        mVersionHelp = versionHelp;
//        return this;
//    }
//
//    public long getMaxSharerDataLock() {
//        return mMaxSharerDataLock;
//    }
//
//    public VodafoneAppConfig setMaxSharerDataLock(long maxSharerDataLock) {
//        mMaxSharerDataLock = maxSharerDataLock;
//        return this;
//    }
//
//    public boolean isPreemptiveSharerDataFetch() {
//        return mPreemptiveSharerDataFetch;
//    }
//
//    public VodafoneAppConfig setPreemptiveSharerDataFetch(boolean preemptiveSharerDataFetch) {
//        mPreemptiveSharerDataFetch = preemptiveSharerDataFetch;
//        return this;
//    }
//
//    public boolean isPinSetup() {
//        return mPinSetup;
//    }
//
//    public VodafoneAppConfig setPinSetup(boolean pinSetup) {
//        mPinSetup = pinSetup;
//        return this;
//    }
//
//    /**
//     * Return the base API url for the selected host
//     */
//    public String getCurrentNetworkBaseUrl() {
//        NetworkHost host = getNetworkHost();
//        return (host != null) ? host.getBaseUrl() : null;
//    }
//
//    public VodafoneAppConfig setCurrentNetworkHost(NetworkHost host) {
//        mCurrentHostUrl = host.getBaseUrl();
//        return this;
//    }
//
//    /**
//     * Return the network group url for the selected host
//     */
//    public NetworkGroup getCurrentNetworkGroup() {
//        NetworkHost host = getNetworkHost();
//        return (host != null) ? host.getGroup() : null;
//    }
//
//    public String getCurrentHostUrl() {
//        return mCurrentHostUrl;
//    }
//
//    public VodafoneAppConfig setSharerDataLockFingerprint(String sharerDetailFingerprint) {
//        mSharerDetailFingerprint = sharerDetailFingerprint;
//        return this;
//    }
//
//    public String getSharerDataLockFingerprint() {
//        return mSharerDetailFingerprint;
//    }
//
//    public boolean isHostStaging() {
//        return getCurrentNetworkGroup() == NetworkGroup.STAGING;
//    }
//
//    public boolean isHostRetail() {
//        return getCurrentNetworkGroup() == NetworkGroup.RETAIL;
//    }
//
//    public boolean isHostProduction() {
//        return getCurrentNetworkGroup() == NetworkGroup.PRODUCTION;
//    }
//
//    public String getLastOpenAppVersion() {
//        return mLastOpenAppVersion;
//    }
//
//    public VodafoneAppConfig setLastOpenAppVersion(String lastOpenAppVersion) {
//        mLastOpenAppVersion = lastOpenAppVersion;
//        return this;
//    }
//
//    public boolean shouldShowAppRating() {
//        return mAppRatingShow;
//    }
//
//    public VodafoneAppConfig setShouldShowAppRating(boolean shouldShowAppRating) {
//        mAppRatingShow = shouldShowAppRating;
//        return this;
//    }
//
//    public VodafoneAppConfig incrementAppOpens() {
//        mAppOpens++;
//        return this;
//    }
//
//    public long getAppOpens() {
//        return mAppOpens;
//    }
//
//    public VodafoneAppConfig setAppOpens(long appOpens) {
//        mAppOpens = appOpens;
//        return this;
//    }
//
//    public long getAppRatingTime() {
//        return mAppRatingTime;
//    }
//
//    public VodafoneAppConfig setAppRatingTime(long appRatingTime) {
//        mAppRatingTime = appRatingTime;
//        return this;
//    }
//
//    public String getLastCachedAssetsUpdate() {
//        return mLastCachedAssetsUpdate;
//    }
//
//    public VodafoneAppConfig setLastCachedAssetsUpdate(String lastCachedAssetsUpdate) {
//        mLastCachedAssetsUpdate = lastCachedAssetsUpdate;
//        return this;
//    }
//
//    public int getLastViewedPromotionId() {
//        return mLastViewedPromotionId;
//    }
//
//    public VodafoneAppConfig setLastViewedPromotionId(int lastViewedPromotionId) {
//        mLastViewedPromotionId = lastViewedPromotionId;
//        return this;
//    }
//
//    public int getLastViewedGeoOfferId() {
//        return mLastViewedGeoOfferId;
//    }
//
//    public VodafoneAppConfig setLastViewedGeoOfferId(int lastViewedGeoOfferId) {
//        mLastViewedGeoOfferId = lastViewedGeoOfferId;
//        return this;
//    }
//
//    public long getLastGeoOfferTime(String mcc) {
//        if (mLastGeoOfferTime != null) {
//            Long cachedValue = mLastGeoOfferTime.get(mcc);
//            return (cachedValue != null) ? cachedValue : 0;
//        }
//        return 0;
//    }
//
//    public VodafoneAppConfig setLastGeoOfferTime(String mcc, long lastGeoOfferTime) {
//        if (mLastGeoOfferTime == null) {
//            mLastGeoOfferTime = new HashMap<>();
//        }
//        mLastGeoOfferTime.put(mcc, lastGeoOfferTime);
//        return this;
//    }
//
//    public boolean haveTermsBeenAccepted() {
//        return mTermsAndConditionsAccepted;
//    }
//
//    public VodafoneAppConfig setTermsAndConditionsAccepted(boolean termsAndConditionsAccepted) {
//        mTermsAndConditionsAccepted = termsAndConditionsAccepted;
//        return this;
//    }
//
//    public boolean hasTutorialBeenViewed() {
//        return mSplashTutorialCompleted;
//    }
//
//    public VodafoneAppConfig setSplashTutorialCompleted(boolean splashTutorialCompleted) {
//        mSplashTutorialCompleted = splashTutorialCompleted;
//        return this;
//    }
//
//    public long getRatingCount() {
//        return mRatingCount;
//    }
//
//    public VodafoneAppConfig setRatingCount(long ratingCount) {
//        mRatingCount = ratingCount;
//        return this;
//    }
//
//    public long getLastAppRatingIncrementTime() {
//        return mLastAppRatingIncrementTime;
//    }
//
//    public VodafoneAppConfig setLastAppRatingIncrementTime(long lastAppRatingIncrementTime) {
//        mLastAppRatingIncrementTime = lastAppRatingIncrementTime;
//        return this;
//    }
//
//    public VodafoneAppConfig addPendingLimitChange(String ctn, long dateInMillis) {
//        for (Iterator<String> iterator = mPendingDataLimitChanges.iterator(); iterator.hasNext(); ) {
//            String string = iterator.next();
//            if (string.contains(ctn)) {
//                iterator.remove();
//            }
//        }
//
//        mPendingDataLimitChanges.add(String.format("%s%d", ctn, dateInMillis));
//        return this;
//    }
//
//    public long getPendingLimitChangeDate(String ctn) {
//        for (String s : mPendingDataLimitChanges) {
//            if (s.contains(ctn)) {
//                String dateString = s.substring(ctn.length());
//                return Long.parseLong(dateString);
//            }
//        }
//
//        return 0;
//    }
//
//    public VodafoneAppConfig addPendingDataPurchase(String cta, long dateInMillis) {
//        mPendingDataPurchases.add(String.format("%s%d", cta, dateInMillis));
//        return this;
//    }
//
//    public long getPendingDataPurchase(String cta) {
//        for (String s : mPendingDataPurchases) {
//            if (s.contains(cta)) {
//                String dateString = s.substring(cta.length());
//                return Long.parseLong(dateString);
//            }
//        }
//
//        return 0;
//    }
//
//    public Set<String> getPendingExtras() {
//        return mPendingExtras;
//    }
//
//    public boolean hasPendingExtra(String extraRef) {
//        for (String s : mPendingExtras) {
//            if (s.contains(extraRef)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public VodafoneAppConfig addPendingExtra(String extraRef, long dateInMillis, String oldState) {
//        mPendingExtras.add(extraRef + ":" + dateInMillis + ":" + oldState);
//        return this;
//    }
//
//    public VodafoneAppConfig removePendingExtra(String extraRef) {
//        String extraToRemove = null;
//
//        for (String pendingExtra : mPendingExtras) {
//            if (pendingExtra.contains(extraRef)) {
//                extraToRemove = pendingExtra;
//                break;
//            }
//        }
//
//        mPendingExtras.remove(extraToRemove);
//        return this;
//    }
//
//    public long getPendingExtraDate(String extraRef) {
//        for (String s : mPendingExtras) {
//            if (s.contains(extraRef)) {
//                String[] extraInfo = s.split(":");
//                return Long.parseLong(extraInfo[1]);
//            }
//        }
//
//        return 0;
//    }
//
//    public String getPendingExtraOldState(String extraRef) {
//        for (String s : mPendingExtras) {
//            if (s.contains(extraRef)) {
//                String[] extraInfo = s.split(":");
//                return extraInfo[2];
//            }
//        }
//
//        return null;
//    }
//
//    public boolean isMigrated() {
//        return mMigrated;
//    }
//
//    public VodafoneAppConfig setMigrated(boolean migrated) {
//        mMigrated = migrated;
//        return this;
//    }
//
//    public long getUpgradeReminderHideDate() {
//        return mUpgradeReminderHideDate;
//    }
//
//    public VodafoneAppConfig setUpgradeReminderHideDate(long upgradeReminderHideDate) {
//        mUpgradeReminderHideDate = upgradeReminderHideDate;
//        return this;
//    }
//
//    public String getUsername() {
//        return mUsername;
//    }
//
//    public VodafoneAppConfig setUsername(String username) {
//        mUsername = username;
//        return this;
//    }
//
//    public void saveBillSummaryNotificationsToJson(String currentSubscriberRef, List<Notification> billSummaryNotifications) {
//        String billSummaryNotificationsJson = new Gson().toJson(billSummaryNotifications);
//
//        if (mBillSummaryNotifications == null) {
//            mBillSummaryNotifications = new HashMap<>();
//        }
//
//        if (currentSubscriberRef != null) {
//            mBillSummaryNotifications.put(currentSubscriberRef, billSummaryNotificationsJson);
//            VodafoneAppConfig.get().saveConfiguration();
//        }
//    }
//
//    public List<Notification> getBillSummaryNotificationsJsonForRef(String msisdn) {
//        if (mBillSummaryNotifications != null && msisdn != null) {
//            String billSummaryNotificationsJson = mBillSummaryNotifications.get(msisdn);
//
//            if (billSummaryNotificationsJson != null) {
//                return new Gson().fromJson(billSummaryNotificationsJson, new TypeToken<ArrayList<Notification>>() {
//                }.getType());
//            }
//        }
//        return null;
//    }
//
//    public HashMap<Integer, Long> getDismissedNotifications(String msisdn) {
//        HashMap<Integer, Long> list = mDismissedNotifications.get(msisdn);
//        if (list != null) {
//            return list;
//        }
//        return new HashMap<>();
//    }
//
//    public VodafoneAppConfig removeDismissedNotificationsForId(String msisdn, Integer key) {
//        HashMap<Integer, Long> list = mDismissedNotifications.get(msisdn);
//        if (list != null) {
//            mDismissedNotifications.get(msisdn).remove(key);
//            VodafoneAppConfig.get().saveConfiguration();
//            VodafoneAppConfig.get().readConfiguration();
//        }
//        return this;
//    }
//
//    public VodafoneAppConfig addDismissedNotification(String msisdn, Integer key, Long value) {
//        HashMap<Integer, Long> list = mDismissedNotifications.get(msisdn);
//        if (list != null) {
//            mDismissedNotifications.get(msisdn).put(key, value);
//            VodafoneAppConfig.get().saveConfiguration();
//        } else {
//            HashMap<Integer, Long> hashmap = new HashMap<>();
//            hashmap.put(key, value);
//            mDismissedNotifications.put(msisdn, hashmap);
//            VodafoneAppConfig.get().saveConfiguration();
//        }
//        return this;
//    }
//
//    public VodafoneAppConfig clearDismissedNotifications() {
//        mDismissedNotifications.clear();
//        VodafoneAppConfig.get().saveConfiguration();
//        return this;
//    }
//
//    public VodafoneAppConfig setPinLater(boolean setPinLater) {
//        mPinTutorialLater = setPinLater;
//        return this;
//    }
//
//    public boolean isSetPinLater() {
//        return mPinTutorialLater;
//    }
//
//    public String getPeriodZeroInvoiceId() {
//        return mPeriodZeroInvoiceId;
//    }
//
//    public VodafoneAppConfig setPeriodZeroInvoiceId(String periodZeroInvoiceId) {
//        mPeriodZeroInvoiceId = periodZeroInvoiceId;
//        return this;
//    }
//
//    public void setCurrentHostUrl(String currentHostUrl) {
//        mCurrentHostUrl = currentHostUrl;
//    }
//
//    public VodafoneAppConfig setPushNotificationsEnabled(boolean enabled) {
//        mPushNotificationsEnabled = enabled;
//        return this;
//    }
//
//    public boolean getPushNotificationsEnabledFlag() {
//        return mPushNotificationsEnabled;
//    }
//
//    public VodafoneAppConfig setAppFirstRun(boolean appFirstRun) {
//        mIsAppFirstRun = appFirstRun;
//        return this;
//    }
//
//    public VodafoneAppConfig setNetPerformServiceOptedIn(boolean optedIn) {
//        mNetPerformServiceOptedIn = optedIn;
//        return this;
//    }
//
//    public boolean isNetPerformServiceOptedIn() {
//        return mNetPerformServiceOptedIn;
//    }
//
//    public boolean isAppFirstRun() {
//        return mIsAppFirstRun;
//    }
//
//    public VodafoneAppConfig setRequestedPermissions(boolean areListOfPermissionsRequested) {
//        mAreListOfPermissionsRequested = areListOfPermissionsRequested;
//        return this;
//    }
//
//    public boolean areListOfPermissionsRequested() {
//        return mAreListOfPermissionsRequested;
//    }
//
//    public boolean isMsisdnChanged() {
//        return mIsMsisdnChanged;
//    }
//
//    public VodafoneAppConfig setIsMsisdnChanged(boolean isMsisdnChanged) {
//        mIsMsisdnChanged = isMsisdnChanged;
//        return this;
//    }
}
