package com.rhtth.myproject.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rhtth.myproject.R


class FindAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_account)

        val emailAccount : EditText = findViewById(R.id.edit_findAccount)
        val sendEmail : Button = findViewById(R.id.sendEmail)
        //String Type으로 변환 (사용자로 부터 입력받은 이메일)


        //firebase 사용자 프로필 가져오기
       // val user = Firebase.auth.currentUser
        sendEmail.setOnClickListener {

            //사용자로부터 입력받는 이메일
            val editEmail : String = emailAccount.text.toString().trim()

            if(editEmail.isNotEmpty() && isEmail(editEmail)) {
                Firebase.auth.sendPasswordResetEmail(editEmail)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "이메일을 전송하였습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "존재하지 않는 이메일 입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else if(editEmail.isEmpty()){
                Toast.makeText(this,"이메일을 입력해 주세요",Toast.LENGTH_SHORT).show()
            }else if(!isEmail(editEmail)){
                Toast.makeText(this,"올바른 이메일 형식을 입력해 주세요",Toast.LENGTH_SHORT).show()
            }
        }


    }


}