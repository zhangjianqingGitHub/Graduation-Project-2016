package com.example.zjq.news.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.fragment.LeftMenuFragment;
import com.example.zjq.news.fragment.bean.LeftMenuBean;
import com.example.zjq.news.menudetailpager.InteracMenuDetailPager;
import com.example.zjq.news.menudetailpager.NewsMenuDetailPager;
import com.example.zjq.news.menudetailpager.PhotosMenuDetailPager;
import com.example.zjq.news.menudetailpager.TopicMenuDetailPager;
import com.example.zjq.news.utils.CacheUtils;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.ToastUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    private List<NewsCenterPagerBean.DataBean> list;
    private String url, url_left;

    private List<LeftMenuBean.DataBean> list_left;

    //详情页面的集合
    private List<MenuDetailBasePager> detailBasePagers;
    private int position_left;


    public NewsCenterPager(Context context) {
        super(context);
    }


    @Override
    public void initData() {
        super.initData();


        //获取侧滑菜单数据
        SetLeftData();


    }

    private void SetMainData() {
        ib_menu.setVisibility(View.VISIBLE);

        url = Constants.NewsList;

        if (CacheUtils.isConnect(context)) {

            //有网
            getDataFromNet(url);

        } else {

            //解析缓存数据
            String result = CacheUtils.getString(context, url);

            if (!TextUtils.isEmpty(result)) {

                ToastUtil.show_center(context, "没有联网哦！先看看缓存的数据吧~");

                processData(result);
            }


        }
    }

    private void SetLeftData() {

        url_left = Constants.NewsTypes;

        if (CacheUtils.isConnect(context)) {
            //有网
            getData_left(url_left);

            ToastUtil.show_center(context, "数据已缓存，断网也能查看部分内容！");

        } else {

            //解析缓存数据
            String result = CacheUtils.getString(context, url_left);

            if (!TextUtils.isEmpty(result)) {

                ToastUtil.show_center(context, "没有联网哦！先看看缓存的数据吧~");

                processData_left(result);
            }

        }
    }


    private void getData_left(final String url_left) {
        RequestParams params = new RequestParams(url_left);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                processData_left(result);

                CacheUtils.setString(context, url_left, result);

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

    //解析侧滑数据
    private void processData_left(String result) {

        LeftMenuBean bean = new Gson().fromJson(result, LeftMenuBean.class);
        if (bean.getCode() == 1) {

            list_left = new ArrayList<>();
            list_left = bean.getData();

            //添加详情页面,根据侧滑中的数据有几个添加几个
            detailBasePagers = new ArrayList<>();
            detailBasePagers.add(new NewsMenuDetailPager(context));
            detailBasePagers.add(new TopicMenuDetailPager(context));
            detailBasePagers.add(new PhotosMenuDetailPager(context));
            detailBasePagers.add(new InteracMenuDetailPager(context));

            //获取页面数据
            SetMainData();

            MainActivity mainActivity = (MainActivity) context;
            LeftMenuFragment fragment = (LeftMenuFragment) mainActivity.getLeftMenuFragment();

            fragment.setData(list_left);

//
//            swichPager(0);

        }
    }

    private void getDataFromNet(final String url) {

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("typeId", list_left.get(position_left).getTypeId());
        params.addBodyParameter("page", 1);

        Log.e("zjq", list_left.get(position_left).getTypeId() + "");

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {


                CacheUtils.setString(context, url, result);
                //解析数据
                processData(result);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("zjq-onError-1", ex.getMessage() + "--" + ex.getLocalizedMessage());


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

        int code = bean.getCode();

        if (code == 1) {
            list.addAll(bean.getData());
        }

        NewsMenuDetailPager detailBasePager = (NewsMenuDetailPager) detailBasePagers.get(0);
        detailBasePager.setData(list);

        swichPager(0);
    }

    //根据位置切换详情页面
    public void swichPager(int position) {

        position_left = position;

        //1 标题
        tv_title.setText(list_left.get(position).getTypeName());

        //移除之前内容
        fl_content.removeAllViews();

        //添加新内容
        MenuDetailBasePager detailBasePager = detailBasePagers.get(position);
        View rootView = detailBasePager.rootview;
        detailBasePager.initData();
        fl_content.addView(rootView);


    }

}
