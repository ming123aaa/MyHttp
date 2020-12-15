package com.example.myhttp;


import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class GsonInterface implements JsonInterFace {
    private static GsonInterface gsonInterface = null;

    private GsonInterface() {

    }

    public static GsonInterface getInstance() {
        if (gsonInterface == null) {
            synchronized (GsonInterface.class) {
                if (gsonInterface == null) {
                    gsonInterface = new GsonInterface();
                }
            }
        }
        return gsonInterface;
    }


    @Override
    public <T> T jsonToObject(String json, Class<T> A) {
        Gson gson = new Gson();
        T t = gson.fromJson(json, A);
        return t;
    }

    @Override
    public <T> List<T> jsonToObjects(String json, Class<T> A) {
        List<T> list = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        int a = jsonArray.size();
        for (int i = 0; i < a; i++) {
            list.add(gson.fromJson(jsonArray.get(i), A));
        }
        return list;
    }


}
