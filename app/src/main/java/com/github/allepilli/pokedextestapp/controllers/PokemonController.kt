package com.github.allepilli.pokedextestapp.controllers

import com.github.allepilli.pokedextestapp.remote.PokemonApi
import com.github.allepilli.pokedextestapp.remote.pokemonlist.PokemonList
import com.github.allepilli.pokedextestapp.util.Resource
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonApi
) {
    suspend fun getPokemonList(): Resource<PokemonList> {
        val response = try {
            api.getPokemonList()
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occured.")
        }

        return Resource.Success(response)
    }
}