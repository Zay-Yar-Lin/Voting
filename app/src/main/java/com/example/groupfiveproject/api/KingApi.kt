package com.example.groupfiveproject.api

import com.example.groupfiveproject.model.KingAndQueen
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KingApi {
    private val kingInterface: KingInterface

    companion object{
        const val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        kingInterface=retrofit.create(KingInterface::class.java)
    }
    fun getKing():Call<KingAndQueen>{
        return kingInterface.getKing()
    }
    fun getQueen():Call<KingAndQueen>{
        return kingInterface.getQueen()
    }
}