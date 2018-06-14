package com.jetway.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by JETIPC1 on 2018/6/14.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements ItemTouchMoveListener{
    private Context mContext;
    private List<String> number;
    private OnStartDragListener mListener;

    public MyRecyclerViewAdapter(Context context, List<String> number, OnStartDragListener listener) {
        mContext = context;
        this.number = number;
        this.mListener = listener;
    }
   //在adapter里面加ItemTouchMoveListener和在MainActivity加有什么区别？
   @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //1:数据交换；  2：刷新

        Collections.swap(number,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return false;
    }

     @Override
    public boolean onItemRemove(int position) {
        //1:删除数据；  2：刷新
        number.remove(position);
        notifyItemRemoved(position);
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_data;
        private ImageView iv_data;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.tv_data);
            iv_data = itemView.findViewById(R.id.iv_data);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_data.setText(number.get(position));
        holder.iv_data.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //传递触摸情况给谁，进行按下图片拖拽
                    mListener.onStartDrag(holder);
                }

                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return number.size();
    }


}

