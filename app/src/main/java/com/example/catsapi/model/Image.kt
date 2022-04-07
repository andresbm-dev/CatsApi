package com.example.catsapi.model

import android.media.Image
import com.example.catsapi.model.database.ImageEntity

data class Image(
    val height: Int,
    val id: String,
    val url: String ="",
    val width: Int
)
fun ImageEntity.toOriginDomain() = Image(
    height = height,
    id = id,
    url = url,
    width = width
)
/*data class Weight(
    val imperial: String,
    val metric: String
)*/