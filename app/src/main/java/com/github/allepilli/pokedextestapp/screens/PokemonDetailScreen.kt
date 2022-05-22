package com.github.allepilli.pokedextestapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.allepilli.pokedextestapp.models.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(identifier: String) {
    val viewModel = PokemonDetailViewModel()
    val pokemon by remember { viewModel.pokemon }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 9.dp)
        ) {
            TopBar()

            Column(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
            ) {

            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier
                .size(18.dp, 24.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Default.ChevronLeft, "")
        }

        IconButton(
            modifier = Modifier
                .size(18.dp, 24.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Default.FavoriteBorder, "")
        }
    }
}

@Preview
@Composable
fun BulbasaurPreview() {
    PokemonDetailScreen(identifier = "1")
}