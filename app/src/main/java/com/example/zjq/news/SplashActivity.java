package com.example.zjq.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.bean.EveryDayWordBean;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.RoundProgressBar;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Random;

public class SplashActivity extends Activity {

    private TextView tv_content, tv_author;
    private RoundProgressBar skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initData();

        listener();


    }

    private void listener() {

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainActivity();
            }
        });
        skip.setOnProgressComplete(new RoundProgressBar.OnProgressComplete() {
            @Override
            public void complete() {
                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {

        if (skip != null) {
            skip.stop();
        }

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);

        finish();

    }

    private void initData() {

        String url = Constants.everyday_word;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("app_id", Constants.APPID);
        params.addBodyParameter("app_secret", Constants.APPSECRET);
        params.addBodyParameter("count", 20);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                EveryDayWordBean bean = new Gson().fromJson(result, EveryDayWordBean.class);

                if (bean.getCode() == 1) {

                    List<EveryDayWordBean.DataBean> list = bean.getData();

                    int n = new Random().nextInt(list.size());

                    tv_content.setText(bean.getData().get(n).getContent());

                    if (!TextUtils.isEmpty(bean.getData().get(n).getAuthor())) {
                        tv_author.setText("—— " + bean.getData().get(n).getAuthor());
                    }

                }

                skip.setshowTime(true);
                skip.start();

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


    private void initView() {

        tv_content = findViewById(R.id.tv_content);
        tv_author = findViewById(R.id.tv_author);
        skip = findViewById(R.id.skip);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (skip != null) {
            skip.stop();
        }
    }
}
