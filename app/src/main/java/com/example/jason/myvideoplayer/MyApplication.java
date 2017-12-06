package com.example.jason.myvideoplayer;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/20
 * 创建时间：11:00
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
