package com.rhtth.myproject.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.rhtth.myproject.R
import com.rhtth.myproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        auth= Firebase.auth


    }

    override fun onStart() {

        super.onStart()

        binding.buttonRegister.setOnClickListener {

            val name = binding.registerName.text.toString().trim()
            val email = binding.registerEmail.text.toString().trim()
            val password = binding.registerPassword.text.toString().trim()
            val passwordCheck = binding.registerPasswordCheck.text.toString().trim()
            createAccount(name,email,password,passwordCheck)

        }

    }

    //계정 생성
    fun createAccount(name : String, email : String,
                      password : String, passwordCheck : String) {


        if (email.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty()
            && password == passwordCheck) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        //user 프로필에 이름 저장
                        val profileUpdates = userProfileChangeRequest {
                            displayName = name
                        }
                        user!!.updateProfile(profileUpdates)

                        Toast.makeText(this, "계정 생성 완료.", Toast.LENGTH_SHORT).show()
                        finish() // 가입창 종료
                    } else if(password.length<6){
                        Toast.makeText(this,"비밀번호를 6자리 이상 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    }else if(!isEmail(email)){
                        Toast.makeText(this,"올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        when{
                            name.isEmpty()  ->
                                Toast.makeText(this,"이름을 입력하지 않았습니다.",Toast.LENGTH_SHORT).show()
                            email.isEmpty()  ->
                                Toast.makeText(this,"이메일을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show()
                            password != passwordCheck ->
                                Toast.makeText(this,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                            password.isEmpty() || passwordCheck.isEmpty() ->
                                Toast.makeText(this,"비밀번호를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    }
                    }
        }

        }





