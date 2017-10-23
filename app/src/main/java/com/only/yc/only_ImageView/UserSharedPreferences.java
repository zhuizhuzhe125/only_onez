package com.only.yc.only_ImageView;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yc on 2017/10/21.
 */

public class UserSharedPreferences {

    public  static SharedPreferences.Editor e;
    public static final String KEY = "token";
    public static final String KEY_one = "key_one";
    public static final String KEY_two = "key_two";
    public static final String APP_ID = "date";

    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY,null);
    }

    public static void cacheToken(Context context,String token) {
        e = context.getSharedPreferences(APP_ID, Context.MODE_APPEND).edit();
        e.putString(KEY,token);
        e.commit();
    }
    public static void UserName(Context context,String token) {
        e = context.getSharedPreferences(APP_ID, Context.MODE_APPEND).edit();
        e.putString(KEY_one,token);
        e.commit();
    }

    public static String UserName(Context context) {
        return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_one,null);
    }

    public static void UserAutograph(Context context,String token) {
        e = context.getSharedPreferences(APP_ID, Context.MODE_APPEND).edit();
        e.putString(KEY_two,token);
        e.commit();
    }

    public static String UserAutograph(Context context) {
        return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_two,null);
    }

    public static void ClearData() {
        e.clear().commit();
    }

    /*public static String seveUserInfo(Context context, String UserName, String UserAutograph) {
        SharedPreferences sp = context.getSharedPreferences("APP_ID",Context.MODE_PRIVATE);
        e = sp.edit();
        e.putString("UserName_one",UserName);
        e.putString("UserAutograph_one",UserAutograph);
        e.commit();

    }

    public static Map<String, String>  getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("APP_ID",Context.MODE_PRIVATE);
        String Username_One =sp.getString("UserName_one",null);
        String UserAutograph_One = sp.getString("UserAutograph_one",null);
        Map<String, String> UserMap = new HashMap<String,String>();
        UserMap.put("Username_One",Username_One);
        UserMap.put("UserAutograph_One",UserAutograph_One);
        return UserMap;
    }
    */

}
