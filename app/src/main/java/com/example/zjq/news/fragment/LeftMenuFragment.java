package com.example.zjq.news.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zjq.news.base.BaseFragment;

/**
 * 左侧菜单的Fragment
 */
public class LeftMenuFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {


        textView = new TextView( mContext );
        textView.setGravity( Gravity.CENTER );
        return textView;
    }

    @Override
    public void initData() {

        textView.setText( "hhhhh打发顺丰" );

        super.initData();





    }
}
