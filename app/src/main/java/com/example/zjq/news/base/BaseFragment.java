package com.example.zjq.news.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类Fragment
 */
public abstract class BaseFragment extends Fragment {

    public Activity mContext;//MainActivity

    /**
     * 当fragment被创建的时候回调这个方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        mContext = getActivity();
    }

    /**
     * 当视图被创建的时候回调
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 让孩子实现自己的视图，达到自己特有的效果
     *
     * @return
     */
    public abstract View initView();

    /**
     * 当Activity被创建之后被回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        initData();
    }

    /**
     * 如果子页面没有数据，联网请求数据，并且绑定到initView初始化的视图上
     */
    public void initData() {

    }



}
