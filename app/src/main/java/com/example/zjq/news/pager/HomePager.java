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
//
        //设置支持JS
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

//
//        //不让从当前网页跳转到系统浏览器中
        webview.setWebViewClient(new MyWebViewClient());


        //https://3g.dxy.cn/newh5/view/pneumonia?from=timeline
        webview.loadUrl("https://3g.dxy.cn/newh5/view/pneumonia?from=timeline");


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


    /**
     * 防止有 URL Scheme 跳转协议类型的url 导致webView加载网页失败
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) return false;
            if (url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
                return false;
            } else {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                } catch (Exception e) {
//                    ToastUtils.showShort("暂无应用打开此链接");
                }
                return true;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            Log.e("zjq-ddd", error.getDescription().toString() + error.getErrorCode() + "");
        }
    }

}
