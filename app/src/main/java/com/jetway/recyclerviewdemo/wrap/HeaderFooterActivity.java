package com.jetway.recyclerviewdemo.wrap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.jetway.recyclerviewdemo.BaseRecyclerActivity;
import com.jetway.recyclerviewdemo.GradLayoutItemDecoration;
import com.jetway.recyclerviewdemo.LinearLayoutItemDecoration;
import com.jetway.recyclerviewdemo.ListAdapter;
import com.jetway.recyclerviewdemo.R;
import com.jetway.recyclerviewdemo.commonAdapter.ItemClickListener;
import com.jetway.recyclerviewdemo.commonAdapter.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class HeaderFooterActivity extends AppCompatActivity {
    private List<String> mDatas;
    private RecyclerView recycler_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);
        initDatas();
        recycler_data = findViewById(R.id.recycler_data);
        recycler_data.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter(this,mDatas,R.layout.recycler_item);
        WrapRecyclerviewAdapter wrapRecyclerviewAdapter = new WrapRecyclerviewAdapter(adapter);
        recycler_data.setAdapter( wrapRecyclerviewAdapter);
        wrapRecyclerviewAdapter.addHeaderView(LayoutInflater.from(this ).inflate(R.layout.layout_header,recycler_data,false));
        wrapRecyclerviewAdapter.addFooterView(LayoutInflater.from(this ).inflate(R.layout.layout_header,recycler_data,false));
        recycler_data.addItemDecoration(new LinearLayoutItemDecoration(this,R.drawable.item_drivder));
    }
    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <'Z' ; i++) {
            mDatas.add((char) i+"");

        }
    }
}
