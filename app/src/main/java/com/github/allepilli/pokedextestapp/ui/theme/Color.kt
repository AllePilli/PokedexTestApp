package com.github.allepilli.pokedextestapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

//val Purple200 = Color(0xFFBB86FC)
//val Purple500 = Color(0xFF6200EE)
//val Purple700 = Color(0xFF3700B3)
//val Teal200 = Color(0xFF03DAC5)

val White = Color(0xFFE5E5E5)
val Dark1 = Color(0xFF1F2029)
val PurpleDark = Color(0xFF46469C)
val PurpleLight = Color(0xFF7E32E0)
val PurpleGradient = Brush.horizontalGradient(
    colors = listOf(PurpleDark, PurpleLight),
)
//linear-gradient(109.73deg, #65CB9A 0%, #15D0DC 100%);
val GreenLight = Color(0xFF65CB9A)
val GreenDark = Color(0xFF15D0DC)
val GreenGradient = Brush.horizontalGradient(
    colors = listOf(GreenLight, GreenDark),
)
val Grey1 = Color(0xFFACB2C1)
val Grey2 = Color(0xFFEFF0F1)
val OrangeLight = Color(0xFFEDCA4D)
val OrangeDark = Color(0xFFE47005)
val OrangeGradient = Brush.horizontalGradient(
    colors = listOf(OrangeLight, OrangeDark),
)