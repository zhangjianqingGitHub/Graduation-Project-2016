package com.example.zjq.news.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zjq.news.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 *
 */
public class BasePager {


    public final Context context;
    public View rootView;

    @ViewInject(R.id.tv_title)
    public TextView tv_title;

    //点击侧滑
    @ViewInject(R.id.ib_menu)
    public ImageButton ib_menu;

    //加载各个子页面
    @ViewInject(R.id.fl_content)
    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;

        rootView = initView();

    }

    private View initView() {

        View view = View.inflate(context, R.layout.base_pager, null);

        x.view().inject(this, view);

        return view;
    }


    public void initData() {

    }
}
