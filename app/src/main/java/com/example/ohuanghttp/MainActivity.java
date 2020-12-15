package com.example.ohuanghttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myhttp.CallBackObject;
import com.example.myhttp.CallBackObjects;
import com.example.myhttp.CallBackString;
import com.example.myhttp.Ihttp;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private String url = "http://192.168.1.111:8080/user/";
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //网络请求json并解析为List集合
//                Ihttp.getInstance().get(url+"reg", User.class, new CallBackObjects<User>() {
//                    @Override
//                    public void success(List<User> ojb) {
//                            textView.setText(ojb.toString());
//                    }
//
//
//                    @Override
//                    public void error(String s) {
//
//                    }
//                });
                HashMap<String, String> map = new HashMap<>();
                map.put("data", "tom");
                //post 网络请求并解析json为对象
//                Ihttp.getInstance().post(url+"quick", map, User.class, new CallBackObject<User>() {
//                    @Override
//                    public void success(User ojb) {
//                        textView.setText(ojb.toString());
//                    }
//
//                    @Override
//                    public void getString(String s) {
//                        super.getString(s);
//                        Log.e(TAG, "getString: "+s );
//                    }
//
//                    @Override
//                    public void error(String s) {
//                        Log.e(TAG, "error: "+s );
//                    }
//                });
                Ihttp.getInstance().post(url+"quick", map, new CallBackString() {
                    @Override
                    public void success(String ojb) {
                        textView.setText(ojb);
                    }

                    @Override
                    public void error(String s) {

                    }
                });
            }
        });
    }
}