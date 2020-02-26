package com.example.zjq.news.menudetailpager.bean;


import java.util.List;

/**
 * 作者：张建青
 * 时间： 2020/2/26 11:48
 * 邮箱：729395495@qq.com
 */
public class TabDetailViewPagerBean {

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<ImgDataBean> images;
        private String title;
        private String content;
        private String source;
        private String ptime;
        private String docid;
        private String cover;

        public List<ImgDataBean> getImages() {
            return images;
        }

        public void setImages(List<ImgDataBean> images) {
            this.images = images;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

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


        public static class ImgDataBean {

            private String position;
            private String imgSrc;
            private Object size;

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getImgSrc() {
                return imgSrc;
            }

            public void setImgSrc(String imgSrc) {
                this.imgSrc = imgSrc;
            }

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }
        }

    }

}
