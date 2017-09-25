package com.github.sfyc23.asynclistutil_example_java;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;

/**
 * Author :leilei on 2017/9/24 22:28
 */
public class ScrollListener extends  RecyclerView.OnScrollListener {
    private AsyncListUtil listUtil;

    public ScrollListener(AsyncListUtil listUtil) {
        this.listUtil = listUtil;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        listUtil.onRangeChanged();
    }
}
