package com.example.jason.myvideoplayer.mainPage.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/9
 */

public class RecyclerType3Photo {
    private String imageId;
    private String rightCorner;
    private String updateInfo;
    private String name;

    public RecyclerType3Photo(String imageId, String rightCorner, String updateInfo, String name) {
        this.imageId = imageId;
        this.rightCorner = rightCorner;
        this.updateInfo = updateInfo;
        this.name = name;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getRightCorner() {
        return rightCorner;
    }

    public void setRightCorner(String rightCorner) {
        this.rightCorner = rightCorner;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
