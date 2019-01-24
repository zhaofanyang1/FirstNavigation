package com.jiyun.firstnavigation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jiyun.firstnavigation.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    public P mPresenter;
    private Unbinder unbinder;
    private View avi;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutId());
        unbinder = ButterKnife.bind(this);
        createProgressBar();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        initEventAndData();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected abstract void initEventAndData();

    protected abstract P createPresenter();

    //创建进度条的View
    private void createProgressBar() {

    }

    protected abstract int createLayoutId();

    @Override
    public void showProgressbar() {
        progressbar();
    }

    private void progressbar() {
        View view = LayoutInflater.from(this).inflate(R.layout.progressbar_layout, null);
        avi = view.findViewById(R.id.avi);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);
    }


    @Override
    public void hideProgressbar() {
       //popupWindow.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter.destoryView();
            mPresenter = null;
        }
    }
}
