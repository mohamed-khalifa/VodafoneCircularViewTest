package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dario Budimir on 18/11/15.
 */

public class VodafoneCircularView extends RelativeLayout {

    public ArrayList<VodafoneCircularImageView> mChildViews;
    private static final int ICON_SIZE = 55;
    private static final int ADD_ICON_SIZE = 60;
    private static final int ADD_CIRCLE_START_DEGREE = -60;
    private static final int CIRCLE_START_DEGREE = 165;
    private static final int DEGREE_BETWEEN_CIRCLES = 30;
    private static final int DEGREE_BETWEEN_ADDITIONAL_CIRCLES = 33;
    private static final int BUTTON_TAG_1 = 11;
    private static final int BUTTON_TAG_2 = 22;
    private static final int DETACHED_SIZE = 15;
    private Listener mListener;
    private Context mContext;
    private Rect mCircleRect;
    private float mStartDeg;
    private float mScale;
    private List<AllowanceValue> mAllowanceValues;
    private boolean doneLoadingViews;
    private VodafoneUsageCircle mVodafoneUsageCircle;
    private List<VodafoneCircularImageView> mAdditionalButtons;
    private boolean mAreAdditionalButtonsCreated;
    private VodafoneCircularImageView mAddCircle;
    private VodafoneCircularImageView mAdditionalButton1;
    private VodafoneCircularImageView mAdditionalButton2;
    private AllowanceValue.Type mLastSelectedAllowanceType;

    public interface Listener {
        void onAllowanceIconClicked(VodafoneCircularImageView icon);

        void onUpdateMainCircle(VodafoneCircularImageView selectedIcon, AllowanceValue.Type allowanceType);

        void onAdditionalButtonClicked(VodafoneCircularImageView icon);
    }

