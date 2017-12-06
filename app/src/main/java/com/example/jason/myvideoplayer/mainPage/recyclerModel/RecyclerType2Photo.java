package com.example.jason.myvideoplayer.mainPage.recyclerModel;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/9
 */

public class RecyclerType2Photo {
    private String imageId;
    private String rightCorner;
    private String updateInfo;
    private String name;
    private String subName;
    private String jumpId;
    private String childId;
    private String jumpKind;

    public RecyclerType2Photo(String imageId, String rightCorner, String updateInfo, String name, String subName, String jumpId, String childId, String jumpKind) {
        this.imageId = imageId;
        this.rightCorner = rightCorner;
        this.updateInfo = updateInfo;
        this.name = name;
        this.subName = subName;
        this.jumpId = jumpId;
        this.childId = childId;
        this.jumpKind = jumpKind;
    }

    public String getJumpKind() {
        return jumpKind;
    }

    public void setJumpKind(String jumpKind) {
        this.jumpKind = jumpKind;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getJumpId() {
        return jumpId;
    }

    public void setJumpId(String jumpId) {
        this.jumpId = jumpId;
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
