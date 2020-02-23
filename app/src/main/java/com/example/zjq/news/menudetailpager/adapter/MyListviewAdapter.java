package com.example.zjq.news.menudetailpager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjq.news.R;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;

import org.xutils.x;

import java.util.List;

public class MyListviewAdapter extends BaseAdapter {


    private final Context context;
    private List<TabDetailBean.ResultBean.ListBean> mDataList;

    public MyListviewAdapter(Context context, List<TabDetailBean.ResultBean.ListBean> list) {
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
        TabDetailBean.ResultBean.ListBean bean = mDataList.get(position);

        x.image().bind(viewHolder.iv_img, bean.getPic());

        viewHolder.tv_content.setText(bean.getTitle());

        viewHolder.tv_time.setText(bean.getTime());

        return convertView;
    }


    static class ViewHolder {
        ImageView iv_img;
        TextView tv_content, tv_time;

    }


}
