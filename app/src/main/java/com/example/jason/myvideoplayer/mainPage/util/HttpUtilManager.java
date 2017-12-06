package com.example.jason.myvideoplayer.mainPage.util;

import com.example.jason.myvideoplayer.mainPage.gson.TabTypeGson;
import com.example.jason.myvideoplayer.mainPage.gson.TabTypeItemForVideoGson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageGson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType3Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType4Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlay2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlayGson;
import com.example.jason.myvideoplayer.videoDetail.util.VideoDetailJsonUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/29
 * 创建时间：14:08
 */

public class HttpUtilManager {

    private static HttpUtilManager httpUtilManager;

    //单例模式
    public static HttpUtilManager getInstance() {
        if (httpUtilManager == null) {
            httpUtilManager = new HttpUtilManager();
        }
        return httpUtilManager;
    }

    //通过网址获取所有频道的列表
    public void getVideoChannelTitle(String address, final CallBack<TabTypeGson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText = response.body();
                        TabTypeGson result = VideoDealJsonUtil.handleWeatherResponse(responseText);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //通过地址获取每个频道的详细信息
    public void getVideoEveryChannel(String address, final CallBack<TabTypeItemForVideoGson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText = response.body();
                        TabTypeItemForVideoGson result = VideoDealJsonUtil.handleVideoTabItemResponse(responseText);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //通过点击事件，获取播放视频的地址1
    public void getVideoDetailPlay1(String address, final CallBack<VideoDetailPlayGson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText = response.body();
                        VideoDetailPlayGson result = VideoDetailJsonUtil.handleVideoDetailPlayResponse(responseText);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //通过点击事件，获取播放视频的地址2
    public void getVideoDetailPlay2(String address, final CallBack<VideoDetailPlay2Gson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText = response.body();
                        VideoDetailPlay2Gson result = VideoDetailJsonUtil.handleVideoDetailPlay2Response(responseText);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //获取视频播放的详细信息 类型1
    public void getVideoDetailMessageItem1(String address, final CallBack<VideoDetailMessageGson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText = response.body();
                        VideoDetailMessageGson result = VideoDetailJsonUtil.handleVideoDetailMessageResponse(responseText);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //获取视频播放的详细信息 类型2
    public void getVideoDetailMessageItem2(String address, final CallBack<VideoDetailMessageType2Gson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseType2 = response.body();
                        VideoDetailMessageType2Gson result = VideoDetailJsonUtil.handleVideoDetailMessageType2Response(responseType2);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //获取视频播放的详细信息 类型3
    public void getVideoDetailMessageItem3(String address, final CallBack<VideoDetailMessageType3Gson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseType = response.body();
                        VideoDetailMessageType3Gson result = VideoDetailJsonUtil.handleVideoDetailMessageType3Response(responseType);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //获取视频播放的详细信息 类型4
    public void getVideoDetailMessageItem4(String address, final CallBack<VideoDetailMessageType4Gson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText4 = response.body();
                        VideoDetailMessageType4Gson result = VideoDetailJsonUtil.handleVideoDetailMessageType4Response(responseText4);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    //获取视频播放的详细信息 类型5
    public void getVideoDetailMessageItem5(String address, final CallBack<VideoDetailMessageType4Gson> callBack) {
        OkGo.<String>get(address)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String responseText4 = response.body();
                        VideoDetailMessageType4Gson result = VideoDetailJsonUtil.handleVideoDetailMessageType4Response(responseText4);
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        callBack.onFailure();
                    }
                });
    }

    public interface CallBack<T> {
        void onSuccess(T result);

        void onFailure();
    }
}
