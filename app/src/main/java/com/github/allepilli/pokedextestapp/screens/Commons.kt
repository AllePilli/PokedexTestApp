package com.github.allepilli.pokedextestapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.allepilli.pokedextestapp.remote.responsetypes.Type
import com.github.allepilli.pokedextestapp.remote.responsetypes.TypeX
import com.github.allepilli.pokedextestapp.remote.responsetypes.color
import com.github.allepilli.pokedextestapp.ui.theme.Dark1
import com.github.allepilli.pokedextestapp.ui.theme.Grey1
import com.github.allepilli.pokedextestapp.ui.theme.SF_Pro_Display
import com.github.allepilli.pokedextestapp.ui.theme.White

@Composable
fun PokemonType(type: Type) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .background(
                color = type.color,
                shape = RoundedCornerShape(10.dp)
            )
            .width(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = type.type.name,
            fontFamily = SF_Pro_Display,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = White
        )
    }
}

@Composable
fun TextTableRow(
    text: String,
    rightContent: @Composable () -> Unit
) {
    TableRow(
        leftContent = {
            Text(
                text = text,
                fontFamily = SF_Pro_Display,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Dark1
            )
        },
        rightContent = rightContent
    )
}

@Composable
fun TableRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            leftContent()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            rightContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonTypePreview() {
    PokemonType(type = Type(1, TypeX("Fire")))
}