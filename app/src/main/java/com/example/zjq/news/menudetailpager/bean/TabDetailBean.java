package com.example.zjq.news.menudetailpager.bean;

import java.util.List;

public class TabDetailBean {


    private String mtime;
    private String digest;
    private String imgsrc;
    private String url;

    public TabDetailBean(String mtime, String digest, String imgsrc, String url) {
        this.mtime = mtime;
        this.digest = digest;
        this.imgsrc = imgsrc;
        this.url = url;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }



    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }


    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
