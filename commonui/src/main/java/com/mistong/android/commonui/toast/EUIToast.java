package com.mistong.android.commonui.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mistong.android.commonui.R;

import java.lang.reflect.Field;

/**
 * @Date 2018/6/27
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class EUIToast {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static Toast sToast;

    public static void showShort(Context context, String content) {
        show(context, content, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context, String content) {
        show(context, content, Toast.LENGTH_LONG);
    }

    public static void show(final Context context, final String content, final int duration) {
        HANDLER.post(new Runnable() {
            @SuppressLint("ShowToast")
            @Override
            public void run() {
                cancel();
                sToast = Toast.makeText(context, content, duration);
                TextView textView = sToast.getView().findViewById(android.R.id.message);
                int textSize = (int) context.getResources().getDimension(R.dimen.toast_text_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                int maxWidth = (int) context.getResources().getDimension(R.dimen.toast_max_width);
                int minWidth = (int) context.getResources().getDimension(R.dimen.toast_min_width);
                textView.setGravity(Gravity.CENTER);
                textView.setMaxWidth(maxWidth);
                textView.setMinWidth(minWidth);
                int horizontalPadding = (int) context.getResources().getDimension(R.dimen.toast_horizontal_padding);
                int verticalPadding = (int) context.getResources().getDimension(R.dimen.toast_vertical_padding);
                sToast.getView().setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
                sToast.getView().setBackgroundResource(R.drawable.eui_toast_bg);
                sToast.setGravity(Gravity.CENTER, 0, 0);
                showToast(context);
            }
        });
    }

    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
        }
    }

    private static void showToast(Context context) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
            try {
                Field field = View.class.getDeclaredField("mContext");
                field.setAccessible(true);
                field.set(sToast.getView(), new ApplicationContextWrapperForApi25(context));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        sToast.show();
    }

    private static final class ApplicationContextWrapperForApi25 extends ContextWrapper {

        ApplicationContextWrapperForApi25(Context context) {
            super(context.getApplicationContext());
        }

        @Override
        public Context getApplicationContext() {
            return this;
        }

        @Override
        public Object getSystemService(@NonNull String name) {
            if (Context.WINDOW_SERVICE.equals(name)) {
                return new WindowManagerWrapper((WindowManager) getBaseContext().getSystemService(name));
            }
            return super.getSystemService(name);
        }

        private static final class WindowManagerWrapper implements WindowManager {

            private final WindowManager base;

            private WindowManagerWrapper(@NonNull WindowManager base) {
                this.base = base;
            }

            @Override
            public Display getDefaultDisplay() {
                return base.getDefaultDisplay();
            }

            @Override
            public void removeViewImmediate(View view) {
                base.removeViewImmediate(view);
            }

            @Override
            public void addView(View view, ViewGroup.LayoutParams params) {
                try {
                    base.addView(view, params);
                } catch (BadTokenException e) {
                    Log.e("WindowManagerWrapper", e.getMessage());
                } catch (Throwable throwable) {
                    Log.e("WindowManagerWrapper", "[addView]", throwable);
                }
            }

            @Override
            public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
                base.updateViewLayout(view, params);
            }

            @Override
            public void removeView(View view) {
                base.removeView(view);
            }
        }
    }
}
