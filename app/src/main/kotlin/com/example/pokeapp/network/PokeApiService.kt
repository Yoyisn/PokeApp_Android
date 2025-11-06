package com.example.pokeapp.network

import com.example.pokeapp.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon
}