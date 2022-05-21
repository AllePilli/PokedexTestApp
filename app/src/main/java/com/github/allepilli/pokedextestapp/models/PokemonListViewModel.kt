package com.github.allepilli.pokedextestapp.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.allepilli.pokedextestapp.controllers.PokemonRepository
import com.github.allepilli.pokedextestapp.util.Resource
import kotlinx.coroutines.launch

class PokemonListViewModel: ViewModel() {
    var pokemonList = mutableStateOf<List<PokemonListEntry>>(listOf())

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            when (val result = PokemonRepository.getPokemonList()) {
                is Resource.Success -> {
                    val pokemonListEntries = result.data!!.toList().map { pokemonListItem ->
                        PokemonListEntry(
                            imageUrl = pokemonListItem.sprites.front_default,
                            name = pokemonListItem.name,
                            number = pokemonListItem.id
                        )
                    }

                    pokemonList.value = pokemonListEntries
                }
                is Resource.Error -> {
                    /* TODO */
                }
            }
        }
    }
}