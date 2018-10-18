package com.mistong.android.commonui.ptr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.header.TwoLevelHeader;

/**
 * @Date 2018/8/28
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class EUITwoLevelHeader extends TwoLevelHeader {

    public EUITwoLevelHeader(@NonNull Context context) {
        super(context);
    }

    public EUITwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EUITwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startTwoLevel() {
        if (mRefreshHeader != null && mRefreshKernel != null) {
            mRefreshHeader.getView().setAlpha(0);
            mRefreshKernel.startTwoLevel(true);
        }
    }
}
