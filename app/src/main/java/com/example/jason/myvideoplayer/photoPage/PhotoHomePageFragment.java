package com.example.jason.myvideoplayer.photoPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.VideoHomePageActivity;
import com.example.jason.myvideoplayer.mainPage.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PhotoHomePageFragment extends Fragment {

    private TabLayout tabLayout;
    private String[] names = new String[]{"图片一", "图片二", "图片三", "图片四", "图片五"};
    private List<String> titles = new ArrayList<>();
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_home_page, container, false);
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
//        fragmentList.add(new VideoFragment());
//        fragmentList.add(new Tab3Fragment());
//        fragmentList.add(new Tab1Fragment());
//        fragmentList.add(new VideoFragment());
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        VideoHomePageActivity activity = (VideoHomePageActivity)getActivity();
        activity.initVideoToolbar("图片");
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
}
