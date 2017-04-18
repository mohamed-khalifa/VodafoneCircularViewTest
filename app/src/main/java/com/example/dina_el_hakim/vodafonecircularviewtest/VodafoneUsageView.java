package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;


import static butterknife.ButterKnife.inject;

/**
 * Created by Rocio Ortega on 29/02/16.
 */
public class VodafoneUsageView extends FrameLayout {

    // stroke in px
    protected int INNER_CIRCLE_STROKE = 8;
    protected int OUTER_CIRCLE_STROKE = 10;
    protected int CIRCLE_STROKE = 2;

    private float SCALE = getResources().getDisplayMetrics().density;

    protected int mStateOkColorPrimary;
    protected int mStateNormalColorSecondary;
    private int mOutlinePadding;
    private int mCircleColor;
    private int mInnerStroke = (int) (INNER_CIRCLE_STROKE * SCALE);
    private int mOuterStroke = (int) (OUTER_CIRCLE_STROKE * SCALE);
    private boolean mShowEndDot;
    private boolean mShowStartDot;
    private boolean mShowTriangle;

    private Paint mUsagePaint;
    private Paint mUsageBackPaint;
    private Paint mPaint;
    protected Paint mBackgroundPaint;

    private AnimatableRectF mCircleRect;
    protected int mSweepAngle;

    private Triangle mArrow;
    private float percentageRemaining;

    public VodafoneUsageView(Context context) {
        super(context);
        init(null);
    }

    public VodafoneUsageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void setUsage(final AllowanceValue allowanceValue, final boolean animate) {
        if (allowanceValue == null) {
            setVisibility(GONE);
            return;
        } else {
            // set fill 0 before view displayed so there is no lag/displayed for full circle
            setFillAmount(0);
            setVisibility(VISIBLE);
        }
        mBackgroundPaint.setColor(getResources().getColor(R.color.transparent));

        fillAllowanceCircle(allowanceValue, animate);

        requestLayout();
        invalidate();
    }

    public void setVucFillColor(int color) {
        mStateOkColorPrimary = color;
        initPaints();
        invalidate();
    }


    private void fillAllowanceCircle(final AllowanceValue allowanceValue, final boolean animate) {
        allowanceValue.getPercentageRemaining();
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
        mSweepAngle = (int) (-360 * fillPercentage);
        percentageRemaining = (fillPercentage) * 100;
        postInvalidate();

        requestLayout();
    }

    /**
     * @param fillAmount value between 0 and 1
     *                   The progress circle will animate as it is filling
     */
    public void setFillAmountWithAnimation(float fillAmount) {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, fillAmount);
        animator.setDuration(700);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setFillAmount((float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    protected int getLayoutResource() {
        return R.layout.layout_vodafone_usage_circle;
    }

    protected void init(AttributeSet attrs) {

        inject(this, inflate(getContext(), getLayoutResource(), this));
        Resources res = getResources();

        mStateOkColorPrimary = Color.WHITE;
        //Transparent white color for Paint
        mStateNormalColorSecondary = Color.argb(50, 255, 255, 255);
        mCircleColor = R.color.grey_dark;

        mCircleRect = new AnimatableRectF();
        mOutlinePadding = getResources().getDimensionPixelSize(R.dimen.screen_margin_small);

        mShowStartDot = true;
        mShowEndDot = false;
        mShowTriangle = true;

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VodafoneUsageCircle);

            try {
                setFillAmount(typedArray.getFloat(R.styleable.VodafoneUsageCircle_vuc_fillAmount, 0));
                mStateOkColorPrimary = typedArray.getColor(R.styleable.VodafoneUsageCircle_vuc_fillColor,
                        mStateOkColorPrimary);

                mStateNormalColorSecondary = typedArray.getColor(R.styleable.VodafoneUsageCircle_vuc_backgroundColor,
                        mStateNormalColorSecondary);

                mCircleColor = typedArray.getResourceId(R.styleable.VodafoneUsageCircle_vuc_circleColor, R.color.grey_dark);

                mShowStartDot = typedArray.getBoolean(R.styleable.VodafoneUsageCircle_vuc_showStartDot, true);
                mShowEndDot = typedArray.getBoolean(R.styleable.VodafoneUsageCircle_vuc_showEndDot, false);
                mShowTriangle = typedArray.getBoolean(R.styleable.VodafoneUsageCircle_vuc_showTriangle, true);

                if (typedArray.hasValue(R.styleable.VodafoneUsageCircle_vuc_strokeWidth)) {
                    int innerStroke = typedArray.getDimensionPixelSize(R.styleable.VodafoneUsageCircle_vuc_strokeWidth,
                            mInnerStroke);
                    int outerStroke = (int) (innerStroke + 2 * SCALE);
                    mInnerStroke = innerStroke;
                    mOuterStroke = outerStroke;
                }
                if (typedArray.hasValue(R.styleable.VodafoneUsageCircle_vuc_outerStroke)) {
                    mOuterStroke = typedArray.getDimensionPixelSize(R.styleable.VodafoneUsageCircle_vuc_outerStroke,
                            mOuterStroke);
                }
                if (typedArray.hasValue(R.styleable.VodafoneUsageCircle_vuc_innerStroke)) {
                    mInnerStroke = typedArray.getDimensionPixelSize(R.styleable.VodafoneUsageCircle_vuc_innerStroke,
                            mInnerStroke);
                }
            } finally {
                typedArray.recycle();
            }
        }

        initPaints();

        if (mShowTriangle) {
            showTriangle();
        }

        // For debugging in design preview
        if (isInEditMode()) {
            mSweepAngle = (int) (-360 * Math.random());
        }
        requestLayout();
        invalidate();
    }

