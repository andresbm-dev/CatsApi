package com.example.catsapi.repository

import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.Image
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class CatListRepositoryTest{

    @RelaxedMockK
    private lateinit var api: CatService

    lateinit var catListRepository: CatListRepository
    @Before
    fun onBefore(){
      MockKAnnotations.init(this)
        catListRepository= CatListRepository(api)
    }

    @Test
    fun `obtener De Repositorio LaRespuesta De Api Cuando Es Vacio El Api`() = runBlocking {
        //Given When Then
        //Given
        val list = emptyList<CatListApiItem>()
        coEvery { api.getCat(1) } returns list
        //When
       val response =  catListRepository.getallCat(1)
        //Then
        assert(response == list)
    }

    @Test
    fun `obtener DE Repositorio La Respuesta De Api  cuando no es Vacio`() = runBlocking {
        //Given When Then
        //Given
        val image = Image(1445, width = 1204, id = "0XYvRd7oD", url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")
        val list = listOf(CatListApiItem(id = "abys", name = "Abyssinian", description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                wikipedia_url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)", image = image))
        coEvery { api.getCat(1) } returns list
        //When
        val response = catListRepository.getallCat(1)
        //Then
        assert(response == list)
    }


}