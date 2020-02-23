package com.example.zjq.news.menudetailpager.tabdetailpager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zjq.news.R;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.menudetailpager.adapter.MyListviewAdapter;
import com.example.zjq.news.menudetailpager.bean.TabDetailBean;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class TabDetailPager extends MenuDetailBasePager {

    private NewsCenterPagerBean.ResultBean.DataBean data;

    private List<String> imgs;

    private ViewPager viewPager;

    private TextView tv_title;

    private LinearLayout ll_point_group;

    private ListView listView;

    //之前高亮点位置
    private int prePosition;
//    private MyRecyclerViewAdapter adapter;

    private MyListviewAdapter adapter;
    private List<TabDetailBean.ResultBean.ListBean> list;
    private int next = 0;


    public TabDetailPager(Context context, NewsCenterPagerBean.ResultBean.DataBean dataBean) {
        super(context);
        this.data = dataBean;
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.tabdetail_pager, null);

        View topNewxView = View.inflate(context, R.layout.topnewx, null);
        //Todo:这里用xutil可能会报错
//        x.view().inject(this, view);
//        x.view().inject(this, topNewxView);
        viewPager = topNewxView.findViewById(R.id.viewpager);
        tv_title = topNewxView.findViewById(R.id.tv_title);
        ll_point_group = topNewxView.findViewById(R.id.ll_point_group);
        listView = view.findViewById(R.id.listview);

        listView.addHeaderView(topNewxView);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        String url = data.getUrl();

//        //下方list view
//        HtmlAsynTask task = new HtmlAsynTask();
//        task.execute(url);

        //加载上方轮播图
        viewpagerTop();

        //红点
        RedPoint();

        //下方数据
        getDataForRecyclerview();


    }


    private void getDataForRecyclerview() {

        String url = Constants.TABDETAILSPAGER;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("channel", "头条");
        params.addBodyParameter("num", 10);
        params.addBodyParameter("start", next);
        params.addBodyParameter("appkey", Constants.key_JISU);


        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                TabDetailBean bean = new Gson().fromJson(result, TabDetailBean.class);

                if (bean.getStatus() == 0) {
                    list = bean.getResult().getList();

                    adapter = new MyListviewAdapter(context, list);
                    listView.setAdapter(adapter);

                    if (next <= 400) {
                        next += 10;
                    } else {
                        next = 0;
                    }

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

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


    private void viewpagerTop() {
        imgs = new ArrayList<>();

        if (!TextUtils.isEmpty(data.getThumbnail_pic_s())) {
            imgs.add(data.getThumbnail_pic_s());
        }

        if (!TextUtils.isEmpty(data.getThumbnail_pic_s02())) {
            imgs.add(data.getThumbnail_pic_s02());
        }

        if (!TextUtils.isEmpty(data.getThumbnail_pic_s03())) {
            imgs.add(data.getThumbnail_pic_s03());
        }

        //设置viewpager的适配器
        viewPager.setAdapter(new TabDetailPagerTopNewsAdapter());
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //设置文本
            tv_title.setText(data.getTitle());
            //红点高亮

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
            imageView.setBackgroundResource(R.drawable.home_scroll_default);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);

            x.image().bind(imageView, imgs.get(position));
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
