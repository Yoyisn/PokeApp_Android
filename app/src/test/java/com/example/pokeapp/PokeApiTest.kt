package com.example.pokeapp

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.PokeApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.strikt.api.expectThat
import io.strikt.assertions.isEqualTo

class PokeApiTest {
    private lateinit var server: MockWebServer
    private lateinit var service: PokeApiService

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/")) // important
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PokeApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `get pokemon returns parsed object`() {
        val json = """{
          "name": "pikachu",
          "height": 4,
          "weight": 60
        }"""
        server.enqueue(MockResponse().setBody(json).setResponseCode(200))

        val pokemon = service.getPokemon("pikachu")

        expectThat(pokemon.name).isEqualTo("pikachu")
        expectThat(pokemon.height).isEqualTo(4)
        expectThat(pokemon.weight).isEqualTo(60)
    }
}
