package com.example.zjq.news.utils;

public class Constants {


    private static final String NewsMian = "https://www.mxnzp.com/api";

    public static final String APPID = "yhlrkxpsofduvtjb";
    public static final String APPSECRET = "OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09";

    //    https://www.mxnzp.com
//    https://www.mxnzp.com/api/news/list?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&typeId=509&page=1
    // http://www.mxnzp.com/api/news/details?newsId=220132&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09
    // http://www.mxnzp.com/api/news/types?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09

    //新闻
    public static final String NewsTypes = NewsMian + "/news/types";
    public static final String NewsList = NewsMian + "/news/list";
    public static final String NewsDetail = NewsMian + "/news/details";

    // https://www.mxnzp.com/api/daily_word/recommend?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&count=20
    //精选语句
    public static final String everyday_word = NewsMian + "/daily_word/recommend";

    // https://www.mxnzp.com/api/history/today?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&type=1
    //历史上的今天
    public static final String lishi = NewsMian + "/history/today";

    // https://www.mxnzp.com/api/jokes/list?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&page=1


    // https://www.mxnzp.com/api/music/song/search?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&page=1&keyWord=黑夜问白天


    // https://www.mxnzp.com/api/music/song/detail?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&songId=309769


    //笑话
    public static final String xiaohua = NewsMian + "/jokes/list";

    //每日一文(随机)
    public static final String everyday_wen = "https://interface.meiriyiwen.com/article/random";


    //
    public static String Ai_BASE = "http://ceshi.admin.51titi.net/api";
    public static String UserInfo = Ai_BASE + "/user/info";
    public static String EditUserInfo = Ai_BASE + "/user/editInfo";
    public static String PIC_URL = Ai_BASE + "/user/updateAvatar";//头像修改
    public static String Deng_Lu = Ai_BASE + "/user/login";
    public static String UpdatePassWord = Ai_BASE + "/user/updatePwd";
    public static String UpdatePhone = Ai_BASE + "/user/updatePhone";
    public static String FroGet = Ai_BASE + "/user/resetPass";
    public static String Zhu_Ce = Ai_BASE + "/user/register";
    public static String Send_YanZheng = Ai_BASE + "/user/sendCode";







}
