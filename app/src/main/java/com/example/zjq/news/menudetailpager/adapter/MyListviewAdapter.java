package com.example.zjq.news.menudetailpager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;
import com.example.zjq.news.menudetailpager.tabdetailpager.TabDetailPager;
import com.example.zjq.news.utils.CacheUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyListviewAdapter extends BaseAdapter {


    private final Context context;
    private List<TabDetailBean> mDataList = new ArrayList<>();


    public MyListviewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TabDetailBean> list) {
        this.mDataList = list;
        notifyDataSetChanged();

    }

    public void addAll(Collection<TabDetailBean> list) {
//        int lastIndex = this.mDataList.size();
//        if (this.mDataList.addAll(list)) {
//            notifyItemRangeInserted(lastIndex, list.size());
//        }
        this.mDataList.addAll(list);
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
        TabDetailBean bean = mDataList.get(position);

        if (bean.getImgsrc() != null) {

            Glide.with(context).load(bean.getImgsrc()).into(viewHolder.iv_img);

        }

        viewHolder.tv_content.setText(bean.getDigest());

        viewHolder.tv_time.setText(bean.getMtime());

        String idArray = CacheUtils.getString(context, TabDetailPager.READ_ARRAY_ID);
        if (idArray.contains(bean.getDocid())) {
            //设置为灰色
            viewHolder.tv_content.setTextColor(Color.GRAY);
        }else {
            //设置为黑色
            viewHolder.tv_content.setTextColor(Color.BLACK);

        }

        return convertView;
    }


    static class ViewHolder {
        ImageView iv_img;
        TextView tv_content, tv_time;

    }


}
