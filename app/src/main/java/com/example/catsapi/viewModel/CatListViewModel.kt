package com.example.catsapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapi.Domain.GetFavoritesRoomUseCase
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.repository.CatListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CatListViewModel
    @Inject constructor (private val repository : CatListRepository,
    private val getFavoritesRoomUseCase: GetFavoritesRoomUseCase): ViewModel() {

    private val catListMLD = MutableLiveData<List<CatListApiItem>>()
    val catListLD: LiveData<List<CatListApiItem>> get() = catListMLD

    val isLoading = MutableLiveData<Boolean>()
    private var catMutable = mutableListOf<CatListApiItem>()

    private var catMutablelistLD_ = MutableLiveData<MutableList<CatListApiItem>>()
    val catMListLD: LiveData<MutableList<CatListApiItem>> get() = catMutablelistLD_

    fun getCatListApi(page: Int): MutableLiveData<List<CatListApiItem>> {

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = repository.getallCat(page)

            if (result.isNotEmpty()) {
                getFavoritesRoomUseCase.getFavorite(result)

                catListMLD.postValue(result)
                catMutable.addAll(result)
                catMutablelistLD_.postValue(catMutable)

            }
            println(result)
            println("el valor del mutable es $catListMLD")
            isLoading.postValue(false)
        }
        return catListMLD
    }


    fun toggleFavorite(cat: CatListApiItem) {
        val catIndex = catMutable.indexOf(cat)
        catMutable[catIndex].isFavorite = !catMutable[catIndex].isFavorite
        catListMLD.postValue(catMutable)
        catMutablelistLD_.postValue(catMutable)
    }

    fun insertAllFavorite(cat: CatListApiItem) {
        viewModelScope.launch {
            repository.insertAllFavorite(cat)
        }
    }


}



