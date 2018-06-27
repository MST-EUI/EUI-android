package com.mistong.android.eui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mistong.android.commonui.dialog.EUIHorizontalDialog;
import com.mistong.android.commonui.dialog.EUIVerticalDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_dialog_1;
    private Button btn_dialog_2;
    private Button btn_dialog_3;
    private Button btn_dialog_4;
    private Button btn_dialog_5;
    private Button btn_dialog_6;
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
        btn_dialog_6 = findViewById(R.id.btn_dialog_6);

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
        btn_dialog_6.setOnClickListener(this);
        btn_toast_1.setOnClickListener(this);
        btn_toast_2.setOnClickListener(this);
        btn_button_1.setOnClickListener(this);
        btn_button_2.setOnClickListener(this);
        btn_loading.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_1:
                EUIVerticalDialog.Builder builder1 = new EUIVerticalDialog.Builder(this);
                builder1.setTitle("标题");
                builder1.setContent("内容内容内容内容内容内容内容内容内容内容内容内容");
                builder1.setPositiveButton("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点1", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.create().show();
                break;
            case R.id.btn_dialog_2:
                EUIHorizontalDialog.Builder builder2 = new EUIHorizontalDialog.Builder(this);
                builder2.setTitle("标题");
                builder2.setContent("内容内容内容内容内容内容内容内容内容内容内容内容");
                builder2.setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点2", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点3", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.create().show();
                break;
            case R.id.btn_dialog_3:
                EUIVerticalDialog.Builder builder3 = new EUIVerticalDialog.Builder(this);
                builder3.setTitle("标题");
                builder3.setContent("内容内容内容内容内容内容内容内容内容内容内容内容");
                builder3.setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点4", Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点5", Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.setCancelButton("再看看", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点6", Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.create().show();
                break;
            case R.id.btn_dialog_4:
                EUIHorizontalDialog.Builder builder4 = new EUIHorizontalDialog.Builder(this);
                builder4.setTitle("标题");
                builder4.setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点7", Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点8", Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.create().show();
                break;
            case R.id.btn_dialog_5:
                EUIVerticalDialog.Builder builder5 = new EUIVerticalDialog.Builder(this);
                builder5.setTitle("标题");
                builder5.setWarnButton("警告", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点9", Toast.LENGTH_SHORT).show();
                    }
                });
                builder5.create().show();
                break;
            case R.id.btn_dialog_6:
                EUIHorizontalDialog.Builder builder6 = new EUIHorizontalDialog.Builder(this);
                builder6.setTitle("标题");
                builder6.isInput(true);
                builder6.setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点10", Toast.LENGTH_SHORT).show();
                    }
                });
                builder6.setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点11", Toast.LENGTH_SHORT).show();
                    }
                });
                builder6.create().show();
                break;
            case R.id.btn_toast_1:
                Toast.makeText(this, "单行吐司", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_toast_2:
                break;
            case R.id.btn_button_1:
                break;
            case R.id.btn_button_2:
                break;
            case R.id.btn_loading:
                break;
            default:
                break;
        }
    }
}
