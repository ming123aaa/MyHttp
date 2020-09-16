package com.example.ohuanghttp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonInterface implements Ihttp.JsonInterFace {
   private Type type=new TypeToken<User>(){

   }.getType();


    public GsonInterface() {

    }



    @Override
    public <T> T jsonToObject(String json,Class<T> A) {
        Gson gson = new Gson();
        T t= gson.fromJson(json, A);
        return t;
    }

    @Override
    public <T> List<T> jsonToObjects(String json, Class<T> A) {
        List<T> list=new ArrayList<>();
        Gson gson = new Gson();
        JsonArray jsonArray=gson.fromJson(json,JsonArray.class);
        int a=jsonArray.size();
        for (int i=0;i<a;i++){
            list.add(gson.fromJson(jsonArray.get(i),A));
        }
        return list;
    }


}
