# MyHttp

## Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
## Step 2. Add the dependency
	dependencies {
	        implementation 'com.github.ming123aaa:MyHttp:1.9.2'
	}
  
## Step3.add more dependency
```
dependencies {
//rxjava
    implementation 'io.reactivex.rxjava2:rxjava:2.0.6'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:4.6.0'
    //gson
    implementation 'com.google.code.gson:gson:2.8.5'
}
```

## 使用

### 初始化
```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ihttp.getInstance().setHttpInterface(OkHttpInterface.getInstance());//添加网络请求模块
        Ihttp.getInstance().setJsonInterFace(GsonInterface.getInstance());//添加json解析模块
    }
}
```
目前支持的网络模块:

OkHttpInterface.getInstance()


目前支持的解析模块:

GsonInterface.getInstance()

### 网络请求将json并解析成对象
网络返回的json为 {"id":1,"name":"tom","age":100}
```java
     HashMap<String,String> map=new HashMap<>();
        map.put("user","tom");
        map.put("password","123");
	
        //post 网络请求并解析json为对象
        Ihttp.getInstance().post(url, map, User.class, new CallBackObject<User>() {
            @Override
            void success(User ojb) {
                textView.setText(ojb.toString());
            }

            @Override
            void error(String s) {

            }
        });
```

###  网络请求将json并解析成List集合
网络返回的json为[{"age":100,"id":1,"name":"tom"},{"age":10,"id":2,"name":"cat"},{"age":23,"id":3,"name":"小鸡"}]
```java
 //网络请求json并解析为List集合
                Ihttp.getInstance().get(url,User.class, new CallBackObjects<User>() {
                    @Override
                    void success(List<User> listOjb) {
                        for (User user:listOjb){
                            Log.d(TAG, "success: "+user.toString());
                            textView.append(user.toString()+"\n");
                        }
                    }
                    @Override
                    void error(String s) {
                        Log.e(TAG, "error: "+s);
                    }
                } );
```
### 网络请求String类型字符串
```java
 //网络请求json并解析为List集合
   Ihttp.getInstance().get(url+"quick",  new CallBackString() {
                     @Override
                     public void success(String ojb) {
                         textView.setText(ojb);
                     }

                     @Override
                     public void error(String s) {

                     }
                 });
```
