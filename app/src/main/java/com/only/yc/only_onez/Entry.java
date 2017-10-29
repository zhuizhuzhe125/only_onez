package com.only.yc.only_onez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.only.yc.only_sqlite.Contant;
import com.only.yc.only_sqlite.DBManger;
import com.only.yc.only_sqlite.SQLiteHelper;

public class Entry extends AppCompatActivity {
    final Intent intent = new Intent();
    private SQLiteHelper helper;
    private Toolbar mToolBar;
    private Button btn_ok;
    private Button btn_q;
    private EditText User_ID;
    private EditText User_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        helper = DBManger.getIntance(this);

        Too_bar();//标题设置；
        Btn_B();//登录注册按钮设置；

    }

    //登录和注册按钮；
    public void Btn_B() {
        btn_ok = (Button) findViewById(R.id.Entry_Btn_OK);
        btn_q = (Button) findViewById(R.id.Entry_Btn_Q);
        User_ID = (EditText) findViewById(R.id.Entry_Edt_UserName);
        User_Password = (EditText) findViewById(R.id.Entry_Edt_UserPassword);

        // 登录跳转；
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String User_id = User_ID.getText().toString();
                String User_password = User_Password.getText().toString();

                String sql = "select "+ Contant.USER_ID+", "+Contant.USER_PASSWORD+" from "+Contant.TABLE_NAME+" " +
                        "where "+Contant.USER_ID+" = '"+User_id+"' and " +
                        ""+Contant.USER_PASSWORD+" = '"+User_password+"'";
                Cursor cursor1 =DBManger.Select(db,sql,null);
                int InIf = cursor1.getCount();
                if(!"".equals(User_id) && !"".equals(User_password)) {
                    if (InIf != 0) {
                        SharedPreferences.Editor e = getSharedPreferences("date",MODE_PRIVATE).edit();
                        e.putString("token",User_id);
                        e.apply();
                        intent.setClass(Entry.this, Index.class);
                        intent.putExtra("User_id", User_id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Entry.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Entry.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
        // 注册跳转；
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
