package com.only.yc.only_ImageView;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yc on 2017/10/21.
 */

public class UserSharedPreferences {


    public static final String KEY = "token";
    public static final String APP_ID = "date";

    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY,null);
    }

    public static void cacheToken(Context context,String token) {
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_APPEND).edit();
        e.putString(KEY,token);
        e.commit();
    }
}
