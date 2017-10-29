package com.only.yc.only_onez;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;
import android.widget.Toast;

import com.only.yc.only_sqlite.Contant;
import com.only.yc.only_sqlite.DBManger;
import com.only.yc.only_sqlite.Person;
import com.only.yc.only_sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity implements View.OnClickListener {
    final Intent intent = new Intent();
    //点击头像打开抽屉；
    private ImageView ImageView_Drawer;
    private ImageView ImageView_Menu;
    //声明抽屉；
    private DrawerLayout Mdrawer;
    // 抽屉点击事件
    private NavigationView navigationView;
    // viewPager
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<View> mViews = new ArrayList<View>();
    private ImageButton Bmessage;
    private ImageButton Bfriend;
    private LinearLayout lMessage_board;
    private LinearLayout lFriend;
    // 浮动按钮：FloatingActionButton
    private FloatingActionButton Fab;
    // 数据库调用；
    private static SQLiteHelper helper;
    //用户名称显示；
    private TextView txt_UserID;//用户ID
    private TextView txt_UserAutoGraph;
    private TextView txt_UserName;
    private String token;
    private String User_ID;//用户ID；
    private View vide;
    private TextView User_Title;
    private SharedPreferences pref;
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
        //判断用户是否登录
        pref = getSharedPreferences("date",MODE_PRIVATE);
        token = pref.getString("token","");
        // 数据库;
        helper = DBManger.getIntance(this);
        //抽屉标题事件；
        Boar_O();// 标题栏相应事件；
        NavigationView(); //抽屉相应事件；
        //ViewPager 相应事件;
        initView();
        initEvent();
        initPage();
        initFloatingActionButton();
        // 初始化用户；
        initUser();
        // 更改个性签名；
        initUserTitle();
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
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()) {
                           case R.id.Index_menu_Search:
                               intent.setClass(Index.this,search.class);
                               startActivity(intent);
                               break;
                           case R.id.Index_menu_Add:
                               Toast.makeText(Index.this,"您点击了添加好友",Toast.LENGTH_SHORT).show();
                               break;
                           case R.id.Index_menu_Setting:
                               Toast.makeText(Index.this,"您点击了设置",Toast.LENGTH_SHORT).show();
                               break;
                           default:
                               break;
                       }
                       return false;
                   }

                });

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
                        //修改页面
                        intent.setClass(Index.this,Modify.class);
                        intent.putExtra("USER_ID",User_ID);
                        startActivity(intent);
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
                    case R.id.drawer_menu_Logout:
                        if(token != null) {
                            intent.setClass(Index.this, Entry.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            pref.edit().clear().commit();
                            startActivity(intent);
                        } else {
                            Toast.makeText(Index.this, "用户未登录", Toast.LENGTH_SHORT).show();
                        }
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
        Image_n();
        switch (v.getId()) {
            case R.id.Bottom_Linear_Message_board:
                mViewPager.setCurrentItem(0);
                Bmessage.setImageResource(R.mipmap.message_board_one);
                break;
            case R.id.Bottom_Linear_Friend:
                mViewPager.setCurrentItem(1);
                Bfriend.setImageResource(R.mipmap.friend_one);
                break;
        }
    }
    //FloatingActionButton 事件；
    public void initFloatingActionButton() {
        Fab = (FloatingActionButton) findViewById(R.id.Index_Fab);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Index.this,words.class);
                startActivity(intent);
            }
        });
    }

    //初始化用户；
    public void initUser() {
        SQLiteDatabase db = helper.getWritableDatabase();
        vide = navigationView.inflateHeaderView(R.layout.index_drawerlayout_head);
        txt_UserID = (TextView) vide.findViewById(R.id.Index_Drawer_head_Txt_UserName);
        txt_UserAutoGraph = (TextView) vide.findViewById(R.id.Index_Drawer_head_Txt_UserTitle);
        txt_UserName = (TextView) findViewById(R.id.Title_txt_UserName);
        Intent inte = getIntent();
        User_ID = inte.getStringExtra("User_id");
        //
        String sql = "select "+Contant.USER_ID+", "+ Contant.USER_NAME+", "+Contant.USER_AUTOGRAPH+""+
                " from "+Contant.TABLE_NAME+" " +
                " where "+Contant.USER_ID+" = '"+User_ID+"'";
        Cursor cursor = DBManger.Select(db, sql, null);
        List<Person> list = DBManger.Show_List(cursor);
        for(Person p:list) {
            txt_UserID.setText(p.getUserID());
            txt_UserName.setText(p.getUserName());
            txt_UserAutoGraph.setText(p.getUserAutograph());
        }
        db.close();


    }

    // 个性签名；
    public void initUserTitle() {
        User_Title = (TextView) vide.findViewById(R.id.Index_Drawer_head_Txt_UserTitle);
        User_Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Index.this,Modify.class);
                intent.putExtra("USER_ID",User_ID);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
