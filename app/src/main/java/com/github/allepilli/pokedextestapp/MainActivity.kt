package com.github.allepilli.pokedextestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.github.allepilli.pokedextestapp.models.PokemonListEntry
import com.github.allepilli.pokedextestapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainContent {
                Pokedex()
            }
        }
    }
}

@Composable
fun MainContent(content: @Composable () -> Unit) {
    PokedexTestAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Pokedex() {
//    var pokemonList by remember {
//        mutableStateOf<PokemonList?>(null)
//    }
//
//    CoroutineScope(Dispatchers.IO).launch {
//        when (val response = PokemonRepository.getPokemonList()) {
//            is Resource.Success -> {
//                pokemonList = response.data!!
//            }
//            is Resource.Error -> {
//
//            }
//        }
//    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TopBar()

        Text(
            text = "Pokédex",
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )

        PokemonSearchField()
        Spacer(modifier = Modifier.height(19.dp))

        TeamAndFavorites()
        Spacer(modifier = Modifier.height(20.dp))

//        Column {
//            PokedexEntry(
//                entry = PokemonListEntry(
//                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
//                    name = "Bulbasaur",
//                    number = 1
//                ),
//            )
//        }
    }
}

@Composable
fun TopBar() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
    ) {
        IconButton(
            modifier = Modifier.size(18.dp, 20.dp),
            onClick = { Toast.makeText(context, "Filter", Toast.LENGTH_SHORT).show() }
        ) {
            Icon(painterResource(id = R.drawable.ic_filter), "")
        }

        IconButton(
            modifier = Modifier.size(18.dp, 20.dp),
            onClick = { Toast.makeText(context, "Sort", Toast.LENGTH_SHORT).show() }
        ) {
            Icon(painterResource(id = R.drawable.ic_sort), "")
        }
    }
}

@Composable
private fun PokemonSearchField() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Grey2, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_magnifyingglass),
            "",
            modifier = Modifier.padding(8.dp, 10.dp, 6.dp, 10.dp)
        )

        Box(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                value = text,
                onValueChange = {
                    text = it
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Grey1,
                    fontFamily = SF_Pro_Display,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp
                ),
                maxLines = 1
            )

            if (text.isEmpty()) Text(
                modifier = Modifier.padding(vertical = 7.dp),
                text = "Pokemon zoeken",
                color = Grey1,
                style = TextStyle(
                    color = Grey1,
                    fontFamily = SF_Pro_Display,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp
                ),
            )
        }
    }
}

@Composable
fun TeamAndFavorites() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ColoredTabButton(
            title = "Mijn team",
            backgroundBrush = PurpleGradient,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        ColoredTabButton(
            title = "Favorieten",
            backgroundBrush = GreenGradient,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ColoredTabButton(
    title: String = "",
    backgroundBrush: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box (
        modifier = modifier
            .size(167.dp, 100.dp)
            .background(backgroundBrush, RoundedCornerShape(10.dp))
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "",
                )
            }

            Text(
                text = title,
                fontFamily = SF_Pro_Display,
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 32.dp),
            )
        }
    }
}

@Composable
fun PokedexEntry(entry: PokemonListEntry) {
    val number = entry.number.toString()
    val missingZeroes = 3 - number.length
    val displayNumber = "0".repeat(missingZeroes) + number

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .clickable { /* TODO */ },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entry.imageUrl)
                .build(),
            contentDescription = entry.name,
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier
                .size(75.dp),
        )

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = entry.name,
                fontFamily = SF_Pro_Display,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Nr. $displayNumber",
                fontFamily = SF_Pro_Display,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainContent {
        Pokedex()
    }
}