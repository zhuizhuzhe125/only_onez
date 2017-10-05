package com.only.yc.only_onez;


import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class Index extends AppCompatActivity {
    //点击头像打开抽屉；
    private ImageView ImageView_Drawer;
    private ImageView ImageView_Menu;
    //声明抽屉；
    private DrawerLayout Mdrawer;

    //启动界面设置；


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //声明控件
        ImageView_Drawer = (ImageView) findViewById(R.id.Title_MyImageView);
        Mdrawer = (DrawerLayout) findViewById(R.id.Index_DrawerLayout_Main);
        //声明菜单健；
        ImageView_Menu = (ImageView) findViewById(R.id.Title_ImageView_Add);

        //对应事件;
        ImageView_Drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mdrawer.openDrawer(Gravity.LEFT);
            }
        });

        ImageView_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Index.this,ImageView_Menu);
                popup.getMenuInflater().inflate(R.menu.mian, popup.getMenu());
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {

//                    }
//                });

                popup.show();
            }
        });


    }
}
