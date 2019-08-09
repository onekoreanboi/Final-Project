package com.example.finalproject.models

data class Segment(
    val attributes: Attributes,
    val expiryDate: String,
    val metadata: MetadataX,
    val stats: Stats,
    val type: String
)