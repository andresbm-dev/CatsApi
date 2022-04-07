package com.example.catsapi.Domain

import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.repository.CatListRepository
import javax.inject.Inject

class GetFavoritesRoomUseCase @Inject constructor(private val repository: CatListRepository) {

    suspend fun getFavorite(result: List<CatListApiItem>){
        val catListFavorites = repository.getAllFavorite()
        result.onEach {
            it.isFavorite = isCatFavorite(it, catListFavorites)
        }

    }
    private fun isCatFavorite(cat: CatListApiItem, catListFavorites: List<CatListApiItem>): Boolean {
        val catFromFavorites = catListFavorites.filter {
            it.id == cat.id
        }.firstOrNull()

        return catFromFavorites?.isFavorite?: false
    }

}