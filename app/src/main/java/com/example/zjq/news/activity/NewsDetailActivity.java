package com.example.zjq.news.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zjq.news.R;
import com.example.zjq.news.utils.DensityUtil;

public class NewsDetailActivity extends Activity implements View.OnClickListener {

    private TextView tvTitle, tv_html;
    private ImageButton ibMenu;
    private ImageButton ibBack;
    private ImageButton ibTextsize;
    private ImageButton ibShare;

    private WebView webview;
    private ProgressBar pbLoading;
    private WebSettings webSettings;
    private RelativeLayout ll_webview;
    private String from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        findViews();
        getdata();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void getdata() {

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        tvTitle.setText(intent.getStringExtra("source"));

         from = intent.getStringExtra("from");

        if (from != null && from.equals("top")) {
            tv_html.setVisibility(View.VISIBLE);
            ll_webview.setVisibility(View.GONE);

            tv_html.setText(Html.fromHtml(url));



        } else {

            tv_html.setVisibility(View.GONE);
            ll_webview.setVisibility(View.VISIBLE);

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

            webview.loadUrl(url);
        }


    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-23 11:13:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ibMenu = (ImageButton) findViewById(R.id.ib_menu);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        ibTextsize = (ImageButton) findViewById(R.id.ib_textsize);
        ibShare = (ImageButton) findViewById(R.id.ib_share);
        webview = (WebView) findViewById(R.id.webview);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        tv_html = findViewById(R.id.tv_html);
        ll_webview = findViewById(R.id.ll_webview);


        ibBack.setVisibility(View.VISIBLE);
        ibTextsize.setVisibility(View.VISIBLE);
        ibShare.setVisibility(View.VISIBLE);

        ibBack.setOnClickListener(this);
        ibTextsize.setOnClickListener(this);
        ibShare.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-03-23 11:13:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibBack) {
            // Handle clicks for ibBack
            finish();
        } else if (v == ibTextsize) {
            showChangeTextSizeDialog();


        } else if (v == ibShare) {
            // Handle clicks for ibShare
        }
    }

    private int tempSize = 2;
    private int realSize = tempSize;

    private void showChangeTextSizeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置文字大小");
        String[] items = {"超大字体", "大字体", "正常字体", "小字体", "超小字体"};

        builder.setSingleChoiceItems(items, realSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                tempSize = position;


            }
        });

        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                realSize = tempSize;

                if (from != null && from.equals("top")) {

                    switch (realSize) {
                        case 0:
                            //超大
                            tv_html.setTextSize(DensityUtil.px2dip(NewsDetailActivity.this,200));
                            break;
                        case 1:
                            //大
                            tv_html.setTextSize(DensityUtil.px2dip(NewsDetailActivity.this,150));

                            break;
                        case 2:
                            tv_html.setTextSize(DensityUtil.px2dip(NewsDetailActivity.this,100));

                            //正常
                            break;
                        case 3:
                            //小
                            tv_html.setTextSize(DensityUtil.px2dip(NewsDetailActivity.this,75));

                            break;

                        case 4:
                            tv_html.setTextSize(DensityUtil.px2dip(NewsDetailActivity.this,50));

                            //超小
                            break;
                    }

                }else {
                    changeTextSize(realSize);
                }

            }
        });

        builder.show();

    }

    private void changeTextSize(int realSize) {

        switch (realSize) {
            case 0:
                //超大
                webSettings.setTextZoom(200);
                break;
            case 1:
                //大
                webSettings.setTextZoom(150);

                break;
            case 2:
                webSettings.setTextZoom(100);

                //正常
                break;
            case 3:
                //小
                webSettings.setTextZoom(75);

                break;

            case 4:
                webSettings.setTextZoom(50);

                //超小
                break;
        }

    }

}
