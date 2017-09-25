package com.github.sfyc23.asynclistutil_example_java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.sfyc23.asynclistutil_example_java.db.CopySQLiteData;
import com.github.sfyc23.asynclistutil_example_java.db.SQLiteItemSource;

/**
 * Author :leilei on 2017/9/24 19:16
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AsyncAdapter adapter;
    private SQLiteItemSource itemSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        itemSource = new SQLiteItemSource(CopySQLiteData.getDatabase(this, CopySQLiteData.ASSET_FILE_NAME));
        adapter = new AsyncAdapter(itemSource, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.onStart(recyclerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.onStop(recyclerView);
    }
}
