package com.example.practice.module.pay

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.bean.PayRequestBean
import com.example.practice.bean.PayResponseBean
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class PayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pay Fragment"
    }
    val text: LiveData<String> = _text

    val payLiveData = MutableLiveData<Result<PayResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var paymentInfo = payLiveData.value

    fun getPayResponse(request: PayRequestBean) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            // read data from networkapi
            val resultFromNetwork = NetworkApi.requestPaymentInfo(request)
            payLiveData.value=resultFromNetwork
            loadingLiveData.postValue(false)
        }
    }

    fun getPayInfo(request: PayRequestBean) {
        val paymentData = NetworkApiTest("https://2e9b84ba-4658-4bed-9499-cd89f96964a4.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            paymentInfo = paymentData.requestPaymentInfo(request)
            loadingLiveData.postValue(false)
        }
    }

}