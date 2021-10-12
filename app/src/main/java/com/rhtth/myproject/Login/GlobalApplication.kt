package com.rhtth.myproject.Login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate(){
        super.onCreate()

        //KaKao SDK 초기화
        KakaoSdk.init(this, "4cd429ce0c55478bad01d9b501043861")

    }
}