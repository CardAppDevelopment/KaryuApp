package com.example.practice.module.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.bean.NotificationBean
import com.example.practice.bean.NotificationData
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {
    val notificationsListLiveData = MutableLiveData<Result<NotificationBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val pullToRefreshLiveData = MutableLiveData<Boolean>()

    val test_notificationsListLiveData=MutableLiveData<NotificationBean>()

    fun getNotificationsList() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val notificationsData= NetworkApiTest("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
            loadingLiveData.postValue(false)
        }

        notification_json_disassembly()
    }

    fun getPTRNotificationsList() {
        pullToRefreshLiveData.postValue(true)
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val notificationsData= NetworkApiTest("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
            pullToRefreshLiveData.postValue(false)
            loadingLiveData.postValue(false)
        }
    }

    fun notification_json_disassembly(): NotificationBean {
        return NotificationBean(mutableListOf(
            NotificationData("店舗1","メッセージ1"),
            NotificationData("店舗2","メッセージ2"),
            NotificationData("店舗3","メッセージ3")
        ))
    }
}