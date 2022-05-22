package com.github.allepilli.pokedextestapp.remote.responsetypes

data class PokemonListItem(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>
)