package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
//
//import com.android.volley.VolleyError;
//import com.myvodafoneapp.helper.SubscriberViewHelper;
//import com.myvodafoneapp.model.SubscriberManager;
//import com.myvodafoneapp.model.configinfo.Journey;
//import com.myvodafoneapp.model.subscriptioninfo.postpay.AddOns;
//import com.myvodafoneapp.model.subscriptioninfo.postpay.PostPayAccount;
//import com.myvodafoneapp.model.subscriptionlist.Subscription;
//import com.myvodafoneapp.navigation.JourneyAppLinks;
//import com.myvodafoneapp.navigation.JourneyLookupService;
//import com.myvodafoneapp.network.request.listeners.VodafoneRequestListener;
//import com.myvodafoneapp.service.VodafoneBus;
//import com.myvodafoneapp.tracking.Tracking;
//import com.myvodafoneapp.tracking.VodafoneTealiumTracking;
//import com.myvodafoneapp.ui.activity.core.MyVodafoneActivity;
//import com.myvodafoneapp.ui.fragment.core.MyVodafoneFragment;
//import com.myvodafoneapp.ui.widget.VodafoneTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

//import static com.myvodafoneapp.logging.LoggerModule.logger;

/**
 * Created by John McDonnell on 29/09/2014
 */
public class DashboardFragment extends Fragment implements VodafoneCircularView.Listener {
//
//    @InjectView(R.id.data_min_txt_container)
//    VodafoneCircularView mCircularView;
//    @InjectView(R.id.main_usage_circle)
//    VodafoneUsageCircle mMainUsageCircle;

//    @InjectView(R.id.allowance_container) RelativeLayout mAllowanceContainer;
//    @InjectView(R.id.allowance_reset_text) VodafoneTextView mAllowanceResetText;
//    @InjectView(R.id.redreshed_at_text) VodafoneTextView mRefreshedAtText;
//    @InjectView(R.id.imageView) ImageView mRefreshedAtImg;
//    @InjectView(R.id.subscriber_container) View mSubscriberContainer;
//    @InjectView(R.id.subscription_switching_view) VodafoneCircularImageView mSubscriberImageView;
//    @InjectView(R.id.subscriber_coins_image_view) ImageView mSubscriberCoinsImageView;
//    @InjectView(R.id.subscriber_msisdn) VodafoneTextView mSubscriberMsisdn;
//    @InjectView(R.id.subscriber_name) VodafoneTextView mSubscriberName;
//    @InjectView(R.id.scroll_holder) ScrollView mScrollView;

    private boolean mHasAnimated;
    private VodafoneCircularImageView mLastIconSelected;
    private int mAllowanceSectionIndex;
    private boolean mIsCtr12CustomerWithAddOnJourney;
    private boolean mIsRedSharerCustomer;
    private VodafoneCircularView mCircularView;
    private VodafoneUsageView mMainUsageCircle;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    public interface OnDashboardFragmentListener {
        void onSubscriberIconClicked(int[] subscriberContainerLocation);

        void onDataPurchaseClicked(boolean showMore);

        void onAllowanceCircleClicked();

        void onDataBundleAddOnsPlusIconClicked();
    }

