package com.example.jason.myvideoplayer.mainPage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.base.BaseActivity;
import com.example.jason.myvideoplayer.newsPage.NewsHomePageFragment;
import com.example.jason.myvideoplayer.photoPage.PhotoHomePageFragment;
import com.example.jason.myvideoplayer.test2Activity;
import de.hdodenhof.circleimageview.CircleImageView;

public class VideoHomePageActivity extends BaseActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CircleImageView circleImage;
    private Toolbar toolbar;
    private TextView toolTitle;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private long exitTime = 0;
    private LinearLayout homepageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_home_page);

        initView();
        initFragments();
    }

    //初始化activity界面，显示fragment
    private void initFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.home_page_layout, new VideoHomePageFragment());
        transaction.commit();
    }

    /**
     * 在fragment中调用此方法，将toolbar与drawerlayout绑定
     */
    public void initVideoToolbar(String name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolTitle = (TextView) findViewById(R.id.toolbar_title);
        toolTitle.setText(name);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //将toolbar图表与drawerlayout绑定
        actionBarDrawerToggle = new ActionBarDrawerToggle(VideoHomePageActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        homepageLayout = (LinearLayout) findViewById(R.id.home_page_layout);
        //添加navigationView的header和menu
        navigationView.inflateHeaderView(R.layout.navgation_header);
        navigationView.inflateMenu(R.menu.navigation_view_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_menu_news:
                        replaceFragment(new NewsHomePageFragment());
                        Toast.makeText(VideoHomePageActivity.this, "新闻", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.ic_menu_photo:
                        replaceFragment(new PhotoHomePageFragment());
                        Toast.makeText(VideoHomePageActivity.this, "图片", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.ic_menu_movic:
                        replaceFragment(new VideoHomePageFragment());
                        Toast.makeText(VideoHomePageActivity.this, "电影", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.text3:
//                        startActivity(new Intent(VideoHomePageActivity.this, test1Activity.class));
                        Toast.makeText(VideoHomePageActivity.this, "待开发...", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.text4:
//                        startActivity(new Intent(VideoHomePageActivity.this, test2Activity.class));
                        Toast.makeText(VideoHomePageActivity.this, "待开发...", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.text5:
//                        startActivity(new Intent(VideoHomePageActivity.this, VideoHomePageActivity.class));
                        Toast.makeText(VideoHomePageActivity.this, "待开发...", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
        //获取头部布局
        View navHeaderView = navigationView.getHeaderView(0);
        //设置监听事件
        circleImage = (CircleImageView) navHeaderView.findViewById(R.id.nav_header_circle_image);
        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VideoHomePageActivity.this, test2Activity.class));
                Toast.makeText(VideoHomePageActivity.this, "未打卡", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
            }
        });
    }

    //替换fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_page_layout, fragment);
        transaction.commit();
    }

    //当点击toolbar左上角图标时，打开drawerlayout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                actionBarDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return true;
    }

    //按下返回键，产生相应的效果
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if(System.currentTimeMillis() - exitTime > 2000){
                    exitTime = System.currentTimeMillis();
                    Snackbar.make(homepageLayout, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
