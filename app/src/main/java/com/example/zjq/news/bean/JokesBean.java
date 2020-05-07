package com.example.zjq.news.bean;

import java.util.List;

public class JokesBean {

    /**
     * code : 1
     * msg : 数据返回成功！
     * data : {"page":1,"totalCount":79003,"totalPage":7901,"limit":10,"list":[{"content":"记得有一次跟朋友出去玩没钱了，回去跟妹妹说（妹妹当时是学生）她毫不犹豫把她的零花钱全部给我了，还说等她挣钱的时候要给我买好看的衣服。 现在想想依然感动的稀里哗啦。。","updateTime":"2020-05-06 23:59:24"},{"content":"发现一头野生的奇行种:学校门口的面包店贴着：\u201c免费加热面包\u201d。小明看到后，对店员说：\u201c给我一个加热面包。小刚，你要不要？免费的不要钱\u201d店员当场就凌乱了。","updateTime":"2020-05-06 23:59:24"},{"content":"白打这么多字了:今天在马路上有人找我捐款，我告诉他我没有现金。他说刷卡、支票、贵重物品都行的。那好吧，我给你写张支票。他说现在你还没签名呢！我做好事一般不留名\u2026\u2026","updateTime":"2020-05-06 23:59:23"},{"content":"又是一个满嘴跑航天飞机的货:同事家一五岁小正太，特逗！前天犯了个小错误，他妈要打他。他很镇定地说道：打吧，我可不怕你，不过你别怪我没提醒你啊，我舅舅是公安局长，你打完我，要吃不了兜着走滴！他妈当时就喷了，小子，威胁你老妈是吧，我打小就独生子女到现在三十年了，你哪来的公安局长舅舅？","updateTime":"2020-05-06 23:59:22"},{"content":"连新娘都无法直视这么二的伴郎团了:听一女同事给刚来的小师妹介绍对象，小师妹问：\u201c那个男的为人怎么样？\u201d女同事郑重其事说：\u201c绝对不出轨的好男人，除非他想死。\u201d小师妹不明白了，女同事笑着说：\u201c他是火车司机。\u201d","updateTime":"2020-05-06 23:59:21"},{"content":"一早见到二逼室友在洗裤头，嘴里还念叨着:清晨起床洗裤头，无数孩子跟水流，不是爹爹不要你，而是你娘不收留。 这一刻，我想我已经被他的才华震惊了！ 居然不过\u2026\u2026","updateTime":"2020-05-06 23:59:19"},{"content":"都是电视犯的错！:幼儿园组织小活动，课习间老师带小盆友们玩过家家。此时老师听到一男两女三个孩子在对话，男孩：\u201c我要当爸爸。\u201d女孩说：\u201c我要当妈妈。\u201d随后另一个女孩说：\u201c那我只有做小三了...\u201d尼玛幼儿园的孩子啊......","updateTime":"2020-05-06 23:59:18"},{"content":"有一种淡淡的忧伤！:lz妹子，刚刚倒追男神成功前两天生病在家，他过来照顾我替我找药的时候居然在储物盒里翻出一支震动棒当时想死的心都有了\u2026两天没联络了，不会吹了吧？","updateTime":"2020-05-06 23:59:18"},{"content":"谁去帮我把灯关掉，好困...:刚刚晚自习，同桌想放屁。我对他说，你先忍着。我数1、2、3然后我拍桌子你放屁。这样就没人知道你放屁了。我同桌说好。结果呢，我数到二就拍桌子了。所有同学都把脸转过来看我们。同桌憋的脸通红，没憋住，然后一个声音洪亮且悠长的屁就这样现场直播了。","updateTime":"2020-05-06 23:59:17"},{"content":"秘书的作用就是：有事秘书干，没事干秘书~~","updateTime":"2020-05-06 23:59:16"}]}
     */

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
        /**
         * page : 1
         * totalCount : 79003
         * totalPage : 7901
         * limit : 10
         * list : [{"content":"记得有一次跟朋友出去玩没钱了，回去跟妹妹说（妹妹当时是学生）她毫不犹豫把她的零花钱全部给我了，还说等她挣钱的时候要给我买好看的衣服。 现在想想依然感动的稀里哗啦。。","updateTime":"2020-05-06 23:59:24"},{"content":"发现一头野生的奇行种:学校门口的面包店贴着：\u201c免费加热面包\u201d。小明看到后，对店员说：\u201c给我一个加热面包。小刚，你要不要？免费的不要钱\u201d店员当场就凌乱了。","updateTime":"2020-05-06 23:59:24"},{"content":"白打这么多字了:今天在马路上有人找我捐款，我告诉他我没有现金。他说刷卡、支票、贵重物品都行的。那好吧，我给你写张支票。他说现在你还没签名呢！我做好事一般不留名\u2026\u2026","updateTime":"2020-05-06 23:59:23"},{"content":"又是一个满嘴跑航天飞机的货:同事家一五岁小正太，特逗！前天犯了个小错误，他妈要打他。他很镇定地说道：打吧，我可不怕你，不过你别怪我没提醒你啊，我舅舅是公安局长，你打完我，要吃不了兜着走滴！他妈当时就喷了，小子，威胁你老妈是吧，我打小就独生子女到现在三十年了，你哪来的公安局长舅舅？","updateTime":"2020-05-06 23:59:22"},{"content":"连新娘都无法直视这么二的伴郎团了:听一女同事给刚来的小师妹介绍对象，小师妹问：\u201c那个男的为人怎么样？\u201d女同事郑重其事说：\u201c绝对不出轨的好男人，除非他想死。\u201d小师妹不明白了，女同事笑着说：\u201c他是火车司机。\u201d","updateTime":"2020-05-06 23:59:21"},{"content":"一早见到二逼室友在洗裤头，嘴里还念叨着:清晨起床洗裤头，无数孩子跟水流，不是爹爹不要你，而是你娘不收留。 这一刻，我想我已经被他的才华震惊了！ 居然不过\u2026\u2026","updateTime":"2020-05-06 23:59:19"},{"content":"都是电视犯的错！:幼儿园组织小活动，课习间老师带小盆友们玩过家家。此时老师听到一男两女三个孩子在对话，男孩：\u201c我要当爸爸。\u201d女孩说：\u201c我要当妈妈。\u201d随后另一个女孩说：\u201c那我只有做小三了...\u201d尼玛幼儿园的孩子啊......","updateTime":"2020-05-06 23:59:18"},{"content":"有一种淡淡的忧伤！:lz妹子，刚刚倒追男神成功前两天生病在家，他过来照顾我替我找药的时候居然在储物盒里翻出一支震动棒当时想死的心都有了\u2026两天没联络了，不会吹了吧？","updateTime":"2020-05-06 23:59:18"},{"content":"谁去帮我把灯关掉，好困...:刚刚晚自习，同桌想放屁。我对他说，你先忍着。我数1、2、3然后我拍桌子你放屁。这样就没人知道你放屁了。我同桌说好。结果呢，我数到二就拍桌子了。所有同学都把脸转过来看我们。同桌憋的脸通红，没憋住，然后一个声音洪亮且悠长的屁就这样现场直播了。","updateTime":"2020-05-06 23:59:17"},{"content":"秘书的作用就是：有事秘书干，没事干秘书~~","updateTime":"2020-05-06 23:59:16"}]
         */

        private int page;
        private int totalCount;
        private int totalPage;
        private int limit;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : 记得有一次跟朋友出去玩没钱了，回去跟妹妹说（妹妹当时是学生）她毫不犹豫把她的零花钱全部给我了，还说等她挣钱的时候要给我买好看的衣服。 现在想想依然感动的稀里哗啦。。
             * updateTime : 2020-05-06 23:59:24
             */

            private String content;
            private String updateTime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
