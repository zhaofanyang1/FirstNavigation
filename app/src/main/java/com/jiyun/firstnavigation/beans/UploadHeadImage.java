package com.jiyun.firstnavigation.beans;

public class UploadHeadImage {

    String headImagePath;

    public UploadHeadImage() {
    }

    public UploadHeadImage(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    public String getHeadImagePath() {
        return headImagePath;
    }

    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    @Override
    public String toString() {
        return "UploadHeadImage{" +
                "headImagePath='" + headImagePath + '\'' +
                '}';
    }
}
