package com.example.myhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.List;

public abstract  class CallBackObjects<T> implements CallBack<List<T>> {

    public void getString(String s) {
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            fail(s);
        }
    };

    @Override
    public void error(String s) {
        Message message = new Message();
        message.obj = s;
        handler.sendMessage(message);
    }

    public abstract void fail(String s);
}
