package com.rhtth.myproject.Login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.rhtth.myproject.R
import com.rhtth.myproject.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
        //Data Binding사용
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_login)

        //anim -> animation적용
       val animation = AnimationUtils.loadAnimation(this,R.anim.animation_thank)

        //TextView Animation 1,2,3 적용
            binding.textViewAnimation1.startAnimation(animation)







    }
}