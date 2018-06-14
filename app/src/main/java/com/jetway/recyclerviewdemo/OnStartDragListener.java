package com.jetway.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;

/**
 * Created by JETIPC1 on 2018/6/14.
 */

public interface OnStartDragListener {
    /**
     * 该接口用于需要主动回调拖拽效果的
     * @param viewHolder
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
