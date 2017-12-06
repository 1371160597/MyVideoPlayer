package com.example.jason.myvideoplayer.videoDetail.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/15
 */

public class VideoDetailPlayGson {

    public int code;
    public Data data;
    public String msg;

    public static class Data {

        public String adParams;
        public int barrage;
        public Bottom bottom;
        public String clipId;
        public int default_quality;
        public int default_quality_force;
        public String drmCid;
        public int drmFlag;
        public String drmToken;
        public FreeTryTips freeTryTips;
        public String fstlvlId;
        public String info;
        public int isPreview;
        public String pcUrl;
        public String plId;
        public int previewTime;
        public int reviewState;
        public String seriesId;
        public String start_time;
        public int time;
        public String videoId;
        public String videoName;
        public List<?> middle;
        public List<Point> point;
        public List<ShadowSources> shadowSources;
        public List<String> videoDomains;
        public List<VideoSources> videoSources;

        public static class Bottom {
        }

        public static class FreeTryTips {
        }

        public static class Point {

            public String imgHash;
            public int partId;
            public int pointId;
            public String pointPic;
            public int pointStart;
            public String pointTitle;
            public int pointType;
        }

        public static class ShadowSources {
            /**
             * definition : 2
             * name : 高清
             * url : http://vcr.cdn.imgo.tv/ncrs/vod.do?fmt=2&random=1510727141330&limitrate=1182&fid=04E27AF7F40E5228DBD75892313DA819&file=/internettv/c1/2017/11/07/04E27AF7F40E5228DBD75892313DA819.ts&pno=3003
             */

            public int definition;
            public String name;
            public String url;
        }

        public static class VideoSources {
            /**
             * cornerLabelStyle : {"color":"#E4B659","font":"VIP"}
             * definition : 4
             * name : 蓝光
             * needPay : 1
             * tips : 蓝光视频，开通VIP免费看
             * url :
             */

            public CornerLabelStyle cornerLabelStyle;
            public String definition;
            public String name;
            public int needPay;
            public String tips;
            public String url;

            public static class CornerLabelStyle {
                /**
                 * color : #E4B659
                 * font : VIP
                 */

                public String color;
                public String font;
            }
        }
    }
}
