package com.example.jason.myvideoplayer.mainPage.util;

import android.util.Log;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/23
 * 创建时间：16:42
 */

public class HttpUtilUrl {

    //首页所有频道的url
    private static String baseChannellis = "http://mob.bz.mgtv.com/odin/c1/channel/list";
    //首页单个频道的url
    private static String baseChanneIndex = "http://st.bz.mgtv.com/odin/c1/channel/index";
    //banner从显示界面跳转到播放界面的播放视频的url
    private static String baseVideoPlay = "http://mobile1.api.hunantv.com/v8/video/getSource";
    //banner从显示界面跳转到播放界面的视屏详细信息的url
    private static String baseVideoMessage = "http://mobile1.api.hunantv.com/v9/video/info";
    //所有url显示的共同的请求参数
    private static String sameParameter = "_support=10100001&" +
            "device=NX569H&" +
            "osVersion=6.0.1&" +
            "appVersion=5.5.5&" +
            "ticket=78441TCWAMPCDSQLB44B&" +
            "userId=0&" +
            "mac=i864226030602243&" +
            "osType=android&" +
            "channel=yybcpd&" +
            "uuid=babdbb65108e46048ccd15718e7e3f8d&" +
            "endType=mgtvapp&" +
            "androidid=be8d1ef80bd69321&" +
            "imei=864226030602243&" +
            "macaddress=02%3A00%3A00%3A00%3A00%3A00&" +
            "version=5.2&" +
            "type=10&" +
            "abroad=0&" +
            "uid=babdbb65108e46048ccd15718e7e3f8d";
//            "uid=babdbb65108e46048ccd15718e7e3f8d&" +
//            "seqId=5eaece8152f76a47b9777e89c440534d";
    private static String vclassId = "vclassId";
    private static String clipId = "clipId";
    private static String videoId = "videoId";
    private static String plId = "plId";

    //获取所有频道的列表地址
    public static String getChannelList() {
        return baseChannellis + "?" + sameParameter;
    }

    //获取每个频道的地址
    public static String getEveryChannel(String mVclassId) {
        return baseChanneIndex + "?" + sameParameter + "&" + vclassId + "=" + mVclassId;
    }

    //获取banner上面视频的播放地址
    public static String getBannerVideoPlay(String jumpId) {
        return baseVideoPlay + "?" + sameParameter + "&" + clipId + "=" + jumpId;
    }

    //获取banner上面视频的详细信息
    public static String getBannerVideoMessage(String jumpId) {
        return baseVideoMessage + "?" + sameParameter + "&" + clipId + "=" + jumpId;
    }

    //获取多布局recycler头条来了上面视频的播放
    public static String getRecyclerItemVideoPlay(String jumpChildId ,String jumpId) {
        return baseVideoPlay + "?" + sameParameter + "&" + videoId + "=" + jumpChildId + "&" + plId + "=" + jumpId;
    }

//    //获取多布局recycler头条来了上面视频的播放
//    public static String getRecyclerItemVideoPlay(String jumpChildId ,String jumpId) {
//        return baseVideoPlay + "?" + sameParameter + "&" + videoId + "=" + jumpChildId + "&" + clipId + "=" + jumpId;
//    }

    //获取多布局recycler头条来了上面视频的详细信息
    public static String getRecyclerItemVideoMessage(String jumpChildId ,String jumpId) {
        return baseVideoMessage + "?" + sameParameter + "&" + videoId + "=" + jumpChildId + "&" + plId + "=" + jumpId;
    }

//    //获取多布局recycler头条来了上面视频的详细信息
//    public static String getRecyclerItemVideoMessage(String jumpChildId ,String jumpId) {
//        return baseVideoMessage + "?" + sameParameter + "&" + videoId + "=" + jumpChildId + "&" + clipId + "=" + jumpId;
//    }
}

