package com.example.zjq.news.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.adapter.VideoAdapter;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.fragment.ContentFragment;
import com.example.zjq.news.menudetailpager.NewsMenuDetailPager;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class VideoPager extends BasePager {

    private View view;
    private RecyclerView rv_video;
    private VideoAdapter adapter;

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        findview();
        getVideoData();

    }

    private void getVideoData() {
        String url = Constants.NewsList;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("typeId", "526");//522 526
        params.addBodyParameter("page", 1);


        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                NewsCenterPagerBean bean = new Gson().fromJson(result, NewsCenterPagerBean.class);

                int code = bean.getCode();


                if (code == 1) {

                    try {
                        adapter.addAll(bean.getData());

                    } catch (Exception e) {

                        Log.e("zjq-e", e.getMessage());
                    }

                }

                pbLoading.setVisibility(View.INVISIBLE);


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


    private void findview() {

        view = View.inflate(context, R.layout.video, null);

        fl_content.addView(view);

        rv_video = view.findViewById(R.id.rv_video);
        tv_title.setText("视频");
        top.setVisibility(View.GONE);

        rv_video.setLayoutManager(new LinearLayoutManager(context));
        adapter = new VideoAdapter(context);
        rv_video.setAdapter(adapter);

        //        //分页滑动,类似
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_video);


    }
}
