package com.only.yc.only_onez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Splash extends AppCompatActivity {
    private String token;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences pref = getSharedPreferences("date",MODE_PRIVATE);
            token = pref.getString("token","");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(token.equals("")) {
                    intent.setClass(Splash.this,Entry.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(Splash.this, Index.class);
                    intent.putExtra("User_id",token);
                    startActivity(intent);
                }
                finish();
            }
        }, 1500);
    }
}
