package com.github.sfyc23.asynclistutil_example_java.db;

import com.github.sfyc23.asynclistutil_example_java.model.Item;

/**
 * Author :leilei on 2017/9/24 20:30
 */
public interface ItemSource {
    int getCount();

    Item getItem(int position);

    void close();
}
