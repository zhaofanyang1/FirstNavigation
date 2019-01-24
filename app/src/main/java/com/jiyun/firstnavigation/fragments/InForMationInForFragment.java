package com.jiyun.firstnavigation.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.adapter.DownListNewsAdapter;
import com.jiyun.firstnavigation.base.BaseFragment;
import com.jiyun.firstnavigation.beans.DownBean;
import com.jiyun.firstnavigation.beans.DownListNews;
import com.jiyun.firstnavigation.contact.DownListNewsI;
import com.jiyun.firstnavigation.presenter.DownListNewsP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class InForMationInForFragment extends BaseFragment<DownListNewsI.DownListNewsIV, DownListNewsP<DownListNewsI.DownListNewsIV>> implements DownListNewsI.DownListNewsIV, XRecyclerView.LoadingListener {

    @BindView(R.id.xy_zixunxiangqing)
    XRecyclerView xy;
    Unbinder unbinder;
    private List<DownListNews.NewListBean> list = new ArrayList<>();
    private final String id;
    private DownListNewsAdapter adapter;
    private String userId = "e8bbadbd51c44a139c789fb1ef062b94";

    public InForMationInForFragment(String channelId) {
        this.id = channelId;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_in_for_mation_in_for;
    }

    @Override
    protected void initData() {
        Gson gson = new Gson();
        String s = gson.toJson(new DownBean(userId, id, "0"));
        Log.e("ssssssss", s);
        mPresenter.getDownListNews(s);
        DividerItemDecoration decoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        xy.addItemDecoration(decoration);
        xy.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new DownListNewsAdapter(list, mActivity);
        xy.setAdapter(adapter);
        xy.setLoadingListener(this);
    }


    @Override
    public DownListNewsP<DownListNewsI.DownListNewsIV> createPresenter() {
        return new DownListNewsP<>();
    }


    @Override
    public void showDownListNews(DownListNews downListNews) {
        list.addAll(downListNews.getNewList());
        Log.e("InForMationInForFragmen", "list:" + list.get(2).getTitle());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e("123445showError", error);
    }



    @Override
    public void onRefresh() {
        xy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        xy.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}
