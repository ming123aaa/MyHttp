# MyHttp

## Step 1. Add the JitPack repository to your build file

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


## Step 2. Add the dependency
	dependencies {
	        implementation 'com.github.ming123aaa:MyHttp:Tag'
	}
  
## Step3.add more dependency
dependencies {
//rxjava
    implementation 'io.reactivex.rxjava2:rxjava:2.0.6'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:4.6.0'
    //gson
    implementation 'com.google.code.gson:gson:2.8.5'
}
