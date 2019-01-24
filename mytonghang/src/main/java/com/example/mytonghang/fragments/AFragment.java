package com.example.mytonghang.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytonghang.R;
import com.example.mytonghang.RequestApi;
import com.example.mytonghang.adapters.MyAdapter;
import com.example.mytonghang.data.Demo;
import com.example.mytonghang.modle.Mainmodle;
import com.example.mytonghang.presenter.Mainpresenter;
import com.example.mytonghang.presenter.Mypresenter;
import com.example.mytonghang.view.Myview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AFragment extends Fragment implements Myview<List<Demo.DataBean.NewListBean>>, XRecyclerView.LoadingListener {


    private final String channelId;
    private XRecyclerView xy;
    private Mypresenter mypresenter;
    private int cursor = 0;
    private List<Demo.DataBean.NewListBean> lists = new ArrayList<>();
    private MyAdapter adapter;

    public AFragment(String channelId) {
        this.channelId = channelId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView(view);
        Log.e("5555555555555555", "zou:");
        mypresenter = new Mainpresenter(this, new Mainmodle());
        Log.e("5555555555555556", "zou:");
        mypresenter.getData(RequestApi.SOURCE, cursor, channelId);
        Log.e("555555555555557777", "zou:");
        return view;
    }

    private void initView(View view) {

        xy = (XRecyclerView) view.findViewById(R.id.xy);
        adapter = new MyAdapter(lists, getContext());
        xy.setAdapter(adapter);
        xy.setLayoutManager(new LinearLayoutManager(getContext()));
        xy.setLoadingListener(this);

    }


    @Override
    public void showList(List<Demo.DataBean.NewListBean> newListBeans) {
        Log.e("数据", "newListBeans:" + newListBeans);
        adapter.setData(newListBeans,cursor);
     //   adapter.notifyDataSetChanged();
        xy.loadMoreComplete();
    }

    @Override
    public void showError(String error) {
        Log.d("AFragment", error.toString());
    }

    @Override
    public void onRefresh() {
        cursor = 0;
        mypresenter.getData(RequestApi.SOURCE, cursor, channelId);
        xy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        cursor++;
        mypresenter.getData(RequestApi.SOURCE, cursor, channelId);
        xy.loadMoreComplete();
    }
}
