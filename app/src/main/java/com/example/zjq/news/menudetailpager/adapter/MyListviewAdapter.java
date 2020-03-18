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
    private List<TabDetailBean.ResultBean.DataBean> mDataList;

    public MyListviewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TabDetailBean.ResultBean.DataBean> list) {
        this.mDataList = list;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {

        if (mDataList != null && mDataList.size() != 0) {
            return mDataList.size();

        } else {
            return 0;
        }
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
        TabDetailBean.ResultBean.DataBean bean = mDataList.get(position);

        if (bean.getThumbnail_pic_s() != null) {

            Glide.with(context).load(bean.getThumbnail_pic_s()).into(viewHolder.iv_img);

        }

        viewHolder.tv_content.setText(bean.getTitle());

        viewHolder.tv_time.setText(bean.getTitle());

        return convertView;
    }


    static class ViewHolder {
        ImageView iv_img;
        TextView tv_content, tv_time;

    }


}
