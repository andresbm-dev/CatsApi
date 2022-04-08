package com.example.catsapi.viewFavorite.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.R
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.model.Image
import com.example.catsapi.viewModel.CatListViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class FavoriteHolder (private val view: View)
    : RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.tvNameFav)
    val photo = view.findViewById<ImageView>(R.id.ivcat)
    fun bind(catFavorite: CatListApiItem) {
        name.text = catFavorite.name
        val photo_ = catFavorite.image?.url
        Picasso.get().load(photo_).into(photo);

    }
}