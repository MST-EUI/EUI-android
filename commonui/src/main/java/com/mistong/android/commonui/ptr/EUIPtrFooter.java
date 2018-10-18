package com.mistong.android.commonui.ptr;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import com.mistong.android.commonui.R;
import com.mistong.android.commonui.utils.DensityUtils;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

/**
 * @Date 2018/8/20
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description 上拉加载更多底部
 */
public class EUIPtrFooter extends InternalAbstract implements RefreshFooter {

    protected Drawable mDrawable;
    protected Paint mPaint;
    protected Paint mPaint2;

    protected int mPencilTop;
    protected int mPencilLeft;
    protected int mShaderStart;
    protected int mShaderEnd;
    protected int mTraceStart;
    protected int mTraceEnd;
    protected int mTraceStartColor;
    protected int mTraceEndColor;

    protected boolean mNoMoreData = false;
    protected boolean isHorizontalMoving = false;
    protected boolean l2r;
    protected int mStopTemp;
    protected Rect mTextBounds;

    protected ValueAnimator horizontalMoveAnimator;

    public EUIPtrFooter(Context context) {
        this(context, null);
    }

    public EUIPtrFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EUIPtrFooter(Context context, AttributeSet attributeSet, int defaultStyle) {
        super(context, attributeSet, defaultStyle);

        mDrawable = context.getResources().getDrawable(R.drawable.pencil);
        mDrawable.setBounds(0 , 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());

        mTraceStartColor = Color.parseColor("#98ADB6B6");
        mTraceEndColor = Color.TRANSPARENT;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DensityUtils.dp2px(context,1));

        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.parseColor("#A7ACB9"));
        mPaint2.setTextSize(DensityUtils.dp2px(context,12));
        mPaint2.setAntiAlias(true);
        mPaint2.setTypeface(Typeface.DEFAULT);

        mTextBounds = new Rect();
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData;
        }
        postInvalidateDelayed(300); //需触发一下view刷新， 让铅笔停驻300ms
        return noMoreData;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPencilLeft = (getMeasuredWidth() - mDrawable.getIntrinsicWidth()) / 2;
        mPencilTop = (getMeasuredHeight() - mDrawable.getIntrinsicHeight()) / 2;
    }

    private void startHorizontalMoving() {
        if (isHorizontalMoving) {
            return;
        }
        final int pencilOffset = mDrawable.getIntrinsicWidth() / 4;
        final int center = (getWidth() - mDrawable.getIntrinsicWidth()) / 2;

        final int shaderLength = mDrawable.getIntrinsicWidth() / 2;
        ValueAnimator startAnimator = ValueAnimator.ofInt(center, getWidth() / 2  - mDrawable.getIntrinsicWidth());
        startAnimator.setDuration(250);
        startAnimator.setStartDelay(300);
        startAnimator.setInterpolator(new LinearInterpolator());
        startAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isHorizontalMoving = true;
                mPencilLeft = (int) animation.getAnimatedValue();

                mShaderStart = mPencilLeft + pencilOffset - shaderLength;
                mShaderEnd = mShaderStart + shaderLength;
                mTraceEnd = mPencilLeft + pencilOffset;
                mTraceStart = getWidth() / 2 - mDrawable.getIntrinsicWidth() / 4;
                invalidate();
            }
        });

        horizontalMoveAnimator = ValueAnimator.ofInt(getWidth() / 2  - mDrawable.getIntrinsicWidth(),
                getWidth() / 2);
        horizontalMoveAnimator.setDuration(500);
        horizontalMoveAnimator.setInterpolator(new LinearInterpolator());
        horizontalMoveAnimator.setRepeatMode(ValueAnimator.REVERSE);
        horizontalMoveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        horizontalMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPencilLeft = (int) animation.getAnimatedValue();
                l2r = mPencilLeft >= mTraceEnd - pencilOffset;
                if (l2r) {
                    mShaderStart = mPencilLeft + pencilOffset - mDrawable.getIntrinsicWidth() / 2;
                    mShaderEnd = mShaderStart + mDrawable.getIntrinsicWidth() / 2;
                    mTraceStart = (getWidth() - 2 * mDrawable.getIntrinsicWidth()) / 2 + pencilOffset;
                } else {
                    mShaderStart = mPencilLeft + pencilOffset + mDrawable.getIntrinsicWidth() / 2;
                    mShaderEnd = mShaderStart + mDrawable.getIntrinsicWidth() / 2;
                    mTraceStart = (getWidth() + mDrawable.getIntrinsicWidth()) / 2 - pencilOffset;
                }
                mTraceEnd = mPencilLeft + pencilOffset; //加上图片右侧到笔尖的距离

                invalidate();
            }
        });
        setLayerType(LAYER_TYPE_HARDWARE, null);

        startAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (horizontalMoveAnimator != null) {
                    horizontalMoveAnimator.start();
                }
            }
        });
        startAnimator.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (horizontalMoveAnimator != null) {
            horizontalMoveAnimator.removeAllUpdateListeners();
            horizontalMoveAnimator.end();
            horizontalMoveAnimator = null;
        }

        final int pencilOffset = mDrawable.getIntrinsicWidth() / 4;
        final int pivot = (getWidth() - mDrawable.getIntrinsicWidth()) / 2;
        final boolean atPivotRight = mPencilLeft >= pivot;
        final int shaderLength = mDrawable.getIntrinsicWidth() / 2;
        final int right = getWidth() / 2;
        final int left = (getWidth() - 2 * mDrawable.getIntrinsicWidth()) / 2;
        final int startX = mPencilLeft;
        mStopTemp = mPencilLeft;
        int length;
        if (l2r) {
            if (atPivotRight) {
                length = (pivot + mDrawable.getIntrinsicWidth()) - mPencilLeft;
            } else {
                length = pivot - mPencilLeft;
            }
        } else {
            if (atPivotRight) {
                length = mPencilLeft - pivot;
            } else {
                length = mPencilLeft - (pivot - mDrawable.getIntrinsicWidth());
            }
        }
        int duration = (int) (((double) length / mDrawable.getIntrinsicWidth()) * 500);
        ValueAnimator stopAnimator = ValueAnimator.ofInt(0, length);
        stopAnimator.setDuration(duration);
        stopAnimator.setInterpolator(new LinearInterpolator());
        stopAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (l2r) {
                    mStopTemp = startX + (int) animation.getAnimatedValue();
                    mPencilLeft = mStopTemp > right? right - (mStopTemp - right):mStopTemp;

                    mShaderStart = mPencilLeft + pencilOffset + (mStopTemp > right?-shaderLength:shaderLength);
                    mShaderEnd = mShaderStart + shaderLength;
                    mTraceEnd = mPencilLeft + pencilOffset;
                    mTraceStart = (mStopTemp > right?right:left) + pencilOffset;
                } else {
                    mStopTemp = startX - (int) animation.getAnimatedValue();
                    mPencilLeft = mStopTemp < left? left + (left - mStopTemp):mStopTemp;

                    mShaderStart = mPencilLeft + pencilOffset + (mStopTemp < left?shaderLength:-shaderLength);
                    mShaderEnd = mShaderStart + shaderLength;
                    mTraceEnd = mPencilLeft + pencilOffset;
                    mTraceStart = (mStopTemp < left?left:right) + pencilOffset;
                }
                invalidate();
            }
        });

        stopAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mTraceStart = 0;
                mTraceEnd = 0;
                isHorizontalMoving = false;
                setLayerType(LAYER_TYPE_NONE, null);
            }
        });
        stopAnimator.start();
        return duration + 500;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.translate(0, 0);
        if (mNoMoreData && !isHorizontalMoving) {
            drawNoMoreData(canvas);
        } else {
            drawPencil(canvas);
            drawTrace(canvas);
        }
    }

    //绘制铅笔图案
    private void drawPencil(Canvas canvas) {
        canvas.save();
        canvas.translate(mPencilLeft, mPencilTop);
        mDrawable.draw(canvas);
        canvas.restore();
    }

    //绘制铅笔笔迹
    private void drawTrace(Canvas canvas) {
        if (!isHorizontalMoving) {
            return;
        }
        int y = (getHeight() + mDrawable.getIntrinsicHeight()) / 2 + 1;
        mPaint.setShader(new LinearGradient(mShaderStart, 0, mShaderEnd, 0, mTraceStartColor, mTraceEndColor, Shader.TileMode.MIRROR));
        canvas.drawLine(mTraceStart, y, mTraceEnd, y, mPaint);
    }

    private void drawNoMoreData(Canvas canvas) {
        String text = "已经到底啦";
        mPaint2.getTextBounds(text, 0, text.length(), mTextBounds);
        int x = getWidth() / 2 - mTextBounds.width() / 2 - mTextBounds.left;
        int y = getHeight() / 2  + mTextBounds.height() / 2 - mTextBounds.bottom;
        canvas.drawText(text, x, y, mPaint2);
        canvas.drawLine(x - DensityUtils.dp2px(getContext(), 24), getHeight()/2, x - DensityUtils.dp2px(getContext(), 12), getHeight()/2, mPaint2);
        canvas.drawLine(getWidth() / 2 + mTextBounds.width() / 2 + DensityUtils.dp2px(getContext(), 12), getHeight()/2,
                getWidth() / 2 + mTextBounds.width() / 2 + DensityUtils.dp2px(getContext(), 24), getHeight()/2, mPaint2);
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (RefreshState.Loading == newState) {
            startHorizontalMoving();
        }
    }

}
