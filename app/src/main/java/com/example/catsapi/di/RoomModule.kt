package com.example.catsapi.di

import android.content.Context
import androidx.room.Room
import com.example.catsapi.model.CatDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    //se crea base de datos y se inyecta
    private val CAT_DATA_BASE_NAME = "cat_database"

    //inyectamos el database o room
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)= Room.databaseBuilder(context, CatDataBase::class.java, CAT_DATA_BASE_NAME).build()

    //inyectamos DAO
    @Singleton
    @Provides
    fun provideCatDao(db: CatDataBase) = db.getCatsDao()

}