package com.example.myhttp;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Ihttp {
   
    private static Ihttp ihttp = null;
    private HttpInterface httpInterface;
    private JsonInterFace jsonInterFace;

    private Ihttp() {
    }

    public static Ihttp getInstance() {
        if (ihttp == null) {
            synchronized (Ihttp.class) {
                if (ihttp == null) {
                    ihttp = new Ihttp();
                }
            }
        }
        return ihttp;
    }

    /*
    post网络请求
    json解析只支持对象解析  不支持集合
     */
    public <T> void post(String Url, final HashMap<String, String> keyMap, final Class<T> tClass
            , final iHttpBack<T> iHttpBack) {
        if (jsonInterFace == null) {
            iHttpBack.error("jsonInterFace为空");
            return;
        }
        if (httpInterface == null) {
            iHttpBack.error("httpInterface为空");
            return;
        }
        Observable.just(Url)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Pattern pattern = Pattern
                                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                        if (pattern.matcher(s).matches()) {
                            return s;
                        } else {
                            iHttpBack.error("url错误");
                            return null;
                        }
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        if (s == null) {
                            iHttpBack.error("url为空");
                        } else {
                            String s1 = httpInterface.post(s, keyMap);
                            if (s1 == null) {
                                iHttpBack.error("请求数据为空");
                            }
                            return s1;
                        }

                        return null;
                    }
                }).map(new Function<String, T>() {
            @Override
            public T apply(String s) {

                if (s == null) {
                    iHttpBack.error("json数据为空无法json解析");
                } else {
                    return jsonInterFace.jsonToObject(s, tClass);
                }

                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        iHttpBack.success(t);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /*
    post网络请求
    json解析只支持集合解析 不支持单个对象
     */
    public <T> void post(String Url, final HashMap<String, String> keyMap, final Class<T> tClass
            , final iHttpBackList<T> iHttpBack) {
        if (jsonInterFace == null) {
            iHttpBack.error("jsonInterFace为空");
            return;
        }
        if (httpInterface == null) {
            iHttpBack.error("httpInterface为空");
            return;
        }
        Observable.just(Url)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Pattern pattern = Pattern
                                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                        if (pattern.matcher(s).matches()) {
                            return s;
                        } else {
                            iHttpBack.error("url错误");
                            return null;
                        }
                    }
                }).
                map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        if (s == null) {
                            iHttpBack.error("url为空");
                        } else {
                            String s1 = httpInterface.post(s, keyMap);
                            if (s1 == null) {
                                iHttpBack.error("请求数据为空");
                            }
                            return s1;
                        }

                        return null;
                    }
                }).map(new Function<String, List<T>>() {
            @Override
            public List<T> apply(String s) {

                if (s == null) {
                    iHttpBack.error("json数据为空无法json解析");
                } else {
                    return jsonInterFace.jsonToObjects(s, tClass);
                }

                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<T> ts) {
                        iHttpBack.success(ts);
                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /*
    get网络请求
    json解析只支持对象解析  不支持集合
     */
    public <T> void get(String Url, final Class<T> tClass
            , final iHttpBack<T> iHttpBack) {
        if (jsonInterFace == null) {
            iHttpBack.error("jsonInterFace为空");
            return;
        }
        if (httpInterface == null) {
            iHttpBack.error("httpInterface为空");
            return;
        }
        Observable.just(Url)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Pattern pattern = Pattern
                                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                        if (pattern.matcher(s).matches()) {
                            return s;
                        } else {
                            iHttpBack.error("url错误");
                            return null;
                        }
                    }
                }).
                map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        if (s == null) {
                            iHttpBack.error("url为空");
                        } else {
                            String s1 = httpInterface.get(s);
                            if (s1 == null) {
                                iHttpBack.error("请求数据为空");
                            }
                            return s1;
                        }

                        return null;
                    }
                }).map(new Function<String, T>() {
            @Override
            public T apply(String s) {

                if (s == null) {
                    iHttpBack.error("json数据为空无法json解析");
                } else {
                    return jsonInterFace.jsonToObject(s, tClass);
                }

                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        iHttpBack.success(t);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /*
    get网络请求
    json解析只支持集合解析 不支持单个对象
     */
    public <T> void get(String Url, final Class<T> tClass
            , final iHttpBackList<T> iHttpBack) {
        if (jsonInterFace == null) {
            iHttpBack.error("jsonInterFace为空");
            return;
        }
        if (httpInterface == null) {
            iHttpBack.error("httpInterface为空");
            return;
        }
        Observable.just(Url)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Pattern pattern = Pattern
                                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                        if (pattern.matcher(s).matches()) {
                            return s;
                        } else {
                            iHttpBack.error("url错误");
                            return null;
                        }
                    }
                }).
                map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        if (s == null) {
                            iHttpBack.error("url为空");
                        } else {
                            String s1 = httpInterface.get(s);
                            if (s1 == null) {
                                iHttpBack.error("请求数据为空");
                            }
                            return s1;
                        }
                        return null;
                    }
                }).map(new Function<String, List<T>>() {
            @Override
            public List<T> apply(String s) {

                if (s == null) {
                    iHttpBack.error("json数据为空无法json解析");
                } else {
                    return jsonInterFace.jsonToObjects(s, tClass);
                }

                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<T> ts) {
                        iHttpBack.success(ts);
                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void setHttpInterface(HttpInterface httpInterface) {
        this.httpInterface = httpInterface;
    }

    public void setJsonInterFace(JsonInterFace jsonInterFace) {
        this.jsonInterFace = jsonInterFace;

    }


    public abstract static class iHttpBack<T> {
        abstract void success(T ojb);

        abstract void error(String s);
    }

    public abstract static class iHttpBackList<T> {
        abstract void success(List<T> listOjb);

        abstract void error(String s);
    }

    public interface HttpInterface {
        String post(String Url, HashMap<String, String> keyMap);

        String get(String Url);
    }

    //json解析
    public interface JsonInterFace {
        //单个对象解析
        <T> T jsonToObject(String json, Class<T> A);

        //集合解析
        <T> List<T> jsonToObjects(String json, Class<T> A);
    }

}
