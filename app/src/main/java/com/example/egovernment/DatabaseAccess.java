package com.example.egovernment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAccess extends SQLiteAssetHelper {
    private static String DATABASE_NAME = "myDatabase.db";
    private static int DataBase_version = 1;

    SQLiteDatabase db;

    public DatabaseAccess(Context context) {
        super(context, DATABASE_NAME, null, DataBase_version);
        db = getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
