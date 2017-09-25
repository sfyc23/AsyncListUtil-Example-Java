package com.github.sfyc23.asynclistutil_example_java;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sfyc23.asynclistutil_example_java.db.ItemSource;
import com.github.sfyc23.asynclistutil_example_java.model.Item;
import com.github.sfyc23.asynclistutil_example_java.synclistutil.DataCallback;
import com.github.sfyc23.asynclistutil_example_java.synclistutil.ViewCallback;

/**
 * Author :leilei on 2017/9/24 21:57
 */
public class AsyncAdapter extends RecyclerView.Adapter<AsyncAdapter.ViewHolder> {
    private ItemSource itemSource;
    private RecyclerView recyclerView;

    private DataCallback dataCallback;
    private AsyncListUtil<Item> listUtil;
    private ScrollListener onScrollListener;

    public AsyncAdapter(ItemSource itemSource, RecyclerView recyclerView) {
        this.itemSource = itemSource;
        this.recyclerView = recyclerView;
        dataCallback = new DataCallback(itemSource);
        listUtil = new AsyncListUtil(Item.class, 5, dataCallback, new ViewCallback(recyclerView));
        onScrollListener = new ScrollListener(listUtil);
    }

    public void onStart(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(onScrollListener);
        listUtil.refresh();
    }

    public void onStop(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(onScrollListener);
        dataCallback.close();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(listUtil.getItem(position),position);
    }

    @Override
    public int getItemCount() {
        return listUtil.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }

        public void bindView(Item item, Integer position) {
            if (item == null) {
                return;
            }
            title.setText(position + " : " + item.title);
            content.setText(item.content != null ? item.content : "loading");
        }
    }
}
