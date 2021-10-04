package com.rhtth.myproject.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rhtth.myproject.MainActivity
import com.rhtth.myproject.MainLoginActivity
import com.rhtth.myproject.R
import com.rhtth.myproject.TranslationActivity
import com.rhtth.myproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //binding변수 선언
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

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

        auth = Firebase.auth

        //Toolbar 지원
        setSupportActionBar(binding.loginToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)   //Actionbar에 표시되는 제목의 표시유무
        supportActionBar?.setTitle("로그인")                             //Actionbar Text내용
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Toolbar의 왼쪽에 뒤로가기 아이콘 추가

        //사용자가 입력한 email과 password를 string형식으로 저장


        //로그인 버튼 클릭시 동작내용
        binding.btnLogin.setOnClickListener {

            val userEmail = binding.etEmail.text.toString().trim()
            val userPassword = binding.etPw.text.toString().trim()
            signIn(userEmail,userPassword)
        }

    }

    override fun onStart() {
        super.onStart()
        //자동로그인 및 회원가입 후 바로 로그인
      //  movePage(auth?.currentUser)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainLoginActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    //로그인시 수행할 작업
    private fun signIn(email : String, password : String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth?.signInWithEmailAndPassword(email,password)
                ?.addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"로그인에 성공 하였습니다",Toast.LENGTH_SHORT).show()
                        movePage(auth?.currentUser)
                    }else{
                        Toast.makeText(this,"로그인에 실패 하였습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this,"알 수 없는 에러가 발생하였습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    //페이지 이동 함수
    fun movePage(user : FirebaseUser?){
        if(user!=null){
            val intent = Intent(this,TranslationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}