package com.rhtth.myproject.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.rhtth.myproject.R
import com.rhtth.myproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //binding변수 선언
    private lateinit var binding: ActivityLoginBinding

    //Toolbar의 뒤로가기 기능
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        val toolbar_item = item.itemId
        when(toolbar_item) {
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Data Binding사용
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //Toolbar 지원
        setSupportActionBar(binding.loginToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)   //Actionbar에 표시되는 제목의 표시유무
        supportActionBar?.setTitle("로그인")                             //Actionbar Text내용
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Toolbar의 왼쪽에 뒤로가기 아이콘 추가


    }
}