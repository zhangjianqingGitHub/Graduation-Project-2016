package com.example.zjq.news.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


import com.example.zjq.news.R;
import com.example.zjq.news.bean.NewsCenterPagerBean;

import org.w3c.dom.Text;

public class VideoAdapter extends ListBaseAdapter<NewsCenterPagerBean.DataBean> {


    private VideoView video_view;
    private Context context;
    private TextView tv_author, tv_content;

    public VideoAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

        NewsCenterPagerBean.DataBean bean = mDataList.get(position);

        video_view = holder.getView(R.id.video_view);
        tv_author = holder.getView(R.id.tv_author);
        tv_content = holder.getView(R.id.tv_content);

        tv_author.setText(bean.getSource());
        tv_content.setText(bean.getDigest());

        String url = bean.getVideoList() + "";
        url = url.substring(1, url.length() - 1);

        video_view.setVideoPath(url);

        video_view.setMediaController(new MediaController(context));
        video_view.start();


    }
}
