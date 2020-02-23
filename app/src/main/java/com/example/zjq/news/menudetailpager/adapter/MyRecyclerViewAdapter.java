package com.example.zjq.news.menudetailpager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zjq.news.R;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<TabDetailBean.ResultBean.ListBean> mDataList = new ArrayList<>();

    public MyRecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.tabdetail_pager_adapter, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyHolder holder, int position) {

        holder.textView.setText(mDataList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        @ViewInject(R.id.tv_content)
        TextView textView;

        MyHolder(View view) {
            super(view);
            x.view().inject(this, view);

        }
    }

    public void setDataList(List<TabDetailBean.ResultBean.ListBean> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(List<TabDetailBean.ResultBean.ListBean> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }


}
