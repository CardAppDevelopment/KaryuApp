package com.example.practice.bean

import android.content.Context
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.io.File
import java.sql.Time
import java.util.*

@Parcelize
data class QRData (
    val id:String,
    val name:String,
    val date:String,
    val time:String,
    val hash:String
): Parcelable


/*data class QRData (
    val id:Int,
    val name:String,
    val date:Date,
    val time:Time,
    val hash:String
){
    companion object{
        var data:QRData=_parseJson("{\n" +
                "  \"id\":1234567890,\n" +
                "  \"name\":\"名前\",\n" +
                "  \"date\":\"2022/01/01\",\n" +
                "  \"time\":\"12:00\",\n" +
                "  \"hash\":\"8018155fe6dca2ef3e713e6ecbc4e6b5649facd6fe12306f8f9d1c38dae0ea79\"\n" +
                "}")

        fun get():QRData{
            return data
        }

        fun _parseJson(fileName:String):QRData{

            val source= fileName
            return Gson().fromJson(source,QRData::class.java)
        }
    }
}*/

