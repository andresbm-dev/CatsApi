package com.example.catsapi.viewFavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsapi.R
import com.example.catsapi.databinding.ActivityDetailBinding
import com.example.catsapi.databinding.ActivityFavoriteBinding
import com.example.catsapi.viewFavorite.adapter.FavoriteAdapter
import com.example.catsapi.viewModel.CatListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFavoriteBinding
    private  val catViewModel: CatListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()

        //setContentView(R.layout.activity_favorite)


    }

    private fun initRecyclerView() {
        val rvFav = binding.rvfav
        rvFav.layoutManager = LinearLayoutManager(this)
        catViewModel.getFavorite()
        catViewModel.favMListLD.observe(this,{ catfav ->
            rvFav.adapter = FavoriteAdapter(catfav)
        })
    }
}