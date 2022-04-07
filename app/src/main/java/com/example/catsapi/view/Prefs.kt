package com.example.catsapi.view

import android.content.Context

class Prefs(context: Context) {
    private val SHARED_NAME = "CatDataBase"
    //clave valor
    val SHARED_FAVORITO = "favorito"
    val SHARED_NO_FAVORITO = "nofavorito"
    val storage = context.getSharedPreferences(SHARED_NAME, 0) //NOMBRE DE BASE DE DATOS Y MODO DE BASE DE DATOS


    //GUARDAR BASE DE DATOS
    fun saveFavorite(favorito: Boolean){
        storage.edit().putBoolean(SHARED_FAVORITO, favorito).apply()  //cuando se vaya agragar un valor
    }
    fun saveNoFavorite( nofavorito:Boolean){
        storage.edit().putBoolean(SHARED_NO_FAVORITO, nofavorito).apply()  //cuando se vaya agragar un valor
    }
    fun getFavorite ():Boolean{
        return storage.getBoolean(SHARED_FAVORITO, false)
    }
    fun getNoFavorite ():Boolean{
        return storage.getBoolean(SHARED_NO_FAVORITO, true)
    }
    fun wipe(){
        storage.edit().clear().apply()
    }


}


