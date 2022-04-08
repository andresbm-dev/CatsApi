package com.example.catsapi.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catsapi.Domain.GetFavoritesRoomUseCase
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.Image
import com.example.catsapi.repository.CatListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CatListViewModelTest{
    private lateinit var catListViewModel: CatListViewModel

    @RelaxedMockK
    private lateinit var repository: CatListRepository

    @RelaxedMockK
    private lateinit var  getFavoritesRoomUseCase: GetFavoritesRoomUseCase

    @get:Rule
    var rule  = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        catListViewModel= CatListViewModel(repository, getFavoritesRoomUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
        //crear un Regla = Función abstraida
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando se crea el viewModel se obtienen los datos del repositorio y se setean los livedata`() =runTest{
        //Given
        val image = Image(1445, width = 1204, id = "0XYvRd7oD", url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")
        val catlist = listOf(
            CatListApiItem(id = "abys", name = "Abyssinian", description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            wikipedia_url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)", image = image)
        )
        //cada vez que lo de adentro se ha llamado...
        coEvery { repository.getallCat(1) } returns catlist

        //When
        catListViewModel.getCatListApi(1)
        //then
        assert(catListViewModel.catListLD.value == catlist)
    }

    @Test
    fun `obtener de desde repositorio una lista de favoritos de base de datos`() = runTest {
        val image = Image(
            1445,
            width = 1204,
            id = "0XYvRd7oD",
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        )
        val image_ = Image(
            id = "hBXicehMA",
            url = "https://cdn2.thecatapi.com/images/hBXicehMA.jpg",
            width = 1600,
            height = 951
        )
        val list = listOf(
            CatListApiItem(
                id = "abys",
                name = "Abyssinian",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                wikipedia_url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                image = image,
                isFavorite = true
            ),
            CatListApiItem(
                id = "abob",
                name = "American Bobtail",
                description = "American Bobtails are loving and incredibly intelligent cats possessing a distinctive wild appearance. They are extremely interactive cats that bond with their human family with great devotion.",
                wikipedia_url = "https://en.wikipedia.org/wiki/American_Bobtail",
                isFavorite = true,
                image = image_
            )
        )

        coEvery { repository.getAllFavorite() } returns list

        catListViewModel.getFavorite()

        assert(catListViewModel.favMListLD.value == list)
    }

    /*@Test
    fun `verificar que se ha insertado un gato favorito a la base de datos`()= runTest {
        val image = Image(
            1445,
            width = 1204,
            id = "0XYvRd7oD",
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        )
        val cat = CatListApiItem(id = "abys", name = "Abyssinian", description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
            wikipedia_url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)", image = image)
        //GIVEN
        coEvery { repository.insertAllFavorite(cat)}
        //WHEN
        catListViewModel.insertAllFavorite(cat)

        //THEN
        coVerify { repository.insertAllFavorite(cat) }
    }*/

    /*@Test
    fun `cuando se pulsa el boton de favoritos se actualiza el valor de isfavorite del modelo de datos`()=
        runTest {
            val image = Image(1445, width = 1204, id = "0XYvRd7oD", url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")

            val cat = CatListApiItem(id = "abys", name = "Abyssinian", description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                wikipedia_url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)", image = image, isFavorite = false)

           coEvery {  catListViewModel.catMListLD.value } returns
            //necesitamos probar que cada vez que se pulse el boton el valor del livedata obtiene
            //el valor negado de cat.isfavorite
            //when
            catListViewModel.toggleFavorite(cat)
            //then
            //assert()
        }*/

}