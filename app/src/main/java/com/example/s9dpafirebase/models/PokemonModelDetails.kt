package com.example.s9dpafirebase.models

import com.google.gson.annotations.SerializedName

data class PokemonModelDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: SpriteModel
)
