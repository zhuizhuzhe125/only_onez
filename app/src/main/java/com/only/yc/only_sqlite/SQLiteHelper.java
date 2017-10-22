package com.only.yc.only_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yc on 2017/10/16.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(Context context) {
        super(context, Contant.DATABASE_NAME, null, Contant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+Contant.TABLE_NAME+" (" +
                ""+Contant.USER_ID+" varchar(16) primary key, " +
                ""+Contant.USER_NAME+" varchar(10) not null, " +
                ""+Contant.USER_PASSWORD+" varchar(16) not null, " +
                ""+Contant.USER_E_MALL+" varchar(20) not null, " +
                ""+Contant.USER_SEX+" varchar(4) not null, " +
                ""+Contant.USER_BIRTHDAY+" varchar(15) not null, " +
                ""+Contant.USER_IMAGE+" varchar(20) not null, " +
                ""+Contant.USER_AUTOGRAPH+" varchar(100)) ";
        db.execSQL(sql);

        Log.v("tag","----111----");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("tag","----222----");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.v("tag","----333----");
    }
}
