package com.example.practice.network

import com.example.practice.network.base.BaseNetworkApi

/**
 * ネットワーク請求実施
 * サーバーのURL：https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/
 */
object NetworkApi : BaseNetworkApi<INetworkService>("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/") {
    //履歴APIをコール
    suspend fun requestHistoryInfo(startDate:String,endDate:String) = getResult {
        service.requestHistoryInfo(startDate,endDate)
    }
    //残高APIをコール
    suspend fun requestLeftMoney() = getResult {
        service.requestLeftMoney()
    }
    //お知らせAPIをコール
    suspend fun requestNotificationInfo() = getResult {
        service.requestNotificationInfo()
    }
}