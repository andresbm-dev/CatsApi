package com.example.catsapi.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.Image
import com.example.catsapi.repository.CatListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @get:Rule
    var rule  = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        catListViewModel= CatListViewModel(repository)
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