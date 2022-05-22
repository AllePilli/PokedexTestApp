package com.github.allepilli.pokedextestapp.models

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.github.allepilli.pokedextestapp.controllers.PokemonRepository
import com.github.allepilli.pokedextestapp.ui.theme.White
import com.github.allepilli.pokedextestapp.util.Resource
import kotlinx.coroutines.launch

class PokemonDetailViewModel(identifier: String): ViewModel() {
    var pokemon = mutableStateOf(PokemonDetailModel("", ""))

    init {
        loadPokemon(identifier)
    }

    private fun loadPokemon(identifier: String) {
        viewModelScope.launch {
            when (val result = PokemonRepository.getPokemon(identifier)){
                is Resource.Success -> {
                    pokemon.value = result.data!!.let { pokemonDetail ->
                        PokemonDetailModel(
                            name = pokemonDetail.name.replaceFirstChar(Char::uppercaseChar),
                            imageUrl = pokemonDetail.sprites.other.`official-artwork`.front_default
                        )
                    }
                }
                is Resource.Error -> TODO()
            }
        }
    }

    fun loadDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val copy = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(copy).generate {
            it?.getDominantColor(White.toArgb())?.let { dominantColor ->
                onFinish(Color(dominantColor))
            }
        }
    }
}