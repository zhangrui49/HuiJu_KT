package com.zhangrui.huijukt.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrui on 2017/7/24.
 */

public class GankDay {


    /**
     * category : ["Android","前端","休息视频","iOS","福利"]
     * error : false
     * results : {"Android":[{"_id":"590859ce421aa90c83a513b5","createdAt":"2017-05-02T18:05:02.17Z","desc":"Android 多状态加载布局的开发 Tips","images":["http://img.gank.io/c1239688-bf22-46a7-ab46-b23bfb8d32da"],"publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"Android","url":"http://gudong.name/2017/04/26/loading_layout_practice.html","used":true,"who":"咕咚"},{"_id":"59087a42421aa90c7fefdd1f","createdAt":"2017-05-02T20:23:30.786Z","desc":"Use StrictMode To Find Things You Did By Accident In Android Development","publishedAt":"2017-05-26T13:43:32.128Z","source":"web","type":"Android","url":"https://blog.mindorks.com/use-strictmode-to-find-things-you-did-by-accident-in-android-development-4cf0e7c8d997","used":true,"who":"AMIT SHEKHAR"},{"_id":"5911343b421aa90c7fefdd6c","createdAt":"2017-05-09T11:15:07.397Z","desc":"横向滚动的、可以支持大量文本选择的自定义View ，简单易用，绝无OOM情况。","images":["http://img.gank.io/1ee1100a-954a-47be-933d-e6fcbbad3d48"],"publishedAt":"2017-05-26T13:43:32.128Z","source":"web","type":"Android","url":"https://github.com/385841539/HorizontalScrollSelectedView","used":true,"who":null},{"_id":"591be651421aa92c794632d9","createdAt":"2017-05-17T13:57:37.6Z","desc":"本文的目的是试图通过分析 LeakCanary 源码来探讨它的 Activity 泄漏检测机制。","images":["http://img.gank.io/b25e261e-01c7-40e5-8853-e4b15c738bae"],"publishedAt":"2017-05-26T13:43:32.128Z","source":"web","type":"Android","url":"http://wingjay.com/2017/05/14/dig_into_leakcanary/","used":true,"who":"wingjay"},{"_id":"5927bb2f421aa92c769a8bc1","createdAt":"2017-05-26T13:20:47.163Z","desc":"用MediaPlayer+TextureView封装一个完美实现全屏、小窗口的视频播放器","images":["http://img.gank.io/3ad94fd6-bfbd-47f2-96f6-4eafe819c6fb"],"publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/420f7b14d6f6","used":true,"who":"daimajia"}],"iOS":[{"_id":"5927bb68421aa92c7be61b5b","createdAt":"2017-05-26T13:21:44.124Z","desc":"iOS 隐私界面保护方法","publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"iOS","url":"https://github.com/alexruperez/LaunchScreenSnapshot","used":true,"who":"S"}],"休息视频":[{"_id":"592785bd421aa92c73b64769","createdAt":"2017-05-26T09:32:45.586Z","desc":"现实的你 VS 网络上的你","publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/F4Lmc7Hlb?fid=1034:f9f6261f53a8a148e9f8430e9c182516","used":true,"who":"lxxself"}],"前端":[{"_id":"59102339421aa90c83a513f5","createdAt":"2017-05-08T15:50:17.20Z","desc":"前端每周清单第 12 期：支付宝前端构建工具发展、LinkedIn用Brotli加快网页响应速度、饿了么PWA 升级实践","publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/26780461","used":true,"who":"王下邀月熊"}],"福利":[{"_id":"5927bc01421aa92c79463324","createdAt":"2017-05-26T13:24:17.785Z","desc":"5-26","publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffyp4g2vwxj20u00tu77b.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private Results results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public ArrayList<Results.Return> generateResults() {
        ArrayList<Results.Return> mResult = new ArrayList<>();
        if (isNotEmpty(results.get福利())) {
            mResult.addAll(results.get福利());
        }
        if (isNotEmpty(results.getAndroid())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("Android");
            mResult.add(mReturn);
            mResult.addAll(results.getAndroid());
        }
        if (isNotEmpty(results.getiOS())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("IOS");
            mResult.add(mReturn);
            mResult.addAll(results.getiOS());
        }
        if (isNotEmpty(results.get前端())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("前端");
            mResult.add(mReturn);
            mResult.addAll(results.get前端());
        }
        if (isNotEmpty(results.get休息视频())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("休息视频");
            mResult.add(mReturn);
            mResult.addAll(results.get休息视频());
        }
        if (isNotEmpty(results.get拓展资源())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("拓展资源");
            mResult.add(mReturn);
            mResult.addAll(results.get拓展资源());
        }
        if (isNotEmpty(results.get瞎推荐())) {
            Results.Return mReturn = new Results.Return();
            mReturn.setType("title");
            mReturn.setDesc("瞎推荐");
            mResult.add(mReturn);
            mResult.addAll(results.get瞎推荐());
        }
        return mResult;
    }

    public static class Results {
        private List<Return> Android;
        private List<Return> iOS;
        private List<Return> 休息视频;
        private List<Return> 前端;
        private List<Return> 福利;
        private List<Return> 拓展资源;
        private List<Return> 瞎推荐;

        public List<Return> get拓展资源() {
            return 拓展资源;
        }

        public void set拓展资源(List<Return> 拓展资源) {
            this.拓展资源 = 拓展资源;
        }

        public List<Return> get瞎推荐() {
            return 瞎推荐;
        }

        public void set瞎推荐(List<Return> 瞎推荐) {
            this.瞎推荐 = 瞎推荐;
        }

        public List<Return> getAndroid() {
            return Android;
        }

        public void setAndroid(List<Return> android) {
            Android = android;
        }

        public List<Return> getiOS() {
            return iOS;
        }

        public void setiOS(List<Return> iOS) {
            this.iOS = iOS;
        }

        public List<Return> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<Return> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<Return> get前端() {
            return 前端;
        }

        public void set前端(List<Return> 前端) {
            this.前端 = 前端;
        }

        public List<Return> get福利() {
            return 福利;
        }

        public void set福利(List<Return> 福利) {
            this.福利 = 福利;
        }

        public static class Return {
            /**
             * _id : 590859ce421aa90c83a513b5
             * createdAt : 2017-05-02T18:05:02.17Z
             * desc : Android 多状态加载布局的开发 Tips
             * images : ["http://img.gank.io/c1239688-bf22-46a7-ab46-b23bfb8d32da"]
             * publishedAt : 2017-05-26T13:43:32.128Z
             * source : chrome
             * type : Android
             * url : http://gudong.name/2017/04/26/loading_layout_practice.html
             * used : true
             * who : 咕咚
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }


    }

    public boolean isNotEmpty(List list) {
        return list != null && list.size() > 0;
    }
}
