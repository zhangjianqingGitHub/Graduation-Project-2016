package com.example.zjq.news.bean;

import java.util.List;

public class EveryDayWordBean {


    /**
     * code : 1
     * msg : 数据返回成功！通知：2020年4月1日将拦截所有非app_id请求，对您造成不便，敬请谅解，详情请访问：https://github.com/MZCretin/RollToolsApi#%E8%A7%A3%E9%94%81%E6%96%B0%E6%96%B9%E5%BC%8F
     * data : [{"content":"很多时候你要区分，到底是别人理解不了你的话，还是你的表述传达不了你的思想。","author":"朵朵"},{"content":"世上最奢侈的人，是肯花时间陪你的人。谁的时间都有价值，把时间分给了你，就等于把自己的世界分给了你。希望拥有的人去好好珍惜。","author":"漫柳如烟"},{"content":"人生最幸福的事，就是在我每天一醒来，总是能看到妳幸福地在我怀里。","author":""},{"content":"水凉了还可以喝，心凉了连说快乐都显得落寞。","author":"矢车菊T"},{"content":"Don\u2019t hurry say have no choice， perhaps， next intersection will meet hope． 不要急着说别无选择，也许下个路口就会遇见希望。","author":"Rain"},{"content":"世上最没用的东西就是工资条，看了生气，擦屁股太细。","author":""},{"content":"社会真的现实的，无论自己受了多大委屈多大痛苦，在别人看来都不紧要的，人家不会看到你努力，只看到结果。","author":"冰"},{"content":"也许你很有实力，但我未必瞧得起你。","author":""},{"content":"你不懂的怜香惜玉就TMD滚蛋！不管我是好是坏，别人学不来我的一切！世上再无第二个我，所以请你表用你的SB行为伤害到姑奶奶我！","author":""},{"content":"总有些事，管你愿不愿意，它都要发生，你只能接受；总有些东西，管你躲不躲避，它都要来临，你只能面对。","author":"漫柳如烟"},{"content":"你在的时候 你是一切 你不在的时候 一切是你","author":""},{"content":"人都有两条路要走，一条是必须走的，一条是想走的，你必须把必须走的路走漂亮，才可以走想走的路。所以要坚持走下去。","author":""},{"content":"行同如陌路,是谁与谁的露水姻缘！彼岸花开，终归与回忆缠绵...","author":""},{"content":"原来喜欢一个人的时候,过程是很微妙的.","author":""},{"content":"今夜、比夜更深的眼睫深处,会不会有一朵白莲开放~","author":""},{"content":"Those who bring sunshine to the lives of others,cannot keep it from them selves. 给别人的生命带来阳光的人，自己也会享有阳光。","author":""},{"content":"对于自己爱过的人说后悔，你是在强调自己有多失败嘛...","author":""},{"content":"传说，两个人相处久了，会达到一种莫名的默契，比如：你不理我，我也就不理你。","author":""},{"content":"我这人从来不记仇，一般有仇当场就报了。","author":""},{"content":"天将降大任于斯人也，必先苦其心志劳其筋骨，后来天改主意了。","author":""}]
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
         * content : 很多时候你要区分，到底是别人理解不了你的话，还是你的表述传达不了你的思想。
         * author : 朵朵
         */

        private String content;
        private String author;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
