package com.example.ohuanghttp;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ihttp.getInstance().setHttpInterface(OkHttpInterface.getInstance());//添加网络请求模块
        Ihttp.getInstance().setJsonInterFace(new GsonInterface());//添加json解析模块
    }
}
