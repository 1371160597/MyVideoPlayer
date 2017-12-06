package com.example.jason.myvideoplayer.mainPage.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.jason.myvideoplayer.customControls.VpSwipeRefreshLayout;
import com.example.jason.myvideoplayer.mainPage.adapter.VideoRecyclerAdapter;
import com.example.jason.myvideoplayer.mainPage.gson.TabTypeItemForVideoGson;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType3Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType2Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType4Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType5Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType6Photo;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtilManager;
import com.example.jason.myvideoplayer.mainPage.util.HttpUtilUrl;
import com.example.jason.myvideoplayer.mainPage.util.Parameter;
import com.example.jason.myvideoplayer.videoDetail.VideoDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class VideoFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener, OnBannerListener, BaseQuickAdapter.OnItemChildClickListener {

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
    private List<Uri> images;//banner的图片地址
    private List<String> titles;//banner的标题
    private List<String> imageAndTitleId;//banner的jumpId
    private List<String> jumpKind;//banner的jumpKind
    private TabTypeItemForVideoGson result;
    private VpSwipeRefreshLayout mSwipeRefresh;



    public VideoFragment() {
        super();
    }

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
        mSwipeRefresh = (VpSwipeRefreshLayout) view.findViewById(R.id.recycler_view_swipe_refresh);
        videoBanner = inflater.inflate(R.layout.video_banner, container, false);
        banner = (Banner) videoBanner.findViewById(R.id.video_banners);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text.setText("欢迎来到第" + channelId + "页！");


        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestHttpForVideoChannel();
            }
        });

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
        banner.setOnBannerListener(this);
        banner.start();
    }

    private void initImagesAndTitles(int position) {
        int bannerNumber = result.data.get(position).moduleData.size();
        String imgHUrlToMobile = null;
        String imgHUrlToMobile1 = null;
        String imgHUrlToMobile2 = null;
        String name = null;
        String jumpId = null;
        String jumpModel = null;
        images = new ArrayList();
        titles = new ArrayList<>();
        imageAndTitleId = new ArrayList<>();
        jumpKind = new ArrayList<>();

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

        for (int i = 0; i < bannerNumber; i++) {
            jumpId = result.data.get(0).moduleData.get(i).jumpId;
            imageAndTitleId.add(jumpId);
            jumpModel = result.data.get(0).moduleData.get(i).jumpKind;
            jumpKind.add(jumpModel);
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

        if(videoBanner.getParent() != null && videoBanner instanceof ViewGroup){
            //先去除banner的父布局，重新加载
            ((ViewGroup)videoBanner.getParent()).removeView(videoBanner);
        }
        adapter.setHeaderView(videoBanner);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        adapter.isFirstOnly(false);
        adapter.setOnItemClickListener(this);
    }

    private void initRecyclerNoBannerAdapter(int type){
        adapter = new VideoRecyclerAdapter(null);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
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
                        String imgHUrl,rightCorner,updateInfo,name,subName,jumpId,childId,jumpKind;
                        for (int j = 0; j < size; j++) {
                            imgHUrl = result.data.get(i).moduleData.get(j).imgHUrl;
                            rightCorner = result.data.get(i).moduleData.get(j).rightCorner;
                            updateInfo = result.data.get(i).moduleData.get(j).updateInfo;
                            name = result.data.get(i).moduleData.get(j).name;
                            subName = result.data.get(i).moduleData.get(j).subName;
                            jumpId = result.data.get(i).moduleData.get(j).jumpId;
                            childId = result.data.get(i).moduleData.get(j).childId;
                            jumpKind = result.data.get(i).moduleData.get(j).jumpKind;
                            RecyclerType2Photo typePhoto = new RecyclerType2Photo(imgHUrl,rightCorner,updateInfo,name,subName,jumpId,childId,jumpKind);
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
        String address = HttpUtilUrl.getEveryChannel(channelId);
        HttpUtilManager.getInstance().getVideoEveryChannel(address, new HttpUtilManager.CallBack<TabTypeItemForVideoGson>() {
            @Override
            public void onSuccess(TabTypeItemForVideoGson mResult) {
                if(mResult.data == null)
                    return;
                result = mResult;
                initVideoTabItemForVideoGsonData();
                mSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "无网络连接，请检查网络后重试。", Toast.LENGTH_SHORT).show();
                if (mSwipeRefresh.isRefreshing()) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    //recycler Item 点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        VideoRecyclerAdapter.VideoRecycler item = (VideoRecyclerAdapter.VideoRecycler) adapter.getItem(position);
        switch (item.getItemType()) {
            case VideoRecyclerAdapter.VideoRecycler.TYPE1:
//                Toast.makeText(getContext(), "1."+item.title, Toast.LENGTH_SHORT).show();
                break;
            case VideoRecyclerAdapter.VideoRecycler.TYPE2:
                Toast.makeText(getContext(), "2."+item.typePhoto.getJumpId(), Toast.LENGTH_SHORT).show();
                Parameter parameter = new Parameter();
                parameter.setJumpId(item.typePhoto.getJumpId());
                parameter.setJumpKind(item.typePhoto.getJumpKind());
                parameter.setJumpChildId(item.typePhoto.getChildId());
                parameter.setJumpMessage("item_new");
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);
                intent.putExtra("parameter_data", parameter);
                startActivity(intent);
                break;
            case VideoRecyclerAdapter.VideoRecycler.TYPE3:
                Toast.makeText(getContext(), "3."+item.type2Photo.getName(), Toast.LENGTH_SHORT).show();
                break;
            case VideoRecyclerAdapter.VideoRecycler.TYPE4:
                Toast.makeText(getContext(), "4."+item.type4Photo.getName(), Toast.LENGTH_SHORT).show();
                break;
            case VideoRecyclerAdapter.VideoRecycler.TYPE5:
                Toast.makeText(getContext(), "5."+item.type5Photo.getName(), Toast.LENGTH_SHORT).show();
                break;
            case VideoRecyclerAdapter.VideoRecycler.TYPE6:
                Toast.makeText(getContext(), "6."+item.type6Photo.getName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    //banner点击事件监听
    @Override
    public void OnBannerClick(int position) {
        Parameter parameter = new Parameter();
        parameter.setJumpId(imageAndTitleId.get(position));
        parameter.setJumpKind(jumpKind.get(position));
        parameter.setJumpMessage("banner");
        Toast.makeText(getContext(), "提示jumpId："+imageAndTitleId.get(position), Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "提示jumpKind："+jumpKind.get(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), VideoDetailActivity.class);
        intent.putExtra("parameter_data", parameter);
        startActivity(intent);
    }
}
