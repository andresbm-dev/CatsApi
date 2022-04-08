package com.example.catsapi.viewFavorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.R
import com.example.catsapi.adapter.CatHolder
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.viewModel.CatListViewModel
import javax.inject.Inject

class FavoriteAdapter (private val catFavorite : List<CatListApiItem> ):RecyclerView.Adapter<FavoriteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteHolder(layoutInflater.inflate(R.layout.itemfav, parent, false)) }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        val item = catFavorite[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int =  catFavorite.size



}
