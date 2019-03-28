package com.jetway.recyclerviewdemo.commonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 通用的recyclerview的adapter
 */

public abstract class RecyclerviewCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;

    private MulitiSpoort<T> mTMulitiSpoort;

    public RecyclerviewCommonAdapter(Context context, List<T> datas, MulitiSpoort<T> TMulitiSpoort) {
        this(context, datas, -1);
        this.mTMulitiSpoort = TMulitiSpoort;
    }

    public RecyclerviewCommonAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
        mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mTMulitiSpoort != null) {
            mLayoutId = viewType;
            Log.e("sss", "onCreateViewHolder: "+viewType+"");
        }
        Log.e("sss", "onCreateViewHolder: "+mLayoutId+"");
        View view = mInflater.inflate(mLayoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTMulitiSpoort != null) {
          return   mTMulitiSpoort.getLayoutId(mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClickListener(position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return mOnItemLongClickListener.OnItemLongClickListener(position);

                }
            });
        }
        convert(holder, mDatas.get(position), position);

    }

    public abstract void convert(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private ItemClickListener mOnItemClickListener;
    private ItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }


}
