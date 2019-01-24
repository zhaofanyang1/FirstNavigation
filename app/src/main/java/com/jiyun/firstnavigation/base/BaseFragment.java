package com.jiyun.firstnavigation.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jiyun.firstnavigation.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment implements BaseView {
    public P mPresenter;
    private Unbinder unbinder;
    public Context context;
    public Activity mActivity;
    //是否执行了懒加载
    protected boolean isInitex = false;
    private PopupWindow popupWindow;

    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        oncreateview();
        //EventBus.getDefault().register(this);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        initData();
        return view;
    }

    //创建布局id
    public abstract int createLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    public void oncreateview() {
    }


    public abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.destoryView();
            mPresenter = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isInitex = true;
            loadUserVisibleData();
        }
    }

    //懒加载数据,之类可重写或者不重写
    public void loadUserVisibleData() {

    }

    @Override
    public void showProgressbar() {
        // progressbar();
    }

    @Override
    public void hideProgressbar() {
        // popupWindow.dismiss();
    }

    public void progressbar() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.progressbar_layout, null);
        popupWindow = new PopupWindow(mActivity);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);
    }


}
