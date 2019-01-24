package com.jiyun.firstnavigation.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.activitys.MyChannels;
import com.jiyun.firstnavigation.adapter.ViewPaterAdapter2;
import com.jiyun.firstnavigation.base.BaseFragment;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.beans.MyChannel;
import com.jiyun.firstnavigation.contact.ListNewsChannelI;
import com.jiyun.firstnavigation.presenter.IListNewsChannelP;
import com.jiyun.firstnavigation.utils.DataBaseUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class InForMationFragment extends BaseFragment<ListNewsChannelI.ListNewsChannelV, IListNewsChannelP<ListNewsChannelI.ListNewsChannelV>> implements ListNewsChannelI.ListNewsChannelV/*, MyChannels.OnClick*/ {


    @BindView(R.id.id_juhe_tab)
    TabLayout tab;
    @BindView(R.id.image_id)
    ImageView image;
    @BindView(R.id.id_viewpager)
    ViewPager vp;
    private List<ListNewsChannel.NewsChannelListBean> list = new ArrayList<>();
    private ViewPaterAdapter2 adapter;
    private List<MyChannel> channelList = new ArrayList<>();


    @Override
    public int createLayoutId() {
        return R.layout.fragment_in_for_mation;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = mActivity.getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean iscb = sp.getBoolean("cb", false);
        //false走数据库
        if (iscb) {
            Log.e("333333333", "33333");
            channelList = DataBaseUtils.getInstance().selectAll();
            List<Fragment> fs = new ArrayList<>();
            List<String> title = new ArrayList<>();
            for (int i = 0; i < channelList.size(); i++) {
                tab.addTab(tab.newTab().setText(channelList.get(i).getTitle()));
                Log.e("走数据库标题", channelList.get(i).getTitle());
                fs.add(new InForMationInForFragment(channelList.get(i).getIdd()));
            }
            tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    vp.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
            adapter = new ViewPaterAdapter2(getChildFragmentManager(), fs, title);
            vp.setAdapter(adapter);
        }
        else {
            mPresenter.getListNewsTab("");
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, MyChannels.class));
                EventBus.getDefault().postSticky(channelList);
            }
        });
    }

    @Override
    public void showListNewsTab(ListNewsChannel listNewsChannel) {
        SharedPreferences user = mActivity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean("cb", true);

        List<Fragment> fragments = new ArrayList<>();
        list.addAll(listNewsChannel.getNewsChannelList());
        for (int i = 0; i < list.size(); i++) {
            tab.addTab(tab.newTab().setText(list.get(i).getChannelName()));
            fragments.add(new InForMationInForFragment(list.get(i).getChannelId()));
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        edit.commit();
        List<MyChannel> mlist = new ArrayList<>();
        List<ListNewsChannel.NewsChannelListBean> newsChannelList = listNewsChannel.getNewsChannelList();
        for (int i = 0; i < newsChannelList.size(); i++) {
            if (i < 12) {
                mlist.add(new MyChannel(null, newsChannelList.get(i).getChannelId(), newsChannelList.get(i).getChannelName(), true));
            } else {
                mlist.add(new MyChannel(null, newsChannelList.get(i).getChannelId(), newsChannelList.get(i).getChannelName(), false));
            }
            fragments.add(new InForMationInForFragment(newsChannelList.get(i).getChannelName()));
        }
        DataBaseUtils.getInstance().insert(mlist);

        channelList = DataBaseUtils.getInstance().selectAll();
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        adapter = new ViewPaterAdapter2(getChildFragmentManager(), fragments);
        vp.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Log.e("error", error);
    }

    @Override
    public IListNewsChannelP<ListNewsChannelI.ListNewsChannelV> createPresenter() {
        return new IListNewsChannelP<>();
    }

  /*  @Override
    public void show() {
        Log.e("44444", "444444444");
        channelList = DataBaseUtils.getInstance().selectAll();
        List<Fragment> fs = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (int i = 0; i < channelList.size(); i++) {
            if (channelList.get(i).getIsClick()) {
                fs.add(new InForMationInForFragment(channelList.get(i).getTitle()));
                title.add(channelList.get(i).getTitle());
            }
        }
        adapter = new ViewPaterAdapter2(getChildFragmentManager(), fs, title);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }*/
}