    public VodafoneCircularView(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    public VodafoneCircularView(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.mContext = mContext;
    }

    public VodafoneCircularView(Context mContext, AttributeSet attrs, int defStyleAttr) {
        super(mContext, attrs, defStyleAttr);
        this.mContext = mContext;
    }

    public ArrayList<VodafoneCircularImageView> getChildViews() {
        return mChildViews;
    }

    public void setChildViews(ArrayList<VodafoneCircularImageView> childViews) {
        mChildViews = childViews;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void initialiseView(List<AllowanceValue> allowances) {
        removeAllViews();

        this.mChildViews = new ArrayList<>();
        this.setWillNotDraw(false);

        mAllowanceValues = allowances;
        mCircleRect = new Rect();
        mStartDeg = CIRCLE_START_DEGREE;
        mScale = getResources().getDisplayMetrics().density;
        mAdditionalButtons = new ArrayList<>();
        doneLoadingViews = false;
    }

    public void setLastSelectedIcon(AllowanceValue.Type lastSelectedAllowanceType) {
        mLastSelectedAllowanceType = lastSelectedAllowanceType;
    }

    private void setupChildViews() {
        // function to get VUC from rootView // DON'T DELETE
        getAllChildren(getRootView());

        //setup viewholders: data / min/ text/ group data / voices
        for (AllowanceValue av : mAllowanceValues) {

            VodafoneCircularImageView allowanceView = new VodafoneCircularImageView(mContext);
            AllowanceValue.Type allowanceType = av.getAllowanceType();
            allowanceView.setType(allowanceType);

            switch (allowanceType) {
                case DATA:
                    setupAllowanceCircle(allowanceView, R.id.data_container, R.drawable.data_img, GONE);
                    break;

                case VOICE_AND_SMS:
                    setupAllowanceCircle(allowanceView, R.id.voice_and_sms_container, R.drawable.text_img, GONE);
                    break;

                case VOICE:
                    setupAllowanceCircle(allowanceView, R.id.minutes_container, R.drawable.calls_img, GONE);
                    break;

                case SMS:
                    setupAllowanceCircle(allowanceView, R.id.text_container, R.drawable.text_img, GONE);
                    break;

                case ANYTIME_VOICE:
                    setupAllowanceCircle(allowanceView, R.id.voiceanytime_container, R.drawable.anytime_voice, GONE);
                    break;

                case INT_VOICE:
                    setupAllowanceCircle(allowanceView, R.id.voiceint_container, R.drawable.international_voice, GONE);
                    break;

                case WEEKEND_VOICE:
                    setupAllowanceCircle(allowanceView, R.id.voiceweekend_container, R.drawable.weekend_voice, GONE);
                    break;

                case LANDLINE_VOICE:
                    setupAllowanceCircle(allowanceView, R.id.voicelandline_container, R.drawable.uk_landline_voice, GONE);
                    break;

                case MOBILE_VOICE:
                    setupAllowanceCircle(allowanceView, R.id.voicemobile_container, R.drawable.mobile_voice, GONE);
                    break;

                case MMS:
                    setupAllowanceCircle(allowanceView, R.id.mms_container, R.drawable.text_img, GONE); //// TODO: 11/06/16 icon for mms
                    break;

                case GROUP_DATA:
                    setupAllowanceCircle(allowanceView, R.id.data_group_container, R.drawable.group_data_img, GONE);
                    break;
            }
        }

        if (mChildViews.size() == 1) {
            //for single allowance there is no icon needed
            mStartDeg = -1;
        }
    }

    private void setupAllowanceCircle(final VodafoneCircularImageView allowanceCircle, int circleID, int image, int visibility) {
        allowanceCircle.setId(circleID);
        allowanceCircle.setImageResource(image);
        allowanceCircle.setClickable(true);
        allowanceCircle.setLayoutParams(new RelativeLayout.LayoutParams((int) (ICON_SIZE * mScale), (int) (ICON_SIZE * mScale)));
        allowanceCircle.setVisibility(visibility);
        allowanceCircle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onAllowanceIconClicked(allowanceCircle);
                }
            }
        });
        allowanceCircle.setImageColorFilter(R.color.circular_color_filter);
        mChildViews.add(allowanceCircle);
        addView(allowanceCircle);
    }

    // Add button Handling
    public void showAddButton(boolean detached) {
        mAreAdditionalButtonsCreated = false;

        if (mAddCircle != null) {
            removeView(mAddCircle);
        }
        mAddCircle = new VodafoneCircularImageView(getContext());
        mAddCircle.setId(R.id.add_button_container);
        mAddCircle.setImageResource(R.drawable.balance_plus_img);
        mAddCircle.setLayoutParams(new RelativeLayout.LayoutParams((int) (ADD_ICON_SIZE * mScale), (int) (ADD_ICON_SIZE * mScale)));
        mAddCircle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAdditionalButtonClicked(mAddCircle);
                mAddCircle.bringToFront();
                invalidate();
            }
        });

        int radius = (int) ((((getResources().getDimensionPixelSize(R.dimen.data_min_txt_container_height) / 2) + (ADD_ICON_SIZE * mScale / 2))));

        if (mVodafoneUsageCircle != null) {
            radius = (int) ((((mVodafoneUsageCircle.getWidth() / 2) + (ADD_ICON_SIZE * mScale / 2)))
                    - (mVodafoneUsageCircle.INNER_CIRCLE_STROKE * mScale));
            if (detached) {
                radius = radius + (int) (DETACHED_SIZE * mScale);
            }
        }
        addViewAtDegreeWithRadius(mAddCircle, ADD_CIRCLE_START_DEGREE, radius);

        ViewTreeObserver vto = mAddCircle.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = mAddCircle.getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);
                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mAllowanceValues != null) {
            if (!doneLoadingViews) {
                mCircleRect = new Rect(l, t, r, b);
                setupChildViews();
                //only if is more then 0 allowances add to circular view
                if (mStartDeg > 0) {
                    for (int i = 0; i < mChildViews.size(); i++) {
                        VodafoneCircularImageView currentSelectedView = mChildViews.get(i);
                        modifyLayoutParams(currentSelectedView, (int) (mStartDeg - DEGREE_BETWEEN_CIRCLES * i), 0);

                        if (mLastSelectedAllowanceType != null && mLastSelectedAllowanceType.equals(currentSelectedView.getType())) {
                            setActiveIcon(i);
                            mListener.onUpdateMainCircle(currentSelectedView, currentSelectedView.getType());
                        }
                    }
                }
                doneLoadingViews = true;
            }

            if (mLastSelectedAllowanceType == null) {
                if (mChildViews.size() > 0) {
                    VodafoneCircularImageView currentSelectedView = mChildViews.get(0);
                    mLastSelectedAllowanceType = currentSelectedView.getType();
                    setActiveIcon(0);
                    mListener.onUpdateMainCircle(currentSelectedView, currentSelectedView.getType());
                }
            }
        }
    }

    private void modifyLayoutParams(View child, int degree, int radius) {
        /**
         * Using Android convention. Right X-axis is degree 0, growing clockwise.
         * */
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) child.getLayoutParams();
        degree = degree % 360;
        if (degree < 0) { // Make it positive
            degree += 360;
        }
        int w = lp.width;
        int h = lp.height;
        //assuming that w is match_parent so we are taking height only (minus child w/2 for positioning view)
        int radius1;
        if (radius > 0) {
            radius1 = radius;
        } else {
            radius1 = ((mCircleRect.height() / 2) - child.getWidth() / 2) - w / 2;
        }
        //Calculate center point for child view
        float sx = (float) (radius1 * Math.cos(degree * Math.PI / 180F)) + mCircleRect.centerX();
        float sy = (float) (radius1 * Math.sin(degree * Math.PI / 180F)) + mCircleRect.centerY();
        //margins left/top
        lp.leftMargin = (int) (sx - (w / 2));
        lp.topMargin = (int) (sy - (h / 2));
        child.setVisibility(VISIBLE);
        child.setLayoutParams(lp);
        postInvalidate();
    }

    public void removeAdditionalCircles() {
        if (mAddCircle != null) {
            mAreAdditionalButtonsCreated = false;
            removeView(mAddCircle);
            removeAdditionalButtons();
            invalidate();
        } else {
            if (mAdditionalButtons.size() > 0) {
                mAreAdditionalButtonsCreated = false;
                removeAdditionalButtons();
                invalidate();
            }

        }
    }

    private void removeAdditionalButtons() {
        for (VodafoneCircularImageView vuc : mAdditionalButtons) {
            removeView(vuc);
        }
    }

    public void addViewAtDegree(View v, int degree) {
        addView(v);
        modifyLayoutParams(v, degree, 0);
    }

    public void addViewAtDegreeWithRadius(View v, int degree, int radius) {
        addView(v);
        modifyLayoutParams(v, degree, radius);
    }

    private void setActiveIcon(int position) {
        if (mChildViews != null && !mChildViews.isEmpty()) {
            VodafoneCircularImageView circle = mChildViews.get(position);
            circle.setSelected(true);
            postInvalidate();
        }
    }

    public VodafoneCircularImageView getIcon(int position) {
        if (!mChildViews.isEmpty() && mChildViews.size() > position) {
            return mChildViews.get(position);
        }
        return null;
    }

    private void selectDefaultIcon(int position) {
        VodafoneCircularImageView vodafoneCircularImageView = getIcon(position);
        if (vodafoneCircularImageView != null) {
            mListener.onAllowanceIconClicked(vodafoneCircularImageView);
        }
    }

    private ArrayList<View> getAllChildren(View v) {
        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }
        ArrayList<View> result = new ArrayList<View>();
        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            if (child.getClass().getName().equals(VodafoneUsageCircle.class.getName())) {
                mVodafoneUsageCircle = (VodafoneUsageCircle) child;
                break;
            }
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));
            result.addAll(viewArrayList);
        }
        return result;
    }

    public void areAdditionalButtonsCreated(boolean areAdditionalButtonsCreated) {
        mAreAdditionalButtonsCreated = areAdditionalButtonsCreated;
    }

    public void showAdditionalCircles(List<DataPurchase> dataPurchases) {
        RotateAnimation rotate = createRotateAnimation();

        if (!mAreAdditionalButtonsCreated) {
            createAdditionalButtons(dataPurchases, rotate);
        } else {
            animateAdditionalButtons(rotate);
        }
    }

    private RotateAnimation createRotateAnimation() {
        // rotate addCircle for 45 degree to be as on iOS
        RotateAnimation rotate = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);

        return rotate;
    }

    private void createAdditionalButtons(List<DataPurchase> dataPurchases, RotateAnimation rotate) {
        if (dataPurchases.size() >= 1) {
            setupFirstAdditionalButton(dataPurchases.get(0));
        }
        if (dataPurchases.size() >= 2) {
            setupSecondAdditionalButton(rotate);
        }

        mAreAdditionalButtonsCreated = !mAreAdditionalButtonsCreated;
        //rotate right for 45 linear
        rotate.setInterpolator(new LinearInterpolator());
        if (mAddCircle != null) {
            mAddCircle.setAnimation(rotate);
        }
    }

    private void animateAdditionalButtons(RotateAnimation rotate) {
        animateButton(mAdditionalButton1, true);
        animateButton(mAdditionalButton2, true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeAdditionalButtons();
            }
        }, 500);
        mAreAdditionalButtonsCreated = !mAreAdditionalButtonsCreated;

        // reverse animation to original position
        if (mAddCircle != null) {
            rotate.setInterpolator(new ReverseInterpolator());
            mAddCircle.setAnimation(rotate);
        }
    }

    private void setupFirstAdditionalButton(DataPurchase dataPurchase) {
        TextDrawable drawableImageBtn = getAdditionalButtonDrawable(20, dataPurchase.getData());
        int degree = ADD_CIRCLE_START_DEGREE + DEGREE_BETWEEN_ADDITIONAL_CIRCLES;
        mAdditionalButton1 = createAdditionalButton(R.id.additional_button1_container, drawableImageBtn, BUTTON_TAG_1, degree);
        mAdditionalButton1.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void setupSecondAdditionalButton(RotateAnimation rotateAnimation) {
        TextDrawable drawableImageBtn = getAdditionalButtonDrawable(16, DynamicText.textFromId(R.string.data_sharer_more_button).toString());
        int degree = ADD_CIRCLE_START_DEGREE + (2 * DEGREE_BETWEEN_ADDITIONAL_CIRCLES);
        mAdditionalButton2 = createAdditionalButton(R.id.additional_button2_container, drawableImageBtn, BUTTON_TAG_2, degree);

        //rotate right for 45 linear
        rotateAnimation.setInterpolator(new LinearInterpolator());
        mAddCircle.setAnimation(rotateAnimation);
    }

    private VodafoneCircularImageView createAdditionalButton(int buttonId, TextDrawable drawableImageBtn, int tag, int degree) {

        final VodafoneCircularImageView additionalButton = new VodafoneCircularImageView(getContext());
        additionalButton.setId(buttonId);
        additionalButton.setLayoutParams(new RelativeLayout.LayoutParams((int) (ICON_SIZE * mScale), (int) (ICON_SIZE * mScale)));
        additionalButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAdditionalButtonClicked(additionalButton);
            }
        });
        additionalButton.setHasBorder(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            additionalButton.setBackground(drawableImageBtn);
        } else {
            additionalButton.setBackgroundDrawable(drawableImageBtn);
        }

        additionalButton.setTag(tag);

        int radius = (int) ((mVodafoneUsageCircle.getWidth() / 2) + (ICON_SIZE * mScale) / 1.5);
        addViewAtDegreeWithRadius(additionalButton, degree, radius);

        mAdditionalButtons.add(additionalButton);
        animateButton(additionalButton, false);

        return additionalButton;
    }

    private TextDrawable getAdditionalButtonDrawable(int fontSize, String text) {

        return TextDrawable.builder().beginConfig()
                .width((int) (ICON_SIZE * mScale))
                .height((int) (ICON_SIZE * mScale))
                .textColor(Color.WHITE)
                .useFont(TypefaceHelper.get(getContext()).getTypeface(TypefaceHelper.CustomFont.VODAFONE_BOLD))
                .fontSize((int) (fontSize * mScale))
                .withBorder(0)
                .endConfig().buildRound(text, Color.BLACK);
    }

    private void animateButton(final VodafoneCircularImageView circleButton, boolean reverseAnimation) {
        if (circleButton != null) {
            int radius = (int) ((mVodafoneUsageCircle.getWidth() / 2) + (ICON_SIZE * mScale) / 1.5);
            RectF rect = new RectF(mCircleRect.centerX() - radius, mCircleRect.centerY() - radius, mCircleRect.centerX
                    () + radius, mCircleRect.centerY() + radius);
            rect.offset(0, ICON_SIZE / 3);
            final Path path = new Path();
            if ((int) circleButton.getTag() == BUTTON_TAG_1) {
                path.arcTo(rect, ADD_CIRCLE_START_DEGREE, DEGREE_BETWEEN_ADDITIONAL_CIRCLES);
            } else if ((int) circleButton.getTag() == BUTTON_TAG_2) {
                path.arcTo(rect, ADD_CIRCLE_START_DEGREE, (2 * DEGREE_BETWEEN_ADDITIONAL_CIRCLES));
            }
            ValueAnimator pathButtonAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
            ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
                float[] point = new float[2];

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float val = animation.getAnimatedFraction();
                    PathMeasure pathMeasure = new PathMeasure(path, false);
                    pathMeasure.getPosTan(pathMeasure.getLength() * val, point, null);
                    circleButton.setX(point[0] - (ICON_SIZE * mScale) / 2);
                    circleButton.setY(point[1] - (ICON_SIZE * mScale) / 2);
                }
            };
            pathButtonAnimator.addUpdateListener(listener);
            if (reverseAnimation)
                pathButtonAnimator.setInterpolator(new ReverseInterpolator());
            pathButtonAnimator.setDuration(500).start();
        }
    }

    public class ReverseInterpolator implements Interpolator {
        // USAGE
        //ReverseInterpolator reverseInterpolator = new ReverseInterpolator(new AccelerateInterpolator())
        //myAnimation.setInterpolator(reverseInterpolator);
        private Interpolator interpolator;

        public ReverseInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
        }

        public ReverseInterpolator() {
            this(new LinearInterpolator());
        }

        @Override
        public float getInterpolation(float input) {
            return 1 - interpolator.getInterpolation(input);
        }
    }
}
