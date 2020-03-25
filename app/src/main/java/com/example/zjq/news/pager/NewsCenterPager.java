package com.example.zjq.news.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.fragment.LeftMenuFragment;
import com.example.zjq.news.fragment.bean.LeftMenuBean;
import com.example.zjq.news.menudetailpager.NewsMenuDetailPager;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    private List<NewsCenterPagerBean.DataBean> list;

    private List<LeftMenuBean.DataBean> list_left;

    //详情页面的集合
    private NewsMenuDetailPager newsMenuDetailPager;


    public NewsCenterPager(Context context) {
        super(context);


    }


    @Override
    public void initData() {
        super.initData();

        //获取侧滑菜单数据
        getData_left();

    }


    private void getData_left() {

        String url_left = Constants.NewsTypes;

        RequestParams params = new RequestParams(url_left);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                LeftMenuBean bean = new Gson().fromJson(result, LeftMenuBean.class);
                if (bean.getCode() == 1) {

                    list_left = new ArrayList<>();
                    list_left = bean.getData();

                    MainActivity mainActivity = (MainActivity) context;
                    LeftMenuFragment fragment = (LeftMenuFragment) mainActivity.getLeftMenuFragment();

                    fragment.setData(list_left);

                    //获取页面数据
                    SetMainData(0);

                }

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

    public void SetMainData(final int position) {
        ib_menu.setVisibility(View.VISIBLE);

        String url = Constants.NewsList;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("typeId", list_left.get(position).getTypeId());
        params.addBodyParameter("page", 1);


        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                list = new ArrayList<>();

                NewsCenterPagerBean bean = new Gson().fromJson(result, NewsCenterPagerBean.class);

                int code = bean.getCode();


                if (code == 1) {

                    try {

                        for (int i = 0; i < bean.getData().size(); i++) {
                            if (!TextUtils.isEmpty(bean.getData().get(i).getSource()) && bean.getData().get(i).getSource() != null
                                    && bean.getData().get(i).getImgList() != null
                                    && bean.getData().get(i).getImgList().size() > 0
                                    && bean.getData().get(i).getNewsId().length() > 6) {

                                list.add(bean.getData().get(i));

                            }
                        }

                        newsMenuDetailPager = new NewsMenuDetailPager(context, list);
//                        newsMenuDetailPager.setData(list);

                        //1 标题
                        tv_title.setText(list_left.get(position).getTypeName());

                        //移除之前内容
                        fl_content.removeAllViews();

                        //添加新内容
                        View rootView = newsMenuDetailPager.rootview;
                        newsMenuDetailPager.initData();
                        fl_content.addView(rootView);

                        pbLoading.setVisibility(View.INVISIBLE);


                    } catch (Exception e) {

                        Log.e("zjq-e", e.getMessage());
                    }

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("zjq-onError-2", ex.getMessage());


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


}
