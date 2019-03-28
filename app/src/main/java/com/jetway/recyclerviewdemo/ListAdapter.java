package com.jetway.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jetway.recyclerviewdemo.commonAdapter.MulitiSpoort;
import com.jetway.recyclerviewdemo.commonAdapter.RecyclerviewCommonAdapter;
import com.jetway.recyclerviewdemo.commonAdapter.ViewHolder;

import java.util.List;

/**
 * Pachage com.jetway.recyclerviewdemo
 * Email 1351703151@qq.com
 * Auther  JETIPC1
 * Create by Dimen on  2019/3/28
 * Version:1.0
 * Desctiption:
 */
public class ListAdapter extends RecyclerviewCommonAdapter<String> {


    public ListAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_data, s);
    }
}
