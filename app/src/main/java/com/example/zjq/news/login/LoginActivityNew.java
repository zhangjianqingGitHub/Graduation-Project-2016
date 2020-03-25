package com.example.zjq.news.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.util.Util;
import com.example.zjq.news.R;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.bean.UserInfoBean;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.SharepUtils;
import com.example.zjq.news.utils.ToastUtil;
import com.example.zjq.news.utils.Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;


public class LoginActivityNew extends Activity {

    private TextView tiaoguo, tv_forget, tv_regist;
    private EditText et_user_name;
    private EditText et_user_password;
    private ImageView iv_qingkong, iv_qingkong1, iv_show;
    private Button btn_denglu;
    private boolean show = false;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        findview();

        listener();


    }

    private void listener() {

        MyListener listener = new MyListener();
        tiaoguo.setOnClickListener(listener);
        iv_qingkong.setOnClickListener(listener);
        btn_denglu.setOnClickListener(listener);
        tv_forget.setOnClickListener(listener);
        tv_regist.setOnClickListener(listener);
        iv_qingkong1.setOnClickListener(listener);
        iv_show.setOnClickListener(listener);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void findview() {

        tiaoguo = findViewById(R.id.tiaoguo);
        et_user_name = findViewById(R.id.et_user_name);
        et_user_password = findViewById(R.id.et_user_password);
        iv_qingkong = findViewById(R.id.iv_qingkong);
        btn_denglu = findViewById(R.id.btn_denglu);
        tv_regist = findViewById(R.id.tv_regist);
        tv_forget = findViewById(R.id.tv_forget);
        iv_qingkong1 = findViewById(R.id.iv_qingkong1);
        iv_show = findViewById(R.id.iv_show);

        et_user_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        String user_phone = SharepUtils.getUserphone(LoginActivityNew.this);
        String user_password = SharepUtils.getUser_psw(LoginActivityNew.this);

        if (!TextUtils.isEmpty(user_phone)) {
            et_user_name.setText(user_phone);
            iv_qingkong1.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user_password)) {
            et_user_password.setText(user_password);
            iv_qingkong.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user_phone) && !TextUtils.isEmpty(user_password)) {
            btn_denglu.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

        }

        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                if (TextUtils.isEmpty(et_user_name.getText().toString())) {

                    iv_qingkong1.setVisibility(View.INVISIBLE);
                } else {
                    iv_qingkong1.setVisibility(View.VISIBLE);
                }

                isShow();


            }
        });


        et_user_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(et_user_password.getText().toString())) {
                    iv_qingkong.setVisibility(View.INVISIBLE);
                } else {
                    iv_qingkong.setVisibility(View.VISIBLE);
                }

                isShow();


                //光标位置
                et_user_password.requestFocus();
                et_user_password.setSelection(et_user_password.getText().length());
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void isShow() {
        if (et_user_name.getText().length() == 11 && et_user_password.getText().length() >= 6) {
            btn_denglu.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

        } else {
            btn_denglu.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

        }
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tiaoguo:
                    //跳过

                    Intent intentmain = new Intent(LoginActivityNew.this, MainActivity.class);

                    startActivity(intentmain);

                    if (SharepUtils.IsLogin(LoginActivityNew.this)) {

                        //清理登录信息
                        SharepUtils.deleLogin(LoginActivityNew.this);

                    }

                    break;
                case R.id.iv_qingkong:
                    et_user_password.setText("");
                    break;
                case R.id.btn_denglu:

                    if (!TextUtils.isEmpty(et_user_name.getText().toString()) && !TextUtils.isEmpty(et_user_password.getText().toString())) {


                        if (Utils.isFastClick()) {
                            Log.e("zjq-ccc", "1111111111");
                            denglu(et_user_name.getText().toString(), et_user_password.getText().toString());
                        }


                    }

                    break;
                case R.id.tv_forget: {
                    //忘记密码
                    Intent intent = new Intent(LoginActivityNew.this, RegisterActivityNew.class);
                    intent.putExtra("from", "forget");
                    startActivityForResult(intent, 2);
                }

                break;
                case R.id.tv_regist:

                    //去注册
                    Intent intent = new Intent(LoginActivityNew.this, RegisterActivityNew.class);
                    intent.putExtra("from", "zhuce");
                    startActivityForResult(intent, 2);
                    break;
                case R.id.iv_qingkong1:
                    et_user_name.setText("");

                    et_user_password.setText("");
                    break;
                case R.id.iv_show:

                    if (show) {
                        iv_show.setImageDrawable(getResources().getDrawable(R.drawable.suoclose));
                        et_user_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        show = false;
                    } else {
                        iv_show.setImageDrawable(getResources().getDrawable(R.drawable.icon_password_open));
                        et_user_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        show = true;
                    }

                    break;
            }

        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == 2) {
            //注册完
            setResult(1);
            finish();
        }

    }

    private void denglu(final String username, final String password) {

        String url = Constants.Deng_Lu;

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    UserInfoBean packlist = new Gson().fromJson(result, UserInfoBean.class);

                    int code = packlist.getCode();


                    if (code == 2000) {
                        //成功
                        UserInfoBean.DataBean mUserInfo = packlist.getData();
                        SharepUtils.saveUserInfonew(LoginActivityNew.this, mUserInfo);
                        SharepUtils.saveLogin(LoginActivityNew.this);

                        SharepUtils.setUserphone(LoginActivityNew.this, username);
                        SharepUtils.setUser_psw(LoginActivityNew.this, password);

                        setResult(1);

                        //.
                        String from = getIntent().getStringExtra("from");


                        if (TextUtils.isEmpty(from)) {
                            finish();
                        } else if (from != null && from.equals("StartActivity")) {
                            Intent intent = new Intent(LoginActivityNew.this, MainActivity.class);
                            startActivity(intent);
                        }


                    } else if (code == 1005) {

                        ToastUtil.show_center(LoginActivityNew.this, "该用户名不存在");
                    } else if (code == 2001) {
                        ToastUtil.show_center(LoginActivityNew.this, "错误的用户或密码");

                    }


//                    ToastUtil.show_center(LoginActivityNew.this, packlist.getMsg());


                } catch (Exception e) {

                    Log.e("zjq-eee", e.getMessage());

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


}
