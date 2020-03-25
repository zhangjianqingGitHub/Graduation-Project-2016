package com.example.zjq.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zjq.news.R;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.GsonResultok;
import com.example.zjq.news.utils.SharepUtils;
import com.google.gson.Gson;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditUserInfoItemActivity extends Activity {
    private EditText et_me_text;
    private ImageView iv_me_self_off, iv_me_sex_women, iv_me_sex_man, iv_back;
    private Button bt_me_self_summit;
    private String type, hint, grade_name;
    private LinearLayout ll_sex, ll_quding;
    private RelativeLayout re_me_sex_women, re_me_sex_man, rl_kuang;
    private ListView lv_grade;
    private List<String> list_name, list_id;
    private TextView tv_home_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info_item);

        initview();
        initdata();
        listener();

    }

    private void initdata() {

        Intent intent = getIntent();
        type = intent.getStringExtra("k");
        hint = intent.getStringExtra("hint");

        if (type.equals("name")) {
            //修改昵称
            tv_home_title.setText("修改昵称");
        } else if (type.equals("age")) {
            tv_home_title.setText("修改年龄");
        } else if (type.equals("school")) {
            tv_home_title.setText("修改学校");
        }


        if (type.equals("sex")) {


            tv_home_title.setText("修改性别");

            //性别修改
            ll_sex.setVisibility(View.VISIBLE);
            ll_quding.setVisibility(View.GONE);
            rl_kuang.setVisibility(View.GONE);

            if (hint.equals("女")) {
                iv_me_sex_women.setImageResource(R.drawable.icon_me_sex_over);
                iv_me_sex_man.setImageResource(R.drawable.icon_me_sex_no);
            } else {
                iv_me_sex_women.setImageResource(R.drawable.icon_me_sex_no);
                iv_me_sex_man.setImageResource(R.drawable.icon_me_sex_over);
            }

        } else {
            et_me_text.setHint(hint);

        }

    }


    private void listener() {
        MyListener listener = new MyListener();
        iv_me_self_off.setOnClickListener(listener);
        bt_me_self_summit.setOnClickListener(listener);
        re_me_sex_women.setOnClickListener(listener);
        re_me_sex_man.setOnClickListener(listener);
        iv_back.setOnClickListener(listener);

        lv_grade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updata(type, list_id.get(position));
                grade_name = list_name.get(position);

            }
        });


        et_me_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(et_me_text.getText().toString())) {
                    iv_me_self_off.setVisibility(View.INVISIBLE);

                    bt_me_self_summit.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));


                } else {

                    bt_me_self_summit.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan));

                    iv_me_self_off.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void initview() {

        iv_back = findViewById(R.id.iv_back);

        et_me_text = findViewById(R.id.et_me_text);
        iv_me_self_off = findViewById(R.id.iv_me_self_off);
        bt_me_self_summit = findViewById(R.id.bt_me_self_summit);
        ll_sex = findViewById(R.id.ll_sex);
        ll_quding = findViewById(R.id.ll_quding);
        re_me_sex_women = findViewById(R.id.re_me_sex_women);
        re_me_sex_man = findViewById(R.id.re_me_sex_man);
        iv_me_sex_women = findViewById(R.id.iv_me_sex_women);
        iv_me_sex_man = findViewById(R.id.iv_me_sex_man);
        rl_kuang = findViewById(R.id.rl_kuang);
        lv_grade = findViewById(R.id.lv_grade);
        tv_home_title = findViewById(R.id.tv_home_title);

        if (TextUtils.isEmpty(et_me_text.getText().toString())) {

            bt_me_self_summit.setBackground(getResources().getDrawable(R.drawable.background_bg_banyuan_hui));

        }
    }


    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_me_self_off:
                    et_me_text.setText("");
                    break;

                case R.id.bt_me_self_summit:

                    if (!TextUtils.isEmpty(et_me_text.getText().toString())) {
                        updata(type, et_me_text.getText().toString());
                    }

                    break;
                case R.id.re_me_sex_women:
                    iv_me_sex_women.setImageResource(R.drawable.icon_me_sex_over);
                    iv_me_sex_man.setImageResource(R.drawable.icon_me_sex_no);

                    updata(type, "2");

                    break;
                case R.id.re_me_sex_man:
                    iv_me_sex_women.setImageResource(R.drawable.icon_me_sex_no);
                    iv_me_sex_man.setImageResource(R.drawable.icon_me_sex_over);
//                    vlue = "1";男
                    updata(type, "1");

                    break;
                case R.id.iv_back:
                    finish();
                    break;
            }

        }
    }

    private void updata(final String type, final String data) {

        String url = Constants.EditUserInfo;

        RequestParams params = new RequestParams(url);

        params.addBodyParameter("uid", SharepUtils.getUserUSER_ID(EditUserInfoItemActivity.this));
        params.addBodyParameter("key", SharepUtils.getUserUSER_KEY(EditUserInfoItemActivity.this));
        params.addBodyParameter("k", type);
        params.addBodyParameter("v", data);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    GsonResultok gsonResultok = new Gson().fromJson(result, GsonResultok.class);

                    int code = gsonResultok.getCode();

                    if (code == 2000) {

                        Intent intent = new Intent();
                        intent.putExtra("type", type);
                        if (type.equals("sex")) {

                            if (data.equals("1")) {
                                intent.putExtra("data", "男");
                            } else {
                                intent.putExtra("data", "女");

                            }

                        } else if (type.equals("grade_id")) {

                            intent.putExtra("data", grade_name);

                        } else {
                            intent.putExtra("data", data);
                        }
                        setResult(1, intent);

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

}
