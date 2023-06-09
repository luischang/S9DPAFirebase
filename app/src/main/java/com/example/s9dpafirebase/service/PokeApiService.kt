package com.example.s9dpafirebase.service

import com.example.s9dpafirebase.models.PokemonModelDetails
import com.example.s9dpafirebase.models.PokemonModelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<PokemonModelResponse>

    @GET("pokemon/{name}")
    fun getPokemonDetails(@Path("name") name: String) : Call<PokemonModelDetails>
}