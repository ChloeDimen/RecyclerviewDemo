package com.jetway.recyclerviewdemo;

/**
 * Created by JETIPC1 on 2018/6/14.
 */

public interface ItemTouchMoveListener {
    /**
     * 当拖拽的时候回调
     * 可以在此方法实现拖拽条目，并实现刷新效果
     * @param fromPosition 从什么位置拖拽
     * @param toPosition 到什么位置
     * @return  是否执行
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * 当条目被移除的回调
     * @param position
     * @return
     */
    boolean onItemRemove( int position);
}
