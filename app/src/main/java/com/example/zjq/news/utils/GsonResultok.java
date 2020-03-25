package com.example.zjq.news.utils;

public class GsonResultok {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    @Override
    public String toString() {
        return "GsonResult2{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
