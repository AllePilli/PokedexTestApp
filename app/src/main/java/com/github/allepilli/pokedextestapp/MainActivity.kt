package com.github.allepilli.pokedextestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColoredTabButton(title = "Mijn team", backgroundBrush = PurpleGradient)
            ColoredTabButton(title = "Favorieten", backgroundBrush = GreenGradient)
        }
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
fun ColoredTabButton(
    title: String = "",
    backgroundBrush: Brush
) {
    Button(
        modifier = Modifier
            .size(167.dp, 100.dp)
            .background(backgroundBrush, RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = { /*TODO*/ },
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