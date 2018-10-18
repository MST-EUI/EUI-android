package com.mistong.android.commonui.ptr;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.mistong.android.commonui.R;
import com.mistong.android.commonui.utils.DensityUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

/**
 * @Date 2018/8/20
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description 上拉加载更多底部
 */
public class EUIPtrHeader extends InternalAbstract implements RefreshHeader {

    protected Drawable mDrawable;
    protected Paint mPaint;

    protected int mPencilTop;
    protected int mPencilLeft;
    protected int mShaderStart;
    protected int mShaderEnd;
    protected int mTraceStart;
    protected int mTraceEnd;
    protected int mTraceStartColor;
    protected int mTraceEndColor;
    protected boolean isAnimating = false;
    protected boolean isHorizontalMoving = false;
    protected boolean l2r;
    protected int mStopTemp;

    protected AnimatorSet animatorSet;
    protected ValueAnimator horizontalMoveAnimator;

    public EUIPtrHeader(Context context) {
        this(context, null);
    }

    public EUIPtrHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EUIPtrHeader(Context context, AttributeSet attributeSet, int defaultStyle) {
        super(context, attributeSet, defaultStyle);

        mDrawable = context.getResources().getDrawable(R.drawable.pencil);
        mDrawable.setBounds(0 , 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());

        mTraceStartColor = Color.parseColor("#98ADB6B6");
        mTraceEndColor = Color.TRANSPARENT;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DensityUtils.dp2px(context, 1));

        mPencilTop = -mDrawable.getIntrinsicHeight();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int extendHeight) {
        if (isAnimating) {
            return;
        }
        //part 1. 跟随下拉手势的下落
        offset += getHeight() / 2; //在拉到一半时定位在最终位置
        int minTop = (height - mDrawable.getIntrinsicHeight()) / 2;
        int minLeft = (getWidth() - mDrawable.getIntrinsicWidth()) / 2;
        double heightRatio = (double) mDrawable.getIntrinsicHeight() / getHeight();
        double top = (1 + heightRatio) * offset - getHeight() / 2 - 3 * mDrawable.getIntrinsicHeight() / 2;
        double left = getWidth() / 2;
        left += ((double) getHeight() * mDrawable.getIntrinsicWidth()) / (2 * mDrawable.getIntrinsicHeight());
        left -= (((double) getHeight() + mDrawable.getIntrinsicHeight()) / (2 * getHeight() * mDrawable.getIntrinsicHeight())) * mDrawable.getIntrinsicWidth() * offset;
        mPencilTop = Math.min(minTop, (int) top);
        mPencilLeft = Math.max(minLeft, (int) left);
        postInvalidate();
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {
        //part 2. 拖动释放后，着陆弹跳一下
        isAnimating = true;
        int startX = (getWidth() - mDrawable.getIntrinsicWidth()) / 2;
        final int endX = (getWidth() - 2 * mDrawable.getIntrinsicWidth()) / 2;
        ValueAnimator bounceXAnimator = ValueAnimator.ofInt(startX, endX);//x轴
        bounceXAnimator.setDuration(500);
        bounceXAnimator.setInterpolator(new LinearInterpolator());
        bounceXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPencilLeft = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        int startY = (height - mDrawable.getIntrinsicHeight()) / 2;
        int bounceYOffset = height / 2 - mDrawable.getIntrinsicHeight();
        ValueAnimator bounceYAnimator = ValueAnimator.ofInt(startY, bounceYOffset); //y轴
        bounceYAnimator.setDuration(500);
        bounceYAnimator.setInterpolator(new CustomInterpolator());
        bounceYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPencilTop = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        //part2.5 根据设计师描述，需要原地再弹跳一次，高度为10dp
        final ValueAnimator secondBounceAnimator = ValueAnimator.ofInt(startY, startY - DensityUtils.dp2px(getContext(), 10));
        secondBounceAnimator.setDuration(500);
        secondBounceAnimator.setInterpolator(new CustomInterpolator());
        secondBounceAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPencilTop = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        secondBounceAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (horizontalMoveAnimator != null) {
                    horizontalMoveAnimator.start();
                }
            }
        });

        //part 3. 水平来回移动
        final int pencilOffset = mDrawable.getIntrinsicWidth() / 4;

        horizontalMoveAnimator = ValueAnimator.ofInt(endX, getWidth() / 2);
        horizontalMoveAnimator.setDuration(500);
//        horizontalMoveAnimator.setStartDelay(300);
        horizontalMoveAnimator.setInterpolator(new LinearInterpolator());
        horizontalMoveAnimator.setRepeatMode(ValueAnimator.REVERSE);
        horizontalMoveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        horizontalMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isHorizontalMoving = true;
                mPencilLeft = (int) animation.getAnimatedValue();
                l2r = mPencilLeft >= mTraceEnd - pencilOffset;
                if (l2r) {
                    mShaderStart = mPencilLeft + pencilOffset - mDrawable.getIntrinsicWidth() / 2;
                    mShaderEnd = mShaderStart + mDrawable.getIntrinsicWidth() / 2;
                    mTraceStart = endX + pencilOffset;
                } else {
                    mShaderStart = mPencilLeft + pencilOffset + mDrawable.getIntrinsicWidth() / 2;
                    mShaderEnd = mShaderStart + mDrawable.getIntrinsicWidth() / 2;
                    mTraceStart = (getWidth() + mDrawable.getIntrinsicWidth()) / 2 - pencilOffset;
                }
                mTraceEnd = mPencilLeft + pencilOffset; //加上图片右侧到笔尖的距离

                invalidate();
            }
        });


        animatorSet = new AnimatorSet();
        animatorSet.play(bounceXAnimator).with(bounceYAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                secondBounceAnimator.start();
            }
        });
        setLayerType(LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        //水平来回停止，定格在水平居中的位置
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            animatorSet.end();
            animatorSet = null;
            isAnimating = false;
        }
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
        drawPencil(canvas);
        drawTrace(canvas);
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

    //先减速后加速的差值器，正弦函数模拟
    protected class CustomInterpolator implements Interpolator {

        CustomInterpolator() {

        }

        public float getInterpolation(float t) {
            return (float) Math.sin(Math.PI * t);
        }
    }
}
