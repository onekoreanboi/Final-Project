package com.example.finalproject.network

import com.example.finalproject.models.PlayerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Endpoints{

    @GET("v2/csgo/standard/profile/steam")
    fun getPlayerList(): Call<List<PlayerData>>

    @GET("v2/csgo/standard/profile/steam/{steamId}?")
    fun getPlayerAPIKey(@Path("steamId") steamIdInput: String,
                        @Header("x-api-key") key: String = "TRN-Api-Key=4da12bea-87ca-49c5-b037-1b1abdc9fda7"): Call<List<PlayerData>>

}