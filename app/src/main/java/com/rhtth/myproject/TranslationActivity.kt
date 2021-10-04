package com.rhtth.myproject


import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.rhtth.myproject.databinding.ActivityTranslationBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import javax.xml.transform.Source
import kotlin.concurrent.thread


class TranslationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTranslationBinding

    //다른 Activity로 부텨 결과값을 받기 위함
    private lateinit var getResultSource: ActivityResultLauncher<Intent>
    private lateinit var getResultTarget: ActivityResultLauncher<Intent>

    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    val url = "https://openapi.naver.com/v1/papago/n2mt"
    val client = OkHttpClient()
    //body로 넘길 json에 필요한 것들 넣기 (네이버 API 참고)
    var json = JSONObject()
    var source_lan : String ?= ""
    var source_data : String? = ""
    var target_data : String? = ""
    var target_lan : String ?= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_translation)

        //초기 기본값 설정
        source_data="한국어"
        target_data="영어"

    }


    override fun onStart() {
        super.onStart()

        //SourceLanguageActivity로 부터 source_data값 가져와서 화면에 출력
        getResultSource = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                source_data = result.data?.getStringExtra("source")
                binding.sourceLanguage.setText(source_data)
            }
        }
        //targetLanguageActivity로 부터 target_data값 가져와서 화면에 출력
        getResultTarget = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                target_data = result.data?.getStringExtra("target")
                binding.targetLanguage.setText(target_data)
            }
        }

        //입력 언어 버튼 클릭시 ->SourceLanguageActivity의 결과값 가져옴
        binding.sourceLanguage.setOnClickListener {
            val intent = Intent(this, SourceLaguageActivity::class.java)
            getResultSource.launch(intent)
        }

        //타겟 언어 버튼 클릭시 ->TargetLanguageActivity의 결과값 가져옴
        binding.targetLanguage.setOnClickListener {
            val intent = Intent(this, TargetLanguageActivity::class.java)
            getResultTarget.launch(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        //선택된 입력 언어에 따라 서버에 보내질 source_lan값 저장
        when(source_data){
            "한국어" -> source_lan ="ko"
            "영어" ->source_lan ="en"
            "일본어" -> source_lan ="ja"
            "중국어(간체)" -> source_lan ="zh-cn"
            "중국어(번체)" -> source_lan = "zh-tw"
            "프랑스어" ->source_lan ="fr"
            "러시아어" ->source_lan ="ru"
            "스페인어" -> source_lan ="es"
        }

        //선택된 타겟 언어에 따라 서버에 보내질 target_lan 저장
        when(target_data){
            "한국어" -> target_lan ="ko"
            "영어" ->target_lan ="en"
            "일본어" -> target_lan ="ja"
            "중국어(간체)" -> target_lan ="zh-cn"
            "중국어(번체)" -> target_lan = "zh-tw"
            "프랑스어" ->target_lan ="fr"
            "러시아어" ->target_lan ="ru"
            "스페인어" -> target_lan ="es"
        }

        //종이비행기 버튼 클릭시 입력 언어 번역
        binding.buttonTranslation.setOnClickListener {

            //입력 언어
            val translation_text = binding.translationText.getText().toString()

            //json객체에 데이터 삽입 source,target,text
            json.put("source", source_lan)
            json.put("target", target_lan)
            json.put("text", translation_text)

            val body = json.toString().toRequestBody(JSON)

            //papago api 요청
            val request = Request.Builder()
                .header("X-Naver-Client-Id", "BbcqDohfdwRkYQPqmAWe")
                .addHeader("X-Naver-Client-Secret", "qP6EDae91j")
                .url(url)
                .post(body)
                .build()

            //callback
            client.newCall(request).enqueue(object :okhttp3.Callback {
                //서버 통신 실패 했을 경우 실행 될 코드
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                }

                // main thread말고 별도의 thread에서 실행해야 함. 네트워크 작업,UI작업
                override fun onResponse(call: okhttp3.Call, response: Response) {
                    Thread{
                        //파파고API로 부터 번역된 언어를 Gson형식으로 바꾸어 PapagoDTO에 저장
                        var PapagoDTO = Gson().fromJson(response.body?.string(), PapagoDTO::class.java)

                        //번역된 언어를 저장할 변수
                        var resultText = PapagoDTO.message?.result?.translatedText.toString()
                        runOnUiThread {
                            if(resultText == "null"){
                                    binding.resultText.setText("올바른 언어를 입력해 주세요")
                            }else binding.resultText.setText(resultText)
                        }
                    }.start()
                }
            })
        }

    }


    //앱을 다시시작해도 번역 기록 남아있음
    override fun onRestart() {
        super.onRestart()
        savedStateRegistry
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}