package com.jiyun.firstnavigation;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.jiyun.firstnavigation.adapter.MyPageAdapter;
import com.jiyun.firstnavigation.fragments.CircleFragment;
import com.jiyun.firstnavigation.fragments.InForMationFragment;
import com.jiyun.firstnavigation.fragments.MyFragment;
import com.jiyun.firstnavigation.fragments.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private ViewPager vp;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tabs);

        initData();
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> title = new ArrayList<>();
        fragments.add(new InForMationFragment());
        fragments.add(new TopicFragment());
        fragments.add(new CircleFragment());
        fragments.add(new MyFragment());
        title.add("资讯");
        title.add("话题");
        title.add("圈子");
        title.add("我的");
        tab.addTab(tab.newTab().setText("资讯"));
        tab.addTab(tab.newTab().setText("话题"));
        tab.addTab(tab.newTab().setText("圈子"));
        tab.addTab(tab.newTab().setText("我的"));

        tab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tab.getTabAt(0).setIcon(R.drawable.selectora);
                tab.getTabAt(1).setIcon(R.drawable.selectorb);
                tab.getTabAt(2).setIcon(R.drawable.selectorc);
                tab.getTabAt(3).setIcon(R.drawable.selectord);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments, title);
        vp.setAdapter(pageAdapter);
        tab.setupWithViewPager(vp);
    }

}



