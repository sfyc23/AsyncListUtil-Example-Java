package com.github.sfyc23.asynclistutil_example_java.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.sfyc23.asynclistutil_example_java.model.Item;

/**
 * Author :leilei on 2017/9/24 20:34
 */
public class SQLiteItemSource implements ItemSource {
    private Cursor cursor;
    private SQLiteDatabase database;

    public SQLiteItemSource(SQLiteDatabase database) {
        this.database = database;
        cursor = database.rawQuery("SELECT title, content FROM data", null);
    }


    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Item getItem(int position) {
        if (cursor == null || cursor.isClosed() != false) {
            cursor = database.rawQuery("SELECT title, content FROM data", null);
        }
        if(cursor.moveToPosition(position)){
            return new Item(cursor.getString(0), cursor.getString(1));
        }
        return new Item("title error", "content error");

    }

    @Override
    public void close() {
        cursor.close();
    }
}
