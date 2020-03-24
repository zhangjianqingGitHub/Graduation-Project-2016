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

/*    private void Animation() {
        //渐变动画，缩放动画，旋转动画
        AlphaAnimation alphaAnimation = new AlphaAnimation( 0, 1 );
//        alphaAnimation.setDuration( 500 );
        alphaAnimation.setFillAfter( true );//停留在播放结束后的状态

        ScaleAnimation scaleAnimation = new ScaleAnimation( 0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f );
//        scaleAnimation.setDuration( 500 );
        scaleAnimation.setFillAfter( true );

        RotateAnimation rotateAnimation = new RotateAnimation( 0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f );//在屏幕一半的位置，相对于自身旋转
//        rotateAnimation.setDuration( 500 );
        rotateAnimation.setFillAfter( true );

        AnimationSet set = new AnimationSet( false );//Interpolator 可以用来控制动画显示过程的快慢（如动画开始很快，结束时有很慢
        set.addAnimation( alphaAnimation );
        set.addAnimation( scaleAnimation );
        set.addAnimation( rotateAnimation );
        set.setDuration( 2000 );

        rl_splash.startAnimation( set );


        //动画播放完成监听
        set.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始播放的时候
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //动画播放结束的时候

                //是否进入引导页
                boolean isStartMain = CacheUtils.getBoolean( SplashActivity.this, START_MAIN );//ctrl+alt+c

                Intent intent;

                if (isStartMain) {

                    //跳转到主页面

                    intent = new Intent( SplashActivity.this, MainActivity.class );

                } else {
                    intent = new Intent( SplashActivity.this, GuideActivity.class );
                }

                startActivity( intent );

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                //动画重复播放的时候

            }
        } );


    }*/
}
