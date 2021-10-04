    package com.rhtth.myproject


    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.Toast
    import com.kakao.sdk.user.UserApiClient




    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.temporary_layout)


            val kakao_logout_button = findViewById<Button>(R.id.button_logout)

            kakao_logout_button.setOnClickListener {
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                    }

                    val intent = Intent(this, MainLoginActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }

            val kakao_unlink_button = findViewById<Button>(R.id.button_delete_account) // 로그인 버튼

            kakao_unlink_button.setOnClickListener {
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                        Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainLoginActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()

                    }
                }
            }


        }
    }




