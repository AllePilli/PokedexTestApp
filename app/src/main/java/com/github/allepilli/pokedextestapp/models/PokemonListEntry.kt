package com.github.allepilli.pokedextestapp.models

data class PokemonListEntry(
    val imageUrl: String,
    val name: String,
    val number: Int,
)

val PokemonListEntry.paddedNumber: String
    get() {
        val numberAsString = "$number"
        val diff = 3 - numberAsString.length
        return "0".repeat(diff) + numberAsString
    }