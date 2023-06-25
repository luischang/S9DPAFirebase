package com.example.s9dpafirebase.models

data class PokemonModelResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonModel>
)
