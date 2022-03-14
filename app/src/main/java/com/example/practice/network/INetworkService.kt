package com.example.practice.network



import com.example.practice.bean.*
import com.example.practice.module.MyData
import com.example.practice.network.base.BaseResponse
import retrofit2.http.*

interface INetworkService {

    @GET("dev/homeinfo")
    suspend fun requestHomeInfo(): BaseResponse<HomeInfoBean>

    @GET("dev/history")
    suspend fun requestHistoryInfo(@Query("start") startDate: String,@Query("end") endDate: String): BaseResponse<HistoryBean>

    @GET("dev/left_money/{id}")
    suspend fun requestLeftMoney(@Path("id") id: String): BaseResponse<LeftMoneyBean>

    @GET("dev/notification_data")
    suspend fun requestNotificationInfo(): BaseResponse<NotificationBean>

    @POST("dev/pay")
    suspend fun requestPaymentInfo(@Body request:PayRequestBean): BaseResponse<PayResponseBean>

    @POST("dev/card_num_login")
    suspend fun requestCardNumLogin(@Body request:CardLoginRequestBean): BaseResponse<CardLoginResponseBean>

    @GET("dev/get_currentLimit")
    suspend fun requestGetCurrentLimit(): BaseResponse<CurrentLimitResponseBean>

    @POST("dev/send_currentlimit_change")
    suspend fun requestCurrentLimit(@Body request:CurrentLimitRequestBean): BaseResponse<ChangeLimitResponseBean>

    @GET("dev/get_custmerInfo")
    suspend fun requestGetCustomerInfo(): BaseResponse<CustomerInfoResponse>

    @POST("dev/pay")
    suspend fun requestChargeInfo(@Body request:ChargeRequestBean): BaseResponse<ChargeResponseBean>

}