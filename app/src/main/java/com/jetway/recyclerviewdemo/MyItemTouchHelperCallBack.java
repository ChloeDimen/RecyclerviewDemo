package com.jetway.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by JETIPC1 on 2018/6/14.
 */

public class MyItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private ItemTouchMoveListener mItemTouchMoveListener;

    public MyItemTouchHelperCallBack(ItemTouchMoveListener itemTouchMoveListener) {
        mItemTouchMoveListener = itemTouchMoveListener;
    }

    //callback回调监听先调用的，用来判断是什么动作，比如判断方向（意思就是我要监听那个方向的拖到）
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //方向Up,down.left,right
        //int swipeFlags = 0;
        //ItemTouchHelper.UP,ItemTouchHelper.DOWN,ItemTouchHelper.LEFT,ItemTouchHelper.RIGHT
        int swipeFlags = ItemTouchHelper.LEFT /*| ItemTouchHelper.RIGHT*/;//添加左右滑动
        int dragFlags=0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//添加上下左右滑动
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//添加上下滑动
        }



        int flags = makeMovementFlags(dragFlags, swipeFlags);


        return flags;
    }

    //当移动的时候回调的方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {//条目布局不同，就不交换位置
            return false;
        }


        //adapter.notifyItemMoved(fromPosition，toPosition);改变拖拽item位置
        mItemTouchMoveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    //侧滑
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //监听侧滑1:删除数据；2：adapter.notifyItemRemove（position）
        mItemTouchMoveListener.onItemRemove(viewHolder.getAdapterPosition());

    }

    //item改变状态
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

        //判断选择状态,改变背景颜色
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorPrimary));

        }



        super.onSelectedChanged(viewHolder, actionState);

    }

    //状态背景颜色复原

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //动画执行完毕

        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        //显示列表删除之后有些条目不出来  ，界面复用问题
        //侧滑和拖拽其实都是在使用ViewCampat这个类,没有作用
        //ViewCompat.setTranslationX(viewHolder.itemView,0);

        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //dx,侧滑改变特效
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //设置透明度1-0
            float result = Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(result);

            //旋转
            viewHolder.itemView.setScaleX(result);
            viewHolder.itemView.setScaleY(result);

        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //是否允许长按拖拽
        return true;
    }


}
