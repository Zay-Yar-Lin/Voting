package com.example.groupfiveproject.api

import com.example.groupfiveproject.model.KingAndQueen
import com.example.groupfiveproject.model.Vote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KingInterface {
    @GET("king")
    fun getKing(): Call<KingAndQueen>
    @POST("kingvote")
    fun voteKing(
        @Query("code") code: String,
        @Query("king_id") kingId: String?
    ): Call<Vote>

    @GET("queen")
    fun getQueen(): Call<KingAndQueen>

    @POST("queenvote")
    fun voteQueen(
        @Query("code") code: String,
        @Query("queen_id") queenId :String?
    ): Call<Vote>
}