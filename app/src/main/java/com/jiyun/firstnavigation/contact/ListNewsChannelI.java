package com.jiyun.firstnavigation.contact;

import com.jiyun.firstnavigation.base.BaseView;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.http.HttpFinishCallbak;


public interface ListNewsChannelI {

    interface ListNewsChannelV extends BaseView{
        void showListNewsTab(ListNewsChannel listNewsChannel);
        void showError(String error);
    }


    interface ListNewsChannelP{
        void getListNewsTab(String json);
    }

    public interface ListNewsChannelCallBak extends HttpFinishCallbak{
        void setListNewsTab(ListNewsChannel listNewsTab);
    }
}
