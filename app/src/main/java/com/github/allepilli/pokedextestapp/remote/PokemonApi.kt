package com.github.allepilli.pokedextestapp.remote

import com.github.allepilli.pokedextestapp.remote.responsetypes.PokemonDetail
import com.github.allepilli.pokedextestapp.remote.responsetypes.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonList

    @GET("pokemon/{identifier}")
    suspend fun getPokemon(@Path("identifier") identifier: String): PokemonDetail
}