package com.mistong.android.eui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mistong.android.commonui.bottomtab.BottomTabBean;
import com.mistong.android.commonui.bottomtab.BottomTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2018/10/18
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class BottomLayoutActivity extends AppCompatActivity {

    private BottomTabLayout bottom_tab_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        bottom_tab_layout = findViewById(R.id.bottom_tab_layout);
        List<BottomTabBean> bottomTabBeanList = getBeans();
        bottom_tab_layout.addTabs(bottomTabBeanList);
        bottom_tab_layout.setListener(new BottomTabLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int itemIndex) {

            }
        });
        bottom_tab_layout.setSelectIndex(0);
    }

    private List<BottomTabBean> getBeans() {
        List<BottomTabBean> bottomTabBeanList = new ArrayList<>();
        Color.parseColor("#2e86ff");
        int activeColor = Color.parseColor("#2e86ff");
        int normalColor = Color.parseColor("#888888");
        BottomTabBean mainPageBean = new BottomTabBean();
        mainPageBean.setResId(R.drawable.app_main_mainpage_n);
        mainPageBean.setActiveResId(R.drawable.app_main_mainpage_s);
        mainPageBean.setTextColor(normalColor);
        mainPageBean.setActiveTextColor(activeColor);
        mainPageBean.setTitle("首页");
        bottomTabBeanList.add(mainPageBean);

        BottomTabBean eRoomBean = new BottomTabBean();
        eRoomBean.setResId(R.drawable.app_main_e_class_n);
        eRoomBean.setActiveResId(R.drawable.app_main_e_class_s);
        eRoomBean.setTextColor(normalColor);
        eRoomBean.setActiveTextColor(activeColor);
        eRoomBean.setTitle("E讲堂");
        bottomTabBeanList.add(eRoomBean);

        BottomTabBean careerBean = new BottomTabBean();
        careerBean.setResId(R.drawable.app_main_voluntary_n);
        careerBean.setActiveResId(R.drawable.app_main_voluntary_s);
        careerBean.setTextColor(normalColor);
        careerBean.setActiveTextColor(activeColor);
        careerBean.setTitle("生涯规划");
        bottomTabBeanList.add(careerBean);

        BottomTabBean fmBean = new BottomTabBean();
        fmBean.setResId(R.drawable.app_main_fm_n);
        fmBean.setActiveResId(R.drawable.app_main_fm_s);
        fmBean.setTextColor(normalColor);
        fmBean.setActiveTextColor(activeColor);
        fmBean.setTitle("心灵成长");
        bottomTabBeanList.add(fmBean);

        BottomTabBean myBean = new BottomTabBean();
        myBean.setResId(R.drawable.app_main_personal_n);
        myBean.setActiveResId(R.drawable.app_main_personal_s);
        myBean.setTextColor(normalColor);
        myBean.setActiveTextColor(activeColor);
        myBean.setTitle("我的");
        bottomTabBeanList.add(myBean);

        return bottomTabBeanList;
    }
}
