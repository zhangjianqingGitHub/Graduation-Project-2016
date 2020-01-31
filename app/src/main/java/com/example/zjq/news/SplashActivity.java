package com.example.zjq.news;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.zjq.news.activity.GuideActivity;
import com.example.zjq.news.utils.CacheUtils;

public class SplashActivity extends Activity {

    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();
    }

    private void initView() {

        rl_splash = findViewById( R.id.rl_splash );

        //启动动画
        Animation();


    }

    private void Animation() {
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
                boolean isStartMain= CacheUtils.getBoolean(SplashActivity.this,START_MAIN);//ctrl+alt+c

                if (isStartMain){

                }else {
                    Intent intent=new Intent( SplashActivity.this, GuideActivity.class);
                    startActivity( intent );
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                //动画重复播放的时候

            }
        } );



    }
}
