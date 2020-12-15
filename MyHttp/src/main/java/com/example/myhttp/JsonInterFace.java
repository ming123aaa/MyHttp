package com.example.myhttp;

import java.util.List;

public interface JsonInterFace {
    //单个对象解析
    <T> T jsonToObject(String json, Class<T> A);

    //集合解析
    <T> List<T> jsonToObjects(String json, Class<T> A);
}
