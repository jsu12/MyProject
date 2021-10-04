package com.rhtth.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rhtth.myproject.databinding.ActivitySourcelaguageBinding

class SourceLaguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySourcelaguageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sourcelaguage)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sourcelaguage)

    }

    override fun onResume() {
        super.onResume()

        //한국어
        binding.sourceKo.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","한국어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //영어
        binding.sourceEn.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","영어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //일본어
        binding.sourceJa.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","일본어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //중국어(간체)
        binding.sourceZhcn.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","중국어(간체)")
            setResult(RESULT_OK,intent)
            finish()
        }

        //중국어(번체)
        binding.sourceZhtw.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","중국어(번체)")
            setResult(RESULT_OK,intent)
            finish()
        }

         //스페인어
        binding.sourceEs.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","스페인어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //프랑스어
        binding.sourceFr.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","프랑스어")
            setResult(RESULT_OK,intent)
            finish()
        }

        //러시아어
        binding.sourceRu.setOnClickListener {
            val intent = Intent(this, TranslationActivity::class.java)
            intent.putExtra("source","러시아어")
            setResult(RESULT_OK,intent)
            finish()
        }


    }

}