package com.mistong.android.eui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;

import com.mistong.android.commonui.list.InfoView;
import com.mistong.android.commonui.toast.EUIToast;

/**
 * @Date 2018/10/19
 * @Author chenweida
 * @Email chenweida@mistong.com
 * @Description
 */
public class InfoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        findViewById(R.id.inf_1).setOnClickListener(this);
        findViewById(R.id.inf_4).setOnClickListener(this);
        findViewById(R.id.inf_5).setOnClickListener(this);

        ((InfoView) findViewById(R.id.inf_3)).setRightToggleListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EUIToast.showShort(InfoActivity.this, isChecked?"选中":"取消");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
