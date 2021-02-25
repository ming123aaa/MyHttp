package com.example.myhttp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static String mapToString(HashMap<String, String> keyMap) {
        StringBuffer stringBuffer = new StringBuffer();
        Set<Map.Entry<String, String>> set = keyMap.entrySet();
        for (Map.Entry<String, String> key : set) {
            stringBuffer.append(key.getKey());
            stringBuffer.append("=");
            stringBuffer.append(key.getValue());
            stringBuffer.append("&");
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        return stringBuffer.toString();
    }


    public void post(String Url, final HashMap<String, String> keyMap, final CallBackString callBack) {
        if (httpInterface == null) {
            callBack.error("httpInterface为空");
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
                            callBack.error("url错误");
                            return null;
                        }
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {


                        String s1 = httpInterface.post(s, keyMap, callBack);
                        return s1;

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String t) {
                        callBack.success(t);
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
    json解析只支持对象解析  不支持集合
     */
    public <T> void post(String Url, final HashMap<String, String> keyMap, final Class<T> tClass
            , final CallBackObject<T> iHttpBack) {
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


                            String s1 = httpInterface.post(s, keyMap, iHttpBack);
                            iHttpBack.getString(s1);

                            return s1;

                    }
                }).map(new Function<String, T>() {
            @Override
            public T apply(String s) {

                if (s.isEmpty()) {
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
            , final CallBackObjects<T> iHttpBack) {
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


                            String s1 = httpInterface.post(s, keyMap, iHttpBack);
                            iHttpBack.getString(s1);

                            return s1;

                    }
                }).map(new Function<String, List<T>>() {
            @Override
            public List<T> apply(String s) {

                if (s .isEmpty()) {
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
            , final CallBackObject<T> iHttpBack) {
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


                            String s1 = httpInterface.get(s, iHttpBack);
                            iHttpBack.getString(s1);

                            return s1;

                    }
                }).map(new Function<String, T>() {
            @Override
            public T apply(String s) {

                if (s .isEmpty()) {
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
            , final CallBackObjects<T> iHttpBack) {
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

                            String s1 = httpInterface.get(s, iHttpBack);
                            iHttpBack.getString(s1);

                            return s1;

                    }
                }).map(new Function<String, List<T>>() {
            @Override
            public List<T> apply(String s) {

                if (s .isEmpty()) {
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


    public void get(String Url, final CallBackString callBack) {
        if (httpInterface == null) {
            callBack.error("httpInterface为空");
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
                            callBack.error("url错误");
                            return null;
                        }
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {


                            String s1 = httpInterface.get(s, callBack);

                            return s1;

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String t) {
                        callBack.success(t);
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


}
