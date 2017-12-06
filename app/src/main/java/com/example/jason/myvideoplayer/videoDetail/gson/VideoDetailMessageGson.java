package com.example.jason.myvideoplayer.videoDetail.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/15
 */

public class VideoDetailMessageGson {


    public int code;
    public Data data;
    public String msg;

    public static class Data {

        public int cf;
        public String clipId;
        public String clipImage;
        public String clipName;
        public CornerLabelStyle cornerLabelStyle;
        public DownloadTips downloadTips;
        public int downloadable;
        public int favorite;
        public String fstlvlId;
        public String fstlvlName;
        public String fstlvlType;
        public int isScroll;
        public int keepPlayType;
        public int keepPlayVideoDuration;
        public Object keepPlayVideoId;
        public Object keepPlayVideoTitle;
        public int keepPlayVideoWatchTime;
        public int pageCount;
        public int pay;
        public Object payTips;
        public String plId;
        public String plImage;
        public String plName;
        public String playPriority;
        public String releaseTime;
        public int reviewState;
        public String scores;
        public int serialNo;
        public String seriesId;
        public String seriesName;
        public ShareInfo shareInfo;
        public int showMode;
        public int subtitleType;
        public int time;
        public String title;
        public int type;
        public String updateTips;
        public String videoId;
        public String videoImage;
        public String videoName;
        public List<CategoryList> categoryList;
        public List<String> detail;
        public List<?> series;

        public static class CornerLabelStyle {

            public Object color;
            public Object font;
        }

        public static class DownloadTips {

            public int tag;
            public String tips;
        }

        public static class ShareInfo {

            public String desc;
            public String image;
            public String title;
            public String url;
        }

        public static class CategoryList {

            public int dataType;
            public int displayType;
            public int isMore;
            public int isRefresh;
            public String ltitle;
            public String mtitle;
            public int objectType;
            public String paramet;
            public int playOrder;
            public String rtitle;
            public Object rtitleTips;
            public String url;
        }
    }
}
