package com.example.myhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterface implements Ihttp.HttpInterface {
    private static OkHttpInterface okHttpInterface = null;
    private OkHttpClient okHttpClient = null;

    private OkHttpInterface() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpInterface getInstance() {
        if (okHttpInterface == null) {
            synchronized (OkHttpInterface.class) {
                if (okHttpInterface == null) {
                    okHttpInterface = new OkHttpInterface();
                }
            }
        }
        return okHttpInterface;
    }


    private FormBody.Builder getFormBodyBuilder(HashMap<String, String> keyMap) {
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String,String>> set = keyMap.entrySet();
        for (Map.Entry<String,String> entry: set) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder;
    }


    private String getToString(String Url) {
        String bodyString = null;
        Request request = new Request.Builder()
                .url(Url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            if (response.isSuccessful()) {
                try {
                    bodyString = response.body().string();//获取数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bodyString;
    }


    private String postToString(String Url, HashMap<String, String> keyMap) {
        String bodyString = null;
        FormBody formBody = null;
        if (keyMap == null) {
            formBody = new FormBody.Builder().build();
        } else {
            formBody = getFormBodyBuilder(keyMap).build();
        }
        Request request = new Request.Builder()
                .url(Url)
                .post(formBody)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();//请求
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            if (response.isSuccessful()) {
                try {
                    bodyString = response.body().string();//获取数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bodyString;
    }

    @Override
    public String post(String Url, HashMap<String, String> keyMap) {
        String json = null;
        if (keyMap == null) {
            json = postToString(Url, null);
        } else {
            json = postToString(Url, keyMap);
        }
        return json;
    }

    @Override
    public String get(String Url) {
        return getToString(Url);
    }
}
