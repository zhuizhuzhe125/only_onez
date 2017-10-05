package com.only.yc.only_onez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button btn_ok;
    private Button btn_q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent intent = new Intent();
        btn_ok = (Button) findViewById(R.id.Login_btn_OK);
        btn_q = (Button) findViewById(R.id.Login_btn_Q);
        //注册跳转
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //登录跳转
        btn_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Login.this,Entry.class);
                startActivity(intent);
            }
        });
    }
}
