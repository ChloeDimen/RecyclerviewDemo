package com.jetway.recyclerviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GradLayoutItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public GradLayoutItemDecoration(Context context, int drawableResId) {
        mDrawable = ContextCompat.getDrawable(context, drawableResId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //绘制
        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

    /**
     * 绘制水平方向
     *
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - layoutParams.leftMargin;
            int right = childAt.getRight() + mDrawable.getIntrinsicWidth() + layoutParams.rightMargin;
            int top = childAt.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            //计算水平分割线的位置
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    /**
     * 绘制垂直方向
     *
     * @param c
     * @param parent
     */

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getRight() + layoutParams.rightMargin;
            int right = left + mDrawable.getIntrinsicWidth();
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            //计算水平分割线的位置
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //留出分割线,会多出最右边和最下边的分割线
        //outRect.bottom = mDrawable.getIntrinsicHeight();
        //outRect.right = mDrawable.getIntrinsicWidth();
        int bottom = mDrawable.getIntrinsicHeight();
        int right = mDrawable.getIntrinsicWidth();

        if (isLastCloumn(view, parent)) {//最后一列   当前位置%列数==0
            right = 0;

        }
        if (isLastRow(view, parent)) {//最后一行
            bottom = 0;

        }
        outRect.right = right;
        outRect.bottom = bottom;
    }

    private boolean isLastRow(View view, RecyclerView parent) {
        //列数
        int spanCount = getSpanCount(parent);
        //行数=总的条目/列表
        int rowNumber = parent.getAdapter().getItemCount() % spanCount == 0 ?
                parent.getAdapter().getItemCount() / spanCount : (parent.getAdapter().getItemCount() / spanCount + 1);
        //当前位置
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        //当前的位置+1》（行数-1）*列数
        return (currentPosition + 1) > (rowNumber - 1) * spanCount;



    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            return spanCount;
        }
        return 1;
    }

    private boolean isLastCloumn(View view, RecyclerView parent) {
        //当前位置
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        return (currentPosition + 1) % spanCount == 0;
    }


}
