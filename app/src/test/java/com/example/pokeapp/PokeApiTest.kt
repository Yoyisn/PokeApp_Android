package com.example.pokeapp

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.PokeApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.runBlocking
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class PokeApiTest {

    private lateinit var server: MockWebServer
    private lateinit var service: PokeApiService

    @Before
    fun setup() {
        server = MockWebServer().apply { start() }

        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getPokemon_returns_parsed_object() {
        runBlocking {
            val json = """
                {
                  "name": "pikachu",
                  "height": 4,
                  "weight": 60
                }
            """.trimIndent()

            server.enqueue(MockResponse().setResponseCode(200).setBody(json))

            // Asumo que getPokemon retorna Pokemon (no nullable)
            val pokemon: Pokemon = service.getPokemon("pikachu")

            // Aserciones simples (evito isNotNull porque pokemon es no-null)
            expectThat(pokemon.name).isEqualTo("pikachu")
            expectThat(pokemon.height).isEqualTo(4)
            expectThat(pokemon.weight).isEqualTo(60)
        }
    }
}
