package com.mistong.android.commonui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.mistong.android.commonui.R;

/**
 * @Author liulf
 * 是和否两个按钮并排的情况
 */
public class EUIHorizontalDialog extends Dialog {
    private TextView tvTitle, tvContent, tvPositive, tvNegative;
    private EditText etInput;

    private String title;
    private String content;
    private String positiveStr;
    private String negativeStr;
    private boolean needContent = false;//是否显示内容
    private boolean needInput = false;//是否显示输入框
    private boolean cancelable;
    private View.OnClickListener positiveButtonClick;
    private View.OnClickListener negativeButtonClick;

    public EUIHorizontalDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CommonDialog);

        this.title = builder.title;
        this.content = builder.content;
        this.positiveStr = builder.positiveStr;
        this.negativeStr = builder.negativeStr;
        this.needContent = builder.needContent;
        this.needInput = builder.needInput;
        this.cancelable = builder.cancelable;
        this.positiveButtonClick = builder.positiveButtonClick;
        this.negativeButtonClick = builder.negativeButtonClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eui_dialog_horizontal);
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelable) {
                    dismiss();
                }
            }
        });

        initView();
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        tvNegative = findViewById(R.id.tv_negative);
        tvPositive = findViewById(R.id.tv_positive);
        etInput = findViewById(R.id.et_input);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvPositive.setText(positiveStr);
        tvNegative.setText(negativeStr);

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

        if (needInput) {
            etInput.setVisibility(View.VISIBLE);
        } else {
            etInput.setVisibility(View.GONE);
        }

        if (positiveButtonClick != null) {
            tvPositive.setOnClickListener(positiveButtonClick);
        }
        if (negativeButtonClick != null) {
            tvNegative.setOnClickListener(negativeButtonClick);
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
        private boolean needContent;//是否显示内容
        private boolean needInput;//是否显示按钮否
        private boolean cancelable = true;//点击外部弹窗消失
        private View.OnClickListener positiveButtonClick;
        private View.OnClickListener negativeButtonClick;
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

        public Builder isContent(Boolean needContent) {
            this.needContent = needContent;
            return this;
        }

        public Builder isInput(Boolean needInput) {
            this.needInput = needInput;
            return this;
        }

        public Builder setCancelable(Boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setPositiveButton(String positiveStr, View.OnClickListener positiveButtonClick) {
            this.positiveStr = positiveStr;
            this.positiveButtonClick = positiveButtonClick;
            return this;
        }

        public Builder setNegativeButton(String negativeStr, View.OnClickListener negativeButtonClick) {
            this.negativeStr = negativeStr;
            this.negativeButtonClick = negativeButtonClick;
            return this;
        }

        public EUIHorizontalDialog create() {
            return new EUIHorizontalDialog(context, this);
        }
    }
}
