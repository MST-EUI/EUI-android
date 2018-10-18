package com.mistong.android.commonui.dialog;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mistong.android.commonui.R;

/**
 * @author liuxiaoshuai
 * @date 2018/10/17
 * @desc 分享的弹窗
 * @email liulingfeng@mistong.com
 */
public class ShareDialog {
    public static void show(Context context, final OneShare oneShare) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = dialog.getLayoutInflater().inflate(R.layout.eui_dialog_share, null);
        LinearLayout qq = view.findViewById(R.id.ll_qq);
        LinearLayout qqZone = view.findViewById(R.id.ll_qq_zone);
        LinearLayout weixin = view.findViewById(R.id.ll_weixin);
        LinearLayout weixinCicle = view.findViewById(R.id.ll_weixin_circle);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);


        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneShare.qqShare();
                dialog.dismiss();
            }
        });

        qqZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneShare.qqZoneShare();
                dialog.dismiss();
            }
        });

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneShare.weixinShare();
                dialog.dismiss();
            }
        });

        weixinCicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneShare.weixinCicle();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    public interface OneShare {
        void qqShare();

        void qqZoneShare();

        void weixinShare();

        void weixinCicle();
    }
}
