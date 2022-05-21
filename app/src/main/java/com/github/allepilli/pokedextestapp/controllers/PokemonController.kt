package com.github.allepilli.pokedextestapp.controllers

import com.github.allepilli.pokedextestapp.remote.PokemonApi
import com.github.allepilli.pokedextestapp.remote.pokemonlist.PokemonList
import com.github.allepilli.pokedextestapp.util.Constants.BASE_URL
import com.github.allepilli.pokedextestapp.util.Resource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(PokemonApi::class.java)

    suspend fun getPokemonList(): Resource<PokemonList> {
        val response = try {
            api.getPokemonList()
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occured.")
        }

        return Resource.Success(response)
    }
}