package com.example.jason.myvideoplayer.mainPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.fragment.Tab3Fragment;
import com.example.jason.myvideoplayer.mainPage.fragment.VideoFragment;
import com.example.jason.myvideoplayer.mainPage.gson.TabTypeGson;
import com.example.jason.myvideoplayer.mainPage.adapter.ViewPagerAdapter;
import com.example.jason.myvideoplayer.mainPage.fragment.Tab1Fragment;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtil;
import com.example.jason.myvideoplayer.mainPage.util.VideoDealJsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class VideoHomePageFragment extends Fragment {

    private TabLayout tabLayout;
    private String[] names;
    private String[] channelId;
    private List<String> titles = new ArrayList<>();
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    private View view;
    private int tabCount;
    private int lastTabCount = -1;
    private SwipeRefreshLayout swipeRefresh;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_home_page, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
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
//        fragmentList.add(new Tab3Fragment());
//        fragmentList.add(new Tab1Fragment());
//        fragmentList.add(new VideoFragment());
//        fragmentList.add(new Tab3Fragment());
//        adapter.notifyDataSetChanged();
        for (int i = 0; i < titles.size(); i++) {
            fragmentList.add(new VideoFragment(channelId[i],names[i]));
//            fragmentList.add(new Tab3Fragment());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        VideoHomePageActivity activity = (VideoHomePageActivity)getActivity();
        activity.initVideoToolbar("11视频11");

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestHttpForVideo();
            }
        });

        //申请网络，获取tablayout标题
        requestHttpForVideo();
    }

    public void updateTabLayout() {
        initTitles();
        initFragment();
        adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, titles);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        初始化ViewPage,并设置适配器
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void requestHttpForVideo() {
        String address = "http://mob.bz.mgtv.com/odin/c1/channel/list?_support=10100001&device=NX569H&osVersion=6.0.1&appVersion=5.5.5&ticket=78441TCWAMPCDSQLB44B&userId=0&mac=i864226030602243&osType=android&channel=yybcpd&uuid=babdbb65108e46048ccd15718e7e3f8d&endType=mgtvapp&androidid=be8d1ef80bd69321&imei=864226030602243&macaddress=02%3A00%3A00%3A00%3A00%3A00&seqId=5eaece8152f76a47b9777e89c440534d&version=5.2&type=10&abroad=0&uid=babdbb65108e46048ccd15718e7e3f8d&timestamp=1509701691";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            TabTypeGson result;
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (lastTabCount == tabCount) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (swipeRefresh.isRefreshing()) {
                                swipeRefresh.setRefreshing(false);
                            }
                        }
                    });
                } else {
                    String responseText = response.body().string();
                    result = VideoDealJsonUtil.handleWeatherResponse(responseText);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(result.data == null)
                                return;
                            tabCount = result.data.size();
                            lastTabCount = tabCount;
                            names = new String[tabCount];
                            channelId = new String[tabCount];
                            for (int i = 0; i < tabCount; i++) {
                                names[i] = result.data.get(i).title;
                                channelId[i] = result.data.get(i).vclassId;
                            }
                            updateTabLayout();
                            swipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }
        });
    }

    //toolbar中加载其他的menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_other_menu, menu);
    }
}
