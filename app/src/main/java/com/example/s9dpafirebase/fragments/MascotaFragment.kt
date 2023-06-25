package com.example.s9dpafirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s9dpafirebase.R
import com.example.s9dpafirebase.adapters.PokemonAdapter
import com.example.s9dpafirebase.models.PokemonModel
import com.example.s9dpafirebase.models.PokemonModelDetails
import com.example.s9dpafirebase.models.PokemonModelResponse
import com.example.s9dpafirebase.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MascotaFragment : Fragment() {

    private lateinit var pokemonAdapter: PokemonAdapter
    private var pokemonList: MutableList<PokemonModel> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_mascota, container, false)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApiService = retrofit.create(PokeApiService::class.java)

        fetchPokemonList(pokeApiService)

        val rvPokemon: RecyclerView = view.findViewById(R.id.rvPokemon)
        rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        pokemonAdapter = PokemonAdapter(pokemonList)
        rvPokemon.adapter = pokemonAdapter
        return view
    }
    private fun fetchPokemonList(pokeApiService: PokeApiService)
    {
        pokeApiService.getPokemonList(0,20)
            .enqueue(object : Callback<PokemonModelResponse>{
                override fun onResponse(
                    call: Call<PokemonModelResponse>,
                    response: Response<PokemonModelResponse>
                ) {
                    val newPokemonList = response.body()?.results ?: emptyList()
                    //Iterar la lista de pokemons para obtener la imagen
                    newPokemonList.forEach{pokemon->
                        pokeApiService.getPokemonDetails(pokemon.name)
                            .enqueue(object: Callback<PokemonModelDetails>{
                                override fun onResponse(
                                    call: Call<PokemonModelDetails>,
                                    response: Response<PokemonModelDetails>
                                ) {
                                    val imageUrl = response.body()?.sprites?.frontDefault ?:""
                                    val pokemonModel = PokemonModel(pokemon.name,imageUrl)
                                    pokemonList.add(pokemonModel)
                                    pokemonAdapter.notifyDataSetChanged()
                                }
                                override fun onFailure(
                                    call: Call<PokemonModelDetails>,
                                    t: Throwable
                                ) {

                                }
                            })
                    }
                    pokemonAdapter.setData(pokemonList)
                }

                override fun onFailure(call: Call<PokemonModelResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}