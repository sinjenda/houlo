package com.sin.lifesim.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sin.lifesim.Krmic;

import java.util.ArrayList;

public class databaseZamestnani extends SQLiteOpenHelper {
    public static String name = "zamestnani";
    public static String create = "CREATE TABLE " + name + "  ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,money INTEGER, requiredKnowlage INTEGER , type TEXT, school TEXT,none TEXT)";

    public databaseZamestnani(Context ctx) {
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

    public Object select(String[] columns) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(name, columns, null, null, null, null, null);
        ArrayList<Integer> ret = new ArrayList<>();
        while (!cursor.isLast()) {
            ret.add(cursor.getInt(0));
        }
        return Krmic.poleConverter(Krmic.poleConverter(Krmic.polepull(ret)));
    }

    @SuppressWarnings("StringConcatenationInLoop")
    public void insert(String[] strings) {
        String save = "";
        int i = 0;
        String last = "";
        for (String s : strings) {
            save = save + s + " ";
            i++;
        }
        save = save + " )";
        getWritableDatabase().execSQL("INSERT INTO " + name + " VALUES( " + save + " " + last + "none )");
        getWritableDatabase().execSQL("INSERT INTO " + name + " VALUES(save)");
    }
}
