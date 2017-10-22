package com.only.yc.only_onez;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.only.yc.only_sqlite.Contant;
import com.only.yc.only_sqlite.DBManger;
import com.only.yc.only_sqlite.SQLiteHelper;



public class Login extends AppCompatActivity {

    final Intent intent = new Intent();
    private SQLiteHelper helper;
    private Toolbar mToolBar;
    private Button btn_ok;
    private Button btn_q;
    private RadioGroup radioGroup_sex;
    private String User_Sex;
    //
    private EditText User_ID;
    private EditText User_Name;
    private EditText User_Password;
    private EditText User_tPassword;
    private EditText User_E_mall;
    private ImageView If_Image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = DBManger.getIntance(this);

        Tob_lar();//设置标题;

        //Sex
        User_Sex();
        User_Login();
        User_if();
        btn_q = (Button) findViewById(R.id.Login_btn_Q);


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

    //用户性别；
    public void User_Sex() {
        radioGroup_sex = (RadioGroup) findViewById(R.id.Login_RadioGroup);
        radioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.Login_Radio_man:
                        User_Sex = "男";
                        break;
                    case R.id.Login_Radio_wem:
                        User_Sex = "女";
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //用户注册；
    public void User_Login() {

        btn_ok = (Button) findViewById(R.id.Login_btn_OK);
        User_ID = (EditText) findViewById(R.id.Login_Edt_UserID);
        User_Name = (EditText) findViewById(R.id.Login_Edt_UserName);
        User_Password = (EditText) findViewById(R.id.Login_Edt_UserPassword);
        User_tPassword = (EditText) findViewById(R.id.Login_Edt_tUserPassword);
        User_E_mall = (EditText) findViewById(R.id.Login_Edt_UserE_mall);

        //注册跳转
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_Id = User_ID.getText().toString();
                String User_name = User_Name.getText().toString();
                String User_password = User_Password.getText().toString();
                String User_tpassword = User_tPassword.getText().toString();
                String User_e_mall = User_E_mall.getText().toString();
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "insert into "+ Contant.TABLE_NAME+" values('"+User_Id+"','"+User_name+"','"+User_password+"'," +
                        "'"+User_e_mall+"','"+User_Sex+"','2016-07-07','', '')";

                if(!"".equals(User_Id) && !"".equals(User_name) && !"".equals(User_password) && !"".equals(User_e_mall)) {
                    if(User_password.equals(User_tpassword)) {
                            DBManger.execSQl(db, sql);
                            db.close();
                            Toast.makeText(Login.this, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "各控件不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //判断用户名是否存在；
    public void User_if() {
        If_Image = (ImageView) findViewById(R.id.Login_Image_IFUser);
        User_ID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String User_Id = User_ID.getText().toString();
                String sql1 = "select "+Contant.USER_ID+" from "+Contant.TABLE_NAME+" where "+Contant.USER_ID+" = '"+User_Id+"' ";
                Cursor cursor1 =DBManger.Select(db,sql1,null);
                int InIf = cursor1.getCount();
                if(!hasFocus) {
                    if(InIf == 0) {
                        If_Image.setImageResource(R.mipmap.if_ok);
                    } else {
                        If_Image.setImageResource(R.mipmap.if_error);
                        Toast.makeText(Login.this,"此用户名不可用",Toast.LENGTH_SHORT).show();
                        btn_ok.setEnabled(false);
                    }
                }
                db.close();
            }
        });
    }
}
