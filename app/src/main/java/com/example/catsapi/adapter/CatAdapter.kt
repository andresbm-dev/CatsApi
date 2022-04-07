package com.example.catsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.model.CatListApiItem
import com.example.catsapi.viewModel.CatListViewModel
import com.example.catsapi.R

import javax.inject.Inject

class CatAdapter
    @Inject constructor(private val catList : List<CatListApiItem>, private val catListViewModel: CatListViewModel)
        : RecyclerView.Adapter<CatHolder> (){

        private val listcat: MutableList<CatListApiItem> = mutableListOf()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return CatHolder(layoutInflater.inflate(R.layout.itemcat, parent, false), catListViewModel )
        }

        override fun onBindViewHolder(holder: CatHolder, position: Int) {
            val item = catList[position]
            holder.bind(item)

        }

        override fun getItemCount(): Int = catList.size

    }
