package com.only.yc.only_onez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Entry extends AppCompatActivity {
    private Button btn_ok;
    private Button btn_q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        final Intent intent = new Intent();
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
}
