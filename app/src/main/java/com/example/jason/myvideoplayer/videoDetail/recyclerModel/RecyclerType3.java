package com.example.jason.myvideoplayer.videoDetail.recyclerModel;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/21
 * 创建时间：10:59
 */

public class RecyclerType3 {
    private String title;
    private String imageUrl;
    private String desc;
    private String name;
    private String info;
    private String videoId;
    private String videoIndex;
    private String color;
    private String font;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoIndex() {
        return videoIndex;
    }

    public void setVideoIndex(String videoIndex) {
        this.videoIndex = videoIndex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public RecyclerType3(String title, String imageUrl, String desc, String name, String info, String videoId, String videoIndex, String color, String font) {

        this.title = title;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.name = name;
        this.info = info;
        this.videoId = videoId;
        this.videoIndex = videoIndex;
        this.color = color;
        this.font = font;
    }
}
