package com.example.myhttp;

public interface CallBack<T> {
      void success(T ojb);
      void error(String s);
}
