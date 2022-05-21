package com.github.allepilli.pokedextestapp.models.pokemonlist

data class PokemonListItem(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>
)