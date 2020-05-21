package com.yrn.app_recycleview_itemdecoration.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yrn.app_recycleview_itemdecoration.R;
import com.yrn.app_recycleview_itemdecoration.adapter.MainRvAdapter;
import com.yrn.app_recycleview_itemdecoration.decoration.TextItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> data = new ArrayList<>();
    private RecyclerView rv;
    private MainRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new TextItemDecoration(this));

        for (int i = 0; i < 50; i++) {
            data.add("数据-----" + i);
        }
        adapter = new MainRvAdapter(data);
        rv.setAdapter(adapter);

    }
}
