package com.example.jason.myvideoplayer.mainPage.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/8
 */

public class TabTypeItemForVideoGson {

    public Channel channel;
    public int code;
    public String msg;
    public String seqid;
    public int serverTime;
    public int timestamp;
    public List<Data> data;

    public static class Channel {

        public String channelIcon;
        public String fstlvlId;
        public String pageType;
        public String title;
        public String vclassId;
        public String vclassType;
        public String wechatDesc;
        public String wechatTitle;
    }

    public static class Data {

        public int adId;
        public String combineId;
        public int dataModuleId;
        public String isExchange;
        public int moduleId;
        public String moduleName;
        public String moduleTitle;
        public String moduleType;
        public List<ModuleData> moduleData;

        public static class ModuleData {

            public String childId;
            public String connectUrl;
            public String cornerType;
            public String filter;
            public String imgHUrl;
            public String imgHUrlToMobile;
            public String imgHVUrl;
            public String isShare;
            public String jumpId;
            public String jumpKind;
            public String mobileTitle;
            public String name;
            public String pageUrl;
            public String phoneImgUrl;
            public String playerType;
            public String rightCorner;
            public int sortNo;
            public String subName;
            public String tvChannelId;
            public String updateInfo;
            public String videoId;
        }
    }
}
