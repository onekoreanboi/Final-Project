package com.example.finalproject.models

data class Data(
    val availableSegments: List<Any>,
    val expiryDate: String,
    val metadata: Metadata,
    val platformInfo: PlatformInfo,
    val segments: List<Segment>,
    val userInfo: UserInfo
)