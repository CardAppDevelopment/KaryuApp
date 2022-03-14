package com.example.practice.network

import com.example.practice.bean.PayRequestBean
import com.example.practice.module.MyData
import com.example.practice.network.base.BaseNetworkApi

/**
 * ネットワーク請求実施
 * サーバーのURL：https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/
 */
object NetworkApi : BaseNetworkApi<INetworkService>("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/") {
//object NetworkApi : BaseNetworkApi<INetworkService>("https://d664bb3e-e2c0-4643-bfcf-8cb71ce79026.mock.pstmn.io") {
    //履歴APIをコール
    suspend fun requestHistoryInfo(startDate:String,endDate:String) = getResult {
        service.requestHistoryInfo(startDate,endDate)
    }
    //残高APIをコール
    suspend fun requestLeftMoney() = getResult {
        service.requestLeftMoney(MyData().getID())
    }
    //お知らせAPIをコール
    suspend fun requestNotificationInfo() = getResult {
        service.requestNotificationInfo()
    }

    //お支払うAPIをコール
    suspend fun requestPaymentInfo(request: PayRequestBean) = getResult {
        service.requestPaymentInfo(request)
    }
}