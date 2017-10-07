package com.only.yc.only_onez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    final Intent intent = new Intent();

    private Toolbar mToolBar;
    private Button btn_ok;
    private Button btn_q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Tob_lar();//设置标题;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //标题设置；
    public void Tob_lar() {
        mToolBar = (Toolbar) findViewById(R.id.Login_Toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
