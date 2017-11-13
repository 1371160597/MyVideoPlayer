package com.example.jason.myvideoplayer.mainPage.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.adapter.VideoRecyclerAdapter;
import com.example.jason.myvideoplayer.mainPage.gson.TabTypeItemForVideoGson;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType3Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType2Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType4Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType5Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType6Photo;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtil;
import com.example.jason.myvideoplayer.mainPage.util.VideoDealJsonUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class VideoFragment extends Fragment {

    private static final int ITEM0 = 0;//0表示加了banner
    private static final int ITEM1 = 1;//1表示加标题
    private String channelId;
    private String channelNames;
    private View view;
    TextView text;
    private RecyclerView recyclerView;
    private VideoRecyclerAdapter adapter;
    private List<VideoRecyclerAdapter.VideoRecycler> data;
    private View videoBanner;
    private Banner banner;
    private List<Uri> images;
    private List<String> titles;

    private TabTypeItemForVideoGson result;



    public VideoFragment(String id,String name) {
        channelId = id;
        channelNames = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        text = (TextView) view.findViewById(R.id.test);
        videoBanner = inflater.inflate(R.layout.video_banner, container, false);
        banner = (Banner) videoBanner.findViewById(R.id.video_banners);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text.setText("欢迎来到第" + channelId + "页！");

        requestHttpForVideoChannel();
    }

    private void initVideoTabItemForVideoGsonData() {
        String moduleType;
        int allDataNumber = result.data.size();
        for (int i = 0; i < allDataNumber; i++) {
            moduleType = result.data.get(i).moduleType;
            if (moduleType.equals("banner")) {
                initImagesAndTitles(i);
                initBanner();
                initRecyclerBannerAdapter(ITEM0);//0表示该fragment有banner，
                break;
            } else {
                initRecyclerNoBannerAdapter(ITEM1);//1表示该fragment无banner
                break;
            }
        }

    }


    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.RotateDown);//CubeOut、RotateDown、DepthPage、ZoomOutSlide
        banner.setBannerTitles(titles);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    private void initImagesAndTitles(int position) {
        int bannerNumber = result.data.get(position).moduleData.size();
        String imgHUrlToMobile = null;
        String imgHUrlToMobile1 = null;
        String imgHUrlToMobile2 = null;
        String name = null;
        images = new ArrayList();
        titles = new ArrayList<>();

        for (int i = 0; i < bannerNumber; i++) {
            imgHUrlToMobile = result.data.get(0).moduleData.get(i).phoneImgUrl;
            if (imgHUrlToMobile.equals("")) {
                imgHUrlToMobile1 = result.data.get(0).moduleData.get(i).imgHUrl;
                if (imgHUrlToMobile1.equals("")) {
                    imgHUrlToMobile2 = result.data.get(0).moduleData.get(i).imgHUrlToMobile;
                    if (imgHUrlToMobile2.equals("")) {

                    } else {
                        imgHUrlToMobile = imgHUrlToMobile2;
                    }
                } else {
                    imgHUrlToMobile = imgHUrlToMobile1;
                }
            }
//            imgHUrlToMobile = result.data.get(0).moduleData.get(i).imgHUrl;
            images.add(Uri.parse(imgHUrlToMobile));
        }

        for (int i = 0; i < bannerNumber; i++) {
            name = result.data.get(0).moduleData.get(i).name;
            titles.add(name);
        }
    }

    private void initRecyclerBannerAdapter(int type){
        adapter = new VideoRecyclerAdapter(null);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {//更改布局，如果是为title，就占所有行
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                VideoRecyclerAdapter.VideoRecycler item = adapter.getItem(position);
                if(item == null)
                    return 1;
                switch (item.getItemType()){
                    case VideoRecyclerAdapter.VideoRecycler.TYPE1:
                        return 2;
                    case VideoRecyclerAdapter.VideoRecycler.TYPE3:
                        return 2;
                    case VideoRecyclerAdapter.VideoRecycler.TYPE4:
                        return 2;
                    case VideoRecyclerAdapter.VideoRecycler.TYPE5:
                        return 2;
                }
                return 1;
            }
        });
        adapter.setNewData(getData(type));
        adapter.setHeaderView(videoBanner);
    }

    private void initRecyclerNoBannerAdapter(int type){
        adapter = new VideoRecyclerAdapter(null);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.setNewData(getData(type));
    }

    public List<VideoRecyclerAdapter.VideoRecycler> getData(int type) {
        List<VideoRecyclerAdapter.VideoRecycler> data = new ArrayList<>();
        int allDataNumber;
        String moduleType;
        if (type == ITEM0) {//有banner
            allDataNumber = result.data.size();
            for (int i = type + 1; i < allDataNumber; i++) {
                moduleType = result.data.get(i).moduleType;
                switch (moduleType) {
                    case "title":
                        data.add(new VideoRecyclerAdapter.VideoRecycler(result.data.get(i).moduleData.get(0).name, VideoRecyclerAdapter.VideoRecycler.TYPE1));
                        break;
                    case "scrossm":
                        int size = result.data.get(i).moduleData.size();
                        String imgHUrl,rightCorner,updateInfo,name,subName;
                        for (int j = 0; j < size; j++) {
                            imgHUrl = result.data.get(i).moduleData.get(j).imgHUrl;
                            rightCorner = result.data.get(i).moduleData.get(j).rightCorner;
                            updateInfo = result.data.get(i).moduleData.get(j).updateInfo;
                            name = result.data.get(i).moduleData.get(j).name;
                            subName = result.data.get(i).moduleData.get(j).subName;
                            RecyclerType2Photo typePhoto = new RecyclerType2Photo(imgHUrl,rightCorner,updateInfo,name,subName);
                            data.add(new VideoRecyclerAdapter.VideoRecycler(typePhoto, VideoRecyclerAdapter.VideoRecycler.TYPE2));
                        }
                        break;
                    case "nonbcross":
                        int size1 = result.data.get(i).moduleData.size();
                        String imgHUrl1,rightCorner1,updateInfo1,name1;
                        for (int j = 0; j < size1; j++) {
                            imgHUrl1 = result.data.get(i).moduleData.get(j).imgHUrl;
                            rightCorner1 = result.data.get(i).moduleData.get(j).rightCorner;
                            updateInfo1 = result.data.get(i).moduleData.get(j).updateInfo;
                            name1 = result.data.get(i).moduleData.get(j).name;
                            RecyclerType3Photo recyclerType3Photo = new RecyclerType3Photo(imgHUrl1, rightCorner1, updateInfo1, name1);
                            data.add(new VideoRecyclerAdapter.VideoRecycler(recyclerType3Photo, VideoRecyclerAdapter.VideoRecycler.TYPE3));
                        }
                        break;
                    case "ipmodel":
                        int size2 = result.data.get(i).moduleData.size();
                        String phoneImgUrl,name2,subName2;
                        for (int j = 0; j < size2; j++) {
                            phoneImgUrl = result.data.get(i).moduleData.get(j).phoneImgUrl;
                            name2 = result.data.get(i).moduleData.get(j).name;
                            subName2 = result.data.get(i).moduleData.get(j).subName;
                            RecyclerType4Photo recyclerType4Photo = new RecyclerType4Photo(phoneImgUrl, name2, subName2);
                            data.add(new VideoRecyclerAdapter.VideoRecycler(recyclerType4Photo, VideoRecyclerAdapter.VideoRecycler.TYPE4));
                        }
                        break;
                    case "bcrossm":
                        int size5 = result.data.get(i).moduleData.size();
                        String imgHUrl5,updateInfo5,name5,subName5;
                        for (int j = 0; j < size5; j++) {
                            imgHUrl5 = result.data.get(i).moduleData.get(j).imgHUrl;
                            updateInfo5 = result.data.get(i).moduleData.get(j).updateInfo;
                            name5 = result.data.get(i).moduleData.get(j).name;
                            subName5 = result.data.get(i).moduleData.get(j).subName;
                            RecyclerType5Photo recyclerType5Photo = new RecyclerType5Photo(imgHUrl5, updateInfo5, name5, subName5);
                            data.add(new VideoRecyclerAdapter.VideoRecycler(recyclerType5Photo, VideoRecyclerAdapter.VideoRecycler.TYPE5));
                        }
                        break;
                    case "noncross":
                        int sizes = result.data.get(i).moduleData.size();
                        String imgHUrls,names;
                        for (int j = 0; j < sizes; j++) {
                            imgHUrls = result.data.get(i).moduleData.get(j).imgHUrl;
                            names = result.data.get(i).moduleData.get(j).name;
                            RecyclerType6Photo type6Photo = new RecyclerType6Photo(imgHUrls,names);
                            data.add(new VideoRecyclerAdapter.VideoRecycler(type6Photo, VideoRecyclerAdapter.VideoRecycler.TYPE6));
                        }
                        break;
                }
            }
        }
        return data;
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    private void requestHttpForVideoChannel() {
        String address = "http://st.bz.mgtv.com/odin/c1/channel/index?_support=10100001&device=NX569H&osVersion=6.0.1&appVersion=5.5.5&ticket=78441TCWAMPCDSQLB44B&userId=0&mac=i864226030602243&osType=android&channel=yybcpd&uuid=babdbb65108e46048ccd15718e7e3f8d&endType=mgtvapp&androidid=be8d1ef80bd69321&imei=864226030602243&macaddress=02%3A00%3A00%3A00%3A00%3A00&seqId=34a14e3cb38dab8364b32414835a9032&version=5.2&type=10&abroad=0&uid=babdbb65108e46048ccd15718e7e3f8d&vclassId=" + channelId + "&timestamp=0";
        HttpUtil.sendOkHttpRequest(address, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                result = VideoDealJsonUtil.handleVideoTabItemResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.data == null)
                            return;
                        initVideoTabItemForVideoGsonData();
//                        initImagesAndTitles();
//                        initBanner();
//                        initRecyclerAdapter();
                    }
                });
            }
        });
    }


}
