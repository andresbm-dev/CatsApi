package com.example.catsapi.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.DetailActivity
import com.example.catsapi.viewModel.CatListViewModel
import com.example.catsapi.R
import javax.inject.Inject

class CatHolder
@Inject constructor (private val view: View,
                     private val catListViewModel: CatListViewModel):
    RecyclerView.ViewHolder(view)  {

    val name = view.findViewById<TextView>(R.id.tvNameCat)
    val btnFav_ = view.findViewById<ImageButton>(R.id.ibToggleFav)



    lateinit var informacion : String
    var url :String = ""
    lateinit var photo: String
    fun bind(catlist : CatListApiItem){

        catlist.name.let{name.text = catlist.name}
        informacion = catlist.description
        url = catlist.wikipedia_url
        photo = catlist.image?.url.orEmpty()
        name.setOnClickListener {
            val intent = Intent(view.context, DetailActivity::class.java).apply {
                putExtra("NAME", "${name.text}")
                putExtra("INFORMACION", informacion)
                putExtra("URL", url)
                putExtra("PHOTO", photo)

            }
            ContextCompat.startActivities(view.context, arrayOf(intent), Bundle.EMPTY)
        }


        if (catlist.isFavorite) {
            //se pinta el corazon lleno rojo
            setupFavorite()
        } else {
            //se pinta el corazon vacio
            setupNotFavorite()
        }

        btnFav_.setOnClickListener {
            catListViewModel.toggleFavorite(cat = catlist)
            catListViewModel.insertAllFavorite(catlist)
        }

    }

    private fun setupFavorite() {
        btnFav_.setImageResource(R.drawable.ic_baseline_favorite_24)
    }

    private fun setupNotFavorite() {
        btnFav_.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }
    }





