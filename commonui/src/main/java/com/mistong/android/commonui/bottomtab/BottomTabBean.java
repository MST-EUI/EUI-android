package com.mistong.android.commonui.bottomtab;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @Date 2018/8/22
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class BottomTabBean {

    private int id;
    private String title;
    private int resId;
    private int activeResId;
    private int textColor;
    private int activeTextColor;
    private Drawable iconDrawable;
    private Drawable activeIconDrawable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getActiveResId() {
        return activeResId;
    }

    public void setActiveResId(int activeResId) {
        this.activeResId = activeResId;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getActiveTextColor() {
        return activeTextColor;
    }

    public void setActiveTextColor(int activeTextColor) {
        this.activeTextColor = activeTextColor;
    }

    //若没有设置，会返回resId指向的资源
    public Drawable getIconDrawable(Context context) {
        if (context != null && iconDrawable == null && resId != 0) {
            return context.getResources().getDrawable(resId);
        }
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    //若没有设置，会返回activeResId指向的资源
    public Drawable getActiveIconDrawable(Context context) {
        if (context != null && activeIconDrawable == null && resId != 0) {
            return context.getResources().getDrawable(activeResId);
        }
        return activeIconDrawable;
    }

    public void setActiveIconDrawable(Drawable activeIconDrawable) {
        this.activeIconDrawable = activeIconDrawable;
    }

}
