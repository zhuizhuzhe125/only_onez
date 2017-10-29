package com.only.yc.only_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/10/16.
 */

public class DBManger {
    private static SQLiteHelper sqLiteHelper;
    public static SQLiteHelper getIntance(Context context) {
        if(sqLiteHelper == null) {
            sqLiteHelper = new SQLiteHelper(context);
        }
        return sqLiteHelper;
    }

    /**
     *  根据sql语句在数据库中执行语句；
     * @param db 数据库对象；
     * @param sql sql语句；
     */

    public static void execSQl(SQLiteDatabase db, String sql) {
        if(db != null) {
            if(sql != null && !"".equals(sql)) {
                db.execSQL(sql);
            }
        }

    }
    //查询数据；
    public static Cursor Select (SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if(db != null) {
            cursor =db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }
    //查询数据；
    public static List<Person> GetList(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String User_ID = cursor.getString(cursor.getColumnIndex(Contant.USER_ID));
            String User_Name = cursor.getString(cursor.getColumnIndex(Contant.USER_NAME));
            //String User_Password = cursor.getString(cursor.getColumnIndex(Contant.USER_PASSWORD));
            String User_E_mall = cursor.getString(cursor.getColumnIndex(Contant.USER_E_MALL));
            String User_Autograph = cursor.getString(cursor.getColumnIndex(Contant.USER_AUTOGRAPH));
            //String User_Image = cursor.getString(cursor.getColumnIndex(Contant.USER_IMAGE));
            String User_Sex = cursor.getString(cursor.getColumnIndex(Contant.USER_SEX));
            String User_Birthday = cursor.getString(cursor.getColumnIndex(Contant.USER_BIRTHDAY));

            Person person = new Person(User_ID, User_Name, User_E_mall, User_Sex,
                    User_Birthday, User_Autograph);
            list.add(person);
        }
        return list;
    }
    //登录初始化用户；
    public static List<Person> Show_List(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String User_ID = cursor.getString(cursor.getColumnIndex(Contant.USER_ID));
            String User_Name = cursor.getString(cursor.getColumnIndex(Contant.USER_NAME));
            String User_Autograph = cursor.getString(cursor.getColumnIndex(Contant.USER_AUTOGRAPH));

            Person person = new Person(User_ID, User_Name, User_Autograph);
            list.add(person);
        }
        return list;
    }
}
