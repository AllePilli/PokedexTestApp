package com.github.allepilli.pokedextestapp.controllers

import com.github.allepilli.pokedextestapp.remote.PokemonApi
import com.github.allepilli.pokedextestapp.remote.responsetypes.PokemonDetail
import com.github.allepilli.pokedextestapp.remote.responsetypes.PokemonList
import com.github.allepilli.pokedextestapp.util.Constants.MOCK_BASE_URL
import com.github.allepilli.pokedextestapp.util.Resource
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val mockApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(MOCK_BASE_URL)
        .build()
        .create(PokemonApi::class.java)

    suspend fun getPokemonList(): Resource<PokemonList> {
        val response = try {
            mockApi.getPokemonList()
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "An unknown error has occurred")
        }

        return Resource.Success(response)
    }

    suspend fun getPokemon(identifier: String): Resource<PokemonDetail> {
        val response = try {
            mockApi.getPokemon(identifier)
        } catch (httpException: HttpException) {
            return Resource.Error(message = httpException.message() ?: "An unknown error has occurred")
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "An unknown error has occurred")
        }

        return Resource.Success(response)
    }
}