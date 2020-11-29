package com.dd.dastur;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseAccess extends AppCompatActivity {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    //mainNames main	tarbie	otau	otbasi	enbek	nauriz	islam	kaza
    public List<String> getList(int columnNumber, String tableName) {
        List<String> list = new ArrayList<>();
        String sqlQueryText = "SELECT * FROM " + tableName;
        Cursor cursor = database.rawQuery(sqlQueryText, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            list.add("  " + cursor.getString(columnNumber));
            cursor.moveToNext();
        }
        cursor.close();
        list.removeAll(Arrays.asList("  ", null));
        list.add("");
        return list;
    }
}
