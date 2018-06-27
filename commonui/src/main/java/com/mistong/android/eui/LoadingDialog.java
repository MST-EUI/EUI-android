package com.mistong.android.eui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.mistong.android.commonui.R;

/**
 * @Date 2018/6/27
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.EUI_Dialog);
        init(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);

        ImageView iv_progress = contentView.findViewById(R.id.iv_progress);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_progress ,
                "rotation", 0f, 360f);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

        setCanceledOnTouchOutside(false);
        setCancelable(true);

        setContentView(contentView);
    }
}