    private OnDashboardFragmentListener mListener;

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        setupView();
//    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(!mHasAnimated);
        mHasAnimated = true;
    }

    @Override
    public void onPause() {
        super.onPause();
//        VodafoneBus.get().unregisterSubscriptionInfoListener(mSubscriptionInfoListener);
//        VodafoneBus.get().unregisterSubscriberSubscriptionInfoListener(mSubscriptionInfoListener);
    }

    @Override
    public void onStop() {
        super.onStop();
//        removeScrollListener(mScrollView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAllowanceUsage(new VodafoneCircularImageView(getContext()), AllowanceValue.Type.GROUP_DATA);
        mCircularView.setListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHasAnimated = false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnDashboardFragmentListener) {
            mListener = (OnDashboardFragmentListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override protected View getBackgroundView() {
//        return mScrollView;
//    }

//    @Optional
//        @OnClick(R.id.subscription_switching_view) void onSubscriptionSwitchingIconClicked() {
//        if (getConfigInfo().isSubscriptionSwitchingEnabled() && getConfigInfo().hasPinJourney()
//                && getRootSubscriberSubscriptionInfo() != null && getRootSubscriberSubscriptionInfo().isPostpayAccount()) {
//
//            logger().log(2, "Dashboard", " subscription switching is enabled");
//            VodafoneTealiumTracking.trackEventWithLoginState("Dashboard", "Subscription switching icon pressed",
//                    MyVodafoneActivity.userIsLoggedIn());
//
//            int[] subscriptionSwitchingContainerLocation = new int[2];
//            mSubscriberContainer.getLocationInWindow(subscriptionSwitchingContainerLocation);
//
//            if (mListener != null) {
//                mListener.onSubscriberIconClicked(subscriptionSwitchingContainerLocation);
//                if (MyVodafoneActivity.userIsLoggedIn()) {
//                    mSubscriberContainer.setVisibility(View.GONE);
//                }
//            }
//        }
//    }

//    @Override
//    public void onAccountDataLoaded() {
////        super.onAccountDataLoaded();
//        updateUI(false);
//    }

//    public int[] getSubscriberIconLocation() {
//        int[] subscriptionSwitchingContainerLocation = new int[2];
//        if (mSubscriberContainer != null) {
//            mSubscriberContainer.getLocationInWindow(subscriptionSwitchingContainerLocation);
//            if (MyVodafoneActivity.userIsLoggedIn()) {
//                mSubscriberContainer.setVisibility(View.GONE);
//            }
//        }
//
//        return subscriptionSwitchingContainerLocation;
//    }

//    public void hideSubscriptionSwitchingIcon() {
//        if (mSubscriberContainer != null) {
//            mSubscriberContainer.setVisibility(View.GONE);
//        }
//    }

    protected void updateUI(boolean animate) {
//        if (getSubscriptionInfo() == null || !getSubscriptionInfo().hasAllowance()) {
//            return;
//        }

        updateAllowanceViews(animate, false, mAllowanceSectionIndex);

        if (animate) {
            animateView();
        }
//
//        updateSubscriberView();
//
//        updateRefreshAtView();
//        setScrollListener(mScrollView);
    }

    //    public void showSubscriberSwitchingButton() {
//        mSubscriberContainer.setVisibility(View.VISIBLE);
//    }


    //
//    public List<AllowanceValue> getAllowanceList(int sectionIndex) {
//        List<AllowanceValue> allowanceValues = new ArrayList<>();
//        AllowanceValue aV = new AllowanceValue();
//        aV.setAllowanceType(AllowanceValue.Type.GROUP_DATA);
//        aV.setUnlimited(false);
//        aV.setDashboardTitle("99.0");
//        aV.setSubTitleText("GB of group 1.0GB left");
//        aV.setRemainingMb(999.0f);
//        aV.setUsageUsed("9999MB used");
//        aV.setUsageTitle("99.0");
//        aV.setRiderMessage("99999Need more data? Please speak to the bill payer");
//        aV.setUsageSubTitle("999999GB of 1.0GB group data allowance left");
//        aV.setState(AllowanceValue.State.OK);
//        aV.setSubTitleText("9999GB of 1.0GB left");
//        aV.setTitleText("9999UK Group Data");
//        aV.setUsageLeft("999.0");
//        aV.mSectionIndex = 0;
//        aV.setPercentageRemaining(99.0f);
//        aV.setUsedMb(999.0f);
//        aV.setIncludedMb(999.0f);
//        allowanceValues.add(aV);
//
//
//        return allowanceValues;
//    }
    public List<AllowanceValue> getAllowanceList(int sectionIndex) {
        List<AllowanceValue> allowanceValues = new ArrayList<>();
        AllowanceValue aV1 = new AllowanceValue();
        aV1.setAllowanceType(AllowanceValue.Type.GROUP_DATA);
        aV1.setUnlimited(false);
        ArrayList<AllowanceValue.DisplayType> types1 = new ArrayList<>();
        AllowanceValue.DisplayType av1type1 = AllowanceValue.DisplayType.USAGE;
        AllowanceValue.DisplayType av1type2 = AllowanceValue.DisplayType.DASHBOARD;

        types1.add(av1type1);
        types1.add(av1type2);

        aV1.setDisplayTypes(types1);
        aV1.setDashboardTitle("199.0");
        aV1.setSubTitleText("GB of group 1.0GB left");
        aV1.setRemainingMb(999.0f);
        aV1.setUsageUsed("9999MB used");
        aV1.setUsageTitle("99.0");
        aV1.setRiderMessage("99999Need more data? Please speak to the bill payer");
        aV1.setUsageSubTitle("999999GB of 1.0GB group data allowance left");
        aV1.setState(AllowanceValue.State.OK);
        aV1.setSubTitleText("9999GB of 1.0GB left");
        aV1.setTitleText("9999UK Group Data");
        aV1.setUsageLeft("999.0");
        aV1.mSectionIndex = 0;
        aV1.setPercentageRemaining(10.0f);
        aV1.setUsedMb(200.0f);
        aV1.setIncludedMb(100.0f);


//        1 = {AllowanceValue@831577341640} "type DATA; isUnlimited true"
//        mDashboardSubtitle = "MB UK data used"
//        mDashboardTitle = "0"
//        mDisplayTypes = {ArrayList@831577343896}  size = 2
//        mWarningMessage = null
//        mUsageUsed = "MB used"
//        mUsageTitle = "<b>0</b>"
//        mRiderMessage = null
//        mUsageSubTitle = "<b>MB</b> used<br>No limit set"
//        mState = {AllowanceValue$State@831576033736} "OK"
//        mSubTitleText = "minutes"
//        mTitle = null
//        mTitleText = "UK Data"
//        mType = {AllowanceValue$Type@831576743792} "DATA"
//        mUsageLeft = null
//        mUnlimited = true
//        mSectionIndex = 0
//        mRemainingMb = 0.0
//        mPercentageRemaining = 0.0
//        mUsedMb = 0.0
//        mIncludedMb = 0.0


        AllowanceValue aV2 = new AllowanceValue();
        aV2.setAllowanceType(AllowanceValue.Type.VOICE);
        aV2.setUnlimited(true);
        aV2.setDisplayTypes(types1);
        aV2.setDashboardTitle("136.5");
        aV2.setSubTitleText("MB UK data used");
        aV2.setRemainingMb(10.0f);
        aV2.setUsageUsed("MB used");
        aV2.setUsageTitle("<b>0</b>");
        //aV2.setRiderMessage("99999Need more data? Please speak to the bill payer");
        aV2.setUsageSubTitle("<b>MB</b> used<br>No limit set");
        aV2.setState(AllowanceValue.State.OK);
        aV2.setSubTitleText("minutes");
        aV2.setTitleText("UK Data");
//        aV2.setUsageLeft("999.0");
        aV2.mSectionIndex = 0;
        aV2.setPercentageRemaining(20.0f);
        aV2.setUsedMb(70.0f);
        aV2.setIncludedMb(30.0f);

        AllowanceValue aV3 = new AllowanceValue();
        aV3.setAllowanceType(AllowanceValue.Type.DATA);
        aV3.setUnlimited(true);
        aV3.setDisplayTypes(types1);
        aV3.setDashboardTitle("34.6");
        aV3.setSubTitleText("MB UK data used");
        aV3.setRemainingMb(100f);
        aV3.setUsageUsed("MB used");
        aV3.setUsageTitle("<b>0</b>");
        //aV2.setRiderMessage("99999Need more data? Please speak to the bill payer");
        aV3.setUsageSubTitle("<b>MB</b> used<br>No limit set");
        aV3.setState(AllowanceValue.State.OK);
        aV3.setSubTitleText("minutes");
        aV3.setTitleText("UK Data");
//        aV2.setUsageLeft("999.0");
        aV3.mSectionIndex = 0;
        aV3.setPercentageRemaining(70.0f);
        aV3.setUsedMb(50.0f);
        aV3.setIncludedMb(30.0f);


        AllowanceValue aV4 = new AllowanceValue();
        aV4.setAllowanceType(AllowanceValue.Type.SMS);
        aV4.setUnlimited(true);
        aV4.setDisplayTypes(types1);
        aV4.setDashboardTitle("80.1");
        aV4.setSubTitleText("MB UK data used");
        aV4.setRemainingMb(30.0f);
        aV4.setUsageUsed("MB used");
        aV4.setUsageTitle("<b>0</b>");
        //aV2.setRiderMessage("99999Need more data? Please speak to the bill payer");
        aV4.setUsageSubTitle("<b>MB</b> used<br>No limit set");
        aV4.setState(AllowanceValue.State.OK);
        aV4.setSubTitleText("minutes");
        aV4.setTitleText("UK Data");
//        aV2.setUsageLeft("999.0");
        aV4.mSectionIndex = 0;
        aV4.setPercentageRemaining(0.0f);
        aV4.setUsedMb(0.0f);
        aV4.setIncludedMb(0.0f);

        allowanceValues.add(aV1);
        allowanceValues.add(aV2);
        allowanceValues.add(aV3);
        allowanceValues.add(aV4);

//        1 = {AllowanceValue@831577341640} "type DATA; isUnlimited true"
//        mDashboardSubtitle = "MB UK data used"
//        mDashboardTitle = "0"
//        mDisplayTypes = {ArrayList@831577343896}  size = 2
//        mWarningMessage = null
//        mUsageUsed = "MB used"
//        mUsageTitle = "<b>0</b>"
//        mRiderMessage = null
//        mUsageSubTitle = "<b>MB</b> used<br>No limit set"
//        mState = {AllowanceValue$State@831576033736} "OK"
//        mSubTitleText = "minutes"
//        mTitle = null
//        mTitleText = "UK Data"
//        mType = {AllowanceValue$Type@831576743792} "DATA"
//        mUsageLeft = null
//        mUnlimited = true
//        mSectionIndex = 0
//        mRemainingMb = 0.0
//        mPercentageRemaining = 0.0
//        mUsedMb = 0.0
//        mIncludedMb = 0.0
//
//        if (getSubscriptionInfo() != null && getSubscriptionInfo().getAllowance() != null) {
//            List<AllowanceSection> allowanceSections = getAllowanceSections();
//            if (allowanceSections != null && allowanceSections.size() > 1) {
//                allowanceValues = getSubscriptionInfo().getAllowance().getDashboardAllowancesForSectionIndex(sectionIndex);
//            } else {
//                allowanceValues = getSubscriptionInfo().getAllowance().getDashboardValues();
//            }
//        } else {
//            allowanceValues = new ArrayList<>();
//        }

        return allowanceValues;
    }

    protected void updateAllowanceViews(boolean animate, boolean isSectionChanged, int sectionIndex) {
        mAllowanceSectionIndex = sectionIndex;
        if (isSectionChanged) {
            mLastIconSelected = null;
        }

        List<AllowanceValue> allowanceValues = getAllowanceList(sectionIndex);

        if (allowanceValues != null && !allowanceValues.isEmpty()) {
            mMainUsageCircle.setUsage(allowanceValues.get(0), animate);
            mCircularView.initialiseView(allowanceValues);

            if (mLastIconSelected != null) {
                final AllowanceValue.Type allowanceType = mLastIconSelected.getType();
                mCircularView.setLastSelectedIcon(allowanceType);
            } else {
                mCircularView.setLastSelectedIcon(null);
            }

        } else {
            Log.i("Dashboard", " allowance values is empty");
        }

        mMainUsageCircle.setOnClickListener(mOnAllowanceCircleClicked);
    }

    private void animateView() {
//        SubscriptionSwitcherIntroAnimation.animateSubscriptionSwitchingView(mSubscriberContainer);
    }

//    private void setupView() {
//        setupSubscriberView();
//        setupRefreshAtView();
//    }
//
//    private void setupSubscriberView() {
//        setupSubscriberMsisdn();
//
//        if (getConfigInfo() != null && getConfigInfo().isSubscriptionSwitchingEnabled() && getConfigInfo().hasPinJourney()
//                && getRootSubscriberSubscriptionInfo() != null && getRootSubscriberSubscriptionInfo().isPostpayAccount()) {
//
//            mSubscriberCoinsImageView.setVisibility(View.VISIBLE);
//
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
//            mSubscriberImageView.setLayoutParams(params);
//        }
//    }
//
//    private void setupSubscriberMsisdn() {
//        final String currentSubscriberMsisdn = SubscriberViewHelper.getCurrentSubscriberMsisdn(getAppConfig().getMSISDN());
//        mSubscriberMsisdn.setText(currentSubscriberMsisdn);
//    }
//
//    private void updateSubscriberView() {
//        final Subscription currentSubscription = SubscriberManager.getInstance().getCurrentSubscriber();
//        final String subscriberName = SubscriberViewHelper.getSubscriberName(getActivity(), currentSubscription, getAppConfig().getMSISDN(), true);
//        mSubscriberName.setText(subscriberName);
//
//        SubscriberViewHelper.setupCurrentSubscriberProfileImage(getActivity(), mSubscriberImageView);
//    }
//
//    private void setupRefreshAtView() {
//        updateRefreshAtView();
//
//        mAllowanceContainer.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                Subscription subscription = SubscriberManager.getInstance().getCurrentSubscriber();
//                Subscription.Type type = subscription.getType();
//                if (type != null && type != Subscription.Type.FIXED_BROADBAND_SERVICE) {
//                    showLoadingView();
//                    updateSubscriptionInfo();
//                }
//            }
//        });
//    }
//
//    private void updateRefreshAtView() {
//        mRefreshedAtText.setVisibility(View.GONE);
//        mRefreshedAtImg.setVisibility(View.GONE);
//
//        if (getSubscriptionInfo() != null) {
//            String date = getSubscriptionInfo().getAccountInfoDetail().getDetailsCorrectAtDate();
//            String time = getSubscriptionInfo().getAccountInfoDetail().getDetailsCorrectAtTime();
//
//            if (date != null && time != null) {
//                String detailsCorrectAtTime = date + " " + time;
//
//                String refreshedAtDynamicText = DynamicText.textFromId(R.string.dashboard_refreshed_at).toString();
//                String refreshedAtStr = refreshedAtDynamicText.replace("$last_refreshed_at$", detailsCorrectAtTime);
//                mRefreshedAtText.setVisibility(View.VISIBLE);
//                mRefreshedAtImg.setVisibility(View.VISIBLE);
//
//                mRefreshedAtText.setText(refreshedAtStr);
//            }
//        } else {
//            logger().log(1, "Dashboard", " subscriptionInfo is null");
//        }
//    }


    public void onUpdateMainCircle(VodafoneCircularImageView selectedIcon, AllowanceValue.Type allowanceType) {
        setAllowanceUsage(selectedIcon, allowanceType);
//        showPlusButtonForDataSharer(allowanceType);
//        showPlusButtonForCTR12Customer();
    }

    private void setAllowanceUsage(VodafoneCircularImageView icon, AllowanceValue.Type type) {
        final List<AllowanceValue> dashboardValues = getAllowanceList(mAllowanceSectionIndex);
        for (AllowanceValue allowanceValue : dashboardValues) {
            if (allowanceValue.getAllowanceType() != null && allowanceValue.getAllowanceType().equals(type)) {
                mMainUsageCircle.setUsage(allowanceValue, true);
                icon.setImageColorFilter(R.color.circular_color_filter);
                icon.setSelected(true);
            }
        }

        if (!dashboardValues.isEmpty()) {
            Log.i("dashboardValues=", dashboardValues.toString());
        } else {
            Log.i("allowances", "list is empty");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dash_board, container, false);
//        ButterKnife.inject(this, view);


        mCircularView = (VodafoneCircularView) view.findViewById(R.id.data_min_txt_container);
        mMainUsageCircle = (VodafoneUsageCircle) view.findViewById(R.id.main_usage_circle);
        return view;
    }


//    private void updateSubscriptionInfo() {
//        SubscriberManager subscriberManager = SubscriberManager.getInstance();
//        if (subscriberManager.currentSubscriberIsRoot()) {
//
//            VodafoneBus.get().registerSubscriptionInfoListener(mSubscriptionInfoListener);
//            VodafoneBus.get().loadSubscriptionInfo();
//        } else {
//            Subscription subscription = subscriberManager.getCurrentSubscriber();
//
//            VodafoneBus.get().registerSubscriberSubscriptionInfoListener(mSubscriptionInfoListener);
//            VodafoneBus.get().loadSubscriberSubscriptionInfo(subscription.getReference(), subscription.getToken());
//        }
//    }
//
//    private void showPlusButtonForDataSharer(AllowanceValue.Type allowanceType) {
//        if (getSubscriptionInfo() != null) {
//            PostPayAccount postPayAccount = getSubscriptionInfo().getPostpayAccount();
//            if (postPayAccount != null && postPayAccount.hasDataSharer() && postPayAccount.getDataSharer().isLeadSharer()
//                    && postPayAccount.getDataSharer().getDataPurchases() != null) {
//
//                List<DataPurchase> dataPurchases = postPayAccount.getDataSharer().getAvailableDataPurchases();
//                if (dataPurchases.size() >= 1) {
//                    mIsRedSharerCustomer = true;
//
//                    if (isLastSelectedTypeGroupData(allowanceType)) {
//                        showAddButtonView();
//                    }
//                } else {
//                    mIsRedSharerCustomer = false;
//                }
//            }
//        }
//    }

    private boolean isLastSelectedTypeGroupData(AllowanceValue.Type allowanceType) {
        return (AllowanceValue.Type.GROUP_DATA.equals(allowanceType));
    }

//    private void showPlusButtonForCTR12Customer() {
//        JourneyLookupService lookupService = new JourneyLookupService();
//        Journey ctr12Journey = lookupService.nameToJourney(JourneyAppLinks.ctr12PlusAddOn);
//
//        List<AddOns> addOns = getAddOns();
//        if (!addOns.isEmpty() && ctr12Journey != null && !mIsRedSharerCustomer) {
//            mIsCtr12CustomerWithAddOnJourney = true;
//            showAddButtonView();
//        } else {
//            mIsCtr12CustomerWithAddOnJourney = false;
//        }
//    }

    private void showAddButtonView() {
//        AllowanceValue allowanceValue = mMainUsageCircle.getAllowance();
//        if (allowanceValue.getPercentageRemaining() < 10 && !allowanceValue.isUnlimited()) {
//            mCircularView.showAddButton(true);
//        } else {
//            mCircularView.showAddButton(false);
//        }
        mCircularView.showAddButton(false);
    }


    public void onAllowanceIconClicked(VodafoneCircularImageView icon) {
        for (int i = 0; i < mCircularView.getChildViews().size(); i++) {
            View view = mCircularView.getChildViews().get(i);
            view.setSelected(false);
            view.postInvalidate();
        }

        boolean removeAdditionalCircles = true;

        switch (icon.getId()) {
            case R.id.data_container:
                setAllowanceUsage(icon, AllowanceValue.Type.DATA);
                mLastIconSelected = icon;
//                Tracking.trackEvent(Tracking.Event.DashboardUKData);
                break;

            case R.id.minutes_container:
                setAllowanceUsage(icon, AllowanceValue.Type.VOICE);
                mLastIconSelected = icon;
//                Tracking.trackEvent(Tracking.Event.DashboardUKCalls);
                break;

            case R.id.text_container:
                setAllowanceUsage(icon, AllowanceValue.Type.SMS);
                mLastIconSelected = icon;
//                Tracking.trackEvent(Tracking.Event.DashboardUKText);
                break;

            case R.id.data_group_container:
                removeAdditionalCircles = false;
                mLastIconSelected = icon;
//                showPlusButtonForDataSharer(AllowanceValue.Type.GROUP_DATA);
                setAllowanceUsage(icon, AllowanceValue.Type.GROUP_DATA);
                break;

            case R.id.mms_container:
                setAllowanceUsage(icon, AllowanceValue.Type.MMS);
                mLastIconSelected = icon;
                break;

            case R.id.voice_and_sms_container:
                setAllowanceUsage(icon, AllowanceValue.Type.VOICE_AND_SMS);
                mLastIconSelected = icon;
                break;

            case R.id.voiceanytime_container:
                setAllowanceUsage(icon, AllowanceValue.Type.ANYTIME_VOICE);
                mLastIconSelected = icon;
                break;

            case R.id.voicemobile_container:
                setAllowanceUsage(icon, AllowanceValue.Type.MOBILE_VOICE);
                mLastIconSelected = icon;
                break;

            case R.id.voiceint_container:
                setAllowanceUsage(icon, AllowanceValue.Type.INT_VOICE);
                mLastIconSelected = icon;
                break;

            case R.id.voicelandline_container:
                setAllowanceUsage(icon, AllowanceValue.Type.LANDLINE_VOICE);
                mLastIconSelected = icon;
                break;

            case R.id.voiceweekend_container:
                setAllowanceUsage(icon, AllowanceValue.Type.WEEKEND_VOICE);
                mLastIconSelected = icon;
                break;
        }

        if (removeAdditionalCircles) {
            mCircularView.removeAdditionalCircles();
        }

        if (!mIsRedSharerCustomer && mIsCtr12CustomerWithAddOnJourney) {
            showAddButtonView();
        }
    }

    public void onAdditionalButtonClicked(VodafoneCircularImageView icon) {
        int iconId = icon.getId();
        if (iconId == R.id.add_button_container) {
            if (mIsRedSharerCustomer) {
                //showDataPurchasesCircles();
            } else if (mIsCtr12CustomerWithAddOnJourney) {
                // onAddOnsPlusIconClicked();
            }
        } else {
            onDataPurchaseCircleClicked(iconId);
        }
    }

//    private void showDataPurchasesCircles() {
//        if (getSubscriptionInfo() != null) {
//            PostPayAccount postpayAccount = getSubscriptionInfo().getPostpayAccount();
//            List<DataPurchase> dataPurchases = postpayAccount.getDataSharer().getAvailableDataPurchases();
//            mCircularView.showAdditionalCircles(dataPurchases);
//            Tracking.trackEvent(Tracking.Event.DashboardUKDataAdd);
//        }
//    }

//    private void onAddOnsPlusIconClicked() {
//        List<AddOns> addonsList = getAddOns();
//        if (!addonsList.isEmpty() && mListener != null) {
//            Tracking.trackEvent(Tracking.Event.BuyMoreData);
//            mListener.onDataBundleAddOnsPlusIconClicked();
//        }
//    }

    private void onDataPurchaseCircleClicked(int iconId) {
        boolean isButtonOneContainer = iconId == R.id.additional_button1_container;
        boolean isButtonTwoContainer = iconId == R.id.additional_button2_container;
        if (mListener != null) {
            mCircularView.areAdditionalButtonsCreated(!(isButtonOneContainer || isButtonTwoContainer));
            mListener.onDataPurchaseClicked(isButtonTwoContainer);
        }
    }

    private View.OnClickListener mOnAllowanceCircleClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onAllowanceCircleClicked();
            }
        }
    };

