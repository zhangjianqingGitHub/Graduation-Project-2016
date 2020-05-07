package com.example.zjq.news.utils;

public class Constants {


    private static final String NewsMian = "https://www.mxnzp.com/api";

    public static final String APPID = "yhlrkxpsofduvtjb";
    public static final String APPSECRET = "OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09";

    //    https://www.mxnzp.com
//    https://www.mxnzp.com/api/news/list?&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&typeId=522&page=1
    // http://www.mxnzp.com/api/news/details?newsId=220132&app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09
    // http://www.mxnzp.com/api/news/types?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09

    //新闻
    public static final String NewsTypes = NewsMian + "/news/types";
    public static final String NewsList = NewsMian + "/news/list";
    public static final String NewsDetail = NewsMian + "/news/details";

    // https://www.mxnzp.com/api/daily_word/recommend?app_id=yhlrkxpsofduvtjb&app_secret=OXlid3FmY0IrTVRtR1NQYUYyRGs2Zz09&count=20


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



    public static String xiaohua1="我们校长有天路过学校后门，忽然听到一句：“我要考牛津！” 校长顿时感动不已，没想到自己学校有如此有志青年，决定看看是哪位，忽然又听到一句： “再给我来两串大腰子！";
    public static String xiaohua2="女朋友出差刚回来，问我：“狗狗呢？”突然想逗她一下就说：“送人了。”看到她快急哭了的表情，我哈哈一笑，端起早上灶上的锅说道：“哈哈！骗你的！在锅里呢！";
    public static String xiaohua3="上课玩手机被班主任发现。班主任生气的说：“你说说，这是第几次了？真是烂泥扶不上墙！让你家长现在来见我！！”我为难的说：“我爸爸在工作。”“那让你妈来见我！”我实在忍无可忍了，说：“爸！你还有完没完了！你昨天刚和妈吵架，谁也不理谁，现在让她过来干什么！”“让她理我。”";
    public static String xiaohua4="刚和男神表白 我162 男神183 男神说喜欢最萌身高差 差个12cm的那种 我心中暗喜 可是他却拒绝我了 然后说 想找一个195的女生…";
    public static String xiaohua5="爷爷退休了，报名上了老年大学正读一年级的孙子好奇地问：“爷爷，你还读书啊?”爷爷说：“我读书有什么不好吗?孙子说：“好是好，就是万一你学校通知开家长会，那你怎么办呢?”......";






}
