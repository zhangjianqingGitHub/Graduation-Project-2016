package com.example.zjq.news.pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.zjq.news.R;
import com.example.zjq.news.base.BasePager;

public class HomePager extends BasePager {

    private WebView webview;
    private WebSettings webSettings;

    public HomePager(Context context) {
        super(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initData() {
        super.initData();

        View view = View.inflate(context, R.layout.homepager, null);

        webview = view.findViewById(R.id.webview);

        tv_title.setText("新冠肺炎实时动态");
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

                pbLoading.setVisibility(View.GONE);
            }
        });

        webview.loadUrl("https://voice.baidu.com/act/newpneumonia/newpneumonia");


//        //设置标题
//        tv_title.setText("主页面");
//        //联网请求得到数据，创建视图
//        TextView textView = new TextView(context);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(25);
//        fl_content.addView(textView);
//        //绑定数据
//        textView.setText("主页面内容");

        fl_content.addView(view);

    }



}
