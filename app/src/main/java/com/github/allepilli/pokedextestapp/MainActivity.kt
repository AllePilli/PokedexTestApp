package com.github.allepilli.pokedextestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.allepilli.pokedextestapp.screens.Pokedex
import com.github.allepilli.pokedextestapp.screens.PokemonDetailScreen
import com.github.allepilli.pokedextestapp.ui.theme.PokedexTestAppTheme
import com.github.allepilli.pokedextestapp.util.Constants.POKEDEX_SCREEN
import com.github.allepilli.pokedextestapp.util.Constants.POKEMON_DETAIL_SCREEN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTestAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = POKEDEX_SCREEN
                ) {
                    composable(route = POKEDEX_SCREEN) {
                        Pokedex(navController = navController)
                    }

                    composable(
                        route = "$POKEMON_DETAIL_SCREEN/{identifier}",
                        arguments = listOf(
                            navArgument("identifier") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val identifier = remember {
                            it.arguments?.getString("identifier") ?: ""
                        }

                        PokemonDetailScreen(identifier = identifier)
                    }
                }
            }
        }
    }
}