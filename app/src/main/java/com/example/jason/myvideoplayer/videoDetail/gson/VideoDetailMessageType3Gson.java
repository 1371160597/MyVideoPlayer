package com.example.jason.myvideoplayer.videoDetail.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/21
 * 创建时间：11:00
 */

public class VideoDetailMessageType3Gson {

    public int code;
    public Data data;
    public String msg;

    public static class Data {

        public int pageCount;
        public int pageSize;
        public int pageTotal;
        public java.util.List<List> list;

        public static class List {
            /**
             * clipId : 167448
             * cornerLabelStyle : {}
             * desc : 第1集
             * image : http://1img.hitv.com/preview/sp_images/2017/dianshiju/129126/4162785/20171106204347370.jpg
             * info : 8391.9万次播放
             * name : 郑秋冬罗伊人再续前缘
             * plId :
             * title : 猎场 第1集
             * type : 4
             * videoId : 4162785
             * videoIndex : 1
             */

            public String clipId;
            public CornerLabelStyle cornerLabelStyle;
            public String desc;
            public String image;
            public String info;
            public String name;
            public String plId;
            public String title;
            public int type;
            public String videoId;
            public int videoIndex;

            public static class CornerLabelStyle {

                public String color;
                public String font;
            }
        }
    }
}
