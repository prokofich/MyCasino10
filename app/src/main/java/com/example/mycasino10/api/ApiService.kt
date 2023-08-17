package com.example.mycasino10.api

import com.example.mycasino10.model.ResponceWebView
import com.example.mycasino10.model.ResponseText
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("splash.php")
    suspend fun setPostParametersPhone(
        @Field("phone_name") phone_name:String,
        @Field("locale") locale:String,
        @Field("unique") unique:String
    ): Response<ResponceWebView>


    @GET("17/RulesSearch.json")
    suspend fun getTextRulesSearch():Response<ResponseText>

    @GET("17/RulesQuestion.json")
    suspend fun getTextRulesQuestion():Response<ResponseText>

}