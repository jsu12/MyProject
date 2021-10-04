package com.rhtth.myproject


import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rhtth.myproject.Login.KakaoLoginActivity
import com.rhtth.myproject.Login.LoginActivity
import com.rhtth.myproject.Login.RegisterActivity


import com.rhtth.myproject.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainLoginBinding
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)


        //anim -> animation적용
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_thank)
        //Data Binding사용
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_login)

        //TextView Animation 1,2,3 적용(감사합니다 text)
        binding.textViewAnimation.startAnimation(animation)

        //카카오로그인 버튼 클릭 시 -> 카카오 로그인 API 호출
        binding.buttonKakaoLogin.setOnClickListener {
            val intent = Intent(this, KakaoLoginActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        //E-mail로그인 버튼 클릭 시 -> Email 로그인 Activity호출
        binding.buttonEmailLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        //회원가입 버튼 클릭 시 ->RegisterActivity호출
        binding.register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }


    }
        //Activity Restart시 Animation효과 재시작
        override fun onRestart() {
            super.onRestart()
            binding.textViewAnimation.startAnimation(animation)
        }

        //Activity Pause시 Animation 효과 정지
        override fun onPause() {
            super.onPause()
            binding.textViewAnimation.clearAnimation()
        }





}


