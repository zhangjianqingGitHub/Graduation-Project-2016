package com.example.zjq.news.utils;

public class Constants {



    private static final String NewsMian = "https://www.mxnzp.com/api";

    public static final String APPID = "yhlrkxpsofduvtjb";
    public static final String APPSECRET = "OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09";

    //    https://www.mxnzp.com
//    https://www.mxnzp.com/api/news/list?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&typeId=509&page=1
    // http://www.mxnzp.com/api/news/details?newsId=220132&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09
    // http://www.mxnzp.com/api/news/types?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09

    //新闻
    public static final String NewsTypes = NewsMian + "/news/types";
    public static final String NewsList = NewsMian + "/news/list";
    public static final String NewsDetail = NewsMian + "/news/details";

   // https://www.mxnzp.com/api/daily_word/recommend?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&count=20
    //精选语句
    public static final String everyday_word = NewsMian + "/daily_word/recommend";



}
