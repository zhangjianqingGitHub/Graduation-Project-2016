package com.example.zjq.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.zjq.news.R;
import com.example.zjq.news.login.RegisterActivityNew;
import com.example.zjq.news.utils.DataCleanManager;
import com.example.zjq.news.utils.SharepUtils;

import java.io.File;

public class SettingActivity extends AppCompatActivity {

    private TextView tv_me_setting_name, tv_home_title, tv_huancun;
    private RelativeLayout re_me_setting_updatephone, re_me_setting_psw, re_me_setting_huancun;
    private ImageView iv_back;
    private Button bt_quit;
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initview();
        initdata();
        listener();
    }

    private void initdata() {
        tv_me_setting_name.setText(SharepUtils.getUserphone(SettingActivity.this));
    }

    private void listener() {
        MyListener listener = new MyListener();
        re_me_setting_updatephone.setOnClickListener(listener);
        iv_back.setOnClickListener(listener);
        re_me_setting_psw.setOnClickListener(listener);
        bt_quit.setOnClickListener(listener);
        re_me_setting_huancun.setOnClickListener(listener);


    }

    private void initview() {
        iv_back = findViewById(R.id.iv_back);
        tv_home_title = findViewById(R.id.tv_home_title);
        tv_home_title.setText("设置");
        tv_me_setting_name = findViewById(R.id.tv_me_setting_name);
        re_me_setting_updatephone = findViewById(R.id.re_me_setting_updatephone);
        re_me_setting_psw = findViewById(R.id.re_me_setting_psw);
        bt_quit = findViewById(R.id.bt_quit);
        tv_huancun = findViewById(R.id.tv_huancun);
        re_me_setting_huancun = findViewById(R.id.re_me_setting_huancun);


        String ss;
        try {
            ss = DataCleanManager.getTotalCacheSize(SettingActivity.this);
            tv_huancun.setText(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.re_me_setting_updatephone: {
                    //修改手机号
                    Intent intent = new Intent(SettingActivity.this, RegisterActivityNew.class);
                    intent.putExtra("from", "updatephone");
                    startActivity(intent);
                }


                break;
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.re_me_setting_psw: {
                    //修改登陆密码
                    Intent intent = new Intent(SettingActivity.this, RegisterActivityNew.class);
                    intent.putExtra("from", "updatepassword");
                    startActivity(intent);
                }


                break;

                case R.id.bt_quit:

                    setResult(2);

                    SharepUtils.deleLogin(SettingActivity.this);

                    finish();
                    break;
                case R.id.re_me_setting_huancun:

                    DataCleanManager.clearAllCache(SettingActivity.this);
                    String ss;
                    try {
                        //clearWebViewCache();
                        ss = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                        tv_huancun.setText(ss);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SharepUtils.setUserphone(SettingActivity.this, "");
                    SharepUtils.setUser_psw(SettingActivity.this, "");
                    break;
            }

        }
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache() {

        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }


    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {


        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {

        }
    }

}
