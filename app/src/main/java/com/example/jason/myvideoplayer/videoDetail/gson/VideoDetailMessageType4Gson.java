package com.example.jason.myvideoplayer.videoDetail.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/21
 * 创建时间：16:38
 */

public class VideoDetailMessageType4Gson {

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
             * desc : 04:31
             * image : http://3img.hitv.com/preview/sp_images/2017/dianshiju/129126/4180859/20171121111449834.jpg
             * info : 1.4万次播放
             * name : 菅纫姿专访：曝光与胡歌吻戏细节
             * plId : 0
             * title : 《猎场》独家专访：超羞涩！菅纫姿曝光与胡歌吻戏细节
             * type : 7
             * videoId : 4180859
             * videoIndex : 0
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
            }
        }
    }
}
