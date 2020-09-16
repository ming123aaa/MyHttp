package com.example.ohuanghttp;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ihttp.getInstance().setHttpInterface(OkHttpInterface.getInstance());
        Ihttp.getInstance().setJsonInterFace(new GsonInterface());
    }
}
