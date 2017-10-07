package com.only.yc.only_onez;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity implements View.OnClickListener {
    Intent intent = new Intent();
    //点击头像打开抽屉；
    private ImageView ImageView_Drawer;
    private ImageView ImageView_Menu;
    //声明抽屉；
    private DrawerLayout Mdrawer;

    // 抽屉点击事件
    private NavigationView navigationView;
    // viewpager
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<View> mViews = new ArrayList<View>();
    private  ImageButton Bmessage;
    private  ImageButton Bfriend;
    private LinearLayout lMessage_board;
    private LinearLayout lFriend;

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

        Boar_O();// 标题栏相应事件；
        NavigationView(); //抽屉相应事件；
        initView();
        initEvent();
        initPage();

    }
    //标题栏相应事件
    public void Boar_O() {

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
//                     标签点击事件；
//                    }
//                });

                popup.show();
            }
        });

    }
    // 抽屉相应事件；
    public void NavigationView() {
        // 抽屉点击事件;
        navigationView = (NavigationView) findViewById(R.id.Index_Nav);

        //抽屉点击事件;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.drawer_menu_Login:
                        intent.setClass(Index.this,Login.class);
                        startActivity(intent);
                        break;
                    case  R.id.drawer_menu_Entry:
                        intent.setClass(Index.this,Entry.class);
                        startActivity(intent);
                        break;
                    case R.id.drawer_menu_Revise:
                        Toast.makeText(Index.this, "您点击了修改信息", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.drawer_menu_Setting:
                        Toast.makeText(Index.this, "您点击了设置", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.drawer_menu_UserAdd:
                        Toast.makeText(Index.this, "您点击了添加好友", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.drawer_menu_Contact:
                        Toast.makeText(Index.this, "您点击了联系我们呢", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    //pager 滑动；
    public void initEvent() {
        lMessage_board.setOnClickListener(this);
        lFriend.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = mViewPager.getCurrentItem();
                Image_n();
                switch (currentItem) {
                    case 0:
                        Bmessage.setImageResource(R.mipmap.message_board_one);
                        break;
                    case 1:
                        Bfriend.setImageResource(R.mipmap.friend_one);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    // 初始化；
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.Index_ViewPager);
        lMessage_board = (LinearLayout) findViewById(R.id.Bottom_Linear_Message_board);
        lFriend = (LinearLayout) findViewById(R.id.Bottom_Linear_Friend);
        Bmessage = (ImageButton) findViewById(R.id.Bottom_Button_MessageBoard);
        Bfriend = (ImageButton) findViewById(R.id.Bottom_Button_Friend);
    }
    //初始化pager；
    public void initPage() {
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        mViews.add(mLayoutInflater.inflate(R.layout.viewpager_messageboard,null));
        mViews.add(mLayoutInflater.inflate(R.layout.viewpager_friend,null));

        mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view == object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

        };
        mViewPager.setAdapter(mPagerAdapter);
    }
    // 图片变换；
    public void Image_n() {
        Bmessage.setImageResource(R.mipmap.message_board);
        Bfriend.setImageResource(R.mipmap.friend);
    }
    //判断要显示那张图片；
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Bottom_Linear_Message_board:
                setSect(1);
                break;
            case R.id.Bottom_Linear_Friend:
                setSect(2);
                break;
        }
    }

    public void setSect(int vim) {
        Image_n();
        switch (vim) {
            case 1:
                mViewPager.setCurrentItem(0);
                Bmessage.setImageResource(R.mipmap.message_board_one);
                break;
            case 2:
                mViewPager.setCurrentItem(1);
                Bfriend.setImageResource(R.mipmap.friend_one);
                break;
        }
    }
}
