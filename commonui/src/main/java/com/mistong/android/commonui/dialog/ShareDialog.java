package com.mistong.android.commonui.dialog;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.LinearLayout;
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
        LinearLayout weibo = view.findViewById(R.id.ll_weibo);
        LinearLayout weixin = view.findViewById(R.id.ll_weixin);
        LinearLayout qq = view.findViewById(R.id.ll_qq);
        LinearLayout cope = view.findViewById(R.id.ll_cope);

        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneShare.weiboShare();
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

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneShare.qqShare();
                dialog.dismiss();
            }
        });

        cope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneShare.cope();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    public interface OneShare {
        void weiboShare();

        void weixinShare();

        void qqShare();

        void cope();
    }
}
