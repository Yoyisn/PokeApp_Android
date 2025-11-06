package com.example.pokeapp

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.PokeApiService

class PokeRepository(private val api: PokeApiService) {
    suspend fun getPokemon(name: String): Pokemon = api.getPokemon(name)
}