package com.example.finalproject.network

import com.example.finalproject.models.NewPlayerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Endpoints{

    @GET("v2/csgo/standard/profile/steam")
    fun getPlayerList(): Call<NewPlayerData>

    @GET("v2/csgo/standard/profile/steam/{steamId}?TRN-Api-Key=4da12bea-87ca-49c5-b037-1b1abdc9fda7")
    fun getPlayerAPIKey(@Path("steamId") steamIdInput: String,
                        @Header("x-api-key") key: String = "TRN-Api-Key=4da12bea-87ca-49c5-b037-1b1abdc9fda7"): Call<NewPlayerData>

}

//https://public-api.tracker.gg/v2/csgo/standard/profile/steam/76561198972125110?TRN-Api-Key=4da12bea-87ca-49c5-b037-1b1abdc9fda7