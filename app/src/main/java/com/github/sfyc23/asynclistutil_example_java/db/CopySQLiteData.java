package com.github.sfyc23.asynclistutil_example_java.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author :leilei on 2017/9/24 19:00
 */
public class CopySQLiteData {
    public final static String ASSET_FILE_NAME = "database.sqlite";
    private static SQLiteDatabase database;

    public static SQLiteDatabase getDatabase(Context context, String assetFileName) {
        if (database == null) {
            try {
                InputStream input = context.getApplicationContext().getAssets().open(assetFileName);
                File outFile = new File(context.getFilesDir(), assetFileName);
                if (outFile.exists()) {
                    outFile.delete();
                } else {
                    outFile.mkdirs();
                }
                copyToFile(input, outFile);
                database = SQLiteDatabase.openOrCreateDatabase(outFile, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    public static void copyToFile(InputStream inputStream, File outputFile) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024 * 8];
            int numOfBytesToRead = inputStream.read(buffer);
            while (numOfBytesToRead > 0) {
                outputStream.write(buffer, 0, numOfBytesToRead);
                numOfBytesToRead = inputStream.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
