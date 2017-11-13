package com.example.jason.myvideoplayer.mainPage.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/9
 */

public class RecyclerType6Photo {
    private String imageId;
    private String name;

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

    public RecyclerType6Photo(String imageId, String name) {

        this.imageId = imageId;
        this.name = name;
    }
}