    private void showTriangle() {
        int arrowWidth = (int) (INNER_CIRCLE_STROKE * SCALE - CIRCLE_STROKE * SCALE);
        mArrow = new Triangle(getContext());
        addView(mArrow);
        mArrow.setLayoutParams(new LayoutParams(arrowWidth, arrowWidth));
        mArrow.setVisibility(GONE);
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        // added rounded start and end points
        // background circle (white)
        //drawArc explanation http://www.cumulations.com/blogs/5/Understanding-Sweep-angle-in-drawArc-method-of-android

        mCircleRect.set(mOutlinePadding, mOutlinePadding, getWidth() - mOutlinePadding, getHeight() - mOutlinePadding);
        //mBackgroundPaint.setColor(getResources().getColor(R.color.black));
        canvas.drawArc(mCircleRect, 0, 360, false, mBackgroundPaint);

        // Add padding to circle
        float arcPadding = (mUsageBackPaint.getStrokeWidth() / 2) + mOutlinePadding;
        mCircleRect.set(0 + arcPadding, 0 + arcPadding, getWidth() - arcPadding,
                getHeight() - arcPadding);

        // Draw usage fill and back paint
        // background circle (slight faded - background)
        mUsageBackPaint.setStrokeWidth(mInnerStroke);
        canvas.drawArc(mCircleRect, 0, 360, false, mUsageBackPaint);

        // usage circle vivid colour (usage)
        mUsagePaint.setStrokeCap(Paint.Cap.ROUND);
        mUsagePaint.setStrokeWidth(mOuterStroke);
        canvas.drawArc(mCircleRect, -90, mSweepAngle, false, mUsagePaint);

        float radius = mCircleRect.width() / 2;
        // start point

        if (mShowStartDot) {
            float ex = (float) (radius * Math.cos(-90 * Math.PI / 180F)) + mCircleRect.centerX();
            float ey = (float) (radius * Math.sin(-90 * Math.PI / 180F)) + mCircleRect.centerY();

            if (mSweepAngle == 0) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(mStateOkColorPrimary);
                canvas.drawCircle(ex, ey, mOuterStroke / 2, paint);
            }
            canvas.drawCircle(ex, ey, CIRCLE_STROKE * SCALE, mPaint);
        }

        float degree = -90 + mSweepAngle;
        double angleDegree = degree * Math.PI / 180F;

        if (mShowEndDot) {
            // end point
            float sx = (float) (radius * Math.cos(angleDegree)) + mCircleRect.centerX();
            float sy = (float) (radius * Math.sin(angleDegree)) + mCircleRect.centerY();
            canvas.drawCircle(sx, sy, CIRCLE_STROKE * SCALE, mPaint);
        }

