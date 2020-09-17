package com.example.ohuanghttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private String url = "http://192.168.1.101:8080/anjotest/Myservlet";
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        HashMap<String, String> map = new HashMap<>();
        map.put("user", "tom");
        map.put("password", "123");
        //post 网络请求并解析json为对象
        Ihttp.getInstance().post(url, map, User.class, new Ihttp.iHttpBack<User>() {

            @Override
            public void success(User ojb) {
                textView.setText(ojb.toString());
            }

            @Override
            public void getString(String s) {
                Log.d(TAG, "getString: " + s);
            }

            @Override
            public void error(String s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //网络请求json并解析为List集合
                Ihttp.getInstance().get(url, User.class, new Ihttp.iHttpBackList<User>() {
                    @Override
                    public void success(List<User> listOjb) {
                        for (User user : listOjb) {
                            Log.d(TAG, "success: " + user.toString());
                            textView.append(user.toString() + "\n");
                        }
                    }

                    @Override
                    public void getString(String s) {
                        Log.d(TAG, "getString: " + s);
                    }

                    @Override
                    public void error(String s) {
                        Log.e(TAG, "error: " + s);
                    }
                });
            }
        });
    }
}