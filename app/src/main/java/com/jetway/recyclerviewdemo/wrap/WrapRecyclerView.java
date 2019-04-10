package com.jetway.recyclerviewdemo.wrap;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Pachage com.jetway.recyclerviewdemo.wrap
 * Author  Demin
 * Create by Dimen on  2019/4/9
 * Version:1.0
 * Describe:
 */
public class WrapRecyclerView extends RecyclerView {
    private WrapRecyclerviewAdapter mAdapter;

    public WrapRecyclerView(Context context) {
        this(context, null);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {


        if (adapter instanceof WrapRecyclerviewAdapter) {
            mAdapter = (WrapRecyclerviewAdapter) adapter;
        } else {
            mAdapter = new WrapRecyclerviewAdapter(adapter);
            adapter.registerAdapterDataObserver(mAdapterDataObserver);
        }
        //删除的问题列表的adapter的改变了，但是WrapRecyclerviewadapter并没有改变，所有需要关联  观察者模式
        super.setAdapter(adapter);
    }
    //方法添加头部底部和移除头部底部

    public void addHeaderView(View view) {

        if (mAdapter != null) {
            mAdapter.addHeaderView(view);
        }
    }

    public void removeHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.removeHeaderView(view);
        }

    }

    public void addFooterView(View view) {

        if (mAdapter != null) {
            mAdapter.addFooterView(view);
        }

    }

    public void removeFooterView(View view) {
        if (mAdapter != null) {
            mAdapter.removeFooterView(view);
        }


    }

    private AdapterDataObserver mAdapterDataObserver=new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mAdapter.notifyItemChanged(positionStart,itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
           mAdapter.notifyItemRangeInserted(positionStart,itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeRemoved(positionStart,itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mAdapter.notifyItemMoved(fromPosition,toPosition);
        }
    };
}
