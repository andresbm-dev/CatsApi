package com.example.catsapi.model

import com.example.catsapi.model.database.CatEntity


data class CatListApiItem(
    val id: String,
    val image: Image?,
    val wikipedia_url: String,
    val name: String,
    val description: String,
    var isFavorite: Boolean = false

)
//fun CatEntity.toModel() = CatListApiItem(name =, )
