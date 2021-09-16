package com.rhtth.myproject

import android.app.Application


public class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Kakao SDK 초기화
        KakaoSdk.init(this,"4cd429ce0c55478bad01d9b501043861")

    }
}
