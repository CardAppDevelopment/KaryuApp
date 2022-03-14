package com.example.practice.bean

data class ChargeRequestBean(
    val date: String,
    val hash: String,
    val id_from: String,
    val id_to: String,
    val money: Int,
    val time: String
)