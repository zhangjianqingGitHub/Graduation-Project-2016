package com.example.zjq.news.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.BaseFragment;
import com.example.zjq.news.fragment.bean.LeftMenuBean;
import com.example.zjq.news.pager.NewsCenterPager;
import com.example.zjq.news.utils.DensityUtil;

import java.util.List;

/**
 * 左侧菜单的Fragment
 */
public class LeftMenuFragment extends BaseFragment {

    private ListView listView;
    private int prePosition = 0;
    private LeftmenuFragmentAdapter Left_adapter;
    private String url;
    private List<LeftMenuBean.DataBean> list_left;

    @Override
    public View initView() {

        listView = new ListView(mContext);
        listView.setPadding(0, DensityUtil.dip2px(mContext, 40), 0, 0);
        listView.setDividerHeight(0);
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setSelector(android.R.color.transparent);

        //设置item 点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //记录点击得位置，变成红色

                prePosition = position;
                Left_adapter.notifyDataSetChanged();
                MainActivity mainActivity = (MainActivity) mContext;

                mainActivity.getSlidingMenu().toggle();//开就关，关就开

                //切换到对应得详情页面
                swichPager(position);


            }
        });

        return listView;
    }

    //根据位置切换不同详情页面
    private void swichPager(int position) {
        //把左侧菜单关闭
        MainActivity mainActivity = (MainActivity) mContext;
        ContentFragment contentFragment = (ContentFragment) mainActivity.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();

        newsCenterPager.swichPager(position);
    }

    @Override
    public void initData() {
        super.initData();

    }


    public void setData(List<LeftMenuBean.DataBean> list_left) {

        this.list_left = list_left;

        Left_adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(Left_adapter);
    }


    class LeftmenuFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_left.size();
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            TextView textView = (TextView) View.inflate(mContext, R.layout.item_leftmenu, null);
            textView.setText(list_left.get(position).getTypeName());

            textView.setEnabled(position == prePosition);

            return textView;
        }
    }
}
