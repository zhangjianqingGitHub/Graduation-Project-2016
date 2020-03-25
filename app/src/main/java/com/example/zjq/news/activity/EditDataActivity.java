package com.example.zjq.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zjq.news.R;
import com.example.zjq.news.bean.UserInfoBean;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.GlideCircleBorderTransform;
import com.example.zjq.news.utils.SharepUtils;
import com.google.gson.Gson;


import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;


public class EditDataActivity extends AppCompatActivity {
    private ImageView iv_back, iv_home_photo;
    private TextView tv_home_title, tv_me_only_username, tv_me_only_name,
            tv_me_only_sex, tv_me_only_age, tv_me_only_grade, tv_me_only_school, tv_huantou;
    private RelativeLayout re_me_only_name, re_me_only_sex, re_me_only_age, re_me_only_school;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        initview();

        initdata();

        listener();
    }

    private void initdata() {

        String url = Constants.UserInfo;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("uid", SharepUtils.getUserUSER_ID(EditDataActivity.this));
        params.addBodyParameter("key", SharepUtils.getUserUSER_KEY(EditDataActivity.this));

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {

                    UserInfoBean infoBean = new Gson().fromJson(result, UserInfoBean.class);

                    int code = infoBean.getCode();

                    if (code == 2000) {

                        tv_me_only_username.setText(infoBean.getData().getUsername());
                        tv_me_only_name.setText(infoBean.getData().getName());
                        tv_me_only_sex.setText(infoBean.getData().getSex());
                        tv_me_only_age.setText(infoBean.getData().getAge());
                        tv_me_only_grade.setText(infoBean.getData().getGradename());
                        tv_me_only_school.setText(infoBean.getData().getSchool());

                        setPic();

                    }

//                    ToastUtil.show_center(EditDataActivity.this, infoBean.getMsg());

                } catch (Exception e) {
                    Log.e("zjq=e", e.getMessage());
                }
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

    private void listener() {
        MyListener listener = new MyListener();

        re_me_only_name.setOnClickListener(listener);
        re_me_only_sex.setOnClickListener(listener);
        re_me_only_age.setOnClickListener(listener);
        re_me_only_school.setOnClickListener(listener);
        iv_back.setOnClickListener(listener);
        iv_home_photo.setOnClickListener(listener);
        tv_huantou.setOnClickListener(listener);

    }

    private void initview() {

        iv_back = findViewById(R.id.iv_back);

        tv_home_title = findViewById(R.id.tv_home_title);
        tv_home_title.setText("编辑资料");

        tv_me_only_username = findViewById(R.id.tv_me_only_username);
        tv_me_only_name = findViewById(R.id.tv_me_only_name);
        tv_me_only_sex = findViewById(R.id.tv_me_only_sex);
        tv_me_only_age = findViewById(R.id.tv_me_only_age);
        tv_me_only_school = findViewById(R.id.tv_me_only_school);

        re_me_only_name = findViewById(R.id.re_me_only_name);
        re_me_only_sex = findViewById(R.id.re_me_only_sex);
        re_me_only_age = findViewById(R.id.re_me_only_age);
        re_me_only_school = findViewById(R.id.re_me_only_school);

        iv_home_photo = findViewById(R.id.iv_home_photo);
        tv_huantou = findViewById(R.id.tv_huantou);

    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.re_me_only_name:
                    startActivity("name", tv_me_only_name.getText().toString());
                    break;
                case R.id.re_me_only_sex:
                    startActivity("sex", tv_me_only_sex.getText().toString());
                    break;
                case R.id.re_me_only_age:
                    startActivity("age", tv_me_only_age.getText().toString());
                    break;

                case R.id.re_me_only_school:
                    startActivity("school", tv_me_only_school.getText().toString());
                    break;
                case R.id.iv_back:
                    setResult(3);
                    finish();
                    break;

                case R.id.tv_huantou:
                case R.id.iv_home_photo:
                    //点击更换头像
                    Intent intentgo1 = new Intent(EditDataActivity.this, PicActivity.class);
                    startActivityForResult(intentgo1, 1);
                    //
                    break;

            }

        }
    }


//    @Override
//    public void onBackPressed() {
//        setResult(3);
//        super.onBackPressed();
//
//    }

    private void startActivity(String name, String hint) {
        Intent intent = new Intent(EditDataActivity.this, EditUserInfoItemActivity.class);
        intent.putExtra("k", name);
        intent.putExtra("hint", hint);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {

            String type = data.getStringExtra("type");
            String newdata = data.getStringExtra("data");

            if (type.equals("age")) {
                tv_me_only_age.setText(newdata);

            } else if (type.equals("name")) {
                tv_me_only_name.setText(newdata);

                SharepUtils.setUserUSER_NAME(EditDataActivity.this, newdata);
            } else if (type.equals("sex")) {
                tv_me_only_sex.setText(newdata);
            }  else if (type.equals("school")) {
                tv_me_only_school.setText(newdata);
            } else if (type.equals("pic")) {

                setPic();

            }

            //修改完 ，更新缓存

        }
    }

    private void setPic() {

        RequestOptions myOptions = new RequestOptions()
                .placeholder(R.drawable.iv_me_header)
                .optionalTransform
                        (new GlideCircleBorderTransform(DensityUtil.dip2px(2), getResources().getColor(R.color.white)));

        Glide.with(EditDataActivity.this).load(SharepUtils.getAvatar(EditDataActivity.this)).apply(myOptions).into(iv_home_photo);
    }

}
