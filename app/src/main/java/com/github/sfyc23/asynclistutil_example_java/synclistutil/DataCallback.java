package com.github.sfyc23.asynclistutil_example_java.synclistutil;

import android.support.v7.util.AsyncListUtil;

import com.github.sfyc23.asynclistutil_example_java.db.ItemSource;
import com.github.sfyc23.asynclistutil_example_java.model.Item;

/**
 * Author :leilei on 2017/9/24 22:08
 */
public class DataCallback extends AsyncListUtil.DataCallback<Item> {
    private ItemSource itemSource;

    public DataCallback(ItemSource itemSource) {
        this.itemSource = itemSource;
    }

    @Override
    public int refreshData() {
        return itemSource.getCount();
    }

    @Override
    public void fillData(Item[] data, int startPosition, int itemCount) {
        if (data == null) {
            return;
        }
        for (int i = 0; i < itemCount; i++) {
//            Log.e("itemCount:",itemCount+"");
            data[i] = itemSource.getItem(startPosition + i);
        }
    }
    public void close(){
        itemSource.close();
    }
}
