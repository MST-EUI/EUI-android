package com.mistong.android.commonui.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @Date 2018/10/17
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class DensityUtils {

    public static int dp2px(Context context, float dpVal) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics()));
    }
}
