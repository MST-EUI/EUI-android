package com.mistong.android.commonui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.mistong.android.commonui.R;

/**
 * 是和否两个按钮分排的情况
 */
public class EUIVerticalDialog extends Dialog {
    private TextView tvTitle, tvContent, tvPositive, tvNegative, tvCancel, tvWarn;
    private View dvNegative, dvCancel, dvWarn;
    private String title;
    private String content;
    private String positiveStr;
    private String negativeStr;
    private String cancelStr;
    private String warnStr;
    private boolean needContent;
    private boolean needPositive;
    private boolean needNegative;
    private boolean needCancel;
    private boolean needWarn;
    private boolean cancelable;
    private View.OnClickListener positiveButtonClick;
    private View.OnClickListener negativeButtonClick;
    private View.OnClickListener cancelButtonClick;
    private View.OnClickListener warnButtonClick;

    private EUIVerticalDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CommonDialog);

        this.title = builder.title;
        this.content = builder.content;
        this.positiveStr = builder.positiveStr;
        this.negativeStr = builder.negativeStr;
        this.cancelStr = builder.cancelStr;
        this.warnStr = builder.warnStr;
        this.needPositive = builder.needPositive;
        this.needNegative = builder.needNegative;
        this.needCancel = builder.needCancel;
        this.needWarn = builder.needWarn;
        this.needContent = builder.needContent;
        this.cancelable = builder.cancelable;
        positiveButtonClick = builder.positiveButtonClick;
        negativeButtonClick = builder.negativeButtonClick;
        cancelButtonClick = builder.cancelButtonClick;
        warnButtonClick = builder.warnButtonClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eui_dialog_vertical);
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelable) {
                    dismiss();
                }
            }
        });

        findViewById(R.id.dialog_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initView();
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        tvNegative = findViewById(R.id.tv_negative);
        tvPositive = findViewById(R.id.tv_positive);
        tvCancel = findViewById(R.id.tv_cancel);
        tvWarn = findViewById(R.id.tv_warn);
        dvNegative = findViewById(R.id.divider_negative);
        dvCancel = findViewById(R.id.divider_cancel);
        dvWarn = findViewById(R.id.divider_warn);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvPositive.setText(positiveStr);
        tvNegative.setText(negativeStr);
        tvCancel.setText(cancelStr);
        tvWarn.setText(warnStr);

        if (cancelable) {
            setCanceledOnTouchOutside(true);
        } else {
            setCanceledOnTouchOutside(false);
        }

        if (needContent) {
            tvContent.setVisibility(View.VISIBLE);
        } else {
            tvContent.setVisibility(View.GONE);
        }

        if (needPositive) {
            tvPositive.setVisibility(View.VISIBLE);
        } else {
            tvPositive.setVisibility(View.GONE);
        }

        if (needNegative) {
            tvNegative.setVisibility(View.VISIBLE);
            dvNegative.setVisibility(View.VISIBLE);
        } else {
            tvNegative.setVisibility(View.GONE);
            dvNegative.setVisibility(View.GONE);
        }

        if (needCancel) {
            tvCancel.setVisibility(View.VISIBLE);
            dvCancel.setVisibility(View.VISIBLE);
        } else {
            tvCancel.setVisibility(View.GONE);
            dvCancel.setVisibility(View.GONE);
        }

        if (needWarn) {
            tvWarn.setVisibility(View.VISIBLE);
            dvWarn.setVisibility(View.VISIBLE);
        } else {
            tvWarn.setVisibility(View.GONE);
            dvWarn.setVisibility(View.GONE);
        }

        if (positiveButtonClick != null) {
            tvPositive.setOnClickListener(positiveButtonClick);
        }
        if (negativeButtonClick != null) {
            tvNegative.setOnClickListener(negativeButtonClick);
        }
        if (cancelButtonClick != null) {
            tvCancel.setOnClickListener(cancelButtonClick);
        }
        if (warnButtonClick != null) {
            tvWarn.setOnClickListener(warnButtonClick);
        }
    }

    @Override
    public void show() {
        super.show();

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

    }

    @Keep
    public static class Builder {
        private String title = "标题";
        private String content = "内容";
        private String positiveStr = "是";
        private String negativeStr = "否";
        private String cancelStr = "再看看";
        private String warnStr = "警告";
        private boolean needContent;//是否显示内容
        private boolean needPositive;//是否显示按钮是
        private boolean needNegative;//是否显示按钮否
        private boolean needCancel;//是否显示按钮再看看
        private boolean needWarn;//是否显示按钮警告
        private boolean cancelable = true;//点击外部弹窗消失
        private View.OnClickListener positiveButtonClick;
        private View.OnClickListener negativeButtonClick;
        private View.OnClickListener cancelButtonClick;
        private View.OnClickListener warnButtonClick;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            needContent = true;
            return this;
        }

        public Builder isPositive(Boolean needPositive) {
            this.needPositive = needPositive;
            return this;
        }

        public Builder isNegative(Boolean needNegative) {
            this.needNegative = needNegative;
            return this;
        }

        public Builder isCancel(Boolean needCancel) {
            this.needCancel = needCancel;
            return this;
        }

        public Builder isWarn(Boolean needWarn) {
            this.needWarn = needWarn;
            return this;
        }

        public Builder isContent(Boolean needContent) {
            this.needContent = needContent;
            return this;
        }

        public Builder setCancelable(Boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setPositiveButton(String positiveStr, View.OnClickListener positiveButtonClick) {
            this.positiveStr = positiveStr;
            needPositive = true;
            this.positiveButtonClick = positiveButtonClick;
            return this;
        }

        public Builder setNegativeButton(String negativeStr, View.OnClickListener negativeButtonClick) {
            this.negativeStr = negativeStr;
            needNegative = true;
            this.negativeButtonClick = negativeButtonClick;
            return this;
        }

        public Builder setCancelButton(String cancelStr, View.OnClickListener cancelButtonClick) {
            this.cancelStr = cancelStr;
            needCancel = true;
            this.cancelButtonClick = cancelButtonClick;
            return this;
        }

        public Builder setWarnButton(String warnStr, View.OnClickListener warnButtonClick) {
            this.warnStr = warnStr;
            needWarn = true;
            this.warnButtonClick = warnButtonClick;
            return this;
        }

        public EUIVerticalDialog create() {
            return new EUIVerticalDialog(context, this);
        }
    }
}
