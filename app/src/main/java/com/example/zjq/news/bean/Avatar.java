package com.example.zjq.news.bean;


import com.example.zjq.news.utils.GsonResultok;

public class Avatar extends GsonResultok {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "avatar='" + avatar + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "data=" + data +
                '}';
    }
}
