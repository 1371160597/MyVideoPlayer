package com.example.jason.myvideoplayer.mainPage.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/6
 */

public class TabTypeGson {

    public int code;
    public String msg;
    public String seqid;
    public int serverTime;
    public int timestamp;
    public List<Data> data;

    public static class Data {

        public String channelHImg;
        public String channelIcon;
        public String description;
        public String fstlvlId;
        public String keywords;
        public String library;
        public String pageType;
        public String title;
        public String vclassId;
        public String vclassName;
        public String vclassType;
        public String wechatDesc;
        public String wechatTitle;
    }
}
