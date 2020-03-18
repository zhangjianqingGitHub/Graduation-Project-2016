package com.example.zjq.news.utils;

public class Constants {

    /*聚合数据*/
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
    public static final String BASE_URL_JUHE = "https://v.juhe.cn/toutiao/index";
    public static final String key = "b0b89109785634c4fcd0a3ca78cd3ad9";

    /**
     * 新闻中心的网络地址
     */
    public static final String NEWSCENTER_PAGER_URL = BASE_URL_JUHE + "?type=" + type + "&key=" + key;


    /*阿凡达数据*/
    public static final String BASE_URL_JISU = "http://api.avatardata.cn/GuoNeiNews";
    public static final String key_AFanDa = "c4d3e9eb577c4559893e4ff4cd701eb2";


    public static final String TABDETAILSPAGER = BASE_URL_JISU + "/Query";


    //app_id:yhlrkxpsofduvtjb   app_secret:OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09

//    https://www.mxnzp.com/api/news/list?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&typeId=509&page=1
    // http://www.mxnzp.com/api/news/details?newsId=F686PCT500258105&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09
    // http://www.mxnzp.com/api/news/types?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09

    public static final String NewsMian = "https://www.mxnzp.com/api";

    public static final String APPID = "yhlrkxpsofduvtjb";
    public static final String APPSECRET = "OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09";

    public static final String NewsTypes = NewsMian + "/news/types";
    public static final String NewsList = NewsMian + "/news/list";
    public static final String NewsDetail = NewsMian + "/news/details";


}
