package com.example.catsapi.di

import com.example.catsapi.model.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //se utiliza para injectar modulos, librerias, dependencias que provee Android
@InstallIn(SingletonComponent::class) // se define el alcance la cual se puede proveer de cualqier parte de la aplicación
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit () : Retrofit{  //proveer Retrofit para poder ser injectado en Repositorio
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService{
        return retrofit.create(APIService::class.java)
    }
}