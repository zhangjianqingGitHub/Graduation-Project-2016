package com.example.zjq.news.login;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telecom.Call;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.zjq.news.R;
import com.example.zjq.news.bean.UserInfoBean;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.SharepUtils;
import com.example.zjq.news.utils.ToastUtil;
import com.google.gson.Gson;


import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivityNew extends Activity {

    private EditText et_user_name, et_yanzheng, et_user_password, et_yaoqingma, et_user_password_too;
    private Button btn_huoqu, btn_regist;
    private ImageView iv_qingkong1, iv_qingkong2, iv_qingkong3, iv_back, iv_qingkong4, iv_show, iv_show_too;
    private TextView tv_tongyi, tv_home_title;
    private MyHandler handler;
    private int m = 60;
    private boolean tongyi = false;
    private RelativeLayout rl_yaoqing, rl_password, rl_password_too, rl_username, rl_yanzheng;
    private LinearLayout ll_xieyi;
    private String from;
    private View view_xian;
    private boolean show = false, show_too = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        initview();

        listener();

        handler = new MyHandler();

    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 1:
                    //
                    if (m != 0) {
                        btn_huoqu.setText(m + "秒后重新获取");
                        handler.removeMessages(1);
                        handler.sendEmptyMessageDelayed(1, 1000);
                        m--;
                    } else {
                        handler.removeMessages(1);
                        btn_huoqu.setText("获取验证码");
                        btn_huoqu.setClickable(true);
                        m = 60;
                    }


                    break;

            }
        }

    }

    private void listener() {
        MyListener listener = new MyListener();
        iv_qingkong1.setOnClickListener(listener);
        iv_qingkong2.setOnClickListener(listener);
        iv_qingkong3.setOnClickListener(listener);
        iv_qingkong4.setOnClickListener(listener);
        btn_huoqu.setOnClickListener(listener);
        btn_regist.setOnClickListener(listener);
        tv_tongyi.setOnClickListener(listener);
        iv_back.setOnClickListener(listener);
        iv_show.setOnClickListener(listener);
        iv_show_too.setOnClickListener(listener);

        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                if (from.equals("updatephone")) {
                    //修改手机号
                    isUpdataPhone();
                }

                if (TextUtils.isEmpty(et_user_name.getText().toString())) {
                    btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));
                    iv_qingkong1.setVisibility(View.INVISIBLE);
                } else {
                    iv_qingkong1.setVisibility(View.VISIBLE);
                }


            }
        });

        et_yanzheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                if (from.equals("updatephone")) {
                    //修改手机号
                    isUpdataPhone();


                } else {
                    if (TextUtils.isEmpty(et_yanzheng.getText().toString())) {
                        btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));
                    }
                }


            }
        });


        et_user_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(et_user_password.getText().toString())) {
                    iv_qingkong2.setVisibility(View.INVISIBLE);
                } else {

                    iv_qingkong2.setVisibility(View.VISIBLE);
                }


                if (from.equals("updatepassword")) {
                    //修改密码
                    if (et_user_password.getText().length() >= 6 && et_user_password_too.getText().length() >= 6) {

                        btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

                    } else {
                        btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

                    }
                } else {
                    if (isaBoolean()) {

                        if (from.equals("forget")) {
                            btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));
                        } else {
                            if (tongyi) {

                                btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));
                            }
                        }


                    } else {

                        btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

                    }
                }


                //光标位置
                et_user_password.requestFocus();
                et_user_password.setSelection(et_user_password.getText().length());


            }
        });


        et_yaoqingma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(et_yaoqingma.getText().toString())) {

                    iv_qingkong3.setVisibility(View.INVISIBLE);
                } else {

                    iv_qingkong3.setVisibility(View.VISIBLE);
                }


            }
        });


        et_user_password_too.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {


                if (TextUtils.isEmpty(et_user_password_too.getText().toString())) {

                    iv_qingkong4.setVisibility(View.INVISIBLE);
                } else {

                    iv_qingkong4.setVisibility(View.VISIBLE);
                }


                if (et_user_password.getText().length() < 6 || et_user_password_too.getText().length() < 6) {

                    btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

                } else {
                    btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

                }

                //光标位置
                et_user_password_too.requestFocus();
                et_user_password_too.setSelection(et_user_password_too.getText().length());

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void isUpdataPhone() {
        if (!TextUtils.isEmpty(et_yanzheng.getText().toString()) && et_user_name.getText().toString().length() == 11) {
            btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));
        } else {
            btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

        }
    }


    private void initview() {

        iv_back = findViewById(R.id.iv_back);

        tv_home_title = findViewById(R.id.tv_home_title);
        tv_home_title.setText("注册");

        et_user_name = findViewById(R.id.et_user_name);
        et_yanzheng = findViewById(R.id.et_yanzheng);
        btn_huoqu = findViewById(R.id.btn_huoqu);
        et_user_password = findViewById(R.id.et_user_password);
        iv_qingkong1 = findViewById(R.id.iv_qingkong1);
        iv_qingkong2 = findViewById(R.id.iv_qingkong2);
        iv_qingkong3 = findViewById(R.id.iv_qingkong3);
        et_yaoqingma = findViewById(R.id.et_yaoqingma);
        btn_regist = findViewById(R.id.btn_regist);
        tv_tongyi = findViewById(R.id.tv_tongyi);
        rl_yaoqing = findViewById(R.id.rl_yaoqing);
        ll_xieyi = findViewById(R.id.ll_xieyi);
        rl_password = findViewById(R.id.rl_password);
        rl_password_too = findViewById(R.id.rl_password_too);
        et_user_password_too = findViewById(R.id.et_user_password_too);
        iv_qingkong4 = findViewById(R.id.iv_qingkong4);
        rl_username = findViewById(R.id.rl_username);
        rl_yanzheng = findViewById(R.id.rl_yanzheng);
        iv_show = findViewById(R.id.iv_show);
        iv_show_too = findViewById(R.id.iv_show_too);

        et_user_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_user_password_too.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        from = getIntent().getStringExtra("from");

        if (from.equals("forget")) {
            //点忘记密码进来的
            tv_home_title.setText("忘记密码");
            rl_yaoqing.setVisibility(View.GONE);
            ll_xieyi.setVisibility(View.INVISIBLE);
            et_user_password.setHint("请输入新密码");
            btn_regist.setText("确定");

        } else if (from.equals("updatephone")) {
            //点修改手机号进来的
            tv_home_title.setText("修改手机号");
            rl_yaoqing.setVisibility(View.GONE);
            ll_xieyi.setVisibility(View.INVISIBLE);
            rl_password.setVisibility(View.GONE);
            btn_regist.setText("立即修改");
            et_user_name.setHint("手机号");
        } else if (from.equals("updatepassword")) {
            //修改密码
            tv_home_title.setText("修改密码");
            rl_yaoqing.setVisibility(View.GONE);
            ll_xieyi.setVisibility(View.INVISIBLE);
            btn_regist.setText("立即修改");
            et_user_password.setHint("请输入旧密码（6-16位）");
            rl_yanzheng.setVisibility(View.GONE);
            rl_username.setVisibility(View.GONE);
            rl_password_too.setVisibility(View.VISIBLE);
        }

    }

    class MyListener implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_qingkong1:
                    et_user_name.setText("");
                    et_user_password.setText("");
                    et_user_password_too.setText("");
                    break;
                case R.id.iv_qingkong2:
                    et_user_password.setText("");
                    break;
                case R.id.iv_qingkong3:
                    et_yaoqingma.setText("");
                    break;
                case R.id.iv_qingkong4:
                    et_user_password_too.setText("");
                    break;

                case R.id.btn_huoqu:
                    //点击获取验证码按钮

                    //获取手机号
                    String phone = et_user_name.getText().toString();
                    String type;
                    if (tv_home_title.getText().equals("忘记密码")) {
                        type = "1";//忘记密码
                    } else if (tv_home_title.getText().equals("修改手机号")) {
                        type = "2";//修改手机号
                    } else {
                        type = "0";//注册
                    }


                    sendyanzheng(phone, type);


                    break;
                case R.id.btn_regist:

                    //点注册,或者忘记密码
                    if (tv_home_title.getText().equals("忘记密码")) {

                        if (isaBoolean()) {

                            forget(et_user_name.getText().toString(), et_user_password.getText().toString(), et_yanzheng.getText().toString());

                        }


                    } else if (tv_home_title.getText().equals("修改手机号")) {

                        if (!TextUtils.isEmpty(et_user_name.getText().toString()) && !TextUtils.isEmpty(et_yanzheng.getText().toString())
                                && et_user_name.getText().length() == 11) {
                            updatephone(SharepUtils.getUserUSER_ID(RegisterActivityNew.this),
                                    SharepUtils.getUserUSER_KEY(RegisterActivityNew.this),
                                    et_user_name.getText().toString(), et_yanzheng.getText().toString());
                        }


                    } else if (tv_home_title.getText().equals("修改密码")) {

                        if (!TextUtils.isEmpty(et_user_password.getText().toString()) &&
                                !TextUtils.isEmpty(et_user_password_too.getText().toString()) &&
                                et_user_password.getText().length() >= 6
                                && et_user_password_too.getText().length() >= 6) {

                            updatepassword(SharepUtils.getUserUSER_ID(RegisterActivityNew.this),
                                    SharepUtils.getUserUSER_KEY(RegisterActivityNew.this),
                                    et_user_password.getText().toString(),
                                    et_user_password_too.getText().toString());


                        }

                    } else {

                        if (isaBoolean()) {
                            //检查是否同意协议
                            if (tongyi) {

                                regist(et_user_name.getText().toString(), et_user_password.getText().toString(), et_yanzheng.getText().toString(), et_yaoqingma.getText().toString());

                            } else {
                                ToastUtil.show_center(RegisterActivityNew.this, "请先阅读并勾选《爱点读用户协议》");
                            }
                        }

                    }


                    break;

                case R.id.tv_tongyi:

                    //同意协议
                    if (!tongyi) {
                        //未同意
                        tv_tongyi.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_tongyi), null, null, null);

                        if (isaBoolean()) {

                            btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

                        }


                        tongyi = true;
                    } else {
                        btn_regist.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

                        tv_tongyi.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_butongyi), null, null, null);

                        tongyi = false;
                    }


                    break;
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.iv_show:


                    if (et_user_password.getText().length() != 0) {
                        if (show) {
                            iv_show.setImageDrawable(getResources().getDrawable(R.drawable.suoclose));
                            et_user_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            show = false;
                        } else {
                            iv_show.setImageDrawable(getResources().getDrawable(R.drawable.icon_password_open));
                            et_user_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            show = true;
                        }
                    }


                    break;
                case R.id.iv_show_too:

                    if (et_user_password_too.getText().length() != 0) {
                        if (show_too) {
                            iv_show_too.setImageDrawable(getResources().getDrawable(R.drawable.suoclose));
                            et_user_password_too.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            show_too = false;
                        } else {
                            iv_show_too.setImageDrawable(getResources().getDrawable(R.drawable.icon_password_open));
                            et_user_password_too.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            show_too = true;
                        }
                    }

                    break;


            }


        }
    }

    private boolean isaBoolean() {

        return !TextUtils.isEmpty(et_yanzheng.getText().toString()) && et_user_password.getText().length() >= 6 && et_user_name.getText().length() == 11;

    }

    private void updatepassword(String uid, String key, String old, final String newp) {

        String url = Constants.UpdatePassWord;

        RequestParams params=new RequestParams(url);
        params.addBodyParameter("uid", uid);
        params.addBodyParameter("key", key);
        params.addBodyParameter("password", old);
        params.addBodyParameter("newpassword", newp);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");

                    if (code.equals("2000")) {
                        //成功
                        finish();

                        //
                        SharepUtils.setUser_psw(RegisterActivityNew.this, newp);
                    }

                    ToastUtil.show_center(RegisterActivityNew.this, jsonObject.getString("msg"));
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

    private void updatephone(String uid, String key, String phone, String code) {


        String url = Constants.UpdatePhone;

        RequestParams params=new RequestParams(url);
        params.addBodyParameter("uid", uid);
        params.addBodyParameter("key", key);
        params.addBodyParameter("code", code);
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("type", "2");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");

                    if (code.equals("2000")) {
                        //成功
                        finish();
                    }

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

    private void forget(final String username, final String password, String code) {


        code = code.replaceAll(" ", "");

        String url = Constants.FroGet;

        RequestParams params=new RequestParams(url);
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);
        params.addBodyParameter("code", code);
        params.addBodyParameter("type", "1");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {


                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");

                    if (code.equals("2000")) {

                        //请求登录接口
                        denglu(username, password);

                    }
//
                    ToastUtil.show_center(RegisterActivityNew.this, jsonObject.getString("msg"));

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

    private void denglu(final String username, final String password) {

        String url = Constants.Deng_Lu;


        RequestParams params=new RequestParams(url);
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    saveAndBack(result, username, password);


                } catch (Exception e) {


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

    private void regist(final String username, final String password, String code, String invite_code) {


        String url = Constants.Zhu_Ce;


        RequestParams params=new RequestParams(url);
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);
        params.addBodyParameter("code", code);

        if (!TextUtils.isEmpty(invite_code)) {
            params.addBodyParameter("invite_code", invite_code);
        } else {
            params.addBodyParameter("invite_code", "1");
        }
        params.addBodyParameter("regsource", "1");
        params.addBodyParameter("regchannel", "0");
        params.addBodyParameter("token_id", "1");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    saveAndBack(result, username, password);


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

    private void saveAndBack(String response, String username, String password) {
        UserInfoBean packlist = new Gson().fromJson(response, UserInfoBean.class);

        int code = packlist.getCode();

        if (code == 2000) {


            //成功
            UserInfoBean.DataBean mUserInfo = packlist.getData();
            SharepUtils.saveUserInfonew(RegisterActivityNew.this, mUserInfo);
            SharepUtils.saveLogin(RegisterActivityNew.this);

            SharepUtils.setUserphone(RegisterActivityNew.this, username);
            SharepUtils.setUser_psw(RegisterActivityNew.this, password);

            setResult(2);
            finish();
        }
    }


    private void sendyanzheng(String phone, String type) {

        String url = Constants.Send_YanZheng;
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("mobile", phone);
        params.addBodyParameter("type", type);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");

                    if (code.equals("2000")) {

                        //收起键盘
                        View v = getCurrentFocus();
                        hideKeyboard(v.getWindowToken());   //收起键盘

                        //发送成功
                        ToastUtil.show_center(RegisterActivityNew.this, jsonObject.getString("msg"));

                        //计时60秒，后重新发送
                        btn_huoqu.setClickable(false);
                        //倒计时
                        handler.sendEmptyMessage(1);


                    } else {


                        Toast.makeText(RegisterActivityNew.this,jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {


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

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



}
