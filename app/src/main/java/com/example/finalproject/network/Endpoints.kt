package com.example.finalproject.network

import com.example.finalproject.models.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Endpoints{

    @GET("v2/csgo/standard/profile/steam")
    fun getStatsList(): Call<List<Player>>

    @GET("v2/csgo/standard/profile/steam/{steamId}")
    fun getSteamId(@Path("steamId") steamIdInput: Int): Call<Player>

    @GET("v2/csgo/standard/profile/steam/{steamId}?")
    fun getStatsAPIKey(@Header("TRN-Api-Key=4da12bea-87ca-49c5-b037-1b1abdc9fda7") key: String): Call<List<Player>>

}