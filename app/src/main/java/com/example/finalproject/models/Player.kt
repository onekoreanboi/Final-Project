package com.example.finalproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


// 05 This creates our class, and defines our constructor, all in one line

data class PlayerData(

    @SerializedName("player_name")
    @Expose
    val playerName: String,

    @SerializedName("player_kills")
    @Expose
    val playerKills: Int,

    @SerializedName("player_deaths")
    @Expose
    val playerDeaths: Int,

    @SerializedName("player_kd")
    @Expose
    val playerKD: Double,

    @SerializedName("player_wins")
    @Expose
    val playerWins: Int

    )

