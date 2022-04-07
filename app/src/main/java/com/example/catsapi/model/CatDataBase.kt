package com.example.catsapi.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.catsapi.model.database.CatDao
import com.example.catsapi.model.database.CatEntity

@Database(entities = [CatEntity::class] , version = 1)
abstract class CatDataBase : RoomDatabase(){

    abstract fun getCatsDao(): CatDao

}
