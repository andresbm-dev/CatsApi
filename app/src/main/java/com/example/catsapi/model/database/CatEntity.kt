package com.example.catsapi.model.database

import androidx.room.*
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.Image
import com.example.catsapi.model.toOriginDomain

@Entity(tableName = "catlist_table")
data class CatEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isfavorite") val isfavorite: Boolean,
    @Embedded  val image: ImageEntity?,
    @ColumnInfo(name = "wikipedia_url")val wikipedia_url: String,
    @ColumnInfo(name = "description")val description: String,

    )

data class ImageEntity(
    val height: Int,
    val id: String,
    val url: String ="",
    val width: Int
)
//convirtiendo el ImageEntity en Image
fun Image.toDataBase()= ImageEntity(height,id,url,width)

fun CatListApiItem.toDataBase()= CatEntity(name = name,
    isfavorite = isFavorite,
    wikipedia_url = wikipedia_url,
    description = description,
    image = image?.toDataBase(),
    id = id
)

fun CatEntity.toOriginDomain() = CatListApiItem(
    name = name,
    description = description,
    wikipedia_url = wikipedia_url,
    image = image?.toOriginDomain(),
    isFavorite = isfavorite,
    id = id
)
