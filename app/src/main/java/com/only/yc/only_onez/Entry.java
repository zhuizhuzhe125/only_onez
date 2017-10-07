package com.only.yc.only_onez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Entry extends AppCompatActivity {
    final Intent intent = new Intent();
    private Toolbar mToolBar;
    private Button btn_ok;
    private Button btn_q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Too_bar();//标题设置；
        Btn_B();//登录注册按钮设置；

    }

    //登录和注册按钮；
    public void Btn_B() {
        btn_ok = (Button) findViewById(R.id.Entry_Btn_OK);
        btn_q = (Button) findViewById(R.id.Entry_Btn_Q);

        // 注册跳转；
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Entry.this,Index.class);
                startActivity(intent);
            }
        });
        // 登录跳转；
        btn_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Entry.this,Login.class);
                startActivity(intent);
            }
        });
    }

    //设置标题;
    public void Too_bar() {
        mToolBar = (Toolbar) findViewById(R.id.Entry_Toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
