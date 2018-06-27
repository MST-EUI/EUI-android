package com.mistong.android.eui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_dialog_1;
    private Button btn_dialog_2;
    private Button btn_dialog_3;
    private Button btn_dialog_4;
    private Button btn_dialog_5;
    private Button btn_toast_1;
    private Button btn_toast_2;
    private Button btn_button_1;
    private Button btn_button_2;
    private Button btn_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog_1 = findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = findViewById(R.id.btn_dialog_2);
        btn_dialog_3 = findViewById(R.id.btn_dialog_3);
        btn_dialog_4 = findViewById(R.id.btn_dialog_4);
        btn_dialog_5 = findViewById(R.id.btn_dialog_5);

        btn_toast_1 = findViewById(R.id.btn_toast_1);
        btn_toast_2 = findViewById(R.id.btn_toast_2);

        btn_button_1 = findViewById(R.id.btn_button_1);
        btn_button_2 = findViewById(R.id.btn_button_2);

        btn_loading = findViewById(R.id.btn_loading);


        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_3.setOnClickListener(this);
        btn_dialog_4.setOnClickListener(this);
        btn_dialog_5.setOnClickListener(this);
        btn_toast_1.setOnClickListener(this);
        btn_toast_2.setOnClickListener(this);
        btn_button_1.setOnClickListener(this);
        btn_button_2.setOnClickListener(this);
        btn_loading.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_1: break;
            case R.id.btn_dialog_2: break;
            case R.id.btn_dialog_3: break;
            case R.id.btn_dialog_4: break;
            case R.id.btn_dialog_5: break;
            case R.id.btn_toast_1:
                Toast.makeText(this, "单行吐司", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_toast_2: break;
            case R.id.btn_button_1: break;
            case R.id.btn_button_2: break;
            case R.id.btn_loading: break;
            default: break;
        }
    }
}
