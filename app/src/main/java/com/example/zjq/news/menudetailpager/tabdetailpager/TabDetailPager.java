package com.example.zjq.news.menudetailpager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
//import android.support.annotation.NonNull;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zjq.news.R;
import com.example.zjq.news.base.MenuDetailBasePager;
import com.example.zjq.news.bean.NewsCenterPagerBean;
import com.example.zjq.news.utils.CacheUtils;
import com.example.zjq.news.utils.ToastUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabDetailPager extends MenuDetailBasePager {

    private NewsCenterPagerBean.ResultBean.DataBean data;

    private List<String> imgs;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.ll_point_group)
    private LinearLayout ll_point_group;

    @ViewInject(R.id.lv_listview)
    private ListView lv_listview;


    public TabDetailPager(Context context, NewsCenterPagerBean.ResultBean.DataBean dataBean) {
        super(context);
        this.data = dataBean;
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.tabdetail_pager, null);

        //Todo:这里用xutil可能会报错
        x.view().inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        String url = data.getUrl();

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

        Log.e("zjq-url", url);


        HtmlAsynTask task = new HtmlAsynTask();
        task.execute(url);


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

            Log.e("zjq-img", imgs.get(position) + "");

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);

            container.removeView((View) object);
        }
    }

    class HtmlAsynTask extends AsyncTask<String, Integer, Document> {


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

                Log.e("zjq-title", title);

            }

        }
    }


}
