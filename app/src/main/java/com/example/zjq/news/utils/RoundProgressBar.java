package com.example.zjq.news.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.zjq.news.R;

public class RoundProgressBar extends View {
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;
    private float tiemsize;

    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress = 0;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;

    /**
     * 进度的风格，实心或者空心
     */
    private int style;
    public static final int STROKE = 0;
    public static final int FILL = 1;
    public boolean flag;
    private int interval = 8;  //第二个圆环距离大圆外边缘的距离，根据ui自己调整
    private String text;//中间要显示的文字
    private float lineWidth = 1;  //中间圆环的宽度
    public int times,timees,timees1;
    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);

        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.WHITE);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColorR, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSizeR, 15);
        text = mTypedArray.getString(R.styleable.RoundProgressBar_textR);
        timees=mTypedArray.getInteger(R.styleable.RoundProgressBar_Times,5);
        timees1=timees*1000;
        times=timees*1000;
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
        mTypedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int radius = (int) (getWidth() - roundWidth) / 2; //圆环的半径

        //最大圆
        int maxLayerX = getWidth() / 2;
        paint.setColor(getResources().getColor(R.color.content_time));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(maxLayerX, maxLayerX, maxLayerX, paint);
        if(flag) {

            //中间圆圈
//        paint.setColor(getResources().getColor(R.color.content_time));
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(lineWidth); //暂时写死在这里，根据自己项目调整该值大小，如果有需要也可写入attr中
//        canvas.drawCircle(maxLayerX, maxLayerX, maxLayerX - interval, paint); //画出圆环

            //中间的文字
            paint.setColor(getResources().getColor(R.color.white));
            paint.setTextSize(textSize);
            paint.setStrokeWidth(0);
            paint.setTypeface(Typeface.DEFAULT);
            float textWidth = paint.measureText(text);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top / 2;
            canvas.drawText(text, maxLayerX - textWidth / 2, baseline, paint);


            paint.setColor(getResources().getColor(R.color.white));
            paint.setTextSize(textSize);
            paint.setStrokeWidth(0);
            paint.setTypeface(Typeface.DEFAULT);
            int tiem = times % 1000;
            int time;
            if (tiem == 0) {
                time = times / 1000;
            } else {
                time = (times / 1000) + 1;
            }
            float textWidths = paint.measureText(time + "s");
            Paint.FontMetricsInt fontMetric = paint.getFontMetricsInt();
            int baselines = (getMeasuredHeight() - fontMetric.bottom + fontMetric.top) / 2 - fontMetric.top / 2 * 3;
            canvas.drawText(time + "s", maxLayerX - textWidths / 2, baselines, paint);

            //设置进度是实心还是空心
            paint.setStrokeWidth(7); //设置圆环的宽度
            paint.setColor(getResources().getColor(R.color.white));  //设置进度的颜色
            paint.setStyle(Paint.Style.FILL);
            float displacement = interval + lineWidth * 2;
            RectF oval = new RectF(maxLayerX - radius + displacement, maxLayerX - radius + displacement,
                    maxLayerX + radius - displacement, maxLayerX + radius - displacement);  //用于定义的圆弧的形状和大小的界限

            switch (style) {
                case STROKE: {
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(oval, 0, 360 * progress / max, false, paint);  //根据进度画圆弧
                    break;
                }
                case FILL: {
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    if (progress != 0)
                        canvas.drawArc(oval, 0, 360 * progress / max, true, paint);  //根据进度画圆弧
                    break;
                }
            }
        }else{
            paint.setColor(getResources().getColor(R.color.white));
            paint.setTextSize(textSize);
            paint.setStrokeWidth(0);
            paint.setTypeface(Typeface.DEFAULT);
            float textWidth = paint.measureText(text);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(text, maxLayerX - textWidth / 2, baseline, paint);
        }

    }
    public void setshowTime(boolean flag){
        this.flag=flag;
    }
    public Thread thread;
    public boolean isround=true;
    public void start() {
        thread=new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress <= 100) {
                    progress += 100/(timees1/100);
                    setProgress(progress);
                    try {
                        Thread.sleep(100);
                        if(times>0){
                            times-=100;
                        }else{
                            times=0;
                            if(isround){
                                if(onprogresscomplete!=null&&progress==100){
                                    onprogresscomplete.complete();
                                }
                            }

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }

    public void stop() {
        progress = 100;
        isround=false;
    }


    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public  OnProgressComplete onprogresscomplete;
    public interface OnProgressComplete{
        void complete();
    }
    public void setOnProgressComplete(OnProgressComplete onprogresscomplete){
        this.onprogresscomplete=onprogresscomplete;
    }

}
