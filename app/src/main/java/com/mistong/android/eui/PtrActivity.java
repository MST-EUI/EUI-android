package com.mistong.android.eui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

/**
 * @Date 2018/10/17
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class PtrActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr);

        smartRefreshLayout = findViewById(R.id.refresh_layout);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishLoadMore(0, true, true);
                    }
                }, 3000);
            }
        });
    }
}
