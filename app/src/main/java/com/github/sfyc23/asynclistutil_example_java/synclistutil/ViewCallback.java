package com.github.sfyc23.asynclistutil_example_java.synclistutil;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Author :leilei on 2017/9/24 22:09
 */
public class ViewCallback extends AsyncListUtil.ViewCallback{

    private RecyclerView recyclerView;
    public ViewCallback(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void getItemRangeInto(int[] outRange) {
        if (outRange == null) {
            return;
        }
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
            outRange[0] = llm.findFirstVisibleItemPosition();
            outRange[1] = llm.findLastVisibleItemPosition();
        }
        if (outRange[0] == -1 && outRange[1] == -1) {
            outRange[0] = 0;
            outRange[1] = 0;
        }
    }

    @Override
    public void onDataRefresh() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemLoaded(int position) {
        recyclerView.getAdapter().notifyItemChanged(position);
    }
}
