package com.example.zjq.news.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zjq.news.base.BaseFragment;

public class ContentFragment extends BaseFragment {


    private TextView textView;

    @Override
    public View initView() {


        textView = new TextView( mContext );
        textView.setGravity( Gravity.CENTER );
        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        textView.setText( "京东快递" );



    }
}
