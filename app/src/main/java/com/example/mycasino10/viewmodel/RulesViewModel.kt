package com.example.mycasino10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino10.api.Repository
import com.example.mycasino10.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RulesViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextRulesSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextRulesSearch()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

    fun getTextRulesQuestion(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextRulesQuestion()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}