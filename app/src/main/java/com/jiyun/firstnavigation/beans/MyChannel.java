package com.jiyun.firstnavigation.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyChannel {
    @Id(autoincrement = true)
    private Long id;
    private String idd;
    private String title;
    private boolean isClick;

    @Generated(hash = 600671705)
    public MyChannel(Long id, String idd, String title, boolean isClick) {
        this.id = id;
        this.idd = idd;
        this.title = title;
        this.isClick = isClick;
    }

    @Generated(hash = 1689090066)
    public MyChannel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdd() {
        return this.idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsClick() {
        return this.isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    @Override
    public String toString() {
        return "MyChannel{" +
                "id=" + id +
                ", idd='" + idd + '\'' +
                ", title='" + title + '\'' +
                ", isClick=" + isClick +
                '}';
    }
}
