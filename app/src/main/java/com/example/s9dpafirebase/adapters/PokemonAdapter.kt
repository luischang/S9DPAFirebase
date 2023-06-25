package com.example.s9dpafirebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s9dpafirebase.R
import com.example.s9dpafirebase.models.PokemonModel
import com.squareup.picasso.Picasso

class PokemonAdapter(private var pokemonList: List<PokemonModel>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

       class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
           val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
           val tvNamePokemon: TextView = itemView.findViewById(R.id.tvNamePokemon)
           val tvUrlPokemon: TextView = itemView.findViewById(R.id.tvUrlPokemon)
       }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_pokemon,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPokemon = pokemonList[position]
        holder.tvNamePokemon.text = itemPokemon.name
        holder.tvUrlPokemon.text = ""
        Picasso.get().load(itemPokemon.imageUrl).into(holder.ivPokemon)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setData(pokemonList: List<PokemonModel>){
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }


}