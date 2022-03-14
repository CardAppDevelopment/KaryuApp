package com.example.practice.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChargeResponseBean(
    val date: String,
    val hash: String,
    val id_from: Int,
    val id_to: Long,
    val money: Int,
    val msg: String,
    val success: Boolean,
    val time: String
): Parcelable