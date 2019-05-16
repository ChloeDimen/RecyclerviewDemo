package com.jetway.recyclerviewdemo.refresh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Package com.jetway.recyclerviewdemo.refresh
 * Author  Dimen
 * Create by Dimen on  2019/5/14
 * Version:1.0
 * Describe:下拉刷新的辅助类为了匹配所有效果
 */
public abstract class RefreshViewCreator {
    /**
     * 获取下拉刷新的view
     * @param context
     * @param parent
     * @return
     */

   public abstract View getRefreshView(Context context, ViewGroup parent);

    /**
     * 正在下拉
     * @param currentDragHeight   当前拖动的高度
     * @param refreshViewHeight  总的刷新高度
     * @param currentRefreshStatus 当前状态
     */
    public abstract void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus);
    /**
     * 正在刷新中
     */
    public abstract void onRefreshing();

    /**
     * 停止刷新
     */
    public abstract void onStopRefresh();
}
