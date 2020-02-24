package com.example.zjq.news.bean;

import java.util.List;

public class NewsCenterPagerBean {


    /**
     * code : 1
     * msg : 数据返回成功！现已提供app_id方式请求接口，不限速，不限流，不封IP，可在自建服务器调用api，欢迎升级使用！非app_id请求的方式将于不久后关闭，请提前替换成app_id请求的方式，详情请访问：https://github.com/MZCretin/RollToolsApi#%E8%A7%A3%E9%94%81%E6%96%B0%E6%96%B9%E5%BC%8F
     * data : [{},{},{},{},{},{},{},{},{"title":"各银行行用卡用卡小技巧","imgList":["http://bjnewsrec-cv.ws.126.net/little286199f2100j00q658in0023d000s600hlp.jpg"],"source":"POS机服务台","newsId":"F63383140539C0P8","digest":"","postTime":"2020-02-23 15:23:21","videoList":null},{"title":"银行理财产品平均收益率连升三周至4.03% 农行再次夺人眼球","imgList":["http://bjnewsrec-cv.ws.126.net/little986050f626cc45ff05a852ea74a3cbb4c3a.jpg"],"source":"金融界","newsId":"F65T521C0519QIKK","digest":"","postTime":"2020-02-24 17:35:27","videoList":null},{"title":"利好叠加助力5G板块加速上攻 20只概念股冲至涨停","imgList":["http://cms-bucket.ws.126.net/2020/0224/263b2d56p00q66zd4003wc000ad007ic.png"],"source":"证券日报","newsId":"F65ES2VQ002580S6","digest":null,"postTime":"2020-02-24 12:33:00","videoList":null}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 各银行行用卡用卡小技巧
         * imgList : ["http://bjnewsrec-cv.ws.126.net/little286199f2100j00q658in0023d000s600hlp.jpg"]
         * source : POS机服务台
         * newsId : F63383140539C0P8
         * digest :
         * postTime : 2020-02-23 15:23:21
         * videoList : null
         */

        private String title;
        private String source;
        private String newsId;
        private String digest;
        private String postTime;
        private Object videoList;
        private List<String> imgList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getPostTime() {
            return postTime;
        }

        public void setPostTime(String postTime) {
            this.postTime = postTime;
        }

        public Object getVideoList() {
            return videoList;
        }

        public void setVideoList(Object videoList) {
            this.videoList = videoList;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }
    }
}
