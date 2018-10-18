package com.mistong.android.commonui.bottomtab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2018/8/22
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description 底部tab容器
 */
public class BottomTabLayout extends LinearLayout {

    protected List<BottomTabBean> mItems;
    protected List<BottomTab> mTabs;
    protected int selectedIndex = -1;
    protected OnItemClickListener listener;

    public BottomTabLayout(@NonNull Context context) {
        this(context, null, -1);
    }

    public BottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mItems = new ArrayList<>();
        mTabs = new ArrayList<>();

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void initItemViews() {
        if (mItems != null && mItems.size() > 0) {
            mTabs.clear();
            setWeightSum(mItems.size());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            for (int i = 0; i < mItems.size(); i++) {
                final BottomTab tab = new BottomTab(getContext());

                final int index = i;
                BottomTabBean bean = mItems.get(i);
                tab.setup(bean);
                tab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setSelectIndex(index);
                        if (listener != null) {
                            listener.onItemClick(index);
                        }
                    }
                });
                mTabs.add(tab);
                addView(tab, lp);
            }
        }
    }

    public void addTabs(List<BottomTabBean> items) {
        mItems.clear();
        removeAllViews();
        selectedIndex = -1;
        if (items != null) {
            mItems.addAll(items);
            initItemViews();
        }
    }

    public void setSelectIndex(int index) {
        if (selectedIndex == index) {
            return;
        }
        selectedIndex = index;
        if (selectedIndex >= 0 && selectedIndex < mTabs.size()) {

            for (int i = 0; i < mTabs.size(); i++) {
                BottomTab tab = mTabs.get(i);
                tab.setSelect(selectedIndex == i);
            }
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setNotice(int index, boolean notice) {
        if (index >= 0 && index < mTabs.size()) {
            mTabs.get(index).setNotice(notice);
        }
    }

    public void setItemLabel(int index, String label) {
        if (index >= 0 && index < mTabs.size()) {
            mTabs.get(index).setLabel(label);
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int itemIndex);
    }

}
