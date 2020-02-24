package com.example.zjq.news.menudetailpager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;

import java.util.List;

public class MyListviewAdapter extends BaseAdapter {


    private final Context context;
    private List<TabDetailBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mDataList;

    public MyListviewAdapter(Context context, List<TabDetailBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        this.context = context;
        this.mDataList = list;
    }


    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_tabdetail_pager, null);

            viewHolder = new ViewHolder();

            viewHolder.iv_img = convertView.findViewById(R.id.iv_img);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();


        }
        TabDetailBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean = mDataList.get(position);

        if (bean.getImageurls().size() != 0) {

//            x.image().bind(viewHolder.iv_img, bean.getImageurls().get(0).getUrl());

            Glide.with(context).load(bean.getImageurls().get(0).getUrl()).into(viewHolder.iv_img);

        }

        viewHolder.tv_content.setText(bean.getTitle());

        viewHolder.tv_time.setText(bean.getPubDate());

        return convertView;
    }


    static class ViewHolder {
        ImageView iv_img;
        TextView tv_content, tv_time;

    }


}
