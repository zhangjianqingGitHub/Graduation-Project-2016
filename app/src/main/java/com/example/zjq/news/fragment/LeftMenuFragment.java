package com.example.zjq.news.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.zjq.news.utils.CacheUtils;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.DensityUtil;
import com.example.zjq.news.utils.ToastUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单的Fragment
 */
public class LeftMenuFragment extends BaseFragment {

    private ListView listView;
    private int prePosition = 0;
    private LeftmenuFragmentAdapter Left_adapter;
    private List<LeftMenuBean.DataBean> list;
    private String url;

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

        newsCenterPager.swichPager(position, list);
    }

    @Override
    public void initData() {
        super.initData();

        if (CacheUtils.isConnect(mContext)) {

            url = Constants.NewsTypes;
            //有网
            getData(url);

            ToastUtil.show_center(mContext, "数据已缓存，断网也能查看部分内容！");

        } else {

            //解析缓存数据
            String result = CacheUtils.getString(mContext, url);

            if (!TextUtils.isEmpty(result)) {

                ToastUtil.show_center(mContext, "没有联网哦！先看看缓存的数据吧~");

                processData(result);
            }


        }


    }

    private void getData(final String url) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                processData(result);

                CacheUtils.setString(mContext, url, result);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("zjq-Left", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //解析数据
    private void processData(String result) {

        LeftMenuBean bean = new Gson().fromJson(result, LeftMenuBean.class);
        if (bean.getCode() == 1) {

//                    ToastUtil.show_center(mContext, bean.getMsg());

            list = new ArrayList<>();
            list = bean.getData();

            Left_adapter = new LeftmenuFragmentAdapter();
            listView.setAdapter(Left_adapter);

            swichPager(prePosition);

        }
    }


    class LeftmenuFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
            textView.setText(list.get(position).getTypeName());

            textView.setEnabled(position == prePosition);

            return textView;
        }
    }
}
