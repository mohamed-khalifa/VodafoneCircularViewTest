package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.InjectView;

public class VodafoneUsageCircle extends VodafoneUsageView {

    private static final int BACKGROUND_ALPHA = 51;

    @InjectView(R.id.textTop)
    AutoResizeTextView mTextTop;
    @InjectView(R.id.textTitle)
    AutoResizeTextView mTextTitle;
    @InjectView(R.id.textSubTitle)
    AutoResizeTextView mTextSubTitle;

    private int mFontColor;
    private AllowanceValue mAllowanceValue;

    public VodafoneUsageCircle(Context context) {
        super(context);
        init();
    }

    public VodafoneUsageCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setUsage(final AllowanceValue allowanceValue, final boolean animate) {
        super.setUsage(allowanceValue, animate);

        this.mAllowanceValue = allowanceValue;

        setupLayout(allowanceValue);

        if (mFontColor == 0) {
            mTextTop.setTextColor(mStateOkColorPrimary);
            mTextTitle.setTextColor(mStateOkColorPrimary);
            mTextSubTitle.setTextColor(mStateOkColorPrimary);
        } else {
            mTextTop.setTextColor(mFontColor);
            mTextTitle.setTextColor(mFontColor);
            mTextSubTitle.setTextColor(mFontColor);
        }

        fillAllowanceCircle(allowanceValue, animate);

        invalidate();
        requestLayout();
    }

    private void init() {
        mTextTop.setTextSize(getResources().getDimension(R.dimen.text_size_large));
        mTextTop.setMinTextSize(getResources().getDimension(R.dimen.text_size_small));
        mTextSubTitle.setTextSize(getResources().getDimension(R.dimen.text_size_large));
        mTextSubTitle.setMinTextSize(getResources().getDimension(R.dimen.text_size_small));
    }

    protected void setupLayout(AllowanceValue allowanceValue) {
        setUsageData(allowanceValue);
    }

    protected CharSequence getSubTitle(AllowanceValue allowanceValue) {
        if (allowanceValue.isUnlimited()) {
            return DynamicText.textFromString(allowanceValue.getUsageUsed());
        } else {
            return DynamicText.textFromString(allowanceValue.getSubTitleText());
        }
    }

    private void setUsageData(final AllowanceValue allowanceValue) {
        mTextTop.setVisibility(VISIBLE);

        CharSequence subTitle = getSubTitle(allowanceValue);
        if (TextUtils.isEmpty(subTitle)) {
            mTextSubTitle.setVisibility(GONE);
        } else {
            mTextSubTitle.setVisibility(VISIBLE);
            mTextSubTitle.setText(subTitle);
        }

        setupTextTitleSize(allowanceValue);

        mTextTop.setText(DynamicText.textFromString(allowanceValue.getTitleText()));
        mTextTitle.setText(allowanceValue.getDashboardTitle());

        setupBackgroundPaint(allowanceValue);
    }

    private void setupTextTitleSize(AllowanceValue allowanceValue) {
        if (allowanceValue.isUnlimited()) {
            mTextTitle.setTextSize(getResources().getDimension(R.dimen.usage_circle_unlimited_text_size));
            mTextTitle.setMinTextSize(getResources().getDimension(R.dimen.usage_circle_unlimited_min_text_size));
        } else {
            mTextTitle.setTextSize(getResources().getDimension(R.dimen.usage_circle_text_size));
            mTextTitle.setMinTextSize(getResources().getDimension(R.dimen.usage_circle_text_size));
        }
    }

    private void setupBackgroundPaint(AllowanceValue allowanceValue) {
        if (allowanceValue.isUnlimited()) {
            mBackgroundPaint.setColor(getResources().getColor(R.color.white));
            mBackgroundPaint.setAlpha(BACKGROUND_ALPHA);
        }
    }

    private void fillAllowanceCircle(final AllowanceValue allowanceValue, final boolean animate) {
        final float progressToShow = allowanceValue.isUnlimited() ? 1.0f : (1.0f * (allowanceValue
                .getPercentageRemaining() / 100));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (animate) {
                    setFillAmountWithAnimation(progressToShow);
                } else {
                    setFillAmount(progressToShow);
                }
            }
        }, 10);
    }

    /**
     * @param fillPercentage value between 0 and 1
     */
    public void setFillAmount(float fillPercentage) {
        super.setFillAmount(fillPercentage);
    }

    protected int getLayoutResource() {
        return R.layout.layout_vodafone_usage_circle;
    }

    protected void init(AttributeSet attrs) {
        super.init(attrs);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VodafoneUsageCircle);

            try {
                if (typedArray.hasValue(R.styleable.VodafoneUsageCircle_vuc_fontColor)) {
                    mFontColor = typedArray.getInteger(R.styleable.VodafoneUsageCircle_vuc_fontColor, Color.BLACK);
                }
            } finally {
                typedArray.recycle();
            }
        }

        // For debugging in design preview
        if (isInEditMode()) {
            if (mTextTitle != null) {
                mTextTitle.setText("5.5");
            }
            if (mTextSubTitle != null) {
                mTextSubTitle.setText("of 10GB left");
            }
        }

        requestLayout();
        invalidate();
    }

    public TextView getTextTop() {
        return mTextTop;
    }

    public TextView getTextTitle() {
        return mTextTitle;
    }

    public TextView getTextSubTitle() {
        return mTextSubTitle;
    }

    public AllowanceValue getAllowance() {
        return mAllowanceValue;
    }

    public void setTextColor(int textColor) {
        mFontColor = textColor;
        mTextTop.setTextColor(mFontColor);
        mTextTitle.setTextColor(mFontColor);
        mTextSubTitle.setTextColor(mFontColor);
    }


}