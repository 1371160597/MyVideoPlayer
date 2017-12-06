package com.example.jason.myvideoplayer.videoDetail.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/20
 * 创建时间：10:33
 */

public class RecyclerType2 {
    private String imageUrl;
    private String title;
    private String count;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public RecyclerType2(String imageUrl, String title, String count) {

        this.imageUrl = imageUrl;
        this.title = title;
        this.count = count;
    }
}
