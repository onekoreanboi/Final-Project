package com.example.finalproject.models

data class RoundsPlayed(
    val category: String,
    val displayCategory: String,
    val displayName: String,
    val displayType: String,
    val displayValue: String,
    val metadata: Metadata,
    val percentile: Double,
    val rank: Any,
    val value: Int
)