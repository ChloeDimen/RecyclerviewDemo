package com.jetway.recyclerviewdemo.wrap;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Pachage com.jetway.recyclerviewdemo.wrap
 * Author  Demin
 * Create by Dimen on  2019/4/9
 * Version:1.0
 * Describe:
 */
public class WrapRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //数据列表的adapter 不包含头部的
    private RecyclerView.Adapter mAdapter;
    //头部和底部的集合 Arraylist没办法区别  必须用Map集合进行标识   key 是int value 是object 就用sparseArray
    private SparseArray<View> mHeaders, mFooters;

    private static int BASE_HEADER_KEY = 1000000;
    private static int BASE_FOOTER_KEY = 2000000;


    public WrapRecyclerviewAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mHeaders = new SparseArray<>();
        mFooters = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //怎样区分是头部还是底部还是列表  只能根据viewType 可能是头部 底部 中间
        if (mHeaders.indexOfKey(viewType) >= 0) {
            //是头部
            return createHeaderFooterViewHolder(mHeaders.get(viewType));
        } else if (mFooters.indexOfKey(viewType) >= 0) {
            return createHeaderFooterViewHolder(mFooters.get(viewType));

        }

        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 创建头部和底部的ViewHolder
     *
     * @param view
     * @return
     */
    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {

        };
    }

    @Override
    public int getItemViewType(int position) {
        //position--》 viewType  头部1 底部-1 列表0

        if (isHeaderPosition(position)) {
            // 直接返回position位置的key
            return mHeaders.keyAt(position);
        }
        if (isFooterPosition(position)) {
            // 直接返回position位置的key
            position = position - mHeaders.size() - mAdapter.getItemCount();
            return mFooters.keyAt(position);
        }
        // 返回列表Adapter的getItemViewType
        position = position - mHeaders.size();
        return mAdapter.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderPosition(position) || isFooterPosition(position)) {
            return;
        }
        // 计算一下位置
        position = position - mHeaders.size();
        mAdapter.onBindViewHolder(holder, position);


    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mHeaders.size() + mFooters.size();
    }

    /**
     * 是不是底部位置
     */
    private boolean isFooterPosition(int position) {
        return position >= (mFooters.size() + mAdapter.getItemCount());
    }

    /**
     * 是不是头部位置
     */
    private boolean isHeaderPosition(int position) {
        return position < mHeaders.size();
    }
    //方法添加头部底部和移除头部底部

    public void addHeaderView(View view) {

        if (mHeaders.indexOfValue(view) == -1) {
            //集合没有就添加，不要重复添加
            mHeaders.put(BASE_HEADER_KEY++, view);
            notifyDataSetChanged();
        }

    }

    public void removeHeaderView(View view) {

        if (mHeaders.indexOfValue(view) >= 0) {
            //集合没有就添加，不要重复添加
            mHeaders.removeAt(mHeaders.indexOfValue(view));
            notifyDataSetChanged();
        }

    }

    public void addFooterView(View view) {

        if (mFooters.indexOfValue(view) == -1) {
            //集合没有就添加，不要重复添加
            mFooters.put(BASE_FOOTER_KEY++, view);
            notifyDataSetChanged();
        }

    }

    public void removeFooterView(View view) {

        if (mFooters.indexOfValue(view) >= 0) {
            //集合没有就添加，不要重复添加
            mFooters.removeAt(mFooters.indexOfValue(view));
            notifyDataSetChanged();
        }

    }
}
