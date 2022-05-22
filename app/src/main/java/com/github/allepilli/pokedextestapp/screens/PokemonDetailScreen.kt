package com.github.allepilli.pokedextestapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.github.allepilli.pokedextestapp.models.PokemonDetailViewModel
import com.github.allepilli.pokedextestapp.ui.theme.Dark1
import com.github.allepilli.pokedextestapp.ui.theme.SF_Pro_Display

@Composable
fun PokemonDetailScreen(navController: NavHostController, identifier: String) {
    val viewModel = PokemonDetailViewModel(identifier)
    val pokemon by remember { viewModel.pokemon }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(navController)
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = pokemon.name,
                        fontWeight = FontWeight.Bold,
                        fontFamily = SF_Pro_Display,
                        fontSize = 36.sp,
                        color = Dark1
                    )
                }

                Spacer(modifier = Modifier.height(21.dp))

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon.imageUrl)
                        .build(),
                    contentDescription = pokemon.name,
                    loading = {
                        CircularProgressIndicator()
                    },
                    modifier = Modifier
                        .size(200.dp),
                )
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier
                .size(36.dp),
            onClick = {
                navController.navigateUp()
            }
        ) {
            Icon(Icons.Default.ChevronLeft, "")
        }

        IconButton(
            modifier = Modifier
                .size(36.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Default.FavoriteBorder, "")
        }
    }
}

@Preview
@Composable
fun BulbasaurPreview() {
    PokemonDetailScreen(navController = rememberNavController(), identifier = "1")
}