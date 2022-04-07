package com.example.catsapi.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatDao {
    @Query("SELECT * FROM catlist_table WHERE isfavorite == 1 ORDER BY name DESC ")
    suspend fun getAllCatFav():List<CatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorite(cat:CatEntity)

    @Delete()
    suspend fun deleteFavorite( cats : List<CatEntity>)

}