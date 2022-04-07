package com.example.catsapi.model

import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



interface APIService {
    //'api_key=0277ef26-ef9c-4214-a3d0-de30c1d42114'
    @Headers("x-api-key: " + "0277ef26-ef9c-4214-a3d0-de30c1d42114")
    @GET(".")
    suspend fun getListCatsForBreeds(@Query("attach_breed") default: Int,
                              @Query("page") page: Int,
                              @Query("limit") limit: Int) : Response<List<CatListApiItem>>



    @Headers("x-api-key: " + "0277ef26-ef9c-4214-a3d0-de30c1d42114")
    @GET(".")
    suspend fun getCatsForBreeds_(@Query("attach_breed") default: Int,
                                     @Query("page") page: Int,
                                     @Query("limit") limit: Int) : List<CatListApiItem>
    //suspend fun getListCatsForBreeds() : Response<List<CatListApiItem>>

}

//https://api.thecatapi.com/v1/breeds&api_Key=0277ef26-ef9c-4214-a3d0-de30c1d42114