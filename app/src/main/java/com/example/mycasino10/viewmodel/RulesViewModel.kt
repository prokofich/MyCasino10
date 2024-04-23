package com.example.mycasino10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino10.model.api.Repository
import com.example.mycasino10.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RulesViewModel:ViewModel() {

    private val repository = Repository()
    val text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextRulesSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repository.getTextRulesSearch()
            withContext(Dispatchers.Main){
                text.value = responce
            }
        }
    }

    fun getTextRulesQuestion(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repository.getTextRulesQuestion()
            withContext(Dispatchers.Main){
                text.value = responce
            }
        }
    }

}