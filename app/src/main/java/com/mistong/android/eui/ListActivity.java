package com.mistong.android.eui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mistong.android.commonui.empty.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private EmptyLayout emptyLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        emptyLayout = findViewById(R.id.emptyLayout);
        recyclerView = findViewById(R.id.rv_content);
        emptyLayout.bindView(recyclerView);

        //制造的bug
        TextView textView=null;
        textView.setText("dsa");
        String substring = "9999".substring(0, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.load_content:
                emptyLayout.showSuccess();
                break;
            case R.id.load_empty:
                emptyLayout.showEmpty();
                break;
            case R.id.load_error:
                emptyLayout.showError();
                emptyLayout.setReloadListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ListActivity.this,"重新加载",Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
