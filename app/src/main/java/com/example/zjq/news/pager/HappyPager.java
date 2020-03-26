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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.zjq.news.R;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.bean.EveryDayWenBean;
import com.example.zjq.news.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class HappyPager extends BasePager {

    private WebView webview;
    private WebSettings webSettings;
    private View view;

    private LinearLayout llWen;
    private LinearLayout llYiqing;
    private LinearLayout llLishi;
    private LinearLayout llXiaohua, ll_every_wen;

    private TextView tv_title, tv_author, tv_content;


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

                    view_yiding();

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

        tv_title = view.findViewById(R.id.tv_title);
        tv_author = view.findViewById(R.id.tv_author);
        tv_content = view.findViewById(R.id.tv_content);
        ll_every_wen = view.findViewById(R.id.ll_every_wen);

        tv_title.setText("娱乐");


    }


    @SuppressLint("SetJavaScriptEnabled")
    private void view_yiding() {

        ll_every_wen.setVisibility(View.GONE);

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

        String url = Constants.everyday_wen;

        RequestParams params = new RequestParams(url);


        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                EveryDayWenBean bean = new Gson().fromJson(result, EveryDayWenBean.class);

                tv_title.setText(bean.getData().getTitle());
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

        String url = Constants.lishi;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("type", "1");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("zjq-lishi", result);
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
        String url = Constants.xiaohua;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("zjq-xiaohau", result);
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




}
