package com.example.catsapi.repository

import com.example.catsapi.model.APIService
import com.example.catsapi.model.CatListApiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatService @Inject constructor(private val apiService: APIService) {

    suspend fun getCat(page:Int):List<CatListApiItem>{
        return try {
            withContext(Dispatchers.IO){
                val response = apiService.getListCatsForBreeds(0,page,6)
                println(response.body())
                response.body()?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}