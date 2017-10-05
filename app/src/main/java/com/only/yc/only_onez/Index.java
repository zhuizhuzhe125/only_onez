package com.only.yc.only_onez;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class Index extends AppCompatActivity {
    //点击头像打开抽屉；
    private ImageView ImageView_Drawer;
    //声明抽屉；
    private DrawerLayout Mdrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //声明控件
        ImageView_Drawer = (ImageView) findViewById(R.id.Title_MyImageView);
        Mdrawer = (DrawerLayout) findViewById(R.id.Index_DrawerLayout_Main);

        ImageView_Drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mdrawer.openDrawer(Gravity.LEFT);
            }
        });
    }
}
