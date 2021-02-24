package com.example.myhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;


public abstract class CallBackString implements CallBack<String> {
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
