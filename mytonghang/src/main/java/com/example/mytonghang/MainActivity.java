package com.example.mytonghang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mytonghang.adapters.V_Adapter;
import com.example.mytonghang.data.NewsChannel;
import com.example.mytonghang.fragments.AFragment;
import com.example.mytonghang.modle.Mainmodle;
import com.example.mytonghang.presenter.Mainpresenter;
import com.example.mytonghang.presenter.Mypresenter;
import com.example.mytonghang.view.Myview;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Myview<List<NewsChannel.DataBean.NewsChannelListBean>> {

    private TabLayout tab;
    private ViewPager vp;
    private Mainpresenter mainpresenter;
    //private Mypresenter mypresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mainpresenter = new Mainpresenter(this, new Mainmodle());
        mainpresenter.getData(RequestApi.TABTITLE, "");
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    public void showList(List<NewsChannel.DataBean.NewsChannelListBean> newsChannelListBeans) {
        Log.e("0000", newsChannelListBeans.toString());
        List<Fragment> fs = new ArrayList<>();
        for (int i = 0; i < newsChannelListBeans.size(); i++) {
            tab.addTab(tab.newTab().setText(newsChannelListBeans.get(i).getChannelName()));
            fs.add(new AFragment(newsChannelListBeans.get(i).getChannelId()));
            Log.e("idididididididid",""+ newsChannelListBeans.get(i).getChannelId());
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
        V_Adapter adapter = new V_Adapter(getSupportFragmentManager(), fs);
        vp.setAdapter(adapter);

    }

    @Override
    public void showError(String error) {

    }
}
