package com.mistong.android.commonui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.mistong.android.commonui.R;


/**
 * @author liuxiaoshuai
 * @date 2018/10/17
 * @desc 底部弹窗选择器
 * @email liulingfeng@mistong.com
 */
public class ActionSheetDialog extends BottomSheetDialog {
    private TextView tvTitle, tvAction1, tvAction2, tvActionWarn, tvActionCancel;
    private View dividerTitle, dividerAction1, dividerAction2;

    private String title;
    private String action1Str;
    private String action2Str;
    private String actionWarnStr;
    private String actionCancelStr;
    private View.OnClickListener action1Listener;
    private View.OnClickListener action2Listener;
    private View.OnClickListener actionWarnListener;

    private ActionSheetDialog(@NonNull Context context, Builder builder) {
        super(context);

        this.title = builder.title;
        this.action1Str = builder.action1Str;
        this.action2Str = builder.action2Str;
        this.actionWarnStr = builder.actionWarnStr;
        this.actionCancelStr = builder.actionCancelStr;
        this.action1Listener = builder.action1Listener;
        this.action2Listener = builder.action2Listener;
        this.actionWarnListener = builder.actionWarnListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.eui_dialog_actionsheet);
        tvTitle = findViewById(R.id.tv_title);
        tvAction1 = findViewById(R.id.tv_action1);
        tvAction2 = findViewById(R.id.tv_action2);
        tvActionWarn = findViewById(R.id.tv_warn);
        tvActionCancel = findViewById(R.id.tv_cancel);
        dividerTitle = findViewById(R.id.divider_title);
        dividerAction1 = findViewById(R.id.divider_action1);
        dividerAction2 = findViewById(R.id.divider_action2);

        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
            dividerTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
            dividerTitle.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(action1Str)) {
            tvAction1.setVisibility(View.GONE);
            dividerAction1.setVisibility(View.GONE);
        } else {
            tvAction1.setVisibility(View.VISIBLE);
            tvAction1.setText(action1Str);
            dividerAction1.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(action2Str)) {
            tvAction2.setVisibility(View.GONE);
            dividerAction2.setVisibility(View.GONE);
        } else {
            tvAction2.setVisibility(View.VISIBLE);
            dividerAction2.setVisibility(View.VISIBLE);
            tvAction2.setText(action2Str);
        }

        if (TextUtils.isEmpty(actionWarnStr)) {
            tvActionWarn.setVisibility(View.GONE);
        } else {
            tvActionWarn.setVisibility(View.VISIBLE);
            tvActionWarn.setText(actionWarnStr);
        }

        tvActionCancel.setText(actionCancelStr);

        if (action1Listener != null) {
            tvAction1.setOnClickListener(action1Listener);
        }

        if (action2Listener != null) {
            tvAction2.setOnClickListener(action2Listener);
        }

        if (actionWarnListener != null) {
            tvActionWarn.setOnClickListener(actionWarnListener);
        }

        tvActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
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

    public static class Builder {
        private String title;
        private String action1Str;
        private String action2Str;
        private String actionWarnStr;
        private String actionCancelStr = "取消";
        private View.OnClickListener action1Listener;
        private View.OnClickListener action2Listener;
        private View.OnClickListener actionWarnListener;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAction1Str(String action1Str) {
            this.action1Str = action1Str;
            return this;
        }

        public Builder setAction2Str(String action2Str) {
            this.action2Str = action2Str;
            return this;
        }

        public Builder setActionWarnStr(String actionWarnStr) {
            this.actionWarnStr = actionWarnStr;
            return this;
        }

        public Builder setActionCancelStr(String actionCancelStr) {
            this.actionCancelStr = actionCancelStr;
            return this;
        }


        public Builder setAction1Listener(View.OnClickListener action1Listener) {
            this.action1Listener = action1Listener;
            return this;
        }

        public Builder setAction1Listener(String action1Str, View.OnClickListener action1Listener) {
            this.action1Str = action1Str;
            this.action1Listener = action1Listener;
            return this;
        }

        public Builder setAction2Listener(View.OnClickListener action2Listener) {
            this.action2Listener = action2Listener;
            return this;
        }

        public Builder setAction2Listener(String action2Str, View.OnClickListener action2Listener) {
            this.action2Str = action2Str;
            this.action2Listener = action2Listener;
            return this;
        }

        public Builder setActionWarnListener(View.OnClickListener actionWarnListener) {
            this.actionWarnListener = actionWarnListener;
            return this;
        }

        public Builder setActionWarnListener(String actionWarnStr, View.OnClickListener actionWarnListener) {
            this.actionWarnStr = actionWarnStr;
            this.actionWarnListener = actionWarnListener;
            return this;
        }

        public ActionSheetDialog create() {
            return new ActionSheetDialog(context, this);
        }
    }
}
