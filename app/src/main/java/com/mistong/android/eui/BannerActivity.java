package com.mistong.android.eui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mistong.android.commonui.banner.BannerEntity;
import com.mistong.android.commonui.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    private BannerView bannerView;
    private List<BannerEntity> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        BannerEntity bannerEntity1 = new BannerEntity();
        bannerEntity1.path = "http://static.235.mistong.com/upload/ewt/image/2018/05/28/20180528153257941.png";
        BannerEntity bannerEntity2 = new BannerEntity();
        bannerEntity2.path = "http://static.235.mistong.com/upload/ewt/image/2018/07/16/20180716170654180.png";
        BannerEntity bannerEntity3 = new BannerEntity();
        bannerEntity3.path = "http://static.235.mistong.com/upload/ewt/image/2018/05/31/20180531180350744.jpg";
        datas.add(bannerEntity1);
        datas.add(bannerEntity2);
        datas.add(bannerEntity3);
        bannerView = findViewById(R.id.banner);
        bannerView.setData(datas);
    }
}
