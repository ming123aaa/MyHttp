package com.example.ohuanghttp;

import android.app.Application;

import com.example.myhttp.GsonInterface;
import com.example.myhttp.Ihttp;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Ihttp.getInstance().setHttpInterface(HttpURLConnectionInterface.getInstance());//添加网络请求模块
        Ihttp.getInstance().setJsonInterFace(GsonInterface.getInstance());//添加json解析模块
    }
}
