package com.example.myhttp;

import java.util.HashMap;

public interface HttpInterface {
        String post(String Url, HashMap<String, String> keyMap,CallBack callBack);

        String get(String Url,CallBack callBack);

}
