package com.mistong.android.eui.fit;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;

/**
 * @author liuxiaoshuai
 * @date 2018/8/28
 * @desc
 * @email liulingfeng@mistong.com
 */
public class DensityUtil {
    public static void setCustomDensity(Activity activity, Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        float targetDensity = appDisplayMetrics.widthPixels / 360;
        int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = appDisplayMetrics.scaledDensity = targetDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = activityDisplayMetrics.scaledDensity = targetDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
