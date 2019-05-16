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
    private WrapRecyclerviewAdapter mWrapRecyclerAdapter;


    // 增加一些通用功能
    // 空列表数据应该显示的空View
    // 正在加载数据页面，也就是正在获取后台接口页面
    private View mEmptyView, mLoadingView;
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
            /*super.onChanged();
            mAdapter.notifyDataSetChanged();*/
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyDataSetChanged没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyDataSetChanged();

            dataChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
           // mAdapter.notifyItemChanged(positionStart,itemCount);
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemChanged没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyItemChanged(positionStart);
            dataChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            //mAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemChanged没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyItemChanged(positionStart, payload);
            dataChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
          // mAdapter.notifyItemRangeInserted(positionStart,itemCount);
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemInserted没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyItemInserted(positionStart);
            dataChanged();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            //mAdapter.notifyItemRangeRemoved(positionStart,itemCount);
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyDataSetChanged没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyItemRemoved(positionStart);
            dataChanged();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
           // mAdapter.notifyItemMoved(fromPosition,toPosition);
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemMoved没效果
            if (mWrapRecyclerAdapter != mAdapter)
                mWrapRecyclerAdapter.notifyItemMoved(fromPosition, toPosition);
            dataChanged();
        }
    };
    /**
     * 添加一个空列表数据页面
     */
    public void addEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    /**
     * 添加一个正在加载数据的页面
     */
    public void addLoadingView(View loadingView) {
        this.mLoadingView = loadingView;
    }

    /**
     * Adapter数据改变的方法
     */
    private void dataChanged() {
        if (mAdapter.getItemCount() == 0) {
            // 没有数据
            if (mEmptyView != null) {
                mEmptyView.setVisibility(VISIBLE);
            } else {
                mEmptyView.setVisibility(GONE);
            }
        }
    }
}
