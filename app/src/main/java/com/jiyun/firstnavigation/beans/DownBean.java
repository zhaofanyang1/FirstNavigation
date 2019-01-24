package com.jiyun.firstnavigation.beans;

public class DownBean {
    String userId;
    String channelId;
    String cursor;

    public DownBean() {
    }

    public DownBean(String userId, String channelId, String cursor) {
        this.userId = userId;
        this.channelId = channelId;
        this.cursor = cursor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
