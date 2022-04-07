package com.example.catsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsapi.adapter.CatAdapter
import com.example.catsapi.viewModel.CatListViewModel
import com.example.catsapi.R
import com.example.catsapi.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.cache2.Relay.Companion.edit
import okhttp3.internal.toImmutableList
import java.nio.file.Files.delete

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val catViewModel: CatListViewModel by viewModels()
    private lateinit var adapterrv: CatAdapter
    var page = 1
    val LIMIT = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        callCatListViewModel()

        catViewModel.isLoading.observe(this, {
            binding.progressBar.isVisible = it
        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.favoritos -> {
                Toast.makeText(this, "mis Favoritos", Toast.LENGTH_SHORT).show()
                //loadFragment(favoriteFragment)
                true
            }
            R.id.info -> {
                Toast.makeText(this, "info", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    fun initReciclerView() {
        val rv = binding.rvlist

        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        catViewModel.catListLD.observe(this) { listcat ->
            if (listcat != null)
                rv.adapter = CatAdapter(listcat, catViewModel)
        }
    }

    private fun callCatListViewModel() {
        catViewModel.getCatListApi(page)
        initReciclerView()
        val scroll = binding.scrollView

        scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page++

                if (page>LIMIT){
                    Toast.makeText(this, "has llegado al final de los datos", Toast.LENGTH_SHORT).show()
                }else {

                    catViewModel.getCatListApi(page)
                    val rv = binding.rvlist
                    catViewModel.catMListLD.observe(this) {
                        val cats = it.toImmutableList()
                        rv.adapter = CatAdapter(cats, catViewModel)

                    }
                    Toast.makeText(this, "Cargando Razas", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

}

