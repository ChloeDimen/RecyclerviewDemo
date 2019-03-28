package com.jetway.recyclerviewdemo.commonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 通用的ViewHolder
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mSparseArray;

    public ViewHolder(View itemView) {
        super(itemView);
        mSparseArray = new SparseArray<>();
    }

    /**
     * 通过id找view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找
        View view = mSparseArray.get(viewId);
        if (view == null) {
            // 直接从ItemView中找,减少findViewByIds
            view = itemView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        return (T) view;

    }

    /**
     * 设置文字
     *
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text) {

        TextView textview = getView(viewId);
        textview.setText(text);
        return this;
    }

    /**
     * 设置控件是否显示
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolder setVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 设置图片资源
     *
     * @param viewId
     * @param resourceId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resourceId) {

        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    //图片处理 路径问题 用第三方的图片加载 Glide picasso
    //用自己的一套规范 HolderImageLoader
    public ViewHolder setImagePath(int viewId, HolderImageLoader holderimageLoader) {
        ImageView imageView = getView(viewId);
        if (holderimageLoader == null) {
            throw new NullPointerException("holderimageLoader is null");
        }
        holderimageLoader.loadImage(imageView.getContext(), imageView, holderimageLoader.getImagePath());
        return this;
    }


    public static abstract class HolderImageLoader {
        private String mPath;

        public HolderImageLoader(String path) {
            mPath = path;
        }

        public String getImagePath() {
            return mPath;
        }

        public abstract void loadImage(Context context, ImageView imageView, String path);
    }

    /**
     * 点击事件
     *
     * @param listener
     */
    public void setOnIntemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);

    }

    /**
     * 长按事件
     *
     * @param listener
     */
    public void setOnIntemLongClicListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);

    }
}
