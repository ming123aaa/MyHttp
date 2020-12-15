package com.example.myhttp;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpURLConnectionInterface implements HttpInterface {
    private static HttpURLConnectionInterface httpURLConnectionInterface=null;
    private HttpURLConnectionInterface(){}

    public static HttpURLConnectionInterface getInstance(){
        if(httpURLConnectionInterface==null){
            synchronized (HttpURLConnectionInterface.class){
                if (httpURLConnectionInterface==null){
                    httpURLConnectionInterface=new HttpURLConnectionInterface();
                }
            }
        }
        return httpURLConnectionInterface;
    }

    public  String mapToString(HashMap<String,String> keyMap){
        StringBuffer stringBuffer=new StringBuffer();
        Set<Map.Entry<String,String>> set = keyMap.entrySet();
        for (Map.Entry<String,String> key : set) {
            stringBuffer.append(key.getKey());
            stringBuffer.append("=");
            stringBuffer.append(key.getValue());
            stringBuffer.append("&");
        }
        stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());
        return stringBuffer.toString();
    }


    private String postToString(String Url, HashMap<String, String> keyMap){
        String s = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(mapToString(keyMap));
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            // 下面对获取到的输入流进行读取
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            s = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return s;
    }


    private String getToString(String Url) {
        String s = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            // 下面对获取到的输入流进行读取
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            s = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return s;
    }

    @Override
    public String post(String Url, HashMap<String, String> keyMap) {
        return postToString(Url,keyMap);
    }

    @Override
    public String get(String Url) {
        return getToString(Url);
    }
}
