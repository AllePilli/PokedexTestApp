package com.github.allepilli.pokedextestapp.remote.responsetypes

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)