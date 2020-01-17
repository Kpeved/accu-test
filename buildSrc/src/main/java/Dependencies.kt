object Versions {
    val kotlin = "1.3.61"
    val compileSdk = 29
    val targetSdk = 29
    val minSdk = 19
    val androidGradlePlugin = "3.5.3"
    val gradleVersionPlugin = "0.27.0"

    val retrofit = "2.7.1"
    val lifecycle = "2.1.0"
}

object Deps {
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionPlugin}"

    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coreKtx = "androidx.core:core-ktx:1.2.0-rc01"
    val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.0-rc01"
    val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
    val appCompat = "androidx.appcompat:appcompat:1.2.0-alpha01"

    val androidXRecyclerView = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
    val androidXCardView = "androidx.cardview:cardview:1.0.0"

    val rxJava = "io.reactivex.rxjava2:rxjava:2.2.17"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
    val rxRelay = "com.jakewharton.rxrelay2:rxrelay:2.1.1"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleApt = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    val glide = "com.github.bumptech.glide:glide:4.7.1"
    val glideApt = "com.github.bumptech.glide:compiler:4.7.1"

    val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.3.0"
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val gson = "com.google.code.gson:gson:2.8.6"

    val dagger = "com.google.dagger:dagger:2.25.4"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:2.25.4"
    val daggerAndroid = "com.google.dagger:dagger-android:2.25.4"
    val threeTen = "com.jakewharton.threetenabp:threetenabp:1.2.1"

    val daggerCompiler = "com.google.dagger:dagger-compiler:2.25.4"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:2.25.4"

    val junit = "junit:junit:4.13"
    val supportTestRunner = "com.android.support.test:runner:1.0.2"
    val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
}