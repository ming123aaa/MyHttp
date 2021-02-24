package com.example.myhttp;

import java.util.List;

public abstract  class CallBackObjects<T> implements CallBack<List<T>> {

    public void getString(String s) {
    }

    @Override
    public void error(String s) {

    }
}
