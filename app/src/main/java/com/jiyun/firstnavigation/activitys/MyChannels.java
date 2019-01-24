package com.jiyun.firstnavigation.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.adapter.MyChannelsAdapter1;
import com.jiyun.firstnavigation.beans.MyChannel;
import com.jiyun.firstnavigation.utils.DataBaseUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyChannels extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.compile)
    ImageView compile;
    @BindView(R.id.xy1)
    RecyclerView xy1;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.pindao)
    TextView pindao;
    @BindView(R.id.xy2)
    RecyclerView xy2;

    List<MyChannel> list1 = new ArrayList<>();
    List<MyChannel> list2 = new ArrayList<>();
    private MyChannelsAdapter1 adapter;
    private boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_channels);
        ButterKnife.bind(this);
        //注册，初始化
        EventBus.getDefault().register(this);
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getShow(List<MyChannel> list) {
        for (int i = 0; i < list.size(); i++) {
            isClick = list.get(i).getIsClick();
            Log.e("MyChannels", "isClick:" + isClick);
            if (isClick) {
                list1.add(list.get(i));
                Log.e("MyChannels", "list1:" + list1);
                xy1.setLayoutManager(new GridLayoutManager(this, 4));
                adapter = new MyChannelsAdapter1(list1, this);
                xy1.setAdapter(adapter);

            } else {
                list2.add(list.get(i));
                Log.e("MyChannels", "list2:" + list2);
                xy2.setLayoutManager(new GridLayoutManager(this, 4));
                adapter = new MyChannelsAdapter1(list2, this);
                xy2.setAdapter(adapter);
            }
        }

    }

    private void initData() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick.show();
                finish();
            }
        });

    }

    //接口回调
    private static OnClick mOnClick;

    public interface OnClick {
        void show();
    }

    public static void setOnClick(OnClick onClick) {
        mOnClick = onClick;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
