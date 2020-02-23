package com.example.zjq.news.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.BaseFragment;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.pager.NewsCenterPager;
import com.example.zjq.news.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单的Fragment
 */
public class LeftMenuFragment extends BaseFragment {

    private ListView listView;
    private String[] data;
    private int prePosition=0;
    private LeftmenuFragmentAdapter Left_adapter;

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

    //接受数据
    public void setData(String[] data) {

        this.data = data;


        //数据    ArrayAdapter<String> adapter = new ArrayAdapter<String>
        //                (this,android.R.layout.simple_expandable_list_item_1,strs);
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_expandable_list_item_1,data);

        //设置适配器
        Left_adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(Left_adapter);

        swichPager(prePosition);


    }

    class LeftmenuFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
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
            textView.setText(data[position]);

//            if (position==prePosition){
//                textView.setEnabled(true);
//            }else {
//                textView.setEnabled(false);
//            }

            textView.setEnabled(position == prePosition);

            return textView;
        }
    }
}
