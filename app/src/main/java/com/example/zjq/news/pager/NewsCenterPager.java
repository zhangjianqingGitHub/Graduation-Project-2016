package com.example.zjq.news.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.fragment.LeftMenuFragment;
import com.example.zjq.news.menudetailpager.InteracMenuDetailPager;
import com.example.zjq.news.menudetailpager.NewsMenuDetailPager;
import com.example.zjq.news.menudetailpager.PhotosMenuDetailPager;
import com.example.zjq.news.menudetailpager.TopicMenuDetailPager;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    private List<NewsCenterPagerBean.ResultBean.DataBean> list;

    //详情页面的集合
    private List<MenuDetailBasePager> detailBasePagers;
    private String[] strs = {};

    public NewsCenterPager(Context context) {
        super(context);
    }


    @Override
    public void initData() {
        super.initData();

        ib_menu.setVisibility(View.VISIBLE);
        //设置标题
        tv_title.setText("新闻中心");
        //联网请求得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        fl_content.addView(textView);
        //绑定数据
        textView.setText("新闻中心内容");

        getDataFromNet();

    }

    private void getDataFromNet() {

        Constants.type = Constants.type_top;

        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("zjq", result);

                //解析数据
                processData(result);

                //设置适配器

                //给左侧菜单传递数据

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("zjq-onError", ex.getMessage());


            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("zjq", cex.getMessage());


            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    //解析json数据
    private void processData(String result) {
        list = new ArrayList<>();

        NewsCenterPagerBean bean = new Gson().fromJson(result, NewsCenterPagerBean.class);

        int code = Integer.parseInt(bean.getResult().getStat());

        if (code == 1) {
            list.addAll(bean.getResult().getData());
        }


        strs = new String[]{"基神", "B神", "翔神", "曹神"};

        MainActivity mainActivity = (MainActivity) context;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();

        //添加详情页面
        detailBasePagers = new ArrayList<>();
        detailBasePagers.add(new NewsMenuDetailPager(context,strs));
        detailBasePagers.add(new TopicMenuDetailPager(context));
        detailBasePagers.add(new PhotosMenuDetailPager(context));
        detailBasePagers.add(new InteracMenuDetailPager(context));

        leftMenuFragment.setData(strs);




    }

    //根据位置切换详情页面
    public void swichPager(int position) {

        //1 标题
        tv_title.setText(strs[position]);

        //移除之前内容
        fl_content.removeAllViews();

        //添加新内容
        MenuDetailBasePager detailBasePager=detailBasePagers.get(position);
        View rootView=detailBasePager.rootview;
        detailBasePager.initData();
        fl_content.addView(rootView);



    }

}
