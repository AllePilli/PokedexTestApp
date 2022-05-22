package com.github.allepilli.pokedextestapp.models

import com.github.allepilli.pokedextestapp.remote.responsetypes.Type

data class PokemonListEntry(
    val imageUrl: String,
    val name: String,
    val number: Int,
    val types: List<Type>
)

val PokemonListEntry.paddedNumber: String
    get() {
        val numberAsString = "$number"
        val diff = 3 - numberAsString.length
        return "0".repeat(diff) + numberAsString
    }