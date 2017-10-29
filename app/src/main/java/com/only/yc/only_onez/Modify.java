package com.only.yc.only_onez;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.only.yc.only_sqlite.Contant;
import com.only.yc.only_sqlite.DBManger;
import com.only.yc.only_sqlite.Person;
import com.only.yc.only_sqlite.SQLiteHelper;

import java.util.List;

public class Modify extends AppCompatActivity {
    private final int QINGQIUMA = 1996;
    private Toolbar Mtoolbar;
    private EditText UserID;
    private EditText UserName;
    private EditText UserE_mall;
    private TextView UserSex;
    private TextView UserBirthdaY;
    private TextView UserAutograph;
    private Button Modify_one;
    final Intent intent = new Intent();
    private  SQLiteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);


        helper = DBManger.getIntance(this);

        Modify_Toobar();
        // 控件声明；
        init();
        initShow();
        // 生日跳转；
        initBirthday();
        //修改信息；
        initOK();
    }

    public void Modify_Toobar() {
        Mtoolbar = (Toolbar) findViewById(R.id.Modify_Toolbar);
        setSupportActionBar(Mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void init() {
        UserID = (EditText) findViewById(R.id.Modify_Edit_UserId);
        UserName = (EditText) findViewById(R.id.Modify_Edit_UserName);
        UserE_mall = (EditText) findViewById(R.id.Modify_Edit_UserE_mall);
        UserSex = (TextView) findViewById(R.id.Modify_Edit_UserSex);
        UserBirthdaY = (TextView) findViewById(R.id.Modify_Edit_UserAge);
        Modify_one = (Button) findViewById(R.id.Modify_Button_Modify);
        UserAutograph = (EditText) findViewById(R.id.Modify_Edit_UserTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case QINGQIUMA:
                if(resultCode == RESULT_OK) {
                    String Birthday = data.getStringExtra("Birth_Day");
                    UserBirthdaY.setText(Birthday);
                }
                break;
        }
    }

    public void initBirthday() {
        UserBirthdaY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Modify.this,UserBirthday.class);
                startActivityForResult(intent,QINGQIUMA);
            }
        });

    }

    public void initShow() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Intent inte = getIntent();
        String User_ID = inte.getStringExtra("USER_ID");
        String sql = "select "+ Contant.USER_ID+", "+ Contant.USER_NAME+", "+Contant.USER_AUTOGRAPH+"," +
                ""+Contant.USER_E_MALL+", "+Contant.USER_SEX+", "+Contant.USER_BIRTHDAY+"" +
                " from "+Contant.TABLE_NAME+" " +
                " where "+Contant.USER_ID+" = '"+User_ID+"'";
        Cursor cursor = DBManger.Select(db, sql, null);
        List<Person> list = DBManger.GetList(cursor);
        for(Person p:list) {
            UserID.setText(p.getUserID());
            UserName.setText(p.getUserName());
            UserE_mall.setText(p.getUserE_mall());
            UserSex.setText(p.getUserSex());
            UserBirthdaY.setText(p.getUserBirthday());
            UserAutograph.setText(p.getUserAutograph());
        }
        UserID.setEnabled(false);
    }

    public void initOK() {
        final SQLiteDatabase db = helper.getWritableDatabase();
        Intent inte = getIntent();
        final String User_ID = inte.getStringExtra("USER_ID");
        Modify_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_Name = UserName.getText().toString();
                String User_Emall = UserE_mall.getText().toString();
                String User_birthday = UserBirthdaY.getText().toString();
                String User_Autograph = UserAutograph.getText().toString();
                String SQL = "UPDATE "+Contant.TABLE_NAME+" SET "+Contant.USER_NAME+" = '"+User_Name+"', " +
                        ""+Contant.USER_E_MALL+" = '"+User_Emall+"', "+Contant.USER_BIRTHDAY+" = '"+User_birthday+"', " +
                        ""+Contant.USER_AUTOGRAPH+" = '"+User_Autograph+"' WHERE "+Contant.USER_ID+" = '"+User_ID+"' ";
                db.execSQL(SQL);
                db.close();
                Toast.makeText(Modify.this,"修改成功",Toast.LENGTH_SHORT).show();
                intent.setClass(Modify.this, Index.class);
                intent.putExtra("User_id",User_ID);
                startActivity(intent);
                initShow();
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

}
