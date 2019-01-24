package com.jiyun.firstnavigation.presenter;

import com.jiyun.firstnavigation.base.BasePresenter;
import com.jiyun.firstnavigation.beans.DownListNews;
import com.jiyun.firstnavigation.contact.DownListNewsI;
import com.jiyun.firstnavigation.contact.ListNewsChannelI;
import com.jiyun.firstnavigation.modle.DownListNewsM;

public class DownListNewsP<V extends DownListNewsI.DownListNewsIV> extends BasePresenter<V> implements DownListNewsI.DownListNewsP, DownListNewsI.DownListNewsICallBak {

    private DownListNewsM downListNewsM = new DownListNewsM();

    @Override
    public void getDownListNews(String json) {
        if (mView != null) {
            mView.showProgressbar();
            downListNewsM.getDownListNews(json,this);
        }
    }

    @Override
    public void errorShow(String error) {
        if (mView != null) {
            mView.hideProgressbar();
            mView.showError(error);
        }
    }

    @Override
    public void setDownListNewsI(DownListNews downListNews) {
        if (mView != null) {
            mView.hideProgressbar();
            mView.showDownListNews(downListNews);
        }
    }



}
