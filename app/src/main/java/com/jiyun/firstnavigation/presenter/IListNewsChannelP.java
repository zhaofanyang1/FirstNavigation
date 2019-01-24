package com.jiyun.firstnavigation.presenter;

import com.jiyun.firstnavigation.base.BasePresenter;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.contact.ListNewsChannelI;
import com.jiyun.firstnavigation.modle.IListNewsChannelM;


public class IListNewsChannelP<V extends ListNewsChannelI.ListNewsChannelV> extends BasePresenter<V> implements ListNewsChannelI.ListNewsChannelP, ListNewsChannelI.ListNewsChannelCallBak {

    private IListNewsChannelM iListNewsChannelM = new IListNewsChannelM();

    @Override
    public void getListNewsTab(String json) {

        if (mView != null) {
            mView.showProgressbar();
            iListNewsChannelM.getListNewsChanne(json, this);
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
    public void setListNewsTab(ListNewsChannel listNewsTab) {
        if (mView != null) {
            mView.hideProgressbar();
            mView.showListNewsTab(listNewsTab);
        }
    }
}
