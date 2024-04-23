package com.example.mycasino10.model.api

import com.example.mycasino10.model.ResponceWebView
import com.example.mycasino10.model.ResponseText
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phoneName:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phoneName, locale, unique)
    }

    suspend fun getTextRulesSearch(): Response<ResponseText> {
        return RetrofitInstance.api.getTextRulesSearch()
    }

    suspend fun getTextRulesQuestion(): Response<ResponseText> {
        return RetrofitInstance.api.getTextRulesQuestion()
    }

}