package com.rhtth.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rhtth.myproject.databinding.ActivityTargetlanguageBinding

class TargetLanguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTargetlanguageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_targetlanguage)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_targetlanguage)

    }

    override fun onResume() {
        super.onResume()

        //한국어
        binding.targetKo.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","한국어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //영어
        binding.targetEn.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","영어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //일본어
        binding.targetJa.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","일본어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //중국어(간체)
        binding.targetZhcn.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","중국어(간체)")
            setResult(RESULT_OK,intent)
            finish()
        }

        //중국어(번체)
        binding.targetZhtw.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","중국어(번체)")
            setResult(RESULT_OK,intent)
            finish()
        }

        //스페인어
        binding.targetEs.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","스페인어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //프랑스어
        binding.targetFr.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","프랑스어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //러시아어
        binding.targetRu.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("target","러시아어")
            setResult(RESULT_OK,intent)
            finish()
        }


    }

}