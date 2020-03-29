package com.example.zjq.news;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.utils.RoundProgressBar;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplashActivity extends Activity {

    private TextView tv_content, tv_author;
    private RoundProgressBar skip;
    private ImageView meitu;

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

        String url = "https://api.77sec.cn/yiyan/api.php";

        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                String guwen = result;

                int begin = result.indexOf("(");
                int end = result.indexOf(")");

                //"君子坦荡荡，小人长戚戚。——孔子"
                guwen = result.substring(begin+2, end-1);

                String[] a = guwen.split("。");

                tv_content.setText(a[0] + "。");
                tv_author.setText(a[1]);



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
        meitu = findViewById(R.id.meitu);

        List<Drawable> imgs = new ArrayList<>();
        imgs.add(getResources().getDrawable(R.drawable.img_1));
        imgs.add(getResources().getDrawable(R.drawable.img_2));
        imgs.add(getResources().getDrawable(R.drawable.img_3));
        imgs.add(getResources().getDrawable(R.drawable.img_4));

        int n = new Random().nextInt(imgs.size());

        Glide.with(SplashActivity.this).load(imgs.get(n)).into(meitu);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (skip != null) {
            skip.stop();
        }
    }
}
