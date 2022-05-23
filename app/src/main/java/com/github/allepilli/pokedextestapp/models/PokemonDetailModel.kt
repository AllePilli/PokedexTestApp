package com.github.allepilli.pokedextestapp.models

import com.github.allepilli.pokedextestapp.remote.responsetypes.Ability
import com.github.allepilli.pokedextestapp.remote.responsetypes.Type

data class PokemonDetailModel(
    val name: String = "",
    val imageUrl: String = "",
    val types: List<Type> = listOf(),
    val height: Int = 0,
    val weight: Int = 0,
    val number: Int = 1,
    val abilities: List<Ability> = listOf()
)

val PokemonDetailModel.paddedNumber: String
    get() {
        val numberAsString = "$number"
        val diff = 3 - numberAsString.length
        return "0".repeat(diff) + numberAsString
    }