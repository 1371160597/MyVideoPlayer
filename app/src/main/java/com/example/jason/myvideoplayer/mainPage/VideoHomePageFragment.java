package com.example.jason.myvideoplayer.mainPage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.adapter.ViewPagerAdapter;
import com.example.jason.myvideoplayer.mainPage.fragment.Tab1Fragment;
import com.example.jason.myvideoplayer.mainPage.fragment.Tab2Fragment;
import com.example.jason.myvideoplayer.mainPage.fragment.Tab3Fragment;
import com.example.jason.myvideoplayer.test2Activity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class VideoHomePageFragment extends Fragment {

    private TabLayout tabLayout;
    private String[] names = new String[]{"精选", "会员", "电影", "电视剧", "综艺", "动漫", "新闻", "音乐", "少儿"};
    private List<String> titles = new ArrayList<>();
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;
    private TextView toolTitle;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_home_page, container, false);
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolTitle = (TextView) view.findViewById(R.id.toolbar_title);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        return view;
    }

    private void initTitles() {
        for (int i = 0; i < names.length; i++) {
            titles.add(names[i]);
        }
    }

    private void initFragment() {
//        fragmentList.add(new Tab1Fragment());
//        fragmentList.add(new Tab2Fragment());
//        fragmentList.add(new Tab3Fragment());
//        fragmentList.add(new Tab1Fragment());
//        fragmentList.add(new Tab2Fragment());
//        fragmentList.add(new Tab3Fragment());
//        fragmentList.add(new Tab1Fragment());
//        fragmentList.add(new Tab2Fragment());
//        fragmentList.add(new Tab3Fragment());
//        adapter.notifyDataSetChanged();
        for (int i = 0; i < titles.size(); i++) {
            fragmentList.add(new Tab1Fragment());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        VideoHomePageActivity activity = (VideoHomePageActivity)getActivity();
        activity.initVideoToolbar("11视频11");
        initTitles();
        adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, titles);
        initFragment();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        初始化ViewPage,并设置适配器
        viewPager.setAdapter(adapter);
//        设置tablayout页签
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        tabLayout.setupWithViewPager(viewPager);
    }

    //toolbar中加载其他的menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_other_menu, menu);
    }
}
