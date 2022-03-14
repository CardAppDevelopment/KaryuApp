package com.example.practice.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.*
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val leftMoneyLiveData = MutableLiveData<Result<LeftMoneyBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val chargeLiveData = MutableLiveData<Result<ChargeResponseBean>>()
    var chargeInfo = chargeLiveData.value


    fun getLeftMoneyData(){
        loadingLiveData.postValue(true)
        viewModelScope.launch {

            val resultFromNetwork = NetworkApi.requestLeftMoney()
            leftMoneyLiveData.value=resultFromNetwork
            loadingLiveData.postValue(false)
        }
    }

    fun getchargeInfo(request: ChargeRequestBean) {
//        val chargeData = NetworkApiTest("https://2e9b84ba-4658-4bed-9499-cd89f96964a4.mock.pstmn.io")
        val chargeData = NetworkApiTest("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/")

        loadingLiveData.postValue(true)

        viewModelScope.launch {
            chargeInfo = chargeData.requestChargeInfo(request)
            loadingLiveData.postValue(false)
        }
    }

}