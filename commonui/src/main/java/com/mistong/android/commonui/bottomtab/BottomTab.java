package com.mistong.android.commonui.bottomtab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mistong.android.commonui.R;


/**
 * @Date 2018/8/27
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description 底部tab item
 */
public class BottomTab extends RelativeLayout {

    private int mUnSelectColor; //文字颜色
    private int mSelectColor;
    private Drawable mUnSelectDrawable; //图片资源
    private Drawable mSelectDrawable;

    private TextView tv_label;
    private ImageView iv_icon;
    private ImageView iv_notice;

    public BottomTab(@NonNull Context context) {
        this(context, null, -1);
    }

    public BottomTab(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BottomTab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View container = LayoutInflater.from(context).inflate(R.layout.bottom_tab_item, this);
        iv_icon = container.findViewById(R.id.iv_icon);
        tv_label = container.findViewById(R.id.tv_label);
        iv_notice = container.findViewById(R.id.iv_notice);
    }

    /**
     * 设置选中、未选中字色和图标 及label文本
     */
    public void setup(BottomTabBean bottomTabBean) {
        if (bottomTabBean == null) {
            return;
        }
        tv_label.setText(bottomTabBean.getTitle());
        mUnSelectColor = bottomTabBean.getTextColor();
        mSelectColor = bottomTabBean.getActiveTextColor();
        mUnSelectDrawable = bottomTabBean.getIconDrawable(getContext());
        mSelectDrawable = bottomTabBean.getActiveIconDrawable(getContext());
    }

    //默认开启动画
    public void setSelect(boolean select) {
        setSelect(select, true);
    }

    public void setSelect(boolean select, boolean animate) {
        iv_icon.setImageDrawable(select?mSelectDrawable:mUnSelectDrawable);
        tv_label.setTextColor(select?mSelectColor:mUnSelectColor);

        //animate
        if (select && animate) {
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv_icon, "scaleX", 1.12f);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv_icon, "scaleY", 1.12f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(scaleXAnimator, scaleYAnimator);
            set.setDuration(300);
            set.setInterpolator(new CustomInterpolator());

            ObjectAnimator scaleXAnimator2 = ObjectAnimator.ofFloat(iv_icon, "scaleX", 1.06f);
            ObjectAnimator scaleYAnimator2 = ObjectAnimator.ofFloat(iv_icon, "scaleY", 1.06f);
            final AnimatorSet set2 = new AnimatorSet();
            set2.playTogether(scaleXAnimator2, scaleYAnimator2);
            set2.setDuration(200);
            set2.setInterpolator(new CustomInterpolator());

            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    set2.start();
                }
            });
            set.start();
        }
    }

    public void setNotice(boolean notice) {
        iv_notice.setVisibility(notice?VISIBLE:GONE);
    }

    public void setLabel(String title) {
        if (title == null) return;
        tv_label.setText(title);
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
