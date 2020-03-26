package com.example.zjq.news.pager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.Util;
import com.example.zjq.news.R;
import com.example.zjq.news.activity.EditDataActivity;
import com.example.zjq.news.activity.MainActivity;
import com.example.zjq.news.activity.SettingActivity;
import com.example.zjq.news.base.BasePager;
import com.example.zjq.news.login.LoginActivityNew;
import com.example.zjq.news.utils.Constants;
import com.example.zjq.news.utils.DensityUtil;
import com.example.zjq.news.utils.GlideCircleBorderTransform;
import com.example.zjq.news.utils.SharepUtils;

public class MePager extends BasePager {

    private View view;
    private RelativeLayout re_me_update, re_me_fankui, re_me_call;
    public TextView tv_user_name, tv_editdata, tv_me_version;
    public ImageView iv_me_setting, iv_home_version_bar, iv_me_photo;
    public LinearLayout ll_login, ll_user;
    private MainActivity mainActivity;

    public MePager(Context context) {
        super(context);

        findView();
    }

    private void findView() {

        view = View.inflate(context, R.layout.pager_me, null);
        fl_content.addView(view);
        top.setVisibility(View.GONE);

        tv_user_name = view.findViewById(R.id.tv_user_name);
        iv_me_setting = view.findViewById(R.id.iv_me_setting);
        iv_home_version_bar = view.findViewById(R.id.iv_home_version_bar);
        re_me_update = view.findViewById(R.id.re_me_update);
        tv_me_version = view.findViewById(R.id.tv_me_version);
        tv_editdata = view.findViewById(R.id.tv_editdata);
        re_me_fankui = view.findViewById(R.id.re_me_fankui);
        re_me_call = view.findViewById(R.id.re_me_call);
        iv_me_photo = view.findViewById(R.id.iv_me_photo);
        ll_login = view.findViewById(R.id.ll_login);
        ll_user = view.findViewById(R.id.ll_user);


    }

    @Override
    public void initData() {
        super.initData();


        if (SharepUtils.IsLogin(context)) {

            showUserInfo();
        } else {
            ll_login.setVisibility(View.VISIBLE);
            ll_user.setVisibility(View.GONE);
            tv_editdata.setVisibility(View.INVISIBLE);
        }

        pbLoading.setVisibility(View.GONE);

        listener();

        mainActivity = (MainActivity) context;

    }


    private void listener() {
        MyListener listener = new MyListener();
        tv_user_name.setOnClickListener(listener);
        iv_me_setting.setOnClickListener(listener);
        tv_editdata.setOnClickListener(listener);
        re_me_update.setOnClickListener(listener);
        re_me_fankui.setOnClickListener(listener);
        re_me_call.setOnClickListener(listener);
        iv_me_photo.setOnClickListener(listener);
        ll_login.setOnClickListener(listener);

    }


    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.re_me_fankui:
//                    if (SharepUtils.IsLogin(context)) {
//                        Intent mecontact = new Intent(context, SettingFanActivity.class);
//                        Bundle bundle = new Bundle();
//
//                        bundle.putString("type", "2");//1、type=1是口语评测2、type=2是点读原生页面
//                        mecontact.putExtras(bundle);
//                        context.startActivity(mecontact);
//                    }
//                    break;

                case R.id.re_me_call:
//                    Intent intentgo1 = new Intent(context, ActivityUtils.openActivity("https://qr.wxswvip.com/site/jump/id/62/code/"));
//
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("title", "联系我们");
//                    bundle1.putString("URL", "https://qr.wxswvip.com/site/jump/id/62/code/");
//                    intentgo1.putExtras(bundle1);
//                    context.startActivity(intentgo1);
                    break;
                case R.id.tv_user_name: {

                    Intent intent = new Intent(context, EditDataActivity.class);
                    context.startActivity(intent);

                }


                break;
                case R.id.iv_me_setting: {

                    if (SharepUtils.IsLogin(context)) {
                        Intent intent = new Intent(context, SettingActivity.class);

                        mainActivity.getContentFragment().startActivityForResult(intent, 2);


                    } else {

                        login();
                    }

                }

                break;
                case R.id.tv_editdata:
                    //编辑资料
                    if (SharepUtils.IsLogin(context)) {
                        Intent intent = new Intent(context, EditDataActivity.class);

                        mainActivity.getContentFragment().startActivityForResult(intent, 3);
                    } else {

                        login();
                    }

                    break;

                case R.id.iv_me_photo: {

                    if (SharepUtils.IsLogin(context)) {

                        Intent intent = new Intent(context, EditDataActivity.class);
                        mainActivity.getContentFragment().startActivityForResult(intent, 3);

                    } else {
                        login();
                    }

                }
                break;
                case R.id.ll_login: {
                    login();

                }

                break;

            }

        }
    }

    private void login() {
        Intent intent = new Intent(context, LoginActivityNew.class);
        mainActivity.getContentFragment().startActivityForResult(intent, 1);
    }




//    private void showDialog() {
//
//        final AlertDialog dialog = new AlertDialog.Builder();
//                .setContentView(R.layout.dialog_ai_diandu)
//                .setText(R.id.buyactivity_sure, "去登录")
//                .setText(R.id.buyactivity_sure_aler_tv1, "请先登录")
//                .show();
//
//        dialog.setOnClickListener(R.id.buyactivity_sure, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, LoginActivityNew.class);
//                context.startActivity(intent, 1);
//
//                dialog.dismiss();
//            }
//        });
//        dialog.setOnClickListener(R.id.buyactivity_cancel, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//
//    }

    public void showUserInfo() {

        ll_login.setVisibility(View.GONE);
        ll_user.setVisibility(View.VISIBLE);
        tv_editdata.setVisibility(View.VISIBLE);

        //设置昵称

        String name = SharepUtils.getUserUSER_NAME(context);

        if (TextUtils.isEmpty(name)) {
            tv_user_name.setText("昵称");
        } else {
            tv_user_name.setText(name);
        }


        RequestOptions myOptions = new RequestOptions()
                .placeholder(R.drawable.iv_me_header)
                .optionalTransform
                        (new GlideCircleBorderTransform(DensityUtil.dip2px(context, 2), context.getResources().getColor(R.color.white)));
        Glide.with(context).load(SharepUtils.getAvatar(context)).apply(myOptions).into(iv_me_photo);


    }
}
