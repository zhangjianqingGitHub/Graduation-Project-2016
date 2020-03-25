package com.example.zjq.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.example.zjq.news.bean.UserInfoBean;


public class SharepUtils {


    public final static String USER_ID = "id";
    public final static String USER_NAME = "name";
    public final static String USER_KEY = "key";
    public final static String USER_PHONE = "userphone";
    public final static String USER_PSW = "userpsw";
    public final static String USER_AVATAR = "avatar";

    /**
     * 默认SharePreferences对象
     */
    private static SharedPreferences mSharePreferences = null;
    /**
     * 文件名
     */

    public final static String USER_SEX = "sex";
    public final static String USER_AGE = "age";
    public final static String LEVEL = "level";
    public final static String lOGINTIME = "login_time";
    public final static String LASTTIME = "last_time";
    public final static String QRCODE = "qrcode";
    public final static String CREATETIME = "create_time";
    public final static String REGSOURCE = "regsource";
    public final static String REGCHANNEL = "regchannel";
    public final static String TOKENID = "tokenid";

    public final static String USERNAME = "username";
    public final static String ORIGINAVATAR = "originavatar";
    public final static String LGRADE = "lgrade";
    public final static String Title = "Title";
    public final static String AddressCode = "addresscode";
    public final static String Invite_code = "Invite_code";

    private static final String TAG = "SharepUtils";


    public static void setUSER_AVATAR(Context mContext, String value) {

        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor meEditor = spf.edit();
        meEditor.putString(USER_AVATAR, value);
        meEditor.apply();

    }


    public static void saveUserInfonew(Context mContext, UserInfoBean.DataBean userInfo) {
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);

        Editor editor = spf.edit();
        editor.clear();
        editor.putString(USER_ID, userInfo.getUid());
        editor.putString(USER_SEX, userInfo.getSex());
        editor.putString(USER_AGE, userInfo.getAge());
        editor.putString(USER_NAME, userInfo.getName());
        editor.putString(USER_AVATAR, userInfo.getAvatar());
        editor.putString(LEVEL, userInfo.getLevel());
        editor.putString(lOGINTIME, userInfo.getLogin_time());
        editor.putString(LASTTIME, userInfo.getLast_time());
        editor.putString(USER_KEY, userInfo.getKey());
        editor.putString(QRCODE, userInfo.getQrcode());
        editor.putString(CREATETIME, userInfo.getCreate_time());
        editor.putString(REGSOURCE, userInfo.getRegsource());
        editor.putString(REGCHANNEL, userInfo.getRegchannel());
        editor.putString(TOKENID, userInfo.getTokenid());
        editor.putString(USERNAME, userInfo.getUsername());
        editor.putString(ORIGINAVATAR, userInfo.getOriginavatar());
        editor.putString(Invite_code, userInfo.getInvite_code());
        editor.apply();

    }


    // 保存登录信息
    public static void saveLogin(Context context) {
        if (context == null)
            return;
        int mode = Activity.MODE_PRIVATE;

        SharedPreferences mySharedPreferences = context.getSharedPreferences("beisuPreferences", mode);
        if (mySharedPreferences != null) {
            Editor editor = mySharedPreferences.edit();
            editor.putBoolean("Login", true);
            editor.apply();
        }
    }

    //获取是否登录
    public static boolean IsLogin(Context mContext) {
        if (mContext == null) {
            return false;
        }
        SharedPreferences spf = mContext.getSharedPreferences("beisuPreferences", Context.MODE_PRIVATE);
        return spf.getBoolean("Login", false);
    }


    public static String getUserUSER_KEY(Context mContext) {
        if (mContext == null) {
            return null;
        }
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_KEY, "");
    }

    public static String getUserUSER_ID(Context mContext) {
        if (mContext == null) {
            return null;
        }
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_ID, "");
    }


    public static void setUserphone(Context mContext, String value) {
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor meEditor = spf.edit();
        meEditor.putString(USER_PHONE, value);
        meEditor.apply();

    }

    public static void clearUserInfo(Context mContext) {
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor mEditor = spf.edit();
        mEditor.putString(USER_ID, "");
        mEditor.putString(USER_KEY, "");
        mEditor.clear();
        mEditor.apply();
    }

    public static void setUserUSER_NAME(Context mContext, String value) {

        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor meEditor = spf.edit();
        meEditor.putString(USER_NAME, value);
        meEditor.apply();
    }

    public static String getUserUSER_NAME(Context mContext) {
        if (mContext == null) {
            return null;
        }
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_NAME, "");
    }


    public static String getUserphone(Context mContext) {
        if (mContext == null) {
            return null;
        }
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_PHONE, "");
    }

    // 取消登录信息
    public static void deleLogin(Context context) {
        if (context == null)
            return;
        int mode = Activity.MODE_PRIVATE;

        SharedPreferences mySharedPreferences = context.getSharedPreferences("beisuPreferences", mode);
        if (mySharedPreferences != null) {
            Editor editor = mySharedPreferences.edit();
            editor.putBoolean("Login", false);
            editor.apply();
        }
    }


    public static String getUser_psw(Context mContext) {
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_PSW, "");
    }

    public static void setUser_psw(Context mContext, String value) {
        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Editor meEditor = spf.edit();
        meEditor.putString(USER_PSW, value);
        meEditor.commit();
    }


    public static String getAvatar(Context mContext) {

        SharedPreferences spf = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return spf.getString(USER_AVATAR, "");

    }

}
