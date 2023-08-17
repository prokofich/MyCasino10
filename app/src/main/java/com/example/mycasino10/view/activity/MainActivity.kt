package com.example.mycasino10.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino10.R
import com.example.mycasino10.constant.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        //устновка полноэкранного режима
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    //функция установки рекорада SEARCH EASY
    fun setRecordSearchEasy(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_SEARCH_EASY,record)
            .apply()
    }

    //функция установки рекорада SEARCH MIDDLE
    fun setRecordSearchMiddle(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_SEARCH_MIDDLE,record)
            .apply()
    }

    //функция установки рекорада SEARCH HARD
    fun setRecordSearchHard(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_SEARCH_HARD,record)
            .apply()
    }

    //функция установки рекорада QUESTION EASY
    fun setRecordQuestionEasy(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_QUESTION_EASY,record)
            .apply()
    }

    //функция установки рекорада QUESTION MIDDLE
    fun setRecordQuestionMiddle(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_QUESTION_MIDDLE,record)
            .apply()
    }

    //функция установки рекорада QUESTION HARD
    fun setRecordQuestionHard(record:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(RECORD_QUESTION_HARD,record)
            .apply()
    }

    //функция получения рекорда SEARCH EASY
    fun getRecordSearchEasy():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_SEARCH_EASY, 0)
    }

    //функция получения рекорда SEARCH MIDDLE
    fun getRecordSearchMiddle():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_SEARCH_MIDDLE, 0)
    }

    //функция получения рекорда SEARCH HARD
    fun getRecordSearchHard():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_SEARCH_HARD, 0)
    }

    //функция получения рекорда QUESTION EASY
    fun getRecordQuestionEasy():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_QUESTION_EASY, 0)
    }

    //функция получения рекорда QUESTION MIDDLE
    fun getRecordQuestionMiddle():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_QUESTION_MIDDLE, 0)
    }

    //функция получения рекорда QUESTION HARD
    fun getRecordQuestionHard():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_QUESTION_HARD, 0)
    }



}