# Android MVVM

<img width="400px" height="700px" src="https://user-images.githubusercontent.com/53843178/63658483-debedf80-c7e5-11e9-9557-10bed7df87af.gif" />
<br/>
<br/>



## MVVM Concept

<img width="750" alt="MVVM" src="https://user-images.githubusercontent.com/52689243/63658924-5e9a7900-c7e9-11e9-9ba6-08deaed6a36f.png">

1. View 에 입력이 들어오면 Command 패턴으로 ViewModel 에 명령을 한다.
2. ViewModel 은 필요한 데이터를 Model 에 요청한다.
3. Model 은 ViewModel 에게 요청받은 데이터를 응답한다.
4. ViewModel 은 응답받은 데이터를 가공해서 저장한다.
5. View 는 ViewModel 과의 Data Binding 으로 인해 자동으로 갱신된다.
<br/>


#### View

- View 는 화면에 보여지는 UI 이다.
- 기본적으로 비즈니스 로직을 배제하지만 UI 와 관련된 로직을 수행할 수 있다.
<br/>


#### ViewModel

- View 를 표현하기 위해 만들어진 View 를 위한 모델이라고 보면 된다.
- ViewModel 은 View 에 연결할 데이터와 명령으로 구성되어있으며 변경 알림을 통해서 View 에게 상태 변화를 전달한다.
- 전달받은 상태 변화를 화면에 반영할지는 View 가 선택하도록 한다.
- 명령은 UI 를 통해서 동작하도록 한다.
<br/>


#### Model

- Model 은 UI 에 표시될 데이터와 비즈니스 로직을 담당한다.
<br/>


#### View, ViewModel, Model 의 관계

- ViewModel 은 Model 을 알지만 View 를 알지 못한다.
- View 는 Model 을 알지 못하나 ViewModel 을 알 수 있다.
- View 는 ViewModel 을 옵저빙하고 있다가 상태 변화가 전달되면 화면을 갱신해야 한다.
<br/>
<br/>



## Data Binding

<img width="649" alt="DataBinding" src="https://user-images.githubusercontent.com/52689243/63493597-edac4600-c4f6-11e9-85ec-b39c36d93ecb.png">

- MVVM 에서 가장 중요한 핵심으로, View 가 오로지 수동적인 포지션을 취할 수 있고 ViewModel 이 View 의 존재를 알지 못하게 하여 의존성에서 벗어날 수 있게 해준다.
- Data Binding 은 View 와 ViewModel 간의 데이터와 명령을 연결해주는 매개체가 되어 서로의 존재를 명확히 알지 않더라도 다양한 인터랙션을 할 수 있도록 도와준다.
- Model 에서 데이터가 변경되면 ViewModel 을 거쳐서 View 로 전달되도록 하는데, Android 에서는 LiveData 나 RxJava 등을 통해 구현할 수 있다.
<br/>
<br/>



## Project Setting

#### BaseApplication.kt

```kotlin
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(0)
            .methodOffset(7)
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}
```
<br/>


#### build.gradle(Module: app)

```gradle
apply plugin: 'kotlin-kapt'

..

android {
    ..

    kotlinOptions {
            jvmTarget = '1.8'
    }
    dataBinding {
            enabled = true
    }
}

..

dependencies {
    // CardView
    implementation "androidx.cardview:cardview:$cardview_version"

    // RecyclerView
    implementation "androidx.recyclerview:recyclerview:$recyclerview_version"

    // Koin
    implementation "org.koin:koin-android:$koin_version"

    // Koin
    // Koin AndroidX Scope features
    implementation "org.koin:koin-androidx-scope:$koin_version"

    // Koin
    // Koin AndroidX ViewModel features
    implementation "org.koin:koin-androidx-viewmodel:$koin_version"

    // Retrofit2
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofit2_version"

    // Okhttp3
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp3_version"

    // Gson
    implementation "com.google.code.gson:gson:$gson_version"

    // RxAndroid
    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"

    // RxJava
    implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"

    // AAC
    kapt "androidx.lifecycle:lifecycle-compiler:$aac_version"
    implementation "androidx.lifecycle:lifecycle-runtime:$aac_version"

    // AAC
    // ViewModel and LiveData
    implementation "androidx.lifecycle:lifecycle-extensions:$aac_version"

    // Logger
    // AndroidManifest.xml 에서 name 추가 필요
    implementation "com.orhanobut:logger:$logger_version"

    // Glide
    implementation "com.github.bumptech.glide:glide:$glide_version"
    annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"

    // Room
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    // Room
    // Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$room_version"

    // Room
    // RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // Paging
    implementation "androidx.paging:paging-runtime-ktx:$paging_version"
    testImplementation "androidx.paging:paging-common-ktx:$paging_version"

    // Paging
    implementation "androidx.paging:paging-rxjava2-ktx:$paging_version"
}
```
<br/>


#### build.gradle(Project: AndroidMVVM)

```gradle
ext {
    // CardView
    cardview_version = '1.0.0'

    // RecyclerView
    recyclerview_version = '1.0.0'

    // Koin
    koin_version = '2.0.1'

    // Retrofit2
    retrofit2_version = '2.6.0'

    // OKhttp3
    okhttp3_version = '4.0.0'

    // Gson
    gson_version = '2.8.5'

    // RxAndroid
    rxandroid_version = '2.1.1'

    // RxJava
    rxjava_version = '2.2.10'

    // AAC
    aac_version = '2.0.0'

    // Logger
    logger_version = '2.2.0'

    // Glide
    glide_version = '4.9.0'

    // Room
    room_version = '2.1.0'

    // Paging
    paging_version = '2.1.0'
}
```
<br/>
<br/>



## Reference

- [KAKAO API](https://developers.kakao.com/docs/restapi)
- [CardView and RecyclerView](https://developer.android.com/jetpack/androidx/migrate)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Retrofit2](https://github.com/square/retrofit)
- [Retrofit2:converter-gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
- [Retrofit2:adapter-rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2)
- [Okhttp3](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- [Gson](https://github.com/google/gson)
- [RxAndroid/RxJava](https://github.com/ReactiveX/RxAndroid)
- [AAC](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Logger](https://github.com/orhanobut/logger)
- [Glide](https://github.com/bumptech/glide)
- [Room](https://developer.android.com/jetpack/androidx/releases/room#declaring_dependencies)
- [Paging](https://developer.android.com/jetpack/androidx/releases/paging#declaring_dependencies)