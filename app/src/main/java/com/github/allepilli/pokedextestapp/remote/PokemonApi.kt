package com.github.allepilli.pokedextestapp.remote

import com.github.allepilli.pokedextestapp.remote.pokemonlist.PokemonList
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonList
}