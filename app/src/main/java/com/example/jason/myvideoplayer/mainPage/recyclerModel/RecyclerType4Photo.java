package com.example.jason.myvideoplayer.mainPage.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/9
 */

public class RecyclerType4Photo {
    private String imageId;
    private String name;
    private String subName;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public RecyclerType4Photo(String imageId, String name, String subName) {

        this.imageId = imageId;
        this.name = name;
        this.subName = subName;
    }
}
