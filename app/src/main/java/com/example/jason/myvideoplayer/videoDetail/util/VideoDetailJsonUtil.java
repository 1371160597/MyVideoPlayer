package com.example.jason.myvideoplayer.videoDetail.util;

import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageGson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType3Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailMessageType4Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlay2Gson;
import com.example.jason.myvideoplayer.videoDetail.gson.VideoDetailPlayGson;
import com.google.gson.Gson;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/15
 */

public class VideoDetailJsonUtil {

    public static VideoDetailMessageGson handleVideoDetailMessageResponse(String response) {

        return new Gson().fromJson(response,VideoDetailMessageGson.class);
    }

    public static VideoDetailPlayGson handleVideoDetailPlayResponse(String response) {

        return new Gson().fromJson(response,VideoDetailPlayGson.class);
    }

    public static VideoDetailPlay2Gson handleVideoDetailPlay2Response(String response) {

        return new Gson().fromJson(response,VideoDetailPlay2Gson.class);
    }

    public static VideoDetailMessageType2Gson handleVideoDetailMessageType2Response(String response) {

        return new Gson().fromJson(response,VideoDetailMessageType2Gson.class);
    }

    public static VideoDetailMessageType3Gson handleVideoDetailMessageType3Response(String response) {

        return new Gson().fromJson(response,VideoDetailMessageType3Gson.class);
    }

    public static VideoDetailMessageType4Gson handleVideoDetailMessageType4Response(String response) {

        return new Gson().fromJson(response,VideoDetailMessageType4Gson.class);
    }
}
