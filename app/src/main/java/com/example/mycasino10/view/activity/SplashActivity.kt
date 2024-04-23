package com.example.mycasino10.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino10.R
import com.example.mycasino10.model.constant.APP_PREFERENCES
import com.example.mycasino10.model.constant.ID
import com.example.mycasino10.model.constant.url_image_background
import com.example.mycasino10.model.constant.url_image_emblema
import com.example.mycasino10.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import java.util.*

class SplashActivity : AppCompatActivity() {

    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        val namePhone = Build.MODEL.toString()
        val locale = Locale.getDefault().displayLanguage.toString()
        var id = ""

        if (getMyId()!=""){
            id = getMyId()
        }else{
            id = UUID.randomUUID().toString()
            setMyId(id)
        }

        //загрузка эмблемы игры
        loadBackgroundImage(url_image_emblema,id_splash_iv_emblema)

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_splash_img)

        splashViewModel.setPostParametersPhone(namePhone,locale,id)

        splashViewModel.webViewUrl.observe(this){ responce ->
            when(responce.body()!!.url){
                "no" -> { goToMainPush() }
                "nopush" -> { goToMainNoPush() }
                else -> { goToWeb(responce.body()!!.url) }
            }
        }


    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(this)
            .load(url)
            .into(id)
    }

    //выход по кнопку назад
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        job.cancel()
        finishAffinity()
    }

    private fun getMyId() : String {
        return getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(ID,"").toString()
    }

    private fun setMyId(id : String) {
        getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().putString(ID,id).apply()
    }

    private fun goToMainPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }

    private fun goToMainNoPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }

   private fun goToWeb(url:String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            val intent = Intent(this@SplashActivity,WebViewActivity::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }
    }

}