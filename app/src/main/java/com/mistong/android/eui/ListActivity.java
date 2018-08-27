package com.mistong.android.eui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.mistong.android.commonui.empty.EmptyLayout;

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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