        if (mShowTriangle) {
            mArrow.setVisibility((mSweepAngle == 0 || mSweepAngle == 360 || mSweepAngle == -360) ? GONE : VISIBLE);
            int arrowWidth = (int) (INNER_CIRCLE_STROKE * SCALE - CIRCLE_STROKE * SCALE);
            float x = (float) (radius * Math.cos(angleDegree)) + mCircleRect.centerX() - arrowWidth / 2;
            float y = (float) (radius * Math.sin(angleDegree)) + mCircleRect.centerY() - arrowWidth / 2;
            mArrow.setX(x);
            mArrow.setY(y);

//            double arcTangent = -x / Math.sqrt(getWidth() * getWidth() - x * x);
//            double arrowDegree = Math.atan(arcTangent);
//            arrowDegree = arrowDegree / Math.PI * 180f;
//            float correction = 0;
//            if(x > mCircleRect.centerX())   {
//                correction = -90f;
//            } else  {
//                correction = 90f;
//            }
//            mArrow.setRotation((float) arrowDegree + correction);
            float arrowDegree = ((((100 - percentageRemaining) / 100) * 4) + 1) * 90;
            mArrow.setRotation(arrowDegree);
        }
        // Draw other views in Layout
        super.dispatchDraw(canvas);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        int size;
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) {
            size = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), mode);
        } else {
            size = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }

        setMeasuredDimension(size, size);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
            if (hasClickedInsideCircle(event.getX(), event.getY())) {
                return super.onTouchEvent(event);
            }
            return false;
        }
        return super.onTouchEvent(event);
    }

    private void initPaints() {
        mUsagePaint = new Paint();
        mUsagePaint.setStyle(Paint.Style.STROKE);
        mUsagePaint.setColor(mStateOkColorPrimary);
        mUsagePaint.setAntiAlias(true);

        mUsageBackPaint = new Paint(mUsagePaint);
        mUsageBackPaint.setColor(mStateNormalColorSecondary);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(Color.TRANSPARENT);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(mCircleColor));
    }

    private boolean hasClickedInsideCircle(float x, float y) {
        float radius = mCircleRect.width() / 2;
        float centerX = mCircleRect.centerX();
        float centerY = mCircleRect.centerY();
        return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) < Math.pow(radius, 2));
    }

    // Not used anymore
    public class VodafoneBounceInterpolator implements Interpolator {
        public VodafoneBounceInterpolator() {
        }

        private float bounce(float t) {
            return t * t * 8.0f;
        }

        // More on Interpolators
        // http://cogitolearning.co.uk/?p=1078
        public float getInterpolation(float t) {
            t *= 1.1226f;
            if (t < 0.3535f) return bounce(t);
            else if (t < 0.7408f) return bounce(t - 0.54719f) + 0.7f;
            else if (t < 0.9644f) return bounce(t - 0.8526f) + 0.9f;
            else return bounce(t - 1.0435f) + 0.95f;
        }
        //old one (no bounce)
        /*public float getInterpolation(float t) {
            float x = t*2.0f;
            if (t<0.5f) return 0.5f*x*x*x*x*x;
            x = (t-0.5f)*2-1;
            return 0.5f*x*x*x*x*x+1;
        }*/
    }

    private class Triangle extends FrameLayout {

        public Triangle(Context context) {
            super(context);
            setWillNotDraw(false);
        }

        public Triangle(Context context, AttributeSet attrs) {
            super(context, attrs);
            setWillNotDraw(false);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int arrowWidth = (int) (INNER_CIRCLE_STROKE * SCALE - CIRCLE_STROKE * SCALE);
            int arrowHeight = (int) (INNER_CIRCLE_STROKE * SCALE) / 2;
            Point triangleLeftPoint1 = new Point(0, arrowHeight);
            Point triangleLeftPoint2 = new Point(arrowWidth / 2, 0);
            Point triangleLeftPoint3 = new Point(arrowWidth, arrowHeight);

            Path triangleLeftPath = new Path();
            triangleLeftPath.setFillType(Path.FillType.EVEN_ODD);
            triangleLeftPath.moveTo(triangleLeftPoint1.x, triangleLeftPoint1.y);
            triangleLeftPath.lineTo(triangleLeftPoint2.x, triangleLeftPoint2.y);
            triangleLeftPath.lineTo(triangleLeftPoint3.x, triangleLeftPoint3.y);
            triangleLeftPath.close();

            canvas.drawPath(triangleLeftPath, mPaint);
            super.onDraw(canvas);
        }
    }

}