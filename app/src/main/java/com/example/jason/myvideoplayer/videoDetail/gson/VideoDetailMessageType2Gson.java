package com.example.jason.myvideoplayer.videoDetail.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/20
 * 创建时间：10:13
 */

public class VideoDetailMessageType2Gson {

    /**
     * code : 0
     * data : [{"uid":"a6d254f96c3f48e09568e3d2d07405a2","cover":"http://userpic.api.max.mgtv.com/person59f152ab72d48.jpg@1wh_1e_1c_0o_0l_800h_800w_90q_1pr","formatHotValue":"1.7万关注","formatCollectionCounts":"1.7万关注","formatOnlineCount":"0人","nickName":"猎场","accountType":3,"hotValue":"17935","onlineCount":0,"collectionCounts":17935,"photo":"http://userpic.api.max.mgtv.com/person59f152ab72d48.jpg","online":"offline"}]
     * msg : success
     */

    public int code;
    public String msg;
    public List<Data> data;

    public static class Data {
        /**
         * uid : a6d254f96c3f48e09568e3d2d07405a2
         * cover : http://userpic.api.max.mgtv.com/person59f152ab72d48.jpg@1wh_1e_1c_0o_0l_800h_800w_90q_1pr
         * formatHotValue : 1.7万关注
         * formatCollectionCounts : 1.7万关注
         * formatOnlineCount : 0人
         * nickName : 猎场
         * accountType : 3
         * hotValue : 17935
         * onlineCount : 0
         * collectionCounts : 17935
         * photo : http://userpic.api.max.mgtv.com/person59f152ab72d48.jpg
         * online : offline
         */

        public String uid;
        public String cover;
        public String formatHotValue;
        public String formatCollectionCounts;
        public String formatOnlineCount;
        public String nickName;
        public int accountType;
        public String hotValue;
        public int onlineCount;
        public int collectionCounts;
        public String photo;
        public String online;
    }
}
