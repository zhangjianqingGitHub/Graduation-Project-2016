package com.example.zjq.news.utils;

public class Constants {

    public static final String type_top = "top";//

    public static final String type_shehui = "shehui";
    public static final String type_yule = "yule";
    public static final String type_tiyu = "tiyu";
    public static final String type_junshi = "junshi";
    public static final String type_keji = "keji";
    public static final String type_caijing = "caijing";
    public static final String type_shishang = "shishang";

    //guonei(国内),guoji(国际),

    //top(头条，默认),shehui(社会),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)

    public static String type = "";
    public static final String BASE_URL = "https://v.juhe.cn/toutiao/index";
    public static final String key = "b0b89109785634c4fcd0a3ca78cd3ad9";

    /**
     * 新闻中心的网络地址
     */
    public static final String NEWSCENTER_PAGER_URL = BASE_URL + "?type=" + type + "&key=" + key;
}
