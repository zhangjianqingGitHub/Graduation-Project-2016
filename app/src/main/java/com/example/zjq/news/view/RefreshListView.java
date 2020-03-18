package com.example.zjq.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zjq.news.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义下拉刷新的List View
 */
public class RefreshListView extends ListView {

    //下拉刷新控件
    private View ll_pull_down;

    private ImageView iv_arrow;

    private ProgressBar pb_status;

    private TextView tv_status;

    private TextView tv_time;

    //下拉刷新，顶部轮播图
    private LinearLayout headerView;

    //下拉刷新控件的高
    private int headHeight;

    private float startY = -1;

    //下拉刷新
    public static final int pull_down_refresh = 0;

    //松手刷新
    public static final int release_refresh = 1;

    //正在刷新
    public static final int refreshing = 2;

    //当前的状态
    private int currentStatus = pull_down_refresh;

    private Animation upAnimation;
    private Animation downAnimation;

    private OnRefreshListener onRefreshListener;

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initHeaderView(context);

        //动画
        initAnimation();

    }

    private void initAnimation() {

        upAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);

        downAnimation = new RotateAnimation(-180, -360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);

    }

    private void initHeaderView(Context context) {

        headerView = (LinearLayout) View.inflate(context, R.layout.refresh_head, null);

        ll_pull_down = headerView.findViewById(R.id.ll_pull_down);
        iv_arrow = headerView.findViewById(R.id.iv_arrow);
        pb_status = headerView.findViewById(R.id.pb_status);
        tv_status = headerView.findViewById(R.id.tv_status);
        tv_time = headerView.findViewById(R.id.tv_time);


//        //测量
        ll_pull_down.measure(0, 0);

        //得到高
        headHeight = ll_pull_down.getMeasuredHeight();

        //默认隐藏下拉刷新控件
        ll_pull_down.setPadding(0, -headHeight, 0, 0);

        //添加头
        addHeaderView(headerView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //记录起始坐标
                startY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                if (startY == -1) {
                    startY = ev.getY();
                }

                //如果正在刷新，则不刷新
                if (refreshing == currentStatus) {
                    break;
                }

                //来到新的坐标
                float endY = ev.getY();

                //记录滑动的距离
                float distanceY = endY - startY;

                if (distanceY > 0) {//下拉
                    int paddingTop = (int) (-headHeight + distanceY);

                    if (paddingTop < 0 && currentStatus != pull_down_refresh) {
                        //下拉了一点，不刷新
                        currentStatus = pull_down_refresh;
                        //更新状态
                        refreshViewState();
                    } else if (paddingTop > 0 && currentStatus != release_refresh) {
                        //下拉完全露出header,松手就刷新
                        currentStatus = release_refresh;
                        //更新状态
                        refreshViewState();
                    }


                    ll_pull_down.setPadding(0, paddingTop, 0, 0);
                }

                break;
            case MotionEvent.ACTION_UP:

                //手离开

                //重新记录
                startY = -1;

                //
                if (currentStatus == pull_down_refresh) {
                    //手离开后，下拉距离任然没有超过header

                    //header会弹，隐藏
                    ll_pull_down.setPadding(0, -headHeight, 0, 0);
                } else if (currentStatus == release_refresh) {
                    //下拉高度足够，松手就刷新
                    currentStatus = refreshing;
                    //设置状态为正在刷新


                    refreshViewState();

                    //完全显示
                    ll_pull_down.setPadding(0, 0, 0, 0);


                    //回调接口
                    if (onRefreshListener != null) {
                        onRefreshListener.onPullDownRefresh();
                    }
                }


                break;
        }


        return super.onTouchEvent(ev);
    }

    private void refreshViewState() {

        switch (currentStatus) {
            case pull_down_refresh://下拉刷新

                iv_arrow.startAnimation(downAnimation);
                tv_status.setText("下拉刷新...");

                break;
            case release_refresh://手松刷新

                iv_arrow.startAnimation(upAnimation);
                tv_status.setText("松手刷新...");
                break;
            case refreshing://正在刷新
                pb_status.setVisibility(VISIBLE);
                tv_status.setText("正在刷新...");
                iv_arrow.clearAnimation();
                iv_arrow.setVisibility(INVISIBLE);

                break;
        }

    }

    //当联网成功或者失败时候记录请求联网的时间
    public void onRefreshFinish(boolean sucess) {

        //还原状态
        tv_status.setText("下拉刷新...");
        currentStatus = pull_down_refresh;
        iv_arrow.clearAnimation();
        pb_status.setVisibility(INVISIBLE);
        iv_arrow.setVisibility(VISIBLE);

        //隐藏下拉刷新控件
        ll_pull_down.setPadding(0, -headHeight, 0, 0);

        if (sucess) {
            //成功
            //设置时间
            tv_time.setText("上次更新时间：" + getSystemTime());
        }

    }

    //得到当前系统时间
    private String getSystemTime() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        return format.format(new Date());

    }

    //定义接口
    public interface OnRefreshListener {

        //当下拉刷新时回调这个方法
        void onPullDownRefresh();

    }


    //设置监听刷新
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.onRefreshListener = listener;
    }
}
