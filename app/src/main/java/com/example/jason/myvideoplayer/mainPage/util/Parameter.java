package com.example.jason.myvideoplayer.mainPage.util;

import java.io.Serializable;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/28
 * 创建时间：16:26
 */

public class Parameter implements Serializable {
    private String jumpId;
    private String jumpKind;
    private String jumpMessage;
    private String jumpChildId;
    private String jumpVideoId;

    public String getJumpVideoId() {
        return jumpVideoId;
    }

    public void setJumpVideoId(String jumpVideoId) {
        this.jumpVideoId = jumpVideoId;
    }

    public String getJumpId() {
        return jumpId;
    }

    public void setJumpId(String jumpId) {
        this.jumpId = jumpId;
    }

    public String getJumpKind() {
        return jumpKind;
    }

    public void setJumpKind(String jumpKind) {
        this.jumpKind = jumpKind;
    }

    public String getJumpMessage() {
        return jumpMessage;
    }

    public void setJumpMessage(String jumpMessage) {
        this.jumpMessage = jumpMessage;
    }

    public String getJumpChildId() {
        return jumpChildId;
    }

    public void setJumpChildId(String jumpChildId) {
        this.jumpChildId = jumpChildId;
    }
}
