package com.example.catsapi.Domain

import android.graphics.pdf.PdfDocument
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.repository.CatListRepository
import javax.inject.Inject

class GetApiUseCase @Inject constructor(private val repository: CatListRepository){

    suspend fun getAllCat(page: Int):List<CatListApiItem> {
        return repository.getallCat(page)
    }


}