package com.mistong.android.commonui.empty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mistong.android.commonui.R;

/**
 * @author liuxiaoshuai
 * @date 2018/8/27
 * @desc 数据为空和数据加载错误的布局
 * @email liulingfeng@mistong.com
 */
public class EmptyLayout extends FrameLayout {
    private View mBindView;//內容控件
    private View mEmptyView;
    private View mErrorView;
    private ImageView ivEmpty;
    private TextView tvEmpty;
    private ImageView ivError;
    private TextView tvError;
    private TextView tvReload;


    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //居中
        params.gravity = Gravity.CENTER;
        mEmptyView = View.inflate(context, R.layout.eui_view_empty, null);
        ivEmpty = mEmptyView.findViewById(R.id.iv_empty);
        tvEmpty = mEmptyView.findViewById(R.id.tv_empty);
        addView(mEmptyView, params);
        mErrorView = View.inflate(context, R.layout.eui_view_error, null);
        ivError = mEmptyView.findViewById(R.id.iv_error);
        tvError = mEmptyView.findViewById(R.id.tv_error);
        tvReload = mEmptyView.findViewById(R.id.tv_reload);
        addView(mErrorView, params);

        setGone();
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    /**
     * 加载失败
     */
    public void showError() {
        showError(null);
    }

    public void showError(String text) {
        showError(text, 0);
    }

    public void showError(String text, int errorResource) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) tvError.setText(text);
        if (errorResource != 0) ivError.setImageResource(errorResource);
        setGone();
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 数据为空
     */
    public void showEmpty() {
        showEmpty(null);
    }

    public void showEmpty(String text) {
        showEmpty(text, 0);
    }

    public void showEmpty(String text, int emptyResource) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) tvEmpty.setText(text);
        if (emptyResource != 0) ivEmpty.setImageResource(emptyResource);
        setGone();
        mEmptyView.setVisibility(View.VISIBLE);
    }

    /**
     * 加载成功
     */
    public void showSuccess() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        if (mBindView != null) mBindView.setVisibility(View.VISIBLE);
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void setReloadListener(View.OnClickListener listener) {
        tvReload.setOnClickListener(listener);
    }
}
