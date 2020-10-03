package com.sin.lifesim.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sin.lifesim.Krmic;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public static String name = "me";
    public static String create = "CREATE TABLE " + name + "  ( _id INTEGER PRIMARY KEY AUTOINCREMENT, money INTEGER )";

    public database(Context ctx) {
        super(ctx, "poznamky", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entity");
        onCreate(db);
    }

    public int[] select(String[] columns) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(name, columns, null, null, null, null, null);
        ArrayList<Integer> ret = new ArrayList<>();
        while (!cursor.isLast()) {
            ret.add(cursor.getInt(0));
        }
        return Krmic.poleConverter(Krmic.poleConverter(Krmic.polepull(ret)));
    }
}
