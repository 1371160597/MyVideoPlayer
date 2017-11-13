package com.example.jason.myvideoplayer.mainPage.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/3
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