//    private VodafoneRequestListener<SubscriptionInfo> mSubscriptionInfoListener = new VodafoneRequestListener<SubscriptionInfo>() {
//        @Override public void onErrorResponse(VolleyError volleyError) {
//            super.onErrorResponse(volleyError);
//            logger().log(2, "Dashboard", " subscriptionInfo response failed with message " + volleyError.getMessage());
//            hideLoadingViewWithDelay();
//        }
//
//        @Override public void onSuccess(@NonNull SubscriptionInfo feed) {
//            super.onSuccess(feed);
//            if (feed.isCachedVersion()) {
//                hideLoadingViewWithDelay();
//            } else {
//                hideLoadingView();
//            }
//
//            Subscription currentSubscriber = SubscriberManager.getInstance().getCurrentSubscriber();
//            if (feed.isResponseSuccess() && currentSubscriber != null && !currentSubscriber.isRoot() && currentSubscriber.getSubscriberDataHolder() != null) {
//                currentSubscriber.getSubscriberDataHolder().setSubscriptionInfo(feed);
//            }
//
//            if (getActivity() != null && feed.isResponseSuccess()) {
//                logger().log(2, "Dashboard", " subscriptionInfo response is success");
//                updateUI(true);
//            } else if (!feed.isResponseSuccess()) {
//                logger().log(2, "Dashboard", " subscriptionInfo response is failure");
//            }
//        }
//    };

//    private void hideLoadingViewWithDelay() {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hideLoadingView();
//            }
//        }, 810);
//    }
}
