package com.example.zjq.news.bean;

import java.util.List;

public class NewsCenterPagerBean {


    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{"uniquekey":"9272e3a075d4dfc2cc9c39bdcae49e26","title":"朱丹再曝家事，\u201c卑微丹\u201d再上线，您能不能换个人设？","date":"2020-02-15 07:45","category":"头条","author_name":"八抓娱","url":"http://mini.eastday.com/mobile/200215074550196.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_10_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_7_mwpm_03200403.jpg"},{"uniquekey":"596a550894bbe3c3ef112b86b86c9110","title":"煮虾时，直接下锅就\u201c废\u201d了，这样做，鲜嫩又弹牙！","date":"2020-02-15 07:45","category":"头条","author_name":"这样做饭更好吃","url":"http://mini.eastday.com/mobile/200215074546850.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_6_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{"uniquekey":"9272e3a075d4dfc2cc9c39bdcae49e26","title":"朱丹再曝家事，\u201c卑微丹\u201d再上线，您能不能换个人设？","date":"2020-02-15 07:45","category":"头条","author_name":"八抓娱","url":"http://mini.eastday.com/mobile/200215074550196.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_10_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_7_mwpm_03200403.jpg"},{"uniquekey":"596a550894bbe3c3ef112b86b86c9110","title":"煮虾时，直接下锅就\u201c废\u201d了，这样做，鲜嫩又弹牙！","date":"2020-02-15 07:45","category":"头条","author_name":"这样做饭更好吃","url":"http://mini.eastday.com/mobile/200215074546850.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20200215/20200215074546_9a29f1dc70fda3905a48589fb1c6718a_6_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 9272e3a075d4dfc2cc9c39bdcae49e26
             * title : 朱丹再曝家事，“卑微丹”再上线，您能不能换个人设？
             * date : 2020-02-15 07:45
             * category : 头条
             * author_name : 八抓娱
             * url : http://mini.eastday.com/mobile/200215074550196.html
             * thumbnail_pic_s : http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_8_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_10_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://07imgmini.eastday.com/mobile/20200215/20200215074550_ce28ac34d0607b28e2be5347fbb22b9f_7_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
