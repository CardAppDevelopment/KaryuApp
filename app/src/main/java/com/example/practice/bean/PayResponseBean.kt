package com.example.practice.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PayResponseBean(
    val payInfo: PaymentInfo
): Parcelable

@Parcelize
data class PaymentInfo(
    val date: String,
    val hash: String,
    val id_from: Int,
    val id_to: Long,
    val money: Int,
    val msg: String,
    val success: Boolean,
    val time: String
): Parcelable