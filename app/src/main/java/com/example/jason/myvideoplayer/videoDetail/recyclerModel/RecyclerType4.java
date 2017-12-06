package com.example.jason.myvideoplayer.videoDetail.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/21
 * 创建时间：17:02
 */

public class RecyclerType4 {
    private String title;
    private String image;
    private String desc;
    private String info;
    private String name;
    private String videoId;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }


    public RecyclerType4(String title, String image, String desc, String info, String name, String videoId) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.info = info;
        this.name = name;
        this.videoId = videoId;
    }
}
