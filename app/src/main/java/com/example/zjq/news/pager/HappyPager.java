package com.example.zjq.news.pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.zjq.news.R;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.bean.EveryDayWenBean;
import com.example.zjq.news.bean.HistoryBean;
import com.example.zjq.news.bean.JokesBean;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HappyPager extends BasePager {

    private WebView webview;
    private WebSettings webSettings;
    private View view;

    private LinearLayout llWen;
    private LinearLayout llYiqing;
    private LinearLayout llLishi;
    private LinearLayout llXiaohua, ll_every_wen;

    private TextView title, tv_author, tv_content;

    private ImageView iv_img;

    private ScrollView sv_view;

    public HappyPager(Context context) {
        super(context);


        findView();
        listener();

    }

    @Override
    public void initData() {
        super.initData();

        //默认是每日一文
        view_wen();

    }

    private void listener() {

        MyListener listener = new MyListener();

        llWen.setOnClickListener(listener);
        llYiqing.setOnClickListener(listener);
        llLishi.setOnClickListener(listener);
        llXiaohua.setOnClickListener(listener);

    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.ll_wen:

                    view_wen();

                    break;
                case R.id.ll_yiqing:

                    view_yiqing();

                    break;
                case R.id.ll_lishi:

                    view_lishi();
                    break;
                case R.id.ll_xiaohua:

                    view_xiaohua();

                    break;

            }

        }
    }


    private void findView() {

        view = View.inflate(context, R.layout.homepager, null);
        fl_content.addView(view);

        webview = view.findViewById(R.id.webview_yiqing);

        llWen = view.findViewById(R.id.ll_wen);
        llYiqing = view.findViewById(R.id.ll_yiqing);
        llLishi = view.findViewById(R.id.ll_lishi);
        llXiaohua = view.findViewById(R.id.ll_xiaohua);
//        top.setVisibility(View.GONE);

        title = view.findViewById(R.id.title);
        tv_author = view.findViewById(R.id.tv_author);
        tv_content = view.findViewById(R.id.tv_content);
        ll_every_wen = view.findViewById(R.id.ll_every_wen);
        sv_view = view.findViewById(R.id.sv_view);


        iv_img = view.findViewById(R.id.iv_img);

        tv_title.setText("娱乐");


    }


    @SuppressLint("SetJavaScriptEnabled")
    private void view_yiqing() {

        ll_every_wen.setVisibility(View.GONE);
        iv_img.setVisibility(View.GONE);


//        tv_title.setText("新冠肺炎实时动态");
//
        //设置支持JS
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //设置双击变大变小
        webSettings.setUseWideViewPort(true);

        //增加缩放按钮
        webSettings.setBuiltInZoomControls(true);

        //设置文字大小
        webSettings.setTextZoom(100);

        //不让从当前网页跳转到系统浏览器中
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                webview.setVisibility(View.VISIBLE);
                pbLoading.setVisibility(View.GONE);
            }
        });

        webview.loadUrl("https://voice.baidu.com/act/newpneumonia/newpneumonia");

    }


    private void view_wen() {

        webview.setVisibility(View.GONE);
        ll_every_wen.setVisibility(View.VISIBLE);
        iv_img.setVisibility(View.GONE);
        sv_view.scrollTo(0, 0);

        String url = Constants.everyday_wen;

        RequestParams params = new RequestParams(url);


        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                EveryDayWenBean bean = new Gson().fromJson(result, EveryDayWenBean.class);

                title.setText(bean.getData().getTitle());
                tv_author.setText(bean.getData().getAuthor());
                tv_content.setText(Html.fromHtml(bean.getData().getContent()));

                pbLoading.setVisibility(View.INVISIBLE);
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


    private void view_lishi() {

        webview.setVisibility(View.GONE);
        ll_every_wen.setVisibility(View.VISIBLE);
        iv_img.setVisibility(View.VISIBLE);
        sv_view.scrollTo(0, 0);


        String url = Constants.lishi;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("type", "1");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                HistoryBean bean = new Gson().fromJson(result, HistoryBean.class);

                int n = new Random().nextInt(bean.getData().size());

                title.setText(bean.getData().get(n).getTitle());
                tv_author.setText(bean.getData().get(n).getYear() + "-" + bean.getData().get(n).getMonth() + "-" + bean.getData().get(n).getDay());
                tv_content.setText(Html.fromHtml(bean.getData().get(n).getDetails()));

                Glide.with(context).load(bean.getData().get(n).getPicUrl()).into(iv_img);

                pbLoading.setVisibility(View.INVISIBLE);
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


    private void view_xiaohua() {

        webview.setVisibility(View.GONE);
        ll_every_wen.setVisibility(View.VISIBLE);
        iv_img.setVisibility(View.GONE);
        sv_view.scrollTo(0, 0);

        title.setText("");
        tv_author.setText("");
        List<String> list = new ArrayList<String>();
        list.add(Constants.xiaohua1);
        list.add(Constants.xiaohua2);
        list.add(Constants.xiaohua3);
        list.add(Constants.xiaohua4);
        list.add(Constants.xiaohua5);

        int x = new Random().nextInt(list.size());
        tv_content.setText(list.get(x));


//        String url = Constants.xiaohua;
//        RequestParams params = new RequestParams(url);
//        params.addBodyParameter("app_id", Constants.APPID);
//        params.addBodyParameter("app_secret", Constants.APPSECRET);
//        params.addBodyParameter("page","1");
//
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//                Log.e("zjq",result);
//
//                JokesBean bean=new Gson().fromJson(result,JokesBean.class);
//
//                int n = new Random().nextInt(bean.getData().getList().size());
//
//                title.setText("");
//                tv_author.setText("");
////                tv_content.setText(bean.getData().getList().get(n).getContent());
//                List list=new ArrayList();
//                list.add(Constants.xiaohua1);
//                list.add(Constants.xiaohua2);
//                list.add(Constants.xiaohua3);
//                list.add(Constants.xiaohua4);
//                list.add(Constants.xiaohua5);
//
//                int x = new Random().nextInt(list.size());
//                tv_content.setText(list.get(x).toString());
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });


    }


}
