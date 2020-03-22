package com.example.zjq.news.menudetailpager.tabdetailpager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.menudetailpager.adapter.MyListviewAdapter;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;
import com.example.zjq.news.menudetailpager.bean.TabDetailViewPagerBean;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.view.HorizontalScrollViewPager;
import com.example.zjq.news.view.RefreshListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class TabDetailPager extends MenuDetailBasePager {

    private NewsCenterPagerBean.DataBean data;

    private List<String> imgs;

    private HorizontalScrollViewPager viewPager;

    private TextView tv_title;

    private LinearLayout ll_point_group;

    private RefreshListView listView;

    //之前高亮点位置
    private int prePosition;

    private MyListviewAdapter adapter;
    private List<TabDetailBean.ResultBean.DataBean> list;
    private List<TabDetailBean.ResultBean.DataBean> list_more;
    private int start = 0;
    private int end = 10;


    public TabDetailPager(Context context, NewsCenterPagerBean.DataBean dataBean) {
        super(context);
        this.data = dataBean;

//        data.getNewsId()


    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.tabdetail_pager, null);

        View topNewxView = View.inflate(context, R.layout.topnewx, null);
        viewPager = topNewxView.findViewById(R.id.viewpager);
        tv_title = topNewxView.findViewById(R.id.tv_title);
        ll_point_group = topNewxView.findViewById(R.id.ll_point_group);
        listView = view.findViewById(R.id.listview);

//        listView.addHeaderView(topNewxView);
        listView.addTopNewsView(topNewxView);

        //设置监听刷新
        listView.setOnRefreshListener(new MyOnRefreshListener());

        return view;
    }

    class MyOnRefreshListener implements RefreshListView.OnRefreshListener {

        @Override
        public void onPullDownRefresh() {

            getDataForImgs();
            getDataForRecyclerview(1);
        }

        @Override
        public void onLoadMore() {

            getDataForRecyclerview(2);
        }
    }

    @Override
    public void initData() {
        super.initData();

//        //下方list view
//        HtmlAsynTask task = new HtmlAsynTask();
//        task.execute(url);


        getDataForImgs();


        adapter = new MyListviewAdapter(context);
        listView.setAdapter(adapter);
        //下方数据
        getDataForRecyclerview(1);


    }

    private void getDataForImgs() {
        String newsId = data.getNewsId();
        String url = Constants.NewsDetail;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("newsId", newsId);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                TabDetailViewPagerBean bean = new Gson().fromJson(result, TabDetailViewPagerBean.class);


                imgs = new ArrayList<>();

                //添加外层图片
                if (data.getImgList() != null && data.getImgList().size() != 0) {
                    for (int i = 0; i < data.getImgList().size(); i++) {
                        if (!TextUtils.isEmpty(data.getImgList().get(i))) {
                            imgs.add(data.getImgList().get(i));
                        }
                    }
                }

                //加载上方轮播图

                if (bean.getData().getImages() != null) {

                    List<TabDetailViewPagerBean.DataBean.ImgDataBean> list_imgs = bean.getData().getImages();
                    int size = list_imgs.size();

                    if (size > 4) {
                        size = 4;
                    }
                    //添加内层图片
                    for (int i = 0; i < size; i++) {
                        if (list_imgs.get(i).getImgSrc() != null && !TextUtils.isEmpty(list_imgs.get(i).getImgSrc())) {
                            imgs.add(list_imgs.get(i).getImgSrc());
                        }
                    }

                }

                //设置viewpager的适配器
                viewPager.setAdapter(new TabDetailPagerTopNewsAdapter());

                //红点
                RedPoint();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("zjq-result-aa", ex.getMessage() + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private void getDataForRecyclerview(final int from) {

        if (from == 2) {
            //加载更多

            if (start < list.size()) {

                start = end;
                end += 10;

                list_more = list.subList(start, end);

                adapter.addAll(list_more);

                listView.onRefreshFinish(false);


            } else {
                //没有更多数据
                listView.NoMore();


            }
        } else {
            String url = "http://v.juhe.cn/toutiao/index";

            RequestParams params = new RequestParams(url);
            params.addBodyParameter("key", "b0b89109785634c4fcd0a3ca78cd3ad9");
            params.addBodyParameter("type", "top");


            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    try {
                        TabDetailBean bean = new Gson().fromJson(result, TabDetailBean.class);

                        list = bean.getResult().getData();

                        if (bean.getResult().getStat().equals("1")) {

                            if (from == 1) {
                                //下拉刷新

                                start = 0;
                                end = 10;
                                list = list.subList(start, end);

                                adapter.setData(list);

                                //隐藏下拉刷新控件-更新时间(true)
                                listView.onRefreshFinish(true);


                            } else {


                            }


                        }
                    } catch (Exception e) {
                        //没有更多数据
                        listView.NoMore();
                    }


                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                    Log.e("zjq-aa", ex.getMessage());

                    //隐藏下拉刷新控件
                    listView.onRefreshFinish(true);

                    //没有更多数据
                    listView.NoMore();

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }


    }


    private void RedPoint() {
        ll_point_group.removeAllViews();

        for (int i = 0; i < imgs.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(8), DensityUtil.dip2px(8));


            if (i == 0) {
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);

                params.leftMargin = DensityUtil.dip2px(8);
            }

            imageView.setLayoutParams(params);
            ll_point_group.addView(imageView);
        }

        //监听页面得额改变
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        tv_title.setText(data.getTitle());


    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //设置文本
            tv_title.setText(data.getTitle());

            //把之前的变成灰色，
            ll_point_group.getChildAt(prePosition).setEnabled(false);

            ll_point_group.getChildAt(position).setEnabled(true);

            prePosition = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    }

    class TabDetailPagerTopNewsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setBackgroundResource(R.drawable.home_scroll_default);
            container.addView(imageView);

            Glide.with(context).load(imgs.get(position)).placeholder(R.drawable.news_pic_default).into(imageView);
//            x.image().bind(imageView, imgs.get(position),imageOptions);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }
    }

/*    class HtmlAsynTask extends AsyncTask<String, Integer, Document> {


        @Override
        protected Document doInBackground(String... strings) {

            try {
                Document doc = Jsoup.connect(strings[0]).get();

                return doc;
            } catch (IOException e) {

                Log.e("zjq-e", e.getMessage());

                return null;
            }


        }

        @Override
        protected void onPostExecute(Document doc) {
            super.onPostExecute(doc);

            if (doc != null) {

                String title = doc.title();

            }

        }
    }*/


}
