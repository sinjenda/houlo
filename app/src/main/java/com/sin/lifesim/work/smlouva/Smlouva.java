package com.sin.lifesim.work.smlouva;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sin.lifesim.work.smlouva.podminka.Podminka;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;


public class Smlouva {


    String title;
    String podminky;
    int zkusenost;
    Podminka podminka;
    Context ctx;
    smlouvaSave save;

    public int getZkusenost() {
        return zkusenost;
    }


    public String getTitle() {
        return title;
    }


    public String getPodminky() {
        return podminky;
    }


    public Smlouva(@NotNull String title, String podminky, int zkusenost, Context ctx) {
        this.title = title;
        this.podminky = podminky;
        this.zkusenost = zkusenost;
        podminka = new Podminka();
        this.ctx = ctx;
        Papir papir = new Papir();
        Smlouva s;
        save = new smlouvaSave(ctx);
    }

    public Smlouva(String title, int zkusenost) {
        podminka = new Podminka();
        podminky = podminka.generate(ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2));
    }

    public boolean test(Smlouva smlouva) {
        return podminka.test(podminky, smlouva);
    }

    public void send(int hours) {
        podminka.setWorktime(podminka.getWorktime() + hours);
    }


    @SuppressWarnings("InnerClassMayBeStatic")
    private class smlouvaSave extends SQLiteOpenHelper {

        private class saveError extends Exception {
            public saveError() {
                super();
            }

            public saveError(String msg) {
                super(msg);
            }

            public saveError(Throwable cause) {
                super(cause);
            }

            public saveError(String msg, Throwable cause) {
                super(msg, cause);
            }


        }


        SQLiteDatabase db;

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE smlouvy " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " title TEXT," +
                    "podminky TEXT," +
                    "zkusenost INTEGER," +
                    "podminka BLOB" +
                    ");");
            this.db = db;
        }

        public smlouvaSave(Context ctx) {
            super(ctx, "smlouvy", null, 1);

        }

        public String getTitle(int id) throws saveError {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT title FROM smlouvy WHERE _id =" + id, null);
            if (cursor.moveToFirst()) {
                return cursor.getString(1);
            } else {
                throw new saveError("id doesn't exist");
            }
        }

        public String getPodminky(int id) throws saveError {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT podminky FROM smlouvy WHERE _id =" + id, null);
            if (cursor.moveToFirst()) {
                return cursor.getString(1);
            } else
                throw new saveError("id does not exist");
        }

        public int getZkusenost(int id) throws saveError {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT zkusenost FROM smlouvy WHERE _id=" + id, null);
            if (cursor.moveToFirst()) {
                return cursor.getInt(1);
            } else throw new saveError("id does not exist");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            throw new UnsupportedOperationException("do not use this");
        }

        public void save(String title, String podminky, int zkusenost, Podminka podminka) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO smlouvy (_id,title,podminky,zkusenost,podminka)" +
                    "VALUES (null," + title + "," + podminky + "," + zkusenost + "," + podminka + ");");
        }

        public SQLiteDatabase getDb() {
            return db;
        }

    }
}
