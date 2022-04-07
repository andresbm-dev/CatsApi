package com.example.catsapi.repository

import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.database.CatDao
import com.example.catsapi.model.database.toDataBase
import com.example.catsapi.model.database.toOriginDomain
import javax.inject.Inject

class CatListRepository @Inject constructor( private val api: CatService, private val catDao: CatDao)
{
    //   private val api = CatService()
    suspend fun getallCat(page: Int):List<CatListApiItem>{
        val response = api.getCat(page)
        return if(response.isNotEmpty())
               response
        else{
               emptyList()
        }
    }

    suspend fun insertAllFavorite(cat: CatListApiItem) {
        catDao.insertAllFavorite(cat.toDataBase())
    }
    suspend fun getAllFavorite():List<CatListApiItem>{
        val response = catDao.getAllCatFav().map { it.toOriginDomain() }
        return response
    }
/*
    suspend fun getAllFavorite():List<CatListApiItem>{
        val response =catDao.getAllCatFav()
        return response
    }*/
}


